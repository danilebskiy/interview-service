package ru.dev.runtime.panic.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private UUID id;

    private String title;

    private String description;

    private List<QuestionDto> questions;
}
