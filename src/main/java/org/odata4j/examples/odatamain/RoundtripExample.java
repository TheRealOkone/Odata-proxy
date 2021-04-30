package org.odata4j.examples.odatamain;

import org.core4j.Func;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.examples.proxy.JDBCBase;
import org.odata4j.examples.proxy.Row;
import org.odata4j.producer.inmemory.InMemoryProducer;
import org.odata4j.producer.resources.DefaultODataProducerProvider;
import org.odata4j.producer.server.ODataServer;

import java.util.ArrayList;
import java.util.List;

import static org.odata4j.examples.odatamain.JaxRsImplementation.JERSEY;

public class RoundtripExample extends AbstractExample {

  JDBCBase a;

  private String password;
  private String user;
  private String url;
  private String query;
  private String place;

  public RoundtripExample( String password, String user,String url, String query,String place) {
    this.password = password;
    this.user = user;
    this.url = url;
    this.query = query;
    this.place = place;
    a = new JDBCBase(url, user, password,query);
  }


  public static class Customer {

    private String id;
    private String name;
    private String column3;
    private String column4;
    private String column5;
    private String column6;
    private String column7;
    private String column8;
    private String column9;
    private String column10;
    private String column11;
    private String column12;
    private String column13;
    private String column14;
    private String column15;
    private String column16;

    public Customer() {}

    public Customer(String id, String name, String column3, String column4, String column5, String column6, String column7, String column8, String hatejava, String okone, String linleytvov, String dungeon, String hatejavaa, String okonea, String linleytvova, String dungeona) {
      this.id = id;
      this.name = name;
      this.column3 = column3;
      this.column4 = column4;
      this.column5 = column5;
      this.column6 = column6;
      this.column7 = column7;
      this.column8 = column8;
      this.column9 = hatejava;
      this.column10 = okone;
      this.column11 = linleytvov;
      this.column12 = dungeon;
      this.column13 = hatejavaa;
      this.column14 = okonea;
      this.column15 = linleytvova;
      this.column16 = dungeona;
    }

    public String getColumn9() {
      return column9;
    }

    public void setColumn9(String column9) {
      this.column9 = column9;
    }

    public String getColumn10() {
      return column10;
    }

    public void setColumn10(String column10) {
      this.column10 = column10;
    }

    public String getColumn11() {
      return column11;
    }

    public void setColumn11(String column11) {
      this.column11 = column11;
    }

    public String getColumn12() {
      return column12;
    }

    public void setColumn12(String column12) {
      this.column12 = column12;
    }

    public String getColumn13() {
      return column13;
    }

    public void setColumn13(String column13) {
      this.column13 = column13;
    }

    public String getColumn14() {
      return column14;
    }

    public void setColumn14(String column14) {
      this.column14 = column14;
    }

    public String getColumn15() {
      return column15;
    }

    public void setColumn15(String column15) {
      this.column15 = column15;
    }

    public String getColumn16() {
      return column16;
    }

    public void setColumn16(String column16) {
      this.column16 = column16;
    }

    public String getColumn3() {
      return column3;
    }

    public void setColumn3(String column3) {
      this.column3 = column3;
    }

    public String getColumn4() {
      return column4;
    }

    public void setColumn4(String column4) {
      this.column4 = column4;
    }

    public String getColumn5() {
      return column5;
    }

    public void setColumn5(String column5) {
      this.column5 = column5;
    }

    public String getColumn6() {
      return column6;
    }

    public void setColumn6(String column6) {
      this.column6 = column6;
    }

    public String getColumn7() {
      return column7;
    }

    public void setColumn7(String column7) {
      this.column7 = column7;
    }

    public String getColumn8() {
      return column8;
    }

    public void setColumn8(String column8) {
      this.column8 = column8;
    }

    public String getId() {
      return id;
    }


    public String getName() {
      return name;
    }

    public void setId(String id) {
      this.id = id;
    }

    public void setName(String name) {
      this.name = name;
    }


  }


  public void run() {

    // create/start the server
    String endpointUri = "http://localhost:8885/RoundtripExample.svc/";

    InMemoryProducer producer = new InMemoryProducer("RoundtripExample",100000);

    List<Row> b;
    a.createconnection();
    b = a.altselect();
    a.close();
    final ArrayList<ArrayList<String>> dt = new ArrayList<ArrayList<String>>();
    for(int k=0;k<b.size();k++){
      for(int j = b.get(k).out().size(); j < 20; j++){
        b.get(k).add("");
      }
      dt.add(b.get(k).out());
    }




    producer.register(Customer.class, "Customers", new Func<Iterable<Customer>>() {
      public Iterable<Customer> apply() {
        List<Customer> customers = new ArrayList<Customer>();
//        for(int k = 0;k < dt.size(); k ++) {
//          customers.add(new Customer(String.valueOf(k), dt.get(k).get(1), dt.get(k).get(3), dt.get(k).get(4)));
//        }
        for(int k = 0;k < dt.size(); k ++) {
          customers.add(new Customer(dt.get(k).get(0),
                                      dt.get(k).get(1),
                  dt.get(k).get(2),
                  dt.get(k).get(3),
          dt.get(k).get(4),
          dt.get(k).get(5),
          dt.get(k).get(6),
          dt.get(k).get(7),
          dt.get(k).get(8),
          dt.get(k).get(9),
          dt.get(k).get(10),
          dt.get(k).get(11),
          dt.get(k).get(12),
          dt.get(k).get(13),
          dt.get(k).get(14),
          dt.get(k).get(15)));
        }
        return customers;
      }
    }, "Id");
    DefaultODataProducerProvider.setInstance(producer);
    ODataServer server = new ODataServerFactory(JERSEY).startODataServer(endpointUri);


    // create the client
    ODataConsumer.dump.responseHeaders(true);
    ODataConsumer consumer = ODataConsumers.create(endpointUri);

    reportEntities("Customers", consumer.getEntities("Customers").execute());

    for (Customer customer : consumer.getEntities(Customer.class, "Customers").execute())
      report(customer.toString());

    // stop the server
    //server.stop();
  }

}
