package ru.dev.runtime.panic.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dev.runtime.panic.interview.domain.entity.Difficulty;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {

    private UUID id;

    private String text;

    private Difficulty difficulty;

    private List<AnswerOptionDto> options;

    private UUID topicId;
}
