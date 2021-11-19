package com.xadmin.petmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.petmanagement.bean.Pet;
import com.xadmin.petmanagement.dao.PetDao;

/**
 * Servlet implementation class PetServlet
 */
@WebServlet("/")
public class PetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PetDao petDAO;
       

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		
		petDAO = new PetDao();
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertPet(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		case "/delete":
			try {
				deletePet(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		case "/update":
			try {
				updatePet(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			break;
		default:
			try {
				listPet(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pet-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertPet(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String age = request.getParameter("age");
		String breed = request.getParameter("breed");
		Pet newPet = new Pet(name, type, age, breed);
			petDAO.insertPet(newPet);
		response.sendRedirect("list");
	}
	

	private void deletePet(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			petDAO.deletePet(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("list");

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Pet existingPet;
		
		try {
			existingPet = petDAO.selectPet(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("pet-form.jsp");
			request.setAttribute("pet", existingPet);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	
		
		
		

	}
	
	private void updatePet(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String age = request.getParameter("age");
		String breed = request.getParameter("breed");

		Pet pet = new Pet(id, name, type, age, breed);
		petDAO.updatePet(pet);
		response.sendRedirect("list");
	}
	
	private void listPet(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		try {
			List<Pet> listPet = petDAO.selectAllPets();
			request.setAttribute("listPet", listPet);
			RequestDispatcher dispatcher = request.getRequestDispatcher("pet-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
}
	



