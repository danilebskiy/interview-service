package ru.dev.runtime.panic.interview.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.dev.runtime.panic.interview.domain.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name="question")
public class Question extends BaseEntity {


    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerOption> options;

}
