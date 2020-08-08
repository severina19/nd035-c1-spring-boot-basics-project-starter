package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<File> findByUserId(Integer userid);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File findByFileId(Integer fileid);

    @Select("SELECT * FROM FILES WHERE userid = #{userid} and fileName = #{fileName}")
    List<File> findByUserIdAndFileName(Integer userid, String fileName);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userid, fileData)" +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFile(int fileId);
}