package org.odata4j.examples.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Autowired
    Context c;


    @RequestMapping(value = "/RoundtripExample.svc/{s}",
            method = RequestMethod.GET/*,
            produces = MediaType.APPLICATION_ATOM_XML_VALUE*/
    )
    public ResponseEntity<String> startPage(@PathVariable String s) {
        return c.foo("http://localhost:8885/RoundtripExample.svc/"+s);
    }

    @RequestMapping(value = "/RoundtripExample.svc/",
            method = RequestMethod.GET/*,
            produces = MediaType.APPLICATION_ATOM_XML_VALUE*/)
    public ResponseEntity<String> sPage() {
        return c.foo("http://localhost:8885/RoundtripExample.svc/");
    }


}