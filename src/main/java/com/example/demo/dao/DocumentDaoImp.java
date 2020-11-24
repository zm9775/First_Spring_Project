package com.example.demo.dao;

import com.example.demo.model.Documents;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("documentDao")
public class DocumentDaoImp implements DocumentDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return  sessionFactory.getCurrentSession();
    }

    @Override
    public void saveDocument(Documents document){
        sessionFactory.getCurrentSession().save(document);
    }
    @Override
    public Documents findDocumentById(int id) {
        return (Documents) sessionFactory.getCurrentSession().get(Documents.class,id);
    }

    @Override
    public void deleteDocument(Documents document) {
        sessionFactory.getCurrentSession().delete(document);
    }
}
