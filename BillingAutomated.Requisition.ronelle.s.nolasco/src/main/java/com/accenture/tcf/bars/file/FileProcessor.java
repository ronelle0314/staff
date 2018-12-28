package com.accenture.tcf.bars.file;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.accenture.sef.xml.impl.BarsWriteXmlUtils;
import com.accenture.sef.xml.interfce.BarsWriteXMLUtilsInterface;
import com.accenture.tcf.bars.dao.IRecordDAO;
import com.accenture.tcf.bars.dao.IRequestDAO;
import com.accenture.tcf.bars.dao.RecordDAOImpl;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.datasource.MySqlDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.factory.InputFileFactory;


public class FileProcessor {
	private IInputFile inputFile;
	@Autowired
	private IOutputFile outputFile;
	private IRequestDAO requestDAO;
	@Autowired
	private IRecordDAO recordDAO;
	
	
	public void execute(File file) throws BarsException {
		InputFileFactory input = InputFileFactory.GetInstance();
		
		input.getInputFile(file);
		
		
		
	}
	public List<Record> retrieveRecordfromDB() throws BarsException{
		recordDAO = new RecordDAOImpl();
		return recordDAO.retrieverecords();
		
		
		

	}
	
	public void writeOutput(List<Record> record) {
		
		List<Record> xmlRecord = record;
		XmlOutputFileImpl xmlfile = new XmlOutputFileImpl(); 
		xmlfile.writeFile(xmlRecord);
		
	}
}
