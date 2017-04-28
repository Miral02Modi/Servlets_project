package com.bridgeit.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class LoginDemo
 */
public class LoginDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password").trim();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String select = "Select * from Student.Empregistration where EmailId=? and password=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306?user=root&password=ninja");
			preparedStatement = connection.prepareStatement(select);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			int getId = 0;
			String getName = "";
			String getMobileNumber = "";
			String getGender = "";
			String deprtment="";
			PrintWriter printWriter = response.getWriter();

			boolean flag = true;
			while (resultSet.next()) {
				flag = false;

				getId = resultSet.getInt(1);
				getName = resultSet.getString(2);
				getMobileNumber = resultSet.getString(6);
				getGender = resultSet.getString(7);
				deprtment = resultSet.getString(8);

				printWriter.println("<html><body bgcolor=green>Successful login");
				printWriter.println("<h2>" + getId + "</h2>");
				printWriter.println("<h2>" + getName + "</h2>");
				printWriter.println("<h2>" + getMobileNumber + "</h2>" + "</body> </html>");

			}

			if (flag == true) {
				System.out.println("No data is found::");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.html");
				requestDispatcher.forward(request, response);
			}
			printWriter.flush();
			printWriter.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
