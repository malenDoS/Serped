
package serped;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.DoubleStream.builder;
import static java.util.stream.IntStream.builder;
import static java.util.stream.LongStream.builder;
import static java.util.stream.Stream.builder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ConsumirXML {
    
    private String direccionRecurso;
    private Integer respuesta;
    private URL urlRecurso;
    private ArrayList listaAgnos;
    private ArrayList listaLugares;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder builder;
    private HttpURLConnection conexion;
    private Document document;
    private ArrayList datosPantalla;
    private String[][] datosArchivos;
    
    public ConsumirXML() throws MalformedURLException, ProtocolException, IOException, ParserConfigurationException, SAXException{
        datosPantalla=new ArrayList<String>();
    this.direccionRecurso="https://analisi.transparenciacatalunya.cat/api/views/fp8n-2tg5/rows.xml?accessType=DOWNLOAD";
       
        this.urlRecurso=new URL(this.direccionRecurso);
        //Abro la conexión.
        HttpURLConnection conexion=(HttpURLConnection)urlRecurso.openConnection();
        //Uso el método GET para conseguir el recurso.
        conexion.setRequestMethod("GET");
        //Realizo la conexion.
        conexion.connect();
        //Compruebo la respuesta de la conexión.
        this.respuesta=conexion.getResponseCode();
        
             this.dbf=DocumentBuilderFactory.newInstance();
             this.builder=dbf.newDocumentBuilder();
             //Abro un flujo de datos para leer los datos.
             this.document=builder.parse(urlRecurso.openStream());
}
    
    public ArrayList<String> getItemsAgno() throws MalformedURLException, IOException, ParserConfigurationException, XPathExpressionException, SAXException{
        this.listaAgnos=new ArrayList<String>();
    
        
        if(respuesta==200){
             //Creo una lista de nodos.
             NodeList listaAgnos=document.getElementsByTagName("any");
             //Recorro la lista de nodos.
             for(int i=0;i<listaAgnos.getLength();i++){
                 Node nodo=listaAgnos.item(i);
                 //Guardo los años en la lista.
                 if(!this.listaAgnos.contains(nodo.getTextContent())){
                 this.listaAgnos.add(nodo.getTextContent());
                 }
             }
             
             //Devuelvo la lista de años.
             return this.listaAgnos;
           
          
         
	}
           return null;
    }
    
    public ArrayList<String> getItemsLugar(){
        this.listaLugares=new ArrayList<String>();
        
        if(respuesta==200){
            NodeList listaNodosL=this.document.getElementsByTagName("comarca");
            //Recorro la lista de nodos.
            for(int i=0;i<listaNodosL.getLength();i++){
                Node nodoL=listaNodosL.item(i);
                if(!this.listaLugares.contains(nodoL.getTextContent())){
                    this.listaLugares.add(nodoL.getTextContent());
                }
            }
            return this.listaLugares;
        }
        return null;
    }
    
    public ArrayList<String> buscarConsulta(ArrayList<String>datos){
        
        int busqueda=0;
        if(datos.get(2).equals("Masculino")&&datos.get(3).equals("1")){
            busqueda=10;
        }else if(datos.get(2).equals("Masculino")&&datos.get(3).equals("2")){
           busqueda=7; 
        }else if(datos.get(2).equals("Masculino")&&datos.get(3).equals("3")){
            busqueda=4; 
        }else if(datos.get(2).equals("Femenino")&&datos.get(3).equals("1")){
            busqueda=9;
        }else if(datos.get(2).equals("Femenino")&&datos.get(3).equals("2")){
            busqueda=6;
        }else if(datos.get(2).equals("Femenino")&&datos.get(3).equals("3")){
            busqueda=3;
        }
        
        
        if(respuesta==200){
            datosPantalla=new ArrayList<String>();
            datosPantalla.add(datos.get(0));
            datosPantalla.add(datos.get(1));
            datosPantalla.add(datos.get(2));
            datosPantalla.add(datos.get(3));
            NodeList listaNodosR=this.document.getElementsByTagName("row");
            
           for(int i=0;i<listaNodosR.getLength();i++){
               
             
               if(datos.get(0).equals(listaNodosR.item(i).getChildNodes().item(0).getTextContent())&&datos.get(1).equals(listaNodosR.item(i).getChildNodes().item(2).getTextContent())){
                   datosPantalla.add(listaNodosR.item(i).getChildNodes().item(busqueda).getTextContent());
                   datosPantalla.add(listaNodosR.item(i).getChildNodes().item(14).getTextContent());
               }
           }
           
           
           return datosPantalla;
        }
        return null;
    }
    
    
    public String[][] datosEs(String lugar) throws IOException, MalformedURLException, ParserConfigurationException, XPathExpressionException, SAXException{
        
        if(respuesta==200){
           ArrayList agnoGuardados=new ArrayList<String>();
            //Compruebo cuantos años están registrados en el xml.
            ArrayList agnos=new ArrayList<String>();
           //Inicializo el array para guardar los datos del xml.
            agnos=getItemsAgno();
            this.datosArchivos=new String[agnos.size()][15];
            NodeList nodos=this.document.getElementsByTagName("row");
           
            
         int contador=0;
               for(int i=0;i<nodos.getLength();i++){
               
                   for(int j=0;j<this.datosArchivos.length;j++){
                       for(int q=0;q<this.datosArchivos[0].length;q++){
             
               if(lugar.equals(nodos.item(i).getChildNodes().item(2).getTextContent())&&nodos.item(i).getChildNodes().item(0).getTextContent().equals(agnos.get(j))){
                   
                   this.datosArchivos[j][q]=(nodos.item(i).getChildNodes().item(q).getTextContent());
               
               }
                       }
                       
             }
                   
           }
                  
                      
            
          
            
             
              
              return this.datosArchivos;
            
        }
        return null;
    }
}

            
            

