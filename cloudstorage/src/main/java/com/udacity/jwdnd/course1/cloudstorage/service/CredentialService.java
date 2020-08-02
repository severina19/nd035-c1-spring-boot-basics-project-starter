package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public Integer addCredential(Credential credential){
        credential = encryptPassword(credential);
        return this.credentialMapper.addCredential(credential);
    }

    private Credential encryptPassword(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encryptedPassword = this.encryptionService.encryptValue(credential.getPassword(), encodedSalt);
        credential.setKey(encodedSalt);
        credential.setPassword(encryptedPassword);
        return credential;
    }
    private Credential decryptPassword(Credential credential) {
        credential.setPassword(this.encryptionService.decryptValue(credential.getPassword(),
                credential.getKey()));
        return credential;
    }

    public void deleteCrendential(Integer credentialid){
        this.credentialMapper.deleteCredential(credentialid);
    }
    public List<Credential> getAllCredentials(Integer userId) throws Exception{
        List<Credential> credentials = this.credentialMapper.findByUserId(userId);
        if (credentials == null) {
            throw new Exception();
        }
        return credentials.stream().map(this::decryptPassword).collect(Collectors.toList());
    }

    public Integer updateCredential(Credential credential) {
        return this.credentialMapper.updateCredential(encryptPassword(credential));
    }

}
