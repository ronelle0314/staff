package com.accenture.tcf.bars.file;

import java.io.File;
import java.util.List;

import com.accenture.tcf.bars.domain.Record;

public interface IOutputFile {
	public void writeFile(List<Record> records);
	public void setFIle (File file);
	public File getFile();
	
}
