package com.previred.challenge.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.challenge.config.EndPoint;
import com.previred.challenge.service.IFileService;
import com.previred.challenge.service.IUfPreviredService;

@RestController
@RequestMapping(EndPoint.V1 + EndPoint.API + EndPoint.UF)
public class GETUfController {

	private final IUfPreviredService ufService;

	private final IFileService fileJsonServiceImpl;
	
	private final IFileService fileXmlServiceImpl;
	
	private final IFileService fileCsvServiceImpl;

	GETUfController(IUfPreviredService ufService, IFileService fileJsonServiceImpl, IFileService fileXmlServiceImpl, IFileService fileCsvServiceImpl) {
		this.ufService = ufService;
		this.fileJsonServiceImpl = fileJsonServiceImpl;
		this.fileXmlServiceImpl = fileXmlServiceImpl;
		this.fileCsvServiceImpl = fileCsvServiceImpl;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(this.ufService.getUf());
	}

	@GetMapping(path = EndPoint.JSON, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Void> getFileJson(HttpServletResponse response) throws IOException {
		response.setHeader("Content-disposition", "attachment; filename=Ufs.json");
		response.getOutputStream().write(this.fileJsonServiceImpl.getFile(this.ufService.getUf()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = EndPoint.XML, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<Void> getFileXml(HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-disposition", "attachment; filename=Ufs.xml");
		response.getOutputStream().write(this.fileXmlServiceImpl.getFile(this.ufService.getUf()));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = EndPoint.CSV, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<byte[]> getFileCsv(HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-disposition", "attachment; filename=Ufs.csv");
		response.getOutputStream().write(this.fileCsvServiceImpl.getFile(this.ufService.getUf()));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
