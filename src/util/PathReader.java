package util;

import javafx.util.Pair;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PathReader {

    private File[] done;
    private File[] process;
    private File fpivot;
    private ArrayList<Pair<Integer ,File>> chain;
    private NameSheme nspivot;

    public PathReader(File path, String extension, String starts_done, String starts_process){

        done = path.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(starts_done) && name.endsWith(extension);
            }
        });
        process = path.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(starts_process) && name.endsWith(extension);
            }
        });
        if(done != null && done.length > 0) this.fpivot = done[done.length - 1];
        else return;
        this.nspivot = new NameSheme(fpivot.getName());
        this.chain = new ArrayList<>();
        initChain();
    }
    public File getPivot(){

        return fpivot;
    }
    public File[] getProcces(){ return process; }
    public int getPivotIdAsInt(){
        return nspivot.getIdAsInt();
    }
    private void initChain(){

        for(int i = 0; i < process.length; i++){

            StringTokenizer tok = new StringTokenizer(process[i].getName(), "N");
            tok.nextToken();
            StringTokenizer tok2 = new StringTokenizer(tok.nextToken(), ".");
            chain.add(new Pair<Integer, File>(Integer.parseInt(tok2.nextToken()), process[i]));
        }
    }
    public File getProcessFileById(int id){

        for(Pair<Integer, File> p : chain){
            if(p.getKey() == id){
                 return p.getValue();
             }
        }
        //NoSuchFileinProcessQueue
        return null;
    }
}
