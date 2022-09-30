package com.epicsevensim.epicsevenfightsimulator.effects.debuff.dot;

import com.epicsevensim.epicsevenfightsimulator.effects.debuff.Debuff;
import com.epicsevensim.epicsevenfightsimulator.units.Unit;
import com.epicsevensim.epicsevenfightsimulator.units.heroes.Hero;

public class Bleed extends Debuff implements Dot {

  private Hero name;

  public Bleed(Hero name, int duration) {
    super(duration, false);
    this.name = name;
  }

  // Bleeds are 30% of current attack, and ignore 70% of current defense. Calculated at beginning of
  // enemy's turn.
  @Override
  public int calc_damage(Unit target) {
    return (int) ((name.getAttack() * 0.3) / (((target.getDefense() * 0.3) / 300) + 1));
  }
}
