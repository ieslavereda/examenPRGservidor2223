package es.ieslavereda.examenprgservidor2223.repository;


import es.ieslavereda.examenprgservidor2223.model.MyDataSource;
import es.ieslavereda.examenprgservidor2223.model.Person;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
        List<Person> persons = new ArrayList<>();
        String query = "select * from person";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)
            ){
            while(rs.next())
                persons.add(Person.builder().id(rs.getInt(1)).
                        first_name(rs.getString(2)).
                        last_name(rs.getString(3)).
                        house_id(rs.getInt(4)).build());
        }
        return persons;
    }

    @Override
    public Person deletePersonById(int id) throws SQLException {
        Person person=getPersonById(id);
        String query = "delete from person where id = ?";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(query)){
            cs.setInt(1,id);
            cs.executeUpdate();
        }
        return person;
    }

    @Override
    public Person updatePerson(Person person1) throws SQLException {
        String query = "update person set first_name = ?, last_name = ?, house_id = ? where id = ?";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            PreparedStatement cs = connection.prepareStatement(query)){
            cs.setInt(4,person1.getId());
            cs.setString(1,person1.getFirst_name());
            cs.setString(2, person1.getLast_name());
            cs.setInt(3,person1.getHouse_id());
            cs.executeUpdate();
        }
        return person1;

    }

    @Override
    public Person addPerson(Person person1) throws SQLException {
        Person person=null;
        String query = "{ ? = call createPerson(?,?,?,?)}";
        try(Connection connection = MyDataSource.getMyDataSource().getConnection();
            CallableStatement cs = connection.prepareCall(query)){
            cs.setNull(2,0);
            cs.setString(3, person1.getFirst_name());
            cs.setString(4, person1.getLast_name());
            cs.setInt(5,person1.getHouse_id());
            cs.execute();
            person = Person.builder().id(cs.getInt(1)).
                        first_name(person1.getFirst_name()).
                        last_name(person1.getLast_name()).
                        house_id(person1.getHouse_id()).build();
        }
        return person;
    }
}
