package ru.dev.runtime.panic.interview.services;

import org.springframework.data.domain.Page;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;

import java.util.List;
import java.util.UUID;

public interface TopicService {

    TopicDto getTopicById(UUID id);

    TopicDto findTopicByWithQuestions(UUID id);

    Page<TopicDto> getAllTopics(int  page, int size);

    TopicDto createTopic(CreateTopicDto createTopicDto);

    TopicDto updateTopic(UUID id, TopicDto topicDto);

    void deleteTopic(UUID id);
}
