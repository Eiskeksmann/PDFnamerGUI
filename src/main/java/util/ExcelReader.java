package util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {


    private File path; //File we try to read from
    private ArrayList<NameSheme> shemes; //NameList we get after we successfully read from File

    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private Cell cell;
    private Row row;
    private CellReference ref;
    private int column;

    public ExcelReader(File path, String tab,String col) throws IOException {


        this.path = path;
        this.wb = new XSSFWorkbook(new FileInputStream(this.path));
        this.sheet = wb.getSheet(tab);
        this.column = getRowIndexByName(col);
        if(this.column == -1) return;
        this.shemes = new ArrayList<NameSheme>();
    }

    public int getColumn(){ return column; }

    private int getRowIndexByName(String column){

        row = sheet.getRow(0);
        for(Cell c : row){

            switch(c.getCellType()){

                case STRING:
                    if(c.getStringCellValue().equals(column)) return c.getAddress().getColumn();
                    else if(c.getStringCellValue().equals("")) return -1;
                    break;
                default:
                    break;
            }
        }
        return -1;
    }

    public boolean CreateNameShemeList(){

        boolean condition = false;
        if(sheet != null) {
            for (int i = 2; !condition; i++) {

                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(column);
                } else return false;
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                } else return false;

                if (cell.getStringCellValue().equals("")) {
                    condition = true;
                    return true;
                } else shemes.add(new NameSheme(cell.getStringCellValue()));
            }
        } else return false;
        return false;
    }
    public NameSheme getPivot(){
        return shemes.get(0);
    }

    public ArrayList<NameSheme> getShemes(){
        return shemes;
    }
    public ArrayList<NameSheme> trimShemes(int pivot){

        int old = shemes.size();
        for(NameSheme ns : shemes)
            if(ns.getIdAsInt() <= pivot)
                shemes.remove(ns);

        int diff = old - shemes.size();
        System.out.println("Removed " + diff + " Items from NameShemeList");
        return shemes;
    }

}
