package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void addFile(MultipartFile fileUpload, Integer userid) throws IOException{

        File file = new File();
        try {
            file.setContentType(fileUpload.getContentType());
            file.setFileData(fileUpload.getBytes());
            file.setFileName(fileUpload.getOriginalFilename());
            file.setFileSize(fileUpload.getSize());
        } catch (IOException e) {
            throw e;
        }
        file.setUserid(userid);

        this.fileMapper.addFile(file);
    }

    public List<File> getAllFiles(Integer userid){
        return this.fileMapper.findByUserId(userid);
    }
    public void deleteFile(int fileid) {
        fileMapper.deleteFile(fileid);
    }
    public File getFileById(int fileid) {
        return fileMapper.findByFileId(fileid);
    }
    public Boolean userHasFile(Integer userid, String fileName){
        if (!ObjectUtils.isEmpty(fileMapper.findByUserIdAndFileName(userid, fileName)))
        {
            return true;
        }

        return false;
    }

}
