package file;

import entities.NftAsset;

public interface FileCreaterService {
	public void createImage(NftAsset asset, String path);
}
