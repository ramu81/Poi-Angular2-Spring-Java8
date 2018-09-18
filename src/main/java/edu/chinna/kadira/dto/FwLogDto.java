package edu.chinna.kadira.dto;

import static lombok.AccessLevel.PUBLIC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * @author Kondapalli Ram
 *
 */
@Data
@ToString
@NoArgsConstructor(access = PUBLIC)
@AllArgsConstructor(access = PUBLIC)
public class FwLogDto {

	@Getter
	long duration;

	@Getter
	String msgId;

	@Getter
	String req;
	
	@Getter
	String res;

	@Getter
	String sourceName;

	@Getter
	String userId;

}
