package com.accenture.tcf.bars.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.accenture.tcf.bars.datasource.MySqlDatasource;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.file.FileProcessor;

public class RecordDAOImpl implements IRecordDAO {

	@Override
	public List<Record> retrieverecords() throws BarsException {
		List<Record> records = new ArrayList<Record>();
		Session session = MySqlDatasource.getSessionFactory().openSession();
		List results = session 
				.createSQLQuery("SELECT billing.billing_cycle, billing.start_date, billing.end_date, customer.first_name, customer.last_name, billing.amount\r\n" + 
						"						FROM (((billing\r\n" + 
						"						Inner join account ON account.account_id = billing.account_id)\r\n" + 
						"						Inner join customer ON customer.customer_id = account.customer_id)\r\n" + 
						"                        Inner join request ON request.billing_cycle = billing.billing_cycle and request.start_date = billing.start_date and request.end_date = billing.end_date)")
					.list();
		
		
	for (Iterator iter = results.iterator(); iter.hasNext();) {
		if(!iter.hasNext()) {
			throw new BarsException(
					BarsException.NO_RECORD_TO_WRITE);
		}else {
		Object[] objs = (Object[]) iter.next();
		
		int billingCycle = (Integer) objs[0];
		Date startDate = (Date) objs[1];
		Date endDate = (Date) objs[2];
		String firstName = (String) objs[3];
		String lastName = (String) objs[4];
		BigDecimal amount = (BigDecimal) objs[5];
		String accountName = firstName + " " + lastName;
		
		records.add(new Record(billingCycle, startDate, endDate, accountName,firstName, lastName, amount));
		}
		
	}
	
	FileProcessor file = new FileProcessor();
	file.writeOutput(records);
	return records;

	}



	
}
