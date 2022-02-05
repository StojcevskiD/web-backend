//package com.example.backend.service.impl;
//
//import com.example.backend.service.interfaces.FileService;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//import java.sql.Blob;
//
//@Service
//public class FileServiceImpl implements FileService {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public FileServiceImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public Blob createBlob(InputStream content, long size) {
//        return sessionFactory.getCurrentSession().getLobHelper().createBlob(content, size);
//    }
//}


