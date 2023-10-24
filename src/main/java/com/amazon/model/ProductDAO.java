package com.amazon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class ProductDAO {

	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public ProductDAO(DataSource dataSource) {
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

	// Get product by id
	public Product getProductById(Long id) {
		Product product = null;
		try {

			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from product where id='" + id + "';");
			while (rs.next()) {
				product = new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getLong("userid"), rs.getString("image"),
						rs.getDouble("rating"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return product;

	}

	// get all product
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		try {
			connection = dataSource.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from product where status='active';");
			while (rs.next()) {
				products.add(new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getLong("userid"), rs.getString("image"),
						rs.getDouble("rating")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;

	}

	// create product
	public boolean createProduct(Product product) {
		boolean success = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("insert into product "
					+ "(name,price,description,userid,image,status,rating) values(?,?,?,?,?,?,?);");

			pStmt.setString(1, product.getName());
			pStmt.setDouble(2, product.getPrice());
			pStmt.setString(3, product.getDescription());
			pStmt.setLong(4, product.getUserid());
			pStmt.setString(5, product.getImage());
			pStmt.setString(6, "active");
			pStmt.setDouble(7, 0);
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

	// update product
	public boolean updateProduct(Product product) {
		boolean success = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update product set " + "name=?," + "price=?," + "description=?,"
					+ "image=?," + "rating=? where id=?;");

			pStmt.setString(1, product.getName());
			pStmt.setDouble(2, product.getPrice());
			pStmt.setString(3, product.getDescription());
			pStmt.setString(4, product.getImage());
			pStmt.setDouble(5, product.getRating());
			pStmt.setLong(6, product.getId());

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

	// really delete
	public boolean deleteProduct(Long id) {
		boolean success = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("delete from product where id=?;");
			pStmt.setLong(1, id);
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

	// flash delete
	public boolean deleteFlash(Long id) {
		boolean success = false;
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update product set status='deleted' where id=?;");
			pStmt.setLong(1, id);
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

	// get total price in the cart
//		public double getTotalCartPrice(ArrayList<Cart> cartList) {
//			double sum = 0;
//
//			if (cartList.size() > 0) {
//				try {
//					connection = dataSource.getConnection();
//
//					for (Cart cart : cartList) {
//						pStmt = connection.prepareStatement("select price from product where id=?;");
//						pStmt.setLong(1, cart.getId());
//						rs = pStmt.executeQuery();
//
//						while (rs.next()) {
//							Double price = rs.getDouble("price");
//							sum = sum + price * cart.getQuantity();
//						}
//
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} finally {
//					close();
//				}
//
//			}
//			return sum;
//
//		}

	// search product
//		public List<Product> searchProduct(String search) {
//			List<Product> productList = new ArrayList<>();
//
//			try {
//				connection = dataSource.getConnection();
//				pStmt = connection.prepareStatement("select * from product where name like ? or description like ?;");
//				pStmt.setString(1, "%" + search + "%");
//				pStmt.setString(2, "%" + search + "%");
//				rs = pStmt.executeQuery();
//				while (rs.next()) {
//					productList.add(new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"),
//							rs.getString("description"), rs.getString("image"), rs.getDouble("rating")));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				close();
//			}
//			return productList;
//
//		}

}
