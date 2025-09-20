package ru.dev.runtime.panic.interview.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.repositories.AnswerOptionRepository;
import ru.dev.runtime.panic.interview.services.AnswerOptionEntityService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerOptionEntityServiceImpl implements AnswerOptionEntityService {

    private final AnswerOptionRepository answerOptionRepository;

    @Override
    public AnswerOption createAnswerOption(AnswerOption answerOption){
        return answerOptionRepository.save(answerOption);
    }

    @Override
    public AnswerOption getAnswerOptionById(UUID id){
        return answerOptionRepository.findById(id).orElse(null);
    }

    @Override
    public List<AnswerOption> getAnswerOptionByQuestionId(UUID questionId){
        return answerOptionRepository.findByQuestionId(questionId);
    }

    @Override
    public AnswerOption updateAnswerOption(AnswerOption answerOption) {
         return answerOptionRepository.save(answerOption);
    }

    @Override
    public void deleteAnswerOptionById(UUID Id) {
        answerOptionRepository.deleteById(Id);
    }
}