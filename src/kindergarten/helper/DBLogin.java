/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andy
 */
public class DBLogin {
    private static String user;
    private static String password;
    private static String database;
    private final static String url = "jdbc:mysql://ec2-176-34-76-54.eu-west-1.compute.amazonaws.com:3306/";
    private final static String driver = "com.mysql.jdbc.Driver";
    
    
    public static String getUser(){
        return user;
    }
    
    public static String getPassword(){
        return password;
    }
    
    public static String getDatabase(){
        return database;
    }
    
    public static String getUrl(){
        return url;
    }
    
    public static String getDriver(){
        return driver;
    }
    
    public static void setUser(String user){
        DBLogin.user = user;
    }
    
    public static void setPassword(String password){
        DBLogin.password = password;
    }
    
    public static void setDatabase(String database){
        DBLogin.database = database;
    }
    
    public static Map<String, String> getPropMap(){
        Map<String, String> propMap = new HashMap<String, String>();
        propMap.put("javax.persistence.jdbc.url", url+database);
        propMap.put("javax.persistence.jdbc.password", password);
        propMap.put("javax.persistence.jdbc.driver", driver);
        propMap.put("javax.persistence.jdbc.user", user);
        return propMap;
    }
}
