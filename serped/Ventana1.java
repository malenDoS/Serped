
package serped;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class Ventana1 extends javax.swing.JFrame {
    


    public Ventana1() {
        initComponents();
        setSize(700,380);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/icono/portapapeles.png")).getImage());
        setVisible(true);
    }

  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Serped");
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setText("Consultas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBoton(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(100, 210, 100, 30);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setText("Informes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBoton(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(300, 210, 110, 30);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton3.setText("Comparación");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBoton(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(500, 210, 120, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Servicios para personas con dependencia.");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(100, 60, 520, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono/fondo.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 691, 414);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-1, -1, 690, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accionBoton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBoton
       
        if(evt.getSource()==this.jButton1){
            setVisible(false);
            Ventana2 nuevaVentana=new Ventana2();
        }else if(evt.getSource()==this.jButton2){
            try {
                Ventana3Inf venInformes=new Ventana3Inf();
            } catch (IOException ex) {
                Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
            }
            setVisible(false);
        }else{
            setVisible(false);
            try {
                Ventana4Gra ventanaG=new Ventana4Gra();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (XPathExpressionException ex) {
                Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_accionBoton

    
   
    
    


 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
 
