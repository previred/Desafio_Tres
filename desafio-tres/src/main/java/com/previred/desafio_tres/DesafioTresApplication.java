package com.previred.desafio_tres;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.previred.common.ComplementoUf;
import com.previred.common.PreviredException;
import com.previred.common.SortedUf;
import com.previred.common.SortedUfs;
import com.previred.desafio.tres.uf.Valores;
import com.previred.desafio.tres.uf.vo.Uf;
import com.previred.desafio.tres.uf.vo.Ufs;

@SpringBootApplication
public class DesafioTresApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(DesafioTresApplication.class);
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HHmmss");
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		
		SpringApplication.run(DesafioTresApplication.class, args);
		
	}	
	
	@Bean
	public CommandLineRunner example() {
		
		return new CommandLineRunner() {
		
			@Override
			public void run(String... args) throws Exception {
			
				LOGGER.info("Staring Desafio Tres Application...");
				
				try {
					
					Valores valores = new Valores();
					Ufs ufs = valores.getRango();
					
					LOGGER.info("Values : start {}, end {}", DATE_FORMAT.format(ufs.getInicio()), DATE_FORMAT.format(ufs.getFin()));
					LOGGER.info("Known values :");
					
					for(Uf uf : ufs.getUfs()) {
					
						LOGGER.info("{} : {}", DATE_FORMAT.format(uf.getFecha()), uf.getValor());
						
					}
					
					LOGGER.info("");
					
					List<SortedUf> complementedAndOrderedList = ComplementoUf.getComplementedRange(ufs);
					LOGGER.info("Complemented and ordered list:");
					
					SortedUfs sufs = new SortedUfs(ufs.getInicio(), ufs.getFin(), complementedAndOrderedList);
					
					DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
					decimalFormatSymbols.setDecimalSeparator(env.getProperty("format.decimalSeparator").charAt(0));
					decimalFormatSymbols.setGroupingSeparator(env.getProperty("format.groupingSeparator").charAt(0));
					DecimalFormat decimalFormat = new DecimalFormat(env.getProperty("format.double"), decimalFormatSymbols);
								
					Gson gson = new GsonBuilder()
				        .setDateFormat(env.getProperty("format.date"))
				        .registerTypeAdapter(Double.class, new JsonSerializer<Double>()  {
				        
				        	public JsonElement serialize(Double value, Type theType, JsonSerializationContext context) {
				        		
				        		return new JsonPrimitive(decimalFormat.format(value));
				        		
				        	}
				        	
				        })
				        .setPrettyPrinting()
				        .create();
				    
					//defining the file full path
					String filePath = null;
					
					if(args.length > 0) {
					
						filePath = args[0];
						
					} else {
						
						filePath = env.getProperty("path.default");
						
					}
					
					Path file = Paths.get(filePath);
					Files.write(file, gson.toJson(sufs).getBytes());
								
					LOGGER.info("Ending Desafio Tres Application...");			
					
				} catch(PreviredException e) {
					
					LOGGER.info("There was an errror runnign the application : {}", e.getMessage());
					
				}
				
			}
			
		};
		
	}

}
