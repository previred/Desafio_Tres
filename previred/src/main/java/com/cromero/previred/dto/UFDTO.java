package com.cromero.previred.dto;

import java.util.Date;

import com.cromero.previred.util.ConstantsUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UFDTO {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ConstantsUtil.DATE_FORMAT)
	private Date fecha;
	private Double dato;
}
