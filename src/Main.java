import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 150;
    private static final int BUTTONS_SPACING = 10;
    private static final int ITENS_SPACING = 20;

    private List<String> foodList = new ArrayList<>();
    private List<String> kindFoodList = new ArrayList<>();
    private List<String> adjectiveFoodList = new ArrayList<>();

    private Stage window;
    private Scene scene1, scene2, scene3, scene4, scene5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initConfigWindow(primaryStage);
    }

    private void buildScene5() {
        //itens
        Label label = new Label("I accepted again!");

        Button buttonOK = new Button("OK");
        buttonOK.setOnAction(e -> window.setScene(scene1));

        VBox layoutScene = new VBox(30);
        layoutScene.getChildren().addAll(label, buttonOK);
        layoutScene.setAlignment(Pos.CENTER);
        scene5 = new Scene(layoutScene, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void buildScene4(String food) {
        //itens
        Label label = new Label(food + " is _____ but not " + foodList.get(foodList.size() - 1));
        TextField adjectiveFoodInput = new TextField();

        Button buttonOK = new Button("OK");
        buttonOK.setOnAction(e -> {
            String adjective = adjectiveFoodInput.getText();
            adjectiveFoodInput.setText("");
            if (adjective == null || adjective.trim().isEmpty()) {
                e.consume();
                AlertBox.display("Atention!", "Type a answer before to click in OK.");
            } else {
                adjectiveFoodList.add(adjective);
                window.setScene(scene1);
            }
        });
        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(e -> {
            adjectiveFoodInput.setText("");
            window.setScene(scene1);
        });

        HBox layoutButtons = new HBox(BUTTONS_SPACING);
        layoutButtons.getChildren().add(buttonOK);
        layoutButtons.getChildren().add(buttonCancel);
        layoutButtons.setAlignment(Pos.CENTER);
        //layout scene
        VBox layoutScene = new VBox(ITENS_SPACING);
        layoutScene.getChildren().add(label);
        layoutScene.getChildren().add(adjectiveFoodInput);
        layoutScene.getChildren().add(layoutButtons);
        layoutScene.setAlignment(Pos.CENTER);

        scene4 = new Scene(layoutScene, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void buildScene3() {
        //itens
        Label foodLabel = new Label("What food did you think?");
        TextField foodInput = new TextField();

        Button buttonOK = new Button("OK");
        buttonOK.setOnAction(e -> {
            String food = foodInput.getText();
            foodInput.setText("");
            if (food == null || food.trim().isEmpty()) {
                e.consume();
                AlertBox.display("Atention!", "Type a answer before to click in OK.");
            } else {
                buildScene4(food);
                foodList.add(food);
                window.setScene(scene4);
            }
        });
        Button buttonCancel = new Button("Cancel");
        buttonCancel.setOnAction(e -> {
            foodInput.setText("");
            window.setScene(scene1);
        });

        HBox layoutButtons = new HBox(BUTTONS_SPACING);
        layoutButtons.getChildren().add(buttonOK);
        layoutButtons.getChildren().add(buttonCancel);
        layoutButtons.setAlignment(Pos.CENTER);

        //layout scene
        VBox layoutScene = new VBox(ITENS_SPACING);
        layoutScene.getChildren().add(foodLabel);
        layoutScene.getChildren().add(foodInput);
        layoutScene.getChildren().add(layoutButtons);
        layoutScene.setAlignment(Pos.CENTER);

        scene3 = new Scene(layoutScene, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void buildScene2(int index, String option) {
        //itens
        Label label = new Label("The dish you thought is " + option + "?");
        Button buttonYes = new Button("Yes");
        buttonYes.setOnAction(e -> {
            buildScene5();
            window.setScene(scene5);
        });
        Button buttonNo = new Button("No");
        buttonNo.setOnAction(e -> {
            window.setScene(scene3);
        });

        HBox layoutButtons = new HBox(BUTTONS_SPACING);
        layoutButtons.getChildren().add(buttonYes);
        layoutButtons.getChildren().add(buttonNo);
        layoutButtons.setAlignment(Pos.CENTER);

        //layout scene
        VBox layoutScene = new VBox(ITENS_SPACING);
        layoutScene.getChildren().add(label);
        layoutScene.getChildren().add(layoutButtons);
        layoutScene.setAlignment(Pos.CENTER);

        scene2 = new Scene(layoutScene, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void buildScene1(int index, String kindFood) {
        buildScene2(index, kindFood);

        //itens
        Label label1 = new Label("Welcome to GourmetGame!");
        Label label2 = new Label("Think of a food you like...");

        Button buttonOK = new Button("OK");
        buttonOK.setOnAction(e -> window.setScene(scene2));

        VBox layoutScene = new VBox(30);
        layoutScene.getChildren().addAll(label1, label2, buttonOK);
        layoutScene.setAlignment(Pos.CENTER);
        scene1 = new Scene(layoutScene, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void initConfigWindow(Stage primaryStage) {
        //storage

        kindFoodList.add("Pasta");

        foodList.add("Lasagna");
        foodList.add("Chocolate Cake");

        //setup
        window = primaryStage;
        window.setTitle("Gourmet Game");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        buildScene1(0, kindFoodList.get(0));
        buildScene3();
        buildScene5();

        //Initial Scene
        window.setScene(scene1);
        window.show();
    }

    private void closeProgram() {
        Boolean asnwer = ConfirmBox.display("Close GourmetGame", "Sure do you want ot exit?");
        if (asnwer) {
            window.close();
        }
    }

}
