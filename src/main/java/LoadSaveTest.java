import es.ieslavereda.examenprgservidor2223.model.Person;
import es.ieslavereda.examenprgservidor2223.repository.PersonRepository;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class LoadSaveTest {
    public static PersonRepository ps = new PersonRepository();

    public static void main(String[] args) {

        try {
            savePerson("ObjectosPersona.bin");
            loadPerson("carga.csv");
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public static void savePerson(String datafile) throws IOException, SQLException {
        try(ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(datafile)))){
            List<Person> persons = ps.getAllPerson();
            for (Person p : persons) {
                os.writeObject(p);
            }
        }
    }

    public static void loadPerson(String loadfile) throws SQLException,IOException {
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(loadfile))){
            do {
                line = br.readLine();
                if(line != null) {
                    String[] lines = line.split(",");
                    Person person = Person.builder().id(0).first_name(lines[0]).last_name(lines[1]).house_id(Integer.parseInt(lines[2])).build();
                    ps.addPerson(person);
                }
            } while (line != null);
        }
    }
}
