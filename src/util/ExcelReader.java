package util;

import org.apache.poi.ss.usermodel.Cell;
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

    public ExcelReader(File path, int i) throws IOException {

        this.path = path;
        this.wb = new XSSFWorkbook(new FileInputStream(this.path));
        this.sheet = wb.getSheetAt(i);
        this.shemes = new ArrayList<>();
    }

    public void CreateNameShemeList(){

        boolean condition = false;
        for(int i = 2; !condition; i++){

            ref = new CellReference("H" + i);
            if(sheet.getRow(ref.getRow()) != null){

                row = sheet.getRow(ref.getRow());
                if(row.getCell(ref.getCol()) != null){

                    cell = row.getCell(ref.getCol());
                }
            } else {

                condition = true;
                System.out.println(i - 2 + " | Elements found");
                break;
            }
            switch(cell.getCellType()){

                case STRING:
                    shemes.add(new NameSheme(cell.getStringCellValue()));
                    break;
                default:
                    System.out.println("CELL: [" + cell.getAddress().getColumn() + "] [" +
                            cell.getAddress().getRow() + "] is not correct formatted!");
            }
        }
    }

    public ArrayList<NameSheme> getShemes(){
        return shemes;
    }
    public ArrayList<NameSheme> trimShemes(int pivot){

        shemes.removeIf(ns -> ns.getIdAsInt() <= pivot);
        return shemes;
    }

}
