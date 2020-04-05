package com.cromero.previred.services.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UFValueModel {
	private String inicio;
	private String fin;
	private List<UFDTOModel> UFs;
}
