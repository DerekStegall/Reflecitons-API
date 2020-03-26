package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.QuestionRepository;
import org.basecampcodingacademy.reflections.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    public QuestionRepository questions;

    @GetMapping("/reflections/{reflectionId}/questions")
    public List<Question> index() {
        return questions.all();
    }

    @PostMapping("/reflections/{reflectionId}/questions")
    public Question create(@RequestBody Question question, @PathVariable Integer reflectionId) {
        question.reflectionId = reflectionId;
        return questions.create(question);
    }

//    @GetMapping("/today")
//    public Question today() {
//        var question =  questions.find(LocalDate.now());
//        return question;
//    }

    @GetMapping("/{id}")
    public Question find(@PathVariable Integer id) {
        var question =  questions.find(id);
        return question;
    }

    @PatchMapping("/reflections/{reflectionId}/questions/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Question update(@PathVariable Integer reflectionId, @PathVariable Integer id, @RequestBody Question question) {
        question.id = id;
        question.reflectionId = reflectionId;
        return questions.update(question);
    }

    @DeleteMapping("/reflections/{reflectionId}/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        questions.delete(id);
    }
}
