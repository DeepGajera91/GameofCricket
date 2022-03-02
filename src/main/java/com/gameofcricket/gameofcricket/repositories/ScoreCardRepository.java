package com.gameofcricket.gameofcricket.repositories;

import com.gameofcricket.gameofcricket.model.MatchState;
import com.gameofcricket.gameofcricket.model.ScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreCardRepository extends MongoRepository<ScoreCard, String> {

  List<ScoreCard> findAll();

  Optional<ScoreCard> findById(String id);

  ScoreCard save(ScoreCard scoreCard);

  @Query(fields = "{'playerStats':1}")
  List<?> findPlayerStatsById(String id);
  // value = "{'matchStatus' : ?0}",
  //   fields =
  //
  // "{'numberOfOvers':1,'batFirstName':1,'batSecondName':1,'firstBatTotalScore':1,'firstBatTotalBall':1,'firstBatTotalWicket':1, 'secondBatTotalScore':1, 'secondBatTotalBall':1, 'secondBatTotalWicket':1}"
  @Query(
      value = "{'matchStatus':'LIVE'}",
      fields =
          "{'numberOfOvers':1,'batFirstName':1,'batSecondName':1,'firstBatTotalScore':1,'firstBatTotalBall':1,'firstBatTotalWicket':1, 'secondBatTotalScore':1, 'secondBatTotalBall':1, 'secondBatTotalWicket':1}")
  List<?> getAllLiveMatches();
}
