package com.example.demo.dao;

import com.example.demo.model.Keywords;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("keywordDao")
public class KeywordDaoImp implements KeywordDao{
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return  sessionFactory.getCurrentSession();
    }

    @Override
    public void saveKeyword(Keywords keyword) {
        sessionFactory.getCurrentSession().save(keyword);
    }

    @Override
    public Keywords findKeywordById(int id) {
        return (Keywords)sessionFactory.getCurrentSession().get(Keywords.class,id);
    }

    @Override
    public void deleteKeyword(Keywords keyword) {
        sessionFactory.getCurrentSession().delete(keyword);
    }
}
