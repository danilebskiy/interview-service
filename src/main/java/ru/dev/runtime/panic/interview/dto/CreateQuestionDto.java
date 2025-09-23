package ru.dev.runtime.panic.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dev.runtime.panic.interview.domain.entity.Difficulty;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateQuestionDto {

    private String text;

    private Difficulty difficulty;

    private List<CreateAnswerOptionDto> options;

    private UUID topicId;
}
