package com.gameofcricket.gameofcricket.services;

import com.gameofcricket.gameofcricket.model.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface ScoreCardService {
  List<ScoreCard> findAll();

  Optional<ScoreCard> findById(String id);

  ScoreCard save(ScoreCard scoreCard);

  List<?> findPlayerStatsById(String id);
}
