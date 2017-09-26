/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtcleaner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TimerTask;
import javax.swing.Timer;

/**
 *
 * @author I849002
 */
public class DTCleaner{
    /**
     * @param args the command line arguments
     */
    public static int propi;
    
    public static int onload;
    
    public static void main(String[] args) {
        
        UIKit k = new UIKit();
        Properties prop = new Properties();
	InputStream input = null;
        try {

		input = new FileInputStream("src/dtcleaner/config.properties");

		// load a properties file
		prop.load(input);
                
		// get the property value and print it out
                onload = Integer.parseInt(prop.getProperty("onload")); 
	

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        // TODO code application logic here
        
    }
    
    public void runClean(){
        
       String loc = null;
       String doc = null;
       String pic = null;
       String music = null;
       String vid = null;
       
        
        Properties prop = new Properties();
	InputStream input = null;
        try {

		input = new FileInputStream("src/dtcleaner/loc.properties");

		// load a properties file
		prop.load(input);
                
		// get the property value and print it out
               loc = prop.getProperty("Desktop");
               doc = prop.getProperty("Document");
               pic = prop.getProperty("Picture");
               music = prop.getProperty("Music");
               vid = prop.getProperty("Video");
               
               
               
        String docext = "xlsx.docx.pdf.pptx.doc.txt.out.pptm.csv.xml";
        String imgext = "png.jpg.jpeg.gif.svg";
        String musicext = "mp3.flac.arf.m4a.wav";
        String vidext = "mp4.avi.mov.swf.flv.wmv";
        
        String[] names = getDTFiles(loc);
        for(int i=0; i<names.length; i++){
           String floc = loc+names[i];
           //System.out.println(floc); 
            
           if(names[i].contains(".")){
               String ext = getDTExt(names[i]);
               //System.out.println(ext);
               if(docext.contains(ext)){
                   //MOVE ALL DOCUMENTS
                 try{
                    File afile =new File(floc);
                    if(afile.renameTo(new File(doc+afile.getName()))){
                    System.out.println("File is moved successful!");
                    }else{System.out.println("File is failed to move!");}
                 }catch(Exception e){e.printStackTrace();}
                   
               }else if(imgext.contains(ext)){
                   //MOVE ALL IMAGES
                   try{
                    File afile =new File(pic+names[i]);
                    if(afile.renameTo(new File("C:\\Users\\I849002\\Pictures\\" + afile.getName()))){
                    System.out.println("File is moved successful!");
                    }else{System.out.println("File is failed to move!");}
                 }catch(Exception e){e.printStackTrace();}
               }
               else if(musicext.contains(ext)){
                   //MOVE ALL IMAGES
                   try{
                    File afile =new File(music+names[i]);
                    if(afile.renameTo(new File("C:\\Users\\I849002\\Music\\" + afile.getName()))){
                    System.out.println("File is moved successful!");
                    }else{System.out.println("File is failed to move!");}
                 }catch(Exception e){e.printStackTrace();}
               }
               else if(vidext.contains(ext)){
                   //MOVE ALL IMAGES
                   try{
                    File afile =new File(vid+names[i]);
                    if(afile.renameTo(new File("C:\\Users\\I849002\\Videos\\" + afile.getName()))){
                    System.out.println("File is moved successful!");
                    }else{System.out.println("File is failed to move!");}
                 }catch(Exception e){e.printStackTrace();}
               }
           }else{
               System.out.println("Not a valid file...");
           }
        }
	

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        
        
        
    }
    public static String[] getDTFiles(String loc){
        File folder = new File(loc);
        File[] listOfFiles = folder.listFiles();
        String[] FNames  = new String[listOfFiles.length];

        for (int i = 0; i < listOfFiles.length; i++) {
          
            //System.out.println("File " + listOfFiles[i].getName());
            FNames[i] = listOfFiles[i].getName();
     
        }
        return FNames;
    }
    public static String getDTExt(String fname){
        String name = fname.split("\\.")[1];
        return name;
    }
    
}
