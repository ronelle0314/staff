 package com.accenture.tcf.bars.file;

import java.io.File;

import org.slf4j.Logger;

public abstract class AbstractOutputFile implements IOutputFile{
	private File file;
	protected Logger logger;
	@Override
	public void setFIle(File file) {
		this.file = file;
		
	}
	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}
	
	
}
