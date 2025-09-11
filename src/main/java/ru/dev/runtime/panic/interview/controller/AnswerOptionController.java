package ru.dev.runtime.panic.interview.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;
import ru.dev.runtime.panic.interview.services.AnswerOptionEntityService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/questions/{questionId}/answer-options")
public class AnswerOptionController {

    private final AnswerOptionEntityService answerOptionEntityService;

    @GetMapping
    public ResponseEntity<List<AnswerOptionDto>> getAnswerOptionByQuestionId(@PathVariable UUID questionId) {
        List<AnswerOptionDto> answerOptions = answerOptionEntityService.getAnswerOptionByQuestionId(questionId);
        return new ResponseEntity<>(answerOptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerOptionDto> getAnswerOptionById(@PathVariable UUID id) {
        AnswerOptionDto answerOptionDto = answerOptionEntityService.getAnswerOptionById(id);
        return new ResponseEntity<>(answerOptionDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnswerOptionDto> createAnswerOption(@PathVariable UUID questionId, @RequestBody CreateAnswerOptionDto createAnswerOptionDto) {
        createAnswerOptionDto.setQuestionId(questionId);
        AnswerOptionDto createDto= answerOptionEntityService.createAnswerOption(createAnswerOptionDto);
        return new ResponseEntity<>(createDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerOptionDto> updateAnswerOption(@PathVariable UUID id, @RequestBody AnswerOptionDto answerOptionDto) {
        AnswerOptionDto updateAnswerOptionDto = answerOptionEntityService.updateAnswerOption(id, answerOptionDto);
        return new ResponseEntity<>(updateAnswerOptionDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswerOptionById(@PathVariable UUID id) {
        answerOptionEntityService.deleteAnswerOptionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

