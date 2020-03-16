package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.QuestionRepository;
import org.basecampcodingacademy.reflections.domain.Question;
import org.basecampcodingacademy.reflections.domain.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reflections/{reflectionId}/questions")
public class QuestionController {
    @Autowired
    public QuestionRepository questions;

    @GetMapping
    public List<Question> index() {
        return questions.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody Question question) {
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

    @PatchMapping("/{id}")
    public Question update(@PathVariable Integer id, @RequestBody Question question) {
        question.id = id;
        return questions.update(question);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        questions.delete(id);
    }
}