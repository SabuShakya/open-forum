package com.sabu.openforium.service;

import com.sabu.openforium.dto.UserRequestDTO;
import com.sabu.openforium.entity.Users;

public interface UserService {

    public void createUser(UserRequestDTO user);
}
