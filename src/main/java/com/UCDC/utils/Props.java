package com.UCDC.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Props {
    private static Properties defaultProps = new Properties();

    private static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private static Map<String,String> properties =  new ConcurrentHashMap<>();

    static {
        try {
            InputStream input;
            input = new FileInputStream(classLoader.getResource("application.properties").getPath());
            defaultProps.load(input);
            input.close();
            for (final String name: defaultProps.stringPropertyNames())
                properties.put(name, defaultProps.getProperty(name));
//            environmentProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void setProperty(String key, String value){
            properties.put(key,value);
    }

    public static  synchronized String getProperty(String key){
        return properties.get(key);
    }

    /**
     * This method overrides the data for properties file, in case it is executed on local and assume the ones set on
     * applications.properties file or Set them from the environment variables
     * @throws IOException
     * @throws URISyntaxException
     */


}
