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
@WebServlet(urlPatterns =  { "/ContactController", "/contacts/list", "/contacts/create", "/contacts/edit", "/contacts/update", "/contacts/delete" })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ContactDao contactDao;
	
    public ContactController() {
        super();
        this.contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/contacts/list")) {
			listContacts(request, response);
		}
		else if (action.equals("/contacts/create")) {
			request.getRequestDispatcher("/new-contact.jsp").forward(request, response);
		}
		else if (action.equals("/contacts/edit")) {
			editContact(request, response);
		}
		else if (action.equals("/contacts/delete")) {
			deleteContact(request, response);
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página não encontrada");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/contacts/create")) {
			createContact(request, response);
		}
		else if (action.equals("/contacts/update")) {
			updateContact(request, response);
		}
		
	}
	
	private void createContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var contact = new Contact(
				request.getParameter("name"),
				request.getParameter("phone"),
				request.getParameter("email")
		);
		
		try {
			contactDao.insert(contact);
			response.sendRedirect(request.getContextPath() + "/contacts/list");			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Erro ao salvar contato");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	private void listContacts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var contacts = contactDao.findAll();
		request.setAttribute("contacts", contacts);
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}
	
	private void editContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("contact", contactDao.findById(id));
			request.getRequestDispatcher("/edit-contact.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Erro ao buscar contato");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}
	
	private void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var contact = new Contact(
				Integer.parseInt(request.getParameter("id")),
				request.getParameter("name"),
				request.getParameter("phone"),
				request.getParameter("email")
		);
		
		try {
			contactDao.update(contact);
			response.sendRedirect(request.getContextPath() + "/contacts/list");			
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Erro ao salvar contato");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
	private void deleteContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			contactDao.deleteById(id);
			response.sendRedirect(request.getContextPath() + "/contacts/list");
		}
		catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Erro ao remover contato");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

}
