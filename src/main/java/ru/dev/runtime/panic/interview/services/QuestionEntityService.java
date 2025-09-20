package ru.dev.runtime.panic.interview.services;

import org.springframework.data.domain.Page;
import ru.dev.runtime.panic.interview.domain.entity.Question;
import ru.dev.runtime.panic.interview.dto.QuestionDto;

import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface QuestionEntityService {

    Question createQuestion(Question question);

    Question getQuestionById(UUID id);

    Page<Question> getAllQuestions(Pageable pageable);

    Question updateQuestion(Question question);

    void deleteQuestionById(UUID id);
}
