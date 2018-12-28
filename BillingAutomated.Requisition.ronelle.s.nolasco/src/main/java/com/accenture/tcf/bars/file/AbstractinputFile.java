package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.tcf.bars.exception.BarsException;
import com.google.protobuf.TextFormat.ParseException;

public abstract class AbstractinputFile implements IInputFile{
	private File file;
	@Autowired
	@Override
	public List readFile() throws BarsException {
		
		return null;
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}
	
	
}
