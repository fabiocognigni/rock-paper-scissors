package com.fabiocognigni.rock_paper_scissors

import com.fabiocognigni.rock_paper_scissors.game.Game.{PlayerItem, Result, Tie, Win}
import com.fabiocognigni.rock_paper_scissors.game.{RockPaperScissorsLizardSpock, Game, RockPaperScissors}

import scala.util.{Failure, Random, Success, Try}

object PlayGame {

  val games: Map[String, Game] =
    Map(  "classic" -> RockPaperScissors,
          "extended" -> RockPaperScissorsLizardSpock)

  def main (args: Array[String]) {
    val gameSelector = Option(System.getProperty("game")) //from -Dgame JVM param
    val game = selectGameType(gameSelector)

    args.length match {
      case 0 =>
        computerVsComputer(game)
      case 1 =>
        playerVsComputer(game, args(0))
      case _ =>
        invalidUsage
    }
  }

  def selectGameType(gameArgument: Option[String]): Game = {
    if(gameArgument.isDefined) {
      games.getOrElse(gameArgument.get,
              {
                println(s"Invalid game type entered (values allowed are ${games.keys}}): playing classic Rock Paper Scissors ...")
                RockPaperScissors})
    } else {
      //no -Dgame argument defaults silently to RockPaperScissors
      RockPaperScissors
    }
  }

  def computerVsComputer(game: Game) = {
    println("Computer VS Computer")

    val computer1 = randomItem(game)
    println(s"Computer 1: ${computer1.name}")
    val computer2 = randomItem(game)
    println(s"Computer 2: ${computer2.name}")

    play(game, computer1, computer2)
  }

  def playerVsComputer(game: Game, userItemName: String) = {
    println("You VS Computer")

    game.nameToItem(userItemName) match {
      case Some(userItem) =>
        println(s"You: ${userItem.name}")
        val computerItem = randomItem(game)
        println(s"Computer: ${computerItem.name}")

        play(game, userItem, computerItem)
      case None =>
        val validItemNames = game.allItems map(_.name)
        println(s"$userItemName is not a valid item. Valid items: $validItemNames")
    }

  }

  def invalidUsage = {
    println(
      s"""
        |Invalid command!
        |Please use:
        |- no arguments for a ComputerVSComputer game;
        |- one argument (your item choice) to play a game against the Computer.
      """.stripMargin)
  }

  def play(game: Game, item1: PlayerItem, item2: PlayerItem) = {
    Try(game.play(item1, item2)) match {
      case Success(result) =>
        handleResult(result)
      case Failure(exception) =>
        println(s"Internal application error: ${exception.getMessage}")
    }
  }

  def handleResult(result: Result) = {
    result match {
      case Tie =>
        println("Tie!")
      case Win(winner, beatAction, loser) =>
        println(s"$winner won!")
        println(s"$winner $beatAction $loser")
    }
  }

  def randomItem(game: Game): PlayerItem = {
    val allItems = game.allItems
    allItems.toVector(Random.nextInt(allItems.size))
  }
}
