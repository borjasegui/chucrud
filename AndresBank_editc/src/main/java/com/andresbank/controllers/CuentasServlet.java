package com.andresbank.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andresbank.dao.CuentaDAO;
import com.andresbank.ddbb.DDBB;

public class CuentasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("dni") != null) {
			String dni = (String) request.getSession().getAttribute("dni");
			
			try {
				request.setAttribute("lista_cuentas", CuentaDAO.getInstance().getCuentasByDni(dni));
			} catch (Exception e) {
				System.out.println("Excepcion:"+e.getMessage());
			}
			
			request.getRequestDispatcher("/cuentas.jsp").forward(request, response);
		} else {
			request.getSession().invalidate();
			response.sendRedirect("login");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
