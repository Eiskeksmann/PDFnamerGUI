package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.print.DocFlavor;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //System.out.println(getClass().getResource("/sample.fxml"));
        // -> file:/C:/Users/mellawa/IdeaProjects/PDFnamerGUI/target/classes/sample.fxml

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = (Parent) loader.load();
        root.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());
        primaryStage.setTitle("PDFnamer");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("file:res/pdfnamer.png"));
        primaryStage.show();
        sampleController sctrl = (sampleController) loader.getController();
        sctrl.setStage(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
