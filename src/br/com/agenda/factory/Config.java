package br.com.agenda.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config(String fileName) throws IOException {
        properties = new Properties();
        FileInputStream input = new FileInputStream(fileName);
        properties.load(input);
        input.close();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

