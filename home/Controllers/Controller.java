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
import javafx.stage.Stage;


public class Controller implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button parseBtn;

    @FXML

    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleParseBtnAction(javafx.event.ActionEvent actionEvent) throws Exception{
        Stage stage = (Stage) parseBtn.getScene().getWindow();
        parseBtn.setText("Loading...");


        MenuController.word_dict = TweetParser.parseTrumpTweets("trumptweets-1515775693.tweets.csv");

        stage.close();

        loadStage("../FXML/Menu.fxml");
    }
}
