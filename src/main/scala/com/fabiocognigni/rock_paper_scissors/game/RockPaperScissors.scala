package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.GameDefinition.PlayerItem

trait RockPaperScissors  extends GameDefinition {

  case object Rock extends PlayerItem("rock")
  case object Paper extends PlayerItem("paper")
  case object Scissors extends PlayerItem("scissors")

  override def winningRules: Map[(PlayerItem, PlayerItem), String] =
    Map(  (Paper, Rock) -> "covers",
          (Rock, Scissors) -> "crushes",
          (Scissors, Paper) -> "cuts")
}

object RockPaperScissors extends RockPaperScissors