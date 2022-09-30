package com.epicsevensim.epicsevenfightsimulator.units.heroes;

import com.epicsevensim.epicsevenfightsimulator.units.enemies.Boss;
import com.epicsevensim.epicsevenfightsimulator.units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Hero extends Unit {

  private int s1_cooldown;
  private int s2_cooldown;
  private int s3_cooldown;

  public Hero(
      int attack,
      int defense,
      int health,
      int speed,
      double crit_chance,
      double crit_damage,
      double effectiveness,
      double effect_resist,
      double dual_attack,
      String element) {
    super(
        attack,
        defense,
        health,
        speed,
        crit_chance,
        crit_damage,
        effectiveness,
        effect_resist,
        dual_attack,
        element);
    this.s1_cooldown = 0;
    this.s2_cooldown = 0;
    this.s3_cooldown = 0;
  }

  public int getS2_cooldown() {
    return s2_cooldown;
  }

  public void setS2_cooldown(int s2_cooldown) {
    this.s2_cooldown = s2_cooldown;
  }

  public int getS3_cooldown() {
    return s3_cooldown;
  }

  public void setS3_cooldown(int s3_cooldown) {
    this.s3_cooldown = s3_cooldown;
  }

  @Override
  public void takeTurn(ArrayList<Unit> targets) {

    this.setCombat_readiness(0.0);
    chooseTarget(targets);

    if (s3_cooldown == 0) {
      s3(targets);
      System.out.println("Using S3");
    } else if (s2_cooldown == 0) {
      s2(targets);
      System.out.println("Using S2");
    } else {
      s1(targets);
      System.out.println("Using S1");
    }

    for (int i = 0; i < targets.size(); i++) {
      if (targets.get(i).isDead()) {}
    }

    if (getS2_cooldown() > 0) {
      setS2_cooldown(getS2_cooldown() - 1);
    }
    if (getS3_cooldown() > 0) {
      setS3_cooldown(getS3_cooldown() - 1);
    }
  }

  private ArrayList<Unit> chooseTarget(ArrayList<Unit> targets) {

    // Sort the target array by health percentage, lowest in front.
    Collections.sort(
        targets,
        new Comparator<Unit>() {
          @Override
          public int compare(Unit first, Unit second) {
            return first.getHealth_percent() > second.getHealth_percent()
                ? -1
                : first.getHealth_percent() < second.getHealth_percent() ? 1 : 0;
          }
        });

    Unit temp = targets.get(0);
    boolean defaultTarget = false;

    for (int i = 0; i < targets.size(); i++) {

      // No point checking anything else if target's not even attackable.
      if (targets.get(i).isAttackable()) {
        // Boss first.
        if (targets.get(i) instanceof Boss) {
          targets.set(0, targets.get(i));
          targets.set(i, temp);

          return targets;
        }
        // Elemental Advantage Second
        if (this.hasAdvantage(targets.get(i))) {
          targets.set(0, targets.get(i));
          targets.set(i, temp);
          defaultTarget = true;
        }
        // Backup in case nothing else is available.
        else if (!defaultTarget) {
          targets.set(0, targets.get(i));
          targets.set(i, temp);
          defaultTarget = true;
        }
      }
    }

    return targets;
  }

  public abstract void s1(ArrayList<Unit> targets);

  public abstract void s2(ArrayList<Unit> targets);

  public abstract void s3(ArrayList<Unit> targets);
}
