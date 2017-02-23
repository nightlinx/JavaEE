package javaee.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.sql.*;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaee.other.DatabaseConnector;
import javaee.other.User;

@WebServlet(urlPatterns = { "/hello" }, asyncSupported = true)
// wytworzenie w ramach metody obsługi (doGet) nowego wątku i wykonanie pewnej
// długotrwałej czynności np połącznie z bazą danych albo skorzystanie z obsługi sieciowej w nowym wątku
// @ServletSecurity(httpMethodConstraints={ @HttpMethodConstraint(value="GET", rolesAllowed = {"admin", "moderator"}, transportGuarantee = TransportGuarantee.NONE)})

public class WitajSwiecieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		DatabaseConnector dc = new DatabaseConnector();
		dc.connect();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}

}
