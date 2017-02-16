/*
 * Developed By
 * Alvin Chau 2/15/2017
 * Andy Phan
 * 
 */

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;



public class SongLib extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        SplitPane root = (SplitPane)loader.load();

      CustomController control = loader.getController();
      control.start(primaryStage);

        primaryStage.setTitle("Song Library");

        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}

