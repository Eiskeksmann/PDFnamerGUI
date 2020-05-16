package sample;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class sampleController implements Initializable {

    //Main View
    @FXML private Button cmd_scan, cmd_sheet, cmd_algorithm, cmd_settings;
    @FXML private Pane pan_scan, pan_sheet, pan_algorithm, pan_settings;

    //Scan Pane
    @FXML private Button cmd_scan_path;
    @FXML private TextField txt_scan_path;
    @FXML private Label lbl_lst_scan;
    @FXML private ListView lst_scan;

    //Sheet Pane
    @FXML private Button cmd_sheet_path;
    @FXML private TextField txt_sheet_path;
    @FXML private Label lbl_sheet_scan;
    @FXML private ListView lst_sheet;

    //Algorithm Pane
    @FXML private Button cmd_run_algorithm;
    @FXML private FontAwesomeIconView icon_scan, icon_sheet, icon_output;
    @FXML private ProgressBar pbar_algorithm;

    //Settings Pane
    @FXML private Button cmd_settings_output_path, cmd_settings_settings_path, cmd_settings_bill_type,
            cmd_settings_log_path, cmd_settings_confirm;
    @FXML private TextField txt_settings_output_path, txt_settings_log_path, txt_settings_settings_path;
    @FXML private ChoiceBox com_settings_bill_type;


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
