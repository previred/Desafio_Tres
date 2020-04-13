package com.navastud.complementouf.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.navastud.complementouf.converters.UfConverter;
import com.navastud.complementouf.converters.UfsConverter;
import com.navastud.complementouf.exception.UfsNotFoundException;
import com.navastud.complementouf.repositories.UfsRepository;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UfsServiceTest {

	@Autowired
	@Qualifier("UfsServiceImpl")
	private UfsService ufsService;

	@MockBean
	@Qualifier("UfsRepository")
	private UfsRepository ufsRepository;

	@Autowired
	@Qualifier("UfsConverter")
	private UfsConverter ufsConverter;

	@Autowired
	@Qualifier("UfConverter")
	private UfConverter ufConverter;

	@InjectMocks
	private Valores valores;

	private List<Uf> listUfs = new ArrayList<Uf>();

	@Before
	public void setUp() {
		Date date = Utils.localDateToDate(LocalDate.of(2012, 01, 01));
		Date date2 = Utils.localDateToDate(LocalDate.of(2013, 01, 01));
		listUfs = DatosUf.getInstance().getUfs(date, date2);
	}

	@Test
	public void whenGetListUfs_ThenAllListUfsShouldBeFound() {
		when(ufsRepository.getListUfsOrderDescend()).thenReturn(listUfs);
		assertThat(ufsService.getAllUfs()).isNotNull()
				.isEqualTo(listUfs.stream().map(uf -> ufConverter.convertUfToUfDto(uf)).collect(Collectors.toList()))
				.hasSize(listUfs.size());
	}

	@Test
	public void whenGetListUfs_ThenAllListUfsShouldBeEmpty() {
		when(ufsRepository.getListUfsOrderDescend()).thenReturn(new ArrayList<Uf>());
		assertThat(ufsService.getAllUfs()).isNotNull().isEmpty();
	}

	@Test
	public void whenGetUfs_ThenUfsShouldBeFound() {
		Ufs ufs = valores.getRango();
		when(ufsRepository.findUfs()).thenReturn(Optional.of(ufs));
		assertThat(ufsService.getUfs()).isNotNull().isEqualTo(ufsConverter.convertUfsToUfsDto(ufs));
	}

	@Test
	public void whenGetUfs_ThenUfsShoulNoBeFound() {
		assertThrows(UfsNotFoundException.class, () -> ufsService.getUfs());
	}
}
