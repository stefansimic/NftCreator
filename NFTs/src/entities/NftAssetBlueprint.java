package entities;

import java.util.HashMap;

public class NftAssetBlueprint {
	public HashMap<NftAssetComponentEnum, Boolean> map = new HashMap<NftAssetComponentEnum, Boolean>();
	
	public void setValue(NftAssetComponentEnum componentName, Boolean useDefaultAsset) {
		this.map.put(componentName, useDefaultAsset);
	}
	
	public HashMap<NftAssetComponentEnum, Boolean> getMap() {
		return this.map;
	}
}
