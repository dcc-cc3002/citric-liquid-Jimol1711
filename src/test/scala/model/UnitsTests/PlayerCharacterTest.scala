package cl.uchile.dcc.citric
package model.UnitsTests

import cl.uchile.dcc.citric.exceptions.InvalidStatException
import cl.uchile.dcc.citric.model.Units.WildUnits.{Chicken, AbstractWildUnit}
import cl.uchile.dcc.citric.model.Units.PlayerCharacter
import cl.uchile.dcc.citric.model.Norma.Norma2

import scala.math.max
import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
  /*
  REMEMBER: It is a good practice to use constants for the values that are used in multiple
  tests, so you can change them in a single place.
  This will make your tests more readable, easier to maintain, and less error-prone.
  */
  private val name = "testPlayer"
  private val maxHp = 10
  private val offense = 1
  private val defense = 1
  private val evasion = 1
  private val offense2 = 2
  private val defense2 = 2
  private val evasion2 = 2
  private var randomNumberGenerator: Random = _
  private val chosenStat1 = "stars"
  private val chosenStat2 = "victories"
  private val testWildUnit: AbstractWildUnit = new Chicken

  private var character: PlayerCharacter = _
  private var character2: PlayerCharacter = _
  private var character3: PlayerCharacter = _
  private var IncorrectCharacter: PlayerCharacter = _


  override def beforeEach(context: BeforeEach): Unit = {
    randomNumberGenerator = new Random(11)
    character = new PlayerCharacter(
      name,
      maxHp,
      offense,
      defense,
      evasion,
      chosenStat1)
    character2 = new PlayerCharacter(
      name,
      maxHp,
      offense2,
      defense2,
      evasion2,
      chosenStat2)
    character3 = new PlayerCharacter(
      name,
      maxHp,
      offense2,
      defense,
      evasion2,
      chosenStat2)
    IncorrectCharacter = new PlayerCharacter(
      name,
      -10,
      offense2,
      defense,
      evasion2,
      chosenStat2)
  }

  test("A character should have correctly set their attributes") {
    assertEquals(character.name, name)
    assertEquals(character.maxHp, maxHp)
    assertEquals(character.offense, offense)
    assertEquals(character.defense, defense)
    assertEquals(character.evasion, evasion)
  }

  // Testing invariant property. The result is always between 1 and 6.
  test("A character should be able to roll a dice") {
    for (_ <- 1 to 10) {
      assert(character.rollDice >= 1 && character.rollDice <= 6)
    }
  }

  test("A character should be able to gain stars on it's turn") {
    val currentStars = character.getStars
    character.onTurnStars()
    assert(character.getStars >= currentStars)
  }

  test("A character should be able to gain victories") {
    val currentVictories = character.getVictories
    character.setVictories(1)
    assert(character.getVictories > currentVictories)
  }

  test("A character should be on KO when losing all it's Hp") {
    assert(!character.KO())
    character.setCurrentHp(character.getCurrentHp - maxHp)
    assert(character.KO())
  }

  test("A character should be able to enter recovery") {
    character.recovery()
    character.setCurrentHp(character.getCurrentHp - maxHp)
    character.recovery()
  }

  test("A player should be able to attack another player and reduce it's hp if it doesn't evade") {
    val character2Hp: Int = character2.getCurrentHp
    character.attack(character2)
    assert(character2.getCurrentHp<=character2Hp)
  }

  test("A player should be able to attack another player and not reduce it's hp if it evades or attack with full offense stat") {
    val character2Hp: Int = character3.getCurrentHp
    character.attack(character3)
    assert(character3.getCurrentHp == character2Hp || character3.getCurrentHp == (character2Hp - character.offense))
  }

  test("A player should gain victories upon defeating another player") {
    val victories1: Int = character.getVictories
    val victories2: Int = character2.getVictories
    character.attack(character2)
    assert(character.getVictories==victories1 || character2.getVictories==victories2 )
  }

  test("A player should be able to get and set a Norma") {
    val normaLevel = character.getNormaLevel
    val newNorma = new Norma2("stars", 20)
    character.setNorma(newNorma)
    assert(character.getNorma == newNorma)
    assert(character.getNormaLevel == normaLevel + 1)
  }

  test("A player should be able to defend") {
    val initialHp = character.getCurrentHp
    val defendedAttack = 5
    character.defend(character2, defendedAttack)
    val expectedMinHp = initialHp - max(1, (defendedAttack - character.offense) + defendedAttack)
    val expectedMaxHp = initialHp
    assert(character.getCurrentHp >= expectedMinHp && character.getCurrentHp <= expectedMaxHp)
  }

  test("A player should be able to evade") {
    val initialHp = character.getCurrentHp
    val evadedAttack = 5
    character.evade(character2, evadedAttack)
    val rollEvade = 3
    val expectedMinHp = initialHp - evadedAttack
    val expectedMaxHp = if (rollEvade + character.aEvasion > (evadedAttack - character.offense) + evadedAttack) initialHp else initialHp - evadedAttack
    assert(character.getCurrentHp >= expectedMinHp && character.getCurrentHp <= expectedMaxHp)
  }

  test("A player should be able to get and set it's currentHp") {
    assert(character.getCurrentHp==10)
    character.setCurrentHp(character.getCurrentHp-5)
    assert(character.getCurrentHp==5)
  }

  test("A player should be able to be attacked by a WildUnit") {
    character.attack(testWildUnit)
    assert(testWildUnit.getCurrentHp==3)
  }

  test("A player shouldn't have a negative value as maxHp") {
    intercept[InvalidStatException]{
      IncorrectCharacter.validateStat(IncorrectCharacter.maxHp, "MaxHp")
    }
  }

}