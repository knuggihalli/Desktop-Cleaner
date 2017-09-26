/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtcleaner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author I849002
 */
public class UIKit extends JFrame{
    
    public String floc;
    public String flocName;
    public JPanel p;
    
    public UIKit(){
        menu();
        leftMenu();
        setTitle("DTCleaner - ConsiderCode LLC");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#34495e"));
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        setVisible(true); 
        
        JButton runMe = new JButton("Click here to ORGANIZE");
        runMe.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                DTCleaner clean = new DTCleaner();
                clean.runClean();
            }
        });
        
        add(runMe);
        

    }
    
     public void menu(){
        JMenuBar menubar = new JMenuBar();
        
        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu settings = new JMenu("Settings");
        JMenu help = new JMenu("Help");
        
        menubar.add(file);
        menubar.add(tools);
        menubar.add(settings);
        menubar.add(help);
        
        setJMenuBar(menubar);
    }
    public void leftMenu(){
        JButton addButton = new JButton("Configure");
        addButton.setBackground(Color.decode("#ecf0f1"));
        addButton.setBorderPainted(false);
        addButton.setOpaque(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                JFrame d = new JFrame();
                d.setTitle("New Location");
                d.setSize(200, 200);
                d.setResizable(false);
                d.setBackground(Color.decode("#ecf0f1"));
                d.setLocationRelativeTo(null);
                
                Font font1 = new Font("SansSerif", Font.BOLD, 20);
                
                JLabel locName = new JLabel("Desktop");
                locName.setHorizontalAlignment(JLabel.CENTER);
                locName.setFont(font1);
                d.add(locName, BorderLayout.PAGE_START);
                
                JButton mainLoc = new JButton("Locate Desktop");
                mainLoc.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        JFileChooser jf = new JFileChooser();
                        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int returnVal = jf.showOpenDialog(UIKit.this);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = jf.getSelectedFile();
                            floc = file.getPath();
                            DTCleaner dt = new DTCleaner();
                            flocName = locName.getText();
                            //System.out.println(floc);
                            //System.out.println(flocName);
                            try {
                                CreateLoc(floc,flocName);
                            } catch (IOException ex) {
                                Logger.getLogger(UIKit.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            d.setVisible(false);
                            
                            
                            //dt.runClean(floc);
                            //This is where a real application would open the file.

                        } else {
                            System.out.println("no file returned");
                        }
                    }
                });
                d.add(mainLoc , BorderLayout.CENTER);
                
                
               
      
                d.setVisible(true); 
            }
        });
        
        p = new JPanel();;            
        p.setBackground(Color.decode("#95a5a6"));
        
        DTCleaner DTonload = new DTCleaner();
        int didload = DTonload.onload;
        if(didload == 1){
            
        }else{
        
        }
        
       p.add(addButton, BorderLayout.CENTER);
        add(p, BorderLayout.LINE_START);
        
       
        
    }

public void CreateLoc(String floc, String flocName) throws IOException{
    //get variables from uikit
/*FileWriter fileWritter = new FileWriter("src/dtcleaner/loc.properties", true);
BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
String saveme = flocName+"="+floc;
bufferWritter.append(saveme);
bufferWritter.append('\n');
bufferWritter.close();*/
    
    Properties prop = new Properties();
    OutputStream output = null;

	try {

		output = new FileOutputStream("src/dtcleaner/loc.properties");

                String doc = floc.replace("Desktop", "Documents\\");
                String pic = floc.replace("Desktop", "Pictures\\");
                String music = floc.replace("Desktop", "Music\\");
                String vid = floc.replace("Desktop", "Videos\\");
                floc = floc+"\\";
                    
		// set the properties value
		prop.setProperty(flocName, floc);
		prop.setProperty("Document", doc);
		prop.setProperty("Picture", pic);
                prop.setProperty("Music", music);
                prop.setProperty("Video", vid);

		// save properties to project root folder
		prop.store(output, null);

	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
  
                           
}

    
}
