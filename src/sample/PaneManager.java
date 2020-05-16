package sample;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;

public class PaneManager {

    private ArrayList<Pane> pList;

    public PaneManager(){

        pList = new ArrayList<>();
    }

    public void addPaneToManager(Pane p){

        pList.add(p);
    }
    public void setActivePane(Pane p){

        for(Pane pp : pList){

            if(pp.equals(p)) p.setVisible(true);
            else pp.setVisible(false);
        }
    }
}
