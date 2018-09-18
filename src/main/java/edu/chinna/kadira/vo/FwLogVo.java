package edu.chinna.kadira.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class FwLogVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2025256361621438770L;

	@Setter
	@Getter
	LocalDateTime tranTimestamp;

	@Setter
	@Getter
	@Id

	String msgId;
	@Setter
	@Getter

	String moduleName;
	@Setter
	@Getter
	String sourceName;

	@Setter
	@Getter
	String userId;

	public FwLogVo() {
	}

}
