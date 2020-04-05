package com.cromero.previred.dto;

import java.util.Date;
import java.util.List;

import com.cromero.previred.util.ConstantsUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UFValueDTO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantsUtil.DATE_FORMAT)
	private Date inicio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantsUtil.DATE_FORMAT)
	private Date fin;
	private List<UFDTO> UFs;
}
