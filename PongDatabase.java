package PONG_GAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PongDatabase {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/baitaplon";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	public static List<ScoreHistory> score1 = new ArrayList<>();
	private static Connection connection;
	private static Statement stmt;

	public static void saveScore(String playerName, int score) {
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String sql = "update scores set player_name = ?, score = ? where player_name = '" + playerName + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, playerName);
			statement.setInt(2, score);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Error saving score: " + e.getMessage());
		}
	}

	public static List<ScoreHistory> findAll() throws SQLException {
		score1 = new ArrayList<>();
		String query = "select * from scores";
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ScoreHistory s1 = new ScoreHistory(rs.getString("player_name"), rs.getInt("score"));
				score1.add(s1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {

			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (connection != null)
				connection.close();
		}
		return score1;
	}
}
