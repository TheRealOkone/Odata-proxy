package org.odata4j.examples.proxy;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;


public class Row {
    public List<Entry<Object, Class>> row;
    public static Map<String, Class> TYPE;

    static {
        TYPE = new HashMap<String, Class>();

        TYPE.put("INTEGER", Integer.class);
        TYPE.put("TINYINT", Integer.class);
        TYPE.put("SMALLINT", Short.class);
        TYPE.put("BIGINT", Long.class);
        TYPE.put("REAL", Float.class);
        TYPE.put("FLOAT", Double.class);
        TYPE.put("DOUBLE", Double.class);
        TYPE.put("DECIMAL", BigDecimal.class);
        TYPE.put("NUMERIC", BigDecimal.class);
        TYPE.put("BOOLEAN", Boolean.class);
        TYPE.put("CHAR", String.class);
        TYPE.put("VARCHAR", String.class);
        TYPE.put("LONGVARCHAR", String.class);
        TYPE.put("DATE", Date.class);
        TYPE.put("TIME", Time.class);
        TYPE.put("TIMESTAMP", Timestamp.class);
        TYPE.put("SERIAL",Integer.class);
        TYPE.put("DATETIME", DateTime.class);
    }

    public Row() {
        row = new ArrayList<Entry<Object, Class>>();
    }

    public <T> void add(T data) {
        if(data != null) {
            row.add(new AbstractMap.SimpleImmutableEntry<Object, Class>(data, data.getClass()));
        }else{
            row.add(null);
        }
    }

    public void add(Object data, String sqlType) {
        System.out.println(sqlType.toUpperCase());

        Class castType = Row.TYPE.get(sqlType.toUpperCase());
        if (sqlType.toUpperCase() == "DATETIME") {
            String a = data.toString();
            castType = String.class;
            try{
                this.add(a);
                return;
            } catch (ClassCastException ee){
                System.out.println("ClassCastException");
            }
        }
        if (sqlType.toUpperCase() == "TINYINT") {
            Boolean a = (Boolean)data;
            castType = Boolean.class;
            try{
                this.add(a);
                return;
            } catch (ClassCastException ee){
                System.out.println("ClassCastException");
            }
        }
        try {
            this.add(castType.cast(data));
        } catch (NullPointerException e) {
            System.out.println(sqlType);
            this.add(null);
        } catch (ClassCastException e){
            try {
                this.add((Float) (data));
            } catch (ClassCastException ee){
                System.out.println("ClassCastException");
            }
        }
    }

    public static void formTable(ResultSet rs, List<Row> table)
            throws SQLException {
        if (rs == null)
            return;

        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();

            int NumOfCol = rsmd.getColumnCount();

            while (rs.next()) {
                Row current_row = new Row();

                for (int i = 1; i <= NumOfCol; i++) {
                    System.out.println(rs.getObject(i)+ " " + rsmd.getColumnTypeName(i));
                    current_row.add(rs.getObject(i), rsmd.getColumnTypeName(i));
                }

                table.add(current_row);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<String> out() {
        ArrayList<String> arr = new ArrayList<String>();

        for(int i = 0;i< row.size(); i++){
            try {
                arr.add(String.valueOf(row.get(i).getKey()));
            }catch(Exception e){
                arr.add("");
            }
        }
        return arr;
    }
}
