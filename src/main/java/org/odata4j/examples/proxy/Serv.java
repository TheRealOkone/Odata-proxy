package org.odata4j.examples.proxy;

import org.odata4j.examples.odatamain.prod.Roundtrip15;

import org.odata4j.examples.visual.Viewform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;


@Service
public class Serv  {
    @Autowired
    Context c;
    String password;
    String username;
    String url;

    public Serv(@Value("${spring.datasource.password}") String password,
                @Value("${spring.datasource.username}") String username,
                @Value("${spring.datasource.url}") String url) {
        this.password = password;
        this.username = username;
        this.url = url;

    }
    @PostConstruct
    public void init(){


        Thread t = Thread.currentThread();

        FutureTask<Viewform> task = new FutureTask<Viewform>(new Callable<Viewform>() {
            public Viewform call() {
                Viewform form = new Viewform();
                while(form.getQuery() == null){
                    System.out.print("");
                }
                return form;
            }});
        Thread w = new Thread(task);
        w.start();



        Viewform finalform = null;
        try {
            finalform = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(finalform != null) {

            if (password == null) {
                password = finalform.getPassword();
            }
            if (username == null) {
                username = finalform.getUser();
            }
            if (url == null) {
                url = finalform.getDatabase();
            }
        }
        c.ini(password,username,url,finalform.getQuery());
        Roundtrip15 example = new Roundtrip15(password,username,url,finalform.getQuery(),finalform.getUser());
        example.run();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

