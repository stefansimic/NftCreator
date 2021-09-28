package file;

import java.io.File;

import entities.NftAsset;

public interface RandomService {
	// todo rename createRandomAsset
	public File getRandomAsset(NftAsset asset, String path);
	// todo add method createRandomAssets
	//public void getRandomAsset(NftAsset asset, String path, int howManyTimes);
}
