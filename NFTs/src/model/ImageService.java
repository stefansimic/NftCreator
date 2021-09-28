package model;

import java.io.File;

import entities.NftAsset;
import file.PseudoRandomService;
import file.RandomService;

// TODO delete obsolete service
public class ImageService {
	private Model model;
	private RandomService randomService;
	
	// TODO Model not needed here. remove Parameter and get path isntead of model
	public ImageService(Model model) {
		this.model = model;
		this.randomService = new PseudoRandomService(this.getAssetPath());
	}
	
	public String getAssetPath() {
		return this.model.getAssetPath();
	}
	
	//TODO remove getter, generate random asset is method needed here
	public File getRandomAsset(NftAsset asset) {
		this.randomService.getRandomAsset(asset, this.getAssetPath());
		
		return null;
	};
}
