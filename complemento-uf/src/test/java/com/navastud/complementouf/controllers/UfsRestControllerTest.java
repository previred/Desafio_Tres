package com.navastud.complementouf.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.navastud.complementouf.converters.UfConverter;
import com.navastud.complementouf.converters.UfsConverter;
import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.dto.UfsDto;
import com.navastud.complementouf.services.UfsService;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;

@RunWith(SpringRunner.class)
@WebMvcTest(UfsRestController.class)
public class UfsRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	@Qualifier("UfsServiceImpl")
	private UfsService ufsService;

	private List<UfDto> listUfs = new ArrayList<UfDto>();

	@Spy
	@Autowired
	private UfConverter ufConverter;

	@Spy
	@Autowired
	private UfsConverter ufsConverter;

	@InjectMocks
	private Valores valores;

	@TestConfiguration
	static class UfsRestControllerTestContextConfiguration {

		@Bean
		public UfConverter UfConverter() {
			return new UfConverter();
		}

		@Bean
		public UfsConverter UfsConverter() {
			return new UfsConverter();
		}

	}

	@Before
	public void setUp() {
		Date date = Utils.localDateToDate(LocalDate.of(2013, 01, 01));
		Date date2 = Utils.localDateToDate(LocalDate.of(2014, 01, 01));
		listUfs = DatosUf.getInstance().getUfs(date, date2).stream().map(uf -> ufConverter.convertUfToUfDto(uf))
				.collect(Collectors.toList());
	}

	@Test
	public void givenStatusOK_whenGetAllUfs_ThenReturnJsonArray() throws Exception {

		when(ufsService.getAllUfs()).thenReturn(listUfs);

		mvc.perform(get("/ufs/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(listUfs.size())))
				.andExpect(jsonPath("$[0].valor", is(listUfs.get(0).getValor().toString())))
				.andExpect(jsonPath("$[0].fecha", is(listUfs.get(0).getFecha().toString())));
	}

	@Test
	public void givenStatusOk_whenGetAllUfs_ThenReturnEmptyJsonArray() throws Exception {

		when(ufsService.getAllUfs()).thenReturn(new ArrayList<UfDto>());

		mvc.perform(get("/ufs/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void givenUfs_WhenGetUfs_ThenReturnJsonObject() throws Exception {

		UfsDto ufs = ufsConverter.convertUfsToUfsDto(valores.getRango());
		when(ufsService.getUfs()).thenReturn(ufs);

		mvc.perform(get("/ufs").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.inicio", is(ufs.getInicio().toString())))
				.andExpect(jsonPath("$.fin", is(ufs.getFin().toString())))
				.andExpect(jsonPath("$.ufs", hasSize(ufs.getUFs().size())));
	}

}
