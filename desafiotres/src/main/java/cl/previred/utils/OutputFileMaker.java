package cl.previred.utils;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class OutputFileMaker {

    private static final String OUTPUT_DIRECTORY = "OutputFiles/";
    private static final Logger logger = Logger.getLogger(OutputFileMaker.class);

    public static void makeJsonFile(String jsonString, String jsonFileName){
        logger.info("The Json File will be stored in the following directory: " + OUTPUT_DIRECTORY + jsonFileName);
        try (FileWriter file = new FileWriter(OUTPUT_DIRECTORY + jsonFileName)) {
            file.write(jsonString);
            file.flush();
            logger.info("The Json File created successfully");
        } catch (IOException e) {

            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

}
