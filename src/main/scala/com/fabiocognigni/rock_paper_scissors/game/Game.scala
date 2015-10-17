package com.fabiocognigni.rock_paper_scissors.game

import com.fabiocognigni.rock_paper_scissors.game.Game.{Win, Tie, Result, PlayerItem}

import scala.util.Random


trait Game {
  
  /**
   * Override it by defining the winning rules for your specific game.
   *
   * Note 1: it has been defined as a method instead of as a val to make it easier inheriting it and possibly
   * referring to it with super when overriding it (not allowed by Scala with val).
   * Note 2: for very large sets of rules a map (winner, loser) might give better lookup performances but for the size
   * of this kind of application choosing a set has roughly the same performances and it makes the overall design cleaner,
   * less redundant and the code simpler.
   */
  def winRules: Set[Win]

  /**
   * Determines all the distinct items according to the rules of the game
   * @return all the distinct items according to the rules of the game
   */
  def allItems: Set[PlayerItem] = {
    val allWinners: Set[PlayerItem] = winRules map (_.winner)
    /**
     * Note: a well defined game should have each item being the winner at least in one case.
     * I'm checking also the losers side to still return all different items in case the game is not well defined (the user will
     * get an error according to the play method design when trying to play with a non defined pair of items).
     */
    val allLosers: Set[PlayerItem] = winRules map (_.loser)

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
  def nameToItem(itemName: String): Option[PlayerItem] = {
    val gameItems: Set[PlayerItem] = allItems

    gameItems.filter(_.name == itemName).headOption
  }

  /**
   * Determines who the winner is.
   * @param item1 item chosen by player 1
   * @param item2 item chosen by player 2
   * @return an object Result containing the winner, the loser and the beat action or an object Tie when players choose same items.
   * @throws IllegalStateException when no matching rules are found to determine the winner (the rules defined are not complete).
   */
  def play(item1: PlayerItem, item2: PlayerItem): Result = {
    if(item1 == item2)
      Tie
    else {
      winRules.filter( winRule => (winRule.winner == item1 && winRule.loser == item2)).headOption getOrElse
        winRules.filter( winRule => (winRule.winner == item2 && winRule.loser == item1)).headOption.getOrElse(
          throw new IllegalStateException(s"No matching rules for the players' items: player1=$item1; player2=$item2; rules=$winRules")
        )
    }
  }

  /**
   * Generates a random item among the items of the games.
   * @return one random item among the items of the games.
   */
  def randomItem: PlayerItem = allItems.toVector(Random.nextInt(allItems.size))
}

object Game {

  abstract class PlayerItem(val name: String)

  /**
   * Common abstraction to express the 2 possible outcomes with their types.
   */
  sealed trait Result

  case object Tie extends Result

  case class Win(winner: PlayerItem, beatAction: String, loser: PlayerItem) extends Result

}
