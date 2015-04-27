package com.bcus.cms.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.bcus.cms.util.ConveyorSystemConstants;

public class FileOutputWriter {
	
	private File getFile() throws IOException{
		File file = new File(ConveyorSystemConstants.OUTPUT_FILE_PATH);
		if(!file.exists()){
			file.createNewFile();
		}
		return file;
	}
	public void write(String content){
		File outputfile;
		try {
			outputfile = getFile();
			FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
