package com.example.demo.service;

import com.example.demo.annotations.LogInitMethods;
import com.example.demo.annotations.Loggable;
import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.entity.PointEntity;
import com.example.demo.model.PointToAdd;
import com.example.demo.repository.PointRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@LogInitMethods
public class PointService {
    @Autowired
    private PointRepo pointRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Loggable
    public PointEntity fromRawToEntity(PointToAdd point, String token){
        long startTime = System.nanoTime();

        getAllUserPoints(token);

        PointEntity entity = new PointEntity();

        entity.setxValue(point.getxValue());
        entity.setyValue(point.getyValue());
        entity.setrValue(point.getrValue());

        entity.setHitResult(isItInArea(entity.getxValue(),entity.getyValue(),entity.getrValue()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        entity.setCurrentTime(dateFormat.format(new Date(System.currentTimeMillis())));

        entity.setUser(userRepo.findByUsername(jwtProvider.getLoginFromToken(token)));

        String executionTime = String.valueOf(System.nanoTime() - startTime);
        executionTime = executionTime.substring(0,3);
        executionTime +="\n\nмкс";

        entity.setExecutionTime(executionTime);

        return entity;
    }

    @Loggable
    public ArrayList<PointEntity> getAllUserPoints(String token){
        ArrayList<PointEntity> points =  pointRepo.getAllByUser(userRepo.findByUsername(jwtProvider.getLoginFromToken(token)));
        if (points != null){
            return points;
        } else {
            return  new ArrayList<>();
        }
    }

    @Transactional
    @Loggable
    public void deleteAllUserPoints(String token) {
        pointRepo.deleteAllByUser(userRepo.findByUsername(jwtProvider.getLoginFromToken(token)));
    }

    @Loggable
    public PointEntity addPoint(PointEntity point){
        return pointRepo.save(point);
    }

    @Loggable
    private boolean isItInArea(double x, double y, double r){
        return ((isHitRectangle(x, y, r) || isHitCircle(x, y, r) ||
                isHitTriangle(x, y, r)));
    }

    @Loggable
    private boolean isHitRectangle(double xValue, double yValue, double rValue) {
        return xValue >= 0 && yValue >= 0 && yValue <= rValue && xValue <= rValue;
    }

    @Loggable
    private boolean isHitCircle(double xValue, double yValue, double rValue) {
        return xValue <= 0 && yValue <= 0 && Math.sqrt(xValue*xValue + yValue*yValue) <= rValue;
    }

    @Loggable
    private boolean isHitTriangle(double xValue, double yValue, double rValue) {
        return xValue >= 0 && yValue <= 0 && yValue >= (xValue - rValue)/2;
    }


}
