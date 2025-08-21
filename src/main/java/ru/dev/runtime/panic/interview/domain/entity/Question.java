package ru.dev.runtime.panic.interview.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.dev.runtime.panic.interview.domain.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column(name = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerOption> options;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

}
