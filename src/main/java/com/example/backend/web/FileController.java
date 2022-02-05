//package com.example.backend.web;
//
//import com.example.backend.exceptions.FileNotFoundException;
//import com.example.backend.model.File;
//import com.example.backend.repository.FileRepository;
//import com.example.backend.service.interfaces.FileService;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.sql.SQLException;
//
//@RestController
//public class FileController {
//    // da se trgne nekako repozitoriumov da se prenese preku service
//
//    private final FileRepository fileRepository;
//    private final FileService fileService;
//
//    @Autowired
//    public FileController(FileRepository fileRepository, FileService fileService) {
//        this.fileRepository = fileRepository;
//        this.fileService = fileService;
//    }
//
//
//    @Transactional
//    @RequestMapping(value = "/blobs", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Long> store(@RequestPart("file") MultipartFile multipartFile) throws IOException, SQLException, URISyntaxException {
////        log.info("Persisting new file: {}", multipartFile.getOriginalFilename());
//        File file = new File(multipartFile.getOriginalFilename(), fileService.createBlob(multipartFile.getInputStream(), multipartFile.getSize()));
//
//        file = fileRepository.save(file);
//
////        log.info("Persisted {} with id: {}", multipartFile.getOriginalFilename(), file.getId());
//        return ResponseEntity.created(new URI("http://localhost:8080/blobs/" + file.getId())).build();
//    }
//
//    @Transactional
//    @RequestMapping(value = "/blobs/{id}", method = RequestMethod.GET)
//    public void load(@PathVariable("id") long id, HttpServletResponse response) throws SQLException, IOException {
////        log.info("Loading file id: {}", id);
//        File file = fileRepository.findById(id).orElseThrow(FileNotFoundException::new);
//
//        response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
//        IOUtils.copy(file.getData().getBinaryStream(), response.getOutputStream());
////        log.info("Sent file id: {}", id);
//    }
//}