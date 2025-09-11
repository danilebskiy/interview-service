package ru.dev.runtime.panic.interview.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;
import ru.dev.runtime.panic.interview.services.TopicEntityService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/topics")
public class TopicController {

    private final TopicEntityService topicEntityService;

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable UUID id) {
        TopicDto topicDto = topicEntityService.getTopicById(id);
        return ResponseEntity.ok(topicDto);
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<TopicDto>  getTopicByQuestionId(@PathVariable UUID id) {
        TopicDto topicDto = topicEntityService.findTopicByWithQuestions(id);
        return ResponseEntity.ok(topicDto);
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topics = topicEntityService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    @PostMapping
    public ResponseEntity<TopicDto> createTopic(@RequestBody CreateTopicDto createTopicDto) {
        TopicDto createdTopic = topicEntityService.createTopic(createTopicDto);
        return new ResponseEntity<>(createdTopic, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable UUID id, @RequestBody TopicDto topicDto) {
        TopicDto updatedTopic = topicEntityService.updateTopic(id, topicDto);
        return ResponseEntity.ok(updatedTopic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable UUID id) {
        topicEntityService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
