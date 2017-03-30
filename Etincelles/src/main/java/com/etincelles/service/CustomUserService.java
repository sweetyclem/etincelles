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

                // user.setId( rs.getLong( "id" ) );
                // user.setFirstName( rs.getString( "first_name" ) );
                // user.setLastName( rs.getString( "last_name" ) );
                // user.setEmail( rs.getString( "email" ) );
                // user.setDescription( rs.getString( "description" ) );
                // user.setCity( City.valueOf( rs.getString( "city" ) ) );
                // user.setPassword( rs.getString( "password" ) );
                // user.setEnabled( rs.getBoolean( "enabled" ) );
                // user.setPromo( rs.getInt( "promo" ) );
                // user.setTwitter( rs.getString( "twitter" ) );
                // user.setFacebook( rs.getString( "facebook" ) );
                // user.setLinkedin( rs.getString( "linkedin" ) );
                // user.setHasPicture( rs.getBoolean( "has_picture" ) );
                // user.setSector( Sector.valueOf( rs.getString( "sector" ) ) );
                // user.setCategory( Category.valueOf( rs.getString( "category"
                // ) ) );
                // user.setType( Type.valueOf( rs.getString( "type" ) ) );
                return user;
            }

        } );
    }
}
