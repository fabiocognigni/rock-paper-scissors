package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Win, Item}

trait RockPaperScissors  extends Game {

  override def name = "Rock Paper Scissors"

  case object Rock extends Item("rock")
  case object Paper extends Item("paper")
  case object Scissors extends Item("scissors")

  override def winRules: Map[(Item, Item), String] = Map(
    (Paper, Rock) -> "covers",
    (Rock, Scissors) -> "crushes",
    (Scissors, Paper) -> "cuts"
  )
}

object RockPaperScissors extends RockPaperScissors