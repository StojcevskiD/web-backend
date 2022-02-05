package com.example.backend.service.interfaces;

import java.io.InputStream;
import java.sql.Blob;

public interface FileService {
    Blob createBlob(InputStream content, long size);
}
