package org.odata4j.examples;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
