package com.andresbank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andresbank.models.Cliente;

public class ClienteDAO extends DAO{
	
	private static ClienteDAO instance = null;

	public static ClienteDAO getInstance() throws Exception {
		if (instance == null)
			instance = new ClienteDAO();

		return instance;
	}

	private ClienteDAO() throws Exception {//la funcion del metodo esta en el padre 
		
	}

	public Cliente getUserByDNIAndPass(String dnirec, String passrec) throws SQLException {
		Cliente resCliente = null;

	

		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();

		// luego hago peticiones
		String sql="SELECT uid, nombre, dni, pin, nomina FROM cliente WHERE dni=? AND pin=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, dnirec);
		psmt.setString(2, passrec);
		
		ResultSet rs = psmt.executeQuery();
		
		System.out.println("Resultset:"+rs);
		
		while(rs.next()){
			System.out.println("Resultset:"+rs.getInt(1)+"::"+ rs.getString(2));
			
			resCliente = new Cliente(
				rs.getInt(1), 
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4)
			);
		}
		
		rs.close();
		psmt.close();		
		conn.close();

		return resCliente;
	}

	public Cliente getUserByDNI(String dnisess) throws SQLException {
		Cliente resCliente = null;

		

		//Connection conn = DriverManager.getConnection(url, "root", "root");
		Connection conn = datasource.getConnection();

		// luego hago peticiones
		String sql="SELECT uid, nombre, dni, pin, nomina FROM cliente WHERE dni=? LIMIT 1";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, dnisess);
		
		ResultSet rs = psmt.executeQuery();
		
		System.out.println("Resultset:"+rs);
		
		if(rs.next()){
			System.out.println("Resultset:"+rs.getInt(1)+"::"+ rs.getString(2));
			
			resCliente = new Cliente(
				rs.getInt(1), 
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4)
			);
		}
		
		rs.close();
		psmt.close();		
		conn.close();

		return resCliente;
	}
}
