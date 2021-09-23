package file;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.NftAsset;
import entities.NftAssetBlueprint;
import entities.NftAssetComponent;
import entities.NftAssetComponentEnum;
import factories.NftAssetFactory;

public class OSFileCreaterService implements FileCreaterService {
	private final String directoryName = "output";

	@Override
	public void createImage(NftAsset asset, String path) {
		System.out.println("Here");
		BufferedImage[] input = new BufferedImage[NftAssetComponentEnum.values().length];

		for (int i = 0; i < input.length; i++) {
			try {
				NftAssetComponent comp = asset.getComponent(NftAssetComponentEnum.values()[i]);
				System.out.println(path + comp.getComponentName() + "/" + comp.getComponentFileId());
				File f = new File(path + comp.getComponentName() + "/" + comp.getComponentFileId());
				input[i] = ImageIO.read(f);
			} catch (IOException x) {
				x.printStackTrace();
			}
		}

		BufferedImage output = new BufferedImage(input[0].getWidth(), input[0].getHeight(),
				BufferedImage.TYPE_INT_ARGB);

		Graphics g = output.getGraphics();
		for (int i = 0; i < input.length; i++) {
			g.drawImage(input[i], 0, 0, null);
		}

		
	
		System.out.println("Name is: " + asset.getId());
		File f = new File(path + "/" + this.directoryName + "/" + asset.getId() + ".png");
		try {
			ImageIO.write(output, "PNG", f);
		} catch (IOException x) {
			// Complain if there was any problem writing
			// the output file.
			x.printStackTrace();
		}
	}

}
