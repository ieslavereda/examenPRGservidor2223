package es.ieslavereda.examenprgservidor2223.controller;

import es.ieslavereda.examenprgservidor2223.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PersonController {
    @Autowired
    PersonService service;

    @GetMapping("person/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(service.getPersonById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
