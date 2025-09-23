package ru.dev.runtime.panic.interview.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTopicDto {

    @Column(unique = true)
    private String title;

    private String description;
}
