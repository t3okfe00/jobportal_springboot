package com.feodev.jobportal.services;


import com.feodev.jobportal.entity.Users;
import com.feodev.jobportal.repository.UsersRepository;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users addNew(Users user){
        user.setActive(true);
        user.setRegistrationDate( new Date(System.currentTimeMillis()));
        return usersRepository.save(user);
    }

    public Optional<Users> getByEmail(String email){
        return  usersRepository.findByEmail(email);
    }
}
