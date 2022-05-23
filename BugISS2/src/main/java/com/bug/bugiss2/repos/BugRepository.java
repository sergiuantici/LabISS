package com.bug.bugiss2.repos;

import com.bug.bugiss2.domain.Bug;
import com.bug.bugiss2.domain.JdbcUtils;
import com.bug.bugiss2.observer.Observer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class BugRepository implements Repository<Long, Bug> {

    private JdbcUtils dbUtils;
    private SessionFactory sessionFactory;
    public BugRepository(Properties props,SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Bug findOne(Long aLong) {
        return null;
    }

    public Collection<Bug> findAll() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String hql = "FROM Bug WHERE status='WIP'";
            List<Bug> result=session.createQuery(hql,Bug.class).list();
            return result;
        }
    }
    @Override
    public Bug save(Bug entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            notifyObservers();
        }
        return entity;
    }

    @Override
    public Bug delete(Long aLong) {
        return null;
    }

    @Override
    public Bug update(Bug entity) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var bug2=new Bug(entity.getName(), entity.getDescription(), "Fixed");
            bug2.setId(entity.getId());
            session.update(bug2);
            session.getTransaction().commit();
            notifyObservers();
        }
        return entity;
    }

    public Collection<Bug> findSome(String searchText) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            searchText+="%";
            String hql = "FROM Bug where name like :search";
            List<Bug> result=session.createQuery(hql,Bug.class).setParameter("search",searchText).list();
            return result;
        }
    }
    private List<Observer> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
