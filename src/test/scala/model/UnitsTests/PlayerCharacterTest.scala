package cl.uchile.dcc.citric
package model

import cl.uchile.dcc.citric.model.Unit.{Chicken, PlayerCharacter, WildUnit}

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name = "testPlayer"
  private val maxHp = 10
  private val attack = 1
  private val defense = 1
  private val evasion = 1
  private var randomNumberGenerator: Random = _
  private val testWildUnit: WildUnit = new Chicken

  private var character: PlayerCharacter = _
  private var character2: PlayerCharacter = _


  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    character = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator
    )
    character2 = new PlayerCharacter(
      name,
      maxHp,
      attack,
      defense,
      evasion,
      randomNumberGenerator
    )
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.attack, attack)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
  }

  // Testing invariant property. The result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
    }
  }

  test("A character should be able to perform a NormaClear") {
    character.NormaClear()
    assert(character.Norma >= 2)
  }

  test("A character should be able to gain stars on it's turn") {
    val currentStars = character.stars
    character.onTurnStars()
    assert(character.stars >= currentStars)
  }

  test("A character should be able to gain victories") {
    val currentVictories = character.victories
    character.victories += 1
    assert(character.victories > currentVictories)
  }

  test("A character should be on KO when losing all it's Hp") {
    assert(!character.KO())
    character.currentHp -= maxHp
    assert(character.KO())
  }

  test("A character should be able to enter recovery") {
    character.recovery()
    character.currentHp -= maxHp
    character.recovery()
  }

  test("A character should be able to enter combat with another player or with a Wild Unit") {
    character.fight(character, testWildUnit)
    character.fight(character, character2)
  }
}