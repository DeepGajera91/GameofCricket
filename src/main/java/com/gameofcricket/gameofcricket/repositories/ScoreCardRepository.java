package com.gameofcricket.gameofcricket.repositories;

import com.gameofcricket.gameofcricket.model.ScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreCardRepository extends MongoRepository<ScoreCard, String> {
  @Override
  List<ScoreCard> findAll();

  Optional<ScoreCard> findById(String id);

  ScoreCard save(ScoreCard scoreCard);
}
