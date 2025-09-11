package ru.dev.runtime.panic.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.runtime.panic.interview.domain.entity.AnswerOption;
import ru.dev.runtime.panic.interview.domain.entity.Question;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, UUID> {

    List<AnswerOption> findByQuestion(Question question);

}
