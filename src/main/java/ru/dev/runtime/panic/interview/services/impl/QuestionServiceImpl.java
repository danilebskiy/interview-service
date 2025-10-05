package ru.dev.runtime.panic.interview.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.domain.entity.Question;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;
import ru.dev.runtime.panic.interview.exception.ResourseNotFoundException;
import ru.dev.runtime.panic.interview.mapper.AnswerOptionMapper;
import ru.dev.runtime.panic.interview.mapper.QuestionMapper;
import ru.dev.runtime.panic.interview.repositories.QuestionRepository;
import ru.dev.runtime.panic.interview.repositories.TopicRepository;
import ru.dev.runtime.panic.interview.services.QuestionEntityService;
import ru.dev.runtime.panic.interview.services.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final TopicRepository topicRepository;
    private final AnswerOptionMapper answerOptionMapper;
    private final QuestionEntityService questionEntityService;

    @Override
    public Page<QuestionDto> getAllQuestions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Question> questionPage = questionEntityService.getAllQuestions(pageable);
        List<QuestionDto> questions = questionPage.stream().map(questionMapper::toQuestionDto).collect(Collectors.toList());
        return new PageImpl<>(questions, pageable, questionPage.getTotalElements());
    }

    @Override
    public QuestionDto getQuestionById(UUID id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Question with id " + id + " not found"));
        return questionMapper.toQuestionDto(question);
    }

    @Override
    @Transactional
    public QuestionDto createQuestion(UUID topicId, CreateQuestionDto createQuestionDto) {
        Question question = questionMapper.toQuestion(createQuestionDto);
        question.setTopic(topicRepository.findById(topicId).orElse(null));
        List<AnswerOption> answerOptions = new ArrayList<>();
        if (createQuestionDto.getOptions() != null && !createQuestionDto.getOptions().isEmpty()) {
            for (CreateAnswerOptionDto createAnswerOptionDto : createQuestionDto.getOptions()) {
                AnswerOption answerOption = answerOptionMapper.createAnswerOptionDtoToAnswerOption(createAnswerOptionDto);
                answerOption.setQuestion(question);
                answerOptions.add(answerOption);
            }
        }
        question.setOptions(answerOptions);
        Question saved = questionEntityService.createQuestion(question);
        return questionMapper.toQuestionDto(saved);
    }

    @Override
    public QuestionDto updateQuestion(UUID id, UUID topicId, QuestionDto questionDto) {
        Question existing = questionEntityService.getQuestionById(id);
        if (existing == null) {
            throw new ResourseNotFoundException("Question not found" + id);
        }
        existing.setText(questionDto.getText());
        existing.setDifficulty(questionDto.getDifficulty());
        if (topicId != null) {
            Topic newTopic = topicRepository.findById(topicId).orElse(null);
            existing.setTopic(newTopic);
        } else {
            existing.setTopic(null);
        }
        Question saved = questionEntityService.updateQuestion(existing);
        return questionMapper.toQuestionDto(saved);

    }

    @Override
    public void deleteQuestionById(UUID id) {
        questionEntityService.deleteQuestionById(id);
    }
}


