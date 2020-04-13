package com.navastud.complementouf.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.navastud.complementouf.models.DateRange;
import com.navastud.complementouf.utls.Utils;
import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UfsRepositoryTest {

	@Spy
	@Autowired
	@Qualifier("UfsRepository")
	private UfsRepository ufsRepository;

	@InjectMocks
	private Valores valores;

	@InjectMocks
	private Ufs ufs;

	@InjectMocks
	private DatosUf datosUf = DatosUf.getInstance();

	@InjectMocks
	private DateRange dateRange;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ufs = valores.getRango();
	}

	@Test
	public void whenGetUfsFromRepository_ThenReturnSameUfsTest() {
		when(ufsRepository.findUfs()).thenReturn(Optional.of(ufs));
		assertThat(ufsRepository.findUfs().get()).isNotNull().isEqualTo(ufs)
				.hasFieldOrPropertyWithValue("inicio", ufs.getInicio()).hasFieldOrPropertyWithValue("fin", ufs.getFin())
				.hasFieldOrPropertyWithValue("ufs", ufs.getUfs());
	}

	@Test
	public void whenGetSetUfsFromRepository_ThenReturnDiferentSetUfsFromUfsTest() {

		when(ufsRepository.findUfs()).thenReturn(Optional.of(ufs));

		LocalDate ldInicio = Utils.dateToLocalDate(ufsRepository.getFechaInicio());
		LocalDate ldFin = Utils.dateToLocalDate(ufsRepository.getFechaFin());
		dateRange = new DateRange(ldInicio, ldFin);

		assertThat(ufsRepository.getListUfsOrderDescend()).isNotNull().isNotEqualTo(ufs.getUfs())
				.hasSize(dateRange.countDays());
	}

	@Test
	public void whenGetSetUfsFromRepository_ThenReturnSameSetUfsFromUfsTest() {

		LocalDate ldInicio = Utils.dateToLocalDate(ufs.getInicio());
		LocalDate ldFin = Utils.dateToLocalDate(ufs.getFin());
		dateRange = new DateRange(ldInicio, ldFin);

		Set<Uf> setUfs = ufs.getUfs();
		List<Date> dates = setUfs.parallelStream().map(uf -> uf.getFecha()).collect(Collectors.toList());

		for (Date date : dateRange.toDateList()) {
			if (!dates.contains(date)) {
				setUfs.add(datosUf.getUf(date));
			}
		}

		List<Uf> listUfs = setUfs.stream().sorted((a, b) -> b.getFecha().compareTo(a.getFecha()))
				.collect(Collectors.toList());

		when(ufsRepository.getListUfsOrderDescend()).thenReturn(listUfs);
		assertThat(ufsRepository.getListUfsOrderDescend()).isNotNull().isEqualTo(listUfs)
				.hasSize(dateRange.countDays());
	}

	@Test
	public void getListUfsFromRespositoryWithRestDatesIsOrderDescendTest() throws Exception {

		Ufs ufsRepo = ufsRepository.findUfs().get();
		List<Uf> listUfs = ufsRepository.getListUfsOrderDescend();

		assertEquals(listUfs.get(0).getFecha(), ufsRepo.getInicio());
		assertEquals(listUfs.get(listUfs.size() - 1).getFecha(), ufsRepo.getFin());
	}

}
