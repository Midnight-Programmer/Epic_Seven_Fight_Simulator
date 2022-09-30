package com.epicsevensim.epicsevenfightsimulator.effects;

public class Effect {

  private int duration;
  private boolean isPermanent;

  public Effect(int duration, boolean isPermanent) {
    this.duration = duration;
    this.isPermanent = isPermanent;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public boolean isPermanent() {
    return isPermanent;
  }

  public void setPermanent(boolean isPermanent) {
    this.isPermanent = isPermanent;
  }

  public void reduceDuration() {
    this.duration -= 1;
    if (this.duration < 0) {
      this.duration = 0;
    }
  }
}
