package edu.chinna.kadira.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.chinna.kadira.dto.FwLogDto;

@Service
public class FwLogServiceImpl implements FwLogService {

	@Override
	public FwLogDto getMsgById(final String msgId) {
		FwLogDto target = new FwLogDto();
		for (FwLogDto fwLogDto : fwLogs) {
			if (fwLogDto.getMsgId().equals(msgId)) {
				target = fwLogDto;
				break;
			}
		}
		return target;
	}

	@Override
	public boolean saveFwLog(final FwLogDto logDto) {
		return fwLogs.add(logDto);
	}

	@Override
	public List<FwLogDto> getFwLogs() {
		return new ArrayList<FwLogDto>(fwLogs);
	}

}
