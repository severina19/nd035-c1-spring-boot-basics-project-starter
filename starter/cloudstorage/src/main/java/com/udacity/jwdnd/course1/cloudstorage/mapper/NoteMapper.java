package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, noteDescription, userid)" +
            "VALUES(#{notetitle}, #{noteDescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> findByUserId(Integer userid);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteNote(int noteid);

    @Update("UPDATE NOTES set noteDescription = #{noteDescription}, notetitle=#{notetitle} where noteid = #{noteid}")
    int updateNote(Note note);



}
