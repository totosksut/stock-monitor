package com.example.stock.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

//@Configuration
public class ConfigUtility {

	public static Properties readProp() {
		Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("C:\\check_stock\\appconfig.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return properties;
	}
}
