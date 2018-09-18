package edu.chinna.kadira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.chinna.kadira.dto.FwLogDto;
import edu.chinna.kadira.service.FwLogService;

@RestController
public class FWLogController {
	@Autowired
	FwLogService lFwLogService;

	@RequestMapping("/fwlogs")
	public List<FwLogDto> getFwLogs() {
		return lFwLogService.getFwLogs();
	}
}
