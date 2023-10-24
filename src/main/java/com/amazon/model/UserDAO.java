package com.amazon.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.amazon.crypto.PasswordEncoder;
import com.amazon.crypto.PasswordValidator;

public class UserDAO {

	private DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// check if user exist
	public boolean checkUserExists(String email) {
		if (getUserByEmail(email) != null) {
			return true;
		}
		return false;
	}

	// check for create if exist email
	private boolean checkEmailExists(String email) {
		if (getUserByEmail(email) != null) {
			return true;
		}
		return false;
	}

	// test , is email?
	public boolean isEmail(String email) {
		if (email.contains("@") && email.contains(".")) {
			return true;
		} else {
			return false;
		}
	}

	// validate user with email and password
	public boolean validateUser(String email, String password) {
		User user = null;

		if (isEmail(email)) {
			user = getUserByEmail(email);
		}

		if (user != null) {
			try {
				if (!PasswordValidator.validatePassword(password, user.getPassword()) || user.getLocked()) {
					return false;
				}
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;

		} else {
			return false;
		}
	}

	// get user by email
	public User getUserByEmail(String email) {
		User user = null;
		try {

			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where email='" + email + "';");
			while (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),
						rs.getString("address"), rs.getString("role"), rs.getBoolean("locked"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return user;

	}

	// get user by name
	public User getUserByName(String name) {
		User user = null;
		try {

			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where name='" + name + "';");
			while (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),
						rs.getString("address"), rs.getString("role"), rs.getBoolean("locked"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return user;

	}

	// get user by id
	public User getUserById(Long id) {
		User user = null;
		try {

			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where id='" + id + "';");
			while (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),
						rs.getString("address"), rs.getString("role"), rs.getBoolean("locked"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return user;

	}

	// get all user
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		try {

			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user;");
			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"), rs.getString("address"), rs.getString("role"),
						rs.getBoolean("locked")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return users;

	}

	// create user
	public boolean createUser(User user) {
		boolean success = false;

		if (checkUserExists(user.getEmail())) {
			return success;
		}

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"insert into user " + "(name,email,password,address,role,locked) values(?,?,?,?,?,?);");

			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getEmail());
			try {
				pStmt.setString(3, PasswordEncoder.encode(user.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(4, user.getAddress());
			pStmt.setString(5, user.getRole());
			pStmt.setBoolean(6, false);
			
			int rowEffected = pStmt.executeUpdate();

			success = rowEffected > 0 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return success;

	}

	// update user
	public int updateUser(User user) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(
					"update user set " + "name=?," + "email=?," + "password=?," + "address" + "role=? where id=?;");

			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getEmail());
			pStmt.setString(3, user.getPassword());
			pStmt.setString(4, user.getRole());
			pStmt.setString(6, user.getAddress());
			pStmt.setLong(7, user.getId());

			rowEffected = pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;

	}

	// really delete
	public int deleteUser(Long id) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from user where id=?;");
			pStmt.setLong(1, id);
			rowEffected = pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}

	// flash delete
	public int lockUser(Long id) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update user set locked=? where id=?;");
			pStmt.setBoolean(1, true);
			pStmt.setLong(2, id);
			rowEffected = pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}

}
