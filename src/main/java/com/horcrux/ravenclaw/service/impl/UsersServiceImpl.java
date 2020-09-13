package com.horcrux.ravenclaw.service.impl;

import com.horcrux.ravenclaw.domain.Users;
import com.horcrux.ravenclaw.repository.RolesRepository;
import com.horcrux.ravenclaw.repository.UsersRepository;
import com.horcrux.ravenclaw.service.UsersService;
import com.horcrux.ravenclaw.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    final static Logger log = LoggerFactory.getLogger(UsersServiceImpl.class.getName());

    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final RolesRepository rolesRepository;

    public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    public UsersResponse authenticateUser(UsersRequest usersRequest){

        String userId = usersRequest.getUserId();
        String password = usersRequest.getPassword();

        UsersResponse response = new UsersResponse();

        Boolean isPasswordMatched = false;

        try {
            log.debug("usersRequest:{}", usersRequest);
            log.debug("userId:{}", userId);
            Users user = usersRepository.findByUserId(userId);
            log.debug("user:{}",user);

            if (user != null) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//                log.debug("encodedpassword:{}",encoder.encode(password));
                String retrievedPassword = user.getPassword();
                isPasswordMatched = encoder.matches(password,retrievedPassword);
                log.debug("isPasswordMatched:{}", isPasswordMatched);
            }

            if(isPasswordMatched == true) {
                log.debug("finding roles");
                response.setRoles(rolesRepository.getRolesByUserId(userId));
                response.setResult("success");
            }
            else{
                log.debug("userId or password does not match");
                response.setResult("fail");
                return response;
            }
        }

        catch(Exception ex){
            log.debug("user not found");
            UsersResponse errorResponse = new UsersResponse();
            errorResponse.setResult("fail");
            return errorResponse;
        }

        return response;
    }


    public UsersStatusResponse userStatusCheck(String userId){

        UsersStatusResponse response = new UsersStatusResponse();

        try {
            log.debug("userId:{}", userId);
            Users user = usersRepository.findByUserId(userId);
            log.debug("user:{}",user);
                if("true".equals(user.getActiveStatus())) {
                    log.debug("finding status");
                    response.setStatus("true");
                }
            else{
                log.debug("inactive user");
                response.setStatus("false");
                return response;
            }
        }

        catch(Exception ex){
            log.debug("user not found");
            UsersStatusResponse errorResponse = new UsersStatusResponse();
            response.setStatus("error");
            return errorResponse;
        }

        return response;
    }

    public UsersDeactivateResponse deactivateUser(String userId){

        UsersDeactivateResponse response = new UsersDeactivateResponse();

        log.debug("userId:{}", userId);
        Users user = usersRepository.getUserByUserId(userId);
        log.debug("checking user's existing status");
        String userStatus = userStatusCheck(userId).getStatus();

        log.debug("userStatus:{}", userStatus);
        if ("true".equals(userStatus)){
            user.setActiveStatus("false");
            usersRepository.save(user);
            response.setResult("success");
        }

        else{
            UsersDeactivateResponse responseError = new UsersDeactivateResponse();
            responseError.setResult("fail");
            return responseError;
        }
        return response;
    }
}
