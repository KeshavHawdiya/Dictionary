package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class myDictionary extends Application {

    // Variables
    int yLine = 15;

    TextField wordTextField;                                   // create variables-global,,, so we can use anywhere below
    Label meaningLabel;
    Button searchButton;
    DictionaryUsingHashMap dictionaryUsingHashMap;
    ListView<String> suggestedWordList;



    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(300, 300);


        wordTextField = new TextField();
        wordTextField.setPromptText("Please enter a word");
        wordTextField.setTranslateY(yLine);                           // setting distance from above (y-axis)
        wordTextField.setTranslateX(10);                              // setting distance from left (x-axis)
        wordTextField.setOnKeyTyped(new EventHandler<KeyEvent>() {    // provide action to textField, so when we type AERO or AER or AE--- then it suggests Aeroplane as result
            @Override
            public void handle(KeyEvent keyEvent) {
//              meaningLabel.setText(wordTextField.getText());        // writing in textField & displaying at meaningLabel
                // comment out above line and have something extra for suggestion functionality
                String word = wordTextField.getText();
                if(word.isBlank() == false && word.length() >= 3){
                    // fetch suggestions
                    String[] suggestions = dictionaryUsingHashMap.getSuggestions(word);

                    // bind suggestions to the list
                    suggestedWordList.getItems().clear();
                    suggestedWordList.getItems().addAll(suggestions);
                    // now type PRO, you will get suggestions --- project, proficient...
                }
            }
        });

        meaningLabel = new Label("I am the meaning");
        meaningLabel.setTranslateY(yLine+30);
        meaningLabel.setTranslateX(10);

        dictionaryUsingHashMap = new DictionaryUsingHashMap();       // create object of DictUsingHM class,,, so we can use in our (search-button's-action) section

        searchButton = new Button("Search ");
        searchButton.setTranslateY(yLine);
        searchButton.setTranslateX(220);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {    // provide action for our (single) search button
            @Override
            public void handle(ActionEvent actionEvent) {             // getText()-method helps in fetching the written word in our textField
                String word = wordTextField.getText();
                if(word.isBlank()){
                    meaningLabel.setText("Please enter a word !");
                    meaningLabel.setTextFill(Color.RED);
                }
                else{
                    meaningLabel.setText(dictionaryUsingHashMap.findMeaning(word));
                    meaningLabel.setTextFill(Color.BLACK);
                }
            }
        });


        // a new control introduce here, after button, setTranslate, circle, etc...
        suggestedWordList = new ListView<>();
        suggestedWordList.setTranslateY(yLine +70);
        // Build logic to get this from dictionary based on matching words types in search box...
        String[] suggestedList = {"aeroplane", "class", "dictionary", "school", "tuition"};
        suggestedWordList.getItems().addAll(suggestedList);
        // provide action so when words suggested, so we can click them by mouse and can get meaning
        suggestedWordList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedWord = suggestedWordList.getSelectionModel().getSelectedItem();
                meaningLabel.setText(selectedWord);
            }
        });



        root.getChildren().addAll(wordTextField, searchButton, meaningLabel, suggestedWordList);
        //adding all components in our UI(interface),,, Pane(that field)

        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
//      FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("myDictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}