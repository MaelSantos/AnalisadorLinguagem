package br.edu.analisador.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/edu/analisador/view/Analisador.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.setTitle("Analisador");
        stage.centerOnScreen();
        stage.show();

    }
    
    public static void main(String[] args) {
		launch(args);		
	}
}
