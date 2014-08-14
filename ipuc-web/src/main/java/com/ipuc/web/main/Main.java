
package com.ipuc.web.main;

import java.io.IOException;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author wilson-rivera
 */
public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args) throws IOException, ParseException, InterruptedException {
        try {
            String[] contextLocations = {
                "classpath*:base-context.xml",
                "classpath*:web-context.xml"
            };
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextLocations);
            context.registerShutdownHook();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
