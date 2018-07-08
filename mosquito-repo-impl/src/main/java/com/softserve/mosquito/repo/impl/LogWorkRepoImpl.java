package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogWorkRepoImpl implements LogWorkRepo {

    private static final Logger LOGGER = LogManager.getLogger(LogWorkRepoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public LogWorkRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LogWorkRepoImpl() {
    }

    @Override
    public LogWork create(LogWork logWork) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(logWork);
        } catch (HibernateException e) {
            LOGGER.error("Error during save logWork!");
        } finally {
            if (session != null) session.close();
        }
        return logWork;
    }

    @Override
    public LogWork read(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(LogWork.class, id);
        } catch (HibernateException e) {
            LOGGER.error("Reading logWork was failed!");
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public LogWork update(LogWork logWork) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            session.update(logWork);
            session.getTransaction().commit();
            return logWork;
        } catch (HibernateException e) {
            LOGGER.error("LogWork updating was failed" + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            LogWork logWork = session.get(LogWork.class, id);
            session.delete(logWork);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            LOGGER.error("Deleting logWork  was failed!");
        } finally {
            if (session != null) session.close();
        }
    }


    public List<LogWork> readAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<LogWork> query = session.createQuery("FROM " + LogWork.class.getName());
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    public List<LogWork> getByUserId(Long userId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<LogWork> query = session.createQuery("from " + LogWork.class.getName()+" where author_id = :id ");
            query.setParameter("id", userId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    public List<LogWork> getByEstimationId(Long estimationId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<LogWork> query = session.createQuery("from " + LogWork.class.getName()+" where estimation_id = :est ");
            query.setParameter("est",estimationId);
            return query.list();
        } catch (HibernateException e) {
            LOGGER.error(e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }
}