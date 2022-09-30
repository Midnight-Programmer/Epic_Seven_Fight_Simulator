package com.epicsevensim.epicsevenfightsimulator.units.heroes;

import com.epicsevensim.epicsevenfightsimulator.effects.debuff.dot.Bleed;
import com.epicsevensim.epicsevenfightsimulator.effects.debuff.Extinction;
import com.epicsevensim.epicsevenfightsimulator.effects.debuff.Unhealable;
import com.epicsevensim.epicsevenfightsimulator.units.Unit;
import java.util.ArrayList;

public class Sigret extends Hero {

  private String name = "Sigret";
  private int s2_cooldown = 3;
  private int s3_cooldown = 4;

  public Sigret(
      int attack,
      int defense,
      int health,
      int speed,
      double crit_chance,
      double crit_damage,
      double effectiveness,
      double effect_resist,
      double dual_attack) {
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
        "Ice");
  }

  public Sigret() {
    super(1228, 553, 5784, 109, 23.0, 165.0, 0.0, 0.0, 5.0, "Ice");
  }

  @Override
  public void s1(ArrayList<Unit> targets) {
    double bleed_chance = 0.4;

    int base_damage = (int) (this.getAttack() * 1 * 1 * 1.871);

    // Do damage
    apply_damage(base_damage, targets.get(0), 0);

    // Debuff application check, and roll for debuffs if needed.
    // Checks twice, because skills does two bleeds.
    for (int i = 0; i < 2; i++) {
      apply_effect(new Bleed(this, 2), bleed_chance, targets.get(0));
    }

    return;
  }

  @Override
  public void s2(ArrayList<Unit> targets) {

    setS2_cooldown(s2_cooldown);

    double unhealable_chance = 0.85;
    double bleed_chance = 0.85;

    int base_damage = (int) (this.getAttack() * 1.25 * 1 * 1.871);

    // Do damage
    apply_damage(base_damage, targets.get(0), 0);

    if (targets.get(0).getHealth() <= (int) (targets.get(0).getMax_health() * 0.5)
        && targets.get(0).isAttackable()) {
      s1_special(targets);
    }

    apply_effect(new Unhealable(2), unhealable_chance, targets.get(0));
    apply_effect(new Bleed(this, 2), bleed_chance, targets.get(0));

    return;
  }

  public void s1_special(ArrayList<Unit> targets) {
    double bleed_chance = 1.0;

    int base_damage = (int) (this.getAttack() * 1 * 1 * 1.871);

    // Do damage
    apply_damage(base_damage, targets.get(0), 0);

    // Debuff application check, and roll for debuffs if needed.
    // Checks twice, because skills does two bleeds.
    for (int i = 0; i < 2; i++) {
      apply_effect(new Bleed(this, 2), bleed_chance, targets.get(0));
    }

    return;
  }

  @Override
  public void s3(ArrayList<Unit> targets) {
    double extinction_chance = 1.0;
    setS3_cooldown(s3_cooldown);

    int base_damage = (int) (this.getAttack() * 1.7 * 0.8 * 1.871);

    // Do damage
    apply_damage(base_damage, targets.get(0), 0.3 + 0.1 * targets.get(0).getDebuffCount());

    // Check if dead to apply extinction
    if (targets.get(0).getHealth() == 0) {
      apply_effect(new Extinction(), extinction_chance, targets.get(0));
    }

    return;
  }
}
