package ru.dev.runtime.panic.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.dto.AnswerOptionDto;
import ru.dev.runtime.panic.interview.dto.CreateAnswerOptionDto;

@Mapper(componentModel = "spring")
public interface AnswerOptionMapper {

    AnswerOptionDto answerOptionToAnswerOptionDto(AnswerOption answerOption);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "question", ignore = true)
    AnswerOption createAnswerOptionDtoToAnswerOption(CreateAnswerOptionDto createAnswerOptionDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(source = "questionId", target = "question.id")
    AnswerOption answerOptionDtoToAnswerOption(AnswerOptionDto answerOptionDto);

}
