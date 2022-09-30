package com.epicsevensim.epicsevenfightsimulator;

import com.epicsevensim.epicsevenfightsimulator.units.Unit;
import com.epicsevensim.epicsevenfightsimulator.units.heroes.Hero;

import java.util.ArrayList;
import java.util.Random;

public class Simulation {

  private ArrayList<Unit> team;
  private ArrayList<Unit> enemies;

  private ArrayList<Unit> deadTeamUnits;

  private ArrayList<Unit> deadEnemyUnits;

  public Simulation(ArrayList<Unit> team, ArrayList<Unit> enemies) {
    this.team = team;
    this.enemies = enemies;

    this.deadTeamUnits = new ArrayList<>();
    this.deadEnemyUnits = new ArrayList<>();
  }

  public boolean startBattle() {

    // Set initial CR
    initializeCR();

    // Determine who goes next
    Unit attacker = getNextAttacker();

    // Set everyone's current CR based on who goes next
    applyCR(attacker);

    // Apply pre-battle effects
    applyPreBattleEffects(attacker);

    // Determine target

    // Attack

    // Calculate damage

    // Figure out who's alive

    // Determine potential counter attacks

    // Apply post-battle effects

    return true;
  }

  private void applyPreBattleEffects(Unit attacker) {
    // Reduce DoT effect durations by 1 and apply damage effects
    attacker.applyDots();
  }

  public boolean BattleResult() {

    // System.out.println(nextTurn().getSpeed());
    initializeCR();
    Unit nextAttacker = getNextAttacker();
    applyCR(nextAttacker);
    if (nextAttacker instanceof Hero) {
      nextAttacker.takeTurn(enemies);
    } else {
      nextAttacker.takeTurn(team);
    }

    return true;
  }

  private void initializeCR() {
    Random rand = new Random();
    int amount = 0;

    for (Unit attacker : team) {
      amount = rand.nextInt(6); // This should give 0-5
      attacker.setCombat_readiness(amount);
    }
    for (Unit attacker : enemies) {
      amount = rand.nextInt(6); // This should give 0-5
      attacker.setCombat_readiness(amount);
    }
  }

  private void applyCR(Unit nextAttacker) {
    // We don't want a negative turn time if they are over 100% CR from boosts
    double turnTime =
        Math.max(
            (100.0 - nextAttacker.getCombat_readiness()) * 100.0 / nextAttacker.getSpeed(), 0.0);

    for (Unit teammate : team) {
      teammate.setCombat_readiness(
          teammate.getCombat_readiness() + (turnTime * (teammate.getSpeed() / 100.0)));
    }

    for (Unit enemy : enemies) {
      enemy.setCombat_readiness(
          enemy.getCombat_readiness() + (turnTime * (enemy.getSpeed() / 100.0)));
    }

    // We should be right about 100% already, but this will get rid of rounding errors
    nextAttacker.setCombat_readiness(100.0);
  }

  private Unit getNextAttacker() {

    Unit nextUnit = null;
    double turnTime =
        100.0 * 100.0; // Would need less than 1 speed to get above this, which isn't possible.

    for (Unit unit : team) {
      if ((100.0 - unit.getCombat_readiness()) * 100.0 / unit.getSpeed() < turnTime) {
        turnTime = (100.0 - unit.getCombat_readiness()) * 100.0 / unit.getSpeed();
        nextUnit = unit;
      }
    }

    for (Unit unit : enemies) {
      if ((100.0 - unit.getCombat_readiness()) * 100.0 / unit.getSpeed() < turnTime) {
        turnTime = (100.0 - unit.getCombat_readiness()) * 100.0 / unit.getSpeed();
        nextUnit = unit;
      }
    }

    /* Sorting, not currently needed
    Collections.sort(
        lineUp,
        new Comparator<Unit>() {
          @Override
          public int compare(Unit first, Unit second) {
            return (((100.0 - first.getCombat_readiness()) * 100.0) / first.getSpeed())
                    < (((100.0 - second.getCombat_readiness()) * 100.0) / second.getSpeed())
                ? -1
                : (((100.0 - first.getCombat_readiness()) * 100.0) / first.getSpeed())
                        > (((100.0 - second.getCombat_readiness()) * 100.0) / second.getSpeed())
                    ? 1
                    : 0;
          }
        });*/

    return nextUnit;
  }
}
