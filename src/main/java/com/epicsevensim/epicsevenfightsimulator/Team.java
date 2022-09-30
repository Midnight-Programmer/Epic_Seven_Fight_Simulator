package com.epicsevensim.epicsevenfightsimulator;

import com.epicsevensim.epicsevenfightsimulator.units.heroes.Hero;

public class Team {

	private Hero hero1;
	private Hero hero2;
	private Hero hero3;
	private Hero hero4;
	
	private int souls;
	
	public Team(Hero hero1, Hero hero2, Hero hero3, Hero hero4) {
		this.hero1 = hero1;
		this.hero2 = hero2;
		this.hero3 = hero3;
		this.hero4 = hero4;
		this.souls = 0;
	}
	
	public Team(Hero hero1, Hero hero2, Hero hero3, Hero hero4, int souls) {
		this.hero1 = hero1;
		this.hero2 = hero2;
		this.hero3 = hero3;
		this.hero4 = hero4;
		this.souls = souls;
	}

	public Hero getHero1() {
		return hero1;
	}

	public void setHero1(Hero hero1) {
		this.hero1 = hero1;
	}

	public Hero getHero2() {
		return hero2;
	}

	public void setHero2(Hero hero2) {
		this.hero2 = hero2;
	}

	public Hero getHero3() {
		return hero3;
	}

	public void setHero3(Hero hero3) {
		this.hero3 = hero3;
	}

	public Hero getHero4() {
		return hero4;
	}

	public void setHero4(Hero hero4) {
		this.hero4 = hero4;
	}

	public int getSouls() {
		return souls;
	}

	public void setSouls(int souls) {
		this.souls = souls;
	}
	
	
}
