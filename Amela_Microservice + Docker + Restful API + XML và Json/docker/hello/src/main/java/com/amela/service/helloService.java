package com.amela.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class helloService {
	public String getName(){
        RestTemplate template = new RestTemplate();
        String nameUrl = "http://name:8081/hello";
        return template.getForObject(nameUrl, String.class);
    }

}
