import home.DriverCode.SloganMaker;
import home.DriverCode.Token;
import home.DriverCode.TweetParser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Map;

public class MainGUI  extends Application {

    private TextField acronym_input = new TextField();
    private Label solutionLabel = new Label();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Map<Token, ArrayList<Token>> word_dict = TweetParser.parseTrumpTweets("trumptweets-1515775693.tweets.csv");


        Label enterLabel = new Label("Enter an acronym");
        Button go = new Button("GO");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(50);
        gridPane.setVgap(50);


        gridPane.add(enterLabel, 1, 1);
        gridPane.add(acronym_input, 2, 1);
        gridPane.add(go, 2, 2);
        gridPane.add(solutionLabel, 2, 3);



        go.setOnAction(e  -> {
            try {
                driverCode(word_dict);
            }
            catch (Exception ex){
            }
                });


        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    private void driverCode(Map<Token, ArrayList<Token>> word_dict) throws Exception{

        SloganMaker maker = new SloganMaker(word_dict);
        String solution = maker.getSlogan(acronym_input.getText()).toString();
        solutionLabel.setText(solution);

    }

}
