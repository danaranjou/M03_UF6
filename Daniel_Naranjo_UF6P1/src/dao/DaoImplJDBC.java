package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Employee;

public class DaoImplJDBC implements Dao {

	private Connection connection;

// Método para Connectar con la Base de Datos
	@Override
	public void connect() throws SQLException {
// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "root";
		String pass = "";
		this.connection = DriverManager.getConnection(url, user, pass);
	}

	@Override
	public Employee getEmployee(int employeeId, String password) {
		Employee employee = null;
// prepare query
		String query = "select * from employee where employeeId = ? ";

		try (PreparedStatement ps = connection.prepareStatement(query)) {
// set id to search for
			ps.setInt(1, employeeId);
			// System.out.println(ps.toString());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					employee = new Employee();
				}
			}
		} catch (SQLException e) {
// in case error in SQL
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}