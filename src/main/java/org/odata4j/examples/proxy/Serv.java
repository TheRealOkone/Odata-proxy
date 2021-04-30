package org.odata4j.examples.proxy;

import org.odata4j.examples.odatamain.RoundtripExample;

import org.odata4j.examples.visual.Viewform;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Serv  {

    public Serv(@Value("${spring.datasource.password}") String password,
                @Value("${spring.datasource.username}") String username,
                @Value("${spring.datasource.url}") String url) {
        int i = 0;
        Viewform form = new Viewform();
        while(form.getQuery() == null){
            System.out.print("");
        }
        if(password == null){
            password = form.getPassword();
        }
        if(username == null){
            username = form.getUser();
        }
        if(url == null){
            url = form.getDatabase();
        }
        RoundtripExample example = new RoundtripExample(password,username,url,form.getQuery(),form.getUser());
        example.run();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
