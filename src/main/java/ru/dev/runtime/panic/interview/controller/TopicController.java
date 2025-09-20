package ru.dev.runtime.panic.interview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dev.runtime.panic.interview.dto.CreateTopicDto;
import ru.dev.runtime.panic.interview.dto.TopicDto;
import ru.dev.runtime.panic.interview.services.TopicService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/{id}")
    public TopicDto getTopicById(@PathVariable UUID id) {
        return topicService.getTopicById(id);
    }

    @GetMapping("/{id}/questions")
    public TopicDto getTopicByQuestionId(@PathVariable UUID id) {
        return topicService.findTopicByWithQuestions(id);
    }

    @GetMapping
    public List<TopicDto> getAllTopics() {
        return topicService.getAllTopics();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicDto createTopic(@RequestBody CreateTopicDto createTopicDto) {
        return topicService.createTopic(createTopicDto);
    }

    @PutMapping("/{id}")
    public TopicDto updateTopic(@PathVariable UUID id, @RequestBody TopicDto topicDto) {
        return topicService.updateTopic(id, topicDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable UUID id) {
        topicService.deleteTopic(id);
    }
}
