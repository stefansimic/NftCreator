package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import entities.NftAsset;
import entities.NftAssetBlueprint;
import entities.NftAssetComponentEnum;
import factories.NftAssetFactory;
import model.Model;
import starter.TestImage;

public class Frame implements ActionListener {
	private JFrame jFrame;
	private Model model;
	private String assetPath = "";
	
	private NftAssetBlueprint blueprint;
	
	private JTextField field;
    private BufferedImage img;

	private int swingConstantNorth = 1;
	private int swingConstantLeft = 2;
	
	public Frame(int width, int height, Model model) {
		this.jFrame = new JFrame("NFT Generator");
		this.model = model;
        
        this.addLabel("Pfad zu den Bildern", this.swingConstantLeft, this.swingConstantNorth);
        
        this.addTextInput();
        
        this.addPathButton();
        
        this.jFrame.pack();
        
        this.setFrameVisibility(true);
	
	}
	
	private void setFrameVisibility(boolean shouldBeVisible) {
        this.jFrame.setVisible(shouldBeVisible);
	}
	
	private void addTextInput() {
		this.field = new JTextField(10);
		this.jFrame.add(this.field, BorderLayout.NORTH);
	}
	
	private void addPathButton() {
		JButton button = new JButton("Speichern");
		button.setPreferredSize(new Dimension(100, 600));
		
		button.addActionListener(this);  
		
		this.jFrame.add(button);
	}
	
	public void addLabel(String text, int horizontal, int vertical) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(horizontal);
		label.setVerticalAlignment(vertical); 
		
		this.jFrame.add(label);
	}
	
	public void setImagePath() {
		try {
			this.jFrame.add(new JLabel(new ImageIcon(this.assetPath)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
	
	private void addImage() {
		this.jFrame.add(new NewImagePanel(
				//"/resources/Lamia_and_the_Soldier.jpg"
				"/Users/stefansimic/Pictures/Krieger/Spartaner_300.jpg"
				));
		this.jFrame.pack();
		
		this.jFrame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {		
		this.assetPath = this.field.getText();
		this.model.createRandomAssets(assetPath, this.createBasicBlueprint());
	}
	
	// image logic
	
	public class NewImagePanel extends JPanel {

        private BufferedImage img;

        public NewImagePanel(String path) {
            try {
                   
            	   
                img = ImageIO.read(TestImage.class.getResource(
                		path
                		// "/resources/Lamia_and_the_Soldier.jpg"
                		));
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
	
	private NftAssetBlueprint createBasicBlueprint() {
		NftAssetBlueprint nab = new NftAssetBlueprint();
		
		for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			nab.setValue(NftAssetComponentEnum.values()[i], false);
		}
		
		return nab;
	}
	
	private NftAsset createBasicAsset() {		
		NftAsset asset = NftAssetFactory.createNftAsset(this.createBasicBlueprint());
		
		System.out.println(asset.getId());
		
		return asset;
	}
}
