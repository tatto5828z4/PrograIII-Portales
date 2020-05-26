/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agenda;


import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ComponentListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Prueba
 */
public class Main extends javax.swing.JFrame {
    private String contra;
    private int hora;
    private Actividad act;
    private Boolean bot0=false,bot1=false,bot2=false,bot3=false,bot4=false,bot5=false,bot6=false,bot7=false,bot8=false,bot9=false,bot10=false,bot11=false,bot12=false,bot13=false,bot14=false,bot15=false,bot16=false,bot17=false,bot18=false,bot19=false,bot20=false,bot21=false,bot22=false,bot23=false;
    private Boolean auxNotificacion=false;
    
    /**
     * Creates new form Main
     */
    public Main() { // envia un password
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/images/logoSDP.png")).getImage());
        fechaMain();
        agregarTooltip();
        //setPass(pass);
        jPanel1.setVisible(false);
        restablecer();
        evaluarActividad();
        mostrarNotificacion();
    }
    
    public void mostrarNotificacion(){
          //System.out.println(jLabelFI.getText());
          if(auxNotificacion==true)
            JOptionPane.showMessageDialog(null,act.imprimirInmediato(jLabelFI.getText())+"\n"+act.imprimirMediana(jLabelFI.getText())+"\n"+act.imprimirBaja(jLabelFI.getText()), "Actividades-"+jLabelFI.getText(), JOptionPane.INFORMATION_MESSAGE, null);
    }
    
    public void evaluarActividad(){
        for(int i=0;i<24;i++){
            //System.out.println(getFecha(i).getTime());
            cargarActividades(contra,getFecha(i));
            cargarNotificaciones(contra,getFecha(i));
        }
    }
    /*private Main() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    public void cargarNotificaciones(String pass,Calendar fec){
        Conexion cc=new Conexion();
        String consulta="";
        String fecAct=Integer.toString(fec.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(fec.get(Calendar.MONTH)+1)+"/"+Integer.toString(fec.get(Calendar.YEAR))+"/"+Integer.toString(fec.get(Calendar.HOUR_OF_DAY));
        String sql="SELECT * FROM actividad WHERE fecha_actividad='"+fecAct+"'";
        try {
            consulta=cc.notificacionActividad(cc, sql);
            cc.desconectar();
            if(consulta.equals("]]]]")==true)
                consulta="";
            if(consulta!=""){
                auxNotificacion=true;
                String[] arrayActividad = consulta.split("]");
                act=new Actividad(arrayActividad[0],arrayActividad[1],arrayActividad[2],fec,arrayActividad[4]);
                if(arrayActividad[4].equals("Inmediata")){
                        act.agregarInmediato(act);
                }
                if(arrayActividad[4].equals("Mediana")){  
                        act.agregarMediana(act);
                }
                if(arrayActividad[4].equals("Baja")){
                        act.agregarBaja(act);
                }
            }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void cargarActividades(String pass,Calendar fec){
        Conexion cc=new Conexion();
        Color color=null;
        String titulo,actividad;
        String fecAct=Integer.toString(fec.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(fec.get(Calendar.MONTH)+1)+"/"+Integer.toString(fec.get(Calendar.YEAR))+"/"+Integer.toString(fec.get(Calendar.HOUR_OF_DAY));
        String sql="SELECT * FROM actividad WHERE fecha_actividad='"+fecAct+"'";
        try {
            titulo=cc.consultarActividad(cc, sql);
            cc.desconectar();
            if (titulo.equals("]-")==true)
                titulo="";
            if(titulo!=""){
                actividad=titulo.substring(titulo.indexOf('-')+1,titulo.length());
                //System.out.println(titulo.substring(0, titulo.indexOf('-'))+"/"+actividad);  
                color=colorCategoria(actividad);
                bloquearBoton("<"+"html"+">"+titulo.substring(0, titulo.indexOf(']'))+"<"+"br"+">"+titulo.substring(titulo.indexOf(']')+1,titulo.indexOf('-'))+"<!--"+"html"+"-->",fec.get(Calendar.HOUR_OF_DAY),color);
            }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        //cc.desconectar();
        //cc.desconectar();
    }
    
    public Color colorCategoria(String categoria){
        Color color = null;
        if(categoria.equals("Laboral")){
            color=Color.orange;
        }
        if(categoria.equals("Salud")){  
            color=Color.yellow;
        }
        if(categoria.equals("Reunion")){
            color=Color.green;
        }
        if(categoria.equals("Social")){
            color=Color.magenta;
        }
        if(categoria.equals("Otro"))
        {
            color=Color.blue;
        }
        return color;
    }
    
    public void setHora(int hora){
        this.hora=hora;
    }
    
    public void restablecer(){
            jButton00.setEnabled(true);
            jButton00.setText("");
            jButton00.setBackground(new Color(238,238,238));
            jButton01.setText("");
            jButton01.setBackground(new Color(238,238,238));
            jButton01.setEnabled(true);
            jButton02.setEnabled(true);
            jButton02.setText("");
            jButton02.setBackground(new Color(238,238,238));
            jButton03.setText("");
            jButton03.setBackground(new Color(238,238,238));
            jButton03.setEnabled(true);
            jButton04.setText("");
            jButton04.setBackground(new Color(238,238,238));
            jButton04.setEnabled(true);
            jButton05.setText("");
            jButton05.setBackground(new Color(238,238,238));
            jButton05.setEnabled(true);
            jButton06.setText("");
            jButton06.setBackground(new Color(238,238,238));
            jButton06.setEnabled(true);
            jButton07.setText("");
            jButton07.setBackground(new Color(238,238,238));
            jButton07.setEnabled(true);
            jButton08.setText("");
            jButton08.setBackground(new Color(238,238,238));
            jButton08.setEnabled(true);
            jButton09.setText("");
            jButton09.setBackground(new Color(238,238,238));
            jButton09.setEnabled(true);
            jButton10.setText("");
            jButton10.setBackground(new Color(238,238,238));
            jButton10.setEnabled(true);
            jButton11.setText("");
            jButton11.setBackground(new Color(238,238,238));
            jButton11.setEnabled(true);
            jButton12.setText("");
            jButton12.setBackground(new Color(238,238,238));
            jButton12.setEnabled(true);
            jButton13.setText("");
            jButton13.setBackground(new Color(238,238,238));
            jButton13.setEnabled(true);
            jButton14.setText("");
            jButton14.setBackground(new Color(238,238,238));
            jButton14.setEnabled(true);
            jButton15.setText("");
            jButton15.setBackground(new Color(238,238,238));
            jButton15.setEnabled(true);
            jButton16.setText("");
            jButton16.setBackground(new Color(238,238,238));
            jButton16.setEnabled(true);
            jButton17.setText("");
            jButton17.setBackground(new Color(238,238,238));
            jButton17.setEnabled(true);
            jButton18.setText("");
            jButton18.setBackground(new Color(238,238,238));
            jButton18.setEnabled(true);
            jButton19.setText("");
            jButton19.setBackground(new Color(238,238,238));
            jButton19.setEnabled(true);
            jButton20.setText("");
            jButton20.setBackground(new Color(238,238,238));
            jButton20.setEnabled(true);
            jButton21.setText("");
            jButton21.setBackground(new Color(238,238,238));
            jButton21.setEnabled(true);
            jButton22.setText("");
            jButton22.setBackground(new Color(238,238,238));
            jButton22.setEnabled(true);
            jButton23.setText("");
            jButton23.setBackground(new Color(238,238,238));
            jButton23.setEnabled(true); 
    }
    
    public void bloquearBoton(String titulo,int hor, Color color){
        if(hor==0){
            jButton00.setText(titulo);
            jButton00.setBackground(color);
            //jButton00.setEnabled(false);
            bot0=true;
         
        }
        if(hor==1){
            jButton01.setText(titulo);
            jButton01.setBackground(color);
            //jButton01.setEnabled(false);
           bot1=true;
        }
        if(hor==2){
            jButton02.setText(titulo);
            jButton02.setBackground(color);
            //jButton02.setEnabled(false);
            bot2=true;
        }
        if(hor==3){
            jButton03.setText(titulo);
            jButton03.setBackground(color);
            //jButton03.setEnabled(false);
            bot3=true;
        }
        if(hor==4){
            jButton04.setText(titulo);
            jButton04.setBackground(color);
            //jButton04.setEnabled(false);
            bot4=true;
        }
        if(hor==5){
            jButton05.setText(titulo);
            jButton05.setBackground(color);
            //jButton05.setEnabled(false);
            bot5=true;
        }
        if(hor==6){
            jButton06.setText(titulo);
            jButton06.setBackground(color);
            //jButton06.setEnabled(false);
            bot6=true;
        }
        if(hor==7){
            jButton07.setText(titulo);
            jButton07.setBackground(color);
            //jButton07.setEnabled(false);
            bot7=true;
        }
        if(hor==8){
            jButton08.setText(titulo);
            jButton08.setBackground(color);
            //jButton08.setEnabled(false);
            bot8=true;
        }
        if(hor==9){
            jButton09.setText(titulo);
            jButton09.setBackground(color);
            //jButton09.setEnabled(false);
            bot9=true;
        }
        if(hor==10){
            jButton10.setText(titulo);
            jButton10.setBackground(color);
            //jButton10.setEnabled(false);
            bot10=true;
        }
        if(hor==11){
            jButton11.setText(titulo);
            jButton11.setBackground(color);
            //jButton11.setEnabled(false);
            bot11=true;
        }
        if(hor==12){
            jButton12.setText(titulo);
            jButton12.setBackground(color);
            //jButton12.setEnabled(false);
            bot12=true;
        }
        if(hor==13){
            jButton13.setText(titulo);
            jButton13.setBackground(color);
            //jButton13.setEnabled(false);
            bot13=true;
        }
        if(hor==14){
            jButton14.setText(titulo);
            jButton14.setBackground(color);
            //jButton14.setEnabled(false);
            bot14=true;
        }
        if(hor==15){
            jButton15.setText(titulo);
            jButton15.setBackground(color);
            //jButton15.setEnabled(false);
            bot15=true;
        }
        if(hor==16){
            jButton16.setText(titulo);
            jButton16.setBackground(color);
            //jButton16.setEnabled(false);
            bot16=true;
        }
        if(hor==17){
            jButton17.setText(titulo);
            jButton17.setBackground(color);
            //jButton17.setEnabled(false);
            bot17=true;
        }
        if(hor==18){
            jButton18.setText(titulo);
            jButton18.setBackground(color);
            //jButton18.setEnabled(false);
            bot18=true;
        }
        if(hor==19){
            jButton19.setText(titulo);
            jButton19.setBackground(color);
            //jButton19.setEnabled(false);
            bot19=true;
        }
        if(hor==20){
            jButton20.setText(titulo);
            jButton20.setBackground(color);
            //jButton20.setEnabled(false);
            bot20=true;
        }
        if(hor==21){
            jButton21.setText(titulo);
            jButton21.setBackground(color);
            //jButton21.setEnabled(false);
            bot21=true;
        }
        if(hor==22){
            jButton22.setText(titulo);
            jButton22.setBackground(color);
            //jButton22.setEnabled(false);
            bot22=true;
        }
        if(hor==23){
            jButton23.setText(titulo);
            jButton23.setBackground(color);
            //jButton23.setEnabled(false);
            bot23=true;
        }
    }
    public void setPass(String contraU){
        contra=contraU;
    }
    
    public String getPass(){
        return contra;
    }
    
    public void fechaMain(){
        Calendar fechaActual = GregorianCalendar.getInstance();
        jLabelFI.setText(Integer.toString(fechaActual.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(fechaActual.get(Calendar.MONTH)+1)+"/"+Integer.toString(fechaActual.get(Calendar.YEAR)));
    }
    
    public void limpiarActividad(){
       jTextFieldTitulo.setText("");
       jTextFieldDescripcion.setText("");
       
    }
    
    public void agregarTooltip(){
        jButton00.setToolTipText("Añadir Actividad");
        jButton01.setToolTipText("Añadir Actividad");
        jButton02.setToolTipText("Añadir Actividad");
        jButton03.setToolTipText("Añadir Actividad");
        jButton04.setToolTipText("Añadir Actividad");
        jButton05.setToolTipText("Añadir Actividad");
        jButton06.setToolTipText("Añadir Actividad");
        jButton07.setToolTipText("Añadir Actividad");
        jButton08.setToolTipText("Añadir Actividad");
        jButton09.setToolTipText("Añadir Actividad");
        jButton10.setToolTipText("Añadir Actividad");
        jButton11.setToolTipText("Añadir Actividad");
        jButton12.setToolTipText("Añadir Actividad");
        jButton13.setToolTipText("Añadir Actividad");
        jButton14.setToolTipText("Añadir Actividad");
        jButton15.setToolTipText("Añadir Actividad");
        jButton16.setToolTipText("Añadir Actividad");
        jButton17.setToolTipText("Añadir Actividad");
        jButton18.setToolTipText("Añadir Actividad");
        jButton19.setToolTipText("Añadir Actividad");
        jButton20.setToolTipText("Añadir Actividad");
        jButton21.setToolTipText("Añadir Actividad");
        jButton22.setToolTipText("Añadir Actividad");
        jButton23.setToolTipText("Añadir Actividad");
    }
    
    public Calendar getFecha(int hora){
        String fecha = jLabelFI.getText();
        String[] fechArray = fecha.split("/");
        int dia = Integer.valueOf(fechArray[0]);
        int mes = Integer.valueOf(fechArray[1]) - 1;
        int anio = Integer.valueOf(fechArray[2]);
        Calendar c1 = new GregorianCalendar(anio, mes, dia,hora,0);
        return c1;
    }
    
    public void suprimirActividad(){
        if(JOptionPane.showConfirmDialog(null, "Desea eliminar esta actividad?")<1){
            Calendar c1=getFecha(hora);
            Conexion cc=new Conexion();
            /*System.out.println(getPass());
            System.out.println(c1.getTime());*/
            String fecAct=Integer.toString(c1.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(c1.get(Calendar.MONTH)+1)+"/"+Integer.toString(c1.get(Calendar.YEAR))+"/"+Integer.toString(c1.get(Calendar.HOUR_OF_DAY));
            String sql="DELETE FROM actividad WHERE fecha_actividad='"+fecAct+"'";
            try {
                cc.eliminarActividad(cc, sql);
                cc.desconectar();
                JOptionPane.showMessageDialog(null, "Actividad eliminada exitosamente!");
                //act.eliminar(fecAct);
                
                restablecer();
                evaluarActividad();
                act.eliminar(fecAct);
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            //cc.desconectar(); 
             switch (hora) {
                case 0:
                    bot0=false;
                    break;
                case 1:
                    bot1=false;
                    break;
                case 2:
                    bot2=false;
                    break;
                case 3:
                    bot3=false;
                    break;
                case 4:
                    bot4=false;
                    break;
                case 5:
                    bot5=false;
                    break;
                case 6:
                    bot6=false;
                    break;
                case 7:
                    bot7=false;
                    break;
                case 8:
                    bot8=false;
                    break;
                case 9:
                    bot9=false;
                    break;
                case 10:
                    bot10=false;
                    break;
                case 11:
                    bot11=false;
                    break;
                case 12:
                    bot12=false;
                    break;
                case 13:
                    bot13=false;
                    break;
                case 14:
                    bot14=false;
                    break;
                case 15:
                    bot15=false;
                    break;
                case 16:
                    bot16=false;
                    break;
                case 17:
                    bot17=false;
                    break;
                case 18:
                    bot18=false;
                    break;
                case 19:
                    bot19=false;
                    break;
                case 20:
                    bot20=false;
                    break;
                case 21:
                    bot21=false;
                    break;
                case 22:
                    bot22=false;
                    break;
                case 23:
                    bot23=false;
                    break;
                default: 
                    System.out.println ("La hora no esta definida"); 
                    break;
            }
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jButton00 = new javax.swing.JButton();
        jButton01 = new javax.swing.JButton();
        jButton02 = new javax.swing.JButton();
        jButton03 = new javax.swing.JButton();
        jButton04 = new javax.swing.JButton();
        jButton05 = new javax.swing.JButton();
        jButton06 = new javax.swing.JButton();
        jButton07 = new javax.swing.JButton();
        jButton08 = new javax.swing.JButton();
        jButton09 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabelFI = new javax.swing.JLabel();
        jLabelDia = new javax.swing.JLabel();
        jLabel23H = new javax.swing.JLabel();
        jLabelHora = new javax.swing.JLabel();
        jLabel0H = new javax.swing.JLabel();
        jLabel1H = new javax.swing.JLabel();
        jLabel2H = new javax.swing.JLabel();
        jLabel3H = new javax.swing.JLabel();
        jLabel4H = new javax.swing.JLabel();
        jLabel5H = new javax.swing.JLabel();
        jLabel6H = new javax.swing.JLabel();
        jLabel7H = new javax.swing.JLabel();
        jLabel8H = new javax.swing.JLabel();
        jLabel9H = new javax.swing.JLabel();
        jLabel10H = new javax.swing.JLabel();
        jLabel11H = new javax.swing.JLabel();
        jLabel12H = new javax.swing.JLabel();
        jLabel13H = new javax.swing.JLabel();
        jLabel14H = new javax.swing.JLabel();
        jLabel15H = new javax.swing.JLabel();
        jLabel16H = new javax.swing.JLabel();
        jLabel17H = new javax.swing.JLabel();
        jLabel18H = new javax.swing.JLabel();
        jLabel19H = new javax.swing.JLabel();
        jLabel20H = new javax.swing.JLabel();
        jLabel21H = new javax.swing.JLabel();
        jLabel22H = new javax.swing.JLabel();
        jLabelActividades = new javax.swing.JLabel();
        jLabelEscogerDia = new javax.swing.JLabel();
        jDateChooserDia = new com.toedter.calendar.JDateChooser();
        jButtonFleIzq = new javax.swing.JButton();
        jButtonFleDer = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jLabelTituloCita = new javax.swing.JLabel();
        jLabelTituloCita1 = new javax.swing.JLabel();
        jLabelTituloCita2 = new javax.swing.JLabel();
        jComboBoxPrioridad = new javax.swing.JComboBox<>();
        jLabelTituloCita3 = new javax.swing.JLabel();
        jLabelTituloCita4 = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jTextFieldTitulo = new javax.swing.JTextField();
        jButtonSalir1 = new javax.swing.JButton();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlOpciones_ventana = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();
        jLabelFondo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton00.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton00ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton00, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 380, 70));

        jButton01.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton01ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton01, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 380, 70));

        jButton02.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton02ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton02, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 380, 70));

        jButton03.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton03ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton03, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 380, 70));

        jButton04.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton04ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton04, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 380, 70));

        jButton05.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton05ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton05, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 380, 70));

        jButton06.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton06ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton06, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 380, 70));

        jButton07.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton07ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton07, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 570, 370, 70));

        jButton08.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton08ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton08, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 640, 370, 80));

        jButton09.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton09ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton09, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 710, 370, 80));

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 780, 370, 80));

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 850, 370, 80));

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 920, 370, 80));

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 990, 370, 80));

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1060, 370, 80));

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1130, 370, 80));

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1200, 370, 80));

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1270, 370, 80));

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1340, 370, 80));

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1410, 370, 80));

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1480, 370, 80));

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1550, 370, 80));

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1620, 370, 80));

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1690, 370, 80));

        jLabelFI.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        jLabelFI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFI.setToolTipText("");
        jPanel2.add(jLabelFI, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 320, 60));

        jLabelDia.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelDia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelDia.setText("Día:");
        jLabelDia.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabelDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 490, 70));

        jLabel23H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel23H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23H.setText("23 H00");
        jLabel23H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel23H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1690, 150, 70));

        jLabelHora.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHora.setText("Hora");
        jLabelHora.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabelHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 70));

        jLabel0H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel0H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel0H.setText("00 H00");
        jLabel0H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel0H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 150, 70));

        jLabel1H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1H.setText("01 H00");
        jLabel1H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel1H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 150, 70));

        jLabel2H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2H.setText("02 H00");
        jLabel2H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel2H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 150, 70));

        jLabel3H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3H.setText("03 H00");
        jLabel3H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel3H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 150, 70));

        jLabel4H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4H.setText("04 H00");
        jLabel4H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel4H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 150, 70));

        jLabel5H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5H.setText("05 H00");
        jLabel5H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel5H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 150, 70));

        jLabel6H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6H.setText("06 H00");
        jLabel6H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel6H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 150, 70));

        jLabel7H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7H.setText("07 H00");
        jLabel7H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel7H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 150, 70));

        jLabel8H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8H.setText("08 H00");
        jLabel8H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel8H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 150, 70));

        jLabel9H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9H.setText("09 H00");
        jLabel9H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel9H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 150, 70));

        jLabel10H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10H.setText("10 H00");
        jLabel10H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel10H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 780, 150, 70));

        jLabel11H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11H.setText("11 H00");
        jLabel11H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel11H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 850, 150, 70));

        jLabel12H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12H.setText("12 H00");
        jLabel12H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel12H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 920, 150, 70));

        jLabel13H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13H.setText(" 13 H00");
        jLabel13H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel13H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 990, 150, 70));

        jLabel14H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14H.setText("14 H00");
        jLabel14H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel14H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1060, 150, 70));

        jLabel15H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15H.setText("15 H00");
        jLabel15H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel15H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1130, 150, 70));

        jLabel16H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16H.setText("16 H00");
        jLabel16H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel16H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1200, 150, 70));

        jLabel17H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17H.setText("17 H00");
        jLabel17H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel17H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1270, 150, 70));

        jLabel18H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18H.setText("18 H00");
        jLabel18H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel18H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1340, 150, 70));

        jLabel19H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19H.setText("19 H00");
        jLabel19H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel19H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1410, 150, 70));

        jLabel20H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20H.setText("20 H00");
        jLabel20H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel20H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1480, 150, 70));

        jLabel21H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21H.setText("21 H00");
        jLabel21H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel21H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1550, 150, 70));

        jLabel22H.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel22H.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22H.setText("22 H00");
        jLabel22H.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel2.add(jLabel22H, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1620, 150, 70));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 560, 540));

        jLabelActividades.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelActividades.setForeground(new java.awt.Color(255, 255, 255));
        jLabelActividades.setText("ACTIVIDADES ");
        getContentPane().add(jLabelActividades, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jLabelEscogerDia.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelEscogerDia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEscogerDia.setText("Seleccione día: ");
        getContentPane().add(jLabelEscogerDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 190, -1));
        getContentPane().add(jDateChooserDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 170, 30));

        jButtonFleIzq.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFleIzq.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Flecha Izquierda.png"))); // NOI18N
        jButtonFleIzq.setBorderPainted(false);
        jButtonFleIzq.setFocusable(false);
        jButtonFleIzq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonFleIzqMouseEntered(evt);
            }
        });
        jButtonFleIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFleIzqActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFleIzq, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 50, 50));

        jButtonFleDer.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFleDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Flecha Derecha.png"))); // NOI18N
        jButtonFleDer.setBorder(null);
        jButtonFleDer.setBorderPainted(false);
        jButtonFleDer.setFocusable(false);
        jButtonFleDer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFleDerActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFleDer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 180, 50, 50));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("SELECCIONAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 140, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonAceptar.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAceptar.setForeground(new java.awt.Color(51, 51, 51));
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 110, 30));

        jLabelTituloCita.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTituloCita.setText("Categoría: ");
        jPanel1.add(jLabelTituloCita, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 40));

        jLabelTituloCita1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTituloCita1.setText("AGREGAR ACTIVIDAD");
        jPanel1.add(jLabelTituloCita1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, 30));

        jLabelTituloCita2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTituloCita2.setText("Descripción: ");
        jPanel1.add(jLabelTituloCita2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 40));

        jComboBoxPrioridad.setBackground(new java.awt.Color(231, 231, 231));
        jComboBoxPrioridad.setEditable(true);
        jComboBoxPrioridad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxPrioridad.setForeground(new java.awt.Color(3, 190, 201));
        jComboBoxPrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inmediata", "Mediana", "Baja" }));
        jComboBoxPrioridad.setSelectedItem(null);
        jPanel1.add(jComboBoxPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 200, 30));

        jLabelTituloCita3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTituloCita3.setText("Prioridad: ");
        jPanel1.add(jLabelTituloCita3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 130, 40));

        jLabelTituloCita4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTituloCita4.setText("Título:");
        jPanel1.add(jLabelTituloCita4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 80, 40));

        jTextFieldDescripcion.setBackground(new java.awt.Color(231, 231, 231));
        jTextFieldDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescripcionActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 200, 30));

        jTextFieldTitulo.setBackground(new java.awt.Color(231, 231, 231));
        jPanel1.add(jTextFieldTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 200, 30));

        jButtonSalir1.setBackground(new java.awt.Color(204, 204, 204));
        jButtonSalir1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonSalir1.setForeground(new java.awt.Color(51, 51, 51));
        jButtonSalir1.setText("CANCELAR");
        jButtonSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalir1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 30));

        jComboBoxCategoria.setBackground(new java.awt.Color(231, 231, 231));
        jComboBoxCategoria.setEditable(true);
        jComboBoxCategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBoxCategoria.setForeground(new java.awt.Color(3, 190, 200));
        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laboral", "Salud", "Reunion", "Social", "Otro" }));
        jComboBoxCategoria.setSelectedItem(null);
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 200, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 470, 330));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Categoria (colores): ");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.green);
        jLabel2.setText("Reunion");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.yellow);
        jLabel3.setText("Salud");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        jLabel4.setBackground(java.awt.Color.red);
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.orange);
        jLabel4.setText("Laboral");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setText("Otro");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.magenta);
        jLabel6.setText("Social");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 470, 80));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo1.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlOpciones_ventana.setBackground(new java.awt.Color(51, 51, 51));
        pnlOpciones_ventana.setForeground(new java.awt.Color(255, 255, 255));

        btnMinimize.setBackground(new java.awt.Color(51, 51, 51));
        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Minimize.png"))); // NOI18N
        btnMinimize.setContentAreaFilled(false);
        btnMinimize.setFocusable(false);
        btnMinimize.setOpaque(true);
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseExited(evt);
            }
        });
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });
        pnlOpciones_ventana.add(btnMinimize);

        btnMaximize.setBackground(new java.awt.Color(51, 51, 51));
        btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Maximize.png"))); // NOI18N
        btnMaximize.setContentAreaFilled(false);
        btnMaximize.setFocusable(false);
        btnMaximize.setOpaque(true);
        btnMaximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseExited(evt);
            }
        });
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });
        pnlOpciones_ventana.add(btnMaximize);

        btnExit.setBackground(new java.awt.Color(51, 51, 51));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Salir.png"))); // NOI18N
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusable(false);
        btnExit.setOpaque(true);
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        pnlOpciones_ventana.add(btnExit);

        jPanel4.add(pnlOpciones_ventana, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabelFondo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/wall-street-cripto-1.jpg"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-130, 0, 1320, 740));

        jLabelFondo1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabelFondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton01ActionPerformed
        setHora(1);
        if(bot1==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }    
    }//GEN-LAST:event_jButton01ActionPerformed

    private void jButton09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton09ActionPerformed
        setHora(9);
        if(bot9==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton09ActionPerformed

    private void jButton08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton08ActionPerformed
        setHora(8);
        if(bot8==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton08ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        setHora(17);
        if(bot17==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        setHora(12);
        if(bot12==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButtonFleIzqMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFleIzqMouseEntered

    }//GEN-LAST:event_jButtonFleIzqMouseEntered

    private void jButtonFleIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFleIzqActionPerformed
        String fecha = jLabelFI.getText();
        String[] fechArray = fecha.split("/");
        int dia = Integer.valueOf(fechArray[0]);
        int mes = Integer.valueOf(fechArray[1]) - 1;
        int anio = Integer.valueOf(fechArray[2]);
        Calendar c1 = new GregorianCalendar(anio, mes, dia);
        c1.add(Calendar.DAY_OF_MONTH, -1);
        Calendar fechaActual = GregorianCalendar.getInstance();
        if (c1.compareTo(fechaActual)>=0 || (c1.get(Calendar.DAY_OF_MONTH)==fechaActual.get(Calendar.DAY_OF_MONTH) && c1.get(Calendar.MONTH)==fechaActual.get(Calendar.MONTH) && c1.get(Calendar.YEAR)==fechaActual.get(Calendar.YEAR))){
            jLabelFI.setText(Integer.toString(c1.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(c1.get(Calendar.MONTH)+1)+"/"+Integer.toString(c1.get(Calendar.YEAR)));
            restablecer();
            evaluarActividad();
            mostrarNotificacion();
        }    
        else
            JOptionPane.showMessageDialog(null, "ERROR!\nLa agenda esta personalizada para eventos futuros!");
    }//GEN-LAST:event_jButtonFleIzqActionPerformed

    private void jButtonFleDerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFleDerActionPerformed
        String fecha = jLabelFI.getText();
        String[] fechArray = fecha.split("/");
        int dia = Integer.valueOf(fechArray[0]);
        int mes = Integer.valueOf(fechArray[1]) - 1;
        int anio = Integer.valueOf(fechArray[2]);
        Calendar c1 = new GregorianCalendar(anio, mes, dia);
        c1.add(Calendar.DAY_OF_MONTH, +1);
        jLabelFI.setText(Integer.toString(c1.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(c1.get(Calendar.MONTH)+1)+"/"+Integer.toString(c1.get(Calendar.YEAR)));
        restablecer();
        evaluarActividad();
        mostrarNotificacion();
    }//GEN-LAST:event_jButtonFleDerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Calendar fechaActual = GregorianCalendar.getInstance();
        Calendar c1=jDateChooserDia.getCalendar();
        if (c1.compareTo(fechaActual)>=0 || (c1.get(Calendar.DAY_OF_MONTH)==fechaActual.get(Calendar.DAY_OF_MONTH) && c1.get(Calendar.MONTH)==fechaActual.get(Calendar.MONTH) && c1.get(Calendar.YEAR)==fechaActual.get(Calendar.YEAR))){ 
            jLabelFI.setText(Integer.toString(c1.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(c1.get(Calendar.MONTH)+1)+"/"+Integer.toString(c1.get(Calendar.YEAR)));
            restablecer();
            evaluarActividad();
            mostrarNotificacion();
            
        }else
            JOptionPane.showMessageDialog(null, "ERROR!\nLa agenda esta personalizada para eventos futuros!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton00ActionPerformed
        setHora(0);
        if(bot0==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
        
    }//GEN-LAST:event_jButton00ActionPerformed

    private void jButton02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton02ActionPerformed
        setHora(2);
        if(bot2==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton02ActionPerformed

    private void jButton03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton03ActionPerformed
        setHora(3);
        if(bot3==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton03ActionPerformed

    private void jButton04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton04ActionPerformed
        setHora(4);
        if(bot4==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton04ActionPerformed

    private void jButton05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton05ActionPerformed
        setHora(5);
        if(bot5==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton05ActionPerformed

    private void jButton06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton06ActionPerformed
        setHora(6);
        if(bot6==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton06ActionPerformed

    private void jButton07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton07ActionPerformed
        setHora(7);
        if(bot7==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton07ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        setHora(10);
        if(bot10==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        setHora(11);
        if(bot11==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        setHora(13);
        if(bot13==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        setHora(14);
        if(bot14==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        setHora(15);
        if(bot15==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        setHora(16);
        if(bot16==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        setHora(18);
        if(bot18==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        setHora(19);
        if(bot19==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        setHora(20);
        if(bot20==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        setHora(21);
        if(bot21==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        setHora(22);
        if(bot22==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        setHora(23);
        if(bot23==false){
            limpiarActividad();
            jPanel1.setVisible(true);
            
        }else{
            suprimirActividad();
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        String titulo,descripcion,categoria,prioridad,sql="";
        Color color = null; 
        titulo=jTextFieldTitulo.getText().replace(" ", "");
        descripcion=jTextFieldDescripcion.getText().replace(" ", "");
        categoria=(String) jComboBoxCategoria.getSelectedItem();
        prioridad=(String) jComboBoxPrioridad.getSelectedItem();
        
        if (titulo.equals(""))
        JOptionPane.showMessageDialog(null, "ERROR!\nEl campo Título es obligatorio!");
        else{
            //System.out.println(titulo+"-"+categoria+prioridad+fecha.getTime()+passUser);
            Conexion cc=new Conexion();
            sql="INSERT INTO actividad (titulo_actividad, descripcion_actividad, tipo_actividad, fecha_actividad, prioridad_actividad) VALUES (?,?,?,?,?)";
            try {
                int n=cc.enviarActividad(cc, titulo, descripcion, categoria, getFecha(hora), prioridad,sql);
                cc.desconectar();
                if (n>0){
                    act=new Actividad(titulo,  descripcion,  categoria, getFecha(hora),prioridad);
                    limpiarActividad();
                    jPanel1.setVisible(false);
                    color=colorCategoria(categoria);
                    bloquearBoton("<"+"html"+">"+titulo+"<"+"br"+">"+descripcion+"<!--"+"html"+"-->",hora,color);
                    JOptionPane.showMessageDialog(null, "GUARDADO!\nActividad registrada exitosamente");
                    //System.out.println(act.getTitulo()+" "+act.getDescripcion()+" "+act.getTipoActividad());
                    if(prioridad.equals("Inmediata")){
                        act.agregarInmediato(act);
                    }
                    if(prioridad.equals("Mediana")){  
                        act.agregarMediana(act);
                    }
                    if(prioridad.equals("Baja")){
                        act.agregarBaja(act);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR DE CONEXION!\nError de registro en la base de datos!");
                    jTextFieldTitulo.setText("");
                    jTextFieldDescripcion.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "ERROR DE CONEXION!\nError de registro en la base de datos!");
            }
            cc.desconectar();
            //cc.desconectar();
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jTextFieldDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDescripcionActionPerformed

    private void jButtonSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalir1ActionPerformed
        jPanel1.setVisible(false);
    }//GEN-LAST:event_jButtonSalir1ActionPerformed

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setBackground(new Color(93, 93, 93));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnMaximizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseEntered
        btnMaximize.setBackground(new Color(93, 93, 93));
    }//GEN-LAST:event_btnMaximizeMouseEntered

    private void btnMaximizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseExited
        btnMaximize.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnMaximizeMouseExited

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
        /*if (maximized) {
            //handle fullscreen - taskbar
            Plataforma.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Plataforma.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }*/
    }//GEN-LAST:event_btnMaximizeActionPerformed

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(255, 101, 101));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(51, 51, 51));
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//        System.exit(0);

    dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void jComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCategoriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton jButton00;
    private javax.swing.JButton jButton01;
    private javax.swing.JButton jButton02;
    private javax.swing.JButton jButton03;
    private javax.swing.JButton jButton04;
    private javax.swing.JButton jButton05;
    private javax.swing.JButton jButton06;
    private javax.swing.JButton jButton07;
    private javax.swing.JButton jButton08;
    private javax.swing.JButton jButton09;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonFleDer;
    private javax.swing.JButton jButtonFleIzq;
    private javax.swing.JButton jButtonSalir1;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JComboBox<String> jComboBoxPrioridad;
    private com.toedter.calendar.JDateChooser jDateChooserDia;
    private javax.swing.JLabel jLabel0H;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10H;
    private javax.swing.JLabel jLabel11H;
    private javax.swing.JLabel jLabel12H;
    private javax.swing.JLabel jLabel13H;
    private javax.swing.JLabel jLabel14H;
    private javax.swing.JLabel jLabel15H;
    private javax.swing.JLabel jLabel16H;
    private javax.swing.JLabel jLabel17H;
    private javax.swing.JLabel jLabel18H;
    private javax.swing.JLabel jLabel19H;
    private javax.swing.JLabel jLabel1H;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20H;
    private javax.swing.JLabel jLabel21H;
    private javax.swing.JLabel jLabel22H;
    private javax.swing.JLabel jLabel23H;
    private javax.swing.JLabel jLabel2H;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3H;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4H;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel5H;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel6H;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel7H;
    private javax.swing.JLabel jLabel8H;
    private javax.swing.JLabel jLabel9H;
    private javax.swing.JLabel jLabelActividades;
    private javax.swing.JLabel jLabelDia;
    private javax.swing.JLabel jLabelEscogerDia;
    private javax.swing.JLabel jLabelFI;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelFondo1;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelTituloCita;
    private javax.swing.JLabel jLabelTituloCita1;
    private javax.swing.JLabel jLabelTituloCita2;
    private javax.swing.JLabel jLabelTituloCita3;
    private javax.swing.JLabel jLabelTituloCita4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldTitulo;
    private javax.swing.JPanel pnlOpciones_ventana;
    // End of variables declaration//GEN-END:variables
}
