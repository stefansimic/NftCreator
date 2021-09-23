package file;

import java.io.File;

import entities.NftAsset;

public interface RandomService {
	public File getRandomAsset(NftAsset asset, String path);
}
