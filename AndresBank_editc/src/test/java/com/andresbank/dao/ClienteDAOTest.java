package com.andresbank.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.andresbank.models.Cliente;

public class ClienteDAOTest extends TestAndres {

	@BeforeClass
	public static void setUpClass() {
		setUpContext();
	}

	@Test
	public void getUserByDNIAndPassVoid() {
		String pass = "";
		String dni = "";
		Cliente clienteEncontrado;
		try {
			clienteEncontrado = ClienteDAO.getInstance().getUserByDNIAndPass(dni, pass);
			assertNull(clienteEncontrado);

		} catch (Exception e) {
			fail(e.getMessage());
			e.printStackTrace();

		}

	}@Test
	public void getUserByDNIAndPassValid() {
		
		String dni = "12345678P";
		String pass = "pppp";
		Cliente clienteEncontrado;
		
		try {
			clienteEncontrado = ClienteDAO.getInstance().getUserByDNIAndPass(dni, pass);
			boolean dniok = clienteEncontrado.getDni().equals(dni);
			boolean passok =clienteEncontrado.getPin().equals(pass);
			
			
			
			assertTrue(dniok && passok);
			
			
		 } catch (Exception e) {
			
			 fail(e.getMessage());
			 e.printStackTrace();
		}
		


	}

}
