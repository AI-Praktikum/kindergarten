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
    private String dbUrl; 

	public DBJdbc() {
                this.dbUrl = DBLogin.getUrl()+DBLogin.getDatabase();
	}

	public ResultSet query(String sql) throws SQLException {
 
		// jdbc
		try {
                        System.out.println(DBLogin.getUser() + ", " + DBLogin.getPassword());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName(DBLogin.getDriver());
                        Connection con = DriverManager.getConnection(dbUrl, DBLogin.getUser(), DBLogin.getPassword());
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+DBLogin.getUser()+" identified by: "+DBLogin.getPassword());
			e.printStackTrace();
		}
		return stmt.executeQuery(sql);
	}

	public int update(String sql) throws SQLException {
		// jdbc
		try {
                        System.out.println(DBLogin.getUser() + ", " + DBLogin.getPassword());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName(DBLogin.getDriver());
                        Connection con = DriverManager.getConnection(dbUrl, DBLogin.getUser(), DBLogin.getPassword());
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+DBLogin.getUser()+" identified by: "+DBLogin.getPassword());
			e.printStackTrace();
		}
		return stmt.executeUpdate(sql);
	}
        
        public boolean delete(String sql) throws SQLException {
		// jdbc
		try {
                        System.out.println(DBLogin.getUser() + ", " + DBLogin.getPassword());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName(DBLogin.getDriver());
                        Connection con = DriverManager.getConnection(dbUrl, DBLogin.getUser(), DBLogin.getPassword());
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+DBLogin.getUser()+" identified by: "+DBLogin.getPassword());
			e.printStackTrace();
		}
            return stmt.execute(sql);
        }
    
}
