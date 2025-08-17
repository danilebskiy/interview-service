package ru.dev.runtime.panic.interview.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.dev.runtime.panic.interview.domain.BaseEntity;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "answer_option")
@Entity
public class AnswerOption extends BaseEntity {


    @Column(name="test")
    private String test;

    @Column(name="is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
