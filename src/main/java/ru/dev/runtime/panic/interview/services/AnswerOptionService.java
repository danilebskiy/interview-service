package ru.dev.runtime.panic.interview.services;


import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;

import java.util.List;
import java.util.UUID;

public interface AnswerOptionService {

    AnswerOptionDto createAnswerOption(UUID questionId, CreateAnswerOptionDto createAnswerOptionDto);

    AnswerOptionDto getAnswerOptionById(UUID id);

    List<AnswerOptionDto> getAnswerOptionByQuestionId(UUID questionId);

    AnswerOptionDto updateAnswerOption(UUID id,UUID questionId, AnswerOptionDto answerOptionDto);

    void deleteAnswerOptionById(UUID id);
}
