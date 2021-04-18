package cl.previred.desafio.tres;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.previred.desafio.tres.uf.DatosUf;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

import cl.previred.desafio.tres.util.DatesUtil;
import cl.previred.desafio.tres.util.FileUtil;

@SpringBootApplication
public class PreviredDesafioTresApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(PreviredDesafioTresApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PreviredDesafioTresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("=================== INICIO EJECUCION DESAFIO TRES ===================");

		try {
			
			Ufs rango = (new Valores()).getRango();
			LocalDate fechaInicio = DatesUtil.toLocalDate(rango.getInicio());
			LocalDate fechaTermino = DatesUtil.toLocalDate(rango.getFin());
			long nDias = fechaInicio.until(fechaTermino, ChronoUnit.DAYS) + 1;
			Set<Uf> ufsEncontradas = rango.getUfs();

			// @formatter:off
			List<Uf> ufsFaltantes = Stream.iterate(fechaInicio, f -> f.plusDays(1)).limit(nDias)
					.filter(f -> ufsEncontradas.stream().noneMatch(uf -> DatesUtil.toLocalDate(uf.getFecha()).isEqual(f)))
					.map(f -> DatosUf.getInstance().getUf(DatesUtil.toDate(f)))
					.collect(Collectors.toList());

			List<Uf> ufsCompletas = Stream.of(ufsEncontradas, ufsFaltantes)
					.flatMap(Collection::stream)
					.sorted(Comparator.comparing(Uf::getFecha))
					.collect(Collectors.toList());
			// @formatter:on

			LOG.info("Analizando valores faltantes entre el [{}] y [{}]", fechaInicio, fechaTermino);
			LOG.info("Cantidad de valores totales: [{}]", nDias);
			LOG.info("Cantidad de valores encontrados: [{}]", ufsEncontradas.size());
			LOG.info("Cantidad de valores faltantes: [{}]", ufsFaltantes.size());
			
			FileUtil.createJson(fechaInicio, fechaTermino, ufsCompletas);
		} catch (Exception e) {
			LOG.error("Error al analizar las UFs");
		} finally {
			LOG.info("=================== TERMINO EJECUCION DESAFIO TRES ==================");
		}

	}
}
