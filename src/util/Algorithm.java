package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


public class Algorithm {

    private Path path;
    private ExcelReader er;
    private PathReader pr;
    private File log, output;
    private int pivot;

    public Algorithm(File excel, File dir) throws IOException {

        this.er = new ExcelReader(excel, 1);
        this.pr = new PathReader(dir, "PDF", "ER", "SCAN");
        this.path = dir.toPath();

        er.CreateNameShemeList();
        //createFileStructure(dir);
        //startProcess();
    }

    public void createFileStructure(File dir){

        log = new File(dir.getPath()+"/LOG");
        output = new File(dir.getPath()+"/OUTPUT");
        if(!log.exists()) log.mkdir();
        else System.out.println("LOG already exists");
        if(!output.exists()) output.mkdir();
        else System.out.println("OUTPUT already exists");
    }

    public void startProcess() throws IOException {

        File fcurrent = null;
        for(NameSheme ns : er.trimShemes(pr.getPivotIdAsInt())){

            fcurrent = pr.getProcessFileById(ns.getIdAsInt());
            if(fcurrent != null){
                try {
                    PdfCopier.copyFile(fcurrent, new File(output.getPath() + "/" + ns.getName()));
                } catch (IOException io){

                    System.out.println(fcurrent.getPath() + " seems not copieable ...");
                }
            }
        }
    }
}
