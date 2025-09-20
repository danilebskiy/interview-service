package ru.dev.runtime.panic.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dev.runtime.panic.interview.domain.entity.Question;
import ru.dev.runtime.panic.interview.dto.CreateQuestionDto;
import ru.dev.runtime.panic.interview.dto.QuestionDto;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toQuestionDto(Question question);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(source = "topicId", target = "topic.id")
    Question toQuestion(CreateQuestionDto createQuestionDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(source = "topicId", target = "topic.id")
    Question toEntity(QuestionDto questionDto);


}
