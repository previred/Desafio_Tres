package com.navastud.complementouf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navastud.complementouf.dto.UfDto;
import com.navastud.complementouf.dto.UfsDto;
import com.navastud.complementouf.services.UfsService;

@RestController
@RequestMapping(value = "/ufs", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class UfsRestController {

	@Qualifier("UfsServiceImpl")
	private UfsService ufsService;

	@Autowired
	public UfsRestController(UfsService ufsService) {
		super();
		this.ufsService = ufsService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<UfDto>> getAllUfs() {
		return ResponseEntity.ok(ufsService.getAllUfs());
	}

	@GetMapping("")
	public ResponseEntity<UfsDto> getUfs() {
		return ResponseEntity.ok(ufsService.getUfs());
	}
}
