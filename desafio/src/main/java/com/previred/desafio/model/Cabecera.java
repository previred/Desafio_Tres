package com.previred.desafio.model;

import java.util.ArrayList;
import java.util.Date;

public class Cabecera {
	
	public Date inicio;
	public Date fin;
	public ArrayList<Cuerpo> Ufs;
	
	public Cabecera(Date inicio, Date fin, ArrayList<Cuerpo> Ufs) {
		this.inicio = inicio;
		this.fin = fin;
		this.Ufs = Ufs;
	}

}
