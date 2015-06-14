/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author adrian
 */
public interface UserDAO {
    public void setDataSource(DataSource data);
    public User create(String Name, String Pass);
    public List<User> all();
    public User find(int Id);
    public boolean update(int Id, String Name, String Pass);
    public boolean delete(int Id);
    
}
