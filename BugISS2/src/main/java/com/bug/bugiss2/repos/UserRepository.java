package com.bug.bugiss2.repos;

import com.bug.bugiss2.domain.JdbcUtils;
import com.bug.bugiss2.domain.User;
import com.bug.bugiss2.observer.Observer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository implements Repository<Long, User>{
    private JdbcUtils dbUtils;
    private SessionFactory sessionFactory;
    public UserRepository(Properties props,SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public User findOne(Long aLong) {
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public User save(User entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
        return entity;
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
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String hql = "FROM User U WHERE U.email = :email and U.password=:password";
            List<User> result = session.createQuery(hql,User.class).setParameter("email",email)
                    .setParameter("password",password).list();
            session.getTransaction().commit();
            if (result.size()==0)
                return null;
            return result.get(0);
        }
    }

    public User existsAlready(String email){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String hql = "FROM User U WHERE U.email = :email";
            List<User> result = session.createQuery(hql,User.class)
                    .setParameter("email",email)
                    .list();
            session.getTransaction().commit();
            if (result.size()==0)
                return null;
            return result.get(0);
        }
    }

    @Override
    public void addObserver(Observer e) {

    }

    @Override
    public void removeObserver(Observer e) {

    }

    @Override
    public void notifyObservers() {

    }
}
