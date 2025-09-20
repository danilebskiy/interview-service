package ru.dev.runtime.panic.interview.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;
import ru.dev.runtime.panic.interview.services.QuestionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public Page<QuestionDto> getAllQuestions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return questionService.getAllQuestions(page, size);
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestionById(@PathVariable UUID id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto createQuestion(@RequestParam UUID topicId, @RequestBody CreateQuestionDto createQuestionDto) {
        return questionService.createQuestion(topicId, createQuestionDto);
    }

    @PutMapping("/{id}")
    public QuestionDto updateQuestion(@PathVariable UUID id,@RequestParam UUID topicId, @RequestBody QuestionDto questionDto) {
        return questionService.updateQuestion(id,topicId, questionDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable UUID id) {
        questionService.deleteQuestionById(id);
    }
}
