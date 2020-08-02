package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    Integer noteid;
    String notetitle;
    String noteDescription;
    Integer userid;

    public Note(Integer noteid, String notetitle, String noteDescription, Integer userid) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.noteDescription = noteDescription;
        this.userid = userid;
    }

    public Integer getNoteid(){
        return this.noteid;
    }
    public String getNotetitle(){
        return this.notetitle;
    }
    public String getNoteDescription(){
        return this.noteDescription;
    }
    public Integer getUserId(){
        return this.userid;
    }
    public void setUserId(Integer userid){
        this.userid = userid;
    }
}
