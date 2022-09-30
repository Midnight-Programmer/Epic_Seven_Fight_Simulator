module com.epicsevensim.epicsevenfightsimulator {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;

  opens com.epicsevensim.epicsevenfightsimulator to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator;
  exports com.epicsevensim.epicsevenfightsimulator.effects;

  opens com.epicsevensim.epicsevenfightsimulator.effects to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator.units;

  opens com.epicsevensim.epicsevenfightsimulator.units to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator.units.heroes;

  opens com.epicsevensim.epicsevenfightsimulator.units.heroes to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator.effects.buff;

  opens com.epicsevensim.epicsevenfightsimulator.effects.buff to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator.effects.debuff;

  opens com.epicsevensim.epicsevenfightsimulator.effects.debuff to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator.units.enemies;

  opens com.epicsevensim.epicsevenfightsimulator.units.enemies to
      javafx.fxml;

  exports com.epicsevensim.epicsevenfightsimulator.effects.debuff.dot;

  opens com.epicsevensim.epicsevenfightsimulator.effects.debuff.dot to
      javafx.fxml;
}
