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
    private ArrayList<Pair<Integer ,File>> chain;
    private NameSheme nspivot;
    private Integer ipivot;
    private boolean preProcessedExist;
    private boolean algorithmReady;

    public PathReader(File path, String extension, String starts_done, String starts_process){

        algorithmReady = false;
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

        this.chain = new ArrayList<>();
        initChain();

    }

    public boolean isPreProcessedExist(){ return preProcessedExist; }
    public boolean isAlgorithmReady(){ return algorithmReady; }
    public void setAlgorithmReady(boolean b) { this.algorithmReady = b; }

    public File[] getProcces(){ return process; }
    public File getProcessFileById(int id){

        for(Pair<Integer, File> p : chain){
            if(p.getKey() == id){
                return p.getValue();
            }
        }
        //NoSuchFileinProcessQueue
        return null;
    }

    public int getPivotIdAsInt(){
        return nspivot.getIdAsInt();
    }
    public int getIntPivot(){
        return ipivot;
    }
    private void initChain(){

        for(int i = 0; i < process.length; i++){

            StringTokenizer tok = new StringTokenizer(process[i].getName(), "N");
            tok.nextToken();
            StringTokenizer tok2 = new StringTokenizer(tok.nextToken(), ".");
            chain.add(new Pair<Integer, File>(Integer.parseInt(tok2.nextToken()), process[i]));
        }
        if(done != null && done.length > 0 && !preProcessedExist){

            this.nspivot = new NameSheme(done[done.length - 1].getName());
            preProcessedExist = true;
            algorithmReady = true;
        }
        if (chain != null && chain.size() > 0 && !preProcessedExist) {

            this.ipivot = chain.get(0).getKey() - 1;
            preProcessedExist = false;
            algorithmReady = true;
        }
    }

}
