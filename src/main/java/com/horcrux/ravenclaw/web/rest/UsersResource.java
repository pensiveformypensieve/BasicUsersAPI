package com.horcrux.ravenclaw.web.rest;

import com.horcrux.ravenclaw.service.UsersService;
import com.horcrux.ravenclaw.service.dto.*;
import io.micrometer.core.annotation.Timed;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsersResource {
    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    private final UsersService usersService;

    public UsersResource(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/security/authenticate")
    @Timed
    public ResponseEntity<?> authenticateUser(@RequestBody UsersRequest usersRequest) throws URISyntaxException, NotFoundException {
        log.debug("REST request to authenticate user : {}", usersRequest);

        UsersResponse result = usersService.authenticateUser(usersRequest);
        UsersResponseError resultError = new UsersResponseError();
        log.debug("response:{}", result);

        if (result.getResult().equals("fail")){
            resultError.setResult("fail");
            resultError.setErrorMessage("invalid password or user id");
            return ResponseEntity.ok().body(resultError);
        }

        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/security/userState")
    public ResponseEntity<UsersStatusResponse> userStatusCheck(@RequestParam(value="userId") String userId)
            throws ResourceNotFoundException {

        UsersStatusResponse result = usersService.userStatusCheck(userId);

        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/security/deactivate")
    public ResponseEntity<?> deactivateUser(@RequestParam(value="userId") String userId)
            throws ResourceNotFoundException {

        UsersDeactivateResponse result = usersService.deactivateUser(userId);
        UsersResponseError resultError = new UsersResponseError();
        log.debug("response:{}", result);

        if (result.getResult().equals("fail")){
            resultError.setResult("fail");
            resultError.setErrorMessage("invalid user");
            return ResponseEntity.ok().body(resultError);
        }

        return ResponseEntity.ok().body(result);
    }
}
