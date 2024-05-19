package com.adminx.UserDataStore.dao;

import com.adminx.UserDataStore.model.User;

import java.sql.*;
import java.util.*;


public class Userdao {
	
	private static String dburl = "jdbc:mysql://localhost:3306/personsdata?useSSL=false";
	private static String username = "root";
	private static String password = "1234";
	
	private static final String INSERT = "INSERT INTO person (name, address, nationality, nic) VALUES (?, ?, ?, ?);";
	private static final String DELETE = "DELETE FROM person WHERE id = ?;";
	private static final String UPDATE = "UPDATE person SET name = ?,address= ?, nationality= ?, nic= ? WHERE id = ?";

	private static final String SELECT_BY_ID = "SELECT name,address,nationality,nic FROM person WHERE id =?";
	private static final String SELECT_ALL = "SELECT * FROM person";
	
	
	public Userdao() {
		
	}
	
	//Connection Creator
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return connection;
	}
	
	
	//Insert user
	public void insertUser(User user) throws SQLException {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getAddress());
			preparedStatement.setString(3, user.getNationality());
			preparedStatement.setString(4, user.getNic());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		
	}
	
	//Update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE);) {
			
			System.out.println("updated User:"+statement);
			statement.setString(1, user.getName());
			statement.setString(2, user.getAddress());
			statement.setString(3, user.getNationality());
			statement.setString(4, user.getNic());
			statement.setString(5, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//Select user by id
	public User selectUser(String id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);) {
			preparedStatement.setString(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String nationality = rs.getString("nationality");
				String nic = rs.getString("nic");
				user = new User(id, name, address, nationality, nic);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	//Delete user
	public boolean deleteUser(String id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE);) {
			statement.setString(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	//Select
	public List<User> selectAllUsers() {

		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String nationality = rs.getString("nationality");
				String nic = rs.getString("nic");
				users.add(new User(id, name, address, nationality, nic));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
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
