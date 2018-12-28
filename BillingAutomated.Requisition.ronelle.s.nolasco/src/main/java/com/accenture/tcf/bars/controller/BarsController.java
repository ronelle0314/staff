package com.accenture.tcf.bars.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.tcf.bars.dao.IRequestDAO;
import com.accenture.tcf.bars.dao.RequestDAOImpl;
import com.accenture.tcf.bars.domain.Record;
import com.accenture.tcf.bars.exception.BarsException;
import com.accenture.tcf.bars.file.FileProcessor;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BarsController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	  
	 */
	@RequestMapping("/")
	public String index() throws ParseException {
		
		return "index";
		
		
	}
	@RequestMapping("/process")
	public ModelAndView process(HttpServletRequest request)throws IOException, BarsException {
		final Logger log = Logger.getLogger(RequestDAOImpl.class);

		String path = request.getParameter("inputfile");
		File file = new File(path);
		ModelAndView mav = new ModelAndView();
		
	try {
		
		FileProcessor process = new FileProcessor();
		process.execute(file);
		List<Record> fileprocess = process.retrieveRecordfromDB();
		mav.addObject("filename", path);
		mav.addObject("contents", fileprocess);
		mav.setViewName("success");
		IRequestDAO requestDAO = new RequestDAOImpl();
		requestDAO.delete();
		return mav;
	}
	 catch (Exception e) {
			if (e.getMessage().contains(
					BarsException.BILLING_CYCLE_NOT_ON_RANGE)) {
				log.error(BarsException.BILLING_CYCLE_NOT_ON_RANGE);
				mav.setViewName("error_billing_cycle");
				mav.addObject("Error", e.getMessage());
				return mav;
			} else if (e.getMessage().contains(
					BarsException.INVALID_END_DATE_FORMAT)) {
				log.error(BarsException.INVALID_END_DATE_FORMAT);
				mav.setViewName("error_invalid_end_date");
				mav.addObject("Error", e.getMessage());
				return mav;
			} else if (e.getMessage().contains(
					BarsException.INVALID_START_DATE_FORMAT)) {
				log.error(BarsException.INVALID_START_DATE_FORMAT);
				mav.setViewName("error_invalid_start_date");
				mav.addObject("Error", e.getMessage());
				return mav;
			} else if (e.getMessage().contains(
					BarsException.PATH_DOES_NOT_EXIST)) {
				log.error(BarsException.PATH_DOES_NOT_EXIST);
				mav.setViewName("error_path_format");
				return mav;
			} else if (e.getMessage().contains(BarsException.NO_SUPPORTED_FILE)) {
				mav.setViewName("error_format");
				return mav;
			} else if (e.getMessage()
					.contains(BarsException.NO_RECORDS_TO_READ)) {
				mav.setViewName("error_no_records");
				return mav;
			} else if (e.getMessage().contains(
					BarsException.NO_RECORD_TO_WRITE)) {
				mav.setViewName("error_no_request");
				return mav;
			}else if (e.getMessage().contains(
					BarsException.NO_SUPPORTED_FILE)) {
				mav.setViewName("error_no_format");
				return mav;
			}else if (e.getMessage().contains(
					BarsException.COLUMN_NOT_IN_RANGE)) {
				mav.setViewName("error_no_format");
				return mav;
			}
		}
		
		
		return mav;
	
	
	}
	
	
	
}
