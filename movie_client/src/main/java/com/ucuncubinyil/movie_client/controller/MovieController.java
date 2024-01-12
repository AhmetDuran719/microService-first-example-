package com.ucuncubinyil.movie_client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.ucuncubinyil.movie_client.domain.Movie;

@Controller
public class MovieController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String handleRequest(Model model) {

        List<ServiceInstance> instances = discoveryClient.getInstances("MOVIE-SERVER");
        if (instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            String url = serviceInstance.getUri().toString();
            url = url + "/list";
            //http://localhost:8081/list
            //[{"name":"Kelebek Etkisi","date":"2004","imdbPoint":"7,7"},
            //{"name":"Zamanın Ötesinde","date":"2014","imdbPoint":"7,5"}]
            RestTemplate restTemplate = new RestTemplate();
            List<Movie> result = restTemplate.getForObject(url, List.class);

            model.addAttribute("result", result);
        }

        return "movie";
    }

}
