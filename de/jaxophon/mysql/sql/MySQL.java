package de.jaxophon.mysql.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Jan Scheel
 * @version 0.0.1
 * @category Database & Connection
 * @since 29.01.2017
 * 
 *        The classes of this project are legally copy-protected. Copying or
 *        decompiling is strictly forbidden!
 */
public class MySQL {

	private String Host;
	private String Database;
	private String Username;
	private String Password;
	private Connection connection;

	public MySQL(String host, String database, String username, String password) {
		this.Host = host;
		this.Database = database;
		this.Username = username;
		this.Password = password;

	}

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("[MySQLAPI] Fehler 01");
			System.out.println("[MySQLAPI] Es fehlen benoetigte Klassen!");
			e1.printStackTrace();
		}
		String url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database;
		try {
			this.connection = DriverManager.getConnection(url, this.Username, this.Password);
		} catch (SQLException e2) {
			System.out.println("[MySQLAPI] Fehler 02");
			System.out.println("[MySQLAPI] Es ist ein Fehler bei der Verbindung aufgetreten!");
			e2.printStackTrace();
		}
	}

	public void Disconnect() {
		try {
			if (!this.connection.isClosed() && this.connection != null) {
				this.connection.close();
				System.out.println("[MySQLAPI] Die Verbindung zum MySQL-Server wurde erfolgreich getrennt!");
			} else {
				System.out.println("[MySQLAPI] Die Verbindung ist bereits getrennt!");
			}
		} catch (SQLException e3) {
			System.out.println("[MySQLAPI] Fehler 03");
			System.out.println("[MySQLAPI] Es ist ein Fehler beim trennen der Verbindung aufgetreten!");
			e3.printStackTrace();
		}
	}

	public ResultSet GetResult(String command) {
		try {
			if (this.connection.isClosed()) {
				this.Connect();
			}

			Statement st = this.connection.createStatement();
			st.executeQuery(command);
			ResultSet rs = st.getResultSet();
			return rs;

		} catch (SQLException e4) {
			System.out.println("[MySQLAPI] Fehler 04");
			System.out.println("[MySQLAPI] Es ist ein Fehler beim Ausfuehren des Befehls aufgetreten!");
			e4.printStackTrace();
		}
		return null;
	}

	public void ExecuteCommand(String command) {
		try {
			if (this.connection.isClosed()) {
				this.Connect();
			}
			Statement st = this.connection.createStatement();
			st.executeUpdate(command);
		} catch (SQLException e5) {
			System.out.println("[MySQLAPI] Fehler 04");
			System.out.println("[MySQLAPI] Es ist ein Fehler beim Ausfuehren des Befehls aufgetreten!");
			e5.printStackTrace();
		}

	}
}