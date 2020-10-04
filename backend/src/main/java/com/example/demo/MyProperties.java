package com.example.demo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "my-properties")
@Configuration("myProperties")
public class MyProperties {

    private String path;
    private int numberOfQuestions;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    @Override
    public String toString() {
        return "MyProperties{" +
                "path='" + path + '\'' +
                ", numberOfQuestions=" + numberOfQuestions +
                '}';
    }
}
