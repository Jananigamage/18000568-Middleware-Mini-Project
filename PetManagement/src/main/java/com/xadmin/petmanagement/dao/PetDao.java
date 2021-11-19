package com.xadmin.petmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.petmanagement.bean.Pet;

public class PetDao {
	
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/petdb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static final String INSERT_PETS_SQL = "INSERT INTO pets" + "  (name, type, age, breed) VALUES "
			+ " (?, ?, ?,?);";

	private static final String SELECT_PET_BY_ID = "select id,name,type,age,breed from pets where id =?";
	private static final String SELECT_ALL_PETS = "select * from pets";
	private static final String DELETE_PETS_SQL = "delete from pets where id = ?;";
	private static final String UPDATE_PETS_SQL = "update pets set name = ?,type= ?, age =?, breed =? where id = ?;";
	
	public PetDao() {
	
	}
	
	protected Connection getConnection() { 
		
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	
	public void insertPet(Pet pet) throws SQLException {
		System.out.println(INSERT_PETS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PETS_SQL)) {
			preparedStatement.setString(1, pet.getName());
			preparedStatement.setString(2, pet.getType());
			preparedStatement.setString(3, pet.getAge());
			preparedStatement.setString(4, pet.getBreed());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public Pet selectPet(int id) {
		Pet pet = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PET_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String type = rs.getString("type");
				String age = rs.getString("age");
				String breed = rs.getString("breed");
				pet = new Pet(id, name, type, age, breed);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pet;
	}
	
	public List<Pet> selectAllPets() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Pet> pets = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PETS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				String age = rs.getString("age");
				String breed = rs.getString("breed");
				pets.add(new Pet(id, name, type, age, breed));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return pets;
	}
	
	
	public boolean updatePet(Pet pet) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PETS_SQL);) {
			System.out.println("updated Pet:"+statement);
			statement.setString(1, pet.getName());
			statement.setString(2, pet.getType());
			statement.setString(3, pet.getAge());
			statement.setInt(4, pet.getId());
			statement.setString(5, pet.getBreed());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean deletePet(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PETS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	

//	private void printSQLException(SQLException e) {
//		// TODO Auto-generated method stub
//		
//	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

		
}


