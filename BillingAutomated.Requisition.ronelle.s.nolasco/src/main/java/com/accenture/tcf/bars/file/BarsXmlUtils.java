package com.accenture.tcf.bars.file;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.accenture.sef.xml.impl.BarsWriteXmlUtils;
import com.accenture.sef.xml.interfce.BarsWriteXMLUtilsInterface;
import com.accenture.tcf.bars.domain.Record;

public class BarsXmlUtils {
	
	public void writexml(List<Record> req) {
	
		List<Record> fileprocess = req;
			
		
		BarsWriteXMLUtilsInterface x = new BarsWriteXmlUtils();
		Document doc = x.createXMLDocument();
		Element rootElement = x.createDocumentElement(doc, "schema");
		
			
			
		for (Record noob : fileprocess) {
			Element staffElement = x.createChildElement(doc, rootElement, "request");

			x.createElementTextNode(doc,staffElement, "BillingCycle", noob.getBillngCycle()+"");
			x.createElementTextNode(doc,staffElement, "StartDate", noob.getStartDate()+"");
			x.createElementTextNode(doc,staffElement, "EndDate", noob.getEndDate()+"");
			x.createElementTextNode(doc,staffElement, "Lastname", noob.getCustomerLastName());
			x.createElementTextNode(doc,staffElement, "Firstname", noob.getCustomerFirstName());
			x.createElementTextNode(doc,staffElement, "Amount", noob.getAmount() +"");
			
			
			
		}
		Date date = new Date();
		String strDateFormat = "MMddyy_hhmmss";
		DateFormat dateformat = new SimpleDateFormat(strDateFormat);
		String formatedDate = dateformat.format(date);
		
		x.transformToXML(doc, "C:\\BARS\\Report\\BARS_Report-"+formatedDate+".xml");
	}
	
}
