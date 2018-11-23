package com.andresbank.models;

public class Cuenta {
	
	private int cid;
	private String nombre;
	private String numero;
	private double saldo;
	
	public Cuenta(int cid, String nombre, String numero, double saldo) {
		super();
		this.cid = cid;
		this.nombre = nombre;
		this.numero = numero;
		this.saldo = saldo;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	

}
