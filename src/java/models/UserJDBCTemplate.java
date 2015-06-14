/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author adrian
 */
public class UserJDBCTemplate implements UserDAO{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    @Override
    public void setDataSource(DataSource data) {
        this.dataSource = data;
        this.jdbcTemplateObject = new JdbcTemplate(data);
    }

    @Override
    public User create(String Name, String Pass) {
        String sql = "insert into Users (Name, Pass) values (?,?)";
        jdbcTemplateObject.update(sql,Name,Pass);
        sql = "select * from Users order by Id DESC limit ?";
        User user = jdbcTemplateObject.queryForObject(sql, new Object[]{1}, new UserMapper());
        return user;
    }

    @Override
    public List<User> all() {
        String sql = "select * from Users";
        List<User> users = jdbcTemplateObject.query(sql, new UserMapper());
        
        return users;
    }

    @Override
    public User find(int Id) {
        String sql = "select * from Users where Id = ?";
        User user = jdbcTemplateObject.queryForObject(sql, new Object[]{Id}, new UserMapper());
        return user;
    }
    
    public User findBy(String attr, String value){
        String sql = "select * from Users where "+attr+" = ?";
        User user = jdbcTemplateObject.queryForObject(sql, new Object[]{value}, new UserMapper());
        System.out.println(user);
        return user;
    }

    @Override
    public boolean update(int Id, String Name, String Pass) {
        String sql = "update Users set Name=?, Pass=? where Id=?";
        jdbcTemplateObject.update(sql, Name, Pass, Id);
        return true;
    }

    @Override
    public boolean delete(int Id) {
        String sql = "delete from Users where Id=?";
        jdbcTemplateObject.update(sql, Id);
        
        return true;
    }
    
    public void buyProduct(int userId, int productId){
        String sql = "insert into Sales (ClientId, ProductId) values (?, ?)";
        jdbcTemplateObject.update(sql, userId, productId);
    }
    
    public List<Sale> getSoldProducts(){
        ArrayList<Sale> sales = new ArrayList<>();
        List<HashMap<String, Integer>> saleRows = jdbcTemplateObject.query("select * from Sales", new RowMapper<HashMap<String, Integer>>(){
            @Override
            public HashMap<String, Integer> mapRow(ResultSet rs, int i) throws SQLException {
                HashMap<String, Integer> saleRow = new HashMap();
                Integer pid = (Integer) rs.getInt("ProductId");
                saleRow.put("productId", pid);
                Integer uId = (Integer) rs.getInt("ClientId");
                saleRow.put("clientId", uId);
                return saleRow;
            }
        });
        for(HashMap row : saleRows){
            Integer pid = (Integer) row.get("productId");
            Product product = jdbcTemplateObject.queryForObject("select * from Products where Id = ? limit 1", new Object[]{pid}, new ProductMapper());
            Integer uid = (Integer) row.get("clientId");
            User client = jdbcTemplateObject.queryForObject("select * from Users where Id = ? limit 1", new Object[]{uid},new UserMapper());
            Sale sale = new Sale();
            sale.setProduct(product);
            sale.setClient(client);
            sales.add(sale);
        }
        
        return sales;
    }
    
        public Map<Integer, String> listAll(){
        List<User> users = this.all();
        Map<Integer, String> usersList = new LinkedHashMap<>();
        for(User user : users){
            usersList.put( user.getId(), user.getName());
        }
        return usersList;
    }
}
