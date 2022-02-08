package com.example.backend.web;

import com.example.backend.model.Exam_Type;
import com.example.backend.model.File;
import com.example.backend.service.interfaces.Exam_TypeService;
import com.example.backend.service.interfaces.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/file")
public class FileController {

    private final FileService fileService;
    private final Exam_TypeService exam_typeService;

    public FileController(FileService fileService, Exam_TypeService exam_typeService) {
        this.fileService = fileService;
        this.exam_typeService = exam_typeService;
    }

    @GetMapping("/get/{id}")
    public File getFile(@PathVariable Long id){
        return fileService.getFile(id);
    }

    @GetMapping("/{id}")
    public List<File> filesForSubject(@PathVariable Long id){
        return fileService.findFilesForSubject(id);
    }

    @PostMapping("/{id}")
    public void uploadFiles(@PathVariable Long id, @RequestParam("type") Long type,
                            @RequestParam("files") List<MultipartFile> files){

        Exam_Type exam_type = exam_typeService.getType(type);
        for(MultipartFile file : files){
            fileService.saveFile(id, file, exam_type);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }

}