package batchJob.common.xml.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import batchJob.common.xml.Book;
import batchJob.common.xml.ContextData;
import batchJob.common.xml.TxtBookData;

public class CommonFileTxtBook implements Book {
	
	File file;

	public CommonFileTxtBook(File file) {
		this.file = file;
	}

	@Override
	public ContextData read() {
		List<String> lines = new ArrayList<String>();
		try {
			final FileReader reader = new FileReader(this.file);
			final BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new TxtBookData(lines);
	}

}
