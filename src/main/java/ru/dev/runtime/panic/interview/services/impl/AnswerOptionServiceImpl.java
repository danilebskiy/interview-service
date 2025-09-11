package ru.dev.runtime.panic.interview.services.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.domain.entity.Question;
import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;
import ru.dev.runtime.panic.interview.exception.ResourseNotFoundException;
import ru.dev.runtime.panic.interview.mapper.AnswerOptionMapper;
import ru.dev.runtime.panic.interview.repositories.AnswerOptionRepository;
import ru.dev.runtime.panic.interview.repositories.QuestionRepository;
import ru.dev.runtime.panic.interview.services.AnswerOptionEntityService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerOptionServiceImpl implements AnswerOptionEntityService {

    private final AnswerOptionMapper answerOptionMapper;
    private final AnswerOptionRepository answerOptionRepository;
    private final QuestionRepository questionRepository;


    @Override
    public List<AnswerOptionDto> getAnswerOptionByQuestionId(UUID questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourseNotFoundException("Question not found with id: " + questionId));
        List<AnswerOption> answerOptions = answerOptionRepository.findByQuestion(question);
        return answerOptions.stream()
                .map(answerOptionMapper::answerOptionToAnswerOptionDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerOptionDto getAnswerOptionById(UUID id) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("AnswerOption not found with id: " + id));
        return answerOptionMapper.answerOptionToAnswerOptionDto(answerOption);
    }

    @Override
    public AnswerOptionDto createAnswerOption(CreateAnswerOptionDto createAnswerOptionDto) {
        Question question = questionRepository.findById(createAnswerOptionDto.getQuestionId()).orElseThrow(() -> new ResourseNotFoundException("Question not found with id: " + createAnswerOptionDto.getQuestionId()));
        AnswerOption answerOption = answerOptionMapper.createAnswerOptionDtoToAnswerOption(createAnswerOptionDto);
        answerOption.setQuestion(question);
        AnswerOption savedAnswerOption = answerOptionRepository.save(answerOption);
        return answerOptionMapper.answerOptionToAnswerOptionDto(savedAnswerOption);
    }

    @Override
    public AnswerOptionDto updateAnswerOption(UUID id, AnswerOptionDto answerOptionDto) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("AnswerOption not found with id: " + id));
        answerOptionMapper.answerOptionToAnswerOptionDto(answerOption);
        AnswerOption updatedAnswerOption = answerOptionRepository.save(answerOption);
        return answerOptionMapper.answerOptionToAnswerOptionDto(updatedAnswerOption);
    }

    @Override
    public void deleteAnswerOptionById(UUID id) {
        AnswerOption answerOption = answerOptionRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("AnswerOption not found with id: " + id));
        answerOptionRepository.delete(answerOption);
    }
}
