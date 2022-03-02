package com.gameofcricket.gameofcricket.controllers;

import com.gameofcricket.gameofcricket.controllers.payload.RequestBodyContent;
import com.gameofcricket.gameofcricket.model.*;
import com.gameofcricket.gameofcricket.services.MatchService;
import com.gameofcricket.gameofcricket.services.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/scorecard")
public class ScoreCardController {

  @Autowired private ScoreCardService scoreCardService;

  @GetMapping("/")
  public List<ScoreCard> getScoreCards() {
    return this.scoreCardService.findAll();
  }

  @GetMapping("/stats/{id}")
  public List<?> getStats(@PathVariable String id) {
    return this.scoreCardService.findPlayerStatsById(id);
  }

  @GetMapping("/live")
  public List<?> getLiveMatches(){
    return this.scoreCardService.getAllLiveMatches();
  }

  @GetMapping("/{id}")
  public ScoreCard getScoreCardById(@PathVariable String id) {
    Optional<ScoreCard> sc = this.scoreCardService.findById(id);
    return sc.orElse(null);
  }

  @PostMapping("/")
  public ScoreCard startNewMatch(@RequestBody RequestBodyContent PostRequestBodyContent)
      throws InterruptedException {
    MatchService cricketMatchService =
        new MatchService(
            new Team(PostRequestBodyContent.getTeam1name()),
            new Team(PostRequestBodyContent.getTeam2name()),
            PostRequestBodyContent.getNumberOfOvers(),
            scoreCardService);
    cricketMatchService.toss();
    cricketMatchService.start();
    cricketMatchService.result();
    return scoreCardService.save(cricketMatchService.getScoreCard());
  }
}
