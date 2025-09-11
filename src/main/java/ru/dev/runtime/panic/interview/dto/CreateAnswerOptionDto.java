package ru.dev.runtime.panic.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnswerOptionDto {

    private String test;

    private boolean isCorrect;

    private UUID questionId;

}
