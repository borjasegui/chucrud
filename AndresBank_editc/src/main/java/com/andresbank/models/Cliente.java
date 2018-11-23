package com.andresbank.models;

import java.util.ArrayList;

public class Cliente {
	private int uid;
	private String nombre;
	private String dni;
	private String pin;
	private ArrayList<Cuenta> cuentas; 
	private Nomina nomina;
	
	public Cliente(int uid, String nombre, String dni, String pin) {
		super();
		this.uid = uid;
		this.nombre = nombre;
		this.dni = dni;
		this.pin = pin;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Nomina getNomina() {
		return nomina;
	}

	public void setNomina(Nomina nomina) {
		this.nomina = nomina;
	}
	
	

}
