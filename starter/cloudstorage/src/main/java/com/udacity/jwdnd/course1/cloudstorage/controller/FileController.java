package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {
    FileService fileService;
    FileMapper fileMapper;
    UserMapper userMapper;

    public FileController(FileService fileService, FileMapper fileMapper, UserMapper userMapper) {
        this.fileService = fileService;
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    @PostMapping("/files")
    public String createOrUpdateFiles(Authentication authentication, MultipartFile fileUpload, Model model) throws IOException {
        String username = authentication.getName();
        Integer userid = this.userMapper.getUser(username).getUserId();
        try {
            this.fileService.addFile(fileUpload, userid);
        } catch (IOException e) {
            throw e;
        }
        return "redirect:/result?UploadSuccess";
    }
    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("id") int fileId) {
        if (fileId > 0) {
            this.fileService.deleteFile(fileId);
            return "redirect:/result?DeleteSuccess";
        }
        return "redirect:/result?DeleteError";
    }

    @RequestMapping(value = "/file/download/{fileid}")
    public ResponseEntity<Resource> viewFile(HttpServletRequest request, @PathVariable("fileid") Integer fileid){
        File file = fileService.getFileById(fileid);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(file.getFileData());

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.getFileSize())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(resource);
    }
}
