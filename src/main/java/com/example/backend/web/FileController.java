package com.example.backend.web;

import com.example.backend.model.File;
import com.example.backend.service.interfaces.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public List<File> filesForSubject(@PathVariable Long id){
        return fileService.findFilesForSubject(id);
    }

    @PostMapping("/{id}")
    public void uploadFiles(@PathVariable Long id, @RequestParam("files") List<MultipartFile> files){
        for(MultipartFile file : files){
            fileService.saveFile(id, file);
        }
    }

}