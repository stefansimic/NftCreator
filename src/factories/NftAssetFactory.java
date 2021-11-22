package factories;

import entities.*;

public class NftAssetFactory {
	public static NftAsset createNftAsset(NftAssetBlueprint blueprint) {
		NftAsset asset = new NftAsset();
		
		blueprint.getMap().entrySet().stream().forEach((componentBlueprint) -> {
			asset.addComponent(
					NftAssetFactory.createNftAssetComponent(
							componentBlueprint.getValue(), 
							componentBlueprint.getKey()
					)
			);
		});
		
		return asset;
	}
	
	private static NftAssetComponent createNftAssetComponent(
			boolean useDefaultComponent, 
			NftAssetComponentEnum componentName
	) {
		return new NftAssetComponent(componentName, useDefaultComponent);
	}
}
