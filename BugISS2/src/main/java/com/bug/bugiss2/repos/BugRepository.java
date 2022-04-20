package com.bug.bugiss2.repos;

import com.bug.bugiss2.domain.Bug;
import com.bug.bugiss2.domain.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BugRepository implements Repository<Long, Bug> {

    private JdbcUtils dbUtils;

    public BugRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Bug findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Bug> findAll() {
        return null;
    }

    @Override
    public Bug save(Bug entity) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into bug(name ,description,status) values (?,?,?)")) {
            preStmt.setString(1,entity.getName());
            preStmt.setString(2,entity.getDescription());
            preStmt.setString(3,entity.getStatus());
            int result = preStmt.executeUpdate();
            return entity;
        } catch (SQLException ex) {
            System.out.println("Error DB" + ex);
        }
        return null;
    }

    @Override
    public Bug delete(Long aLong) {
        return null;
    }

    @Override
    public Bug update(Bug entity) {
        return null;
    }
}
