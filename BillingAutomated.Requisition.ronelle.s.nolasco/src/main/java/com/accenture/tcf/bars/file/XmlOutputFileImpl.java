package com.accenture.tcf.bars.file;

import java.util.List;

import com.accenture.tcf.bars.domain.Record;

public class XmlOutputFileImpl extends AbstractOutputFile{

	@Override
	public void writeFile(List<Record> records) {
		
		BarsXmlUtils utils = new BarsXmlUtils();
		utils.writexml(records);
	}

	
}
