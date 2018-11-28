/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melissageorge;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import melissageorge.view_controller.AddPartController;
import melissageorge.view_controller.ModifyPartController;
import melissageorge.view_controller.MainController;
import melissageorge.view_controller.AddProductController;
/**
 *
 * @author melissageorge
 */
public class MainApp extends Application {
    FXMLLoader loader = new FXMLLoader();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view_controller/Main.fxml"));


        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();
//        AnchorPane root;
//        try {
//        loader.setLocation(MainApp.class.getResource("view_controller/Main.fxml"));
//        root = (AnchorPane) loader.load();
//
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Inventory Management");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
