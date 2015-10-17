package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.GameDefinition.PlayerItem

object RockPaperScissorsLizardSpock extends RockPaperScissors {

  override def winningRules: Map[(PlayerItem, PlayerItem), String] =
    super.winningRules ++
    Map(  (Rock, Lizard) -> "crushes",
          (Paper, Spock) -> "disproves",
          (Scissors, Lizard) -> "decapitates",
          (Lizard, Paper) -> "eats",
          (Lizard, Spock) -> "poisons",
          (Spock, Scissors) -> "smashes",
          (Spock, Rock) -> "vaporizes")

  case object Lizard extends PlayerItem("lizard")
  case object Spock extends PlayerItem("spock")
}
