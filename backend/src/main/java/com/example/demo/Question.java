package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Question {

    @Id
    private Integer id;
    private String description;
    private LinkedHashMap<String, Boolean> answers;

    public Question(){
        this.id = 0;
        this.description = "";
        this.answers = new LinkedHashMap<String, Boolean>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setAnswers(String ansDescription , Boolean answer) {
        this.answers.put(ansDescription, answer);
    }

    public Map<String, Boolean> getAnswers() {
        return answers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                "description='" + description + '\'' +
                ", answers=" + answers +
                '}';
    }
}
