package com.previred.uf;

import com.previred.uf.delegate.UfDelegate;
import com.previred.uf.delegate.ValoresDelegate;
import com.previred.uf.delegate.impl.ValoresDelegateImpl;
import com.previred.uf.service.valores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class UfApplication {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UfApplication p = new UfApplication();
				try {
					p.start(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.exit(1);
			}
		});
	}

	private void start(String[] args) throws Exception {

		File file;
		String Archivo = "valores.json";
		String ruta = "";
		//Si se pasa un argumento, entonces genero el archivo con esa ruta
		if(args.length == 0){
			ruta = System.getProperty("user.dir") + "//" + Archivo;
		}else{
			ruta = args[0];
		}
		file = new File(ruta);
		try {
			if (file.createNewFile())
			{
				//Escribe el contenido
				valores valores = new valores();
				FileWriter writer;
				try {
					writer = new FileWriter(file);
					writer.write(valores.generaJSON());
					writer.close();
					System.out.println("Archivo creado! " + ruta);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Archivo ya existe. " + ruta);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
