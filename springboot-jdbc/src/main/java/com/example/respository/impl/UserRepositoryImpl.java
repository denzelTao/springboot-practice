package com.example.respository.impl;

import com.example.model.User;
import com.example.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired(required = false)
    private JdbcTemplate primaryJdbcTemplate;

    @Override
    public int save(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
                jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("INSERT INTO user(name , password, age) values (?,?,?)",user.getName(),user.getPassword(),user.getAge());
    }

    @Override
    public int update(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("UPDATE USER set name=?, password=?, age=? where id=?",user.getName(),user.getPassword(),user.getAge(),user.getId());
    }

    @Override
    public int delete(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("delete  from user where id=?",id);
    }

    @Override
    public List<User> findAll(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.query("select * from user",new UserRowMapper());
//        return jdbcTemplate.query("select * from user",new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User findById(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate==null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("select * from user where id=?",new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }

    class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setAge(resultSet.getInt("age"));
            return user;
        }
    }
}
