package org.basecampcodingacademy.reflections.domain;

public class Question {

    public Integer id;
    public String prompt; // You will have to change this line eventually
    public Integer reflectionId;

    public Question() {}

    public Question(Integer id, String prompt, Integer reflectionId) {
        this.id = id;
        this.prompt = prompt;
        this.reflectionId = reflectionId;
    }
}

