package sugimomoto.samplecrud.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyFileManager {

    private String propartyPath;

    private Properties properties;

    public PropertyFileManager(String propertyPath){
        this.propartyPath = propertyPath;
        this.InitializePropertyFile();
    }

    private void InitializePropertyFile(){

        Path path = Paths.get(propartyPath);

        try(BufferedReader reader = Files.newBufferedReader(path,StandardCharsets.UTF_8)){
            properties = new Properties();
            properties.load(reader);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public String GetPropertyValue(String keyName){
        return properties.getProperty(keyName);
    }
}

