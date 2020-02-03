package com.previred.egs;

import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;
import com.previred.egs.domain.GenerateJsonFile;
import com.previred.egs.domain.GenerateOutputFile;
import com.previred.egs.domain.UfDataAggregator;

public class Main {

	public static void main(String[] args) {

		Valores valores = new Valores();
		Ufs ufs = valores.getRango();
		UfDataAggregator ufDataAggregator = new UfDataAggregator();
		ufs.setUfs(ufDataAggregator.fillMissingValues(ufs));

		GenerateOutputFile generateOutputFile = new GenerateJsonFile();
		generateOutputFile.generateFile(ufs);

	}
}

