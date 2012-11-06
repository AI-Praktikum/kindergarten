/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kindergarten.helper;

/**
 *
 * @author andy
 */
public class DBLogin {
    private String user;
    private String password;
    private String database;
    
    public DBLogin(){
        
    }
    
    public String getUser(){
        return this.user;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getDatabase(){
        return this.database;
    }
    
    public void setUser(String user){
        this.user = user;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setDatabase(String database){
        this.database = database;
    }
}
