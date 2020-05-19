package util;

import java.util.StringTokenizer;

public class NameSheme {

    private int length;
    private String name;
    private String type;
    private String month;
    private String id;
    private String number;
    private String fileextension;

    public NameSheme(String name){

        this.name = name;
        StringTokenizer tok = new StringTokenizer(this.name, "-");
        this.length = tok.countTokens();
        this.type = tok.nextToken();
        this.month = tok.nextToken();
        this.id = tok.nextToken();
        String side = tok.nextToken();
        StringTokenizer sidetok = new StringTokenizer(side, ".");
        number = sidetok.nextToken();
        fileextension = sidetok.nextToken();
    }
    public String getName(){ return name; }
    public String getType(){ return type; }
    public String getMonth(){ return month; }
    public String getId(){ return id; }
    public String getNumber() { return number;}
    public String getFileextension() { return fileextension; }
    public int getLength() { return length; }

    public boolean equals(NameSheme ns){

        if(ns.getFileextension().equals(this.fileextension) &&
            ns.getId().equals(this.id) && ns.getMonth().equals(this.month) &&
                ns.getNumber().equals(this.number) && ns.getType().equals(this.type)){
            return true;
        }
        return false;
    }
    public boolean compareID(String id){

        if(id.equals(this.id)) return true;
        return false;
    }
    public int getIdAsInt(){
        return Integer.parseInt(id);
    }

}
