package com.andresbank.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andresbank.dao.CuentaDAO;
import com.andresbank.models.Cuenta;

public class BorrarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("dni") != null) {

			try {
				String cidrec = request.getParameter("cid");
				int cidInt = Integer.parseInt(cidrec);

				boolean isOK = CuentaDAO.getInstance().borrarCuenta(cidInt);
				response.sendRedirect("cuentas");
			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
				request.setAttribute("mensaje_error", "Ooops...intentalo más tarde");
			}

		} else {
			response.sendRedirect("cuentas");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
