package ru.dev.runtime.panic.interview.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;
import ru.dev.runtime.panic.interview.mapper.AnswerOptionMapper;
import ru.dev.runtime.panic.interview.repositories.QuestionRepository;
import ru.dev.runtime.panic.interview.services.AnswerOptionEntityService;
import ru.dev.runtime.panic.interview.services.AnswerOptionService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnswerOptionServiceImpl implements AnswerOptionService {

    private final AnswerOptionEntityService answerOptionEntityService;
    private final AnswerOptionMapper answerOptionMapper;
    private final QuestionRepository questionRepository;


    @Override
    public List<AnswerOptionDto> getAnswerOptionByQuestionId(UUID questionId) {
        List<AnswerOption> entities = answerOptionEntityService.getAnswerOptionByQuestionId(questionId);
        return answerOptionMapper.listAnswerOptionToAnswerOptionDto(entities);
    }

    @Override
    public AnswerOptionDto getAnswerOptionById(UUID id) {
        AnswerOption entity = answerOptionEntityService.getAnswerOptionById(id);
        return answerOptionMapper.answerOptionToAnswerOptionDto(entity);
    }

    @Override
    public AnswerOptionDto createAnswerOption(UUID questionId, CreateAnswerOptionDto createAnswerOptionDto) {
        AnswerOption entity = answerOptionMapper.createAnswerOptionDtoToAnswerOption(createAnswerOptionDto);
        entity.setQuestion(questionRepository.findById(questionId).orElse(null));
        AnswerOption createdEntity = answerOptionEntityService.createAnswerOption(entity);
        return answerOptionMapper.answerOptionToAnswerOptionDto(createdEntity);
    }

    @Override
    public AnswerOptionDto updateAnswerOption(UUID id, UUID questionId, AnswerOptionDto answerOptionDto) {
        AnswerOption existingEntity = answerOptionEntityService.getAnswerOptionById(id);
        if (existingEntity != null) {
            existingEntity.setTest(answerOptionDto.getTest());
            AnswerOption updatedEntity = answerOptionEntityService.updateAnswerOption(existingEntity);
            return answerOptionMapper.answerOptionToAnswerOptionDto(updatedEntity);
        }
        return null;
    }

    @Override
    public void deleteAnswerOptionById(UUID id) {
        answerOptionEntityService.deleteAnswerOptionById(id);
    }
}
