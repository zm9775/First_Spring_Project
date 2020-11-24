package com.example.demo.dao;

import com.example.demo.model.Keywords;

public interface KeywordDao {
    void saveKeyword(Keywords keyword);

    Keywords findKeywordById(int id);

    void deleteKeyword(Keywords keyword);
}
