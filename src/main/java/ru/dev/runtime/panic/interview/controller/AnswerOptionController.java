package ru.dev.runtime.panic.interview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;
import ru.dev.runtime.panic.interview.services.AnswerOptionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions/{questionId}/answer-options")
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    @GetMapping
    public List<AnswerOptionDto> getAnswerOptionByQuestionId(@PathVariable UUID questionId) {
        return answerOptionService.getAnswerOptionByQuestionId(questionId);
    }

    @GetMapping("/{id}")
    public AnswerOptionDto getAnswerOptionById(@PathVariable UUID id) {
        return answerOptionService.getAnswerOptionById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerOptionDto createAnswerOption(@PathVariable UUID questionId, @RequestBody CreateAnswerOptionDto createAnswerOptionDto) {
        return answerOptionService.createAnswerOption(questionId, createAnswerOptionDto);
    }

    @PutMapping("/{id}")
    public AnswerOptionDto updateAnswerOption(@PathVariable UUID id, @PathVariable UUID questionId, @RequestBody AnswerOptionDto answerOptionDto) {
        return answerOptionService.updateAnswerOption(id, questionId, answerOptionDto);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswerOptionById(@PathVariable UUID id) {
        answerOptionService.deleteAnswerOptionById(id);

    }
}

