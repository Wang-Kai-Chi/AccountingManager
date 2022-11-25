package com.ericwang.jaccount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileReader {
	private Path path;
	private String result;
	
	public MyFileReader() {
		
	}

	public MyFileReader(String url) {
		path = Path.of(url);
		try {
			result = Files.readString(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void debug() {
		System.out.println(result);
	}
}
