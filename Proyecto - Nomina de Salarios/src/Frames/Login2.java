/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.Plataforma.GenerarQR;
import com.sun.glass.events.KeyEvent;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.Timer;
import java.util.TimerTask;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Login2 extends javax.swing.JFrame {
    
    
     String Base_de_Datos = "jdbc:mysql://localhost/Nomina_de_Empleados";
    String Usuario = "root";
    String Clave = "jorgito5828H";

    
    
    private Timer tiempo;
    public static String[] DatosPersonales1 = new String[8];
    public static String[] PermisosOpciones1 = new String[25];
    ImageIcon tab1Icon = new ImageIcon(
            this.getClass().getResource("/Imagenes/Login/1.png"));

    ImageIcon tab2Icon = new ImageIcon(
            this.getClass().getResource("/Imagenes/Login/2.png"));
    ImageIcon tab3Icon = new ImageIcon(
            this.getClass().getResource("/Imagenes/Login/3.png"));
    
    String[] NombresColumnasUsuarios = {"ID_Usuario", "ID_Concepto", "NombreUsuario", "Contraseña", "CorreoElectronico", "Telefono", "Direcciones",
        "Sesiones",};
    String[] NombresColumnasConceptos = {"ID_Concepto", "NombreConcepto", "IngresarUser", "ModificarUser", "EliminarUser", "ConsultarUser",
        "IngresarUserTipo", "ModificarUserTipo", "EliminarUserTipo", "ConsultarUserTipo", "IngresarEmpleado", "ModificarEmpleado", "EliminarEmpleado", "ConsultarEmpleado",
        "IngresarDepartamento", "ModificarDepartamento", "EliminarDepartamento", "ConsultarDepartamento",
        "IngresarConcepto", "ModificarConcepto", "EliminarConcepto", "ConsultarConcepto", "Tabla", "Barras", "Pastel"};
    Icon registro = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Cambios.png"));
    

    public Login2() {
        initComponents();
        this.setLocationRelativeTo(null);

        jProgressBar.setVisible(false);
        lblConectar.setVisible(false);
        lblPorcentaje.setVisible(false);
        lblCalendario.setVisible(false);

        jTabbedPane1.removeAll();
        jTabbedPane1.repaint();
        jTabbedPane1.revalidate();
        if (CantidadDeRegistros("Usuarios") == 0) {
            jTabbedPane1.addTab("INICIAR SESION", tab1Icon, pnlIngreso);
            jTabbedPane1.addTab("RECUPERAR", tab2Icon, pnlRecuperar);
            jTabbedPane1.addTab("REGISTRO     ", tab3Icon, pnlRegistro);
            Icon Aviso = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NoHayRegistros.png"));
            JOptionPane.showMessageDialog(null, "ES NECESARO CREAR UN USUARIO EN LA PESTAÑA DE REGISTRO", "REGISTRARSE", JOptionPane.INFORMATION_MESSAGE, Aviso);

        } else {
            jTabbedPane1.addTab("INICIAR SESION", tab1Icon, pnlIngreso);
            jTabbedPane1.addTab("RECUPERAR", tab2Icon, pnlRecuperar);

        }

        jTabbedPane1.repaint();
        jTabbedPane1.revalidate();

    }

    public class progreso implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            jProgressBar.setVisible(true);
            lblConectar.setVisible(true);
            lblPorcentaje.setVisible(true);
            int numero = jProgressBar.getValue();
            if (numero < 100) {

                numero++;
                jProgressBar.setValue(numero);
                lblPorcentaje.setText(Integer.toString(numero) + "%");
            } else {
                tiempo.stop();
                Plataforma PF = new Plataforma();
                PF.setVisible(true);
                dispose();
            }
        }
    }
    public void tiempoEspera() {

        java.util.Timer tiempoDeAnuncio = new java.util.Timer();
        TimerTask task = new TimerTask() {
            int tiempo = 0;

            @Override
            public void run() {

                if (tiempo == 1) {

                }
                tiempo++;

            }
        };
        tiempoDeAnuncio.schedule(task, 1, 1000);
    }

    public void CrearUsuario() {
        String categoria =  txtIDTipo.getText().trim();

        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into Usuarios values(?,?,?,?,?,?,?,?)");

            pst.setString(1, txtID.getText().trim());
            pst.setString(2, categoria);
            pst.setString(3, txtUsuarioRegistrar.getText().trim());
            pst.setString(4, txtContraseña.getText().trim());
            pst.setString(5, txtCorreo.getText().trim());
            pst.setString(6, txtTelefono.getText().trim());
            pst.setString(7, addr.getHostAddress()+" "+hostname);
            pst.setString(8,"0");

            pst.executeUpdate();
             GenerarQR( txtID.getText().trim(), txtUsuarioRegistrar.getText().trim());
            Icon ingresado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Ingresado.png"));
            JOptionPane.showMessageDialog(null, "USUARIO REGISTRADO - LOS DATOS FUERON ANOTADOS EN LA BASE DE DATOS", "INGRESADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, ingresado);
            DesktopNotify.showDesktopMessage("Registrado", "El Usuario fue ingresado con Exito", DesktopNotify.SUCCESS, 7000L);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void CrearTipo() {

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into Concepto values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, txtIDTipo.getText().trim());
            pst.setString(2, txtUsuarioRegistrarTipo.getText().trim());

            for (int i = 0; i < 23; i++) {

                pst.setString(i + 3, "1");

            }

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "TIPO USUARIO REGISTRADO - LOS DATOS FUERON ANOTADOS EN LA BASE DE DATOS", "INGRESADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, registro);
            DesktopNotify.showDesktopMessage("Registrado", "El Tipo de Usuario fue ingresado con Exito", DesktopNotify.SUCCESS, 7000L);
           CrearUsuario();
           jTabbedPane1.removeAll();
        jTabbedPane1.repaint();
        jTabbedPane1.revalidate();
        jTabbedPane1.addTab("INICIAR SESION", tab1Icon, pnlIngreso);
        jTabbedPane1.addTab("RECUPERAR", tab2Icon, pnlRecuperar);
        jTabbedPane1.repaint();
        jTabbedPane1.revalidate();
        } catch (Exception e) {
              Icon icon = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/ErrorNum.png"));
              JOptionPane.showMessageDialog(null, "AVISO\n" + "POSIBLEMENTE EL ID DE TIPO DE USUARIO YA EXISTE DUPLICADO INGRESE OTRO ID", "AVISO", JOptionPane.INFORMATION_MESSAGE, icon);
            e.printStackTrace();
        }

    }

    public void BuscarRegistroUsuarios(String Usuario_Basededatos, String Tabla, String Filtro, int cantidad) {
          
        if (cantidad > 8) {
                 PermisosOpciones1 = new String[25];
            } else {
                 DatosPersonales1 = new String[8];
            }
       
        
        int[] columnas = new int[cantidad];
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from " + Tabla + " where " + Filtro + " = ?");
            pst.setString(1, Usuario_Basededatos);
            ResultSet rs = pst.executeQuery();
            if (cantidad > 8) {
                for (int i = 0; i < cantidad; i++) {
                    columnas[i] = rs.findColumn(NombresColumnasConceptos[i]);
                }
            } else {
                for (int i = 0; i < cantidad; i++) {
                    columnas[i] = rs.findColumn(NombresColumnasUsuarios[i]);
                }
            }

            if (rs.next()) {
                if (cantidad > 8) {
                    for (int i = 0; i < cantidad; i++) {
                        PermisosOpciones1[i] = rs.getString(columnas[i]);
                    }
                } else {
                    for (int i = 0; i < cantidad; i++) {
                        DatosPersonales1[i] = rs.getString(columnas[i]);
                    }

                }

            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int CantidadDeRegistros(String Tabla) {
        int CantidadDeRegistros = 0;
        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();
            ResultSet AuxParaCantidadRegistros = resultado;

            while (AuxParaCantidadRegistros.next()) {
                CantidadDeRegistros++;
            }
        } catch (Exception e) {

        }
        return CantidadDeRegistros;
    }

    public void InicioSesion() {
        String nulldato = null;
        /* for (int i = 2; i < 25; i++) {
            if (PermisosOpciones1[i].equals("0")) {
                nulldato = null;

            } else {
                nulldato = " ";
                i = 25;
            }
        }*/

        Icon icon = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NohayRegistros.png"));
        Icon icon2 = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/ErrorNum.png"));
        BuscarRegistroUsuarios(txtUsuario.getText().trim(), "Usuarios", "NombreUsuario", 8);

        if (DatosPersonales1[2] == (null) && DatosPersonales1[3] == (null)) {
            JOptionPane.showMessageDialog(null, "AVISO\n" + "EL USUARIO NO EXISTE", "AVISO", JOptionPane.INFORMATION_MESSAGE, icon2);
        } else {

            if (DatosPersonales1[1] == null) {

                JOptionPane.showMessageDialog(null, "ADVERTENCIA\n" + "NO TIENES LOS PERMISOS NECESARIOS CONSULTE CON EL ADMINSITRADOR", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE, icon);
            } else {
                BuscarRegistroUsuarios(DatosPersonales1[1], "Concepto", "ID_Concepto", 25);
                if (txtUsuario.getText().trim().equals(DatosPersonales1[2]) && psContraseña.getText().trim().equals(DatosPersonales1[3])) {
                    tiempo.start();

                } else {
                    JOptionPane.showMessageDialog(null, "AVISO\n" + "NOMBRE DE USUARIO O CONTRASEÑA INCORRECTA", "AVISO", JOptionPane.INFORMATION_MESSAGE, icon);
                }

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

        pnlContenido = new javax.swing.JPanel();
        btnAjustes = new javax.swing.JButton();
        pnlIzquierdo = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlIngreso = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblConect = new javax.swing.JLabel();
        lblSignIn = new javax.swing.JLabel();
        lblmundo = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        pnlUsername = new javax.swing.JPanel();
        lblPass = new javax.swing.JLabel();
        psContraseña = new javax.swing.JPasswordField();
        pnlContraseña = new javax.swing.JPanel();
        cbPermanecerConectado = new javax.swing.JCheckBox();
        btnAcceder = new javax.swing.JButton();
        lblCrear = new javax.swing.JLabel();
        lblRecuperar = new javax.swing.JLabel();
        pnlRecuperar = new javax.swing.JPanel();
        lblLogo2 = new javax.swing.JLabel();
        lblConect2 = new javax.swing.JLabel();
        lblmundo2 = new javax.swing.JLabel();
        lblSignIn2 = new javax.swing.JLabel();
        lblUser2 = new javax.swing.JLabel();
        txtUsuarioRecuperar = new javax.swing.JTextField();
        pnlUsername2 = new javax.swing.JPanel();
        lblPass3 = new javax.swing.JLabel();
        txtCorreoRecuperar = new javax.swing.JTextField();
        pnlContraseña3 = new javax.swing.JPanel();
        btnRecuperar = new javax.swing.JButton();
        lblSignIn3 = new javax.swing.JLabel();
        pnlRegistro = new javax.swing.JPanel();
        lblLogo1 = new javax.swing.JLabel();
        lblConect1 = new javax.swing.JLabel();
        lblmundo1 = new javax.swing.JLabel();
        lblSignIn1 = new javax.swing.JLabel();
        lblUser1 = new javax.swing.JLabel();
        txtUsuarioRegistrar = new javax.swing.JTextField();
        pnlUsername1 = new javax.swing.JPanel();
        lblPass1 = new javax.swing.JLabel();
        lblUser3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        pnlUsername3 = new javax.swing.JPanel();
        txtContraseña = new javax.swing.JTextField();
        pnlUsername5 = new javax.swing.JPanel();
        lblPass4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        pnlUsername7 = new javax.swing.JPanel();
        lblPass5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        pnlUsername8 = new javax.swing.JPanel();
        btnRegistrarse = new javax.swing.JButton();
        txtUsuarioRegistrarTipo = new javax.swing.JTextField();
        pnlUsername9 = new javax.swing.JPanel();
        lblPass6 = new javax.swing.JLabel();
        txtIDTipo = new javax.swing.JTextField();
        pnlUsername10 = new javax.swing.JPanel();
        lblPass7 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        lblPorcentaje = new javax.swing.JLabel();
        lblConectar = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        lblCalendario = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlContenido.setBackground(new java.awt.Color(0, 0, 0));
        pnlContenido.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlContenidoMouseDragged(evt);
            }
        });
        pnlContenido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlContenidoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlContenidoMouseReleased(evt);
            }
        });
        pnlContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/settings.png"))); // NOI18N
        btnAjustes.setBorder(null);
        btnAjustes.setBorderPainted(false);
        btnAjustes.setContentAreaFilled(false);
        btnAjustes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAjustes.setFocusable(false);
        btnAjustes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnAjustesMouseMoved(evt);
            }
        });
        btnAjustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAjustesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAjustesMousePressed(evt);
            }
        });
        btnAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustesActionPerformed(evt);
            }
        });
        pnlContenido.add(btnAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 600, 40, 40));

        pnlIzquierdo.setBackground(new java.awt.Color(255, 255, 255));
        pnlIzquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);

        pnlIngreso.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngreso.setForeground(new java.awt.Color(255, 255, 255));
        pnlIngreso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_62331388LKjvr2U.png"))); // NOI18N
        pnlIngreso.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 25, -1, 50));

        lblConect.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblConect.setForeground(new java.awt.Color(51, 51, 51));
        lblConect.setText("CONECT (MySQL)");
        pnlIngreso.add(lblConect, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        lblSignIn.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 34)); // NOI18N
        lblSignIn.setForeground(new java.awt.Color(51, 51, 51));
        lblSignIn.setText("SIGN IN");
        pnlIngreso.add(lblSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, -1));

        lblmundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_6233435AKIJjT1Z.png"))); // NOI18N
        pnlIngreso.add(lblmundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        lblUser.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(167, 167, 167));
        lblUser.setText("Nombre de usuario");
        lblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUserMouseClicked(evt);
            }
        });
        pnlIngreso.add(lblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(231, 231, 231));
        txtUsuario.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(167, 167, 167));
        txtUsuario.setBorder(null);
        txtUsuario.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioMousePressed(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });
        pnlIngreso.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 220, 40));

        pnlUsername.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsernameLayout = new javax.swing.GroupLayout(pnlUsername);
        pnlUsername.setLayout(pnlUsernameLayout);
        pnlUsernameLayout.setHorizontalGroup(
            pnlUsernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsernameLayout.setVerticalGroup(
            pnlUsernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlIngreso.add(pnlUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 235, 40));

        lblPass.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass.setForeground(new java.awt.Color(167, 167, 167));
        lblPass.setText("Password");
        lblPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPassMouseClicked(evt);
            }
        });
        pnlIngreso.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        psContraseña.setBackground(new java.awt.Color(231, 231, 231));
        psContraseña.setForeground(new java.awt.Color(167, 167, 167));
        psContraseña.setBorder(null);
        psContraseña.setSelectionColor(new java.awt.Color(65, 209, 123));
        psContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                psContraseñaMousePressed(evt);
            }
        });
        psContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                psContraseñaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                psContraseñaKeyTyped(evt);
            }
        });
        pnlIngreso.add(psContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 220, 40));

        pnlContraseña.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlContraseñaLayout = new javax.swing.GroupLayout(pnlContraseña);
        pnlContraseña.setLayout(pnlContraseñaLayout);
        pnlContraseñaLayout.setHorizontalGroup(
            pnlContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlContraseñaLayout.setVerticalGroup(
            pnlContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlIngreso.add(pnlContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 235, 40));

        cbPermanecerConectado.setBackground(new java.awt.Color(255, 255, 255));
        cbPermanecerConectado.setFont(new java.awt.Font("Berlin Sans FB", 0, 10)); // NOI18N
        cbPermanecerConectado.setForeground(new java.awt.Color(102, 102, 102));
        cbPermanecerConectado.setText("Mantener sesion iniciada");
        cbPermanecerConectado.setBorder(null);
        cbPermanecerConectado.setMargin(new java.awt.Insets(3, 3, 3, 3));
        pnlIngreso.add(cbPermanecerConectado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        btnAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar2.png"))); // NOI18N
        btnAcceder.setBorder(null);
        btnAcceder.setBorderPainted(false);
        btnAcceder.setContentAreaFilled(false);
        btnAcceder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAcceder.setFocusable(false);
        btnAcceder.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnAccederMouseMoved(evt);
            }
        });
        btnAcceder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAccederMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAccederMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAccederMousePressed(evt);
            }
        });
        btnAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccederActionPerformed(evt);
            }
        });
        pnlIngreso.add(btnAcceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, 70));

        lblCrear.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblCrear.setForeground(new java.awt.Color(204, 204, 204));
        lblCrear.setText("Crear una cuenta");
        pnlIngreso.add(lblCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, -1));

        lblRecuperar.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblRecuperar.setForeground(new java.awt.Color(204, 204, 204));
        lblRecuperar.setText("¿No puedes iniciar sesión?");
        pnlIngreso.add(lblRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, -1, -1));

        jTabbedPane1.addTab("", pnlIngreso);

        pnlRecuperar.setBackground(new java.awt.Color(255, 255, 255));
        pnlRecuperar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_62331388LKjvr2U.png"))); // NOI18N
        pnlRecuperar.add(lblLogo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 25, -1, 50));

        lblConect2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblConect2.setForeground(new java.awt.Color(51, 51, 51));
        lblConect2.setText("CONECT (MySQL)");
        pnlRecuperar.add(lblConect2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        lblmundo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_6233435AKIJjT1Z.png"))); // NOI18N
        pnlRecuperar.add(lblmundo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        lblSignIn2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 34)); // NOI18N
        lblSignIn2.setForeground(new java.awt.Color(51, 51, 51));
        lblSignIn2.setText("PASSWORD");
        pnlRecuperar.add(lblSignIn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 180, -1));

        lblUser2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblUser2.setForeground(new java.awt.Color(167, 167, 167));
        lblUser2.setText("Nombre de usuario");
        lblUser2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUser2MouseClicked(evt);
            }
        });
        pnlRecuperar.add(lblUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        txtUsuarioRecuperar.setBackground(new java.awt.Color(231, 231, 231));
        txtUsuarioRecuperar.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtUsuarioRecuperar.setForeground(new java.awt.Color(167, 167, 167));
        txtUsuarioRecuperar.setBorder(null);
        txtUsuarioRecuperar.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtUsuarioRecuperar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioRecuperarMousePressed(evt);
            }
        });
        txtUsuarioRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioRecuperarActionPerformed(evt);
            }
        });
        txtUsuarioRecuperar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioRecuperarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioRecuperarKeyTyped(evt);
            }
        });
        pnlRecuperar.add(txtUsuarioRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 220, 40));

        pnlUsername2.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername2Layout = new javax.swing.GroupLayout(pnlUsername2);
        pnlUsername2.setLayout(pnlUsername2Layout);
        pnlUsername2Layout.setHorizontalGroup(
            pnlUsername2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsername2Layout.setVerticalGroup(
            pnlUsername2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRecuperar.add(pnlUsername2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 235, 40));

        lblPass3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass3.setForeground(new java.awt.Color(167, 167, 167));
        lblPass3.setText("Correo Electronico");
        lblPass3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass3MouseClicked(evt);
            }
        });
        pnlRecuperar.add(lblPass3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        txtCorreoRecuperar.setBackground(new java.awt.Color(231, 231, 231));
        txtCorreoRecuperar.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtCorreoRecuperar.setForeground(new java.awt.Color(167, 167, 167));
        txtCorreoRecuperar.setBorder(null);
        txtCorreoRecuperar.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtCorreoRecuperar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCorreoRecuperarMousePressed(evt);
            }
        });
        txtCorreoRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoRecuperarActionPerformed(evt);
            }
        });
        txtCorreoRecuperar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoRecuperarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoRecuperarKeyTyped(evt);
            }
        });
        pnlRecuperar.add(txtCorreoRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 220, 40));

        pnlContraseña3.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlContraseña3Layout = new javax.swing.GroupLayout(pnlContraseña3);
        pnlContraseña3.setLayout(pnlContraseña3Layout);
        pnlContraseña3Layout.setHorizontalGroup(
            pnlContraseña3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlContraseña3Layout.setVerticalGroup(
            pnlContraseña3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRecuperar.add(pnlContraseña3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 235, 40));

        btnRecuperar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar2.png"))); // NOI18N
        btnRecuperar.setBorder(null);
        btnRecuperar.setBorderPainted(false);
        btnRecuperar.setContentAreaFilled(false);
        btnRecuperar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecuperar.setFocusable(false);
        btnRecuperar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRecuperarMouseMoved(evt);
            }
        });
        btnRecuperar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRecuperarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRecuperarMousePressed(evt);
            }
        });
        btnRecuperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarActionPerformed(evt);
            }
        });
        pnlRecuperar.add(btnRecuperar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, 70));

        lblSignIn3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 34)); // NOI18N
        lblSignIn3.setForeground(new java.awt.Color(51, 51, 51));
        lblSignIn3.setText("FORGOT");
        pnlRecuperar.add(lblSignIn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 160, -1));

        jTabbedPane1.addTab("", pnlRecuperar);

        pnlRegistro.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlRegistroMouseEntered(evt);
            }
        });
        pnlRegistro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_62331388LKjvr2U.png"))); // NOI18N
        pnlRegistro.add(lblLogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 50));

        lblConect1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        lblConect1.setForeground(new java.awt.Color(51, 51, 51));
        lblConect1.setText("CONECT (MySQL)");
        pnlRegistro.add(lblConect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        lblmundo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_6233435AKIJjT1Z.png"))); // NOI18N
        pnlRegistro.add(lblmundo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        lblSignIn1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 34)); // NOI18N
        lblSignIn1.setForeground(new java.awt.Color(51, 51, 51));
        lblSignIn1.setText("SIGN UP");
        pnlRegistro.add(lblSignIn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 140, -1));

        lblUser1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblUser1.setForeground(new java.awt.Color(167, 167, 167));
        lblUser1.setText("Nombre de usuario");
        lblUser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUser1MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        txtUsuarioRegistrar.setBackground(new java.awt.Color(231, 231, 231));
        txtUsuarioRegistrar.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtUsuarioRegistrar.setForeground(new java.awt.Color(167, 167, 167));
        txtUsuarioRegistrar.setBorder(null);
        txtUsuarioRegistrar.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtUsuarioRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioRegistrarMousePressed(evt);
            }
        });
        txtUsuarioRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioRegistrarActionPerformed(evt);
            }
        });
        txtUsuarioRegistrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioRegistrarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioRegistrarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioRegistrarKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtUsuarioRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 220, 40));

        pnlUsername1.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername1Layout = new javax.swing.GroupLayout(pnlUsername1);
        pnlUsername1.setLayout(pnlUsername1Layout);
        pnlUsername1Layout.setHorizontalGroup(
            pnlUsername1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsername1Layout.setVerticalGroup(
            pnlUsername1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 235, 40));

        lblPass1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass1.setForeground(new java.awt.Color(167, 167, 167));
        lblPass1.setText("Password");
        lblPass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass1MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        lblUser3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblUser3.setForeground(new java.awt.Color(167, 167, 167));
        lblUser3.setText("ID");
        lblUser3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUser3MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblUser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        txtID.setBackground(new java.awt.Color(231, 231, 231));
        txtID.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(167, 167, 167));
        txtID.setBorder(null);
        txtID.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtIDMousePressed(evt);
            }
        });
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 220, 40));

        pnlUsername3.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername3Layout = new javax.swing.GroupLayout(pnlUsername3);
        pnlUsername3.setLayout(pnlUsername3Layout);
        pnlUsername3Layout.setHorizontalGroup(
            pnlUsername3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsername3Layout.setVerticalGroup(
            pnlUsername3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 235, 40));

        txtContraseña.setBackground(new java.awt.Color(231, 231, 231));
        txtContraseña.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(167, 167, 167));
        txtContraseña.setBorder(null);
        txtContraseña.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtContraseñaMousePressed(evt);
            }
        });
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 220, 40));

        pnlUsername5.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername5Layout = new javax.swing.GroupLayout(pnlUsername5);
        pnlUsername5.setLayout(pnlUsername5Layout);
        pnlUsername5Layout.setHorizontalGroup(
            pnlUsername5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsername5Layout.setVerticalGroup(
            pnlUsername5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 235, 40));

        lblPass4.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass4.setForeground(new java.awt.Color(167, 167, 167));
        lblPass4.setText("Correo Electronico");
        lblPass4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass4MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblPass4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        txtCorreo.setBackground(new java.awt.Color(231, 231, 231));
        txtCorreo.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(167, 167, 167));
        txtCorreo.setBorder(null);
        txtCorreo.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCorreoMousePressed(evt);
            }
        });
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 220, 40));

        pnlUsername7.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername7Layout = new javax.swing.GroupLayout(pnlUsername7);
        pnlUsername7.setLayout(pnlUsername7Layout);
        pnlUsername7Layout.setHorizontalGroup(
            pnlUsername7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsername7Layout.setVerticalGroup(
            pnlUsername7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 235, 40));

        lblPass5.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass5.setForeground(new java.awt.Color(167, 167, 167));
        lblPass5.setText("Telefono");
        lblPass5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass5MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblPass5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        txtTelefono.setBackground(new java.awt.Color(231, 231, 231));
        txtTelefono.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(167, 167, 167));
        txtTelefono.setBorder(null);
        txtTelefono.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTelefonoMousePressed(evt);
            }
        });
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 220, 40));

        pnlUsername8.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername8Layout = new javax.swing.GroupLayout(pnlUsername8);
        pnlUsername8.setLayout(pnlUsername8Layout);
        pnlUsername8Layout.setHorizontalGroup(
            pnlUsername8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        pnlUsername8Layout.setVerticalGroup(
            pnlUsername8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 235, 40));

        btnRegistrarse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar2.png"))); // NOI18N
        btnRegistrarse.setBorder(null);
        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setContentAreaFilled(false);
        btnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarse.setEnabled(false);
        btnRegistrarse.setFocusable(false);
        btnRegistrarse.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegistrarseMouseMoved(evt);
            }
        });
        btnRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegistrarseMousePressed(evt);
            }
        });
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });
        pnlRegistro.add(btnRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, -1, 70));

        txtUsuarioRegistrarTipo.setBackground(new java.awt.Color(231, 231, 231));
        txtUsuarioRegistrarTipo.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtUsuarioRegistrarTipo.setForeground(new java.awt.Color(167, 167, 167));
        txtUsuarioRegistrarTipo.setBorder(null);
        txtUsuarioRegistrarTipo.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtUsuarioRegistrarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUsuarioRegistrarTipoMousePressed(evt);
            }
        });
        txtUsuarioRegistrarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioRegistrarTipoActionPerformed(evt);
            }
        });
        txtUsuarioRegistrarTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioRegistrarTipoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioRegistrarTipoKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtUsuarioRegistrarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 130, 40));

        pnlUsername9.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername9Layout = new javax.swing.GroupLayout(pnlUsername9);
        pnlUsername9.setLayout(pnlUsername9Layout);
        pnlUsername9Layout.setHorizontalGroup(
            pnlUsername9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlUsername9Layout.setVerticalGroup(
            pnlUsername9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 150, 40));

        lblPass6.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass6.setForeground(new java.awt.Color(167, 167, 167));
        lblPass6.setText("Nombre Tipo de Usuario");
        lblPass6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass6MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblPass6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        txtIDTipo.setBackground(new java.awt.Color(231, 231, 231));
        txtIDTipo.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        txtIDTipo.setForeground(new java.awt.Color(167, 167, 167));
        txtIDTipo.setBorder(null);
        txtIDTipo.setSelectionColor(new java.awt.Color(65, 209, 123));
        txtIDTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtIDTipoMousePressed(evt);
            }
        });
        txtIDTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDTipoActionPerformed(evt);
            }
        });
        txtIDTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIDTipoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDTipoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDTipoKeyTyped(evt);
            }
        });
        pnlRegistro.add(txtIDTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 130, 40));

        pnlUsername10.setBackground(new java.awt.Color(231, 231, 231));

        javax.swing.GroupLayout pnlUsername10Layout = new javax.swing.GroupLayout(pnlUsername10);
        pnlUsername10.setLayout(pnlUsername10Layout);
        pnlUsername10Layout.setHorizontalGroup(
            pnlUsername10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlUsername10Layout.setVerticalGroup(
            pnlUsername10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        pnlRegistro.add(pnlUsername10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 150, 40));

        lblPass7.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        lblPass7.setForeground(new java.awt.Color(167, 167, 167));
        lblPass7.setText("ID Tipo de Usuario");
        lblPass7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass7MouseClicked(evt);
            }
        });
        pnlRegistro.add(lblPass7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

        jTabbedPane1.addTab("", pnlRegistro);

        pnlIzquierdo.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, -3, 305, 646));

        pnlContenido.add(pnlIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 640));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/Salir.png"))); // NOI18N
        btnSalir.setBorder(null);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        pnlContenido.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 40, 30));

        lblPorcentaje.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 18)); // NOI18N
        lblPorcentaje.setForeground(new java.awt.Color(255, 255, 255));
        lblPorcentaje.setText("   %");
        pnlContenido.add(lblPorcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 580, 50, 30));

        lblConectar.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblConectar.setForeground(new java.awt.Color(255, 255, 255));
        lblConectar.setText("Conectado a servidor...");
        pnlContenido.add(lblConectar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 590, 150, 20));

        jProgressBar.setBorder(null);
        pnlContenido.add(jProgressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 610, 540, 10));

        lblCalendario.setBackground(new java.awt.Color(204, 204, 204));
        lblCalendario.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblCalendario.setForeground(new java.awt.Color(102, 102, 102));
        lblCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/oie_jpg.png"))); // NOI18N
        lblCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCalendarioMouseClicked(evt);
            }
        });
        pnlContenido.add(lblCalendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 470, -1, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/wall-street-cripto-1.jpg"))); // NOI18N
        lblFondo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pnlContenido.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-240, 0, 1380, 820));

        getContentPane().add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAjustesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjustesMouseMoved

    private void btnAjustesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjustesMouseExited

    private void btnAjustesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjustesMousePressed

    private void btnAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustesActionPerformed
        lblCalendario.setVisible(true);


    }//GEN-LAST:event_btnAjustesActionPerformed

    private void pnlContenidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlContenidoMousePressed
        xx = evt.getX();
        xy = evt.getY();
        //this.setOpacity(0.85f);
    }//GEN-LAST:event_pnlContenidoMousePressed

    private void pnlContenidoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlContenidoMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlContenidoMouseDragged

    private void pnlContenidoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlContenidoMouseReleased
        //this.setOpacity(1.0f);
    }//GEN-LAST:event_pnlContenidoMouseReleased

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked

        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tiempo = new Timer(5, new progreso());
    }//GEN-LAST:event_formWindowOpened

    private void lblCalendarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCalendarioMouseClicked
        lblCalendario.setVisible(false);
    }//GEN-LAST:event_lblCalendarioMouseClicked
    public void EnviarRecuperacion() {
        try {
            Properties correo = new Properties();
            correo.put("mail.smtp.host", "smtp.gmail.com");
            correo.setProperty("mail.smtp.starttls.enable", "true");
            correo.setProperty("mail.smtp.port", "587");
            correo.setProperty("mail.smtp.user", "grupo2programacioniii@gmail.com");
            correo.setProperty("mail.smtp.auth", "true");
            Session sesion = Session.getDefaultInstance(correo, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText("USUARIO: " + DatosPersonales1[2] + "\n" + "CONTRASEÑA: " + DatosPersonales1[3]);
            BodyPart ImagenAdjunta = new MimeBodyPart();
            ImagenAdjunta.setDataHandler(new DataHandler(new FileDataSource("Recuperar.png")));
            ImagenAdjunta.setFileName("Recuperar.png");
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            m.addBodyPart(ImagenAdjunta);
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress("grupo2programacioniii@gmail.com"));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(DatosPersonales1[4]));
            mensaje.setSubject("Recuperacion de Contraseña - Proyecto Nomina UMG");
            mensaje.setContent(m);
            Transport t = sesion.getTransport("smtp");
            t.connect("grupo2programacioniii@gmail.com", "jlhgdhvhhekkovcg");
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            Icon icon = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Enviado.png"));
            JOptionPane.showMessageDialog(null, "USUARIO Y CONTRASEÑA ENVIDADOS A SU CORREO CON EXITO", "EXITO", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (Exception e) {
             Icon icon2 = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/ErrorNum.png"));
             JOptionPane.showMessageDialog(null, "ES POSIBLE QUE EL CORREO NO ES BIEN ESCRITO O NO SEA GMAIL POR FAVOR CONSULTE"+e, "ERROR", JOptionPane.INFORMATION_MESSAGE, icon2);
            

        }
    }
    private void btnRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarActionPerformed

        Icon icon2 = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/ErrorNum.png"));
        Icon icon = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NohayRegistros.png"));
        BuscarRegistroUsuarios(txtUsuarioRecuperar.getText().trim(), "Usuarios", "NombreUsuario", 8);

        if (DatosPersonales1[2] == (null) && DatosPersonales1[3] == (null)) {
            JOptionPane.showMessageDialog(null, "AVISO\n" + "EL USUARIO NO EXISTE", "AVISO", JOptionPane.INFORMATION_MESSAGE, icon2);
        } else {

            BuscarRegistroUsuarios(DatosPersonales1[1], "Concepto", "ID_Concepto", 25);
            if (txtUsuarioRecuperar.getText().trim().equals(DatosPersonales1[2]) && txtCorreoRecuperar.getText().trim().equals(DatosPersonales1[4])) {
                EnviarRecuperacion();

            } else {
                JOptionPane.showMessageDialog(null, "AVISO\n" + "NOMBRE DE USUARIO O CORREO INCORRECTO", "AVISO", JOptionPane.INFORMATION_MESSAGE, icon);
            }

        }
    }//GEN-LAST:event_btnRecuperarActionPerformed

    private void btnRecuperarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecuperarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRecuperarMousePressed

    private void btnRecuperarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecuperarMouseExited
        btnRecuperar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar2.png")));
    }//GEN-LAST:event_btnRecuperarMouseExited

    private void btnRecuperarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecuperarMouseMoved
        btnRecuperar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar.png")));
    }//GEN-LAST:event_btnRecuperarMouseMoved

    private void lblPass3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPass3MouseClicked

    private void txtCorreoRecuperarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoRecuperarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoRecuperarKeyTyped

    private void txtCorreoRecuperarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoRecuperarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoRecuperarKeyPressed

    private void txtCorreoRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoRecuperarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoRecuperarActionPerformed

    private void txtCorreoRecuperarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoRecuperarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoRecuperarMousePressed

    private void lblUser2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUser2MouseClicked

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed

       //  InicioSesion(); InicioSesion();
    }//GEN-LAST:event_btnAccederActionPerformed

    private void btnAccederMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccederMousePressed
        //  InicioSesion();
    }//GEN-LAST:event_btnAccederMousePressed

    private void btnAccederMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccederMouseExited
        btnAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar2.png")));
    }//GEN-LAST:event_btnAccederMouseExited

    private void btnAccederMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccederMouseMoved
        btnAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar.png")));
    }//GEN-LAST:event_btnAccederMouseMoved

    private void psContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psContraseñaKeyTyped
        // lblUser.setText("");
    }//GEN-LAST:event_psContraseñaKeyTyped

    private void psContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psContraseñaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnAcceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar.png")));
            InicioSesion();

        }
    }//GEN-LAST:event_psContraseñaKeyPressed

    private void psContraseñaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_psContraseñaMousePressed
        // lblPass.setText("");

    }//GEN-LAST:event_psContraseñaMousePressed

    private void lblPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseClicked
        //  lblPass.setText("");

    }//GEN-LAST:event_lblPassMouseClicked

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        //lblUser.setText("");
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed

    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMousePressed
        // lblUser.setText("");
    }//GEN-LAST:event_txtUsuarioMousePressed

    private void lblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUserMouseClicked
        ///  lblUser.setText("");

    }//GEN-LAST:event_lblUserMouseClicked

    private void lblUser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUser1MouseClicked

    private void txtUsuarioRegistrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarMousePressed

    private void txtUsuarioRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarActionPerformed

    private void txtUsuarioRegistrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarKeyPressed

    private void txtUsuarioRegistrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarKeyTyped

    private void lblPass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPass1MouseClicked

    private void lblUser3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUser3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUser3MouseClicked

    private void txtIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDMousePressed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKeyPressed

    private void txtIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKeyTyped

    private void txtContraseñaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtContraseñaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaMousePressed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void txtContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaKeyPressed

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaKeyTyped

    private void lblPass4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPass4MouseClicked

    private void txtCorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoMousePressed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoKeyPressed

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void lblPass5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPass5MouseClicked

    private void txtTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTelefonoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoMousePressed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnRegistrarseMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarseMouseMoved
        btnRegistrarse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar.png")));
    }//GEN-LAST:event_btnRegistrarseMouseMoved

    private void btnRegistrarseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarseMouseExited
        btnRegistrarse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/entrar2.png")));
    }//GEN-LAST:event_btnRegistrarseMouseExited

    private void btnRegistrarseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarseMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarseMousePressed

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        CrearTipo();
        
        
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void txtUsuarioRecuperarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioRecuperarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRecuperarMousePressed

    private void txtUsuarioRecuperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioRecuperarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRecuperarActionPerformed

    private void txtUsuarioRecuperarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRecuperarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRecuperarKeyPressed

    private void txtUsuarioRecuperarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRecuperarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRecuperarKeyTyped

    private void txtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyReleased

    }//GEN-LAST:event_txtIDKeyReleased

    private void txtUsuarioRegistrarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarKeyReleased

    }//GEN-LAST:event_txtUsuarioRegistrarKeyReleased

    private void txtContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyReleased

    }//GEN-LAST:event_txtContraseñaKeyReleased

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased

    }//GEN-LAST:event_txtCorreoKeyReleased

    private void pnlRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistroMouseEntered
        JTextField[] Cajas = {txtID, txtUsuarioRegistrar, txtContraseña, txtCorreo, txtTelefono,txtIDTipo, txtUsuarioRegistrarTipo};
        int si[] = new int[7];
        for (int i = 0; i < 7; i++) {
            if (Cajas[i].getText().trim().equals("")) {

                si[i] = 0;
            } else {
                si[i] = 1;
            }

        }
        if (si[0] == 1 && si[1] == 1 && si[2] == 1 && si[3] == 1 && si[4] == 1&& si[5] == 1&& si[6] == 1) {
            btnRegistrarse.setEnabled(true);
        } else {
            btnRegistrarse.setEnabled(false);
        }
    }//GEN-LAST:event_pnlRegistroMouseEntered

    private void txtUsuarioRegistrarTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarTipoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarTipoKeyTyped

    private void txtUsuarioRegistrarTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarTipoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarTipoKeyPressed

    private void txtUsuarioRegistrarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarTipoActionPerformed

    private void txtUsuarioRegistrarTipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioRegistrarTipoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioRegistrarTipoMousePressed

    private void lblPass6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPass6MouseClicked

    private void lblPass7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPass7MouseClicked

    private void txtIDTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDTipoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTipoKeyTyped

    private void txtIDTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDTipoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTipoKeyReleased

    private void txtIDTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDTipoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTipoKeyPressed

    private void txtIDTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTipoActionPerformed

    private void txtIDTipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIDTipoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDTipoMousePressed

    private void btnAccederMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAccederMouseClicked
       InicioSesion();
    }//GEN-LAST:event_btnAccederMouseClicked

    /**
     * @param args the command line arguments
     */
    int xx, xy;

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcceder;
    private javax.swing.JButton btnAjustes;
    private javax.swing.JButton btnRecuperar;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox cbPermanecerConectado;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCalendario;
    private javax.swing.JLabel lblConect;
    private javax.swing.JLabel lblConect1;
    private javax.swing.JLabel lblConect2;
    private javax.swing.JLabel lblConectar;
    private javax.swing.JLabel lblCrear;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblLogo2;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPass1;
    private javax.swing.JLabel lblPass3;
    private javax.swing.JLabel lblPass4;
    private javax.swing.JLabel lblPass5;
    private javax.swing.JLabel lblPass6;
    private javax.swing.JLabel lblPass7;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblRecuperar;
    private javax.swing.JLabel lblSignIn;
    private javax.swing.JLabel lblSignIn1;
    private javax.swing.JLabel lblSignIn2;
    private javax.swing.JLabel lblSignIn3;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUser1;
    private javax.swing.JLabel lblUser2;
    private javax.swing.JLabel lblUser3;
    private javax.swing.JLabel lblmundo;
    private javax.swing.JLabel lblmundo1;
    private javax.swing.JLabel lblmundo2;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlContraseña;
    private javax.swing.JPanel pnlContraseña3;
    private javax.swing.JPanel pnlIngreso;
    private javax.swing.JPanel pnlIzquierdo;
    private javax.swing.JPanel pnlRecuperar;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JPanel pnlUsername;
    private javax.swing.JPanel pnlUsername1;
    private javax.swing.JPanel pnlUsername10;
    private javax.swing.JPanel pnlUsername2;
    private javax.swing.JPanel pnlUsername3;
    private javax.swing.JPanel pnlUsername5;
    private javax.swing.JPanel pnlUsername7;
    private javax.swing.JPanel pnlUsername8;
    private javax.swing.JPanel pnlUsername9;
    private javax.swing.JPasswordField psContraseña;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCorreoRecuperar;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDTipo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtUsuarioRecuperar;
    private javax.swing.JTextField txtUsuarioRegistrar;
    private javax.swing.JTextField txtUsuarioRegistrarTipo;
    // End of variables declaration//GEN-END:variables
}
