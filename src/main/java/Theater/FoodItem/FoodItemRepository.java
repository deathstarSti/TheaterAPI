package Theater.FoodItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import Theater.FoodItem.FoodItem;


public class FoodItemRepository {

	private String jdbcURL = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String SELECT_FOODITEM_BY_ID = "select * from fooditem where ID =?";
	
	public FoodItemRepository() {
		
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
	
	
	
	public List<FoodItem> getFoodItems() {

		
		List<FoodItem> foodItems = new ArrayList<FoodItem>();
		
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				Statement statement = connection.createStatement()) {
			String sql = "SELECT * FROM `fooditem`;";

			ResultSet rs = statement.executeQuery(sql);

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				double saleprice = rs.getDouble("SalePrice");
				double UnitPrice = rs.getDouble("UnitPrice");
				int quantity = rs.getInt("Quantity");
				
				foodItems.add(new FoodItem(id, name, saleprice, UnitPrice, quantity));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return foodItems;
	}
	
	public FoodItem getFoodItem(int id) {

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOODITEM_BY_ID);) {
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next() == false) {
				return null;
			} else {

				do {
					String name = rs.getString("Name");
					double salePrice = rs.getDouble("SalePrice");
					double unitPrice = rs.getDouble("UnitPrice");
					int quantity = rs.getInt("Quantity");

					return new FoodItem(id, name, salePrice, unitPrice, quantity);
				} while (rs.next());
			}


		} catch (SQLException e) {
			printSQLException(e);
		}

		return null;
	}

	public int insertFoodItem(FoodItem newFoodItem) {

		if ((newFoodItem.getName() != null) && (newFoodItem.getSalePrice() != 0)
				&& (newFoodItem.getUnitPrice() != 0) && (newFoodItem.getQuantity() != 0)) {

			String INSERT_FOODITEM_SQL = "INSERT INTO fooditem"
					+ "  (Name, SalePrice, UnitPrice, Quantity) VALUES " + " (?, ?, ?, ?);";

			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOODITEM_SQL,
							Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, newFoodItem.getName());
				preparedStatement.setDouble(2, newFoodItem.getSalePrice());
				preparedStatement.setDouble(3, newFoodItem.getUnitPrice());
				preparedStatement.setInt(4, newFoodItem.getQuantity());
//				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();
				if (rs.next()) {
					int last_inserted_id = rs.getInt(1);
//					System.out.println("NEW ID = " + last_inserted_id);
					return last_inserted_id;
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		return -1;
	}
	
	public int updateFoodItem(int id, FoodItem fooditem) {

		if ((fooditem.getName() != null) && (fooditem.getSalePrice() != 0)
				&& (fooditem.getUnitPrice() != 0) && (fooditem.getQuantity() != 0)) {

			String UPDATE_FOODITEM_SQL = "UPDATE fooditem SET Name = ?, SalePrice= ?, UnitPrice= ?, Quantity = ? "
					+ "  WHERE ID = ?;";

			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FOODITEM_SQL,
							Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, fooditem.getName());
				preparedStatement.setDouble(2, fooditem.getSalePrice());
				preparedStatement.setDouble(3, fooditem.getUnitPrice());
				preparedStatement.setInt(4, fooditem.getQuantity());
				preparedStatement.setInt(5, id);
//				System.out.println(preparedStatement);
				
				return  preparedStatement.executeUpdate();
				
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		return -1;
	}
	
	public int deleteFoodItem(int id) {

		
		String DELETE_FOODITEM_SQL = "DELETE FROM fooditem WHERE id = ?;";
		int row = 0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOODITEM_SQL);) {
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query
			
			row = preparedStatement.executeUpdate();


		} catch (SQLException e) {
			printSQLException(e);
		}
		System.out.println(row);
		return row;
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
