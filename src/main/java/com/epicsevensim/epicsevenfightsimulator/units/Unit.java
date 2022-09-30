package com.epicsevensim.epicsevenfightsimulator.units;

import com.epicsevensim.epicsevenfightsimulator.effects.buff.Buff;
import com.epicsevensim.epicsevenfightsimulator.effects.debuff.Debuff;
import com.epicsevensim.epicsevenfightsimulator.effects.Effect;

import com.epicsevensim.epicsevenfightsimulator.effects.debuff.dot.Dot;
import java.util.ArrayList;
import java.util.Random;

public abstract class Unit {

  private int attack;
  private int defense;
  private int health;
  private int speed;
  private double crit_chance;
  private double crit_damage;
  private double effectiveness;
  private double effect_resist;
  private double dual_attack;
  private int max_health;
  private double combat_readiness;
  private boolean isAttackable = true;
  private boolean isDebuffable = true;

  private double health_percent;

  private String element;
  private final ArrayList<Effect> effects = new ArrayList<>(10);

  public Unit(
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
    this.attack = attack;
    this.defense = defense;
    this.max_health = health;
    this.health = health;
    this.speed = speed;
    this.crit_chance = crit_chance;
    this.crit_damage = crit_damage;
    this.effectiveness = effectiveness;
    this.effect_resist = effect_resist;
    this.dual_attack = dual_attack;
    this.combat_readiness = 0.0;
    this.health_percent = 100.0;
    this.element = element;
  }

  public double getHealth_percent() {
    return health_percent;
  }

  public void setHealth_percent(double health_percent) {
    this.health_percent = health_percent;
  }

  public double getCombat_readiness() {
    return combat_readiness;
  }

  public void setCombat_readiness(double combat_readiness) {
    this.combat_readiness = combat_readiness;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getDefense() {
    return defense;
  }

  public void setDefense(int defense) {
    this.defense = defense;
  }

  public int getMax_health() {
    return max_health;
  }

  public void setMax_health(int max_health) {
    this.max_health = max_health;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
    this.health_percent = (this.health / this.max_health) * 100.0;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public double getCrit_chance() {
    return crit_chance;
  }

  public void setCrit_chance(double crit_chance) {
    this.crit_chance = crit_chance;
  }

  public double getCrit_damage() {
    return crit_damage;
  }

  public void setCrit_damage(double crit_damage) {
    this.crit_damage = crit_damage;
  }

  public double getEffectiveness() {
    return effectiveness;
  }

  public void setEffectiveness(double effectiveness) {
    this.effectiveness = effectiveness;
  }

  public double getEffect_resist() {
    return effect_resist;
  }

  public void setEffect_resist(double effect_resist) {
    this.effect_resist = effect_resist;
  }

  public double getDual_attack() {
    return dual_attack;
  }

  public void setDual_attack(double dual_attack) {
    this.dual_attack = dual_attack;
  }

  public boolean isAttackable() {
    return isAttackable;
  }

  public void setAttackable(boolean isAttackable) {
    this.isAttackable = isAttackable;
  }

  public boolean isDebuffable() {
    return isDebuffable;
  }

  public void setDebuffable(boolean isDebuffable) {
    this.isDebuffable = isDebuffable;
  }

  public void clearDebuffs() {

    effects.removeIf(debuff -> (debuff instanceof Debuff));
  }

  public void clearBuffs() {
    effects.removeIf(buff -> (buff instanceof Buff));
  }

  public void removeDebuff(Debuff debuff) {
    this.effects.remove(debuff);
    if (this.effects.size() < 10) {
      this.isDebuffable = true;
    }
  }

  public void removeBuff(Buff buff) {
    this.effects.remove(buff);
    if (this.effects.size() < 10) {
      this.isDebuffable = true;
    }
  }

  public ArrayList<Effect> getEffects() {
    return effects;
  }

  public int getDebuffCount() {
    int count = 0;

    for (Effect debuff : effects) {
      if (debuff instanceof Debuff) {
        count++;
      }
    }

    return count;
  }

  public void apply_damage(int base_damage, Unit target, double penetration) {

    if (target.isAttackable()) {
      int final_damage =
          (int) (base_damage / (((target.getDefense() * (1 - penetration)) / 300.0) + 1.0));

      if (target.getHealth() > final_damage) {
        target.setHealth(target.getHealth() - final_damage);
      } else {
        target.setHealth(0);
      }
    }
  }

  public void apply_effect(Effect effect, double effect_chance, Unit target) {

    if (target.isDebuffable) {
      Random rand = new Random();
      if (rand.nextDouble() <= effect_chance) {
        target.effects.add(effect);
      }
    }

    if (target.effects.size() >= 10) {
      target.isDebuffable = false;
    }
  }

  public String getElement() {
    return element;
  }

  public void setElement(String element) {
    this.element = element;
  }

  public boolean hasAdvantage(Unit target) {
    if (this.element == "Ice" && target.getElement() == "Fire") {
      return true;
    }
    if (this.element == "Fire" && target.getElement() == "Earth") {
      return true;
    }
    if (this.element == "Earth" && target.getElement() == "Ice") {
      return true;
    }
    if (this.element == "Dark" && target.getElement() == "Light") {
      return true;
    }
    if (this.element == "Light" && target.getElement() == "Dark") {
      return true;
    }

    return false;
  }

  public boolean isDead() {
    return this.getHealth() <= 0;
  }

  public abstract void takeTurn(ArrayList<Unit> targets);

  public void applyDots() {
    for (Effect effect : effects) {
      if (effect instanceof Dot) {
        this.setHealth(this.getHealth() - ((Dot) effect).calc_damage(this));
        effect.reduceDuration();
        if (effect.getDuration() <= 0) {
          this.removeEffect(effect);
        }
      }
    }
  }

  private void removeEffect(Effect effect) {
    this.effects.remove(effect);
  }
}
