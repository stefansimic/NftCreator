package starter;

import entities.*;
import factories.NftAssetFactory;
import frame.Frame;
import model.Model;

public class Starter {

	public static void main(String[] args) {
		System.out.println("Start!");
		
		Starter.start();
		
		System.out.println("End!");
	}
	
	private static void start() {		
		Model model = new Model();
		
		// TODO remove Frame size or set to realistic size
		Frame frame = new Frame(600, 600, model);
	}
	
	// TODO remove unused function
	private static NftAsset createBasicAsset() {
		NftAssetBlueprint nab = new NftAssetBlueprint();
		
		for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			nab.setValue(NftAssetComponentEnum.values()[i], false);
		}
		
		
		NftAsset asset = NftAssetFactory.createNftAsset(nab);
		
		System.out.println(asset.getId());
		
		return asset;
	}

}
