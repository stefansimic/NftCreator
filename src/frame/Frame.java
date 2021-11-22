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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
	private JCheckBox[] checkboxValues;
	
	private JTextField field;
	private JFormattedTextField input;
    private BufferedImage img;

	private int swingConstantNorth = 1;
	private int swingConstantLeft = 2;
	
	public Frame(int width, int height, Model model) {
		this.jFrame = new JFrame("NFT Generator");
		this.model = model;
        
        this.addLabel("Pfad zu den Bildern", this.swingConstantLeft, this.swingConstantNorth);
        
        this.addTextInput();
        
        this.addPathButton();
        
        this.addSpinnerModel();
        this.addCheckboxes();
        
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
		button.setPreferredSize(new Dimension(100, 100));
		
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
	
	@Override
	public void actionPerformed(ActionEvent event) {		
		this.assetPath = this.field.getText();
		int timesToCreateAsset = Integer.parseInt(this.input.getText());
		this.model.createRandomAssets(assetPath, this.createBasicBlueprint(), timesToCreateAsset);
	}
	
	
	private NftAssetBlueprint createBasicBlueprint() {
		NftAssetBlueprint nab = new NftAssetBlueprint();
		
		for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			nab.setValue(NftAssetComponentEnum.values()[i], this.checkboxValues[i].isSelected());
		}
		
		return nab;
	}
	
	private NftAsset createBasicAsset() {		
		NftAsset asset = NftAssetFactory.createNftAsset(this.createBasicBlueprint());
		
		System.out.println(asset.getId());
		
		return asset;
	}
	
	private void addSpinnerModel() {
		 JLabel label;
	    JFormattedTextField input;
	    JPanel panel;

	    BoxLayout layout = new BoxLayout(this.jFrame.getContentPane(), BoxLayout.Y_AXIS);
	    this.jFrame.setLayout(layout);

	    label = new JLabel("Wieviel sollen generiert werden (ganze Zahl):");
	    this.input = new JFormattedTextField(1);
	    this.input.setValue(1);
	    this.input.setColumns(20);
	    panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    panel.add(label);
	    panel.add(this.input);
	    this.jFrame.add(panel);

	    this.jFrame.add(new JTextField());
	    this.jFrame.pack();
	    this.jFrame.setVisible(true);
	}
	
	private void addCheckboxes() {
		// NftAssetComponentEnum
		this.checkboxValues = new JCheckBox[NftAssetComponentEnum.values().length];
		
		for (int i = 0; i < this.checkboxValues.length; i++) {
			this.checkboxValues[i] = new JCheckBox(NftAssetComponentEnum.values()[i].name());
			this.jFrame.add(this.checkboxValues[i]);
		}
	}
}
