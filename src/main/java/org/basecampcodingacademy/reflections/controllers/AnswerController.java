package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.AnswerRepository;
import org.basecampcodingacademy.reflections.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AnswerController {
        @Autowired
        public AnswerRepository answers;

        @GetMapping
        public List<Answer> index() {
            return answers.all();
        }

        @PostMapping("/responses/{responseId}/answers")
        @ResponseStatus(HttpStatus.CREATED)
        public Answer create(@RequestBody Answer answer, @PathVariable Integer responseId) {
            answer.responseId = responseId;
            return answers.create(answer);
        }
        @PatchMapping("/{id}")
        public Answer update(@PathVariable Integer responseId, @PathVariable Integer id, @RequestBody Answer answer) {
            answer.id = id;
            answer.responseId = responseId;
            return answers.update(answer);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void delete(@PathVariable Integer id) {
            answers.delete(id);
        }
}
