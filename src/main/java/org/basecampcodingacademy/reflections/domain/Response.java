package org.basecampcodingacademy.reflections.domain;

public class Response {
    public Integer id;
    public Integer reflectionId; // You will have to change this line eventually
    public String userName;
    public String answers;

    public Response() {}

    public Response(Integer id, Integer reflectionId, String userName, String answers
    ) {
        this.id = id;
        this.reflectionId = reflectionId;
        this.userName = userName;
        this.answers = answers;
    }
}
