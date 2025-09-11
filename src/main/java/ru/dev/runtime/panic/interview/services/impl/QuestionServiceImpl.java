package ru.dev.runtime.panic.interview.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.domain.entity.Question;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;
import ru.dev.runtime.panic.interview.exception.ResourseNotFoundException;
import ru.dev.runtime.panic.interview.mapper.AnswerOptionMapper;
import ru.dev.runtime.panic.interview.mapper.QuestionMapper;
import ru.dev.runtime.panic.interview.repositories.QuestionRepository;
import ru.dev.runtime.panic.interview.repositories.TopicRepository;
import ru.dev.runtime.panic.interview.services.QuestionEntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionEntityService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final TopicRepository topicRepository;
    private final AnswerOptionMapper answerOptionMapper;

    @Override
    public List<QuestionDto> getAllQuestions(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Question> questionPage = questionRepository.findAll(pageable);
        List<Question> questions = questionPage.getContent();
        return questions.stream()
                .map(questionMapper::toQuestionDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(UUID id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("Question with id " + id + " not found"));
        return questionMapper.toQuestionDto(question);
    }

    @Override
    @Transactional
    public QuestionDto createQuestion(CreateQuestionDto createQuestionDto) {
        Topic topic = topicRepository.findById(createQuestionDto.getTopicId())
                .orElseThrow(() -> new ResourseNotFoundException("Topic not found with id " + createQuestionDto.getTopicId()));
        Question question = questionMapper.toQuestion(createQuestionDto);
        question.setTopic(topic);
        List<AnswerOption> answerOptions = new ArrayList<>();
        if (createQuestionDto.getOptions() != null && !createQuestionDto.getOptions().isEmpty()) {
            for (CreateAnswerOptionDto optionDto : createQuestionDto.getOptions()) {
                AnswerOption answerOption = answerOptionMapper.createAnswerOptionDtoToAnswerOption(optionDto);
                answerOption.setQuestion(question);
                answerOptions.add(answerOption);
            }
        }
        question.setOptions(answerOptions);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(savedQuestion);
    }

    @Override
    public QuestionDto updateQuestion(UUID id, QuestionDto questionDto) {
        Topic topic = topicRepository.findById(questionDto.getTopicId())
                .orElseThrow(() -> new ResourseNotFoundException("Topic not found with id " + questionDto.getTopicId()));
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Question not found with id " + id));
        questionMapper.toQuestionDto(question);
        question.setTopic(topic);
        Question updateQuestion = questionRepository.save(question);
        return questionMapper.toQuestionDto(updateQuestion);
    }

    @Override
    public void deleteQuestionById(UUID id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Question not found with id " + id + " "));
        questionRepository.delete(question);
    }
}


