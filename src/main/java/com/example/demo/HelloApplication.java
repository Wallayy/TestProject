package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    /*
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
     */
    public void start(Stage primaryStage) {
        System.out.println("Program started!");
        primaryStage.setTitle("Button thingy");
        Button btn = new Button();
        btn.setText("This is a button!");

        // create a label
        Label label = new Label("Caution Message here!");
        label.setAlignment(Pos.CENTER); // Center the text on the label

        // create a popup
        Popup popup = new Popup();

        // set background
        label.setStyle(" -fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

        // set size of label
        label.setMinWidth(500);
        label.setMinHeight(75);

        // Make the popup draggable
        final Delta dragDelta = new Delta();
        label.setOnMousePressed(mouseEvent -> {
            dragDelta.x = popup.getX() - mouseEvent.getScreenX();
            dragDelta.y = popup.getY() - mouseEvent.getScreenY();
        });

        label.setOnMouseDragged(mouseEvent -> {
            popup.setX(mouseEvent.getScreenX() + dragDelta.x);
            popup.setY(mouseEvent.getScreenY() + dragDelta.y);
        });

        // action event
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        if (!popup.isShowing())
                            popup.show(primaryStage);
                        else
                            popup.hide();
                    }
                };

        btn.setOnAction(event);

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    // Delta class to store x and y coordinates
    private static class Delta {
        double x, y;
    }
    public static void main(String[] args) {
        launch(args);
    }
}