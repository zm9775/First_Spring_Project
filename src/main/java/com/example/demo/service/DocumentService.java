package com.example.demo.service;

import com.example.demo.model.Documents;

public interface DocumentService {

    void saveDocument(Documents document);

    Documents findDocumentById(int id);

    void deleteDocument(Documents document);
}
