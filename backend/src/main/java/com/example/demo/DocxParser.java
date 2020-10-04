package com.example.demo;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Component
public class DocxParser {

    /**
     * The function parses docx file that contains multiple choice questions.
     * Every question saved in the DB with primary key id.
     * @return list of all the qeustions from the dock file
     */
    public static List<Question> readDocxFile(String path) {
        // The path of the file - enter here your path
        String myDocxPath = path;
        int id = 0;
        List<Question> questionList = new ArrayList<>();

        try {
            Question question = null;
            LinkedHashMap<String, Boolean> temp = null;
            File file = new File(myDocxPath);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            Random rand = new Random();

            for (XWPFParagraph para : paragraphs) {
                if(para.getNumFmt().equals("decimal") && para.getStyle().equals("ListParagraph")){
                    question = new Question();
                    temp = new LinkedHashMap<String, Boolean>();

                    question.setId(++id);
                    question.setDescription(para.getText());
                    questionList.add(question);
                }
                // create 4 answers for each question
                else if(para.getNumFmt().equals("lowerLetter") && para.getStyle().equals("ListParagraph")){
                    if(temp.isEmpty()){
                        // the first answer will always contain true value
                        temp.put(para.getText(), true);
                    }
                    else{
                        // the other answer are not true and set to false
                        temp.put(para.getText(), false);
                    }
                    // add the answers in random order
                    if(temp.size()==4) {
                        while (question.getAnswers().size() < 4) {
                            int index = rand.nextInt(4);
                            String Key = (String) temp.keySet().toArray()[index];
                            Boolean valueForFirstKey = (Boolean) temp.get(Key);
                            question.setAnswers(Key, valueForFirstKey);
                        }
                    }
                }
            }
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
