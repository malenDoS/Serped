
package serped;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author malen
 */
public class PlantillaInf {
    
    private int numeroPla;
    private FileOutputStream fos;
    private String titulo;
    private final Document documento;
    private ArrayList datosTexto;
    private String formato;
    private String[][]datosXML;
    BufferedWriter bw;
    
    public PlantillaInf(int plantilla,String titulo,String formato,String[][]datosXML){
        this.numeroPla=plantilla;
        this.titulo=titulo;
        this.datosTexto=new ArrayList<String>();
        this.documento=new Document();
        this.formato=formato;
        this.datosXML=datosXML;
    }
    
    public Boolean generarPDF() throws FileNotFoundException, DocumentException, IOException{
     
       
        
        if(this.numeroPla==1&&this.formato.equals("PDF")){
             //Creo el filaOutpuStream para abrir el canal de salida de datos.
            this.fos=new FileOutputStream(this.titulo+".pdf");
            crearPDF();
           return true;
            
        }else if(this.numeroPla==2&&this.formato.equals("PDF")){
             //Creo el filaOutpuStream para abrir el canal de salida de datos.
            this.fos=new FileOutputStream(this.titulo+".pdf");
            crearPDF();
            return true;
        }else if(this.numeroPla==2||this.formato.equals("TXT")){
            //Creo una instancia de la clase BufferedWriter.
            this.bw=new BufferedWriter(new FileWriter(this.titulo+".txt"));
            crearTXT();
            return true;
        }
        
        return false;
    }
    
    
    public void crearTXT() throws IOException{
        
    
        this.bw.write(this.titulo);
        
        for(int i=0;i<this.datosXML.length;i++){
               
                for(int j=0;j<15;j++){
                    this.datosTexto.add(this.datosXML[i][j]);
                }
        }
      
        
        for(int i=0;i<this.datosTexto.size();i+=15){
        this.bw.write("");
        this.bw.newLine();
        this.bw.write("Localizaci??n: "+this.datosTexto.get(i+2));
        this.bw.newLine();
        this.bw.write("C??digo: "+this.datosTexto.get(i+1));
        this.bw.newLine();
        this.bw.write("A??o: "+this.datosTexto.get(i));
        this.bw.newLine();
        this.bw.write("");
        this.bw.write("Total de personas con grados de dependencia: "+this.datosTexto.get(i+14)+". N??mero de mujeres: "+this.datosTexto.get(i+12)+". N??mero de hombres: "+this.datosTexto.get(i+13)+".");
        this.bw.newLine();
        this.bw.write("Total de personas con grado 1 de dependencia: "+this.datosTexto.get(i+11)+". N??mero de mujeres con grado 1 de dependencia: "+this.datosTexto.get(i+9)+". Numero de hombres con grado 1 de dependencia: "+this.datosTexto.get(i+10)+".");
        this.bw.newLine();
        this.bw.write("Total de personas con grado 2 de dependencia: "+this.datosTexto.get(i+8)+". N??mero de mujeres con grado 2 de dependencia: "+this.datosTexto.get(i+6)+". Numero de hombres con grado 2 de dependencia: "+this.datosTexto.get(i+7)+".");
        this.bw.newLine();
        this.bw.write("Total de personas con grado 3 de dependencia: "+this.datosTexto.get(i+5)+". N??mero de mujeres con grado 3 de dependencia: "+this.datosTexto.get(i+3)+". Numero de hombres con grado 3 de dependencia: "+this.datosTexto.get(i+4)+".");
        this.bw.newLine();
        this.bw.write("");
        this.bw.write("");
        }
        
        this.bw.close();
    }   
    public void crearPDF() throws DocumentException, IOException{
         //Agrego el pdfWriter al documento y al fileOutputStream.
            PdfWriter.getInstance(documento, fos);
            //Crear el p??rrafo para el t??tulo.
            Paragraph parrafoT=new Paragraph(this.titulo);
            //Alinear el t??tulo.
            parrafoT.setAlignment(1);
            Font fuenteTitulo=new Font(Font.FontFamily.HELVETICA,40);
            parrafoT.setFont(fuenteTitulo);
            //Abro el documento y agrego el t??tulo.
            this.documento.open();
            this.documento.add(parrafoT);
            //Agrego un espacio.
            this.documento.add(Chunk.NEWLINE);
            
           for(int i=0;i<this.datosXML.length;i++){
               
                for(int j=0;j<15;j++){
                    this.datosTexto.add(this.datosXML[i][j]);
                }
                ArrayList<Paragraph>parrafos1=new ArrayList<Paragraph>();
                parrafos1=crearParrafosPDF();
                //Agrego los p??rrafos al documento.
                for(int q=0;q<parrafos1.size();q++){
                this.documento.add(parrafos1.get(q));
                this.datosTexto.clear();
               
            }
                 this.documento.add(Chunk.NEWLINE);
                this.documento.add(Chunk.NEWLINE);
            }
            
            
            //Cierro el documento.
            this.documento.close();
            this.fos.close();
     
        
    }
    
  
    
    public ArrayList crearParrafosPDF(){
        ArrayList<Paragraph>parrafo=new ArrayList<Paragraph>();
        Paragraph parrafoC=null;
        Paragraph parrafoA1=null;
        Paragraph parrafoL=new Paragraph("Localizaci??n: "+this.datosTexto.get(2));
            
            parrafoC=new Paragraph("C??digo: "+this.datosTexto.get(1));
            parrafoA1=new Paragraph("A??o: "+this.datosTexto.get(0));
            
            Paragraph parrafoT1=new Paragraph("Total de personas con grados de dependencia: "+this.datosTexto.get(14)+". N??mero de mujeres: "+this.datosTexto.get(12)+". N??mero de hombres: "+this.datosTexto.get(13)+".");
            Paragraph parrafoT2=new Paragraph("Total de personas con grado 1 de dependencia: "+this.datosTexto.get(11)+". N??mero de mujeres con grado 1 de dependencia: "+this.datosTexto.get(9)+". Numero de hombres con grado 1 de dependencia: "+this.datosTexto.get(10)+".");
            Paragraph parrafoT3=new Paragraph("Total de personas con grado 2 de dependencia: "+this.datosTexto.get(8)+". N??mero de mujeres con grado 2 de dependencia: "+this.datosTexto.get(6)+". Numero de hombres con grado 2 de dependencia: "+this.datosTexto.get(7)+".");
            Paragraph parrafoT4=new Paragraph("Total de personas con grado 3 de dependencia: "+this.datosTexto.get(5)+". N??mero de mujeres con grado 3 de dependencia: "+this.datosTexto.get(3)+". Numero de hombres con grado 3 de dependencia: "+this.datosTexto.get(4)+".");
            if(this.numeroPla==2){
                
                parrafoA1.setAlignment(1);
            }
             //Instancio la clase Font para crear la fuente de los p??rrafos.
            Font fuenteParrafos=new Font(Font.FontFamily.HELVETICA,12);
            parrafoL.setFont(fuenteParrafos);
            parrafoC.setFont(fuenteParrafos);
            parrafoA1.setFont(fuenteParrafos);
            parrafoT1.setFont(fuenteParrafos);
            parrafoT2.setFont(fuenteParrafos);
            parrafoT3.setFont(fuenteParrafos);
            parrafoT4.setFont(fuenteParrafos);
            //Agrego la alineaci??n justificada.
            parrafoT1.setAlignment(Element.ALIGN_JUSTIFIED);
            parrafoT2.setAlignment(Element.ALIGN_JUSTIFIED);
            parrafoT3.setAlignment(Element.ALIGN_JUSTIFIED);
            parrafoT4.setAlignment(Element.ALIGN_JUSTIFIED);
            
            parrafo.add(parrafoL);
            if(this.numeroPla==1){
              
                parrafo.add(parrafoC);
                parrafo.add(parrafoA1);
            }
            parrafo.add(parrafoT1);
            parrafo.add(parrafoT2);
            parrafo.add(parrafoT3);
            parrafo.add(parrafoT4);
            
            if(this.numeroPla==2){
                parrafo.add(parrafoA1);
            }
          
            return parrafo;
    }
}
