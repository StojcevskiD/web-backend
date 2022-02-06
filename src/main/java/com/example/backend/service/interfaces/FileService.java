package com.example.backend.service.interfaces;


import com.example.backend.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    void saveFile(Long id, MultipartFile file);
}
