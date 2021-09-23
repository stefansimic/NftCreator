package model;

import java.io.File;

import entities.NftAsset;
import file.PseudoRandomService;
import file.RandomService;

public class ImageService {
	private Model model;
	private RandomService randomService;
	
	public ImageService(Model model) {
		this.model = model;
		this.randomService = new PseudoRandomService(this.getAssetPath());
	}
	
	public String getAssetPath() {
		return this.model.getAssetPath();
	}
	
	public File getRandomAsset(NftAsset asset) {
		this.randomService.getRandomAsset(asset, this.getAssetPath());
		
		return null;
	};
}
