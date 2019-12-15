package home.Controllers;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import home.DriverCode.TweetParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button parseBtn;


    private void loadStage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/Menu.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleParseBtnAction(javafx.event.ActionEvent actionEvent) throws Exception{

        MenuController.word_dict = TweetParser.parseTrumpTweets("trumptweets-1515775693.tweets.csv");

        Stage stage = (Stage) parseBtn.getScene().getWindow();
        stage.close();
        loadStage();
    }
}
