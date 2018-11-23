package com.andresbank.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andresbank.dao.ClienteDAO;
import com.andresbank.ddbb.DDBB;
import com.andresbank.models.Cliente;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dnirec = request.getParameter("dni");
		String passrec = request.getParameter("pass");

		try {

			Cliente unCli = ClienteDAO.getInstance().getUserByDNIAndPass(dnirec, passrec);

			if (unCli != null) {
				request.getSession().setAttribute("dni", dnirec);
				response.sendRedirect("cuentas");
			} else {
				request.setAttribute("mensaje_error", "Usuario incorrecto");
				doGet(request, response);
			}
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			request.setAttribute("mensaje_error", "Ooops...tenemos un problemita...inténtalo más tarde...");
			doGet(request, response);
		}

	}

}
