package org.odata4j.examples;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

public class Context {

    public static String loadUrl(String u) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line,l = "";

        try {
            url = new URL(u);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                l += line;
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
            }
        }
        return l;
    }



    public static ResponseEntity<String> foo(String url){
        RestTemplate a = new RestTemplate();
        //ResponseEntity<> resp = a.getForObject(url, ResponseEntity.class);
        //ResponseEntity<String> response = a.exchange("http://localhost:8885/RoundtripExample.svc/", HttpMethod.GET, (ResponseEntity<String>)null, String.class, (Object)null);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        //headers.add("Accept","text/html,application/xhtml+xml,application/xml;");
        headers.add("Accept-Encoding", "gzip,deflate");
        headers.add("Host", "localhost:8885");
        headers.add("Connection", "Keep-Alive");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        System.out.println(url);
        ResponseEntity<String> response=null;
        //response= a.exchange(url, HttpMethod.GET, entity, String.class);
        response= new ResponseEntity<String>(loadUrl(url),HttpStatus.OK);

        System.out.println(response);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.addAll(response.getHeaders());
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET");
        responseHeaders.add("Access-Control-Allow-Headers", "Accept, Origin, Content-Type, MaxDataServiceVersion");
        responseHeaders.add("Access-Control-Expose-Headers", "DataServiceVersion");
        responseHeaders.add("Content-type","application/xml");
        //System.out.println("!!!!\n" + response.getBody());
        //return ResponseEntity.ok().headers(responseHeaders).body(response.getBody());
        return new ResponseEntity<String>(response.getBody(), responseHeaders, HttpStatus.OK );
    }

}
