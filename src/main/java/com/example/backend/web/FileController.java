package com.example.backend.web;

import com.example.backend.model.ExamType;
import com.example.backend.model.File;
import com.example.backend.service.interfaces.ExamTypeService;
import com.example.backend.service.interfaces.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/file")
public class FileController {

    private final FileService fileService;
    private final ExamTypeService examTypeService;

    public FileController(FileService fileService, ExamTypeService exam_typeService) {
        this.fileService = fileService;
        this.examTypeService = exam_typeService;
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

        ExamType exam_type = examTypeService.getType(type);
        for(MultipartFile file : files){
            fileService.saveFile(id, file, exam_type);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id){
        fileService.deleteFile(id);
    }

}