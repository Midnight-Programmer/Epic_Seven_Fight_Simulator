module com.epicsevensim.epicsevenfightsimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.epicsevensim.epicsevenfightsimulator to javafx.fxml;
    exports com.epicsevensim.epicsevenfightsimulator;
}