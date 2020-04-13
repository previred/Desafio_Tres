package com.navastud.complementouf.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.vo.Uf;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UfConverterTest {

	@InjectMocks
	private DatosUf datosUf;

	@InjectMocks
	private Uf uf;

	@InjectMocks
	private UfDto ufDto;

	@Spy
	@Autowired
	@Qualifier("UfConverter")
	private UfConverter ufConverter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		uf = datosUf.getUf(Utils.localDateToDate(LocalDate.of(2012, 12, 01)));
		ufDto.setFecha(LocalDate.now());
		ufDto.setValor(Utils.doubleToString(uf.getValor()));
	}

	@Test
	public void whenGetConverterUfToUfDto_ThenReturnUfDtoObjectTest() {
		when(ufConverter.convertUfToUfDto(uf)).thenReturn(ufDto);
		assertThat(ufConverter.convertUfToUfDto(uf)).isNotNull().isEqualTo(ufDto).hasFieldOrPropertyWithValue("valor",
				Utils.doubleToString(uf.getValor()));
	}

	@Test
	public void whenGetConverterUfDtoToUf_ThenReturnUfObjectTest() {
		when(ufConverter.convertUfDtoToUf(ufDto)).thenReturn(uf);
		assertThat(ufConverter.convertUfDtoToUf(ufDto)).isNotNull().isEqualTo(uf);
	}

}
