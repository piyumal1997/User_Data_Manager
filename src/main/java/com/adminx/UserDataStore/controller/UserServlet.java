package com.adminx.UserDataStore.controller;

import com.adminx.UserDataStore.model.User;
import com.adminx.UserDataStore.dao.Userdao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import static java.lang.System.out;
//import java.lang.*;


@WebServlet("/")

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Userdao Userdaos;   
   
	public void init(ServletConfig config) throws ServletException {
		Userdaos = new Userdao();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch(action) {
			case"/new":
				showNewForm(request, response);
				break;
			case"/insert":
				insertUser(request, response);
				break;
			case"/delete":
				deleteUser(request, response);
				break;
			case"/edit":
				showEditForm(request, response);
				break;
			case"/update":
				updateUser(request, response);
				break;
			case "/view":
				showNICForm(request, response);
				break;
			case"/gen":
				showNic(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	
	}
	
	

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> ListUser = Userdaos.selectAllUsers();
		request.setAttribute("listUser", ListUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String id = request.getParameter("id");
		User existingUser = Userdaos.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
	
	private void showNICForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("nicshow.jsp");
		dispatcher.forward(request, response);
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String nationality = request.getParameter("nationality");
		String nic = request.getParameter("nic");
		
		String call = null;
		
		if ((nic.length() == 10 && nic.trim().matches("^[0-9]{9}[vVxX]$")) || (nic.length() == 12 && nic.matches("[0-9]{12}$"))){
			
			String[] results = new String[2]; 
			String nic0 = nic;
			
			if (nic0.length() == 10) {
	            results[0] = nic0.substring(0, 2);
	            results[1] = nic0.substring(2, 5);
	        } else if (nic0.length() == 12) {
	            results[0] = nic0.substring(0, 4);
	            results[1] = nic0.substring(4, 7);
	        }
			
			int nic1 = Integer.parseInt(results[1]);
	        
	        if (!(nic1 < 1 || nic1 > 866)) {
	        	
	        	User newUser = new User(name, address, nationality, nic);
	    		Userdaos.insertUser(newUser);
	    		response.sendRedirect("list");
	    		
	        } else {
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("eshow.jsp");
	        	request.setAttribute("name", "NIC number not valid!");
	        	request.setAttribute("name0", "Your NIC number is not Valid range. Please try again!");
	    		dispatcher.forward(request, response);

	        }
        } else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("eshow.jsp");
        	request.setAttribute("name", "NIC number not valid!");
        	request.setAttribute("name0", "Your NIC number doesn't have Max length or Valid characters. Please try again!");
    		dispatcher.forward(request, response);
        }
		
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String nationality = request.getParameter("nationality");
		String nic = request.getParameter("nic");
		String id = request.getParameter("id");
		
		if ((nic.length() == 10 && nic.trim().matches("^[0-9]{9}[vVxX]$")) || (nic.length() == 12 && nic.matches("[0-9]{12}$"))){
			
			String[] results2 = new String[2];
			String nic1 = nic;
			
			if (nic1.length() == 10) {
	            results2[0] = nic1.substring(0, 2);
	            results2[1] = nic1.substring(2, 5);
	        } else if (nic1.length() == 12) {
	            results2[0] = nic1.substring(0, 4);
	            results2[1] = nic1.substring(4, 7);
	        }
			
			int nic2 = Integer.parseInt(results2[1]);
	        
	        if (!(nic2 < 1 || nic2 > 866)) {
	        	User updateUsers = new User(id, name, address, nationality, nic);
	    		Userdaos.updateUser(updateUsers);
	    		response.sendRedirect("list");
	        } else {
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("eshow.jsp");
	        	request.setAttribute("name", "NIC number not valid!");
	        	request.setAttribute("name0", "Your NIC number is not Valid range. Please try again!");
	    		dispatcher.forward(request, response);
	        }
        } else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("eshow.jsp");
        	request.setAttribute("name", "NIC number not valid!");
        	request.setAttribute("name0", "Your NIC number doesn't have Max length or Valid characters. Please try again!");
    		dispatcher.forward(request, response);
        }

		
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		String id = request.getParameter("id");
		
		Userdaos.deleteUser(id);
		response.sendRedirect("list");

	}
	
	private void showNic(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String nic = request.getParameter("nic");
		
		if ((nic.length() == 10 && nic.trim().matches("^[0-9]{9}[vVxX]$")) || (nic.length() == 12 && nic.matches("[0-9]{12}$"))){
			
			String[] results3 = new String[2];
			String[] results4 = new String[5];
			String nic3 = nic;
			
			if (nic.length() == 10) {
	            results3[0] = nic3.substring(0, 2);
	            results3[1] = nic3.substring(2, 5);
	        } else if (nic.length() == 12) {
	            results3[0] = nic3.substring(0, 4);
	            results3[1] = nic3.substring(4, 7);
	        }
			
			int nic4 = Integer.parseInt(results3[1]);
	        
	        if (!(nic4 < 1 || nic4 > 866)) {
	        	
		        String gender;
		        int day = 0;
		        String month = null;
		        int dayText = Integer.parseInt(results3[1]); 
		        int age = 0;
		        int years = 0;
		        int year = Calendar.getInstance().get(Calendar.YEAR);
		        
		        //Gender
		        if(dayText < 500){
		            gender = "Male";
		        } else{
		            gender = "Female";
		            dayText = dayText - 500;
		        }
		        
		        
		        //Day & Month 
		        if (dayText > 335) {
		            day = dayText - 335;
		            month = "December";
		        }
		        else if (dayText > 305) {
		            day = dayText - 305;
		            month = "November";
		        }
		        else if (dayText > 274) {
		            day = dayText - 274;
		            month = "October";
		        }
		        else if (dayText > 244) {
		            day = dayText - 244;
		            month = "September";
		        }
		        else if (dayText > 213) {
		            day = dayText - 213;
		            month = "Auguest";
		        }
		        else if (dayText > 182) {
		            day = dayText - 182;
		            month = "July";
		        }
		        else if (dayText > 152) {
		            day = dayText - 152;
		            month = "June";
		        }
		        else if (dayText > 121) {
		            day = dayText - 121;
		            month = "May";
		        }
		        else if (dayText > 91) {
		            day = dayText - 91;
		            month = "April";
		        }
		        else if (dayText > 60) {
		            day = dayText - 60;
		            month = "March";
		        }
		        else if (dayText < 32) {
		            month = "January";
		            day = dayText;
		        }
		        else if (dayText > 31) {
		            day = dayText - 31;
		            month = "Febuary";
		        }
		        
		        if(results3[0].length() == 2) {
		        	results3[0] = "19" + results3[0];
		        	years = Integer.parseInt(results3[0]);
		        	
		        	age = year - years;
		        	
		        }else {
		        	years = Integer.parseInt(results3[0]);
		        	age = year - years;
		        }
		        
		        results4[0] = String.valueOf(day);
		        results4[1] = month;
		        results4[2] = gender;
		        results4[3] = String.valueOf(age);
		        results4[4] = results3[0] + " / " + results4[1] + " / " + results4[0];
		        
		        
		        RequestDispatcher dispatcher = request.getRequestDispatcher("nicshow.jsp");
		        request.setAttribute("name1", results4[4]);
		        request.setAttribute("name2", results4[3]);
		        request.setAttribute("name3", results4[2]);
	    		dispatcher.forward(request, response);
	    		
	        } else {
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("eshow.jsp");
	        	request.setAttribute("name", "NIC number not valid!");
	        	request.setAttribute("name0", "Your NIC number is not Valid range. Please try again!");
	    		dispatcher.forward(request, response);
	        }
        } else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("eshow.jsp");
        	request.setAttribute("name", "NIC number not valid!");
        	request.setAttribute("name0", "Your NIC number doesn't have Max length or Valid characters. Please try again!");
    		dispatcher.forward(request, response);
        }
		
	}
	
}
