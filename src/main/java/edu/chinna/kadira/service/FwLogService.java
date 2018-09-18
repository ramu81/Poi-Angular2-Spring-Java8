package edu.chinna.kadira.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.chinna.kadira.dto.FwLogDto;

/**
 * 
 * @author Kondapalli Ram
 *
 */
public interface FwLogService {
	
	Set<FwLogDto> fwLogs = Collections.synchronizedSet(new HashSet<FwLogDto>());

	FwLogDto getMsgById(String msgId);

	boolean saveFwLog(FwLogDto logDto);

	List<FwLogDto> getFwLogs();
}
