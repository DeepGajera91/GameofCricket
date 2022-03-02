package com.gameofcricket.gameofcricket.services;

import com.gameofcricket.gameofcricket.model.MatchState;
import com.gameofcricket.gameofcricket.model.ScoreCard;
import com.gameofcricket.gameofcricket.repositories.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {

  @Autowired private ScoreCardRepository scoreCardRepository;

  @Override
  public List<ScoreCard> findAll() {
    return scoreCardRepository.findAll();
  }

  @Override
  public Optional<ScoreCard> findById(String id) {
    return scoreCardRepository.findById(id);
  }

  @Override
  public ScoreCard save(ScoreCard scoreCard) {
    return scoreCardRepository.save(scoreCard);
  }

  @Override
  public List<?> findPlayerStatsById(String id){
    return scoreCardRepository.findPlayerStatsById(id);
  }

  @Override
  public List<?> getAllLiveMatches(){
    return scoreCardRepository.getAllLiveMatches();
  }
}
