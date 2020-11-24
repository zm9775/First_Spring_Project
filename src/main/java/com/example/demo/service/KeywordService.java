package com.example.demo.service;

import com.example.demo.model.Keywords;

public interface KeywordService {
    void saveKeyword(Keywords keyword);

    Keywords findKeywordById(int id);

    void deleteKeyword(Keywords keyword);
}
