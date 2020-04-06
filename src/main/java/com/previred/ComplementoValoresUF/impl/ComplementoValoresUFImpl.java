package com.previred.ComplementoValoresUF.impl;

import com.previred.ComplementoValoresUF.service.ComplementoValoresUFService;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;

public class ComplementoValoresUFImpl implements ComplementoValoresUFService {

	@Override
	public Ufs getRangoUfd() {
		Valores valores = new Valores();
		Ufs ufs = valores.getRango();
		return ufs;
	}

}
