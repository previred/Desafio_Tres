package com.navastud.complementouf.converters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

import com.navastud.complementouf.dto.UfsDto;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Ufs;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UfsConverterTest {

	@InjectMocks
	private Valores valores;

	@InjectMocks
	private Ufs ufs;

	@InjectMocks
	private UfsDto ufsDto;

	@Spy
	@Autowired
	@Qualifier("UfsConverter")
	private UfsConverter ufsConverter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ufs = valores.getRango();
	}

	@Test
	public void whenGetConverterUfsToUfsDto_ThenReturnUfsDtoObjectTest() {
		when(ufsConverter.convertUfsToUfsDto(ufs)).thenReturn(ufsDto);
		assertThat(ufsConverter.convertUfsToUfsDto(ufs)).isNotNull().isEqualTo(ufsDto)
				.hasFieldOrPropertyWithValue("inicio", ufsDto.getInicio())
				.hasFieldOrPropertyWithValue("fin", ufsDto.getFin());
	}

	public void whenGetConverterUfsDtoToUfs_ThenReturnUfsObjectTest() {
		when(ufsConverter.convertUfsDtoToUfs(ufsDto)).thenReturn(ufs);
		assertThat(ufsConverter.convertUfsDtoToUfs(ufsDto)).isNotNull().isEqualTo(ufs)
				.hasFieldOrPropertyWithValue("inicio", ufs.getInicio())
				.hasFieldOrPropertyWithValue("fin", ufs.getFin());
	}
}
