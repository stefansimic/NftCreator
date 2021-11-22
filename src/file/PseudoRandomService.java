package file;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import entities.NftAsset;
import entities.NftAssetComponentEnum;

public class PseudoRandomService implements RandomService {
	private FileService fileService;

	private HashMap<NftAssetComponentEnum, List<File>> fileMap 
		= new HashMap<NftAssetComponentEnum, List<File>>();
	
	public PseudoRandomService(String path) {
		this.createFileService();
		this.fileMap = this.fileService.getAllFiles(path);
	}
	
	public void createRandomAssets(NftAsset asset, int numberOfAssets) {
		for (int j = 0; j < numberOfAssets; j++) {
			this.createRandomAsset(asset);
		}
	}
	
	private void createRandomAsset(NftAsset asset) {
		for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
			NftAssetComponentEnum componentName = NftAssetComponentEnum.values()[i];
	
			String fileName = this.fileMap.get(componentName)
					.get(this.getRandomNumberBetween(0, this.fileMap.get(componentName).size() -1)).getName();
	
			asset.getComponent(componentName).setFileName(fileName);
		}
		
		this.fileService.saveImage(asset);
	}
	
	private int getRandomNumberBetween(int firstNumber, int secondNumber) {
		return new Random().nextInt(secondNumber - firstNumber + 1) + firstNumber;
	}

	private void createFileService() {
		if (this.fileService == null) {
			this.fileService = new OSFileService();
		}
	}
}
