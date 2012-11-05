/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author andy
 */
public class DBJdbc {
    Statement stmt;

	String user;
	String pw;

	public DBJdbc(String user, String pw) {
		this.user = user;
		this.pw = pw;
                String dbUrl = "jdbc:mysql://localhost:3306/HAWHamburg";
		// jdbc
		try {
                        System.out.println(user + ", " + pw);
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(dbUrl, user, pw);
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+user+" identified by: "+pw);
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}

	public int update(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}
        
        public boolean delete(String sql) throws SQLException {
            return stmt.execute(sql);
        }
    
}
