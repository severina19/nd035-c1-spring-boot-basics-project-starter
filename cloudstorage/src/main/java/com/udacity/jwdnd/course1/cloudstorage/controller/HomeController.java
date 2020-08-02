package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.apache.catalina.startup.CredentialHandlerRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private NoteService noteService;
    private UserMapper userMapper;
    private FileService fileService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService, UserMapper userMapper, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping(value = {"/", "/home"})
    public ModelAndView homeView(Authentication authentication)  throws Exception{
        String username = authentication.getName();

        ModelAndView modelAndView = new ModelAndView("home");
        Integer userid = this.userMapper.getUserIdByName(username);
        modelAndView.addObject("notes", this.noteService.getAllNotes(userid));
        modelAndView.addObject("files", this.fileService.getAllFiles(userid));
        modelAndView.addObject("credentials", this.credentialService.getAllCredentials(userid));

        return modelAndView;
    }

    @GetMapping("/result")
    public String result() {
        return "result";

    }
}
