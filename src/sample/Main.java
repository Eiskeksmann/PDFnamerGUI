package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        primaryStage.setTitle("PDFnamer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        sampleController sctrl = (sampleController) loader.getController();
        sctrl.setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
