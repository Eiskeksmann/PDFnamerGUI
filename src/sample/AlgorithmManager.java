package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;

public class AlgorithmManager {

    private Button cmd_run_algorithm;
    private FontAwesomeIconView icon_scan;
    private FontAwesomeIconView icon_sheet;
    private FontAwesomeIconView icon_output;

    private boolean scan_condition;
    private boolean sheet_condition;
    private boolean output_condition;

    private boolean permission;

    public AlgorithmManager(Button cmd_run_algorithm, FontAwesomeIconView icon_scan, FontAwesomeIconView icon_sheet,
                            FontAwesomeIconView icon_output, boolean output_condition){

        scan_condition = false;
        sheet_condition = false;
        this.cmd_run_algorithm = cmd_run_algorithm;
        this.icon_scan = icon_scan;
        this.icon_sheet = icon_sheet;
        this.icon_output = icon_output;
        this.output_condition = output_condition;

        permission = false;
    }
    public AlgorithmManager(Button cmd_run_algorithm, FontAwesomeIconView icon_scan, FontAwesomeIconView icon_sheet,
                            FontAwesomeIconView icon_output){

        scan_condition = false;
        sheet_condition = false;
        this.cmd_run_algorithm = cmd_run_algorithm;
        this.icon_scan = icon_scan;
        this.icon_sheet = icon_sheet;
        this.icon_output = icon_output;
        this.output_condition = false;

        permission = false;
    }
    public void setScan_condition(boolean b){
        scan_condition = b;
        if(scan_condition) icon_scan.setIcon(FontAwesomeIcon.CHECK);
        else icon_scan.setIcon(FontAwesomeIcon.TIMES);
        runChecker();
    }
    public void setSheet_condition(boolean b){
        sheet_condition = b;
        if(sheet_condition) icon_sheet.setIcon(FontAwesomeIcon.CHECK);
        else icon_sheet.setIcon(FontAwesomeIcon.TIMES);
        runChecker();
    }
    public void setOutput_condition(boolean b){
        output_condition = b;
        if(output_condition) icon_output.setIcon(FontAwesomeIcon.CHECK);
        else icon_output.setIcon(FontAwesomeIcon.TIMES);
        runChecker();
    }

    private void runChecker(){

        if(scan_condition && sheet_condition && output_condition) {

            cmd_run_algorithm.setDisable(false);
            permission = true;
        }
        else {

            cmd_run_algorithm.setDisable(true);
            permission = false;
        }
    }
    public boolean getPermission(){
        return permission;
    }
    public boolean getScanCondition(){ return scan_condition; }
    public boolean getSheetCondition(){ return sheet_condition; }
    public boolean getOutputCondition(){ return output_condition; }

}
