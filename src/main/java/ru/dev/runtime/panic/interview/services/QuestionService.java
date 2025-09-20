package ru.dev.runtime.panic.interview.services;

import org.springframework.data.domain.Page;
import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    QuestionDto createQuestion(UUID topicId, CreateQuestionDto createQuestionDto);

    QuestionDto getQuestionById(UUID id);

    Page<QuestionDto> getAllQuestions(int page, int size);

    QuestionDto updateQuestion(UUID id,UUID topicId, QuestionDto questionDto);

    void deleteQuestionById(UUID id);
}
