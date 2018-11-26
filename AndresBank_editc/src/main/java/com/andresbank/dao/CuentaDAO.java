package com.andresbank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.andresbank.models.Cliente;
import com.andresbank.models.Cuenta;

public class CuentaDAO extends	DAO{
	

	private static CuentaDAO instance = null;

	public static CuentaDAO getInstance() throws Exception {
		if (instance == null)
			instance = new CuentaDAO();

		return instance;
	}

	private CuentaDAO() throws Exception {
		
	}

	public ArrayList<Cuenta> getCuentasByDni(String dni) throws SQLException {
		ArrayList<Cuenta> rescuentas = null;

		

		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();

		// luego hago peticiones
		String sql = "SELECT c.cid,c.nombre,c.numero,c.saldo FROM cuenta c, cliente_cuenta cc, cliente cl WHERE c.cid=cc.cuenta AND cc.cliente=cl.uid AND cl.dni=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, dni);

		ResultSet rs = psmt.executeQuery();
		System.out.println("Resultset:" + rs);

		rescuentas = new ArrayList<Cuenta>();

		while (rs.next()) {
			System.out.println("Resultset:" + rs.getInt(1) + "::" + rs.getString(2) + "::" + rs.getString(3));

			rescuentas.add(new Cuenta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
		}

		System.out.println("Arraylist resultante:" + rescuentas);

		rs.close();
		psmt.close();
		conn.close();

		return rescuentas;
	}

	public int crearCuenta(Cuenta cuenta, Cliente cliente) throws SQLException {
		int cidres = 0;

		String url = "jdbc:mysql://localhost/andresbank";

		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();

		conn.setAutoCommit(false);

		try {
			// INSERTAR CUENTA
			String sql = "INSERT INTO cuenta (nombre, numero, saldo) VALUES (?, ?, ?)";
			PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, cuenta.getNombre());
			psmt.setString(2, cuenta.getNumero());
			psmt.setDouble(3, cuenta.getSaldo());

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				cidres = rs.getInt(1);
			}

			rs.close();
			psmt.close();

			System.out.println("Creada cuenta..." + cidres);

			// INSERTAR CLIENTE_CUENTA
			sql = "INSERT INTO cliente_cuenta (cliente, cuenta) VALUES (?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, cliente.getUid());
			psmt.setInt(2, cidres);

			psmt.executeUpdate();
			psmt.close();

			conn.commit();
		} catch (Exception e) {
			System.out.println("Excepcion tx:" + e.getMessage());
			cidres = 0;
			conn.rollback();
			throw new SQLException();
		}

		conn.close();

		return cidres;
	}

	public Cuenta getCuentaByCid(int cid) throws SQLException {
		Cuenta rescuenta = null;

		String url = "jdbc:mysql://localhost/andresbank";

		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();

		// luego hago peticiones
		String sql = "SELECT c.cid,c.nombre,c.numero,c.saldo FROM cuenta c WHERE c.cid=? LIMIT 1";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setInt(1, cid);

		ResultSet rs = psmt.executeQuery();

		if (rs.next()) {
			rescuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
		}

		rs.close();
		psmt.close();
		conn.close();

		return rescuenta;
	}

	public boolean actualizarCuenta(Cuenta cuenta) throws SQLException {
		boolean todoOk = false;

		

		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();

		String sql = "UPDATE cuenta SET nombre=?,numero=?,saldo=? WHERE cid=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, cuenta.getNombre());
		psmt.setString(2, cuenta.getNumero());
		psmt.setDouble(3, cuenta.getSaldo());
		psmt.setInt(4, cuenta.getCid());

		psmt.executeUpdate();
		todoOk = true;

		psmt.close();
		conn.close();

		return todoOk;
	}

	public boolean borrarCuenta(int cidInt) throws SQLException {
		
		boolean todoOk = false;
		
//borrar siempre primero la base datos dependiente de la otra
		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();
		
		conn.setAutoCommit(false);
		
		try {
			
	
		String sql = "DELETE FROM cliente_cuenta WHERE cuenta=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, cidInt);
		
		psmt.executeUpdate();
		todoOk = true;
		
		
	    sql = "DELETE FROM cuenta WHERE cid=?";
		psmt = conn.prepareStatement(sql);
		
		psmt.setInt(1, cidInt);
		
		psmt.executeUpdate();
		todoOk = true;

		psmt.close();
		conn.commit();
		} catch (Exception e) {
			System.out.println("Excep tx:" + e.getMessage());
			conn.close();
			throw new SQLException();
		}finally {
			conn.close();
		}
		return todoOk;
		
	}

}
