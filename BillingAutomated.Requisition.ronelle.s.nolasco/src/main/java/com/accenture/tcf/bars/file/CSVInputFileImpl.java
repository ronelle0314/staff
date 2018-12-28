package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.tcf.bars.dao.RecordDAOImpl;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class CSVInputFileImpl extends AbstractinputFile{
	final static Logger log = Logger.getLogger(RequestDAOImpl.class);
	@Override
	public List readFile() throws BarsException {
		File file = getFile();
		BufferedReader reader = null;
		String read = "";
		String line = "";
		String csvSplitBy = ",";
		try {
			int count;
			reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
			int row = 0;
			
				while ((line = reader.readLine()) != null) {
				
				
				// use comma as separator
				String[] bill = line.split(csvSplitBy);
				System.out.println(bill[0]);
				//count billing size
				count = Integer.parseInt(bill[0]);
				//count row that fails
				
				row++;
				if(count > 12) {
					throw new BarsException(
							BarsException.BILLING_CYCLE_NOT_ON_RANGE + row);	
				}
				System.out.println(count);
				
				// Set preferred date format,
				//For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
				    
				SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
				sdfrmt.setLenient(false);
				Date date,date2;
				System.out.println(count);
			try {
				if(bill.length > 3){
					throw new BarsException(
							BarsException.COLUMN_NOT_IN_RANGE + bill.length);
				}
					try {
						
						date = sdfrmt.parse(bill[1]);
					}catch (java.text.ParseException e) {
						throw new BarsException(
								BarsException.INVALID_START_DATE_FORMAT + row);
					} 	try {
						
						date2 = sdfrmt.parse(bill[2]);
					}catch (java.text.ParseException e) {
						throw new BarsException(
								BarsException.INVALID_END_DATE_FORMAT + row);
					} 
					Request req = new Request(count, date, date2);
					RequestDAOImpl request = new RequestDAOImpl();
					request.insertRequest(req);
					
					RecordDAOImpl record = new RecordDAOImpl();
					record.retrieverecords();
			
				
			} catch (Exception e) {
				log.error(e.getMessage());
			} 
				}
			
			reader.close();
		} catch (FileNotFoundException e) {
			throw new BarsException(
					BarsException.PATH_DOES_NOT_EXIST);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} 
		return super.readFile();
	}

	
}
