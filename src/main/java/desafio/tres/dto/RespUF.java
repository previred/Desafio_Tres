package desafio.tres.dto;

public class RespUF {

		@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RespUF [fecha=");
		builder.append(fecha);
		builder.append(", dato=");
		builder.append(dato);
		builder.append("]");
		return builder.toString();
	}
		String fecha;
		String dato;
		
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getDato() {
			return dato;
		}
		public void setDato(String dato) {
			this.dato = dato;
		}
		
		
	}
