package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.Item

object RockPaperScissorsLizardSpock extends RockPaperScissors {

  override def name = "Rock Paper Scissors Lizard Spock"

  case object Lizard extends Item("lizard")
  case object Spock extends Item("spock")

  override def winRules: Map[(Item, Item), String] = super.winRules ++ Map(
    (Rock, Lizard) -> "crushes",
    (Paper , Spock) -> "disproves",
    (Scissors, Lizard) -> "decapitates",
    (Lizard, Paper) -> "eats",
    (Lizard, Spock) -> "poisons",
    (Spock, Scissors) -> "smashes",
    (Spock, Rock) -> "vaporizes")
}
