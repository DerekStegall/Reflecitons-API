package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.QuestionRepository;
import org.basecampcodingacademy.reflections.db.ReflectionRepository;
import org.basecampcodingacademy.reflections.domain.Reflection;
import org.basecampcodingacademy.reflections.exceptions.ReflectionAlreadyExists;
import org.basecampcodingacademy.reflections.exceptions.UnluckyReflectionDayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/reflections")
public class ReflectionController {
    @Autowired
    public ReflectionRepository reflections;
    @Autowired
    public QuestionRepository questions;

    @GetMapping
    public List<Reflection> index() {
        return reflections.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reflection create(@RequestBody Reflection reflection) throws UnluckyReflectionDayException, ReflectionAlreadyExists {
//        if (reflection.date.getDayOfMonth() == 13){
//            throw new UnluckyReflectionDayException(reflection.date);
//        }
//        return reflections.create(reflection);
        if (Objects.isNull(reflections.find(reflection.date))) {
            return reflections.create(reflection);
        }
        throw new ReflectionAlreadyExists(reflection.date);
    }

    @GetMapping("/today")
    public Reflection today(@RequestParam(defaultValue = "") String include) {
        var reflection =  reflections.find(LocalDate.now());
        if(include.equals("questions")){
            reflection.questions = questions.forReflection(reflection.id);
        }
        return reflection;
    }

    @GetMapping("/{id}")
    public Reflection find(@PathVariable Integer id) {
        var reflection =  reflections.find(id);
        return reflection;
    }

    @PatchMapping("/{id}")
    public Reflection update(@PathVariable Integer id, @RequestBody Reflection reflection) {
        reflection.id = id;
        return reflections.update(reflection);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        reflections.delete(id);
    }

    @ExceptionHandler({ UnluckyReflectionDayException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUnluckyReflectionDayException(UnluckyReflectionDayException ex){
        var errorMap = new HashMap<String, String>();
        errorMap.put("error", ex.date.toString());
        return errorMap;
    }
    @ExceptionHandler({ ReflectionAlreadyExists.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleReflectionAlreadyExists(ReflectionAlreadyExists ex){
        var errorMap = new HashMap<String, String>();
        errorMap.put("error", "Reflection for "+ ex.date.toString() + " already exists");
        return errorMap;
    }
}
