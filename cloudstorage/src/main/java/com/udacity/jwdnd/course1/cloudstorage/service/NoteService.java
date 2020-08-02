package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteMapper notesMapper;
    private UserMapper userMapper;

    public NoteService(NoteMapper notesMapper, UserMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public List<Note> getAllNotes(Integer userid) {
        List<Note> notes = this.notesMapper.findByUserId(userid);
        return notes;
    }

    public void addNote(Note note) {
        Integer noteid = this.notesMapper.addNote(note);
    }

    public void updateNote(Note note) {
        Integer n_record = this.notesMapper.updateNote(note);
    }

    public void deleteNote(int noteid) {
        notesMapper.deleteNote(noteid);
    }

}