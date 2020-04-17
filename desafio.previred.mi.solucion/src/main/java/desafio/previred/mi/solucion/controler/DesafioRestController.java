package desafio.previred.mi.solucion.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import desafio.previred.mi.solucion.entities.ViewUfs;
import desafio.previred.mi.solucion.service.ServiceManager;

@RestController
@RequestMapping("/api")
public class DesafioRestController {

	@Autowired	
	private ServiceManager servicefun ;
		
	@RequestMapping(value = "/valores", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<?> getValoresJson() {

		ViewUfs ufs = new ViewUfs();
		ufs = servicefun.getUfs();
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"valores.json");
		

		return new ResponseEntity<ViewUfs>( ufs ,cabecera , HttpStatus.OK);	
	}
	

	
}
