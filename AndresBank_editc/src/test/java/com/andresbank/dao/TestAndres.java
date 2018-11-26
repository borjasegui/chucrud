package com.andresbank.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class TestAndres {
	private static Logger logger = Logger.getLogger("Test");
	
	public static void setUpContext(){
	
		try {
			 // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
    		InitialContext ic=null;
    		try{
    			 ic = new InitialContext();
    		}
    		catch (NamingException ex){
    		  	logger.log(Level.SEVERE,ex.getMessage());
    			ex.printStackTrace();
    		}

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");
           
            // Construct DataSource
            MysqlDataSource  ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost/andresbank");
            ds.setUser( "borjauser" );
            ds.setPassword( "brjsr" );
            
            ic.bind("java:/comp/env/jdbc/andresbbdd", ds);
        } catch (NamingException ex) {
        	logger.log(Level.SEVERE,ex.getMessage());
        	ex.printStackTrace();
        } catch (Exception ex) {
        	logger.log(Level.SEVERE,ex.getMessage());
        	ex.printStackTrace();
		}
	}
}