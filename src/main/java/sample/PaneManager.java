package sample;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PaneManager {

    private ArrayList<Pane> pList;

    public PaneManager(){

        pList = new ArrayList<Pane>();
    }

    public void addPaneToManager(Pane p){

        pList.add(p);
    }
    public void setActivePane(Pane p){

        for(Pane pa : pList){

            if(pa.equals(p)) p.setVisible(true);
            else pa.setVisible(false);
        }
    }
}
