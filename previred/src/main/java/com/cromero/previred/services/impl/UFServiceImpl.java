package com.cromero.previred.services.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cromero.previred.dto.UFDTO;
import com.cromero.previred.dto.UFValueDTO;
import com.cromero.previred.services.UFService;
import com.cromero.previred.services.model.UFDTOModel;
import com.cromero.previred.services.model.UFValueModel;
import com.cromero.previred.services.utils.SortByUFDate;
import com.cromero.previred.util.UtilDates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@Service
public class UFServiceImpl implements UFService {

	@Override
	public UFValueDTO getUfs() throws Exception {
		Valores valores = new Valores();
		UFValueDTO response = this.getUFValueDTO(valores.getRango());
		SortByUFDate c = new SortByUFDate();
		response.getUFs().sort(c);
		this.writeFile(response);

		return response;
	}

	private void writeFile(UFValueDTO response) throws Exception {
		UFValueModel result = this.getModel(response);
		try {
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			String representacionBonita = prettyGson.toJson(result);
			try (FileWriter file = new FileWriter("valores.json")) {
				file.write(representacionBonita);
				file.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JsonIOException e) {
			throw new Exception(e.getMessage());
		}

	}

	private UFValueModel getModel(UFValueDTO uFValueDTO) {
		UFValueModel response = new UFValueModel();
		response.setFin(this.getStringDate(uFValueDTO.getFin(), "dd-MM-yyyy"));
		response.setInicio(this.getStringDate(uFValueDTO.getInicio(), "dd-MM-yyyy"));
		response.setUFs(new ArrayList<>());
		uFValueDTO.getUFs().forEach(item -> {
			UFDTOModel e = new UFDTOModel();
			e.setDato(item.getDato());
			e.setFecha(this.getStringDate(item.getFecha(), "dd-MM-yyyy"));
			response.getUFs().add(e);
		});
		return response;
	}

	private String getStringDate(Date date, String format) {
		SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(format);
		String response = DATE_TIME_FORMAT.format(date);
		return response;
	}

	private UFValueDTO getUFValueDTO(Ufs ufs) {
		if (ufs == null) {
			return null;
		}
		UFValueDTO response = new UFValueDTO();
		response.setFin(ufs.getFin());
		response.setInicio(ufs.getInicio());
		response.setUFs(this.getUFList(ufs.getInicio(), ufs.getFin(), ufs.getUfs()));
		return response;
	}

	private List<UFDTO> getUFList(Date initDate, Date endDate, Set<Uf> ufs) {
		List<UFDTO> response = this.getArrayOfDates(initDate, endDate);
		response.forEach(value -> {
			Double dato = null;
			dato = this.getDato(value.getFecha(), ufs, DatosUf.getInstance().getUfs(initDate, endDate));
			value.setDato(dato);
		});
		return response;
	}

	private Double getDato(Date fecha, Set<Uf> ufs, List<Uf> ufsRangeDate) {
		if (fecha == null) {
			return null;
		}
		if (ufs == null) {
			return null;
		}
		Uf uf = this.getUfFromSet(fecha, ufs);
		if (uf != null) {
			return uf.getValor();
		}

		uf = this.getUfFromDatosUf(fecha, ufsRangeDate);
		if (uf != null) {
			return uf.getValor();
		}
		return null;
	}

	private Uf getUfFromSet(Date fecha, Set<Uf> ufs) {
		Uf response = null;
		Object[] arr = ufs.toArray();
		for (int i = 0; i < arr.length; i++) {
			Uf uf = (Uf) arr[i];
			if (uf.getFecha().compareTo(fecha) == 0) {
				return uf;
			}
		}
		return response;
	}

	private Uf getUfFromDatosUf(Date fecha, List<Uf> ufsRangeDate) {
		Uf response = null;
		Object[] arr = ufsRangeDate.toArray();
		for (int i = 0; i < arr.length; i++) {
			Uf uf = (Uf) arr[i];
			if (uf.getFecha().compareTo(fecha) == 0) {
				return uf;
			}
		}

		try {
			response = DatosUf.getInstance().getUf(fecha);
			return response;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private List<UFDTO> getArrayOfDates(Date initDate, Date endDate) {
		List<UFDTO> response = new ArrayList<>();
		Date currentDate = initDate;
		while ((currentDate.compareTo(endDate) == -1) || (currentDate.compareTo(endDate) == 0)) {
			UFDTO e = new UFDTO();
			Double dato = 0.0;
			e.setDato(dato);
			e.setFecha(currentDate);
			response.add(e);
			currentDate = UtilDates.addDays(currentDate, 1);
		}
		return response;
	}
}
