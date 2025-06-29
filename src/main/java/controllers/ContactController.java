package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.dao.ContactDao;
import models.entities.Contact;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(urlPatterns =  { "/ContatactController", "/contacts" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactDao contactDao;
	
    public ContactController() {
        super();
        this.contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("new-contact.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var contact = new Contact(
				request.getParameter("name"),
				request.getParameter("phone"),
				request.getParameter("email")
		);
		
		try {
			contactDao.insert(contact);
			response.sendRedirect("main");			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Erro ao salvar contato");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
