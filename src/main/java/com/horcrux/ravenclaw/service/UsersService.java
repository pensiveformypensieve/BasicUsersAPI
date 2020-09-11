package com.horcrux.ravenclaw.service;

import com.horcrux.ravenclaw.service.dto.UsersRequest;
import com.horcrux.ravenclaw.service.dto.UsersResponse;

public interface UsersService {

    UsersResponse authenticateUser(UsersRequest usersRequest);
}
