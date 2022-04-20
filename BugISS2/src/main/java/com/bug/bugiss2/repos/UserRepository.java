package com.bug.bugiss2.repos;

import com.bug.bugiss2.domain.JdbcUtils;
import com.bug.bugiss2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository implements Repository<Long, User>{
    private JdbcUtils dbUtils;

    public UserRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public User findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Connection con = dbUtils.getConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from User")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    Long id = result.getLong("id");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    String role=result.getString("role");
                    User user = new User(email,password,role);
                    user.setId(id);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error BD " + ex);
        }
        return users;
    }

    @Override
    public User save(User entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into User(email,password,role) values (?,?,?)")) {
            preStmt.setString(1,entity.getEmail());
            preStmt.setString(2,entity.getPassword());
            preStmt.setString(3,entity.getRole());
            int result = preStmt.executeUpdate();
            return entity;
        } catch (SQLException ex) {
            System.out.println("Error DB" + ex);
        }
        return null;
    }

    @Override
    public User delete(Long aLong) {
        return null;
    }


    @Override
    public User update(User entity) {
        return null;
    }

    public User exists(String email, String password) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from user where email=? and password=?")) {
            preStmt.setString(1, email);
            preStmt.setString(2, password);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    Long id = result.getLong("id");
                    String role=result.getString("role");
                    User user = new User(email,password,role);
                    user.setId(id);
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error BD " + ex);
        }
        return null;
    }
}
