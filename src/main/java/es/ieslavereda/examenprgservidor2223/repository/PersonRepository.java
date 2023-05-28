package es.ieslavereda.examenprgservidor2223.repository;


import es.ieslavereda.examenprgservidor2223.model.MyDataSource;
import es.ieslavereda.examenprgservidor2223.model.Person;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonRepository implements IPersonRepository {


    @Override
    public Person getPersonById(int id) throws SQLException {
        Person person=null;
        String query = "{call getPerson(?)}";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setInt(1,id);
            ResultSet rs = cs.executeQuery();
            if(rs.next())
                person = Person.builder().id(rs.getInt(1)).
                        first_name(rs.getString(2)).
                        last_name(rs.getString(3)).
                        house_id(rs.getInt(4)).build();
        }
        return person;
    }

    @Override
    public List<Person> getAllPerson() throws SQLException {
        return null;
    }

    @Override
    public Person deletePersonById(int id) throws SQLException {
        return null;
    }

    @Override
    public Person updatePerson(Person person) throws SQLException {
        return null;
    }

    @Override
    public Person addPerson(Person person) throws SQLException {
        return null;
    }

    @Override
    public Person addPersonById(Person person) throws SQLException {
        return null;
    }
}
