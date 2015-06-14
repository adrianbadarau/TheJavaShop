/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adrian
 */
public class ProductMapper implements RowMapper<Product>{
    public Product mapRow( ResultSet rs, int rowCount) throws SQLException{
        Product product = new Product();
        product.setId(rs.getInt("Id"));
        product.setDescription(rs.getString("Description"));
        product.setName(rs.getString("Name"));
        product.setPrice(rs.getFloat("Price"));
        
        return product;
        
    }
    
}
