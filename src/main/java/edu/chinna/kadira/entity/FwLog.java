package edu.chinna.kadira.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class FwLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6463435601504623906L;
	@Setter
	@Getter
	long duration;
	@Setter
	@Getter
	@Id
	String msgId;
	@Setter
	@Getter
	String req;
	@Setter
	@Getter
	String res;
	@Setter
	@Getter
	String sourceName;
	@Setter
	@Getter
	String userId;

	public FwLog() {
	}

}
