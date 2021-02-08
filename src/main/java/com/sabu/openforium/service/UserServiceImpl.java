package com.sabu.openforium.service;

import com.sabu.openforium.dto.UserRequestDTO;
import com.sabu.openforium.entity.Users;
import com.sabu.openforium.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserRequestDTO user) {

        Users users = new Users();
        users.setName(user.getName());
        users.setUsername(user.getUsername());
        users.setPassword(user.getPassword());
        userRepository.save(users);
    }
}
