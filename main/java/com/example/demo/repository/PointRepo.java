package com.example.demo.repository;

import com.example.demo.entity.PointEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PointRepo extends CrudRepository<PointEntity, Long> {

    ArrayList<PointEntity> getAllByUser(UserEntity user);

    void deleteAllByUser(UserEntity user);
}
