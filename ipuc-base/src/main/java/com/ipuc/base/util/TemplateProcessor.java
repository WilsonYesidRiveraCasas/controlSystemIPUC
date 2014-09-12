package com.ipuc.base.util;

import java.io.File;
import java.io.IOException;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
/**
 *
 * @author Carlos Sepulveda
 */
public class TemplateProcessor {

    public String DEFAULT_ENCODING = "UTF-8";

    public String DEFAULT_PATH = "templates/";

    private String encoding;

    private String pathFolder;

    private Configuration freemarker;
    
    public TemplateProcessor() throws IOException {
        this.encoding = DEFAULT_ENCODING;
        this.pathFolder = DEFAULT_PATH;
    }

    public String process(String path) throws TemplateException, IOException {
        return process(path, null);
    }

    public String process(String path, Map<String, Object> info) throws TemplateException, IOException {
        configure();
        return processTemplate(path, info);
    }

    private void configure() throws IOException {
        freemarker = new Configuration();
        freemarker.setDefaultEncoding(encoding);
        freemarker.setDirectoryForTemplateLoading(new File(pathFolder));
    }

    private String processTemplate(String templateName, Map<String, Object> root) throws TemplateException, IOException {
        Writer out = new StringWriter();
        freemarker.getTemplate(templateName).process(root, out);
        return out.toString();
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getPathFolder() {
        return pathFolder;
    }

    public void setPathFolder(String path) {
        this.pathFolder = path;
    }
}
