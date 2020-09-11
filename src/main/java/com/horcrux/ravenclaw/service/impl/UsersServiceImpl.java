package com.horcrux.ravenclaw.service.impl;

import com.horcrux.ravenclaw.domain.Users;
import com.horcrux.ravenclaw.repository.RolesRepository;
import com.horcrux.ravenclaw.repository.UsersRepository;
import com.horcrux.ravenclaw.service.UsersService;
import com.horcrux.ravenclaw.service.dto.UsersRequest;
import com.horcrux.ravenclaw.service.dto.UsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

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

        try {
            log.debug("usersRequest:{}", usersRequest);
            log.debug("userId:{}", userId);
            Users user = usersRepository.getUserByUserId(userId);
            log.debug("user:{}",user);
            if(user!=null && user.getPassword().equals(password)) {
                response.setRoles(rolesRepository.findRolesByUserId(userId));
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
            response.setResult("fail");
            return errorResponse;
        }

        return response;
    }


}
