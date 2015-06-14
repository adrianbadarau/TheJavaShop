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
public interface ProductDAO {
    public void setDataSource(DataSource data);
    public void create(String Name, String Description, float Price);
    public List<Product> all();
    public Product find(int Id);
    public void update(int Id, String Name, String Description, float Price);
    public void delete(int Id);
}
