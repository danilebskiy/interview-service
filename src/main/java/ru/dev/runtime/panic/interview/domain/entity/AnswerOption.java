package ru.dev.runtime.panic.interview.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.dev.runtime.panic.interview.domain.BaseEntity;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "answer_option")
@Entity
public class AnswerOption extends BaseEntity {

    @Column(name = "test")
    private String test;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
