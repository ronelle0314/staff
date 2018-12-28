package com.accenture.tcf.bars.factory;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.file.CSVInputFileImpl;
import com.accenture.tcf.bars.file.IInputFile;
import com.accenture.tcf.bars.file.TextInputFileImpl;

public class InputFileFactory {
	
	
	private InputFileFactory() {
	
	}
	
	public static InputFileFactory GetInstance() {
		InputFileFactory factory = new InputFileFactory();
		
		return factory;
		
		
	}
	
	public IInputFile getInputFile(File file) throws BarsException{
		String checkfile = file.toString();
		if(checkfile.endsWith(".csv")) {
			
			CSVInputFileImpl csv = new CSVInputFileImpl();
			csv.setFile(file);
			csv.readFile();
		}else if(checkfile.endsWith(".txt"))
		{
			TextInputFileImpl txt = new TextInputFileImpl();
			txt.setFile(file);
			txt.readFile();
		}else{
			throw new BarsException(
					BarsException.NO_SUPPORTED_FILE);
		}
		
		return null;
		
	}
}
