package com.example.demo.controller;

import com.example.demo.annotations.Loggable;
import com.example.demo.entity.PointEntity;
import com.example.demo.model.PointToAdd;
import com.example.demo.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping("api/points")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/add")
    private ResponseEntity addPoint(@Valid @RequestBody PointToAdd point, BindingResult bindingResult, HttpServletRequest request){
        try{
            if (bindingResult.hasErrors()){
                List<FieldError> errors = bindingResult.getFieldErrors();
                String errorMessage = "";
                for (FieldError error : errors ) {
                    errorMessage = errorMessage + error.getDefaultMessage() + '\n';
                }
                return ResponseEntity.badRequest().body(errorMessage);
            }

            String token = request.getHeader("Authorization");
            if (hasText(token) && token.startsWith("Bearer ")){
                token = token.substring(7);
            }

            PointEntity savedEntity = pointService.addPoint(pointService.fromRawToEntity(point,token));
            if (savedEntity != null){
                return ResponseEntity.ok(savedEntity);
            }
            return ResponseEntity.badRequest().body("Не удалось сохранить точку");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/get/all")
    private ResponseEntity<ArrayList<PointEntity>> getAllPoints(HttpServletRequest request){
        try{
            String token = request.getHeader("Authorization");
            if (hasText(token) && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            ArrayList<PointEntity> list = pointService.getAllUserPoints(token);
            return ResponseEntity.ok(list);

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity<String> deletePoints(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        pointService.deleteAllUserPoints(token);
        return ResponseEntity.ok("Ваши точки удалены");
    }
}
