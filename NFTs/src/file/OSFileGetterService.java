package file;

import java.io.File;
import java.util.List;

public class OSFileGetterService implements FileGetterService {
	private final String name;
	
	public OSFileGetterService(String name) {
		this.name = name;
	}

	@Override
	public List<File> getAllFiles(String path) {
		System.out.println("here: " + this.name);
		
		File folder = new File(path + "/" +this.name);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}
		
		
		return List.of(listOfFiles);
	}

	@Override
	public File getFile(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
