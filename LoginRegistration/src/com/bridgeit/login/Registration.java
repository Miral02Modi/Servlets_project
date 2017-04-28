package com.bridgeit.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class HomePage1
 */
public class Registration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		String strName = request.getParameter("Name");
		String strPassword = request.getParameter("password");
		String strMobileNumber = request.getParameter("phoneNumber");
		String strDepartment = request.getParameter("dName");
		String strBirthday =  request.getParameter("bday");
		String strGender = request.getParameter("gender");
		String strDept = request.getParameter("dName");
		String stremail = request.getParameter("emailid");
		
		
		System.out.println(strId+strName+strPassword+strMobileNumber+strDepartment+strBirthday+strGender+strDept+stremail);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertQuery = "insert into Student.Empregistration values(?,?,?,?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306?user=root&password=ninja");
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, strName);
			preparedStatement.setString(3, strPassword);
			preparedStatement.setString(4, strBirthday);
			preparedStatement.setString(5, stremail);
			preparedStatement.setString(6, strMobileNumber);
			preparedStatement.setString(7,strGender);
			preparedStatement.setString(8,strDepartment	);
			preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
