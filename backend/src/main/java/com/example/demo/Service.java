package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private QuestionRepository repository;

    // Get all the questions from the docx file
    public List<Question> getQuestionList(){
        return repository.findAll();
    }

    // Get specific amount of different questions - inorder to create a test
    public List<Question> getRandomTest(){

        Long DBSize = repository.count();
        int questionsInTest = 3;
        List<Question> randomTest = new ArrayList<>();

        if(DBSize < questionsInTest){
            questionsInTest = (int) (DBSize/2);
        }

        while(randomTest.size() < questionsInTest){
            int index = (int)(Math.random() * DBSize);
            Question randomQ = getQuestionList().get(index);
            if(!randomTest.contains(randomQ)){
                randomTest.add(randomQ);
            }
        }
        return randomTest;
    }

    public TestAnalysis getTestAnalysis(JsonNode jsonNode){
        List<Question> allQuestions = getQuestionList();
        return new TestAnalysis(jsonNode, allQuestions);
    }

}
