package com.epicsevensim.epicsevenfightsimulator.units.enemies;

import com.epicsevensim.epicsevenfightsimulator.units.Unit;
import java.util.ArrayList;

public class Boss extends Unit {
    public Boss(int attack, int defense, int health, int speed, double crit_chance, double crit_damage,
                 double effectiveness, double effect_resist, double dual_attack, String element) {
        super(attack, defense, health, speed, crit_chance, crit_damage, effectiveness, effect_resist, dual_attack, element);
    }

    @Override
    public void takeTurn(ArrayList<Unit> targets) {

    }

}
