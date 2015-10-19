package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Win, Tie, Result, Item}

import scala.util.Random


trait Game {

  /**
   * The name of the game
   */
  def name: String
  
  /**
   * Override it by defining the winning rules for your specific game.
   * Format: (winnerItem, loserItem) -> beatAction
   *
   * Note 1: it has been defined as a method instead of a val to make it easier inheriting it and possibly
   * referring to it with "super.winRules" when overriding it (not allowed by Scala with val).
   * Note 2: rules could also be expressed as Set[Win] but this wouldn't guarantee the uniqueness of the tuple
   * (winner, loser) by design. In addition a map has better access performance in case of a large set of rules.
   */
  def winRules: Map[(Item, Item), String]

  /**
   * Determines all the distinct items according to the rules of the game
   * @return all the distinct items according to the rules of the game
   */
  def allItems: Set[Item] = {
    val allWinners: Set[Item] = winRules.keys map(_._1) toSet
    /**
     * Note: a well defined game should have each item being the winner at least in one case.
     * I'm checking also the losers side to still return all different items even when the game is not well defined (the user will
     * get an error - according to how the play method has been designed - when trying to play with a non defined pair of items).
     */
    val allLosers: Set[Item] = winRules.keys map(_._2) toSet

    allWinners union allLosers
  }

  /**
   * Retrieves the player item entity given the name of the item.
   * Assumption: a valid item must exist in the rules of the game.
   * Note: defining the conversion name to item in a separate data structure would be less complex from a
   * computational point of view but harder to maintain and prone to inconsistencies (due to redundancy).
   * @param itemName name of the item
   * @return a defined option with the player item object when the name exists, None if the name does not exist in the rules.
   */
  def nameToItem(itemName: String): Option[Item] = {
    val gameItems: Set[Item] = allItems

    gameItems.filter(_.name == itemName).headOption
  }

  /**
   * Determines who the winner item is.
   * @param item1 item chosen by player 1
   * @param item2 item chosen by player 2
   * @return an object Result containing the winner, the loser and the beat action or an object Tie when players choose same items.
   * @throws IllegalStateException when no matching rules are found to determine the winner (the rules defined are not complete).
   */
  def play(item1: Item, item2: Item): Result = {
    if(item1 == item2)
      Tie
    else {
      winRules.get(item1, item2).map(Win(item1, _, item2)).getOrElse(
        winRules.get(item2, item1).map(Win(item2, _, item1)).getOrElse(
          throw new IllegalStateException(s"No matching rules for the players' items: player1=$item1; player2=$item2; rules=$winRules")))
    }
  }

  /**
   * Generates a random item among the items of the games.
   * @return one random item among the items of the games.
   */
  def randomItem: Item = allItems.toVector(Random.nextInt(allItems.size))
}

object Game {

  abstract class Item(val name: String)

  /**
   * Common abstraction to express the 2 possible outcomes with their types.
   */
  sealed trait Result

  case object Tie extends Result

  case class Win(winner: Item, beatAction: String, loser: Item) extends Result

}
