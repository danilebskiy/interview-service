package ru.dev.runtime.panic.interview.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;
import ru.dev.runtime.panic.interview.services.QuestionEntityService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/questions")
public class QuestionController {


    private final QuestionEntityService questionEntityService;

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<QuestionDto> questions = questionEntityService.getAllQuestions(page, size);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable UUID id) {
        QuestionDto questionDto = questionEntityService.getQuestionById(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody CreateQuestionDto createQuestionDto) {
        QuestionDto createQuestion = questionEntityService.createQuestion(createQuestionDto);
        return new ResponseEntity<>(createQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable UUID id, @RequestBody QuestionDto questionDto) {
        QuestionDto updateQuestion = questionEntityService.updateQuestion(id, questionDto);
        return new ResponseEntity<>(updateQuestion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID id) {
        questionEntityService.deleteQuestionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
