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
    Statement s;

	String user;
	String pw;

	public DBJdbc(String user, String pw) {
		this.user = user;
		this.pw = pw;
		// jdbc
		try {
			String url = "jdbc:oracle:thin:@oracle.informatik.haw-hamburg.de:1521:inf09";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection c = DriverManager.getConnection(url, user, pw);
			s = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String sql) throws SQLException {
		return s.executeQuery(sql);
	}

	public int update(String sql) throws SQLException {
		return s.executeUpdate(sql);
	}
        
        public boolean delete(String sql) throws SQLException {
            return s.execute(sql);
        }
    
}
