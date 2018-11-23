package com.andresbank.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andresbank.dao.CuentaDAO;
import com.andresbank.models.Cuenta;

@WebServlet("/editarcuenta")
public class EditarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("dni") != null) {

			try {
				String cidrec = request.getParameter("cid");

				int cidInt = Integer.parseInt(cidrec);

				Cuenta miCuenta = CuentaDAO.getInstance().getCuentaByCid(cidInt);

				request.setAttribute("micuenta", miCuenta);
				request.getRequestDispatcher("/editarcuenta.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
				request.setAttribute("mensaje_error", "Ooops...intentalo más tarde");
				
			}
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("dni") != null) {

			try {
				String cidrec = request.getParameter("cid");
				String nombrerec = request.getParameter("nombre");
				String numerorec = request.getParameter("numero");
				String saldorec = request.getParameter("saldo");

				int cidInt = Integer.parseInt(cidrec);
				double saldoDbl = Double.parseDouble(saldorec);

				Cuenta miCuenta = new Cuenta(cidInt, nombrerec, numerorec, saldoDbl);

				boolean isOK= CuentaDAO.getInstance().actualizarCuenta(miCuenta);
				
				if(isOK) {
					request.setAttribute("mensaje_ok", "Tu cuenta ha sido actualizada");
					response.sendRedirect("cuentas");
				}else {
					request.setAttribute("mensaje_error", "Verifica tus datos...tu cuenta no actualizada");
				}
				
				request.setAttribute("micuenta", miCuenta);

			} catch (Exception e) {
				System.out.println("Exception:" + e.getMessage());
				request.setAttribute("mensaje_error", "Ooops...intentalo más tarde");

			}

			doGet(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

}
