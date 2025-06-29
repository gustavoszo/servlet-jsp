package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dao.ContactDao;

@WebServlet(urlPatterns =  { "/MainController", "/main" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactDao contactDao;
       
    public MainController() {
        super();
        contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var contacts = contactDao.findAll();
		request.setAttribute("contacts", contacts);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}

