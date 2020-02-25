package com.previred.desafio.service;

import java.util.Date;

import com.previred.desafio.model.UfsTO;
import com.previred.desafio.tres.uf.vo.Uf;

public interface UfService {

	public UfsTO getRango();

	public Uf getUf(Date fecha);

	public UfsTO generateJSON();

}
