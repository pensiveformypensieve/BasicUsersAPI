package com.horcrux.ravenclaw.service;

import com.horcrux.ravenclaw.service.dto.UsersRequest;
import com.horcrux.ravenclaw.service.dto.UsersResponse;
import com.horcrux.ravenclaw.service.dto.UsersStatusResponse;

public interface UsersService {

    UsersResponse authenticateUser(UsersRequest usersRequest);

    UsersStatusResponse userStatusCheck(String userId);
}
