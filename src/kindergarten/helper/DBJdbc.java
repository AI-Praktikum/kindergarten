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
    private DBLogin login;
    private String dbUrl = "jdbc:mysql://ec2-176-34-76-54.eu-west-1.compute.amazonaws.com:3306/"; 

	public DBJdbc(DBLogin logindata) {
		this.login = logindata;
                this.dbUrl = this.dbUrl + login.getDatabase();
	}

	public ResultSet query(String sql) throws SQLException {
 
		// jdbc
		try {
                        System.out.println(this.login.getUser() + ", " + this.login.getPassword());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(dbUrl, this.login.getUser(), this.login.getPassword());
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+this.login.getUser()+" identified by: "+this.login.getPassword());
			e.printStackTrace();
		}
		return stmt.executeQuery(sql);
	}

	public int update(String sql) throws SQLException {
		// jdbc
		try {
                        System.out.println(this.login.getUser() + ", " + this.login.getPassword());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(dbUrl, this.login.getUser(), this.login.getPassword());
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+this.login.getUser()+" identified by: "+this.login.getPassword());
			e.printStackTrace();
		}
		return stmt.executeUpdate(sql);
	}
        
        public boolean delete(String sql) throws SQLException {
		// jdbc
		try {
                        System.out.println(this.login.getUser() + ", " + this.login.getPassword());
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection c = DriverManager.getConnection(url, user, pw);
//			s = c.createStatement();
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection(dbUrl, this.login.getUser(), this.login.getPassword());
                        stmt = con.createStatement();
		} catch (Exception e) {
                        System.out.println("Exception mit User: "+this.login.getUser()+" identified by: "+this.login.getPassword());
			e.printStackTrace();
		}
            return stmt.execute(sql);
        }
    
}
