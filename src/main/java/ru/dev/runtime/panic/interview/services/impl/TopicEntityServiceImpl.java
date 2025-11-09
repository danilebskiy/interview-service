package ru.dev.runtime.panic.interview.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.repositories.TopicRepository;
import ru.dev.runtime.panic.interview.services.TopicEntityService;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TopicEntityServiceImpl implements TopicEntityService {

    private final TopicRepository topicRepository;


    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Page<Topic> getAllTopics(Pageable pageable){
        return topicRepository.findAll(pageable);
    }

    @Override
    public Topic getTopicById(UUID id){
        return topicRepository.findById(id).get();
    }

    @Override
    public Topic findTopicByWithQuestions(UUID id){
        return topicRepository.findById(id).get();
    }

    @Override
    public Topic updateTopic(UUID id, Topic topic){
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(UUID id){
        topicRepository.deleteById(id);
    }

}
