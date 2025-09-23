package ru.dev.runtime.panic.interview.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;

import java.util.List;
import java.util.UUID;

public interface TopicEntityService {

    Topic getTopicById(UUID id);

    Topic findTopicByWithQuestions(UUID id);

    Page<Topic> getAllTopics(Pageable pageable);

    Topic createTopic(Topic topic);

    Topic updateTopic(UUID id, Topic topic);

    void deleteTopic(UUID id);
}
