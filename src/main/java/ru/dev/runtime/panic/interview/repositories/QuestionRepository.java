package ru.dev.runtime.panic.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dev.runtime.panic.interview.domain.entity.Question;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    Optional<Question> findById(UUID id);
}
