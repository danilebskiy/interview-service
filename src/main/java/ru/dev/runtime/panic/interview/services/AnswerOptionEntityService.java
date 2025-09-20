package ru.dev.runtime.panic.interview.services;

import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;

import java.util.List;
import java.util.UUID;

public interface AnswerOptionEntityService {

    AnswerOption createAnswerOption(AnswerOption answerOption);

    AnswerOption getAnswerOptionById(UUID Id);

    List<AnswerOption> getAnswerOptionByQuestionId(UUID questionId);

    AnswerOption updateAnswerOption(AnswerOption answerOption);

    void deleteAnswerOptionById(UUID Id);
}
