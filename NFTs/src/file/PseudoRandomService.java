package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import entities.NftAsset;
import entities.NftAssetComponent;
import entities.NftAssetComponentEnum;

// TODO refactor whole service to not violate clean code principles
// have to work much more with FileService
public class PseudoRandomService implements RandomService {
	private String textFile = "/Nft_asset_ids.txt";
	private FileService fileService;

	private HashMap<NftAssetComponentEnum, List<File>> fileMap 
		= new HashMap<NftAssetComponentEnum, List<File>>();
	
	public PseudoRandomService(String path) {
		this.textFile = path + this.textFile;
	}
	
	@Override
	public File getRandomAsset(NftAsset asset, String path) {
		this.createFileService();
		boolean isFileUnique = false;
		int counter = 0;

		this.fileMap = this.fileService.getAllFiles(path);

		while (!isFileUnique || counter > 1000) {

			for (int i = 0; i < NftAssetComponentEnum.values().length; i++) {
				NftAssetComponentEnum componentName = NftAssetComponentEnum.values()[i];

				String fileName = this.fileMap.get(componentName)
						.get(this.getRandomNumberBetween(0, this.fileMap.get(componentName).size() -1)).getName();

				asset.getComponent(componentName).setFileName(fileName);
			}
			
			isFileUnique = this.isIdInTextFile(asset.getId());
			counter++;
		}
		
		if (isFileUnique) {
			try {
				this.writeIdInTextFile(asset.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.fileService.saveImage(asset);
			
		}

		return null;
	}
	
	private int getRandomNumberBetween(int firstNumber, int secondNumber) {
		return new Random().nextInt(secondNumber - firstNumber + 1) + firstNumber;
	}
	
	// TODO the methods which go directly into files should be moved in another class because it has no 
	// place in random service.
	
	private void writeIdInTextFile(String id) throws IOException {
		
	    Files.writeString(
	            Path.of(System.getProperty("java.io.tmpdir"), this.getTextFile().toString()),
	            id + System.lineSeparator()
	            // OpenOption.CREATE, OpenOption.APPEND
	        );
	    
		/*
		FileWriter fw = new FileWriter(this.getTextFile());
		fw.write(System.lineSeparator());
		fw.write(id);
		fw.close();
		*/
	}
	
	private boolean isIdInTextFile(String id) {
		try (BufferedReader br = new BufferedReader(new FileReader(this.getTextFile()))) {
			
		    String line;
		    
		    while ((line = br.readLine()) != null) {
		    	System.err.println(line);
		       // process the line.
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private File getTextFile() throws IOException {
		File file = new File(this.textFile);
		
		file.createNewFile();
		
		return file;
	}

	private void createFileService() {
		if (this.fileService == null) {
			this.fileService = new OSFileService();
		}
	}
}
