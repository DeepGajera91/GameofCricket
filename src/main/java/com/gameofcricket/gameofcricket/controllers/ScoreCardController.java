package com.gameofcricket.gameofcricket.controllers;

import com.gameofcricket.gameofcricket.controllers.payload.RequestBodyContent;
import com.gameofcricket.gameofcricket.model.Match;
import com.gameofcricket.gameofcricket.model.ScoreCard;
import com.gameofcricket.gameofcricket.model.Team;
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

  @GetMapping("/{id}")
  public ScoreCard getScoreCardById(@PathVariable String id) {
    Optional<ScoreCard> sc = this.scoreCardService.findById(id);
    return sc.orElse(null);
  }

  @PostMapping("/")
  public ScoreCard startNewMatch(@RequestBody RequestBodyContent obj) {
    Match cricketMatch =
        new Match(
            new Team(obj.getTeam1name()), new Team(obj.getTeam2name()), obj.getNumberOfOvers());
    cricketMatch.toss();
    cricketMatch.start();
    cricketMatch.result();
    return scoreCardService.save(cricketMatch.getScoreCard());
  }
}
