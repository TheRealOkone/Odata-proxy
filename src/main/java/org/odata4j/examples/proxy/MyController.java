package org.odata4j.examples.proxy;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyController {


    @RequestMapping(value = "/RoundtripExample.svc/{s}",
            method = RequestMethod.GET/*,
            produces = MediaType.APPLICATION_ATOM_XML_VALUE*/
    )
    public ResponseEntity<String> startPage(@PathVariable String s) {
        return Context.foo("http://localhost:8885/RoundtripExample.svc/"+s);
    }

    @RequestMapping(value = "/RoundtripExample.svc/",
            method = RequestMethod.GET/*,
            produces = MediaType.APPLICATION_ATOM_XML_VALUE*/)
    public ResponseEntity<String> sPage() {
        return Context.foo("http://localhost:8885/RoundtripExample.svc/");
    }


}