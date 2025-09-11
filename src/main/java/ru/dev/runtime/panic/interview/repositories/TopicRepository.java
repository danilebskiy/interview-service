package ru.dev.runtime.panic.interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dev.runtime.panic.interview.domain.entity.Topic;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {

    @Query("SELECT t FROM Topic t LEFT JOIN FETCH t.questions WHERE t.id = :id")
    Optional<Topic> findByWithQuestions(UUID id);
}
