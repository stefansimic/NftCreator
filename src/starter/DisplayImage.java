package starter;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayImage {

    public static void main(String avg[]) throws IOException
    {
        DisplayImage abc=new DisplayImage();
    }

    public DisplayImage() throws IOException
    {
    	
    	// File file = new File("resources/Lamia_and_the_Soldier.jpg");
    	
    	// Path path = Paths.get("/Documents/Bilder/Lamia_and_the_Soldier.jpg");

    	// file = new File("/stefansimic/Documents/Bilder/Lamia_and_the_Soldier.jpg");
    	
    	ImageIcon icon = 
    			new ImageIcon("/Users/stefansimic/Documents/Bilder/Lamia_and_the_Soldier.jpg");
    	
    	// Image image = icon.getImage();
    	
    

    	System.out.println(icon.toString());
    	
    	// BufferedImage img=ImageIO.read(path.toFile());
    	
    	JFrame frame = new JFrame("Test");
        frame.setSize(500, 500);
    	
    	frame.add(new JLabel(icon));
    	
        
        frame.setVisible(true);
    	
    	/*
    	
    	BufferedImage img=ImageIO.read(new File("/Users/stefansimic/Documents/Bilder/Lamia_and_the_Soldier.jpg"));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
    }
}
