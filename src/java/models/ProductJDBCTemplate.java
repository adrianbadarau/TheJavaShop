/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author adrian
 */
public class ProductJDBCTemplate implements ProductDAO{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplate = new JdbcTemplate(data);
    }

    @Override
    public void create(String Name, String Description, float Price) {
        String sql = "insert into Products (Name, Description, Price) values (?,?,?)";
        jdbcTemplate.update(sql, Name, Description, Price);
    }

    @Override
    public List<Product> all() {
        String sql = "select * from Products";
        List<Product> products = jdbcTemplate.query(sql, new ProductMapper());
        
        return products;
    }

    @Override
    public Product find(int Id) {
        String sql = "select * from Products where Id =?";
        Product product = jdbcTemplate.queryForObject(sql, new Object[]{Id}, new ProductMapper());
        
        return product;
    }

    @Override
    public void update(int Id, String Name, String Description, float Price) {
        String sql = "update Products set Name=?, Description=?, Price=? where Id=?";
        jdbcTemplate.update(sql, Name,Description,Price, Id);
        
    }

    @Override
    public void delete(int Id) {
        String sql = "delete from Products where Id =?";
        jdbcTemplate.update(sql, Id);
    }
    
    public Map<Integer, String> listAll(){
        List<Product> products = this.all();
        Map<Integer, String> productsList = new LinkedHashMap<>();
        for(Product prod : products){
            productsList.put( prod.getId(), prod.getName());
        }
        
        return productsList;
    }
    
}
