
package yazlab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Tugrul
 */
public class dosyaoku {
    private String DosyaYolu;
    public static int dugumsayisi;
    public static int baslangıcdugum;
    public static int hedefdugum;
    public static int iterasyon;
    public static  List<List<String>> list = new ArrayList<List<String>>();
    public dosyaoku(){
    //Boş kurucu ...
    }
    public void setExcelDostaYolu(String DosyaYolum){
        this.DosyaYolu=DosyaYolum;
    }
     public void ExceldenOku() throws IOException{
         
     int i=0;
     Scanner dosya = new Scanner(new File(DosyaYolu));
    
     while (dosya.hasNext()) {
            String line = dosya.next();
            List<String> temp = new ArrayList<String>();
            temp = Arrays.asList(line.split(","));
            list.add(temp);
            i++;
            dugumsayisi=i;
            System.out.println(list.get(i));
           
        }
         
         
     }
     
}
