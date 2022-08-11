package com.graduation.authentication.service;

import com.graduation.authentication.model.*;
import com.graduation.authentication.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.*;

@Service("userService")
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User addStudent(User u){
        User user=userRepository.save(u);
        Role role=roleRepository.findById(3).get();
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('register_student')")
    public User addStudent(String username, String password, String email) {
        String pass=passwordEncoder.encode(password);
        User user=new User(username,pass,email,true,true,true,true);
        Role role=roleRepository.findById(2).get();
        user.getRoles().add(role);
        return userRepository.save(user);
    }
    public User changeMyPassword(Principal principal, String password){
        User user=userRepository.findByUsername(principal.getName()).get();
        String pass=passwordEncoder.encode(password);
        user.setPassword(pass);
        return userRepository.save(user);
    }

    public HashMap<String,String> login(String username,String password){
        LinkedMultiValueMap<String,String> logMap=new LinkedMultiValueMap<>();
        logMap.put("password", Collections.singletonList(password));
        logMap.put("username",Collections.singletonList(username));
        logMap.put("grant_type",Collections.singletonList("password"));
        Login login=new Login("password",username,password);
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        List<MediaType> mediaTypes=new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypes);
        HttpEntity<LinkedMultiValueMap<String,String>> Request=new HttpEntity<>(logMap,httpHeaders);

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("mobile","pin"));
        HashMap<String ,Object> response=restTemplate.postForObject("http://localhost:9191/oauth/token",Request,HashMap.class);

        HashMap<String,String> res=new HashMap<>();
        if(response!=null) {
            res.put("token", response.get("access_token").toString());
            res.put("exp",response.get("expires_in").toString());
            res.put("role","1");
        }
            return res;
    }

}
