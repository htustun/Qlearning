/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Tugrul
 */
public class main extends dosyaoku {
    
    
    
    
    
    
    
    

    public main() throws IOException {
        super();
        BufferedImage img=ImageIO.read(new File("saved.png"));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(600,600);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
    
    
    public static int size = 60;
    public static int dugumsayisi=4;
    public static int ktablosu[][] = new int[dugumsayisi][dugumsayisi];
    public static void main(String[] args) throws FileNotFoundException, IOException {
            dosyaoku ExcelReader=new dosyaoku();
            Scanner input = new Scanner(System.in);
            String x="input";
            String z=".txt";
            String y="";
       
            y = (JOptionPane.showInputDialog("inputu giriniz."));
            baslangıcdugum=Integer.parseInt(JOptionPane.showInputDialog(null,"baslnagıcdugumu giriniz."));
            hedefdugum=Integer.parseInt(JOptionPane.showInputDialog(null,"hedefdugumu giriniz."));
            iterasyon=Integer.parseInt(JOptionPane.showInputDialog(null,"iterasyon sayisini giriniz."));
          

            ExcelReader.setExcelDostaYolu(x+y+z);
            ExcelReader.ExceldenOku();
            matris hesapla=new matris();
            hesapla.matrishesapla();
            hesapla.QmatrisHesapla();
            hesapla.yolhesapla();
            hesapla.outRtxt(y);
            hesapla.outQtxt(y);
            hesapla.outpathtxt(y);
            hesapla.ciz(y);
            main abc=new main();
            
           
          
           
            
           
        
        
    }
    
}
