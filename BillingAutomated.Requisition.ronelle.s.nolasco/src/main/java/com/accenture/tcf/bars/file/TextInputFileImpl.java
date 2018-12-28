package com.accenture.tcf.bars.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.accenture.tcf.bars.dao.RecordDAOImpl;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.datasource.MySqlDatasource;
import com.accenture.tcf.bars.domain.Request;
import com.accenture.tcf.bars.exception.BarsException;

public class TextInputFileImpl extends AbstractinputFile{

	@Override
	public List readFile() throws BarsException {
	
	File file = getFile();
	BufferedReader reader = null;
	String line = "";
	
	
	try {
		reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
	
		SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
	    int row = 0;
		sdfrmt.setLenient(false);
		line = reader.readLine();
		if(line == null ) {
			throw new BarsException(
					BarsException.NO_RECORDS_TO_READ + row);
		}else {
		while ((line = reader.readLine()) != null) {
		 row ++;
			String bc = "";
			String sd = "";
			String ed = "";
			String converteded = "";
			String convertedsd ="";
			if(line.length() == 18) {
		
			for(int i = 0 ; i < line.length(); i++ ) {
				
				if (i <= 1) {
					bc += line.charAt(i);
				}else if(i >= 2 && i <= 9) {
					sd += line.charAt(i);
				}else if(i >= 10 && i <= 17) {
					ed += line.charAt(i);
				}
				
			}
			int billing = Integer.parseInt(bc);
			convertedsd = sd.substring(0, 2) + "/" + sd.substring(2, 4)+ "/" + sd.substring(4,  8);
			converteded = ed.substring(0, 2) + "/" + ed.substring(2, 4)+ "/" + ed.substring(4,  8);	
			Date date;
			Date date2;
			if(billing < 1 || billing > 12 ) {
				throw new BarsException(
						BarsException.BILLING_CYCLE_NOT_ON_RANGE + row);
			}
			try {
				
				date = sdfrmt.parse(convertedsd);
			}catch(java.text.ParseException e) {
				throw new BarsException(
						BarsException.INVALID_START_DATE_FORMAT + row);
			}
			try {
				date2 = sdfrmt.parse(converteded);
				}
				catch(java.text.ParseException e) {
					throw new BarsException(
							BarsException.INVALID_END_DATE_FORMAT + row);
				}
				
				SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Request.class)
						.buildSessionFactory();
				Session session = factory.getCurrentSession();
				
				Request req = new Request(billing, date, date2);
				RequestDAOImpl request = new RequestDAOImpl();
				request.insertRequest(req);
				
				RecordDAOImpl record = new RecordDAOImpl();
				record.retrieverecords();
			
		}else {
				System.out.println("Invalid File Format");
				break;
			}
		}
	}
	} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
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
