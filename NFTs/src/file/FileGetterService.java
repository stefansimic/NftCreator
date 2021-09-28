package file;

import java.io.File;
import java.util.List;

public interface FileGetterService {
	public List<File> getAllFiles(String path);
	// todo remove unused method
	public File getFile(String id);
}
