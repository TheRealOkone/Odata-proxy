package org.odata4j.examples.proxy;

import org.odata4j.examples.odatamain.RoundtripExample;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Serv  {



    public Serv(@Value("${spring.datasource.password}") String password,
                @Value("${spring.datasource.username}") String username,
                @Value("${spring.datasource.url}") String url) {

        RoundtripExample example = new RoundtripExample(password,username,url);
        example.run();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
