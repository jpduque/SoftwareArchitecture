package com.UCDC.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Storage<T> {

    private static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    private T entity;
    private Session session;

    public void beginTransaction() {
        session = Storage.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void commit() {
        session.getTransaction().commit();
    }

    public Transaction tx (){
        return session.getTransaction();
    }

    public Storage(T entity) {
        this.entity = entity;
    }

    public void update(T entity) {
        session.update(entity);
    }

    public int insert(T entity) {
        return (Integer) session.save(entity);
    }

    public void delete(T entity) {
        session.delete(entity);
    }

     /**
      * This call will issue a warning about the unchecked cast,
      * but we know the value returned will be of the right type because
      * we specify the entity (T) class in the call.
      * Note that "get" will return a null if no value with this id fails
      **/
    @SuppressWarnings(value = "unchecked")
    public T getById(Integer id) {
        return (T) session.get(entity.getClass(), id);
    }

    /***
     * This method would be the one that query the data from the data base based on
     * the field and column passed through the parameters calling the Commons
     * allowing to query by any desired column using the query API of hibernate
     ***/

    @SuppressWarnings(value = "unchecked")
    public List<T> getByColumn(String key, String value) throws NoSuchFieldException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) entity.getClass());
        Root<T> from = criteria.from((Class<T>) entity.getClass());
        criteria.select(from);
        criteria.where(builder.equal(from.get(entity.getClass().getDeclaredField(key).getName()), value));
        TypedQuery<T> typed = session.createQuery(criteria);
        return typed.getResultList();
    }

    @SuppressWarnings(value = "unchecked")
    public List<T> getAll() throws NoSuchFieldException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) entity.getClass());
        Root<T> from = criteria.from((Class<T>) entity.getClass());
        criteria.select(from);
        TypedQuery<T> typed = session.createQuery(criteria);
        return typed.getResultList();
    }
}