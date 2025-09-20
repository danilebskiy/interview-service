package ru.dev.runtime.panic.interview.services.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.stereotype.Service;
import ru.dev.runtime.panic.interview.domain.entity.Question;
import ru.dev.runtime.panic.interview.dto.QuestionDto;
import ru.dev.runtime.panic.interview.repositories.QuestionRepository;
import ru.dev.runtime.panic.interview.services.QuestionEntityService;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionEntityServiceImpl implements QuestionEntityService {

    private final QuestionRepository questionRepository;

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getQuestionById(UUID id){
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Question> getAllQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public void deleteQuestionById(UUID id) {
        questionRepository.deleteById(id);
    }
}
