package es.ieslavereda.examenprgservidor2223.controller;

import es.ieslavereda.examenprgservidor2223.model.Person;
import es.ieslavereda.examenprgservidor2223.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("person/")
    public ResponseEntity<?>  getAllPerson(){
        try{
            return new ResponseEntity<>(service.getAllPerson(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(service.deletePersonById(id), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("person/")
    public ResponseEntity<?>  updatePerson(@RequestBody Person person){
        try{
            return new ResponseEntity<>(service.updatePerson(person), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("person/")
    public ResponseEntity<?>  addPerson(@RequestBody Person person){
        try{
            return new ResponseEntity<>(service.addPerson(person), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
