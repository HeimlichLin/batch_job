package batchJob.common.utils;

import java.io.File;

public class FolderUtils {
	
	public static void createFolders(String... paths) {
		for (String path : paths) {
			File file = new File(path);
			file.mkdirs();
		}
	}
	
	public static void createFolders(File... files) {
		for (File file : files) {
			file.mkdirs();
		}
	}
	
	public static File getResourcesFile(Class<?> pClass, final String file) {
		final ClassLoader classLoader = pClass.getClassLoader();
		return new File(classLoader.getResource(file).getFile());
	}

}
