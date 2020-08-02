package com.udacity.jwdnd.course1.cloudstorage.model;


public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private Integer userid;
    private byte[] fileData;

    public File(Integer fileId, String fileName, String contentType, Long fileSize, Integer userid, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userid = userid;
        this.fileData = fileData;
    }
    public File(){}

    public void setUserid(Integer userid){
        this.userid = userid;
    }
    public String getFileName(){
        return this.fileName;
    }
    public String getContentType(){
        return this.contentType;
    }
    public Integer getFileId(){
        return this.fileId;
    }
    public Integer getUserid(){
        return this.userid;
    }
    public byte[] getFileData(){
        return this.fileData;
    }
    public Long getFileSize(){return this.fileSize;}
    public void setFileName(String fileName){this.fileName = fileName;}

    public void setContentType(String contentType){this.contentType = contentType;}

    public void setFileId(Integer id){this.fileId = id;}

    public void setFileSize(Long fileSize){this.fileSize = fileSize;}

    public void setFileData(byte[] fileData){this.fileData = fileData;}

}

