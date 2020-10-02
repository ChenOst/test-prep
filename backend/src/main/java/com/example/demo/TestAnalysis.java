package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class TestAnalysis {

    private int testScore = 0;
    private String[] answers;

    public TestAnalysis(JsonNode jsonNode,List<Question> allQuestions){
        int numberOfRightAnswers =0;
        int index = 0;
        answers = new String[jsonNode.size()];

        for(JsonNode node: jsonNode){
            // Get the user answer and the question id - the DS starts with 0 while the question id with 1
            int idInDB = node.get("idInDB").asInt() - 1;
            String userAnswer = node.get("userAnswer").asText();

            // If user answer is true count
            if(allQuestions.get(idInDB).getAnswers().get(userAnswer)){
                numberOfRightAnswers++;
            }

            for(Map.Entry<String, Boolean> ans: allQuestions.get(idInDB).getAnswers().entrySet()){
                // Get true answer to all questions
                if(ans.getValue()){
                    answers[index++]= ans.getKey();
                }
            }
        }
        // Calculate test score
        testScore = calculateScore(jsonNode.size(), numberOfRightAnswers);
        System.out.println(testScore);
    }

    public int calculateScore(int numberOfQuestions, int numberOfRightAnswers){
        double scoreForQuestion = (double)100/numberOfQuestions;
        int score = (int)Math.ceil(scoreForQuestion * numberOfRightAnswers);
        return score;
    }

    @Override
    public String toString() {
        return "TestAnalysis{" +
                "testScore=" + testScore +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
    public String[] getAnswers() {
        return answers;
    }
    public void setTestScore(int testScore) {
        this.testScore = testScore;
    }
    public int getTestScore() {
        return testScore;
    }
}
