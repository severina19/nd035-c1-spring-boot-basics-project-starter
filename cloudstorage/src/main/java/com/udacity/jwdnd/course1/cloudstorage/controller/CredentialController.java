package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CredentialController {

    private CredentialService credentialService;
    private UserMapper userMapper;

    public CredentialController(CredentialService credentialService, UserMapper userMapper) {
        this.credentialService = credentialService;
        this.userMapper = userMapper;
    }

    @PostMapping("/credentials")
    public String addCredential(Authentication authentication, Credential credential, Model model){
        String username = authentication.getName();
        Integer userid = this.userMapper.getUser(username).getUserId();
        credential.setUserid(userid);

        if (!ObjectUtils.isEmpty(credential.getCredentialid())){
            this.credentialService.updateCredential(credential);
        } else {
            this.credentialService.addCredential(credential);
        }
        return "redirect:/result?CredentialSuccess";
    }
    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("id") int credentialid){
        this.credentialService.deleteCrendential(credentialid);
        if (credentialid > 0) {
            this.credentialService.deleteCrendential(credentialid);
            return "redirect:/result?CredentialDeleteSuccess";
        }
        return "redirect:/result?DeleteError";

    }
}
