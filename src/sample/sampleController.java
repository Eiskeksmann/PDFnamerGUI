package sample;


import com.sun.glass.ui.CommonDialogs;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Algorithm;
import util.ExcelReader;
import util.NameSheme;
import util.PathReader;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class sampleController implements Initializable {

    //Main View
    @FXML private Button cmd_scan, cmd_sheet, cmd_algorithm, cmd_settings;
    @FXML private Pane pan_scan, pan_sheet, pan_algorithm, pan_settings;

    //Scan Pane
    @FXML private CheckBox cb_scan_setouput;
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
    @FXML private ChoiceBox<String> com_settings_bill_type;


    private PaneManager pm;
    private AlgorithmManager am;
    private Stage s;
    private String scan_path, sheet_path, output_path, settings_path;
    private Algorithm algorithm;
    private ExcelReader er;
    private PathReader pr;
    private FileChooser fc;
    private DirectoryChooser dc;

    public void setStage(Stage s){
        this.s = s;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Init Controller Tools
        fc = new FileChooser();
        dc = new DirectoryChooser();
        am = new AlgorithmManager(cmd_run_algorithm, icon_scan, icon_sheet, icon_output);

        //Init PaneManager
        pm = new PaneManager();
        pm.addPaneToManager(pan_scan);
        pm.addPaneToManager(pan_sheet);
        pm.addPaneToManager(pan_algorithm);
        pm.addPaneToManager(pan_settings);
        pm.setActivePane(pan_scan);

        //Init Combo Box
        com_settings_bill_type.getItems().add("ER");
        com_settings_bill_type.getItems().add("KK");
        com_settings_bill_type.setValue(com_settings_bill_type.getItems().get(0));

        //Init Checkbox
        cb_scan_setouput.setSelected(false);

        //Init Settings
        txt_settings_settings_path.setPromptText("-optional-");

        //Init Button Structure
        cmd_sheet_path.setDisable(true);

    }

    //HBox Top Nav
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

    //Scan Pane
    public void clickedCmdScanPath(){

        File f = dc.showDialog(s);
        if(f != null){

            scan_path = f.getPath();
            txt_scan_path.setText(scan_path);
            pr = new PathReader(f,"PDF", com_settings_bill_type.getValue(), "SCAN");
            if(pr.getProcces() != null && pr.getProcces().length > 0){

                for(File pdf : pr.getProcces()){
                    lst_scan.getItems().add(pdf.getName());
                }
                am.setScan_condition(true);
                cmd_sheet_path.setDisable(false);
            } else {

                scan_path = "";
                txt_scan_path.setText("Pfad enthält nur ungültige Dokumente");
                lst_scan.getItems().clear();
                pr = null;
                am.setScan_condition(false);
            }
        }
    }

    //Sheet Pane
    public void clickedCmdSheetPath() throws IOException {

        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files","*.xlsx"));
        File f = fc.showOpenDialog(s);
        if(f != null){

            sheet_path = f.getPath();
            txt_sheet_path.setText(sheet_path);
            er = new ExcelReader(f,com_settings_bill_type.getValue(),"File");

            if(er.getColumn() != -1) {

                if(er.CreateNameShemeList()) {
                    if (pr.isPreProcessedExist()) {

                        er.trimShemes(pr.getPivotIdAsInt());
                    } else if (!pr.isPreProcessedExist()) {

                        er.trimShemes(pr.getIntPivot());
                    }
                    lst_sheet.getItems().clear();
                    for (NameSheme ns : er.getShemes()) {
                        lst_sheet.getItems().add(ns.getName());
                    }
                    if(lst_sheet.getItems().size() > 0) am.setSheet_condition(true);
                    else am.setSheet_condition(false); return;
                } else am.setSheet_condition(false); return;
            } else am.setSheet_condition(false); return;
        } else am.setSheet_condition(false); return;

    }
}
