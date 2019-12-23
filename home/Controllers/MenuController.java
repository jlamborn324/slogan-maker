package home.Controllers;

import java.net.URL;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

import home.DriverCode.SloganMaker;
import home.DriverCode.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MenuController implements Initializable{

    protected static Map<Token, PriorityQueue<Token>> word_dict;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField acronymField;

    @FXML
    private Button goBtn;

    @FXML
    private TextArea solutionTextfield;


    public void handleGoBtnAction(ActionEvent event) {
        SloganMaker maker = new SloganMaker(word_dict);

        if (!acronymField.getText().matches("[a-zA-Z]+")) {
            acronymField.setText("");
            acronymField.setPromptText("Please only use letters");
        }
        else {
            if (!maker.getSlogan(acronymField.getText(), new PriorityQueue<>(word_dict.keySet()))) {
                solutionTextfield.setText("No solution could be found");

            }
            else{
                String solution = maker.solution.toString();
                solutionTextfield.setText(solution);
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
