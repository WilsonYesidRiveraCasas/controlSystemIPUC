/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.web.server;

import com.ipuc.web.interceptor.ExceptionInterceptor;
import com.ipuc.web.interceptor.SecurityInterceptor;
import freemarker.template.Configuration;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import org.jogger.Jogger;
import org.jogger.middleware.router.RouterMiddleware;
import org.jogger.middleware.router.interceptor.Interceptor;
import org.jogger.middleware.router.loader.ControllerLoader;
import org.jogger.middleware.router.loader.FileSystemRoutesLoader;
import org.jogger.middleware.statik.StaticMiddleware;
import org.jogger.template.FreemarkerTemplateEngine;

/**
 *
 * @author wilson-rivera
 */
public class Server {

    private ControllerLoader controllerLoader;

    public void initServer() throws ParseException, InterruptedException, IOException {

        FileSystemRoutesLoader routesLoader = new FileSystemRoutesLoader("routes.config");
        routesLoader.setControllerLoader(controllerLoader);

        RouterMiddleware router = new RouterMiddleware();
        router.setRoutes(routesLoader.load());

        StaticMiddleware statik = new StaticMiddleware("static");

        Interceptor exceptionInterceptor = new ExceptionInterceptor();
        Interceptor securityInterceptor = new SecurityInterceptor();
        router.addInterceptor(exceptionInterceptor);
        router.addInterceptor(securityInterceptor);

        Jogger app = new Jogger(statik, router);

        setTemplateEngine(app);
        app.listen(8000);
        app.join();
    }

    private static void setTemplateEngine(Jogger app) throws IOException {
        Configuration freemarker = new Configuration();
        freemarker.setDirectoryForTemplateLoading(new File("templates/"));
        freemarker.setDefaultEncoding("UTF-8");
        app.setTemplateEngine(new FreemarkerTemplateEngine(freemarker));
    }

    public void setControllerLoader(ControllerLoader controllerLoader) {
        this.controllerLoader = controllerLoader;
    }

}
