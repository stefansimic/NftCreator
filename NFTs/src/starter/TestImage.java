package starter;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestImage {
	private String imgPath = "/Users/stefansimic/Documents/Bilder/Lamia_and_the_Soldier.jpg";

    public TestImage() {
        JFrame frame = new JFrame("Test Image");
        frame.add(new NewImagePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public class NewImagePanel extends JPanel {

        private BufferedImage img;

        public NewImagePanel() {
            try {
            	   Path path = FileSystems.getDefault().getPath(imgPath);
                   
            	   
                img = ImageIO.read(
                		// new URL(imgPath)
                		// path.toFile().toURI().toURL()
                		TestImage.class.getResource("/resources/Lamia_and_the_Soldier.jpg")
                		);
                
                File fileOfRes = new File("/resources/Lamia_and_the_Soldier.jpg");
                
                
                System.out.println("a" + path.toFile().exists());
                System.out.println("Path Exists(1):"+ Files.exists(path));
                System.out.println("Path Exist(2) :"+path.toFile().exists());
                
                System.out.println("Path readable(3) :"+Files.isReadable(path));
                System.out.println("Path readable(4):"+path.toFile().canRead());
                System.out.println("Path readable(5):"+fileOfRes.canRead());
                
                copy(
                		Files.newInputStream(path)
                		//getClass().getResourceAsStream(imgPath)
                		, "/resources/");
                
            } catch (IOException ex) {
                System.out.println("Could not load image");
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600, 600);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestImage();
            }
        });
    }
    
    public static boolean copy(InputStream source , String destination) {
        boolean succeess = true;

        System.out.println("Copying ->" + source + "\n\tto ->" + destination);

        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
        	System.out.println("exception occured!" + ex.getStackTrace());
            succeess = false;
        }

        return succeess;

    }
}