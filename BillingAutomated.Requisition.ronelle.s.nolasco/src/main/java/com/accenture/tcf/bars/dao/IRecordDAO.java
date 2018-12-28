package com.accenture.tcf.bars.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;

public interface IRecordDAO{
	@Autowired
	public List<Record> retrieverecords() throws BarsException;
}
