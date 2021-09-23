package entities;

import java.util.HashMap;

public class NftAsset {
	private HashMap<NftAssetComponentEnum, NftAssetComponent> assets 
		= new HashMap<NftAssetComponentEnum, NftAssetComponent>();
	
	public void addComponent(NftAssetComponent component) {
		this.assets.putIfAbsent(component.getComponentName(), component);
	}
	
	public NftAssetComponent getComponent(NftAssetComponentEnum name) {
		return this.assets.get(name);
	}
	
	public HashMap<NftAssetComponentEnum, NftAssetComponent> getAssets() {
		return this.assets;
	}
	
	public String getId() {
		String id = "";
		
		for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			id += this.assets.get(NftAssetComponentEnum.values()[i]).getComponentFileId();
		}
		
		return id;
	}
}
