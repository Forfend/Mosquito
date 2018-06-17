package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriorityRepoImpl implements PriorityRepo {
    private static final Logger LOGGER = LogManager.getLogger(PriorityRepoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public PriorityRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Priority create(Priority priority) {
        try {
            Session session = sessionFactory.openSession();
            session.save(priority);

            return priority;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Priority read(Long id) {
        try {
            Session session = sessionFactory.openSession();
            Priority priority = session.get(Priority.class, id);

            return priority;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Priority update(Priority priority) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(priority);
            session.getTransaction().commit();
            return priority;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Priority priority = session.get(Priority.class, id);
            session.delete(priority);
            session.getTransaction().commit();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Priority> getAll() {
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery("From " + Priority.class.getName());

            return query.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}