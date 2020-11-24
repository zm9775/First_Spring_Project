package com.example.demo.dao;

import com.example.demo.model.Documents;

public interface DocumentDao {

    void saveDocument(Documents document);

    Documents findDocumentById(int id);

    void deleteDocument(Documents document);

}
