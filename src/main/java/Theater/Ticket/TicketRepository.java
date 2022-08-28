package Theater.Ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import Theater.Ticket.Ticket;

public class TicketRepository {

	private String jdbcURL = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_TICKET_BY_ID = "select * from tickets where ID =?";

	public TicketRepository() {

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

	public List<Ticket> getTickets() {

		List<Ticket> tickets = new ArrayList<Ticket>();

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				Statement statement = connection.createStatement()) {
			String sql = "SELECT * FROM `tickets`;";

			ResultSet rs = statement.executeQuery(sql);

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("ID");
				String moviename = rs.getString("MovieName");
				double saleprice = rs.getDouble("SalePrice");
				double StudioCutPercentage = rs.getDouble("StudioCutPercentage");
				int quantity = rs.getInt("Quantity");

				tickets.add(new Ticket(id, moviename, saleprice, StudioCutPercentage, quantity));
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return tickets;
	}

	public Ticket getTicket(int id) {

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TICKET_BY_ID);) {
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next() == false) {
				return null;
			} else {

				do {
					String moviename = rs.getString("MovieName");
					double saleprice = rs.getDouble("SalePrice");
					double StudioCutPercentage = rs.getDouble("StudioCutPercentage");
					int quantity = rs.getInt("Quantity");

					return new Ticket(id, moviename, saleprice, StudioCutPercentage, quantity);
				} while (rs.next());
			}

		} catch (SQLException e) {
			printSQLException(e);
		}

		return null;
	}

	public int insertTicket(Ticket newTicket) {

		if ((newTicket.getMovieName() != null) && (newTicket.getSalePrice() != 0)
				&& (newTicket.getStudioCutPercentage() != 0) && (newTicket.getQuantity() != 0)) {

			String INSERT_TICKET_SQL = "INSERT INTO tickets"
					+ "  (MovieName, SalePrice, StudioCutPercentage, Quantity) VALUES " + " (?, ?, ?, ?);";

			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TICKET_SQL,
							Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, newTicket.getMovieName());
				preparedStatement.setDouble(2, newTicket.getSalePrice());
				preparedStatement.setDouble(3, newTicket.getStudioCutPercentage());
				preparedStatement.setInt(4, newTicket.getQuantity());
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

	public int updateTicket(int id, Ticket ticket) {

		if ((ticket.getMovieName() != null) && (ticket.getSalePrice() != 0) && (ticket.getStudioCutPercentage() != 0)
				&& (ticket.getQuantity() != 0)) {

			String UPDATE_TICKET_SQL = "UPDATE tickets SET MovieName = ?, SalePrice= ?, StudioCutPercentage= ?, Quantity = ? "
					+ "  WHERE ID = ?;";

			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TICKET_SQL,
							Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, ticket.getMovieName());
				preparedStatement.setDouble(2, ticket.getSalePrice());
				preparedStatement.setDouble(3, ticket.getStudioCutPercentage());
				preparedStatement.setInt(4, ticket.getQuantity());
				preparedStatement.setInt(5, id);
//				System.out.println(preparedStatement);

				return preparedStatement.executeUpdate();

			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		return -1;
	}

	public int deleteTicket(int id) {

		String DELETE_TICKET_SQL = "DELETE FROM tickets WHERE id = ?;";
		int row = 0;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TICKET_SQL);) {
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
