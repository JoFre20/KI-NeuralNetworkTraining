package Utilis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {
	
	public final static String MAIN_DIR = "data/";
	
	
	public void init() {
		File file_main = new File(MAIN_DIR);
		if(!file_main.exists()) {
			file_main.mkdir();
		}
	}
	
	public boolean checkIfFileExist(String name) {
		return new File(name + ((name.contains(".json")) ? "" : ".json")).exists() && new File(name + ((name.contains(".json")) ? "" : ".json")).canRead();
	}
	
	public boolean checkIfFolderExist(String name) {
		return new File(name).exists() && new File(name).canRead();
	}
	
	public void createFile(String name) {
		File file = new File(name + ((name.contains(".json")) ? "" : ".json"));
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToDisk(String name, String json) {
		try {
			File file = new File (name + ((name.contains(".json")) ? "" : ".json"));
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(json);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readFromDisk(String name) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(name + ((name.contains(".json")) ? "" : ".json"))));
			return content;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteFile(String name) {
		File file = new File(name + ((name.contains(".json")) ? "" : ".json"));
		file.delete();
	}
	
	private String readFromInputStream(InputStream inputStream)
			  throws IOException {
			    StringBuilder resultStringBuilder = new StringBuilder();
			    try (BufferedReader br
			      = new BufferedReader(new InputStreamReader(inputStream))) {
			        String line;
			        while ((line = br.readLine()) != null) {
			            resultStringBuilder.append(line).append("\n");
			        }
			    }
			  return resultStringBuilder.toString();
			}

}