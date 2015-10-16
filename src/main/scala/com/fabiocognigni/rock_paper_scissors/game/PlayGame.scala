package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.GameDefinition.{PlayerItem, Win, Tie, Result}

import scala.util.{Random, Failure, Success, Try}

object PlayGame {

  def main (args: Array[String]) {
    args.length match {
      case 0 =>
        computerVsComputer
      case 1 =>
        playerVsComputer(args(0))
      case _ =>
        invalidUsage
    }
  }

  def computerVsComputer = {
    println("Computer VS Computer")

    val computer1 = randomItem
    println(s"Computer 1: ${computer1.name}")
    val computer2 = randomItem
    println(s"Computer 2: ${computer2.name}")

    play(computer1, computer2)
  }

  def playerVsComputer(userItemName: String) = {
    println("You VS Computer")

    RockPaperScissors.nameToItem(userItemName) match {
      case Some(userItem) =>
        println(s"You: ${userItem.name}")
        val computerItem = randomItem
        println(s"Computer: ${computerItem.name}")

        play(userItem, computerItem)
      case None =>
        val validItemNames = RockPaperScissors.allItems map(_.name)
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

  def play(item1: PlayerItem, item2: PlayerItem) = {
    Try(RockPaperScissors.play(item1, item2)) match {
      case Success(result) =>
        handleResult(result)
      case Failure(exception) =>
        println(s"Internal application error: $exception.getMessage")
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

  def randomItem: PlayerItem = {
    val allItems = RockPaperScissors.allItems
    allItems.toVector(Random.nextInt(allItems.size))
  }
}
