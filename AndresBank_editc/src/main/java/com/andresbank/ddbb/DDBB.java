package com.andresbank.ddbb;

import java.util.ArrayList;

import com.andresbank.models.Cliente;
import com.andresbank.models.Cuenta;
import com.andresbank.models.Nomina;

public class DDBB {

	private static DDBB instance = null;
	private ArrayList<Cliente> listaclientes;
	private ArrayList<Cuenta> listacuentas;
	private ArrayList<Nomina> listanominas;

	public static DDBB getInstance() {
		if (instance == null)
			instance = new DDBB();
		return instance;
	}

	private DDBB() {
		this.fillClientes();
		this.fillCuentas();
		this.fillNominas();
		
		this.relateClientesCuentas();
	}

	private boolean fillClientes() {
		listaclientes = new ArrayList<Cliente>();
		listaclientes.add(new Cliente(1, "Pepe", "12345678P", "ppp"));
		listaclientes.add(new Cliente(2, "Jeny", "12345678J", "jjj"));
		listaclientes.add(new Cliente(3, "Rupert", "12345678R", "rrr"));
		return true;
	}

	private boolean fillCuentas() {
		listacuentas = new ArrayList<Cuenta>();
		listacuentas.add(new Cuenta(1, "C1", "c1-23456", 100));
		listacuentas.add(new Cuenta(2, "C2", "c2-23456", 50000));
		listacuentas.add(new Cuenta(3, "C3", "c3-23456", 100000));
		return true;
	}

	private boolean fillNominas() {
		return true;
	}
		
	private boolean relateClientesCuentas() {
		listaclientes.get(0).setCuentas(new ArrayList<Cuenta>());
		listaclientes.get(0).getCuentas().add(listacuentas.get(0));
		
		listaclientes.get(1).setCuentas(new ArrayList<Cuenta>());
		listaclientes.get(1).getCuentas().add(listacuentas.get(1));
		listaclientes.get(1).getCuentas().add(listacuentas.get(2));
		
		return true;
	}

	public Cliente getUserByDNIAndPass(String dnirec, String passrec) {
		Cliente resCliente=null;
		
		for (Cliente cliente : listaclientes) {
			if(cliente.getDni().equals(dnirec) && cliente.getPin().equals(passrec)) {
				resCliente=cliente;
				break;
			}
		}
		
		return resCliente;

	}
	
	public ArrayList<Cuenta> getCuentasByDni(String dni){
		ArrayList<Cuenta> cuentasEncontradas=null;
		
		for (Cliente cliente : this.listaclientes) {
			if(cliente.getDni().equals(dni)) {
				cuentasEncontradas=cliente.getCuentas();
				break;
			}
		}
		
		return cuentasEncontradas;
	}
	
	

}
