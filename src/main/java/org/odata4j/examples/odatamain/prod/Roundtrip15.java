package org.odata4j.examples.odatamain.prod;

import org.core4j.Func;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.examples.odatamain.AbstractExample;
import org.odata4j.examples.odatamain.ODataServerFactory;
import org.odata4j.examples.odatamain.models.Customer1;
import org.odata4j.examples.odatamain.models.Customer15;
import org.odata4j.examples.odatamain.models.Customer9;
import org.odata4j.examples.proxy.JDBCBase;
import org.odata4j.examples.proxy.Row;
import org.odata4j.producer.inmemory.InMemoryProducer;
import org.odata4j.producer.resources.DefaultODataProducerProvider;
import org.odata4j.producer.server.ODataServer;

import java.util.ArrayList;
import java.util.List;

import static org.odata4j.examples.odatamain.JaxRsImplementation.JERSEY;

public class Roundtrip15 extends AbstractExample implements RE{

  JDBCBase a;

  private String password;
  private String user;
  private String url;
  private String query;
  private String place;

  public Roundtrip15(String password, String user, String url, String query, String place) {
    this.password = password;
    this.user = user;
    this.url = url;
    this.query = query;
    this.place = place;
    a = new JDBCBase(url, user, password,query);
  }


public static void reg15(InMemoryProducer producer, ArrayList<ArrayList<String>> dt){
  producer.register(Customer15.class, "Customers", new Func<Iterable<Customer15>>() {
    public Iterable<Customer15> apply() {
      List<Customer15> customers = new ArrayList<Customer15>();
//        for(int k = 0;k < dt.size(); k ++) {
//          customers.add(new Customer(String.valueOf(k), dt.get(k).get(1), dt.get(k).get(3), dt.get(k).get(4)));
//        }
      for(int k = 0;k < dt.size(); k ++) {
        customers.add(new Customer15(dt.get(k).get(0),
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
                dt.get(k).get(14)));
      }
      return customers;
    }
  }, "Column1");
}

  public static void reg9(InMemoryProducer producer, ArrayList<ArrayList<String>> dt){
    producer.register(Customer9.class, "Customers", new Func<Iterable<Customer9>>() {
      public Iterable<Customer9> apply() {
        List<Customer9> customers = new ArrayList<Customer9>();
//        for(int k = 0;k < dt.size(); k ++) {
//          customers.add(new Customer(String.valueOf(k), dt.get(k).get(1), dt.get(k).get(3), dt.get(k).get(4)));
//        }
        for(int k = 0;k < dt.size(); k ++) {
          customers.add(new Customer9(dt.get(k).get(0),
                  dt.get(k).get(1),
                  dt.get(k).get(2),
                  dt.get(k).get(3),
                  dt.get(k).get(4),
                  dt.get(k).get(5),
                  dt.get(k).get(6),
                  dt.get(k).get(7),
                  dt.get(k).get(8)));
        }
        return customers;
      }
    }, "Column1");
  }

  public static void reg1(InMemoryProducer producer, ArrayList<ArrayList<String>> dt){
    producer.register(Customer1.class, "Customers", new Func<Iterable<Customer1>>() {
      public Iterable<Customer1> apply() {
        List<Customer1> customers = new ArrayList<Customer1>();
//        for(int k = 0;k < dt.size(); k ++) {
//          customers.add(new Customer(String.valueOf(k), dt.get(k).get(1), dt.get(k).get(3), dt.get(k).get(4)));
//        }
        for(int k = 0;k < dt.size(); k ++) {
          customers.add(new Customer1(dt.get(k).get(0)));
        }
        return customers;
      }
    }, "Column1");
  }


  public void run() {

    // create/start the server
    String endpointUri = "http://localhost:8885/RoundtripExample.svc/";

    InMemoryProducer producer = new InMemoryProducer("Roundtrip15",100000);

    List<Row> b;
    List<String> c;
    a.createconnection();
    b = a.altselect();
    c = a.cselect();
    a.close();
    int sz = 0;
    for(int k=0;k<b.size();k++){
      if(b.get(k).out().size() > sz) {
        sz = b.get(k).out().size();
      }
    }

    final ArrayList<ArrayList<String>> dt = new ArrayList<ArrayList<String>>();
    for(int k=0;k<b.size();k++){
      for(int j = b.get(k).out().size(); j < 16; j++){
        b.get(k).add("");
      }
      dt.add(b.get(k).out());
    }
    System.out.println(sz);
    if (sz == 15) {
      reg15(producer, dt);
    }
    if (sz == 9) {
      reg9(producer, dt);
    }
    if (sz == 1) {
      reg1(producer, dt);
    }

    DefaultODataProducerProvider.setInstance(producer);
    ODataServer server = new ODataServerFactory(JERSEY).startODataServer(endpointUri);


//    // create the client
    ODataConsumer.dump.responseHeaders(true);
    ODataConsumer consumer = ODataConsumers.create(endpointUri);
//
//    reportEntities("Customers", consumer.getEntities("Customers").execute());
//
//    for (Customer15 customer : consumer.getEntities(Customer15.class, "Customers").execute())
//      report(customer.toString());

    // stop the server
    //server.stop();
  }

}
