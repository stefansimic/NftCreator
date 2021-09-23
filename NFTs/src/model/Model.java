package model;

import entities.NftAssetBlueprint;
import factories.NftAssetFactory;

public class Model {
	private ImageService imageService;
	private String assetPath = "";

	public String getAssetPath() {
		return assetPath;
	}

	public void setAssetPath(String assetPath, NftAssetBlueprint blueprint) {
		System.out.println(assetPath);
		this.assetPath = assetPath;
		this.initImageService();
		this.imageService.getRandomAsset(NftAssetFactory.createNftAsset(blueprint));
	}
	
	private void initImageService() {
		this.imageService = new ImageService(this);
	}

}
