package com.epicsevensim.epicsevenfightsimulator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EpicSevenController {

    private final ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> hero1_dropdown;
    @FXML
    private ChoiceBox<String> hero2_dropdown;
    @FXML
    private ChoiceBox<String> hero3_dropdown;
    @FXML
    private ChoiceBox<String> hero4_dropdown;
    @FXML
    private Button button1;

    @FXML
    private TextField hero1_attack;

    public void initialize() {
        //system.out.println("Controller Initializing");
        heroData();
    }

    public void button1Press(ActionEvent event) {
        hero1_attack.setText("1000");
    }

    private void heroData() {
        list.removeAll();
        list.addAll("Sigret");
        hero1_dropdown.getItems().addAll(list);
        hero2_dropdown.getItems().addAll(list);
        hero3_dropdown.getItems().addAll(list);
        hero4_dropdown.getItems().addAll(list);
    }

}