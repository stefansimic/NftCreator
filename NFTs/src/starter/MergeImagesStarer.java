package starter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
  
import javax.imageio.ImageIO;

import entities.*;
import factories.NftAssetFactory;

public class MergeImagesStarer {
	private static final String path = "/Users/stefansimic/resources/";

	 public static void main(String args[]) throws Exception {
		 NftAssetBlueprint blueprint = new NftAssetBlueprint();
		 
		 for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			 blueprint.setValue(NftAssetComponentEnum.values()[i], true);
		 }
		 
		 NftAsset asset = NftAssetFactory.createNftAsset(blueprint);
		 
		 
		// Array of input images.
	        BufferedImage[] input = new BufferedImage[NftAssetComponentEnum.values().length];
	         
	        // Load each input image.
	        // Assume they are called "image_0.png", "image_1.png",
	        // etc.
	        for ( int i = 0; i < input.length; i++ ) {
	            try {
	            	NftAssetComponent comp = asset.getComponent(NftAssetComponentEnum.values()[i]);
	            	System.out.println(path + comp.getComponentName() + comp.getComponentFileId() + ".png");
	                File f = new File(path + comp.getComponentName() + "/" + comp.getComponentFileId() + ".png" );
	                input[i] = ImageIO.read( f );
	            }
	            catch ( IOException x ) {
	                // Complain if there is any problem loading 
	                // an input image.
	                x.printStackTrace();
	            }
	        }
	         
	        // Create the output image.
	        // It is the same size as the first
	        // input image.  I had to specify the type
	        // so it would keep it's transparency.
	        BufferedImage output = new BufferedImage( 
	                input[0].getWidth(), 
	                input[0].getHeight(), 
	                BufferedImage.TYPE_INT_ARGB );
	         
	        // Draw each of the input images onto the
	        // output image.
	        Graphics g = output.getGraphics();
	        for ( int i = 0; i < input.length; i++ ) {
	            g.drawImage( input[i], 0, 0, null );
	        }
	         
	        // Create the output image file and write the
	        // output image to it.
	        File f = new File( "/Users/stefansimic/Documents/development/Eclipse/NFTs/bin/resources/image.png" );
	        try {
	            ImageIO.write( output, "PNG", f );
	        }
	        catch ( IOException x ) {
	            // Complain if there was any problem writing 
	            // the output file.
	            x.printStackTrace();
	        }	 
	 }
}
