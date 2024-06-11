package com.report.apireport.controller;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.PSource;
import java.util.Collection;

@RestController
public class ApiController {

    @GetMapping("/health")
    String health(){
        return "OK";
    }
    @GetMapping("/generarPdf")
    void generarReporte(String json){
        return ;
    }




}
