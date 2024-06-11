package com.report.apireport;

import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws JRException {

        Scanner sc = new Scanner(System.in);

        String URL = "C:/Users/Javi/Documents/Prueba_report.jrxml";

        String usuario;
        String password;
        String texto;



        System.out.println("Introduzca usuario:");
        usuario = sc.nextLine();

        System.out.println("Introduzca contraseña:");
        password = sc.nextLine();

        System.out.println("Texto adicional:");
        texto = sc.nextLine();

        //Cadena que quiero exportar a JSON
        String resultado = (usuario + " " + password + " " + texto + ".");
        System.out.println(resultado);

        //Convertir dicha cadena.
        JSONObject json = new JSONObject();
        json.put("Mensaje", resultado);


        /*
        //Guardamos dicho objeto en un archivo.
        try (FileWriter file = new FileWriter("C:/Users/Javi/IdeaProjects/api-report/src/main/resources/archivo.json")){
            file.write(json.toString(4)); //Numero de espacio en la sangria del archivo.
            System.out.println("Objeto JSON guardado en archivo.json");
        }catch (IOException e){
            e.printStackTrace();
        }
        */

        String rawJsonData = json.toString();
        //Load compiled jasper report that we created on first section.
        JasperReport report = (JasperReport) JRLoader.loadObject(new File("src/main/resources/Principal.jasper"));
        //Convert json string to byte array.
        ByteArrayInputStream jsonDataStream = new ByteArrayInputStream(rawJsonData.getBytes());
        //Create json datasource from json stream
        JsonDataSource ds = new JsonDataSource(jsonDataStream);
        //Create HashMap to add report parameters
        Map parameters = new HashMap();
        //Add title parameter. Make sure the key is same name as what you named the parameter in jasper report.
        parameters.put("title", "Prueba 1 pedofile");
        //Create Jasper Print object passing report, parameter json data source.
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, ds);
        //Export and save pdf to file
        JasperExportManager.exportReportToPdfFile(jasperPrint,"C:/Users/Javi/IdeaProjects/api-report/src/main/resources/archivo.pdf");




    }


}
