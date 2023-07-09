package com.boschini.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class PlayController {

    @FXML
    private Button ButtoConfermation;

    @FXML
    private Button buttonConvert;

    @FXML
    private Label labelOfferta;

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
    @FXML
    private Button buttonWin;
    @FXML
    private Label labelScore;
    @FXML
    private Label labelWin;
    @FXML
    private Button buttonConvert50;

    @FXML
    private Label labelCost;

    @FXML
    private Label labelCostConvert;

    @FXML
    private Label labelCostConvert50;

    @FXML
    private Label labelCostSkip;
    @FXML
    private Label labelExchange;
    @FXML
    private Label labelShop;
    @FXML
    private Label labelBestScore;


    User utente = new User();
    boolean aggiorna;
    int rnd;
    int record;
    int stepLiv;
    int coef;
    int liv;
    int score=20;
    int costSkip;
    int costConvert;

    Thing[] mode = new Thing[]{
            //livello1
            new Thing(1, "penna"), new Thing(2, "tazza"),
            new Thing(25, "set di coltelli"), new Thing(2, "matita"),
            new Thing(3, "sacchetto di elastici"), new Thing(2, "merendina"),
            new Thing(5, "deodorante"), new Thing(3, "coca cola"),
            new Thing(15, "libro"), new Thing(20, "maglietta"),
            new Thing(30, "cassa di vino"), new Thing(40, "casseta attrezzi"),
            new Thing(5, "metro"), new Thing(10, "termometro"),
            new Thing(70, "avvitatore"), new Thing(12, "scopa"),
            new Thing(78, "aspirapolvere"), new Thing(15, "coltellino svizzero"),
            new Thing(35, "zaino"), new Thing(48, "alexa"),

            //livello 2
            new Thing(270, "telefono"), new Thing(200, "computer"),
            new Thing(80, "stereo"), new Thing(150, "tablet"),
            new Thing(100, "orologio"), new Thing(370, "gommone"),
            new Thing(20, "scatola misteriosa"), new Thing(70, "quadro"),
            new Thing(500, "monete da collezzione"), new Thing(15000, "tavolo d'epoca"),
            new Thing(1200, "piano cottura nuovo"), new Thing(120, "aripods"),
            new Thing(70, "smartwatch"), new Thing(120, "-"),
            new Thing(1200, "-"), new Thing(120, "-"),
            new Thing(1200, "-"), new Thing(120, "-"),
            new Thing(1200, "-"), new Thing(120, "-"),

            //livello 3
            new Thing(4000, "moto"), new Thing(10000, "fiat 500L"),
            new Thing(30000, "audi q3"), new Thing(70000, "barca a vela"),
            new Thing(1000, "Tv"), new Thing(9000, "trattorino"),
            new Thing(40000, "escavatore"), new Thing(50000, "terreno"),
            new Thing(5, "---"), new Thing(6, "---"),
            new Thing(5, "---"), new Thing(6, "---"),
            new Thing(5, "---"), new Thing(6, "---"),
            new Thing(5, "---"), new Thing(6, "---"),
            new Thing(5, "---"), new Thing(6, "---"),
            new Thing(5, "---"), new Thing(6, "---"),
            new Thing(5, "---"), new Thing(6, "---")};

    @FXML
    void restart(ActionEvent event) throws IOException {
        if(score> utente.getScore()){
            String fscore = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
                    "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/score.txt";
            utente.setScore(score);
            try {
                File f3 = new File(fscore);
                FileWriter fw3 = new FileWriter(f3);
                fw3.write(String.valueOf(utente.getScore()));
                fw3.flush();
                fw3.close();
            }
            catch(IOException e2) {
                e2.printStackTrace();
            }
        }
        score=20;
        initialize();
    }

    @FXML
    public void initialize() {
        labelWin.setVisible(false);
        labelScore.setVisible(false);
        buttonWin.setVisible(false);
        skipTrade.setVisible(true);
        buttonConvert.setVisible(true);
        buttonConvert50.setVisible(true);
        labelCost.setVisible(true);
        labelCostConvert.setVisible(true);
        labelCostConvert50.setVisible(true);
        labelCostSkip.setVisible(true);
        labelcountNetxLevel.setVisible(true);
        labelExchange.setVisible(true);
        labelOfferta.setVisible(true);
        labelShop.setVisible(true);
        ButtoConfermation.setVisible(true);
        buttonConvert50.setDisable(false);
        String nomeUtente = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " + "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/user.txt";
        String modalita = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " + "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/mode.txt";
        String score = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " + "informatica\\2_anno" +
                "\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/score.txt";
        liv = 1;
        record = 0;
        stepLiv = 0;
        aggiorna = false;

        utente.setUsername(readFile(nomeUtente));
        if(readFile(score).equals("-")){
            utente.setScore(0);
        }
        else{
            utente.setScore(Integer.parseInt(readFile(score)));
        }

        int mod = Integer.parseInt(readFile(modalita));
        utente.setMode(mod);
        costimodalita(utente.getMode());
        utente.setOggetto(new Thing(1, "coin"));
        utente.setCoin(0);
        coef = setCoef(utente.getMode());
        labelDifficulty.setText("Diff: " + modalitaToString(utente.getMode()));
        labelUser.setText("User: " + utente.getUsername());
        coinValue.setText(String.valueOf(utente.getCoin()));

        labelBestScore.setText("Best Score: "+utente.getScore());

        next();
        labelLevel.setText("Lvl: " + 0);
    }

    public int setCoef(int d) {
        switch (d) {
            case 1:
                return 3;
            case 2:
                return 5;
            case 3:
                return 10;
        }
        return 0;
    }

    public void next() {
        if ((utente.getCoin()) < costSkip) {
            skipTrade.setDisable(true);
        } else {
            skipTrade.setDisable(false);
        }
        if (aggiorna == true) {
            utente.setOggetto(mode[rnd]);
            aggiorna = false;
        }

        if (utente.getOggetto().getNameObject().equals("coin") || (utente.getCoin()) < costConvert) {
            buttonConvert.setDisable(true);
        } else {
            buttonConvert.setDisable(false);
        }

        if (record <= utente.getOggetto().getCosto()) {
            record = utente.getOggetto().getCosto();
            labelRecord.setText(utente.getOggetto().getNameObject());
        }

        personalObject.setText(utente.getOggetto().getNameObject());

        offert();
        labelLevel.setText("Lvl: " + liv);
        labelcountNetxLevel.setText("Next level in " + (coef - stepLiv) + " correct trade");
        if(labelOfferta.getText().equals("casa")){
            labelcountNetxLevel.setVisible(false);
        }
    }

    public void offert() {
        rnd = (int) (Math.random() * 20);
        switch (liv) {

            case 1:
                labelOfferta.setText(mode[rnd].getNameObject());
                liv = 1;
                break;
            case 2:
                rnd += 20;
                labelOfferta.setText(mode[rnd].getNameObject());
                liv = 2;
                break;
            case 3:
                rnd += 40;
                labelOfferta.setText(mode[rnd].getNameObject());
                liv = 3;
                break;
            case 4:
                Thing casa = new Thing(200000, "casa");
                labelOfferta.setText(casa.getNameObject());
                buttonConvert50.setDisable(true);
                buttonConvert.setDisable(true);
                skipTrade.setDisable(true);
                break;
        }

        if (stepLiv == coef) {
            stepLiv = 0;
        }
    }

    public static String modalitaToString(int x) {
        String stringa = "";
        if (x == 1) {
            return "easy";
        }
        if (x == 2) {
            return "medium";
        }
        if (x == 3) {
            return "hard";
        }
        return stringa;
    }

    public static String readFile(String path) {
        char[] in = new char[20];
        StringBuilder stringBuilder = new StringBuilder();
        int size = 0;
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            size = fr.read(in);

            for (int i = 0; i < size; i++)
                stringBuilder.append(in[i]);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @FXML
    void onClikSkip(ActionEvent event) {
        utente.setCoin(utente.getCoin() - costSkip);
        coinValue.setText(String.valueOf(utente.getCoin()));
        next();
    }

    @FXML
    void onClikConverterToCoin(ActionEvent event) {
        utente.setCoin(((utente.getOggetto().getCosto()) + utente.getCoin()));
        utente.setCoin(utente.getCoin() - costConvert);
        coinValue.setText(String.valueOf(utente.getCoin()));
        utente.setOggetto(new Thing(1, "coin"));
        stepLiv = 0;
        liv = 1;
        next();
    }

    @FXML
    void onClikConverter50(ActionEvent event) {
        utente.setCoin((int) ((utente.getOggetto().getCosto()) / 2 + utente.getCoin()));
        coinValue.setText(String.valueOf(utente.getCoin()));
        utente.setOggetto(new Thing(1, "coin"));
        stepLiv = 0;
        liv = 1;
        next();
    }

    @FXML
    void update(ActionEvent event) {
        if(labelOfferta.getText().equals("casa")){
            win();
        }
        else {
            aggiorna = true;
            if (utente.getOggetto().getCosto() <= mode[rnd].getCosto()) {
                stepLiv++;
                score++;
            } else {
                stepLiv--;
                score-=2;
            }
            if (stepLiv == coef) {
                liv++;
            }
            if (stepLiv == -3 && liv > 1) {
                liv--;
            }
            next();
        }
    }

    public void win() {
        labelWin.setVisible(true);
        labelScore.setVisible(true);
        labelScore.setText("Score: "+score);
        buttonWin.setVisible(true);
        skipTrade.setVisible(false);
        buttonConvert.setVisible(false);
        buttonConvert50.setVisible(false);
        labelCost.setVisible(false);
        labelCostConvert.setVisible(false);
        labelCostConvert50.setVisible(false);
        labelCostSkip.setVisible(false);
        labelcountNetxLevel.setVisible(false);
        labelExchange.setVisible(false);
        labelOfferta.setVisible(false);
        labelShop.setVisible(false);
        ButtoConfermation.setVisible(false);

        utente.setOggetto(new Thing(200000, "casa"));
        if (record <= utente.getOggetto().getCosto()) {
            record = utente.getOggetto().getCosto();
            labelRecord.setText(utente.getOggetto().getNameObject());
        }
        personalObject.setText(utente.getOggetto().getNameObject());

    }

    @FXML
    void onClikRestart(ActionEvent event) {

        if(score> utente.getScore()){
            String fscore = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
                    "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/score.txt";
            utente.setScore(score);
            try {
                File f3 = new File(fscore);
                FileWriter fw3 = new FileWriter(f3);
                fw3.write(String.valueOf(utente.getScore()));
                fw3.flush();
                fw3.close();
            }
            catch(IOException e2) {
                e2.printStackTrace();
            }
        }
        score=20;
        initialize();
    }

    @FXML
    void onClikEasy(ActionEvent event) {
        utente.setMode(1);
        writeModOnFile(utente.getMode());
        labelDifficulty.setText("Diff: " + modalitaToString(utente.getMode()));
        costimodalita(utente.getMode());
        if ((utente.getCoin()) < costSkip) {
            skipTrade.setDisable(true);
        } else {
            skipTrade.setDisable(false);
        }

        if (utente.getOggetto().getNameObject().equals("coin") || (utente.getCoin()) < costConvert) {
            buttonConvert.setDisable(true);
        } else {
            buttonConvert.setDisable(false);
        }
        coef = setCoef(utente.getMode());
        labelcountNetxLevel.setText("Next level in " + (coef - stepLiv) + " correct trade");
    }

    @FXML
    void onClikMedium(ActionEvent event) {
        utente.setMode(2);
        writeModOnFile(utente.getMode());
        labelDifficulty.setText("Diff: " + modalitaToString(utente.getMode()));
        costimodalita(utente.getMode());
        if ((utente.getCoin()) < costSkip) {
            skipTrade.setDisable(true);
        } else {
            skipTrade.setDisable(false);
        }

        if (utente.getOggetto().getNameObject().equals("coin") || (utente.getCoin()) < costConvert) {
            buttonConvert.setDisable(true);
        } else {
            buttonConvert.setDisable(false);
        }
        coef = setCoef(utente.getMode());
        labelcountNetxLevel.setText("Next level in " + (coef - stepLiv) + " correct trade");

    }

    @FXML
    void onClikHard(ActionEvent event) {

        utente.setMode(3);
        writeModOnFile(utente.getMode());
        labelDifficulty.setText("Diff: " + modalitaToString(utente.getMode()));
        costimodalita(utente.getMode());
        if ((utente.getCoin()) < costSkip) {
            skipTrade.setDisable(true);
        } else {
            skipTrade.setDisable(false);
        }

        if (utente.getOggetto().getNameObject().equals("coin") || (utente.getCoin()) < costConvert) {
            buttonConvert.setDisable(true);
        } else {
            buttonConvert.setDisable(false);
        }
        coef = setCoef(utente.getMode());
        labelcountNetxLevel.setText("Next level in " + (coef - stepLiv) + " correct trade");
    }


    public void writeModOnFile(int m){
        String modalita = "C:\\Users\\Boschini\\Documents\\Giacomo\\Università Ingegnieria " +
                "informatica\\2_anno\\programmazioneOggetti\\demo\\src\\main\\java\\com\\boschini\\demo/mode.txt";
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
    }

    public void costimodalita(int x) {

        if (x == 1) {
            costSkip=3;
            costConvert=2;

        }
        if (x == 2) {
            costSkip=5;
            costConvert=3;
        }
        if (x == 3) {
            costSkip=7;
            costConvert=5;
        }

        labelCostSkip.setText(costSkip+"$");
        labelCostConvert.setText(costConvert+"$");
    }


    @FXML
    void onClikInfoLevel(ActionEvent event) {
        Alert alert= new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("How to play");
        alert.setHeaderText("Level");
        alert.setContentText("To win you must overcome 3 level of exchange,\nto move to the level of successive " +
                "exchange you must make a number of convenient exhcange, the number is different for each " +
                "difficulty\n");
        alert.showAndWait();
    }

    @FXML
    void onClikInfoShop(ActionEvent event) {
        Alert alert= new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("How to play");
        alert.setHeaderText("Shop");
        alert.setContentText("In the shop you can:\n"+
                "-Skip trade: skipping an offert you consider disadvantageous\n"+
                "-Converter to money: convert into coins, for the cost specified, the value of the object you own\n"+
                "-Converter 50%: convert into coins the value of the object you own free of charge");
        alert.showAndWait();
    }

    @FXML
    void onClikInfoTrade(ActionEvent event) {
        Alert alert= new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle("How to play");
        alert.setHeaderText("Trade");
        alert.setContentText("To have a house you have to exchange your object with the\n"+
                "offers, if you don't want to exchange yuor object\n"+
                "you can use the help of the shop");
        alert.showAndWait();
    }

}
