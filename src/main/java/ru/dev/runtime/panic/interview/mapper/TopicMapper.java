package ru.dev.runtime.panic.interview.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicDto toTopicDto(Topic topic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    Topic toTopic(CreateTopicDto createTopicDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    Topic toEntity(TopicDto topicDto);

}
