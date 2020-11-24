package com.example.demo.service;

import com.example.demo.dao.KeywordDao;
import com.example.demo.model.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("keywordService")
public class KeywordServiceImp implements KeywordService{

    @Autowired
    KeywordDao keywordDao;

    @Override
    @Transactional
    public void saveKeyword(Keywords keyword) {
        if(findKeywordById(keyword.getId()) == null)
            keywordDao.saveKeyword(keyword);
    }

    @Override
    @Transactional
    public Keywords findKeywordById(int id) {
        return keywordDao.findKeywordById(id);
    }

    @Override
    @Transactional
    public void deleteKeyword(Keywords keyword) {
        keywordDao.deleteKeyword(keyword);
    }
}
