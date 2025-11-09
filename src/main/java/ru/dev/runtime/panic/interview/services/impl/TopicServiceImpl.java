package ru.dev.runtime.panic.interview.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.runtime.panic.interview.domain.entity.Topic;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;
import ru.dev.runtime.panic.interview.mapper.TopicMapper;
import ru.dev.runtime.panic.interview.repositories.TopicRepository;
import ru.dev.runtime.panic.interview.services.TopicEntityService;
import ru.dev.runtime.panic.interview.services.TopicService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicEntityService topicEntityService;
    public final TopicMapper topicMapper;

    @Override
    public TopicDto getTopicById(UUID id){
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Topic not found" + id));
        return topicMapper.toTopicDto(topic);
    }

    @Override
    public TopicDto findTopicByWithQuestions(UUID id){
        Topic topic = topicEntityService.findTopicByWithQuestions(id);
        return topicMapper.toTopicDto(topic);
    }

    @Override
    public Page<TopicDto> getAllTopics(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Topic> topicPage = topicEntityService.getAllTopics(pageable);
        List<TopicDto> topics = topicPage.stream().map(topicMapper::toTopicDto).collect(Collectors.toList());
        return new PageImpl<>(topics, pageable, topicPage.getTotalElements());
    }

    @Override
    @Transactional
    public TopicDto createTopic(CreateTopicDto createTopicDto){
        if (topicRepository.existsByTitle(createTopicDto.getTitle())){
            throw new IllegalArgumentException("Топик с таким названием уже существует");
        }
        Topic topic = topicMapper.toTopic(createTopicDto);
        Topic savedTopic = topicEntityService.createTopic(topic);
        return topicMapper.toTopicDto(savedTopic);
    }

    @Override
    @Transactional
    public TopicDto updateTopic(UUID id, TopicDto topicDto){
        Topic existingTopic = topicEntityService.getTopicById(id);
        if (existingTopic == null){
            throw new EntityNotFoundException("Topic not found" + id);
        }
        existingTopic.setTitle(topicDto.getTitle());
        existingTopic.setDescription(topicDto.getDescription());

        Topic updatedTopic = topicEntityService.updateTopic(id, existingTopic);
        return topicMapper.toTopicDto(updatedTopic);
    }

    @Override
    public void deleteTopic(UUID id){
        topicEntityService.deleteTopic(id);
    }
}
