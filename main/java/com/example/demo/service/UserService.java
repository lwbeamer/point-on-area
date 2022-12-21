package com.example.demo.service;

import com.example.demo.annotations.Loggable;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserAlreadyExistException;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Loggable
    public UserEntity saveUser(UserEntity userEntity) throws UserAlreadyExistException {
        if (findByUsername(userEntity.getUsername())!=null)
        {
            throw new UserAlreadyExistException("Пользователь с таким именем уже зарегистрирован!");
        }


        if (roleRepo.findByName("ROLE_USER") == null){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName("ROLE_USER");
            roleRepo.save(roleEntity);
        }
        RoleEntity userRole = roleRepo.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return userRepo.save(userEntity);
    }

    @Loggable
    public UserEntity findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    @Loggable
    public UserEntity findByUsernameAndPassword(String username, String password){
        UserEntity userEntity = findByUsername(username);
        if (userEntity != null){
            if (passwordEncoder.matches(password, userEntity.getPassword())){
                  return userEntity;
            }
        }
        return null;
    }
}
