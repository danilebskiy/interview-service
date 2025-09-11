package ru.dev.runtime.panic.interview.services;

import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;

import java.util.List;
import java.util.UUID;

public interface QuestionEntityService {

    QuestionDto createQuestion(CreateQuestionDto createQuestionDto);

    QuestionDto getQuestionById(UUID id);

    List<QuestionDto> getAllQuestions(int page, int size);

    QuestionDto updateQuestion(UUID id, QuestionDto questionDto);

    void deleteQuestionById(UUID id);

}
