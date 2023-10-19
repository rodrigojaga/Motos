package MODELOVENDEDORES;


import POO.facturaPOO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;

/**
 * 
 * @author rodri
 * Clase que crea el PDF de la factura de la compra del usuario 
 */
public class crearPDF {
    
    public String ruta;
    Document documento = new Document();
    FileOutputStream fileOutputStream;    
    com.itextpdf.text.Font fuenteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);
    com.itextpdf.text.Font fuenteParrafo = FontFactory.getFont(FontFactory.HELVETICA, 10);
    com.itextpdf.text.Font fuenteParrafo1 = FontFactory.getFont(FontFactory.HELVETICA, 9);
    com.itextpdf.text.Font fuenteParrafo2 = FontFactory.getFont(FontFactory.HELVETICA, 8);
    com.itextpdf.text.Font fuenteParrafoNegrita = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.BOLD);
    com.itextpdf.text.Font fuenteParrafoCursiva = FontFactory.getFont(FontFactory.HELVETICA, 8,Font.ITALIC);
    String nombreArchivo;
    
    
     //Reutilizable
    /**
     * Comienza con la creacion de la factura, dandole nombre y recibe por parametros
     * todos los datos necesarios para hacer la factura, lanza excepciones en 
     * caso de que algo salga mal
     * @param as
     * @param noFactura
     * @param fecha
     * @param hora
     * @param nombreVendedor
     * @param noCaja
     * @param nitCliente
     * @param nombreCliente
     * @param total
     * @throws FileNotFoundException
     * @throws DocumentException 
     */
    public void crearDocumento(LinkedList<facturaPOO> as,String noFactura, String fecha,String hora,String nombreVendedor,String noCaja, String nitCliente, String nombreCliente,String total) throws FileNotFoundException, DocumentException{
        //Tipo de hoja y margenes left-right-top-bottom
        
        documento = new Document(PageSize.A4,75,75,50,50);
        nombreArchivo = "FACTURA_ELECTRONICA_TALLERMOTOSPRO_"+nombreCliente+"_"+fecha+".pdf";
        fileOutputStream = new FileOutputStream(nombreArchivo);
        PdfWriter.getInstance(documento, fileOutputStream);
        abrir(as,noFactura,fecha,hora,nombreVendedor,noCaja,nitCliente,nombreCliente,total);
        
    }

    /**
     * Comienza a crear los encabezados de la factura
     * Estos metodos se ejecutan en cadena hasta la finalizacion de la factura
     * @param as
     * @param noFactura
     * @param fecha
     * @param hora
     * @param nombreVendedor
     * @param noCaja
     * @param nitCliente
     * @param nombreCliente
     * @param total
     * @throws DocumentException 
     */
    public void abrir(LinkedList<facturaPOO> as,String noFactura, String fecha,String hora,String nombreVendedor,String noCaja, String nitCliente, String nombreCliente,String total) throws DocumentException{
        documento.open();
        titulo(as,noFactura,fecha,hora,nombreVendedor,noCaja,nitCliente,nombreCliente,total);
    }
    
    /**
     * Comienza a crear los encabezados de la factura
     * Estos metodos se ejecutan en cadena hasta la finalizacion de la factura
     * @param as
     * @param noFactura
     * @param fecha
     * @param hora
     * @param nombreVendedor
     * @param noCaja
     * @param nitCliente
     * @param nombreCliente
     * @param total
     * @throws DocumentException 
     */
    public void titulo(LinkedList<facturaPOO> as,String noFactura, String fecha,String hora,String nombreVendedor,String noCaja, String nitCliente, String nombreCliente,String total) throws DocumentException{
        PdfPTable tabla = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(new Phrase("MOTOS PROFESIONALES - LOS MEJORES EN SACATEPEQUEZ\n",fuenteTitulo));
        celda.setColspan(5);
        celda.setBorderColor(BaseColor.WHITE);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
        documento.add(tabla);
        parrafoTitulo(as,noFactura,fecha,hora,nombreVendedor,noCaja,nitCliente,nombreCliente,total);
        
    }
    /**
     * Sigue creando los encabezados de la factura
     * Estos metodos se ejecutan en cadena hasta la finalizacion de la factura
     * @param as
     * @param noFactura
     * @param fecha
     * @param hora
     * @param nombreVendedor
     * @param noCaja
     * @param nitCliente
     * @param nombreCliente
     * @param total
     * @throws DocumentException 
     */
    public void parrafoTitulo(LinkedList<facturaPOO> as,String noFactura, String fecha,String hora,String nombreVendedor,String noCaja, String nitCliente, String nombreCliente,String total) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        Paragraph saltos = new Paragraph();
        parrafo.add(new Phrase("\n"));
        parrafo.setAlignment(1);
        parrafo.add(new Phrase("CENTRO AUTOMOTRIZ 'CONCORDIA'\n",fuenteParrafo));
        parrafo.add(new Phrase("DOCUMENTO TRIBUTARIO ELECTRONICO\n",fuenteParrafo));
        parrafo.add(new Phrase("FACTURA\n",fuenteParrafo));
        parrafo.add(new Phrase("NUMERO: "+noFactura+"\n",fuenteParrafo));
        parrafo.add(new Phrase("FECHA/HORA: "+hora+"\n",fuenteParrafo));
        parrafo.add(new Phrase("\n"));
        documento.add(parrafo);
        datos2(nombreVendedor,noCaja,nitCliente,nombreCliente,as,total);
    }
    

    //Reutilizable
    /**
     * Pone los datos de lo consumido por el usuario y la factura
     * @param nombreVendedor
     * @param noCaja
     * @param nitCliente
     * @param nombreCliente
     * @param as
     * @param total
     * @throws DocumentException 
     */
    private void datos2(String nombreVendedor,String noCaja, String nitCliente, String nombreCliente,LinkedList<facturaPOO> as,String total) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        Paragraph saltos = new Paragraph();
        parrafo.setAlignment(0);
        parrafo.add(new Phrase("Le atendio "+nombreVendedor+"\n",fuenteParrafo1));
        parrafo.add(new Phrase("Caja: "+noCaja+"\n",fuenteParrafo1));
        parrafo.add(new Phrase("NIT/CUI/PASAPORTE: "+nitCliente+"\n",fuenteParrafo1));
        parrafo.add(new Phrase("Nombre: "+nombreCliente+"\n",fuenteParrafo1));
        parrafo.add(new Phrase("Direccion: Predeterminada como Ciudad"+"\n",fuenteParrafo1));
        parrafo.add(new Phrase(Chunk.NEWLINE));
        documento.add(parrafo);
        
    //cerrar();
        agregartabla(as,total);
    }
   
    /**
     * Agrega los productos y las cantidades compradas de este produto
     * @param as
     * @param total
     * @throws DocumentException 
     */
    public void agregartabla(LinkedList<facturaPOO> as,String total) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        Paragraph parrafoIz = new Paragraph();
        parrafo.add(new Phrase("\n"));
        
        parrafo.setAlignment(0);
        for(facturaPOO fac: as){
            
            parrafo.add(new Phrase("Codigo - "+String.valueOf(fac.getCodigoProducto())+"\n",fuenteParrafo2));
            parrafo.add(new Phrase(fac.getNombreProducto()+"     ----------------------    Q."+fac.getPrecioProducto()+"\n",fuenteParrafoNegrita));
            parrafo.add(new Phrase("Cantidad - "+fac.getCantidadProducto()+"\n",fuenteParrafo2));
            parrafo.add(new Phrase("                                                    SubTotal - Q."+fac.getSubtotalProducto(),fuenteParrafoCursiva));
            parrafo.add(new Phrase(Chunk.NEWLINE));
            
        }
        
        documento.add(parrafo);
        
       total(total);
       
       
    }
    
    /**
     * Imprime el final de la factura
     * @param total
     * @throws DocumentException 
     */
    private void total(String total) throws DocumentException{
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(1);
        parrafo.add(new Phrase(Chunk.NEWLINE));
        parrafo.add(new Phrase("TOTAL - Q."+total+"\n",fuenteParrafoNegrita));
        parrafo.add(new Phrase(Chunk.NEWLINE));
        parrafo.add(new Phrase("ES UN PLACER SERVIRLE"));
        parrafo.add(new Phrase(Chunk.NEWLINE));
        parrafo.add(new Phrase("ESPERAMOS SU VISITA NUEVAMENTE"));
        parrafo.add(new Phrase(Chunk.NEWLINE));
        parrafo.add(new Phrase("---------------------------------------"));
        documento.add(parrafo);
        cerrar();
    }
    
    /**
     * Cierra el documento y lo abre al guardarlo
     */
    public void cerrar(){
        documento.close();
        try {
            File clientes_doc = new File(nombreArchivo);
            
            Desktop.getDesktop().open(clientes_doc);
            ruta = String.valueOf(clientes_doc.getAbsoluteFile());
        } catch (Exception e) {
        }
        
    }
    
    
}
