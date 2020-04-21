package com.previred.desafio.tres.dto;

/**
 * Objeto que contiene respuesta de la generacion de la lista de Ufs
 * @author Luis San Martin
 *
 */
public class RespuestaDto {
	private Integer codigo;
	String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "[codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}
}
