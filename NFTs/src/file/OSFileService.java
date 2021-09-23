package file;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import entities.NftAsset;
import entities.NftAssetComponent;
import entities.NftAssetComponentEnum;

public class OSFileService implements FileService {
	private HashMap<NftAssetComponentEnum, FileGetterService> fileGetterMap 
	= new HashMap<NftAssetComponentEnum, FileGetterService>();

	private HashMap<NftAssetComponentEnum, List<File> > fileMap 
		= new HashMap<NftAssetComponentEnum, List<File> >();
	
	private String path;
	
	public OSFileService() {
		
		for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			this.fileGetterMap.put(NftAssetComponentEnum.values()[i], 
					new OSFileGetterService(NftAssetComponentEnum.values()[i].name())
			);
		}
	}


	@Override
	public HashMap<NftAssetComponentEnum, List<File>> getAllFiles(String path) {
		this.path = path;
		
		if (this.fileMap.isEmpty()) {
			for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
				
				this.fileMap.put(NftAssetComponentEnum.values()[i],
						this.fileGetterMap.get(
								NftAssetComponentEnum.values()[i]
										).getAllFiles(path)
				);
			}
		}
		
		return this.fileMap;
	}

	@Override
	public List<File> getAllFilesForComponent(NftAssetComponentEnum componentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveImage(NftAsset asset) {
		new OSFileCreaterService().createImage(asset, this.path);
		
	}

}
