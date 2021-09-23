package file;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import entities.NftAssetComponentEnum;
import entities.NftAsset;
import entities.NftAssetComponent;

public interface FileService {
	public HashMap<NftAssetComponentEnum, List<File>> getAllFiles(String path);
	public List<File> getAllFilesForComponent(NftAssetComponentEnum componentName);
	public void saveImage(NftAsset asset);
}
