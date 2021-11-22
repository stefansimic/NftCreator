package model;

import entities.NftAsset;
import entities.NftAssetBlueprint;
import file.PseudoRandomService;
import file.RandomService;
import factories.NftAssetFactory;

public class Model {
	private RandomService randomService;

	public void createRandomAssets(String assetPath, NftAssetBlueprint blueprint, int timesToCreateAsset) {
		System.out.println(assetPath);
		
		this.randomService = new PseudoRandomService(assetPath != null ? assetPath : "");
		
		this.createRandomAsset(NftAssetFactory.createNftAsset(blueprint), timesToCreateAsset);
	}
	
	public void createRandomAsset(NftAsset asset, int timesToCreateAsset) {
		this.randomService.createRandomAssets(asset, timesToCreateAsset);
	};

}
