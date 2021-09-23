package file;

import java.io.File;
import java.util.List;

public interface FileGetterService {
	public List<File> getAllFiles(String path);
	public File getFile(String id);
}
