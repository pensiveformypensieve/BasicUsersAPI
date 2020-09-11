package com.horcrux.ravenclaw.web.rest;

import com.horcrux.ravenclaw.service.UsersService;
import com.horcrux.ravenclaw.service.dto.UsersRequest;
import com.horcrux.ravenclaw.service.dto.UsersResponse;
import io.micrometer.core.annotation.Timed;
import javassist.NotFoundException;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UsersResponse> authenticateUser(@RequestBody UsersRequest usersRequest) throws URISyntaxException, NotFoundException {
        log.debug("REST request to authenticate user : {}", usersRequest);

        UsersResponse result = usersService.authenticateUser(usersRequest);
        log.debug("response:{}", result);

        return ResponseEntity.ok().body(result);
    }
}
