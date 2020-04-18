package com.previred.challenge.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
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

	@GetMapping(path = EndPoint.JSON)
	public ResponseEntity<byte[]> getFileJson(HttpServletResponse response) throws IOException {
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=Ufs.json")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
				.body(this.fileJsonServiceImpl.getFile(this.ufService.getUf()));
	}

	@GetMapping(path = EndPoint.XML)
	public ResponseEntity<byte[]> getFileXml(HttpServletResponse response) throws IOException {
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=Ufs.xml")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
				.body(this.fileXmlServiceImpl.getFile(this.ufService.getUf()));
	}

	@GetMapping(path = EndPoint.CSV)
	public ResponseEntity<byte[]> getFileCsv(HttpServletResponse response) throws IOException {
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=Ufs.csv")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
				.body(this.fileCsvServiceImpl.getFile(this.ufService.getUf()));
	}
	
}
