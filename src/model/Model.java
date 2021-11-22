package model;

import entities.NftAsset;
import entities.NftAssetBlueprint;
import file.PseudoRandomService;
import file.RandomService;
import factories.NftAssetFactory;

public class Model {
	private RandomService randomService;

	public void createRandomAssets(String assetPath, NftAssetBlueprint blueprint) {
		System.out.println(assetPath);
		
		this.randomService = new PseudoRandomService(assetPath != null ? assetPath : "");
		
		this.createRandomAsset(NftAssetFactory.createNftAsset(blueprint));
	}
	
	public void createRandomAsset(NftAsset asset) {
		int numberOfAssets = 1;
		this.randomService.createRandomAssets(asset, numberOfAssets);
	};

}
