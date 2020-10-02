package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @CrossOrigin
    @RequestMapping
    public List<Question> getQuestionList(){
        return service.getQuestionList();
    }

    @CrossOrigin
    @RequestMapping("/random")
    public List<Question> getRandomQuestion(){
        return service.getRandomTest();
    }

    @CrossOrigin
    @RequestMapping(method=RequestMethod.POST, value="/testAnalysis")
    public TestAnalysis processTestAnalysis(@RequestBody JsonNode jsonNode){
        return service.getTestAnalysis(jsonNode);
    }
}
