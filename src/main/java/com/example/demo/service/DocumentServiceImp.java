package com.example.demo.service;

import com.example.demo.dao.DocumentDao;
import com.example.demo.model.Documents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("documentService")
public class DocumentServiceImp implements DocumentService{
    @Autowired
    DocumentDao documentDao;

    @Override
    @Transactional
    public void saveDocument(Documents document){
        if(findDocumentById(document.getId()) == null)
            documentDao.saveDocument(document);
    }

    @Override
    @Transactional
    public Documents findDocumentById(int id) {
        return documentDao.findDocumentById(id);
    }

    @Override
    @Transactional
    public void deleteDocument(Documents document) {
        documentDao.deleteDocument(document);
    }
}
