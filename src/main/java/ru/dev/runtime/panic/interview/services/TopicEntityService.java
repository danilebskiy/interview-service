package ru.dev.runtime.panic.interview.services;

import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;

import java.util.List;
import java.util.UUID;

public interface TopicEntityService {

    TopicDto getTopicById(UUID id);

    TopicDto findTopicByWithQuestions(UUID id);

    List<TopicDto> getAllTopics();

    TopicDto createTopic(CreateTopicDto createTopicDto);

    TopicDto updateTopic(UUID id, TopicDto topicDto);

    void deleteTopic(UUID id);
}
