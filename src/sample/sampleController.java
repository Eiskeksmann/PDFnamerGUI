package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class sampleController implements Initializable {

    @FXML private Button cmd_scan, cmd_sheet, cmd_algorithm, cmd_settings;
    @FXML private Pane pan_scan, pan_sheet, pan_algorithm, pan_settings;

    private PaneManager pm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pm = new PaneManager();
        pm.addPaneToManager(pan_scan);
        pm.addPaneToManager(pan_sheet);
        pm.addPaneToManager(pan_algorithm);
        pm.addPaneToManager(pan_settings);
        pm.setActivePane(pan_scan);
    }

    public void clickedCmdScan(){

        pm.setActivePane(pan_scan);
    }
    public void clickedCmdSheet(){

        pm.setActivePane(pan_sheet);
    }
    public void clickedCmdAlgorithm(){

        pm.setActivePane(pan_algorithm);
    }
    public void clickedCmdSettings(){

        pm.setActivePane(pan_settings);
    }
}
