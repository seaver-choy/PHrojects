/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phrojects.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Seaver
 */
public class MySQLConnectionManager {
    private static volatile Connection conn = null;
    
    public MySQLConnectionManager(){}
    
    public static Connection getInstance() {
        if (conn == null ) {
                    try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/phrojects";
			conn = DriverManager.getConnection(url, "root", "DLSU1234");
			System.out.println(conn + "fdsfsdf");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
        }
        }
		return conn;
    }
}
