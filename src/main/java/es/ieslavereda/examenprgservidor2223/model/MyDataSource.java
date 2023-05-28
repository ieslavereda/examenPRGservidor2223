package es.ieslavereda.examenprgservidor2223.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;

public class MyDataSource {
    public static DataSource getMyDataSource() {
        MysqlDataSource mySQL = new MysqlDataSource();
        mySQL.setURL("jdbc:mysql://localhost:3306/bbddJava");
        mySQL.setUser("examen");
        mySQL.setPassword("1234");
        return mySQL;
    }
}
