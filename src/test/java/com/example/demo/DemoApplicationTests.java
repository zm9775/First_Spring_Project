package com.example.demo;

import com.example.demo.configuration.AppConfig;
import com.example.demo.model.Documents;
import com.example.demo.model.Keywords;
import com.example.demo.service.DocumentService;
import com.example.demo.service.KeywordService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    private AbstractApplicationContext context;
    private DocumentService documentService;
    private DateFormat formatter;

    private KeywordService keywordService;

    @BeforeEach
    public void setUp() throws ParseException {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        documentService = (DocumentService) context.getBean("documentService");
        formatter = new SimpleDateFormat("dd/MM/yyyy");

        keywordService = (KeywordService) context.getBean("keywordService");

        Keywords keyword1 = new Keywords(1,"title1");
        Keywords keyword2 = new Keywords(2,"title2");
        Keywords keyword3 = new Keywords(3,"title3");
        Keywords keyword4 = new Keywords(4,"title4");
        Keywords keyword5 = new Keywords(5,"title5");
        Keywords keyword6 = new Keywords(6,"title6");
        Keywords keyword7 = new Keywords(7,"title7");
        Keywords keyword8 = new Keywords(8,"title8");
        Keywords keyword9 = new Keywords(9,"title9");
        Keywords keyword10 = new Keywords(10,"title10");

        Documents document1 = new Documents(1,formatter.parse("12/11/2020"),"doc1",keyword2,keyword3);
        Documents document2 = new Documents(2,formatter.parse("15/11/2020"),"doc2",keyword1);
        Documents document3 = new Documents(3,formatter.parse("19/10/2020"),"doc3",keyword2,keyword7);
        Documents document4 = new Documents(4,formatter.parse("13/09/2020"),"doc4",keyword7,keyword2,keyword5,keyword6);
        Documents document5 = new Documents(5,formatter.parse("18/08/2020"),"doc5",keyword1,keyword4,keyword8);
        Documents document6 = new Documents(6,formatter.parse("7/10/2020"),"doc6");
        Documents document7 = new Documents(7,formatter.parse("27/05/2020"),"doc7");
        Documents document8 = new Documents(8,formatter.parse("22/04/2020"),"doc8",keyword10,keyword5);

        documentService.saveDocument(document1);
        documentService.saveDocument(document2);
        documentService.saveDocument(document3);
        documentService.saveDocument(document4);
        documentService.saveDocument(document5);
        documentService.saveDocument(document6);
        documentService.saveDocument(document7);
        documentService.saveDocument(document8);

    }
    @Test
    void contextLoads() {
        //Documents table tests
        Documents doc3 = documentService.findDocumentById(3);
        Assert.assertEquals(3,doc3.getId());
        Assert.assertEquals("19/10/2020",formatter.format(doc3.getDate()));
        Assert.assertEquals("doc3",doc3.getName());

        Documents doc4 = documentService.findDocumentById(4);
        Assert.assertEquals(4,doc4.getId());
        Assert.assertEquals("13/09/2020",formatter.format(doc4.getDate()));
        Assert.assertEquals("doc4",doc4.getName());

        Documents doc5 = documentService.findDocumentById(5);
        Assert.assertEquals(5,doc5.getId());
        Assert.assertEquals("18/08/2020",formatter.format(doc5.getDate()));
        Assert.assertEquals("doc5",doc5.getName());

        Documents doc7 = documentService.findDocumentById(7);
        Assert.assertEquals(7,doc7.getId());
        Assert.assertEquals("27/05/2020",formatter.format(doc7.getDate()));
        Assert.assertEquals("doc7",doc7.getName());

        //Keywords table tests
        Keywords keyword4 = keywordService.findKeywordById(4);
        Assert.assertEquals(4,keyword4.getId());
        Assert.assertEquals("title4",keyword4.getTitle());

        Keywords keyword6 = keywordService.findKeywordById(6);
        Assert.assertEquals(6,keyword6.getId());
        Assert.assertEquals("title6",keyword6.getTitle());

        Keywords keyword8 = keywordService.findKeywordById(8);
        Assert.assertEquals(8,keyword8.getId());
        Assert.assertEquals("title8",keyword8.getTitle());

        //document_keyword table tests
        List<Keywords> keywordsList3 = documentService.findDocumentById(3).getKeywords();
        List<Keywords> keywordsList4 = documentService.findDocumentById(4).getKeywords();
        Assert.assertEquals(keywordsList3.get(0).getTitle(),keywordsList4.get(0).getTitle());
        Assert.assertEquals(keywordsList3.get(1).getTitle(),keywordsList4.get(3).getTitle());

        List<Keywords> keywordsList8 = documentService.findDocumentById(8).getKeywords();
        Assert.assertEquals(keywordsList4.get(1).getTitle(),keywordsList8.get(0).getTitle());

        List<Keywords> keywordsList1 = documentService.findDocumentById(1).getKeywords();
        Assert.assertEquals(keywordsList1.get(0).getTitle(),keywordsList3.get(0).getTitle());
        Assert.assertEquals(keywordsList1.get(0).getTitle(),keywordsList4.get(0).getTitle());

        List<Keywords> keywordsList2 = documentService.findDocumentById(2).getKeywords();
        List<Keywords> keywordsList5 = documentService.findDocumentById(5).getKeywords();
        if(!keywordsList2.isEmpty())
            Assert.assertEquals(keywordsList2.get(0).getTitle(),keywordsList5.get(0).getTitle());

        //Test after delete documents from Documents table
        documentService.deleteDocument(documentService.findDocumentById(6));
        Assert.assertNull(documentService.findDocumentById(6));

        documentService.deleteDocument(documentService.findDocumentById(5));
        Assert.assertTrue(documentService.findDocumentById(2).getKeywords().isEmpty());
        Assert.assertNull(documentService.findDocumentById(5));

    }
    @AfterEach
    public void teardown(){
        context.close();
    }

}
