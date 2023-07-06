package com.boschini.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CoinToHouseController {
    @FXML
    private ChoiceBox<String> difficulty;
    @FXML
    private Label labelName;
    @FXML
    private Button start;
    @FXML
    private TextField username;

    @FXML
    public void initialize() {
        Thing penna= new Thing(1.2,1);
        difficulty.getItems().removeAll(difficulty.getItems());
        difficulty.getItems().addAll("easy", "medium", "hard");
        difficulty.getSelectionModel().select("easy");
    }

    @FXML
    void onClickStart(ActionEvent event) throws IOException {
        if (difficulty.getSelectionModel().getSelectedItem().equals("easy")) {
            username.setText("Boschini");
        }
        if (difficulty.getSelectionModel().getSelectedItem().equals("medium")) {
            username.setText("Boschini2");
        }
        if (difficulty.getSelectionModel().getSelectedItem().equals("hard")) {
            username.setText("Boschini3");
        }
            labelName.setText(username.getText());

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("play.fxml")));
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("home");
        stage.setScene(scene);
        stage.show();


    }


}