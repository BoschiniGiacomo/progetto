package com.boschini.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class HomeController {
    @FXML
    private ChoiceBox<String> difficulty;
    @FXML
    private Label labelName;
    @FXML
    private Button start;

    @FXML
    private TextField username;

static  String nomeUtente = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
        "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/user.txt";
static  String modalita = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
        "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/mode.txt";
    static  String score = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
            "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/score.txt";
    @FXML
    public void initialize() {
        difficulty.getItems().removeAll(difficulty.getItems());
        difficulty.getItems().addAll("easy", "medium", "hard");
        difficulty.getSelectionModel().select("easy");
        newFile(nomeUtente);
        newFile(modalita);
        newFile(score);
    }
    // Listato 1. Creazione di un file vuoto
    public static void newFile(String path) {


        try {
            File file = new File(path);
            if (file.exists())
                System.out.println("Il file " + path + " esiste");
            else if (file.createNewFile())
                System.out.println("Il file " + path + " è stato creato");
            else
                System.out.println("Il file " + path + " non può essere creato");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void onClickStart(ActionEvent event) throws IOException {
        if(username.getText().isEmpty()){
            Alert alert= new Alert((Alert.AlertType.ERROR));
            alert.setTitle("Username");
            alert.setHeaderText("required field");
            alert.showAndWait();
        }
        else if(username.getText().length()>16){
            Alert alert= new Alert((Alert.AlertType.ERROR));
            alert.setTitle("Username");
            alert.setHeaderText("Max 16 characters");
            alert.showAndWait();
            username.setText(username.getText(0,16));
        }
        else {
            User utente = new User();
            utente.setUsername(username.getText());

            try {
                File file = new File(nomeUtente);
                FileWriter fw = new FileWriter(file);
                fw.write(username.getText());
                fw.flush();
                fw.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }




            if (difficulty.getSelectionModel().getSelectedItem().equals("easy")) {
                utente.setMode(1);

            }
            if (difficulty.getSelectionModel().getSelectedItem().equals("medium")) {
                utente.setMode(2);

            }
            if (difficulty.getSelectionModel().getSelectedItem().equals("hard")) {
                utente.setMode(3);

            }

            try {
                File f2 = new File(modalita);
                FileWriter fw2 = new FileWriter(f2);
                fw2.write(String.valueOf(utente.getMode()));
                fw2.flush();
                fw2.close();
            }
            catch(IOException e2) {
                e2.printStackTrace();
            }


            try {
                File f3 = new File(score);
                FileWriter fw3 = new FileWriter(f3);
                fw3.write("-");
                fw3.flush();
                fw3.close();
            }
            catch(IOException e2) {
                e2.printStackTrace();
            }


            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("play.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("1 coin to house");
            stage.setScene(scene);
            stage.show();


        }

    }


}