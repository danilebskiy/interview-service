package ru.dev.runtime.panic.interview.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.dev.runtime.panic.interview.domain.BaseEntity;

import java.util.List;
@EqualsAndHashCode(callSuper=true)
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "topics")
public class Topic extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Question> questions;

}
