package ru.dev.runtime.panic.interview.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;
import ru.dev.runtime.panic.interview.mapper.TopicMapper;
import ru.dev.runtime.panic.interview.repositories.TopicRepository;
import ru.dev.runtime.panic.interview.services.TopicEntityService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicEntityService {

    private final TopicRepository topicRepository;

    public final TopicMapper topicMapper;

    @Override
    public TopicDto getTopicById(UUID id){
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Topic not found" + id));
        return topicMapper.toTopicDto(topic);
    }

    @Override
    public TopicDto findTopicByWithQuestions(UUID id){
        Topic topic = topicRepository.findByWithQuestions(id)
                .orElseThrow(() ->new EntityNotFoundException("Topic not found" + id));
        return topicMapper.toTopicDto(topic);
    }

    @Override
    public List<TopicDto> getAllTopics(){
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::toTopicDto)
                .collect(Collectors.toList());
    }

    @Override
    public TopicDto createTopic(CreateTopicDto createTopicDto){
        Topic topic = topicMapper.toTopic(createTopicDto);
        Topic savedTopic = topicRepository.save(topic);
        return topicMapper.toTopicDto(savedTopic);
    }

    @Override
    public TopicDto updateTopic(UUID id, TopicDto topicDto){
        Topic existingTopic = topicRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Topic not found" + id));
        existingTopic.setTitle(topicDto.getTitle());
        existingTopic.setDescription(topicDto.getDescription());

        Topic updatedTopic = topicRepository.save(existingTopic);
        return topicMapper.toTopicDto(updatedTopic);
    }

    @Override
    public void deleteTopic(UUID id){
        topicRepository.deleteById(id);
    }
}
