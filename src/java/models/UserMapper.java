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
public class UserMapper implements RowMapper<User>{
    public User mapRow(ResultSet rs, int rowCount) throws SQLException{
        User user = new User();
        user.setId(rs.getInt("Id"));
        user.setName(rs.getString("Name"));
        user.setPass(rs.getString("Pass"));
        
        return user;
    }
}
