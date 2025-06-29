package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Database;
import database.DatabaseException;
import models.entities.Contact;

public class ContactDao {

	public void insert(Contact contact) {
		Connection connection = Database.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement("INSERT INTO contatos (nome, fone, email) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, contact.getName());
			statement.setString(2, contact.getPhone());
			statement.setString(3, contact.getEmail());
			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					contact.setId(id);
				}
			} else {
				throw new DatabaseException("Ocorreu um erro ao tentar salvar o contato");
			}
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} finally {
			Database.closeConnection(connection);
			Database.closeStatement(statement);
			Database.closeResultSet(resultSet);
		}
	}

	public ArrayList<Contact> findAll() {
		Connection connection = Database.getConnection(); 
		PreparedStatement statement = null;
	    ResultSet resultSet = null;
        ArrayList<Contact> list = new ArrayList<Contact>();
	    try {
	    	statement = connection.prepareStatement("SELECT * FROM contatos ORDER BY nome");
	            
	    	resultSet = statement.executeQuery();
				
	        while (resultSet.next()) {
	           var contact = new Contact();
	           contact.setId(resultSet.getInt(1));
	           contact.setName(resultSet.getString(2));
	           contact.setPhone(resultSet.getString(3));
	           contact.setEmail(resultSet.getString(4));
	           list.add(contact);
	        }
	        
		} catch(SQLException e) {
			e.printStackTrace(); // ou log
			return new ArrayList<>(); // evita NullPointerException
	    } finally {
	    	Database.closeConnection(connection);
	    	Database.closeStatement(statement);
	    	Database.closeResultSet(resultSet);
	    }
	    return list;
	}
}
