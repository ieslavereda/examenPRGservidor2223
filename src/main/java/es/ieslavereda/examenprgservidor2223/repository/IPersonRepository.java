package es.ieslavereda.examenprgservidor2223.repository;

import es.ieslavereda.examenprgservidor2223.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface IPersonRepository {
    Person getPersonById(int id) throws SQLException;
    List<Person> getAllPerson() throws SQLException;
    Person deletePersonById(int id) throws SQLException;
    Person updatePerson(Person person) throws SQLException;
    Person addPerson(Person person) throws SQLException;

}
