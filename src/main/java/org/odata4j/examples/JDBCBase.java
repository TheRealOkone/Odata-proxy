package org.odata4j.examples;

import org.odata4j.examples.Row;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBase {


    String db_url;

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    String user;

    String password;



    private  Connection connection = null;
    public JDBCBase(String db_url, String user, String password){
        this.user = user;
        this.password = password;
        this.db_url = db_url;

    }
    public void createconnection(){

        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(db_url, user, password);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseEntity<List<Row>> select() {
        Statement stm = null;
        List<Row> table = new ArrayList<Row>();
        try {
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from time_entries");
            Row.formTable(rs, table);
            return new ResponseEntity<List<Row>>(table, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ResponseEntity<List<Row>>(table, HttpStatus.OK);
    }

    public List<Row> altselect() {
        Statement stm = null;
        List<Row> table = new ArrayList<Row>();
        try {
            stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from time_entries");
            Row.formTable(rs, table);
            return table;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return table;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }





}
