/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipuc.base.util;

import com.ipuc.base.exception.ReportException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author wilson-rivera
 */
public final class ReportsProcessor {
    
    private final static String DEFAULT_PATH = "reports/";
    
    private final static String DEFAULT_PATH_TEST = "/home/wilson-rivera/controlSystemIPUC/ipuc-web/reports/";
    
    private final static String EXTENSION_FILE = ".jasper";
    
    private ReportsProcessor() {
    }
    
    public static byte[] process(String report, Map<String, Object> parametros) throws ReportException {
        String directoryForTemplateLoading = DEFAULT_PATH + report + EXTENSION_FILE;
        try {
            JasperReport ireport = (JasperReport) JRLoader.loadObjectFromFile(directoryForTemplateLoading);
            JasperPrint jprint = JasperFillManager.fillReport(ireport, parametros);
            return JasperExportManager.exportReportToPdf(jprint);
        } catch (JRException ex) {
            throw new ReportException("Error process report " + report + ". " + ex.getMessage());
        }
    }
    
    public static void processFile(String report, Map<String, Object> parametros) throws ReportException {
        String directoryForTemplateLoading = DEFAULT_PATH_TEST + report + EXTENSION_FILE;
        try {
            JasperReport ireport = (JasperReport) JRLoader.loadObjectFromFile(directoryForTemplateLoading);
            
            //JasperReport ireport2 = JasperCompileManager.compileReport(directoryForTemplateLoading);
            
            JRParameter[] params = ireport.getParameters();
            for(JRParameter param : params) {
                System.out.println(param.getName());
            }
            
            JasperPrint jprint = JasperFillManager.fillReport(ireport, parametros);
            JasperExportManager.exportReportToPdfFile(jprint, "/home/wilson-rivera/Documentos/reporte.pdf");
        } catch (JRException ex) {
            throw new ReportException("Error process report " + report + ". " + ex.getMessage());
        }
    }
    
    public static void main(String args[]) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("nombreCreyente", "Wilson rivera");
        parametros.put("nombreCongregacion", "policarpa");
        try {
            ReportsProcessor.processFile("certificadoMembresia2", parametros);
        } catch (ReportException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
