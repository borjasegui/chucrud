package com.andresbank.dao;

import javax.sql.DataSource;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;

public class DAO {
	
	protected String driverclass ="com.mysql.jdbc.Driver";
	protected String url = "jdbc:mysql://localhost/andresbank";
	protected DataSource datasource = null;
	
	protected DAO() throws Exception{//este es el padreeeee
		//Class.forName(driverclass).newInstance();// new com.mysql.jdbc.Driver
		
		Context initContext =new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		datasource =(DataSource) envContext.lookup("jdbc/andresbbdd");
	}
	

}
