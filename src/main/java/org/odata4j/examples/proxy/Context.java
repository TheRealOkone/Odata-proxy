package org.odata4j.examples.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
//@Service
public class Context {

    private String password;
    private String user;
    private String url;
    private String query;

    public void ini(String password, String user, String url, String query) {
        this.password = password;
        this.user = user;
        this.url = url;
        this.query = query;
    }

    public String loadUrl(String u) {
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



    public ResponseEntity<String> foo(String url){
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
        response= new ResponseEntity<String>(loadUrl(url),HttpStatus.OK);

        JDBCBase base = new JDBCBase(this.url, user, password,query);
        base.createconnection();
        List<String> colname = base.cselect();
        base.close();
        String tst = response.getBody().replace(":8885",":8080");
            for(int i = colname.size() - 1;i >= 0; i--)
                tst = tst.replace("Column" + Integer.toString(i + 1),colname.get(i));
            



        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println(query);
        responseHeaders.addAll(response.getHeaders());
        /*responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "GET");
        responseHeaders.add("Access-Control-Allow-Headers", "Accept, Origin, Content-Type, MaxDataServiceVersion");
        responseHeaders.add("Access-Control-Expose-Headers", "DataServiceVersion");*/
        responseHeaders.add("Content-type","application/xml");
        //System.out.println("!!!!\n" + response.getBody());
        //return ResponseEntity.ok().headers(responseHeaders).body(response.getBody());
        return new ResponseEntity<String>(tst, responseHeaders, HttpStatus.OK );
    }

}
