package util;

import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public class Algorithm {

    private Path path;
    private String outpath;
    private ExcelReader er;
    private PathReader pr;
    private File log, output;
    private int pivot;

    public Algorithm(File dir) throws IOException {

        //this.er = new ExcelReader(excel, 1);
        //this.pr = new PathReader(dir, "PDF", "ER", "SCAN");
        this.path = dir.toPath();

        er.CreateNameShemeList();
        //createFileStructure(dir);
        //startProcess();
    }
    public Algorithm(ExcelReader er, PathReader pr, String path) throws IOException{

        this.er = er;
        this.pr = pr;
        this.outpath = path;

        er.CreateNameShemeList();
        createFileStructure(path);
    }

    public void createFileStructure(File dir){

        log = new File(dir.getPath()+"/LOG");
        output = new File(dir.getPath()+"/OUTPUT");
        if(!log.exists()) log.mkdir();
        else System.out.println("LOG already exists");
        if(!output.exists()) output.mkdir();
        else System.out.println("OUTPUT already exists");
    }

    private void createFileStructure(String path){

        //log = new File(path + "/LOG");
        output = new File(path + "/OUTPUT");
        //if(!log.exists()) log.mkdir();
        //else System.out.println("LOG already exists");
        if(!output.exists()) output.mkdir();
        else System.out.println("OUTPUT already exists");
    }

    public void startProcess(ProgressBar pb) throws IOException {

        //Calculate ProgressBarSteps
        int iterator = 0;
        double progessvalue = er.getShemes().size() / 100;
        pb.setProgress(0);

        File fcurrent = null;
        for(NameSheme ns : er.getShemes()){

            iterator++;
            fcurrent = pr.getProcessFileById(ns.getIdAsInt());
            if(fcurrent != null){
                try {

                    PdfCopier.copyFile(fcurrent, new File(output.getPath() + "/" + ns.getName()));
                    pb.setProgress(iterator * progessvalue);

                } catch (IOException io){

                    iterator--;
                    pb.setProgress(iterator * progessvalue);
                    System.out.println(fcurrent.getPath() + " seems not copieable ...");
                }
            }
        }
    }
}
