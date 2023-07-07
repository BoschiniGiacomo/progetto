package com.boschini.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class PlayController {

    @FXML
    private Button confermation;

    @FXML
    private Button buttonConvert;


    @FXML
    private Label offerta;

    @FXML
    private Button skipTrade;

    @FXML
    private Label coinValue;

    @FXML
    private Label personalObject;
    @FXML
    private Label labelDifficulty;

    @FXML
    private Label labelRecord;

    @FXML
    private Label labelUser;


    @FXML
    private Label labelLevel;

    @FXML
    private Label labelcountNetxLevel;

    User utente= new User();
    boolean aggiorna=false;
    int rnd;
    int record=0;
    int step=0,stepLiv=0;
    int coef;

    Thing[] mode= new Thing[]{
            //livello1
            new Thing(1,"penna"),
            new Thing(2,"tazza"),
            new Thing(25,"set di coltelli"),
            new Thing(2,"matita"),
            new Thing(3,"sacchetto di elastici"),
            new Thing(2,"merendina"),
            new Thing(5,"deodorante"),
            new Thing(3,"coca cola"),
            new Thing(15,"libro"),
            new Thing(20,"maglietta"),
            new Thing(30,"cassa di vino"),

            //livello 2
            new Thing(70,"telefono ricondizionato"),
            new Thing(200,"computer"),
            new Thing(80,"stereo"),
            new Thing(150,"tablet"),
            new Thing(100,"orologio"),
            new Thing(370,"gommone"),
            new Thing(20,"scatola misteriosa"),
            new Thing(30,"----"),
            new Thing(5,"monete da collezzione"),
            new Thing(6,"tavolo d'epoca"),
            new Thing(2,"piano cottura nuovo"),

            //livello 3
            new Thing(2,"moto"),
            new Thing(2,"fiat 500L"),
            new Thing(3,"audi q3"),
            new Thing(2,"barca a vela"),
            new Thing(3,"Tv"),
            new Thing(2,"trattorino"),
            new Thing(4,"escavatore"),
            new Thing(3,"terreno"),
            new Thing(5,""),
            new Thing(6,""),
            new Thing(2,""),};


    @FXML
    void restart(ActionEvent event) throws IOException {//da sistemare!!!!
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home2.fxml")));
        Stage stage2= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene2 = new Scene(root1);
        stage2.setTitle("home");
        stage2.setScene(scene2);
        stage2.show();
    }


    @FXML
    public void initialize() {


        String nomeUtente = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
                "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/user.txt";
        String modalita = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
                "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/mode.txt";

        utente.setUsername(readFile(nomeUtente));
        int mod= Integer.parseInt(readFile(modalita));
        utente.setMode(mod);
        coef=setCoef(utente.getMode());
        labelDifficulty.setText("Difficulty: "+modalitaToString(utente.getMode()));
        labelUser.setText("User: "+utente.getUsername());
        coinValue.setText(String.valueOf(utente.getCoin()));

        next();
    }

    public int setCoef(int d){
        switch(d){
            case 1:
               return 3;
            case 2:
                return 5;
            case 3:
                return 10;
        }
        return 0;
    }
    public void next(){
        if((utente.getCoin())<3){
            skipTrade.setDisable(true);
        }
        else{
            skipTrade.setDisable(false);
        }
        if(aggiorna==true){
            utente.setOggetto(mode[rnd]);
            aggiorna=false;
        }

        if(utente.getOggetto().getNameObject().equals("coin") ||(utente.getCoin())<2){
            buttonConvert.setDisable(true);
        }
        else{
            buttonConvert.setDisable(false);
        }


        if(record<=utente.getOggetto().getCosto()){
            record=utente.getOggetto().getCosto();
            labelRecord.setText(utente.getOggetto().getNameObject());
        }



        personalObject.setText(utente.getOggetto().getNameObject());
        labelLevel.setText("Level: "+ (step%coef));
        labelcountNetxLevel.setText("Next level in "+(coef-stepLiv)+" correct trade");
    offert();
    }

    public void offert(){
        rnd = (int)(Math. random()*10);
        switch (step%coef) {

            case 0:
                offerta.setText(mode[rnd].getNameObject());
                break;
            case 1:
                offerta.setText(mode[rnd+10].getNameObject());
                break;
            case 2:
                offerta.setText(mode[rnd+20].getNameObject());
                break;
            case 3:
                Thing casa=  new Thing(200000,"casa");
                offerta.setText(casa.getNameObject());
                break;
        }

        if(stepLiv==coef){
            stepLiv=0;
        }


    }
    public static String modalitaToString(int x) {
        String stringa="";
        if (x==1) {
            return "easy";
        }
        if (x==2) {
            return "medium";
        }
        if (x==3) {
            return "hard";
        }
        return stringa;
    }
    public static String readFile(String path) {
        char[] in = new char[20];
        StringBuilder stringBuilder= new StringBuilder();
        int size = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            size = fr.read(in);

            for(int i=0; i<size; i++)
               stringBuilder.append(in[i]);
            fr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    @FXML
    void onClikSkip(ActionEvent event) {
        utente.setCoin(utente.getCoin()-3);
        coinValue.setText(String.valueOf(utente.getCoin()));
        next();


    }

    @FXML
    void onClikConverterToCoin(ActionEvent event) {
       utente.setCoin(((utente.getOggetto().getCosto()) + utente.getCoin()));
        coinValue.setText(String.valueOf(utente.getCoin()));
        utente.setOggetto(new Thing(1,"coin"));
        next();

    }

    @FXML
    void onClikConverter50(ActionEvent event) {
        utente.setCoin((int)((utente.getOggetto().getCosto())/2 + utente.getCoin()));
        coinValue.setText(String.valueOf(utente.getCoin()));
        utente.setOggetto(new Thing(1,"coin"));
        next();
    }
    @FXML
    void update(ActionEvent event) {
    aggiorna=true;
        if(utente.getOggetto().getCosto()<=utente.getOggetto().getCosto()){
            step++;
            stepLiv++;
            labelRecord.setText(utente.getOggetto().getNameObject());
        }
        next();
    }
}
