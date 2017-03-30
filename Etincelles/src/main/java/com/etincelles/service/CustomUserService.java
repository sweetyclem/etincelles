package com.etincelles.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.etincelles.entities.User;

@Service
public class CustomUserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserService  userService;

    public List<User> searchQuery( String query ) {
        return jdbcTemplate.query( query, new RowMapper<User>() {

            public User mapRow( ResultSet rs, int arg1 ) throws SQLException {
                Long id = rs.getLong( "id" );
                User user = new User();
                user = userService.findById( id );
                return user;
            }

        } );
    }

    public void deleteUserSkill( String query ) {
        jdbcTemplate.execute( query );
    }
}
