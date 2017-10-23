/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import static yazlab2.main.size;


/**
 *
 * @author Tugrul
 */
public class matris extends dosyaoku {
        
        public matris(){
            super();
        }
        List<String> path = new ArrayList<String>();
         static public Random rnd = new Random();
         public static int  durum;
         public static double gamma=0.8;
         public static int ktablosu[][] = new int[dugumsayisi][dugumsayisi];
         public static int Rmatris[][] = new int[dugumsayisi][dugumsayisi];
         public static double Qmatris[][] = new double [dugumsayisi][dugumsayisi];
         
         
         
         public static void matrishesapla(){
             
            
             //ktablosuna -1 atandı
             for (int i = 0; i < dugumsayisi; i++) {
                 for (int j = 0; j <dugumsayisi; j++) {
                     ktablosu[i][j]=-1;
                 }
             }
             
            
             //Rmatris -1 atandı
             for (int i = 0; i < dugumsayisi; i++) {
                 for (int j = 0; j <dugumsayisi; j++) {
                     Rmatris[i][j]=-1;
                 }
             }
             //qmatris 0 atandı
             for (int i = 0; i < dugumsayisi; i++) {
                 for (int j = 0; j <dugumsayisi; j++) {
                    Qmatris[i][j]=0;
                 }
             }
             //list e atadıgımız stringleri integer cevirip  ktablosuna ataldık
             for(int j=0 ; j<list.size() ; j++){
            for (int k = 0; k <list.get(j).size(); k++) {
                ktablosu[j][k]=Integer.parseInt(list.get(j).get(k));
                
            }
            
         
        }
             
              for(int k=0 ; k<dugumsayisi ; k++){
            for (int j = 0; j <dugumsayisi; j++) {
                int gecici=0;
                gecici=ktablosu[k][j];
                if (gecici==hedefdugum) {
                    Rmatris[k][gecici]=100;
                }
                else if(gecici>=0){
                    Rmatris[k][gecici]=0;
                }
                else {
                    Rmatris[hedefdugum][hedefdugum]=100;
                }
                
                 
                
            }
        }
          
              System.out.println("-------------------R-----------------");
         for(int j=0 ; j<dugumsayisi ; j++){
            for (int k = 0; k <dugumsayisi; k++) {
                System.out.print(" "+Rmatris[j][k]+" ");
                
            }
               System.out.println();
        }
           
              
            
         }
         
     public static int getRandomAction(final int upperBound)
    {
        int action = 0;
        boolean choiceIsValid = false;
        
        while(choiceIsValid == false)
        {
            action = new Random().nextInt(upperBound);
            if(Rmatris[durum][action] > -1){
                choiceIsValid = true;
            }
        }

        return action;
    }
         
   private static double maximum(final int State, final boolean ReturnIndexOnly)
    {
    
        int winner = 0;
        boolean foundNewWinner = false;
        boolean done = false;

        while(!done)
        {
            foundNewWinner = false;
            for(int i = 0; i < dugumsayisi; i++)
            {
                if(i != winner){             // Avoid self-comparison.
                    if(Qmatris[State][i] > Qmatris[State][winner]){
                        winner = i;
                        foundNewWinner = true;
                    }
                }
            }

            if(foundNewWinner == false){
                done = true;
            }
        }

        if(ReturnIndexOnly == true){
            return winner;
        }else{
            return  Qmatris[State][winner];
        }
    }
    
    
         
    void QmatrisHesapla(){
        int hamle=0;
        int ilerideger=0;
      
       
         for (int i = 0; i <iterasyon; i++){
             durum = rnd.nextInt(dugumsayisi);
          do{
              
                hamle=getRandomAction(dugumsayisi);
                
                
                
               
                        Qmatris[durum][hamle]=(Rmatris[durum][hamle])+(gamma*maximum(hamle, false));
                       
                       
                        durum=hamle;
                        
            }while(durum!=hedefdugum);
                 
            
        }
         System.out.println("----------------Q--------------------");
        DecimalFormat deci = new DecimalFormat("0.0");
         
          for(int j=0 ; j<dugumsayisi ; j++){
            for (int k = 0; k <dugumsayisi; k++) {
                System.out.print(" "+deci.format(Qmatris[j][k])+" ");
               
            }
               System.out.println();
        }
        
    }
    void yolhesapla(){
        
        System.out.println("--------------en kısa yol---------------");
        while (baslangıcdugum!=hedefdugum) {
           double x=maximum(baslangıcdugum, true);
           path.add(String.valueOf(baslangıcdugum));
           System.out.print(baslangıcdugum+"-");
            baslangıcdugum=(int) x;
          
            
        }
         path.add(String.valueOf(hedefdugum));
         System.out.print(hedefdugum);
         System.out.println();
         System.out.println();


}
  void outRtxt(String x) throws IOException{
      BufferedWriter bw= new BufferedWriter(new FileWriter("outQ"+x+".txt"));

DecimalFormat deci = new DecimalFormat("0.0");
      bw.write("Q 3000 İterasyon sonra");
      bw.newLine();
        for(int j=0 ; j<dugumsayisi ; j++){
            for (int k = 0; k <dugumsayisi; k++){
                bw.write(String.valueOf(deci.format(Qmatris[j][k])+"  "));
                
            }
                bw.newLine();
        }
        
        bw.flush();
  }
   void outQtxt(String x) throws IOException{
      BufferedWriter bw= new BufferedWriter(new FileWriter("outR"+x+".txt"));
      DecimalFormat deci = new DecimalFormat("0.0");
     
      bw.write("R--------------------------------");
      bw.newLine();
        for(int j=0 ; j<dugumsayisi ; j++){
            for (int k = 0; k <dugumsayisi; k++){
                bw.write(String.valueOf(deci.format(Rmatris[j][k])+"  "));
            }
                bw.newLine();
        }
        
         
        
        bw.flush();
  }
   void outpathtxt(String x) throws IOException{
      BufferedWriter bw= new BufferedWriter(new FileWriter("outpath"+x+".txt"));
      bw.write("Path-------------");      
        bw.newLine();
        for (int i = 0; i <path.size(); i++) {
                       
            bw.write(path.get(i));
            if(i<path.size()-1){
                
                bw.write("-");
                
            }
          
      }
        
        bw.flush();
  }
   public void ciz(String y) throws IOException{
        Scanner dosya = new Scanner(new File("input"+y+".txt"));

        List<List<String>> list = new ArrayList<List<String>>();

        while (dosya.hasNext()) {
            List<String> temp = new ArrayList<String>();

            String line = dosya.next();

            temp = Arrays.asList(line.split(","));
            list.add(temp);
            
        }
        int blockNum = (int)Math.sqrt(list.size());

        int blockSize = size / blockNum ;
        
        size += blockNum+ 1;

    
        String[][] image = new String [size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                image[i][j] = " ";
            }
            
        }

        for (int j = 0; j < size; j++) {
            image[0][j] = "-"; //sütün
            image[size-1][j] = "-";//sütün
            image[j][0] = "|";//satır
            image[j][size-1] = "|";//satır


        }

        for(int j = 1; j < blockNum; j++){
            for(int k = 1; k < size-1; k++){
                image[k][((blockSize)*j)+j] = "|";//Satır
                image[((blockSize)*j)+j][k] = "-";//Sütün

            }
        }
        
        int fark=0;
        int asd=0;
       
        for (int dugumsayısı = 0; dugumsayısı < list.size() ; dugumsayısı++) {          
                      
            for (int j = 0; j < list.get(dugumsayısı).size(); j++) {
                
                asd=Integer.parseInt(list.get(dugumsayısı).get(j).replace("\uFEFF", "").trim());
                fark=(dugumsayısı)-asd;
                               
                
                for (int i = 1; i < blockSize+1; i++) {
                    
                    if(fark==-1){
                        
                        image[i+(blockSize*(dugumsayısı/blockNum))+(dugumsayısı/blockNum)]
                                [(blockSize*((dugumsayısı%blockNum)+1))+((dugumsayısı%blockNum)+1)]=" ";
                    }
                    else if (fark == -blockNum) {
                        
                        image[(blockSize*((dugumsayısı/blockNum)+1))+((dugumsayısı/blockNum)+1)]
                                [i+(blockSize*((dugumsayısı%blockNum)))+(dugumsayısı%blockNum)]=" ";                     
                    }
                }
            }                      
        }
        
        
        
        
        
       
        
        for (int i = 0; i < path.size()-1; i++) {
            
            int ilk= Integer.parseInt(path.get(i));
            int ileri=Integer.parseInt(path.get(i+1));
            int fark1 = ilk-ileri;
            
            
            
            if(fark1==-1){
                
                
                for (int j = ((blockSize/2)+(blockSize*(ilk%blockNum))) ; j < ((blockSize/2)+(blockSize*(ileri%blockNum))); j++) {
                    
                    image[((blockSize/2)+(blockSize*(ilk/blockNum)))][j]="*";
                }
            } 
            else if(fark1==1){
                
                
                
                for (int j = ((blockSize/2)+(blockSize*(ilk%blockNum))); j > ((blockSize/2)+(blockSize*(ileri%blockNum))); j--) {
 
                    image[((blockSize/2)+(blockSize*(ilk/blockNum)))][j]="*";
                    
                }
            
            
            }
            else if (fark1==blockNum) {
                
                
                for (int j = ((blockSize/2)+(blockSize*(ilk/blockNum))); j > ((blockSize/2)+(blockSize*(ileri/blockNum))); j--) {
                    
                    image[j][((blockSize/2)+(blockSize*(ileri%blockNum)))]="*";
                }
                
            }
            
            else if (fark1==-blockNum) {
                
                
                for (int j = ((blockSize/2)+(blockSize*(ilk/blockNum))); j < ((blockSize/2)+(blockSize*(ileri/blockNum))); j++) {
                    
                    image[j][((blockSize/2)+(blockSize*(ilk%blockNum)))]="*";
                    
                }
                
            }
            
            
        }
        
       

        
        BufferedImage resim = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
       

        for(int i = 0; i < size ;i++){
            for(int j = 0; j < size; j++){
                if(image[j][i] == "-" || image[j][i] == "|" ){
                    resim.setRGB(i,j, Color.BLUE.getRGB());
                }
                
                else if(image[j][i] == "*"){
                    
                    resim.setRGB(i,j, Color.RED.getRGB());
                    
                }
                
                else {
                    resim.setRGB(i,j, Color.WHITE.getRGB());

                }
               

            }
            
        }
        
        
        BufferedImage resizedImage = new BufferedImage(500, 500,  BufferedImage.TYPE_INT_RGB);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(resim, 0, 0, 500, 500, null);
	g.dispose();
        
        File outputfile = new File("saved.png");
        ImageIO.write(resizedImage, "png", outputfile);

        for(String[] a : image){
            for(String v : a){
                System.out.print(v);
                
            }
            System.out.print("\n");
        }
        
        System.out.println("");
        
        
    }
 
}
 
