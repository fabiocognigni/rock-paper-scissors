package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.Item

/**
 * Trait that can be re-used for extensions like Rock Paper Scissors Lizard Spock
 */
trait RockPaperScissors  extends Game {

  case object Rock extends Item("rock")
  case object Paper extends Item("paper")
  case object Scissors extends Item("scissors")

  override def winRules: Map[(Item, Item), String] = Map(
    (Paper, Rock) -> "covers",
    (Rock, Scissors) -> "crushes",
    (Scissors, Paper) -> "cuts"
  )
}

/**
 * Define the object to be able to have a concrete (singleton) instance of the game.
 */
object RockPaperScissors extends RockPaperScissors {
  override def name = "Rock Paper Scissors"
}