package Frames;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.barcodelib.barcode.QRCode;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import ds.desktop.notify.DesktopNotify;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import Agenda.*;
/**
 *
 * @author Langas
 */
public class Plataforma extends javax.swing.JFrame implements Runnable {


    //RELOJ
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public static String constante = "D:\\Proyectos_Git\\Nueva_Version_Nomina\\Proyecto - Nomina de Salarios\\src\\Imagenes";
    String Base_de_Datos = "jdbc:mysql://35.225.163.187/Nomina_de_Empleados";
    String Usuario = "josue";
    String Clave = "zapata";

    //Esta variable la cambian es la direccion del manual, el word esta en la carpeta src
    String URL= "D:\\Proyectos_Git\\Nueva_Version_Nomina\\Proyecto - Nomina de Salarios\\src\\Manual-de-usuario.docx";
    //Esta variable la cambian es la direccion para generar los QR
    //public static String constante = "D:\\Proyectos_Git\\Nueva_Version_Nomina\\Proyecto - Nomina de Salarios\\src\\Imagenes";
     /*//Esta variable la solo una vez es la Base de datos general
=======
    //Esta variable la cambian es la direccion del manual, el word esta en la carpeta src
    String URL = "C:\\Users\\jorgi\\Documents\\NuevoProyecto\\PrograIII-Portales-master\\Proyecto - Nomina de Salarios\\src\\Manual-de-usuario.docx";
    //Esta variable la cambian es la direccion para generar los QR
    public static String constante = "C:\\Users\\jorgi\\Documents\\NuevoProyecto\\PrograIII-Portales-master\\Proyecto - Nomina de Salarios\\src\\Imagenes";
    //Esta variable la solo una vez es la Base de datos general
>>>>>>> 02906463b984441bf627bfd806af40a730675726
    String Base_de_Datos = "jdbc:mysql://35.225.163.187/Nomina_de_Empleados";
    //Usuario
    String Usuario = "jorge";
    //Uclave
    String Clave = "condominio";
<<<<<<< HEAD
    
>>>>>>> 2ff761fde8536c93db6939f7e4140d85dfe4572f*/
//=======
//>>>>>>> 02906463b984441bf627bfd806af40a730675726

    boolean theme = false;
    boolean graficas = false;
    boolean maximized = true;
    String[][] BasedeDatosTipos;
    String[][] BasedeDatosUsuarios;
    String[][] BasedeDatosEmpleadosPlanillaDet;
    String[][] BasedeDatosEmpleados;
    String[][] BasedeDatosPuestos;
    String[][] BasedeDatosDep;
    String[][] BasedeDatosConceptos;
    String[][] BasedeDatosPlanillaGen;
    int[] color = {222, 222, 222};
    int xy, xx;
    float TotalPercepcion = 0;
    float TotalDeducion = 0;
    String[] NombresColumnas = {"ID_Concepto", "NombreConcepto", "IngresarUser", "ModificarUser", "EliminarUser", "ConsultarUser",
        "IngresarUserTipo", "ModificarUserTipo", "EliminarUserTipo", "ConsultarUserTipo", "IngresarEmpleado", "ModificarEmpleado", "EliminarEmpleado", "ConsultarEmpleado",
        "IngresarDepartamento", "ModificarDepartamento", "EliminarDepartamento", "ConsultarDepartamento",
        "IngresarConcepto", "ModificarConcepto", "EliminarConcepto", "ConsultarConcepto", "Tabla", "Barras", "Pastel"};
    String[] NombresColumnasUsuarios = {"ID_Usuario", "ID_Concepto", "NombreUsuario", "Contraseña", "CorreoElectronico", "Telefono", "Direcciones",
        "Sesiones",};
    String[] NombresColumnasPlanillaDet = {"id_planillaDet", "id_conceptoPlanilla", "id_empleado", "valor_conceptoDet"};
    String[] NombresColumnasPlanillaGen = {"id_planillaGen", "id_empleado", "total_percepsion", "total_deduccion", "total_liquido"};
    String[] NombresColumnasEmpleados = {"id_empleado", "nombre_empleado", "apellido_empleado", "dpi_empleado", "telefono_empleado", "estado_empleado", "sueldo", "id_puesto", "id_departamento", "ID_Usuario"};
    String[] NombresColumnasPuestos = {"id_puesto", "nombre_puestos", "estado"};
    String[] NombresColumnasDep = {"id_departamento", "nombre_departamento", "estado"};
    String[] NombresColumnasConceptos = {"id_conceptoPlanilla", "nombre_concepto", "tipo_concepto", "clase_concepto", "Valor_concepto", "ID_Usuario"};

    int RegistrosTipo = CantidadDeRegistros("Concepto");
    int RegistrosUsuario = CantidadDeRegistros("Usuarios");
    int RegistrosPlanillaDet = CantidadDeRegistros("PlanillaDet");
    int RegistrosEmpleados = CantidadDeRegistros("Empleado");
    int RegistrosDep = CantidadDeRegistros("Departamento");
    int RegistrosPuestos = CantidadDeRegistros("Puesto");
    int RegistrosConceptos = CantidadDeRegistros("Concepto_Planilla");
    int RegistrosPlanillaGen = CantidadDeRegistros("PlanillaGen");
    TableRowSorter trs;

    public String[] DatosPersonales2 = new String[8];
    public String[] PermisosOpciones2 = new String[23];

    /**
     * Creates new form Plataforma
     */
    public Plataforma() {

        initComponents();
        //reloj
        h1=new Thread(this);
        h1.start();

        this.setLocationRelativeTo(null);
        for (int i = 0; i < 8; i++) {
            DatosPersonales2[i] = Login2.DatosPersonales1[i];
        }
        for (int i = 2; i < 25; i++) {
            PermisosOpciones2[i - 2] = Login2.PermisosOpciones1[i];
        }
        JButton[] BotonesPermisos = {btnOpsion_Ingresar, btnOpsion_Modificar, btnOpsion_Eliminar, btnOpsion_Consultar, btnOpsion_IngresarTipo, btnOpsion_ModificarTipo,
            btnOpsion_EliminarTipo, btnOpsion_ConsultarTipo, btnOpsion_IngresarEmpleado, btnOpsion_ModificarEmpleado, btnOpsion_EliminarEmpleado, btnOpsion_ConsultaEmpleado,
            btnOpsion_IngresarDep, btnOpsion_ModificarDep, btnOpsion_EliminarDep, btnOpsion_ConsultaDep,
            btnOpsion_IngresarConcepto, btnOpsion_ModificarConcepto, btnOpsion_EliminarConcepto, btnOpsion_ConsultaConcepto, btnTabla, btnBarras, btnPastel};

        for (int i = 0; i < 23; i++) {
            if (PermisosOpciones2[i].equals("1")) {
                BotonesPermisos[i].setEnabled(true);
                if (i == 12) {
                    btnOpsion_IngresarPuesto.setEnabled(true);
                }
                if (i == 13) {
                    btnOpsion_ModificarPuesto.setEnabled(true);
                }
                if (i == 14) {
                    btnOpsion_EliminarPuesto.setEnabled(true);
                }
                if (i == 15) {
                    btnOpsion_ConsultaPuesto.setEnabled(true);
                }
            } else {
                if (PermisosOpciones2[21].equals("0") && PermisosOpciones2[22].equals("0")) {
                    btnGrafica.setEnabled(false);
                }
                if (i == 12) {
                    btnOpsion_IngresarPuesto.setEnabled(false);
                }
                if (i == 13) {
                    btnOpsion_ModificarPuesto.setEnabled(false);
                }
                if (i == 14) {
                    btnOpsion_EliminarPuesto.setEnabled(false);
                }
                if (i == 15) {
                    btnOpsion_ConsultaPuesto.setEnabled(false);
                }
                BotonesPermisos[i].setEnabled(false);
            }
        }
        /*lblUsername.setText(DatosPersonales2[2]);
        lblTipoUser.setText(Login2.PermisosOpciones1[1]);
        lblIde.setText(DatosPersonales2[0]);
        lblUsernameNomina.setText(DatosPersonales2[2]);
        lblTipoNomina1.setText(Login2.PermisosOpciones1[1]);
        lblIdeNomina.setText(DatosPersonales2[0]);
        lblIdeIncio.setText(DatosPersonales2[0]);*/

        lblnameBitacora.setText(DatosPersonales2[2]);
        lblTipodeUser.setText(Login2.PermisosOpciones1[1]);
        lblIP.setText(DatosPersonales2[6]);
        int sesion1 = Integer.parseInt(DatosPersonales2[7]) + 1;
        lblMAC.setText(Integer.toString(sesion1));
        lblCorreo.setText(DatosPersonales2[4]);
        lblTel.setText(DatosPersonales2[5]);

        lblIdeIncio1.setText(DatosPersonales2[0]);
        lblnameBitacora1.setText(DatosPersonales2[2]);
        lblTipodeUser1.setText(Login2.PermisosOpciones1[1]);
        lblIP1.setText(DatosPersonales2[6]);

        lblMAC1.setText(Integer.toString(sesion1));
        lblCorreo1.setText(DatosPersonales2[4]);
        lblTel1.setText(DatosPersonales2[5]);

        lblIdeIncio2.setText(DatosPersonales2[0]);
        lblnameBitacora2.setText(DatosPersonales2[2]);
        lblTipodeUser2.setText(Login2.PermisosOpciones1[1]);
        lblIP2.setText(DatosPersonales2[6]);

        lblMAC2.setText(Integer.toString(sesion1));
        lblCorreo2.setText(DatosPersonales2[4]);
        lblTel2.setText(DatosPersonales2[5]);
        JButton[] botones = {btnHome, btnAjustes, btnCuenta, btnGrafica, btnCalculadora, btnPagina, btnCerrar};
        for (JButton btn : botones) {
            btn.setBackground(new Color(12, 12, 12));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    btn.setBackground(new Color(0, 153, 255));

                }

                @Override
                public void mouseExited(MouseEvent e) {

                    btn.setBackground(new Color(12, 12, 12));

                }

            });

        }

        JButton[] MenuBarUsuarios = {btnCatalogos, btnProcesos, btnInformes, btnHerramientas, btnHelp, btnMantenimientoUsuarios, btnMantenimientoUsuarios1,
            btnME, btnMP, btnMPP, btnMD, btnMC, btnIP, btnCalc, btnManual};
        for (JButton btn : MenuBarUsuarios) {
            btn.setBackground(new Color(46, 46, 46));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    btn.setBackground(new Color(75, 75, 75));

                }

                @Override
                public void mouseExited(MouseEvent e) {

                    btn.setBackground(new Color(46, 46, 46));

                }

            });

        }
        JButton[] MenuBarNomina = {btnCatalogos1, btnProcesos1, btnInformes1, btnHerramientas1, btnHelp1};
        for (JButton btn : MenuBarNomina) {
            btn.setBackground(new Color(46, 46, 46));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    btn.setBackground(new Color(75, 75, 75));

                }

                @Override
                public void mouseExited(MouseEvent e) {

                    btn.setBackground(new Color(46, 46, 46));

                }

            });

        }
        JButton[] subbotones = {btnTemas, btnBarras, btnPastel};
        for (JButton btn : subbotones) {
            btn.setBackground(new Color(32, 32, 32));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                    btn.setBackground(new Color(153, 153, 255));

                }

                @Override
                public void mouseExited(MouseEvent e) {

                    btn.setBackground(new Color(32, 32, 32));

                }

            });

        }

        JButton[] OpcionesMenuUsuarios = {btnOpsion_Ingresar, btnOpsion_Modificar, btnOpsion_Eliminar, btnOpsion_Consultar};
        for (JButton btn : OpcionesMenuUsuarios) {
            btn.setBackground(new Color(36, 36, 36));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(93, 136, 222));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(36, 36, 36));

                }

            });

        }

        JButton[] OpcionesMenuUsuariosTipo = {btnOpsion_IngresarTipo, btnOpsion_ModificarTipo, btnOpsion_EliminarTipo, btnOpsion_ConsultarTipo};
        for (JButton btn : OpcionesMenuUsuariosTipo) {
            btn.setBackground(new Color(36, 36, 36));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(255, 102, 102));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(36, 36, 36));

                }

            });

        }

        JButton[] OpcionesMenuNominaIngreso = {btnOpsion_IngresarEmpleado, btnOpsion_ModificarEmpleado, btnOpsion_EliminarEmpleado, btnOpsion_ConsultaEmpleado};
        for (JButton btn : OpcionesMenuNominaIngreso) {
            btn.setBackground(new Color(36, 36, 36));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(77, 107, 224));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(36, 36, 36));

                }

            });
        }
        JButton[] OpcionesMenuNominaConsulta = {btnOpsion_IngresarConcepto, btnOpsion_ModificarConcepto, btnOpsion_EliminarConcepto, btnOpsion_ConsultaConcepto};
        for (JButton btn : OpcionesMenuNominaConsulta) {
            btn.setBackground(new Color(36, 36, 36));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(40, 132, 225));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(36, 36, 36));

                }

            });
        }
        JButton[] OpcionesMenuNominaModificar = {btnOpsion_IngresarPuesto, btnOpsion_ModificarPuesto, btnOpsion_EliminarPuesto, btnOpsion_ConsultaPuesto};
        for (JButton btn : OpcionesMenuNominaModificar) {
            btn.setBackground(new Color(36, 36, 36));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(0, 175, 219));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(36, 36, 36));

                }

            });
        }
        JButton[] OpcionesMenuNominaEliminar = {btnOpsion_IngresarDep, btnOpsion_ModificarDep, btnOpsion_EliminarDep, btnOpsion_ConsultaDep};
        for (JButton btn : OpcionesMenuNominaEliminar) {
            btn.setBackground(new Color(36, 36, 36));
            btn.setUI(new BasicButtonUI());
            btn.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(0, 163, 163));

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(36, 36, 36));

                }

            });
        }
        //pnlTemas.removeAll();
        pnlTemas.setVisible(false);
        pnlContenido.removeAll();
        pnlContenido.repaint();
        pnlContenido.revalidate();
        pnlContenido.add(pnlMenuInicio);
        pnlContenido.add(pnlIInicio);
        pnlContenido.repaint();
        pnlContenido.revalidate();

        pnlMenu_barUser.setVisible(false);
        lblNomina.setForeground(new java.awt.Color(204, 204, 204));
        lblUsuarios.setForeground(new java.awt.Color(204, 204, 204));
        lblIinicio.setForeground(new java.awt.Color(0, 204, 204));

        pnlOpciones.setVisible(false);
        pnlOpcionesTipo.setVisible(false);

        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);

        lblClaro.setVisible(false);
        lblOscuro.setVisible(false);
        pnlSubOpcionTema.setVisible(false);
        pnlGraph.setVisible(false);
        btnMantenimientoUsuarios.setVisible(false);
        btnMantenimientoUsuarios1.setVisible(false);

        pnlCatalogo.setVisible(false);
        pnlProcesos.setVisible(false);
        pnlHerramientas.setVisible(false);
        pnlInformes.setVisible(false);
        pnlAyuda.setVisible(false);
        /* Timer tiempoDeAnuncio = new Timer();

        TimerTask task = new TimerTask() {
            int tiempo = 0;

            @Override
            public void run() {

                if (tiempo == 1) {
                    lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/fondo4.jpg")));
                    // lbllogouser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/menubaruser.png")));
                } else if (tiempo == 2) {
                    lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/fondo5.jpg")));
                    //lbllogouser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/menubaruser.png")));
                    tiempo = 0;
                } else if (tiempo == 3) {
                    lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/fondo0.jpg")));
                    // lbllogouser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/menubaruser.png")));

                }

                tiempo++;
            }
        };
        tiempoDeAnuncio.schedule(task, 10, 2000);*/
        GuardarBasedeDatosMatriz("Concepto", 25, NombresColumnas);
        GuardarBasedeDatosMatrizUsuarios("Usuarios", 8, NombresColumnasUsuarios);
        GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);
        GuardarBasedeDatosMatrizEmpleadosPlanillaGen("PlanillaGen", 5, NombresColumnasPlanillaGen);
        GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
        GuardarBasedeDatosMatrizPuestos("Puesto", 3, NombresColumnasPuestos);
        GuardarBasedeDatosMatrizDep("Departamento", 3, NombresColumnasDep);
        GuardarBasedeDatosMatrizConceptos("Concepto_Planilla", 6, NombresColumnasConceptos);
        AgregarItemsdeTipo();
        tblUsuariosModificar.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblUsuariosModificar.getTableHeader().setOpaque(false);
        tblUsuariosModificar.getTableHeader().setBackground(new Color(51, 51, 51));
        tblUsuariosModificar.getTableHeader().setForeground(new Color(255, 255, 255));
        tblUsuariosModificar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));

        tblUsuariosEliminar.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblUsuariosEliminar.getTableHeader().setOpaque(false);
        tblUsuariosEliminar.getTableHeader().setBackground(new Color(51, 51, 51));
        tblUsuariosEliminar.getTableHeader().setForeground(new Color(255, 255, 255));
        tblUsuariosEliminar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));

        tblUsuariosConsulta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblUsuariosConsulta.getTableHeader().setOpaque(false);
        tblUsuariosConsulta.getTableHeader().setBackground(new Color(51, 51, 51));
        tblUsuariosConsulta.getTableHeader().setForeground(new Color(255, 255, 255));
        tblUsuariosConsulta.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));

        tblUsuariosModificarTipo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblUsuariosModificarTipo.getTableHeader().setOpaque(false);
        tblUsuariosModificarTipo.getTableHeader().setBackground(new Color(51, 51, 51));
        tblUsuariosModificarTipo.getTableHeader().setForeground(new Color(255, 255, 255));

        tblUsuariosModificarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));

        tblUsuariosEliminarTipo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblUsuariosEliminarTipo.getTableHeader().setOpaque(false);
        tblUsuariosEliminarTipo.getTableHeader().setBackground(new Color(51, 51, 51));
        tblUsuariosEliminarTipo.getTableHeader().setForeground(new Color(255, 255, 255));
        tblUsuariosEliminarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));

        tblTipodeUsuarioConsulta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblTipodeUsuarioConsulta.getTableHeader().setOpaque(false);
        tblTipodeUsuarioConsulta.getTableHeader().setBackground(new Color(51, 51, 51));
        tblTipodeUsuarioConsulta.getTableHeader().setForeground(new Color(255, 255, 255));
        tblTipodeUsuarioConsulta.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));

        tblPlanillaDet.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPlanillaDet.getTableHeader().setOpaque(false);
        tblPlanillaDet.getTableHeader().setBackground(new Color(51, 51, 51));
        tblPlanillaDet.getTableHeader().setForeground(new Color(255, 255, 255));
        tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));

        tblEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblEmpleados.getTableHeader().setOpaque(false);
        tblEmpleados.getTableHeader().setBackground(new Color(51, 51, 51));
        tblEmpleados.getTableHeader().setForeground(new Color(255, 255, 255));
        tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));

        tblPuestos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPuestos.getTableHeader().setOpaque(false);
        tblPuestos.getTableHeader().setBackground(new Color(51, 51, 51));
        tblPuestos.getTableHeader().setForeground(new Color(255, 255, 255));
        tblPuestos.setModel(new DefaultTableModel(BasedeDatosPuestos, NombresColumnasPuestos));

        tblDep.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblDep.getTableHeader().setOpaque(false);
        tblDep.getTableHeader().setBackground(new Color(51, 51, 51));
        tblDep.getTableHeader().setForeground(new Color(255, 255, 255));
        tblDep.setModel(new DefaultTableModel(BasedeDatosDep, NombresColumnasDep));

        tblConceptos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblConceptos.getTableHeader().setOpaque(false);
        tblConceptos.getTableHeader().setBackground(new Color(51, 51, 51));
        tblConceptos.getTableHeader().setForeground(new Color(255, 255, 255));
        tblConceptos.setModel(new DefaultTableModel(BasedeDatosConceptos, NombresColumnasConceptos));

        tblPlanillaGen.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPlanillaGen.getTableHeader().setOpaque(false);
        tblPlanillaGen.getTableHeader().setBackground(new Color(51, 51, 51));
        tblPlanillaGen.getTableHeader().setForeground(new Color(255, 255, 255));
        tblPlanillaGen.setModel(new DefaultTableModel(BasedeDatosPlanillaGen, NombresColumnasPlanillaGen));

        AgregarNuevaSesion();
        Graficar();

    }

    @SuppressWarnings("unchecked")
    public void run() {
        //definimos el hilo
                Thread t= Thread.currentThread();
                
                while(t==h1){
                    
                    calcula();
                    lbl_reloj.setText(hora+":"+minutos+":"+segundos+" "+ampm);
                    try {
                        Thread.sleep(1000);
                        
                    } catch (Exception e) {
                    }
                }
            }

            private void calcula() {
                Calendar calendario = new GregorianCalendar();
                Date FechaHoraActual= new Date();
                calendario.setTime(FechaHoraActual);
                ampm= calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
                
                if (ampm.equals("PM")) {
                    int h=calendario.get(Calendar.HOUR_OF_DAY)-12;
                    hora=h>9?""+h:"0"+h;
                    
                }else
                {
                    hora=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
                }
                minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
                segundos=calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
                
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel32 = new javax.swing.JLabel();
        jPanel_Plataforma = new javax.swing.JPanel();
        pnlizquierdo = new javax.swing.JPanel();
        pnlLogo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        btnAjustes = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        btnGrafica = new javax.swing.JButton();
        btnCalculadora = new javax.swing.JButton();
        btnPagina = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        pnlSubOpcionTema = new javax.swing.JPanel();
        btnTemas = new javax.swing.JButton();
        pnlGraph = new javax.swing.JPanel();
        pnlSubOpcionGrafica = new javax.swing.JPanel();
        btnBarras = new javax.swing.JButton();
        pnlSubOpcionGrafica2 = new javax.swing.JPanel();
        btnPastel = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        pnlEncabezados = new javax.swing.JPanel();
        lblIinicio = new javax.swing.JLabel();
        lblUsuarios = new javax.swing.JLabel();
        lblNomina = new javax.swing.JLabel();
        pnlOpciones_ventana = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        pnlTemas = new javax.swing.JPanel();
        lblOscuro = new javax.swing.JLabel();
        lblClaro = new javax.swing.JLabel();
        lbl_reloj = new javax.swing.JLabel();
        lblNomina1 = new javax.swing.JLabel();
        pnlMenu_barUser = new javax.swing.JPanel();
        lbllogouser = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        pnlMenuInicio = new javax.swing.JPanel();
        lblUsernameInicio = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pnlDetalles = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        lblMAC = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblTipodeUser = new javax.swing.JLabel();
        lblnameBitacora = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblIdeIncio = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        pnlContacto = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lblTel = new javax.swing.JLabel();
        cbVertical = new javax.swing.JCheckBox();
        cbHorizontal = new javax.swing.JCheckBox();
        cbLineal = new javax.swing.JCheckBox();
        cbPastel = new javax.swing.JCheckBox();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        pnlMenuUsuarios = new javax.swing.JPanel();
        btnCatalogos = new javax.swing.JButton();
        btnProcesos = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnInformes = new javax.swing.JButton();
        btnHerramientas = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnInicioUsuarios = new javax.swing.JButton();
        lblIngresarUser = new javax.swing.JLabel();
        btnTipodeUsuario = new javax.swing.JButton();
        lbltipodeUsuario = new javax.swing.JLabel();
        btnMantenimientoUsuarios = new javax.swing.JButton();
        pnlOpciones = new javax.swing.JPanel();
        btnOpsion_Consultar = new javax.swing.JButton();
        btnOpsion_Ingresar = new javax.swing.JButton();
        btnOpsion_Modificar = new javax.swing.JButton();
        btnOpsion_Eliminar = new javax.swing.JButton();
        btnMantenimientoUsuarios1 = new javax.swing.JButton();
        pnlOpcionesTipo = new javax.swing.JPanel();
        btnOpsion_ConsultarTipo = new javax.swing.JButton();
        btnOpsion_IngresarTipo = new javax.swing.JButton();
        btnOpsion_ModificarTipo = new javax.swing.JButton();
        btnOpsion_EliminarTipo = new javax.swing.JButton();
        pnlDetalles1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lblMAC1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblTipodeUser1 = new javax.swing.JLabel();
        lblnameBitacora1 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblIdeIncio1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblIP1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlContacto1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblCorreo1 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lblTel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jDayChooser2 = new com.toedter.calendar.JDayChooser();
        pnlMenu = new javax.swing.JPanel();
        btnEmpleados = new javax.swing.JButton();
        lblEmpleados = new javax.swing.JLabel();
        btnPuestos = new javax.swing.JButton();
        lblConceptos = new javax.swing.JLabel();
        btnDepartamentos = new javax.swing.JButton();
        lblPuestos = new javax.swing.JLabel();
        btnConceptos = new javax.swing.JButton();
        lblDep = new javax.swing.JLabel();
        btnTabla = new javax.swing.JButton();
        lblTabla = new javax.swing.JLabel();
        pnlCatalogo = new javax.swing.JPanel();
        btnME = new javax.swing.JButton();
        btnMP = new javax.swing.JButton();
        btnMD = new javax.swing.JButton();
        pnlProcesos = new javax.swing.JPanel();
        btnMC = new javax.swing.JButton();
        btnMPP = new javax.swing.JButton();
        pnlInformes = new javax.swing.JPanel();
        btnIP = new javax.swing.JButton();
        pnlHerramientas = new javax.swing.JPanel();
        btnCalc = new javax.swing.JButton();
        pnlAyuda = new javax.swing.JPanel();
        btnManual = new javax.swing.JButton();
        pnlOpciones_NominaConceptos = new javax.swing.JPanel();
        btnOpsion_EliminarConcepto = new javax.swing.JButton();
        btnOpsion_IngresarConcepto = new javax.swing.JButton();
        btnOpsion_ModificarConcepto = new javax.swing.JButton();
        btnOpsion_ConsultaConcepto = new javax.swing.JButton();
        pnlOpciones_NominaDepartamentos = new javax.swing.JPanel();
        btnOpsion_IngresarDep = new javax.swing.JButton();
        btnOpsion_ModificarDep = new javax.swing.JButton();
        btnOpsion_EliminarDep = new javax.swing.JButton();
        btnOpsion_ConsultaDep = new javax.swing.JButton();
        pnlOpciones_NominaPuestos = new javax.swing.JPanel();
        btnOpsion_IngresarPuesto = new javax.swing.JButton();
        btnOpsion_ModificarPuesto = new javax.swing.JButton();
        btnOpsion_EliminarPuesto = new javax.swing.JButton();
        btnOpsion_ConsultaPuesto = new javax.swing.JButton();
        pnlOpciones_NominaEmpleados = new javax.swing.JPanel();
        btnOpsion_IngresarEmpleado = new javax.swing.JButton();
        btnOpsion_ModificarEmpleado = new javax.swing.JButton();
        btnOpsion_ConsultaEmpleado = new javax.swing.JButton();
        btnOpsion_EliminarEmpleado = new javax.swing.JButton();
        pnlDetalles2 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        lblMAC2 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblTipodeUser2 = new javax.swing.JLabel();
        lblnameBitacora2 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblIdeIncio2 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblIP2 = new javax.swing.JLabel();
        pnlContacto2 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        lblCorreo2 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        lblTel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jDayChooser3 = new com.toedter.calendar.JDayChooser();
        jPanel2 = new javax.swing.JPanel();
        btnHelp1 = new javax.swing.JButton();
        btnHerramientas1 = new javax.swing.JButton();
        btnInformes1 = new javax.swing.JButton();
        btnProcesos1 = new javax.swing.JButton();
        btnCatalogos1 = new javax.swing.JButton();
        pnlIInicio = new javax.swing.JPanel();
        pnlBienvenida = new javax.swing.JPanel();
        pnlCuerpoUsuarios = new javax.swing.JPanel();
        pnlInicio1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlIngreso_Usuarios = new javax.swing.JPanel();
        lblIngresoUser = new javax.swing.JLabel();
        lblname1 = new javax.swing.JLabel();
        lblAsterisco = new javax.swing.JLabel();
        lblNombreDeUsuario = new javax.swing.JLabel();
        pnlNombreDeUsuario = new javax.swing.JPanel();
        txtNombreDeUsuario = new javax.swing.JTextField();
        pnlID = new javax.swing.JPanel();
        txtIDUsuario = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        pnlContraseña = new javax.swing.JPanel();
        txtContraseñaUser = new javax.swing.JPasswordField();
        Ver = new javax.swing.JLabel();
        No_Ver = new javax.swing.JLabel();
        lblConfirmar = new javax.swing.JLabel();
        pnlConfirmar = new javax.swing.JPanel();
        txtConfirmarUser = new javax.swing.JPasswordField();
        Ver1 = new javax.swing.JLabel();
        No_Ver1 = new javax.swing.JLabel();
        lblMail = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlMail = new javax.swing.JPanel();
        txtMail = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        pnlTelefono = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        lblAsterisco1 = new javax.swing.JLabel();
        lblAsterisco2 = new javax.swing.JLabel();
        lblAsterisco3 = new javax.swing.JLabel();
        lblAsterisco4 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        lblTipo = new javax.swing.JLabel();
        lblAsterisco6 = new javax.swing.JLabel();
        lblPrimerNombre = new javax.swing.JLabel();
        lblAsterisco9 = new javax.swing.JLabel();
        jspnlModificar_Usuarios = new javax.swing.JScrollPane();
        pnlModificar_Usuarios = new javax.swing.JPanel();
        lblModificarUser = new javax.swing.JLabel();
        lblname2 = new javax.swing.JLabel();
        lblAsterisco5 = new javax.swing.JLabel();
        pnlBuscarIDModificar = new javax.swing.JPanel();
        txtBuscarIDModificar = new javax.swing.JTextField();
        lblPrimerNombre1 = new javax.swing.JLabel();
        btnBuscarID = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuariosModificar = new javax.swing.JTable();
        btnModificarUser = new javax.swing.JButton();
        lblPrimerNombre4 = new javax.swing.JLabel();
        lblAsterisco15 = new javax.swing.JLabel();
        lblPass1 = new javax.swing.JLabel();
        lblAsterisco19 = new javax.swing.JLabel();
        pnlID1 = new javax.swing.JPanel();
        txtIDUsuarioModificar = new javax.swing.JTextField();
        pnlContraseña1 = new javax.swing.JPanel();
        txtContraseñaUserModificar = new javax.swing.JTextField();
        pnlConfirmar1 = new javax.swing.JPanel();
        txtConfirmarUserModficar = new javax.swing.JTextField();
        lblConfirmar1 = new javax.swing.JLabel();
        lblAsterisco20 = new javax.swing.JLabel();
        lblMail1 = new javax.swing.JLabel();
        lblAsterisco21 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        pnlMail1 = new javax.swing.JPanel();
        txtMailModificar = new javax.swing.JTextField();
        lblAsterisco22 = new javax.swing.JLabel();
        pnlTelefono1 = new javax.swing.JPanel();
        txtTelefonoModificar = new javax.swing.JTextField();
        lblVeces = new javax.swing.JLabel();
        cbTipo1 = new javax.swing.JComboBox<>();
        lblAsterisco23 = new javax.swing.JLabel();
        lblTipo1 = new javax.swing.JLabel();
        lblNombreDeUsuario2 = new javax.swing.JLabel();
        lblname6 = new javax.swing.JLabel();
        lblAsterisco24 = new javax.swing.JLabel();
        pnlNombreDeUsuario1 = new javax.swing.JPanel();
        txtNombreDeModificar = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        lblQR = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lblTipo2 = new javax.swing.JLabel();
        pnlEliminar_Usuarios = new javax.swing.JPanel();
        lblEliminarUser = new javax.swing.JLabel();
        lblAsterisco7 = new javax.swing.JLabel();
        lblname3 = new javax.swing.JLabel();
        btnEliminarUser = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuariosEliminar = new javax.swing.JTable();
        pnlBuscarIDEliminar = new javax.swing.JPanel();
        txtBuscarIDEliminar = new javax.swing.JTextField();
        lblPrimerNombre2 = new javax.swing.JLabel();
        lblPrimerNombre9 = new javax.swing.JLabel();
        lblQR1 = new javax.swing.JLabel();
        lblnombre1 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        pnlConsulta_Usuarios = new javax.swing.JPanel();
        lblConsultaUser = new javax.swing.JLabel();
        lblname4 = new javax.swing.JLabel();
        lblAsterisco8 = new javax.swing.JLabel();
        pnlBuscarIDConsulta = new javax.swing.JPanel();
        txtBuscarIDConsulta = new javax.swing.JTextField();
        lblPrimerNombre3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsuariosConsulta = new javax.swing.JTable();
        cbTipoConsulta = new javax.swing.JComboBox<>();
        chbPorNombreUsuario = new javax.swing.JCheckBox();
        chbPorId = new javax.swing.JCheckBox();
        chbPorIdConcepto = new javax.swing.JCheckBox();
        lblPrimerNombre5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblQR2 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblnombre2 = new javax.swing.JLabel();
        lblPrimerNombre10 = new javax.swing.JLabel();
        pnlIngreso_UsuariosTipo = new javax.swing.JPanel();
        lblIngresoUserTipo = new javax.swing.JLabel();
        lblname5 = new javax.swing.JLabel();
        lblNombreDeUsuario1 = new javax.swing.JLabel();
        pnlIDTipoUsuario = new javax.swing.JPanel();
        txtIDTipoUsuario = new javax.swing.JTextField();
        lblPermisos = new javax.swing.JLabel();
        btnRegistrarTipo = new javax.swing.JButton();
        lblAsterisco10 = new javax.swing.JLabel();
        lblAsterisco11 = new javax.swing.JLabel();
        chbPermisoModificarConcepto = new javax.swing.JCheckBox();
        chbPermisoModificarEmpleado = new javax.swing.JCheckBox();
        chbPermisoModificarDep = new javax.swing.JCheckBox();
        chbPermisoNomina = new javax.swing.JCheckBox();
        chbPermisoIngresoDep = new javax.swing.JCheckBox();
        chbPermisoIngresoConcepto = new javax.swing.JCheckBox();
        chbPermisoEliminarEmpleado = new javax.swing.JCheckBox();
        chbPermisoEliminarDep = new javax.swing.JCheckBox();
        chbPermisoEliminarConcepto = new javax.swing.JCheckBox();
        chbPermisoTabla = new javax.swing.JCheckBox();
        chbPermisoConsultaDep = new javax.swing.JCheckBox();
        chbPermisoConsultaConcepto = new javax.swing.JCheckBox();
        chbPermisoConsultaEmpleado = new javax.swing.JCheckBox();
        chbPermisoIngresoEmpleado = new javax.swing.JCheckBox();
        chbPermisoUsuario = new javax.swing.JCheckBox();
        chbPermisoIngresoUser = new javax.swing.JCheckBox();
        chbPermisoModificarUser = new javax.swing.JCheckBox();
        chbPermisoEliminarUser = new javax.swing.JCheckBox();
        chbPermisoConsultarUser = new javax.swing.JCheckBox();
        chbPermisoIngresoUserTipo = new javax.swing.JCheckBox();
        chbPermisoModificarUserTipo = new javax.swing.JCheckBox();
        chbPermisoEliminarUserTipo = new javax.swing.JCheckBox();
        chbPermisoConsultarUserTipo = new javax.swing.JCheckBox();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        lblname = new javax.swing.JLabel();
        lblAsterisco12 = new javax.swing.JLabel();
        pnlNombreDeUsuario2 = new javax.swing.JPanel();
        txtNombreDeTipoUsuario = new javax.swing.JTextField();
        chbPermisoPastel = new javax.swing.JCheckBox();
        chbPermisoBarras = new javax.swing.JCheckBox();
        jspModificar_UsuariosTipo = new javax.swing.JScrollPane();
        pnlModificar_UsuariosTipo = new javax.swing.JPanel();
        lblModificarUserTipo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblUsuariosModificarTipo = new javax.swing.JTable();
        btnModificarUserTipo = new javax.swing.JButton();
        pnlIDTipoUsuario1 = new javax.swing.JPanel();
        txtIDTipoUsuarioModificar = new javax.swing.JTextField();
        pnlNombreDeUsuario3 = new javax.swing.JPanel();
        txtNombreDeTipoUsuarioModificar = new javax.swing.JTextField();
        lblname9 = new javax.swing.JLabel();
        lblAsterisco13 = new javax.swing.JLabel();
        lblname10 = new javax.swing.JLabel();
        lblAsterisco14 = new javax.swing.JLabel();
        lblAsterisco18 = new javax.swing.JLabel();
        chbPermisoIngresoUser1 = new javax.swing.JCheckBox();
        jSeparator8 = new javax.swing.JSeparator();
        chbPermisoModificarUser1 = new javax.swing.JCheckBox();
        chbPermisoModificarUserTipo1 = new javax.swing.JCheckBox();
        chbPermisoIngresoUserTipo1 = new javax.swing.JCheckBox();
        chbPermisoEliminarUserTipo1 = new javax.swing.JCheckBox();
        chbPermisoEliminarUser1 = new javax.swing.JCheckBox();
        chbPermisoConsultarUser1 = new javax.swing.JCheckBox();
        chbPermisoConsultarUserTipo1 = new javax.swing.JCheckBox();
        jSeparator9 = new javax.swing.JSeparator();
        chbPermisoIngresoEmpleado1 = new javax.swing.JCheckBox();
        chbPermisoModificarEmpleado1 = new javax.swing.JCheckBox();
        chbPermisoEliminarEmpleado1 = new javax.swing.JCheckBox();
        chbPermisoConsultaEmpleado1 = new javax.swing.JCheckBox();
        chbPermisoTabla1 = new javax.swing.JCheckBox();
        chbPermisoConsultaDep1 = new javax.swing.JCheckBox();
        chbPermisoEliminarDep1 = new javax.swing.JCheckBox();
        chbPermisoModificarDep1 = new javax.swing.JCheckBox();
        chbPermisoIngresoDep1 = new javax.swing.JCheckBox();
        chbPermisoIngresoConcepto1 = new javax.swing.JCheckBox();
        chbPermisoModificarConcepto1 = new javax.swing.JCheckBox();
        chbPermisoEliminarConcepto1 = new javax.swing.JCheckBox();
        chbPermisoConsultaConcepto1 = new javax.swing.JCheckBox();
        lblname11 = new javax.swing.JLabel();
        lblPermisos2 = new javax.swing.JLabel();
        pnlBuscarIDEliminar2 = new javax.swing.JPanel();
        txtBuscarIDModificarTipo = new javax.swing.JTextField();
        btnBuscarIDEModificarTipo = new javax.swing.JButton();
        lblPermisos3 = new javax.swing.JLabel();
        lblPermisos4 = new javax.swing.JLabel();
        chbPermisoBarras1 = new javax.swing.JCheckBox();
        chbPermisoPastel1 = new javax.swing.JCheckBox();
        pnlEliminar_UsuariosTipo = new javax.swing.JPanel();
        lblEliminarUserTipo = new javax.swing.JLabel();
        lblAsterisco16 = new javax.swing.JLabel();
        lblname7 = new javax.swing.JLabel();
        btnEliminarUserTipo = new javax.swing.JButton();
        pnlBuscarIDEliminar1 = new javax.swing.JPanel();
        txtBuscarIDEliminarTipo = new javax.swing.JTextField();
        lblPrimerNombre6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblUsuariosEliminarTipo = new javax.swing.JTable();
        lblPrimerNombre8 = new javax.swing.JLabel();
        pnlConsulta_UsuariosTipo = new javax.swing.JPanel();
        lblConsultaUserTipo = new javax.swing.JLabel();
        lblname8 = new javax.swing.JLabel();
        lblAsterisco17 = new javax.swing.JLabel();
        pnlBuscarIDConsulta1 = new javax.swing.JPanel();
        txtBuscarIDConsultaTipo = new javax.swing.JTextField();
        lblPrimerNombre7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTipodeUsuarioConsulta = new javax.swing.JTable();
        pnlCuerpoNomina = new javax.swing.JPanel();
        pnlInicio = new javax.swing.JPanel();
        lblLogo1 = new javax.swing.JLabel();
        pnlEmpleados = new javax.swing.JPanel();
        pnlIngresoEmpleado = new javax.swing.JPanel();
        lblIngreso_NominaEmpleado = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_Sueldo_Empleado = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_BuscarE = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txt_Nombre_Empleado1 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txt_Apellido_Empleado1 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txt_DPI_Empleado1 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txt_Telefono_Empleado1 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txt_ID_Empleado = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jButton_InsertarE = new javax.swing.JButton();
        jButton_ModificarE = new javax.swing.JButton();
        jButton_EliminarE = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        jButton_BuscarE = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        cbEstado = new javax.swing.JComboBox<>();
        cbDepartamento = new javax.swing.JComboBox<>();
        cbPuesto = new javax.swing.JComboBox<>();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        pnlModificarEmpleado = new javax.swing.JPanel();
        lblModificar_NominaEmpleado = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel70 = new javax.swing.JLabel();
        txt_Sueldo_Empleado1 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        txt_Nombre_Empleado2 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txt_Apellido_Empleado2 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txt_DPI_Empleado2 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txt_Telefono_Empleado2 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        txt_Estado_Empleado2 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txt_ID_Empleado1 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        txt_IDP_Empleado2 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        txt_IDD_Empleado2 = new javax.swing.JTextField();
        txt_IDU_Empleado2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        pnlEliminarEmpleado = new javax.swing.JPanel();
        lblEliminar_NominaEmpleado = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pnlConsultaEmpleado = new javax.swing.JPanel();
        lblConsulta_NominaEmpleado = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        pnlPuestos = new javax.swing.JPanel();
        pnlIngresoPuesto = new javax.swing.JPanel();
        lblIngreso_NominaEmpleado1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_idpuesto = new javax.swing.JTextField();
        txt_nombrepuesto = new javax.swing.JTextField();
        btn_ingresarpuesto = new javax.swing.JButton();
        btn_modificarpuesto = new javax.swing.JButton();
        btn_eliminarpuesto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_buscarpuesto = new javax.swing.JTextField();
        btn_buscarpuesto = new javax.swing.JButton();
        label_statuspuesto = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblPuestos = new javax.swing.JTable();
        cbEstadoP = new javax.swing.JComboBox<>();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        pnlModificarPuesto = new javax.swing.JPanel();
        lblModificar_NominaEmpleado1 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        txt_idpuesto1 = new javax.swing.JTextField();
        txt_nombrepuesto1 = new javax.swing.JTextField();
        txt_estadopuesto1 = new javax.swing.JTextField();
        btn_ingresarpuesto1 = new javax.swing.JButton();
        btn_modificarpuesto1 = new javax.swing.JButton();
        btn_eliminarpuesto1 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        txt_buscarpuesto1 = new javax.swing.JTextField();
        btn_buscarpuesto1 = new javax.swing.JButton();
        pnlEliminarPuesto = new javax.swing.JPanel();
        lblEliminar_NominaEmpleado1 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        pnlConsultaPuesto = new javax.swing.JPanel();
        lblConsulta_NominaEmpleado1 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        pnlDepartamentos = new javax.swing.JPanel();
        pnlIngresoDep = new javax.swing.JPanel();
        lblIngreso_NominaEmpleado2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_iddep = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_nombredep = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_ingresardep = new javax.swing.JButton();
        btn_modificardep = new javax.swing.JButton();
        btn_eliminardep = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btn_buscardep = new javax.swing.JButton();
        txt_buscardep = new javax.swing.JTextField();
        label_status = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblDep = new javax.swing.JTable();
        cbEstadoD = new javax.swing.JComboBox<>();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        pnlModificarDep = new javax.swing.JPanel();
        lblModificar_NominaEmpleado2 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        pnlEliminarDep = new javax.swing.JPanel();
        lblEliminar_NominaEmpleado2 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel91 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        pnlConsultaDep = new javax.swing.JPanel();
        lblConsulta_NominaEmpleado2 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel92 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        pnlConceptos = new javax.swing.JPanel();
        pnlIngresoConcepto = new javax.swing.JPanel();
        lblIngreso_NominaEmpleado3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_Buscar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_Nombre_Concepto_Planilla = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_Valor_Concepto_Planilla = new javax.swing.JTextField();
        txt_ID_Concepto_Planilla = new javax.swing.JTextField();
        jButton_Ingresar = new javax.swing.JButton();
        jButton_Modificar = new javax.swing.JButton();
        jButton_Eliminar = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jButton_Buscar = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        tblConceptos = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        cbTipoConcepto = new javax.swing.JComboBox<>();
        cbClase = new javax.swing.JComboBox<>();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        pnlModificarConcepto = new javax.swing.JPanel();
        lblModificar_NominaEmpleado3 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        txt_Nombre_Concepto_Planilla1 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txt_Tipo_Concepto_Planilla1 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txt_Clase_Concepto_Planilla1 = new javax.swing.JTextField();
        txt_Valor_Concepto_Planilla1 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        txt_ID_Concepto_Planilla1 = new javax.swing.JTextField();
        txt_IDU_Concepto_Planilla1 = new javax.swing.JTextField();
        pnlEliminarConcepto = new javax.swing.JPanel();
        lblEliminar_NominaEmpleado3 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel100 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        pnlConsultaConcepto = new javax.swing.JPanel();
        lblConsulta_NominaEmpleado3 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        pnlTabla = new javax.swing.JPanel();
        lblTabla_Nomina = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblPlanillaGen = new javax.swing.JTable();
        txt_IDEmpleadoPlanilla = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jButton_InsertarE2 = new javax.swing.JButton();
        txt_IDConceptoPlanilla = new javax.swing.JTextField();
        txtBuscarTabla = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblPlanillaDet = new javax.swing.JTable();
        btnEliminarTabla = new javax.swing.JButton();
        btnModificarTabla1 = new javax.swing.JButton();
        btnBuscarTabla = new javax.swing.JButton();
        txtValorConcepto = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        cbPercepcion = new javax.swing.JCheckBox();
        cbDeduccion = new javax.swing.JCheckBox();
        cbISR = new javax.swing.JCheckBox();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        cbIDConcepto = new javax.swing.JComboBox<>();
        cbIDEmpleado = new javax.swing.JComboBox<>();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();

        jLabel32.setText("jLabel32");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel_Plataforma.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Plataforma.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel_PlataformaMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel_PlataformaMouseMoved(evt);
            }
        });
        jPanel_Plataforma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_PlataformaMousePressed(evt);
            }
        });
        jPanel_Plataforma.setLayout(new java.awt.BorderLayout());

        pnlizquierdo.setBackground(new java.awt.Color(12, 12, 12));
        pnlizquierdo.setPreferredSize(new java.awt.Dimension(160, 0));
        pnlizquierdo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLogo.setBackground(new java.awt.Color(12, 12, 12));
        pnlLogo.setPreferredSize(new java.awt.Dimension(70, 80));
        pnlLogo.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(30, 30, 30));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/UMG.png"))); // NOI18N
        jLabel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel2MouseMoved(evt);
            }
        });
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });
        pnlLogo.add(jLabel2, java.awt.BorderLayout.CENTER);

        pnlizquierdo.add(pnlLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 5, 90, 90));

        btnHome.setBackground(new java.awt.Color(12, 12, 12));
        btnHome.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnHome.setForeground(new java.awt.Color(204, 204, 204));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/home.png"))); // NOI18N
        btnHome.setText(" Home");
        btnHome.setPreferredSize(new java.awt.Dimension(40, 40));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 160, -1));

        btnAjustes.setBackground(new java.awt.Color(12, 12, 12));
        btnAjustes.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnAjustes.setForeground(new java.awt.Color(204, 204, 204));
        btnAjustes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/settings.png"))); // NOI18N
        btnAjustes.setText("Ajustes");
        btnAjustes.setPreferredSize(new java.awt.Dimension(40, 40));
        btnAjustes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btnAjustesMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnAjustesMouseMoved(evt);
            }
        });
        btnAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustesActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnAjustes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 160, -1));

        btnCuenta.setBackground(new java.awt.Color(12, 12, 12));
        btnCuenta.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnCuenta.setForeground(new java.awt.Color(204, 204, 204));
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/user.png"))); // NOI18N
        btnCuenta.setText("Cuenta");
        btnCuenta.setPreferredSize(new java.awt.Dimension(40, 40));
        btnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentaActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 160, -1));

        btnGrafica.setBackground(new java.awt.Color(12, 12, 12));
        btnGrafica.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnGrafica.setForeground(new java.awt.Color(204, 204, 204));
        btnGrafica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Grafica.png"))); // NOI18N
        btnGrafica.setText("Grafica");
        btnGrafica.setPreferredSize(new java.awt.Dimension(40, 40));
        btnGrafica.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnGraficaMouseMoved(evt);
            }
        });
        btnGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficaActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, -1));

        btnCalculadora.setBackground(new java.awt.Color(12, 12, 12));
        btnCalculadora.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnCalculadora.setForeground(new java.awt.Color(204, 204, 204));
        btnCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/calc.png"))); // NOI18N
        btnCalculadora.setText("Calcula");
        btnCalculadora.setPreferredSize(new java.awt.Dimension(40, 40));
        btnCalculadora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCalculadoraMouseClicked(evt);
            }
        });
        btnCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculadoraActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));

        btnPagina.setBackground(new java.awt.Color(12, 12, 12));
        btnPagina.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnPagina.setForeground(new java.awt.Color(204, 204, 204));
        btnPagina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Pagina.png"))); // NOI18N
        btnPagina.setText("Pagina");
        btnPagina.setPreferredSize(new java.awt.Dimension(40, 40));
        btnPagina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPaginaMouseClicked(evt);
            }
        });
        btnPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaginaActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));

        btnCerrar.setBackground(new java.awt.Color(12, 12, 12));
        btnCerrar.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(204, 204, 204));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Exit.png"))); // NOI18N
        btnCerrar.setText("Cerrar ");
        btnCerrar.setPreferredSize(new java.awt.Dimension(40, 40));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));

        pnlSubOpcionTema.setBackground(new java.awt.Color(38, 38, 38));
        pnlSubOpcionTema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSubOpcionTemaMouseClicked(evt);
            }
        });
        pnlSubOpcionTema.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTemas.setBackground(new java.awt.Color(32, 32, 32));
        btnTemas.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnTemas.setForeground(new java.awt.Color(204, 204, 204));
        btnTemas.setText("Tema");
        btnTemas.setBorder(null);
        btnTemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTemasMouseExited(evt);
            }
        });
        btnTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemasActionPerformed(evt);
            }
        });
        pnlSubOpcionTema.add(btnTemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 40));

        pnlizquierdo.add(pnlSubOpcionTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, 40));

        pnlGraph.setBackground(new java.awt.Color(12, 12, 12));
        pnlGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlGraphMouseExited(evt);
            }
        });
        pnlGraph.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSubOpcionGrafica.setBackground(new java.awt.Color(38, 38, 38));
        pnlSubOpcionGrafica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSubOpcionGraficaMouseClicked(evt);
            }
        });
        pnlSubOpcionGrafica.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBarras.setBackground(new java.awt.Color(32, 32, 32));
        btnBarras.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnBarras.setForeground(new java.awt.Color(204, 204, 204));
        btnBarras.setText("Barras");
        btnBarras.setBorder(null);
        btnBarras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBarrasMouseExited(evt);
            }
        });
        btnBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarrasActionPerformed(evt);
            }
        });
        pnlSubOpcionGrafica.add(btnBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 40));

        pnlGraph.add(pnlSubOpcionGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        pnlSubOpcionGrafica2.setBackground(new java.awt.Color(38, 38, 38));
        pnlSubOpcionGrafica2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSubOpcionGrafica2MouseClicked(evt);
            }
        });
        pnlSubOpcionGrafica2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPastel.setBackground(new java.awt.Color(32, 32, 32));
        btnPastel.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        btnPastel.setForeground(new java.awt.Color(204, 204, 204));
        btnPastel.setText("Pastel");
        btnPastel.setBorder(null);
        btnPastel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPastelMouseExited(evt);
            }
        });
        btnPastel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPastelActionPerformed(evt);
            }
        });
        pnlSubOpcionGrafica2.add(btnPastel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 40));

        pnlGraph.add(pnlSubOpcionGrafica2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, 40));

        pnlizquierdo.add(pnlGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, 90));

        jPanel_Plataforma.add(pnlizquierdo, java.awt.BorderLayout.WEST);

        pnlCentro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCentro.setPreferredSize(new java.awt.Dimension(700, 508));
        pnlCentro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlEncabezados.setBackground(new java.awt.Color(35, 35, 35));
        pnlEncabezados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIinicio.setBackground(new java.awt.Color(0, 0, 0));
        lblIinicio.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblIinicio.setForeground(new java.awt.Color(0, 204, 204));
        lblIinicio.setText("INICIO");
        lblIinicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIinicioMouseClicked(evt);
            }
        });
        pnlEncabezados.add(lblIinicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 10, 40, -1));

        lblUsuarios.setBackground(new java.awt.Color(153, 153, 255));
        lblUsuarios.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblUsuarios.setForeground(new java.awt.Color(204, 204, 204));
        lblUsuarios.setText("USUARIOS");
        lblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsuariosMouseClicked(evt);
            }
        });
        pnlEncabezados.add(lblUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        lblNomina.setBackground(new java.awt.Color(0, 0, 0));
        lblNomina.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblNomina.setForeground(new java.awt.Color(204, 204, 204));
        lblNomina.setText("NOMINA DE EMPLEADOS");
        lblNomina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNominaMouseClicked(evt);
            }
        });
        pnlEncabezados.add(lblNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        pnlOpciones_ventana.setBackground(new java.awt.Color(35, 35, 35));

        btnMinimize.setBackground(new java.awt.Color(35, 35, 35));
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

        btnMaximize.setBackground(new java.awt.Color(35, 35, 35));
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

        btnExit.setBackground(new java.awt.Color(35, 35, 35));
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

        pnlEncabezados.add(pnlOpciones_ventana, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, -1, -1));

        pnlTemas.setBackground(new java.awt.Color(35, 35, 35));
        pnlTemas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOscuro.setBackground(new java.awt.Color(35, 35, 35));
        lblOscuro.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblOscuro.setForeground(new java.awt.Color(204, 204, 204));
        lblOscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Dark.png"))); // NOI18N
        lblOscuro.setText("DARK");
        lblOscuro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOscuroMouseClicked(evt);
            }
        });
        pnlTemas.add(lblOscuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        lblClaro.setBackground(new java.awt.Color(35, 35, 35));
        lblClaro.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblClaro.setForeground(new java.awt.Color(204, 204, 204));
        lblClaro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Ligth.png"))); // NOI18N
        lblClaro.setText("LIGTH");
        lblClaro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblClaroMouseClicked(evt);
            }
        });
        pnlTemas.add(lblClaro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pnlEncabezados.add(pnlTemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 70, 20));

        lbl_reloj.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        lbl_reloj.setForeground(new java.awt.Color(255, 255, 255));
        lbl_reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_reloj.setText("Reloj");
        pnlEncabezados.add(lbl_reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 7, 140, 20));

        lblNomina1.setBackground(new java.awt.Color(0, 0, 0));
        lblNomina1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        lblNomina1.setForeground(new java.awt.Color(204, 204, 204));
        lblNomina1.setText("CRONOGRAMA DE ACTIVIDADES");
        lblNomina1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNomina1MouseClicked(evt);
            }
        });
        pnlEncabezados.add(lblNomina1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        pnlCentro.add(pnlEncabezados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 40));

        pnlMenu_barUser.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenu_barUser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlMenu_barUser.add(lbllogouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 60, 80));
        pnlMenu_barUser.add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 80));

        pnlCentro.add(pnlMenu_barUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1090, 80));

        pnlContenido.setBackground(new java.awt.Color(102, 102, 102));

        pnlMenuInicio.setBackground(new java.awt.Color(222, 222, 222));
        pnlMenuInicio.setPreferredSize(new java.awt.Dimension(1100, 40));
        pnlMenuInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsernameInicio.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblUsernameInicio.setForeground(new java.awt.Color(57, 80, 103));
        lblUsernameInicio.setText("Profile details");
        pnlMenuInicio.add(lblUsernameInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(127, 140, 141));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Usted inicia sesión como administrador,");
        pnlMenuInicio.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 275, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(52, 152, 219));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Cerrar sesión ?");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel20MousePressed(evt);
            }
        });
        pnlMenuInicio.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, -1, -1));

        pnlDetalles.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("Sesiones");
        pnlDetalles.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        lblMAC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblMAC.setForeground(new java.awt.Color(141, 141, 141));
        lblMAC.setText("0");
        pnlDetalles.add(lblMAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 100, -1));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("Tipo de Usuario");
        pnlDetalles.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblTipodeUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTipodeUser.setForeground(new java.awt.Color(141, 141, 141));
        lblTipodeUser.setText("Adminostrador");
        pnlDetalles.add(lblTipodeUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, -1));

        lblnameBitacora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblnameBitacora.setForeground(new java.awt.Color(141, 141, 141));
        lblnameBitacora.setText("Jose");
        pnlDetalles.add(lblnameBitacora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(204, 204, 204));
        jLabel36.setText("Id:");
        pnlDetalles.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        lblIdeIncio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIdeIncio.setForeground(new java.awt.Color(141, 141, 141));
        lblIdeIncio.setText("1");
        pnlDetalles.add(lblIdeIncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 30, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 204, 204));
        jLabel38.setText("Nombre de Usuario");
        pnlDetalles.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(141, 141, 141));
        jLabel39.setText("Datos Personales");
        pnlDetalles.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(204, 204, 204));
        jLabel44.setText("Direccion IP y MAC:");
        pnlDetalles.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        lblIP.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIP.setForeground(new java.awt.Color(141, 141, 141));
        lblIP.setText("127.0.0196");
        pnlDetalles.add(lblIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 130, -1));

        pnlMenuInicio.add(pnlDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 420, 120));

        pnlContacto.setBackground(new java.awt.Color(255, 255, 255));
        pnlContacto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(141, 141, 141));
        jLabel34.setText("Contacto");
        pnlContacto.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(204, 204, 204));
        jLabel40.setText("Correo Electronico:");
        pnlContacto.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblCorreo.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(141, 141, 141));
        lblCorreo.setText("Adminostrador@gmai.com");
        pnlContacto.add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setText("Telefono:");
        pnlContacto.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblTel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTel.setForeground(new java.awt.Color(141, 141, 141));
        lblTel.setText("4159-6960");
        pnlContacto.add(lblTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, -1));

        cbVertical.setBackground(new java.awt.Color(255, 255, 255));
        cbVertical.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbVertical.setForeground(new java.awt.Color(141, 141, 141));
        cbVertical.setText("Grafica Vertical");
        cbVertical.setBorder(null);
        cbVertical.setFocusable(false);
        cbVertical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVerticalActionPerformed(evt);
            }
        });
        pnlContacto.add(cbVertical, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        cbHorizontal.setBackground(new java.awt.Color(255, 255, 255));
        cbHorizontal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbHorizontal.setForeground(new java.awt.Color(141, 141, 141));
        cbHorizontal.setText("Grafica Horizontal");
        cbHorizontal.setBorder(null);
        cbHorizontal.setFocusable(false);
        cbHorizontal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHorizontalActionPerformed(evt);
            }
        });
        pnlContacto.add(cbHorizontal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 67, 120, -1));

        cbLineal.setBackground(new java.awt.Color(255, 255, 255));
        cbLineal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbLineal.setForeground(new java.awt.Color(141, 141, 141));
        cbLineal.setSelected(true);
        cbLineal.setText("Grafica Lineal");
        cbLineal.setBorder(null);
        cbLineal.setFocusable(false);
        cbLineal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLinealActionPerformed(evt);
            }
        });
        pnlContacto.add(cbLineal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 100, -1));

        cbPastel.setBackground(new java.awt.Color(255, 255, 255));
        cbPastel.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cbPastel.setForeground(new java.awt.Color(141, 141, 141));
        cbPastel.setText("Grafica Pastel");
        cbPastel.setBorder(null);
        cbPastel.setFocusable(false);
        cbPastel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPastelActionPerformed(evt);
            }
        });
        pnlContacto.add(cbPastel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 95, 100, -1));
        pnlContacto.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 220, 100));

        pnlMenuInicio.add(pnlContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 600, 120));

        pnlMenuUsuarios.setBackground(new java.awt.Color(222, 222, 222));
        pnlMenuUsuarios.setPreferredSize(new java.awt.Dimension(1100, 40));
        pnlMenuUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCatalogos.setBackground(new java.awt.Color(46, 46, 46));
        btnCatalogos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCatalogos.setForeground(new java.awt.Color(204, 204, 204));
        btnCatalogos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Catalogos.png"))); // NOI18N
        btnCatalogos.setText("Catalogos");
        btnCatalogos.setBorder(null);
        btnCatalogos.setBorderPainted(false);
        btnCatalogos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCatalogos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCatalogos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCatalogosMouseMoved(evt);
            }
        });
        btnCatalogos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCatalogosMouseClicked(evt);
            }
        });
        btnCatalogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogosActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnCatalogos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 20));

        btnProcesos.setBackground(new java.awt.Color(46, 46, 46));
        btnProcesos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnProcesos.setForeground(new java.awt.Color(204, 204, 204));
        btnProcesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Procesos.png"))); // NOI18N
        btnProcesos.setText("Procesos");
        btnProcesos.setBorder(null);
        btnProcesos.setBorderPainted(false);
        btnProcesos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProcesos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnProcesos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnProcesosMouseMoved(evt);
            }
        });
        btnProcesos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProcesosMouseClicked(evt);
            }
        });
        btnProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesosActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnProcesos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 120, 20));

        btnHelp.setBackground(new java.awt.Color(46, 46, 46));
        btnHelp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHelp.setForeground(new java.awt.Color(204, 204, 204));
        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Ayuda.png"))); // NOI18N
        btnHelp.setText("Ayuda");
        btnHelp.setBorder(null);
        btnHelp.setBorderPainted(false);
        btnHelp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHelp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHelp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnHelpMouseMoved(evt);
            }
        });
        btnHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHelpMouseClicked(evt);
            }
        });
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 120, 20));

        btnInformes.setBackground(new java.awt.Color(46, 46, 46));
        btnInformes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInformes.setForeground(new java.awt.Color(204, 204, 204));
        btnInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/informes.png"))); // NOI18N
        btnInformes.setText("Informes");
        btnInformes.setBorder(null);
        btnInformes.setBorderPainted(false);
        btnInformes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInformes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInformes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnInformesMouseMoved(evt);
            }
        });
        btnInformes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInformesMouseClicked(evt);
            }
        });
        btnInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformesActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnInformes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 120, 20));

        btnHerramientas.setBackground(new java.awt.Color(46, 46, 46));
        btnHerramientas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHerramientas.setForeground(new java.awt.Color(204, 204, 204));
        btnHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Herramientas.png"))); // NOI18N
        btnHerramientas.setText("Herramientas");
        btnHerramientas.setBorder(null);
        btnHerramientas.setBorderPainted(false);
        btnHerramientas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHerramientas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHerramientas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnHerramientasMouseMoved(evt);
            }
        });
        btnHerramientas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHerramientasMouseClicked(evt);
            }
        });
        btnHerramientas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHerramientasActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 140, 20));

        jPanel1.setBackground(new java.awt.Color(46, 46, 46));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pnlMenuUsuarios.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 470, 20));

        btnInicioUsuarios.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnInicioUsuarios.setForeground(new java.awt.Color(54, 76, 98));
        btnInicioUsuarios.setText("USUARIOS");
        btnInicioUsuarios.setContentAreaFilled(false);
        btnInicioUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicioUsuarios.setFocusable(false);
        btnInicioUsuarios.setPreferredSize(new java.awt.Dimension(100, 27));
        btnInicioUsuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnInicioUsuariosMouseMoved(evt);
            }
        });
        btnInicioUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioUsuariosMouseExited(evt);
            }
        });
        btnInicioUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioUsuariosActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnInicioUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 145, 125, 29));

        lblIngresarUser.setBackground(new java.awt.Color(102, 153, 255));
        lblIngresarUser.setOpaque(true);
        pnlMenuUsuarios.add(lblIngresarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 171, 125, 8));

        btnTipodeUsuario.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnTipodeUsuario.setForeground(new java.awt.Color(54, 76, 98));
        btnTipodeUsuario.setText("TIPO DE USUARIO");
        btnTipodeUsuario.setContentAreaFilled(false);
        btnTipodeUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTipodeUsuario.setFocusable(false);
        btnTipodeUsuario.setPreferredSize(new java.awt.Dimension(100, 27));
        btnTipodeUsuario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnTipodeUsuarioMouseMoved(evt);
            }
        });
        btnTipodeUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTipodeUsuarioMouseExited(evt);
            }
        });
        btnTipodeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipodeUsuarioActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnTipodeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 145, 170, 29));

        lbltipodeUsuario.setBackground(new java.awt.Color(222, 222, 222));
        lbltipodeUsuario.setOpaque(true);
        pnlMenuUsuarios.add(lbltipodeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 171, 125, 8));

        btnMantenimientoUsuarios.setBackground(new java.awt.Color(46, 46, 46));
        btnMantenimientoUsuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMantenimientoUsuarios.setForeground(new java.awt.Color(204, 204, 204));
        btnMantenimientoUsuarios.setText("Mantemiento Usuarios");
        btnMantenimientoUsuarios.setBorder(null);
        btnMantenimientoUsuarios.setBorderPainted(false);
        btnMantenimientoUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMantenimientoUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMantenimientoUsuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMantenimientoUsuariosMouseMoved(evt);
            }
        });
        btnMantenimientoUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMantenimientoUsuariosMouseClicked(evt);
            }
        });
        btnMantenimientoUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoUsuariosActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnMantenimientoUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 170, 20));

        pnlOpciones.setBackground(new java.awt.Color(102, 153, 255));
        pnlOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOpcionesMouseExited(evt);
            }
        });

        btnOpsion_Consultar.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_Consultar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_Consultar.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_Consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/ConsultarUser.png"))); // NOI18N
        btnOpsion_Consultar.setText(" Consultar");
        btnOpsion_Consultar.setBorder(null);
        btnOpsion_Consultar.setBorderPainted(false);
        btnOpsion_Consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_Consultar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_Consultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultarMouseClicked(evt);
            }
        });
        btnOpsion_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ConsultarActionPerformed(evt);
            }
        });

        btnOpsion_Ingresar.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_Ingresar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_Ingresar.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_Ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/IngresarUser.png"))); // NOI18N
        btnOpsion_Ingresar.setText(" Ingresar Usuario");
        btnOpsion_Ingresar.setBorder(null);
        btnOpsion_Ingresar.setBorderPainted(false);
        btnOpsion_Ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_Ingresar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_Ingresar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarMouseMoved(evt);
            }
        });
        btnOpsion_Ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarMouseClicked(evt);
            }
        });
        btnOpsion_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_IngresarActionPerformed(evt);
            }
        });

        btnOpsion_Modificar.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_Modificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_Modificar.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/ModificarUser.png"))); // NOI18N
        btnOpsion_Modificar.setText(" Modificar Usuario");
        btnOpsion_Modificar.setBorder(null);
        btnOpsion_Modificar.setBorderPainted(false);
        btnOpsion_Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_Modificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_Modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarMouseClicked(evt);
            }
        });
        btnOpsion_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ModificarActionPerformed(evt);
            }
        });

        btnOpsion_Eliminar.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_Eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_Eliminar.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/EliminarUser.png"))); // NOI18N
        btnOpsion_Eliminar.setText(" Eliminar Usuario");
        btnOpsion_Eliminar.setBorder(null);
        btnOpsion_Eliminar.setBorderPainted(false);
        btnOpsion_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_Eliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarMouseClicked(evt);
            }
        });
        btnOpsion_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_EliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOpsion_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpsion_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpsion_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpsion_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                .addComponent(btnOpsion_Ingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpsion_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpsion_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpsion_Consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMenuUsuarios.add(pnlOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 210, -1));

        btnMantenimientoUsuarios1.setBackground(new java.awt.Color(14, 14, 14));
        btnMantenimientoUsuarios1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMantenimientoUsuarios1.setForeground(new java.awt.Color(204, 204, 204));
        btnMantenimientoUsuarios1.setText("Mantemiento Tipos");
        btnMantenimientoUsuarios1.setBorder(null);
        btnMantenimientoUsuarios1.setBorderPainted(false);
        btnMantenimientoUsuarios1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMantenimientoUsuarios1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMantenimientoUsuarios1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMantenimientoUsuarios1MouseMoved(evt);
            }
        });
        btnMantenimientoUsuarios1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMantenimientoUsuarios1MouseClicked(evt);
            }
        });
        btnMantenimientoUsuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMantenimientoUsuarios1ActionPerformed(evt);
            }
        });
        pnlMenuUsuarios.add(btnMantenimientoUsuarios1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 170, 20));

        pnlOpcionesTipo.setBackground(new java.awt.Color(0, 204, 204));
        pnlOpcionesTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOpcionesTipoMouseExited(evt);
            }
        });

        btnOpsion_ConsultarTipo.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_ConsultarTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ConsultarTipo.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ConsultarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/ConsultarUser.png"))); // NOI18N
        btnOpsion_ConsultarTipo.setText(" Consultar");
        btnOpsion_ConsultarTipo.setBorder(null);
        btnOpsion_ConsultarTipo.setBorderPainted(false);
        btnOpsion_ConsultarTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ConsultarTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ConsultarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultarTipoMouseClicked(evt);
            }
        });
        btnOpsion_ConsultarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ConsultarTipoActionPerformed(evt);
            }
        });

        btnOpsion_IngresarTipo.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_IngresarTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_IngresarTipo.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_IngresarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/IngresarUser.png"))); // NOI18N
        btnOpsion_IngresarTipo.setText(" Ingresar Tipo de Usuario");
        btnOpsion_IngresarTipo.setBorder(null);
        btnOpsion_IngresarTipo.setBorderPainted(false);
        btnOpsion_IngresarTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_IngresarTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_IngresarTipo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarTipoMouseMoved(evt);
            }
        });
        btnOpsion_IngresarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarTipoMouseClicked(evt);
            }
        });
        btnOpsion_IngresarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_IngresarTipoActionPerformed(evt);
            }
        });

        btnOpsion_ModificarTipo.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_ModificarTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ModificarTipo.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ModificarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/ModificarUser.png"))); // NOI18N
        btnOpsion_ModificarTipo.setText(" Modificar Tipo de Usuario");
        btnOpsion_ModificarTipo.setBorder(null);
        btnOpsion_ModificarTipo.setBorderPainted(false);
        btnOpsion_ModificarTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ModificarTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ModificarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarTipoMouseClicked(evt);
            }
        });
        btnOpsion_ModificarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ModificarTipoActionPerformed(evt);
            }
        });

        btnOpsion_EliminarTipo.setBackground(new java.awt.Color(36, 36, 36));
        btnOpsion_EliminarTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_EliminarTipo.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_EliminarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/EliminarUser.png"))); // NOI18N
        btnOpsion_EliminarTipo.setText(" Eliminar Tipo de Usuario");
        btnOpsion_EliminarTipo.setBorder(null);
        btnOpsion_EliminarTipo.setBorderPainted(false);
        btnOpsion_EliminarTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_EliminarTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_EliminarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarTipoMouseClicked(evt);
            }
        });
        btnOpsion_EliminarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_EliminarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOpcionesTipoLayout = new javax.swing.GroupLayout(pnlOpcionesTipo);
        pnlOpcionesTipo.setLayout(pnlOpcionesTipoLayout);
        pnlOpcionesTipoLayout.setHorizontalGroup(
            pnlOpcionesTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesTipoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlOpcionesTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOpsion_IngresarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpsion_ModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpsion_EliminarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpsion_ConsultarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlOpcionesTipoLayout.setVerticalGroup(
            pnlOpcionesTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesTipoLayout.createSequentialGroup()
                .addComponent(btnOpsion_IngresarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpsion_ModificarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpsion_EliminarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnOpsion_ConsultarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlMenuUsuarios.add(pnlOpcionesTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 210, -1));

        pnlDetalles1.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalles1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(204, 204, 204));
        jLabel30.setText("Sesiones");
        pnlDetalles1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        lblMAC1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblMAC1.setForeground(new java.awt.Color(141, 141, 141));
        lblMAC1.setText("0");
        pnlDetalles1.add(lblMAC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 100, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(204, 204, 204));
        jLabel33.setText("Tipo de Usuario");
        pnlDetalles1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblTipodeUser1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTipodeUser1.setForeground(new java.awt.Color(141, 141, 141));
        lblTipodeUser1.setText("Adminostrador");
        pnlDetalles1.add(lblTipodeUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, -1));

        lblnameBitacora1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblnameBitacora1.setForeground(new java.awt.Color(141, 141, 141));
        lblnameBitacora1.setText("Jose");
        pnlDetalles1.add(lblnameBitacora1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setText("Id:");
        pnlDetalles1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        lblIdeIncio1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIdeIncio1.setForeground(new java.awt.Color(141, 141, 141));
        lblIdeIncio1.setText("1");
        pnlDetalles1.add(lblIdeIncio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 30, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("Nombre de Usuario");
        pnlDetalles1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(141, 141, 141));
        jLabel43.setText("Datos Personales");
        pnlDetalles1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(204, 204, 204));
        jLabel45.setText("Direccion IP y MAC:");
        pnlDetalles1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        lblIP1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIP1.setForeground(new java.awt.Color(141, 141, 141));
        lblIP1.setText("127.0.0196");
        pnlDetalles1.add(lblIP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 130, -1));

        pnlMenuUsuarios.add(pnlDetalles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 26, 420, 120));
        pnlMenuUsuarios.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, -1, -1));

        pnlContacto1.setBackground(new java.awt.Color(255, 255, 255));
        pnlContacto1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(141, 141, 141));
        jLabel35.setText("Contacto");
        pnlContacto1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(204, 204, 204));
        jLabel47.setText("Correo Electronico:");
        pnlContacto1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblCorreo1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblCorreo1.setForeground(new java.awt.Color(141, 141, 141));
        lblCorreo1.setText("Adminostrador@gmai.com");
        pnlContacto1.add(lblCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(204, 204, 204));
        jLabel51.setText("Telefono:");
        pnlContacto1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblTel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTel1.setForeground(new java.awt.Color(141, 141, 141));
        lblTel1.setText("4159-6960");
        pnlContacto1.add(lblTel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(127, 140, 141));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Usted inicia sesión como administrador,");
        pnlContacto1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 5, 250, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(52, 152, 219));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Cerrar sesión ?");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });
        pnlContacto1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 5, -1, -1));
        pnlContacto1.add(jDayChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 210, 80));

        pnlMenuUsuarios.add(pnlContacto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 26, 600, 120));

        pnlMenu.setBackground(new java.awt.Color(222, 222, 222));
        pnlMenu.setPreferredSize(new java.awt.Dimension(1100, 40));
        pnlMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEmpleados.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnEmpleados.setForeground(new java.awt.Color(54, 76, 98));
        btnEmpleados.setText("PERSONAL DE TRABAJO");
        btnEmpleados.setContentAreaFilled(false);
        btnEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEmpleados.setFocusable(false);
        btnEmpleados.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnEmpleadosMouseMoved(evt);
            }
        });
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, 220, 30));

        lblEmpleados.setBackground(new java.awt.Color(88, 122, 255));
        lblEmpleados.setOpaque(true);
        pnlMenu.add(lblEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 200, 8));

        btnPuestos.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnPuestos.setForeground(new java.awt.Color(54, 76, 98));
        btnPuestos.setText("PUESTOS");
        btnPuestos.setContentAreaFilled(false);
        btnPuestos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPuestos.setFocusable(false);
        btnPuestos.setPreferredSize(new java.awt.Dimension(100, 27));
        btnPuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuestosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnPuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 145, 120, -1));

        lblConceptos.setBackground(new java.awt.Color(222, 222, 222));
        lblConceptos.setOpaque(true);
        lblConceptos.setPreferredSize(new java.awt.Dimension(100, 0));
        pnlMenu.add(lblConceptos, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 125, 8));

        btnDepartamentos.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnDepartamentos.setForeground(new java.awt.Color(54, 76, 98));
        btnDepartamentos.setText("DEPARTAMENTOS");
        btnDepartamentos.setContentAreaFilled(false);
        btnDepartamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnDepartamentos.setFocusable(false);
        btnDepartamentos.setPreferredSize(new java.awt.Dimension(100, 27));
        btnDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepartamentosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnDepartamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 145, 170, -1));

        lblPuestos.setBackground(new java.awt.Color(222, 222, 222));
        lblPuestos.setOpaque(true);
        lblPuestos.setPreferredSize(new java.awt.Dimension(100, 0));
        pnlMenu.add(lblPuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 170, 110, 10));

        btnConceptos.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnConceptos.setForeground(new java.awt.Color(54, 76, 98));
        btnConceptos.setText("CONCEPTOS");
        btnConceptos.setContentAreaFilled(false);
        btnConceptos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConceptos.setFocusable(false);
        btnConceptos.setPreferredSize(new java.awt.Dimension(100, 27));
        btnConceptos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConceptosActionPerformed(evt);
            }
        });
        pnlMenu.add(btnConceptos, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 145, 142, -1));

        lblDep.setBackground(new java.awt.Color(222, 222, 222));
        lblDep.setOpaque(true);
        lblDep.setPreferredSize(new java.awt.Dimension(100, 0));
        pnlMenu.add(lblDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 170, 160, 10));

        btnTabla.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        btnTabla.setForeground(new java.awt.Color(54, 76, 98));
        btnTabla.setText("TABLA / PLANILLA");
        btnTabla.setContentAreaFilled(false);
        btnTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTabla.setFocusable(false);
        btnTabla.setPreferredSize(new java.awt.Dimension(100, 27));
        btnTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTablaActionPerformed(evt);
            }
        });
        pnlMenu.add(btnTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 145, 170, -1));

        lblTabla.setBackground(new java.awt.Color(222, 222, 222));
        lblTabla.setOpaque(true);
        lblTabla.setPreferredSize(new java.awt.Dimension(100, 0));
        pnlMenu.add(lblTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 170, 160, 9));

        btnME.setBackground(new java.awt.Color(46, 46, 46));
        btnME.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnME.setForeground(new java.awt.Color(204, 204, 204));
        btnME.setText("Mantenimiento de P/E");
        btnME.setBorder(null);
        btnME.setBorderPainted(false);
        btnME.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnME.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnME.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnME.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMEMouseMoved(evt);
            }
        });
        btnME.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMEMouseClicked(evt);
            }
        });
        btnME.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMEActionPerformed(evt);
            }
        });

        btnMP.setBackground(new java.awt.Color(46, 46, 46));
        btnMP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMP.setForeground(new java.awt.Color(204, 204, 204));
        btnMP.setText("Mantenimiento Puestos");
        btnMP.setBorder(null);
        btnMP.setBorderPainted(false);
        btnMP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMPMouseMoved(evt);
            }
        });
        btnMP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMPMouseClicked(evt);
            }
        });
        btnMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMPActionPerformed(evt);
            }
        });

        btnMD.setBackground(new java.awt.Color(46, 46, 46));
        btnMD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMD.setForeground(new java.awt.Color(204, 204, 204));
        btnMD.setText("Mantenimientos Dep");
        btnMD.setBorder(null);
        btnMD.setBorderPainted(false);
        btnMD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMD.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMDMouseMoved(evt);
            }
        });
        btnMD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMDMouseClicked(evt);
            }
        });
        btnMD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCatalogoLayout = new javax.swing.GroupLayout(pnlCatalogo);
        pnlCatalogo.setLayout(pnlCatalogoLayout);
        pnlCatalogoLayout.setHorizontalGroup(
            pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                .addGroup(pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnME, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMP, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMD, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCatalogoLayout.setVerticalGroup(
            pnlCatalogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCatalogoLayout.createSequentialGroup()
                .addComponent(btnME, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnMP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnMD, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 190, 60));

        btnMC.setBackground(new java.awt.Color(46, 46, 46));
        btnMC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMC.setForeground(new java.awt.Color(204, 204, 204));
        btnMC.setText("Mantenimiento Conceptos");
        btnMC.setBorder(null);
        btnMC.setBorderPainted(false);
        btnMC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMC.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMCMouseMoved(evt);
            }
        });
        btnMC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMCMouseClicked(evt);
            }
        });
        btnMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMCActionPerformed(evt);
            }
        });

        btnMPP.setBackground(new java.awt.Color(46, 46, 46));
        btnMPP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMPP.setForeground(new java.awt.Color(204, 204, 204));
        btnMPP.setText("Mantenimientos Planilla");
        btnMPP.setBorder(null);
        btnMPP.setBorderPainted(false);
        btnMPP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMPP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMPP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMPP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnMPPMouseMoved(evt);
            }
        });
        btnMPP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMPPMouseClicked(evt);
            }
        });
        btnMPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMPPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProcesosLayout = new javax.swing.GroupLayout(pnlProcesos);
        pnlProcesos.setLayout(pnlProcesosLayout);
        pnlProcesosLayout.setHorizontalGroup(
            pnlProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProcesosLayout.createSequentialGroup()
                .addGroup(pnlProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMC, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMPP, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlProcesosLayout.setVerticalGroup(
            pnlProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProcesosLayout.createSequentialGroup()
                .addComponent(btnMC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnMPP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlProcesos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 190, 40));

        btnIP.setBackground(new java.awt.Color(46, 46, 46));
        btnIP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnIP.setForeground(new java.awt.Color(204, 204, 204));
        btnIP.setText("Informes de Planilla");
        btnIP.setBorder(null);
        btnIP.setBorderPainted(false);
        btnIP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnIP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnIP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIP.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnIPMouseMoved(evt);
            }
        });
        btnIP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIPMouseClicked(evt);
            }
        });
        btnIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInformesLayout = new javax.swing.GroupLayout(pnlInformes);
        pnlInformes.setLayout(pnlInformesLayout);
        pnlInformesLayout.setHorizontalGroup(
            pnlInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformesLayout.createSequentialGroup()
                .addComponent(btnIP, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlInformesLayout.setVerticalGroup(
            pnlInformesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformesLayout.createSequentialGroup()
                .addComponent(btnIP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlInformes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 190, 20));

        btnCalc.setBackground(new java.awt.Color(46, 46, 46));
        btnCalc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCalc.setForeground(new java.awt.Color(204, 204, 204));
        btnCalc.setText("Calculadora");
        btnCalc.setBorder(null);
        btnCalc.setBorderPainted(false);
        btnCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCalc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCalc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCalcMouseMoved(evt);
            }
        });
        btnCalc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCalcMouseClicked(evt);
            }
        });
        btnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHerramientasLayout = new javax.swing.GroupLayout(pnlHerramientas);
        pnlHerramientas.setLayout(pnlHerramientasLayout);
        pnlHerramientasLayout.setHorizontalGroup(
            pnlHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHerramientasLayout.createSequentialGroup()
                .addComponent(btnCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlHerramientasLayout.setVerticalGroup(
            pnlHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHerramientasLayout.createSequentialGroup()
                .addComponent(btnCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 190, 20));

        btnManual.setBackground(new java.awt.Color(46, 46, 46));
        btnManual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnManual.setForeground(new java.awt.Color(204, 204, 204));
        btnManual.setText("Manual");
        btnManual.setBorder(null);
        btnManual.setBorderPainted(false);
        btnManual.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnManual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnManual.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnManual.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnManualMouseMoved(evt);
            }
        });
        btnManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnManualMouseClicked(evt);
            }
        });
        btnManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAyudaLayout = new javax.swing.GroupLayout(pnlAyuda);
        pnlAyuda.setLayout(pnlAyudaLayout);
        pnlAyudaLayout.setHorizontalGroup(
            pnlAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAyudaLayout.createSequentialGroup()
                .addComponent(btnManual, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlAyudaLayout.setVerticalGroup(
            pnlAyudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAyudaLayout.createSequentialGroup()
                .addComponent(btnManual, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlMenu.add(pnlAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 190, 20));

        pnlOpciones_NominaConceptos.setBackground(new java.awt.Color(0, 204, 106));
        pnlOpciones_NominaConceptos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOpciones_NominaConceptosMouseExited(evt);
            }
        });
        pnlOpciones_NominaConceptos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOpsion_EliminarConcepto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_EliminarConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_EliminarConcepto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_EliminarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Conceptos.png"))); // NOI18N
        btnOpsion_EliminarConcepto.setText(" Eliminar Conceptos");
        btnOpsion_EliminarConcepto.setBorder(null);
        btnOpsion_EliminarConcepto.setBorderPainted(false);
        btnOpsion_EliminarConcepto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_EliminarConcepto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_EliminarConcepto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarConceptoMouseClicked(evt);
            }
        });
        btnOpsion_EliminarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_EliminarConceptoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaConceptos.add(btnOpsion_EliminarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 20));

        btnOpsion_IngresarConcepto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_IngresarConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_IngresarConcepto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_IngresarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Conceptos.png"))); // NOI18N
        btnOpsion_IngresarConcepto.setText("Mantenimientos Conceptos");
        btnOpsion_IngresarConcepto.setBorder(null);
        btnOpsion_IngresarConcepto.setBorderPainted(false);
        btnOpsion_IngresarConcepto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_IngresarConcepto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_IngresarConcepto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarConceptoMouseClicked(evt);
            }
        });
        btnOpsion_IngresarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_IngresarConceptoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaConceptos.add(btnOpsion_IngresarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 20));

        btnOpsion_ModificarConcepto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ModificarConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ModificarConcepto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ModificarConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Conceptos.png"))); // NOI18N
        btnOpsion_ModificarConcepto.setText(" Modificar Conceptos");
        btnOpsion_ModificarConcepto.setBorder(null);
        btnOpsion_ModificarConcepto.setBorderPainted(false);
        btnOpsion_ModificarConcepto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ModificarConcepto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ModificarConcepto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarConceptoMouseClicked(evt);
            }
        });
        btnOpsion_ModificarConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ModificarConceptoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaConceptos.add(btnOpsion_ModificarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 20));

        btnOpsion_ConsultaConcepto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ConsultaConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ConsultaConcepto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ConsultaConcepto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Conceptos.png"))); // NOI18N
        btnOpsion_ConsultaConcepto.setText(" Consulta Conceptos");
        btnOpsion_ConsultaConcepto.setBorder(null);
        btnOpsion_ConsultaConcepto.setBorderPainted(false);
        btnOpsion_ConsultaConcepto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ConsultaConcepto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ConsultaConcepto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultaConceptoMouseClicked(evt);
            }
        });
        btnOpsion_ConsultaConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ConsultaConceptoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaConceptos.add(btnOpsion_ConsultaConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 20));

        pnlMenu.add(pnlOpciones_NominaConceptos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 210, 80));

        pnlOpciones_NominaDepartamentos.setBackground(new java.awt.Color(0, 204, 106));
        pnlOpciones_NominaDepartamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOpciones_NominaDepartamentosMouseExited(evt);
            }
        });
        pnlOpciones_NominaDepartamentos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOpsion_IngresarDep.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_IngresarDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_IngresarDep.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_IngresarDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Departamentos.png"))); // NOI18N
        btnOpsion_IngresarDep.setText("Mantenimientos Departamentos");
        btnOpsion_IngresarDep.setBorder(null);
        btnOpsion_IngresarDep.setBorderPainted(false);
        btnOpsion_IngresarDep.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_IngresarDep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_IngresarDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarDepMouseClicked(evt);
            }
        });
        btnOpsion_IngresarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_IngresarDepActionPerformed(evt);
            }
        });
        pnlOpciones_NominaDepartamentos.add(btnOpsion_IngresarDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 20));

        btnOpsion_ModificarDep.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ModificarDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ModificarDep.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ModificarDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Departamentos.png"))); // NOI18N
        btnOpsion_ModificarDep.setText(" Modificar Departamentos");
        btnOpsion_ModificarDep.setBorder(null);
        btnOpsion_ModificarDep.setBorderPainted(false);
        btnOpsion_ModificarDep.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ModificarDep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ModificarDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarDepMouseClicked(evt);
            }
        });
        btnOpsion_ModificarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ModificarDepActionPerformed(evt);
            }
        });
        pnlOpciones_NominaDepartamentos.add(btnOpsion_ModificarDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 20));

        btnOpsion_EliminarDep.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_EliminarDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_EliminarDep.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_EliminarDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Departamentos.png"))); // NOI18N
        btnOpsion_EliminarDep.setText(" Eliminar Departamentos");
        btnOpsion_EliminarDep.setBorder(null);
        btnOpsion_EliminarDep.setBorderPainted(false);
        btnOpsion_EliminarDep.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_EliminarDep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_EliminarDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarDepMouseClicked(evt);
            }
        });
        btnOpsion_EliminarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_EliminarDepActionPerformed(evt);
            }
        });
        pnlOpciones_NominaDepartamentos.add(btnOpsion_EliminarDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 20));

        btnOpsion_ConsultaDep.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ConsultaDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ConsultaDep.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ConsultaDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Departamentos.png"))); // NOI18N
        btnOpsion_ConsultaDep.setText(" Consulta Departamentos");
        btnOpsion_ConsultaDep.setBorder(null);
        btnOpsion_ConsultaDep.setBorderPainted(false);
        btnOpsion_ConsultaDep.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ConsultaDep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ConsultaDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultaDepMouseClicked(evt);
            }
        });
        btnOpsion_ConsultaDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ConsultaDepActionPerformed(evt);
            }
        });
        pnlOpciones_NominaDepartamentos.add(btnOpsion_ConsultaDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 20));

        pnlMenu.add(pnlOpciones_NominaDepartamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 210, 80));

        pnlOpciones_NominaPuestos.setBackground(new java.awt.Color(0, 204, 106));
        pnlOpciones_NominaPuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOpciones_NominaPuestosMouseExited(evt);
            }
        });
        pnlOpciones_NominaPuestos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOpsion_IngresarPuesto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_IngresarPuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_IngresarPuesto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_IngresarPuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Puestos.png"))); // NOI18N
        btnOpsion_IngresarPuesto.setText("Mantenimientos Puesto");
        btnOpsion_IngresarPuesto.setBorder(null);
        btnOpsion_IngresarPuesto.setBorderPainted(false);
        btnOpsion_IngresarPuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_IngresarPuesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_IngresarPuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarPuestoMouseClicked(evt);
            }
        });
        btnOpsion_IngresarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_IngresarPuestoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaPuestos.add(btnOpsion_IngresarPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 20));

        btnOpsion_ModificarPuesto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ModificarPuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ModificarPuesto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ModificarPuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Puestos.png"))); // NOI18N
        btnOpsion_ModificarPuesto.setText(" Modificar Puesto");
        btnOpsion_ModificarPuesto.setBorder(null);
        btnOpsion_ModificarPuesto.setBorderPainted(false);
        btnOpsion_ModificarPuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ModificarPuesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ModificarPuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarPuestoMouseClicked(evt);
            }
        });
        btnOpsion_ModificarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ModificarPuestoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaPuestos.add(btnOpsion_ModificarPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 20));

        btnOpsion_EliminarPuesto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_EliminarPuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_EliminarPuesto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_EliminarPuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Puestos.png"))); // NOI18N
        btnOpsion_EliminarPuesto.setText(" Eliminar Puesto");
        btnOpsion_EliminarPuesto.setBorder(null);
        btnOpsion_EliminarPuesto.setBorderPainted(false);
        btnOpsion_EliminarPuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_EliminarPuesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_EliminarPuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarPuestoMouseClicked(evt);
            }
        });
        btnOpsion_EliminarPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_EliminarPuestoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaPuestos.add(btnOpsion_EliminarPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 20));

        btnOpsion_ConsultaPuesto.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ConsultaPuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ConsultaPuesto.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ConsultaPuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Puestos.png"))); // NOI18N
        btnOpsion_ConsultaPuesto.setText(" Consulta Puestos");
        btnOpsion_ConsultaPuesto.setBorder(null);
        btnOpsion_ConsultaPuesto.setBorderPainted(false);
        btnOpsion_ConsultaPuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ConsultaPuesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ConsultaPuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultaPuestoMouseClicked(evt);
            }
        });
        btnOpsion_ConsultaPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ConsultaPuestoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaPuestos.add(btnOpsion_ConsultaPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 20));

        pnlMenu.add(pnlOpciones_NominaPuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 210, 80));

        pnlOpciones_NominaEmpleados.setBackground(new java.awt.Color(0, 204, 106));
        pnlOpciones_NominaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOpciones_NominaEmpleadosMouseExited(evt);
            }
        });
        pnlOpciones_NominaEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOpsion_IngresarEmpleado.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_IngresarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_IngresarEmpleado.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_IngresarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Empleados.png"))); // NOI18N
        btnOpsion_IngresarEmpleado.setText("Mantenimientos Empleado");
        btnOpsion_IngresarEmpleado.setBorder(null);
        btnOpsion_IngresarEmpleado.setBorderPainted(false);
        btnOpsion_IngresarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_IngresarEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_IngresarEmpleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarEmpleadoMouseMoved(evt);
            }
        });
        btnOpsion_IngresarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_IngresarEmpleadoMouseClicked(evt);
            }
        });
        btnOpsion_IngresarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_IngresarEmpleadoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaEmpleados.add(btnOpsion_IngresarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 20));

        btnOpsion_ModificarEmpleado.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ModificarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ModificarEmpleado.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ModificarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Empleados.png"))); // NOI18N
        btnOpsion_ModificarEmpleado.setText(" Modificar Empleado");
        btnOpsion_ModificarEmpleado.setBorder(null);
        btnOpsion_ModificarEmpleado.setBorderPainted(false);
        btnOpsion_ModificarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ModificarEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ModificarEmpleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarEmpleadoMouseMoved(evt);
            }
        });
        btnOpsion_ModificarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ModificarEmpleadoMouseClicked(evt);
            }
        });
        btnOpsion_ModificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ModificarEmpleadoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaEmpleados.add(btnOpsion_ModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 20));

        btnOpsion_ConsultaEmpleado.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_ConsultaEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_ConsultaEmpleado.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_ConsultaEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Empleados.png"))); // NOI18N
        btnOpsion_ConsultaEmpleado.setText(" Consulta Empleado");
        btnOpsion_ConsultaEmpleado.setBorder(null);
        btnOpsion_ConsultaEmpleado.setBorderPainted(false);
        btnOpsion_ConsultaEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_ConsultaEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_ConsultaEmpleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultaEmpleadoMouseMoved(evt);
            }
        });
        btnOpsion_ConsultaEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_ConsultaEmpleadoMouseClicked(evt);
            }
        });
        btnOpsion_ConsultaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_ConsultaEmpleadoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaEmpleados.add(btnOpsion_ConsultaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 210, 20));

        btnOpsion_EliminarEmpleado.setBackground(new java.awt.Color(46, 46, 46));
        btnOpsion_EliminarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOpsion_EliminarEmpleado.setForeground(new java.awt.Color(204, 204, 204));
        btnOpsion_EliminarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Empleados.png"))); // NOI18N
        btnOpsion_EliminarEmpleado.setText(" Eliminar Empleado");
        btnOpsion_EliminarEmpleado.setBorder(null);
        btnOpsion_EliminarEmpleado.setBorderPainted(false);
        btnOpsion_EliminarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnOpsion_EliminarEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOpsion_EliminarEmpleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarEmpleadoMouseMoved(evt);
            }
        });
        btnOpsion_EliminarEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpsion_EliminarEmpleadoMouseClicked(evt);
            }
        });
        btnOpsion_EliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpsion_EliminarEmpleadoActionPerformed(evt);
            }
        });
        pnlOpciones_NominaEmpleados.add(btnOpsion_EliminarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 20));

        pnlMenu.add(pnlOpciones_NominaEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 210, 80));

        pnlDetalles2.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalles2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(204, 204, 204));
        jLabel52.setText("Sesiones");
        pnlDetalles2.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, -1, -1));

        lblMAC2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblMAC2.setForeground(new java.awt.Color(141, 141, 141));
        lblMAC2.setText("0");
        pnlDetalles2.add(lblMAC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 100, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(204, 204, 204));
        jLabel53.setText("Tipo de Usuario");
        pnlDetalles2.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        lblTipodeUser2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTipodeUser2.setForeground(new java.awt.Color(141, 141, 141));
        lblTipodeUser2.setText("Adminostrador");
        pnlDetalles2.add(lblTipodeUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, -1));

        lblnameBitacora2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblnameBitacora2.setForeground(new java.awt.Color(141, 141, 141));
        lblnameBitacora2.setText("Jose");
        pnlDetalles2.add(lblnameBitacora2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(204, 204, 204));
        jLabel54.setText("Id:");
        pnlDetalles2.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        lblIdeIncio2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIdeIncio2.setForeground(new java.awt.Color(141, 141, 141));
        lblIdeIncio2.setText("1");
        pnlDetalles2.add(lblIdeIncio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 30, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(204, 204, 204));
        jLabel55.setText("Nombre de Usuario");
        pnlDetalles2.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(141, 141, 141));
        jLabel56.setText("Datos Personales");
        pnlDetalles2.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(204, 204, 204));
        jLabel57.setText("Direccion IP y MAC:");
        pnlDetalles2.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        lblIP2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIP2.setForeground(new java.awt.Color(141, 141, 141));
        lblIP2.setText("127.0.0196");
        pnlDetalles2.add(lblIP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 130, -1));

        pnlMenu.add(pnlDetalles2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 26, 420, 120));

        pnlContacto2.setBackground(new java.awt.Color(255, 255, 255));
        pnlContacto2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(141, 141, 141));
        jLabel58.setText("Contacto");
        pnlContacto2.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(204, 204, 204));
        jLabel59.setText("Correo Electronico:");
        pnlContacto2.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblCorreo2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblCorreo2.setForeground(new java.awt.Color(141, 141, 141));
        lblCorreo2.setText("Adminostrador@gmai.com");
        pnlContacto2.add(lblCorreo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, -1));

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(204, 204, 204));
        jLabel60.setText("Telefono:");
        pnlContacto2.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblTel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblTel2.setForeground(new java.awt.Color(141, 141, 141));
        lblTel2.setText("4159-6960");
        pnlContacto2.add(lblTel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(127, 140, 141));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Usted inicia sesión como administrador,");
        pnlContacto2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 5, 260, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(52, 152, 219));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Cerrar sesión ?");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });
        pnlContacto2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 5, -1, -1));
        pnlContacto2.add(jDayChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 210, 80));

        pnlMenu.add(pnlContacto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 26, 600, 120));

        jPanel2.setBackground(new java.awt.Color(46, 46, 46));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pnlMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 470, 20));

        btnHelp1.setBackground(new java.awt.Color(46, 46, 46));
        btnHelp1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHelp1.setForeground(new java.awt.Color(204, 204, 204));
        btnHelp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Ayuda.png"))); // NOI18N
        btnHelp1.setText("Ayuda");
        btnHelp1.setBorder(null);
        btnHelp1.setBorderPainted(false);
        btnHelp1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHelp1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHelp1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnHelp1MouseMoved(evt);
            }
        });
        btnHelp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHelp1MouseClicked(evt);
            }
        });
        btnHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btnHelp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 120, 20));

        btnHerramientas1.setBackground(new java.awt.Color(46, 46, 46));
        btnHerramientas1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnHerramientas1.setForeground(new java.awt.Color(204, 204, 204));
        btnHerramientas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Herramientas.png"))); // NOI18N
        btnHerramientas1.setText("Herramientas");
        btnHerramientas1.setBorder(null);
        btnHerramientas1.setBorderPainted(false);
        btnHerramientas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnHerramientas1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHerramientas1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnHerramientas1MouseMoved(evt);
            }
        });
        btnHerramientas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHerramientas1MouseClicked(evt);
            }
        });
        btnHerramientas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHerramientas1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btnHerramientas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 140, 20));

        btnInformes1.setBackground(new java.awt.Color(46, 46, 46));
        btnInformes1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInformes1.setForeground(new java.awt.Color(204, 204, 204));
        btnInformes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/informes.png"))); // NOI18N
        btnInformes1.setText("Informes");
        btnInformes1.setBorder(null);
        btnInformes1.setBorderPainted(false);
        btnInformes1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInformes1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInformes1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnInformes1MouseMoved(evt);
            }
        });
        btnInformes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInformes1MouseClicked(evt);
            }
        });
        btnInformes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformes1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btnInformes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 120, 20));

        btnProcesos1.setBackground(new java.awt.Color(46, 46, 46));
        btnProcesos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnProcesos1.setForeground(new java.awt.Color(204, 204, 204));
        btnProcesos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Procesos.png"))); // NOI18N
        btnProcesos1.setText("Procesos");
        btnProcesos1.setBorder(null);
        btnProcesos1.setBorderPainted(false);
        btnProcesos1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProcesos1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnProcesos1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnProcesos1MouseMoved(evt);
            }
        });
        btnProcesos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProcesos1MouseClicked(evt);
            }
        });
        btnProcesos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesos1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btnProcesos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 120, 20));

        btnCatalogos1.setBackground(new java.awt.Color(46, 46, 46));
        btnCatalogos1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCatalogos1.setForeground(new java.awt.Color(204, 204, 204));
        btnCatalogos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Catalogos.png"))); // NOI18N
        btnCatalogos1.setText("Catalogos");
        btnCatalogos1.setBorder(null);
        btnCatalogos1.setBorderPainted(false);
        btnCatalogos1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCatalogos1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCatalogos1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCatalogos1MouseMoved(evt);
            }
        });
        btnCatalogos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCatalogos1MouseClicked(evt);
            }
        });
        btnCatalogos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogos1ActionPerformed(evt);
            }
        });
        pnlMenu.add(btnCatalogos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 20));

        pnlIInicio.setLayout(new java.awt.CardLayout());

        pnlBienvenida.setBackground(new java.awt.Color(255, 255, 255));
        pnlBienvenida.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlBienvenida.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlBienvenida.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlIInicio.add(pnlBienvenida, "card4");

        pnlCuerpoUsuarios.setLayout(new java.awt.CardLayout());

        pnlInicio1.setBackground(new java.awt.Color(255, 255, 255));
        pnlInicio1.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlInicio1.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlInicio1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo2.png"))); // NOI18N
        lblLogo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblLogoMouseMoved(evt);
            }
        });
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogoMouseExited(evt);
            }
        });
        pnlInicio1.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, -1));

        pnlCuerpoUsuarios.add(pnlInicio1, "card4");

        pnlIngreso_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngreso_Usuarios.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlIngreso_Usuarios.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlIngreso_Usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngresoUser.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblIngresoUser.setForeground(new java.awt.Color(44, 62, 80));
        lblIngresoUser.setText("INGRESO DE USUARIOS");
        pnlIngreso_Usuarios.add(lblIngresoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblname1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname1.setForeground(new java.awt.Color(153, 153, 153));
        lblname1.setText("Nombre");
        pnlIngreso_Usuarios.add(lblname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        lblAsterisco.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 320, 10, -1));

        lblNombreDeUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombreDeUsuario.setForeground(new java.awt.Color(153, 153, 153));
        lblNombreDeUsuario.setText("Nombre de Usuario");
        pnlIngreso_Usuarios.add(lblNombreDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, -1));

        pnlNombreDeUsuario.setBackground(new java.awt.Color(231, 231, 231));
        pnlNombreDeUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreDeUsuario.setBackground(new java.awt.Color(231, 231, 231));
        txtNombreDeUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreDeUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreDeUsuario.setBorder(null);
        pnlNombreDeUsuario.add(txtNombreDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlIngreso_Usuarios.add(pnlNombreDeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 260, 40));

        pnlID.setBackground(new java.awt.Color(231, 231, 231));
        pnlID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDUsuario.setBackground(new java.awt.Color(231, 231, 231));
        txtIDUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtIDUsuario.setBorder(null);
        pnlID.add(txtIDUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlIngreso_Usuarios.add(pnlID, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 260, 40));

        lblPass.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPass.setForeground(new java.awt.Color(153, 153, 153));
        lblPass.setText("Contraseña");
        pnlIngreso_Usuarios.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        pnlContraseña.setBackground(new java.awt.Color(231, 231, 231));
        pnlContraseña.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtContraseñaUser.setBackground(new java.awt.Color(231, 231, 231));
        txtContraseñaUser.setBorder(null);
        txtContraseñaUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaUserKeyReleased(evt);
            }
        });
        pnlContraseña.add(txtContraseñaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 40));

        Ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/Visible.png"))); // NOI18N
        Ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerMouseClicked(evt);
            }
        });
        pnlContraseña.add(Ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, 20));

        No_Ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/No_Visible.png"))); // NOI18N
        No_Ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                No_VerMouseClicked(evt);
            }
        });
        pnlContraseña.add(No_Ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 20, 20));

        pnlIngreso_Usuarios.add(pnlContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 260, 40));

        lblConfirmar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblConfirmar.setForeground(new java.awt.Color(153, 153, 153));
        lblConfirmar.setText("Confirme Contraseña");
        pnlIngreso_Usuarios.add(lblConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        pnlConfirmar.setBackground(new java.awt.Color(231, 231, 231));
        pnlConfirmar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtConfirmarUser.setBackground(new java.awt.Color(231, 231, 231));
        txtConfirmarUser.setBorder(null);
        txtConfirmarUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmarUserKeyReleased(evt);
            }
        });
        pnlConfirmar.add(txtConfirmarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 40));

        Ver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/Visible.png"))); // NOI18N
        Ver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ver1MouseClicked(evt);
            }
        });
        pnlConfirmar.add(Ver1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, 20));

        No_Ver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login/No_Visible.png"))); // NOI18N
        No_Ver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                No_Ver1MouseClicked(evt);
            }
        });
        pnlConfirmar.add(No_Ver1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 20, 20));

        pnlIngreso_Usuarios.add(pnlConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 260, 40));

        lblMail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMail.setForeground(new java.awt.Color(153, 153, 153));
        lblMail.setText("Correo Electronico");
        pnlIngreso_Usuarios.add(lblMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("Telefono");
        pnlIngreso_Usuarios.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        pnlMail.setBackground(new java.awt.Color(231, 231, 231));
        pnlMail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMail.setBackground(new java.awt.Color(231, 231, 231));
        txtMail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMail.setForeground(new java.awt.Color(153, 153, 153));
        txtMail.setBorder(null);
        pnlMail.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 580, 40));

        pnlIngreso_Usuarios.add(pnlMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 590, 40));

        btnRegistrar.setBackground(new java.awt.Color(247, 88, 88));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Registrar.png"))); // NOI18N
        btnRegistrar.setBorder(null);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setEnabled(false);
        btnRegistrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseMoved(evt);
            }
        });
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseExited(evt);
            }
        });
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlIngreso_Usuarios.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 110, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setText("Gmail");
        pnlIngreso_Usuarios.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, -1, -1));

        pnlTelefono.setBackground(new java.awt.Color(231, 231, 231));
        pnlTelefono.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono.setBackground(new java.awt.Color(231, 231, 231));
        txtTelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(153, 153, 153));
        txtTelefono.setBorder(null);
        pnlTelefono.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 320, 40));

        pnlIngreso_Usuarios.add(pnlTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 330, 40));

        lblAsterisco1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco1.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco1.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco1, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 50, 10, -1));

        lblAsterisco2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco2.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco2.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 10, -1));

        lblAsterisco3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco3.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco3.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 10, -1));

        lblAsterisco4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco4.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco4.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco4, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 280, 10, -1));

        cbTipo.setBorder(null);
        cbTipo.setFocusable(false);
        pnlIngreso_Usuarios.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 190, -1));

        lblTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(153, 153, 153));
        lblTipo.setText("ID Tipo de Usuario");
        pnlIngreso_Usuarios.add(lblTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        lblAsterisco6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco6.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco6.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 10, -1));

        lblPrimerNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre.setText("ID");
        pnlIngreso_Usuarios.add(lblPrimerNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        lblAsterisco9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco9.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco9.setText("*");
        pnlIngreso_Usuarios.add(lblAsterisco9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, 10, -1));

<<<<<<< HEAD
        txtConfirmarUser2.setBackground(new java.awt.Color(231, 231, 231));
        txtConfirmarUser2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtConfirmarUser2.setForeground(new java.awt.Color(153, 153, 153));
        txtConfirmarUser2.setBorder(null);
        txtConfirmarUser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmarUser2KeyReleased(evt);
            }
        });
        pnlIngreso_Usuarios.add(txtConfirmarUser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 250, 40));

=======
>>>>>>> ec692187cadd910ccf59b3a1bffc09200ebac154
        pnlCuerpoUsuarios.add(pnlIngreso_Usuarios, "card4");

        jspnlModificar_Usuarios.setBorder(null);
        jspnlModificar_Usuarios.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jspnlModificar_Usuarios.setMinimumSize(new java.awt.Dimension(17, 10));
        jspnlModificar_Usuarios.setPreferredSize(new java.awt.Dimension(1090, 40));

        pnlModificar_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificar_Usuarios.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlModificar_Usuarios.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlModificar_Usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificarUser.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblModificarUser.setForeground(new java.awt.Color(44, 62, 80));
        lblModificarUser.setText("MODIFICAR  USUARIOS");
        pnlModificar_Usuarios.add(lblModificarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblname2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname2.setForeground(new java.awt.Color(153, 153, 153));
        lblname2.setText("ID");
        pnlModificar_Usuarios.add(lblname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 20, -1));

        lblAsterisco5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco5.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco5.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco5, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 60, 10, -1));

        pnlBuscarIDModificar.setBackground(new java.awt.Color(231, 231, 231));
        pnlBuscarIDModificar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarIDModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarIDModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarIDModificar.setBorder(null);
        txtBuscarIDModificar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarIDModificarCaretUpdate(evt);
            }
        });
        txtBuscarIDModificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarIDModificarKeyTyped(evt);
            }
        });
        pnlBuscarIDModificar.add(txtBuscarIDModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, 40));

        pnlModificar_Usuarios.add(pnlBuscarIDModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 530, 40));

        lblPrimerNombre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre1.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre1.setText("ID de la persona");
        pnlModificar_Usuarios.add(lblPrimerNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 100, -1));

        btnBuscarID.setBackground(new java.awt.Color(0, 204, 102));
        btnBuscarID.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        btnBuscarID.setBorder(null);
        btnBuscarID.setBorderPainted(false);
        btnBuscarID.setContentAreaFilled(false);
        btnBuscarID.setFocusable(false);
        btnBuscarID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIDActionPerformed(evt);
            }
        });
        pnlModificar_Usuarios.add(btnBuscarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 55, 110, 30));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tblUsuariosModificar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowindex, int colindex){
                return false;
            }
        };
        tblUsuariosModificar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblUsuariosModificar.setForeground(new java.awt.Color(51, 51, 51));
        tblUsuariosModificar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        tblUsuariosModificar.setGridColor(new java.awt.Color(255, 255, 255));
        tblUsuariosModificar.setRowHeight(20);
        tblUsuariosModificar.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane1.setViewportView(tblUsuariosModificar);

        pnlModificar_Usuarios.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 800, 210));

        btnModificarUser.setBackground(new java.awt.Color(0, 204, 102));
        btnModificarUser.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Modificar.png"))); // NOI18N
        btnModificarUser.setBorder(null);
        btnModificarUser.setBorderPainted(false);
        btnModificarUser.setContentAreaFilled(false);
        btnModificarUser.setEnabled(false);
        btnModificarUser.setFocusable(false);
        btnModificarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUserActionPerformed(evt);
            }
        });
        pnlModificar_Usuarios.add(btnModificarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 55, 110, 30));

        lblPrimerNombre4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre4.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre4.setText("ID");
        pnlModificar_Usuarios.add(lblPrimerNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        lblAsterisco15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco15.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco15.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 10, -1));

        lblPass1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPass1.setForeground(new java.awt.Color(153, 153, 153));
        lblPass1.setText("Contraseña");
        pnlModificar_Usuarios.add(lblPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        lblAsterisco19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco19.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco19.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 10, -1));

        pnlID1.setBackground(new java.awt.Color(231, 231, 231));
        pnlID1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDUsuarioModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtIDUsuarioModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDUsuarioModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtIDUsuarioModificar.setBorder(null);
        pnlID1.add(txtIDUsuarioModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_Usuarios.add(pnlID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 260, 40));

        pnlContraseña1.setBackground(new java.awt.Color(231, 231, 231));
        pnlContraseña1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtContraseñaUserModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtContraseñaUserModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtContraseñaUserModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtContraseñaUserModificar.setBorder(null);
        txtContraseñaUserModificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContraseñaUserModificarKeyReleased(evt);
            }
        });
        pnlContraseña1.add(txtContraseñaUserModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_Usuarios.add(pnlContraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 260, 40));

        pnlConfirmar1.setBackground(new java.awt.Color(231, 231, 231));
        pnlConfirmar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtConfirmarUserModficar.setBackground(new java.awt.Color(231, 231, 231));
        txtConfirmarUserModficar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtConfirmarUserModficar.setForeground(new java.awt.Color(153, 153, 153));
        txtConfirmarUserModficar.setBorder(null);
        txtConfirmarUserModficar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmarUserModficarKeyReleased(evt);
            }
        });
        pnlConfirmar1.add(txtConfirmarUserModficar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_Usuarios.add(pnlConfirmar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 260, 40));

        lblConfirmar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblConfirmar1.setForeground(new java.awt.Color(153, 153, 153));
        lblConfirmar1.setText("Confirme Contraseña");
        pnlModificar_Usuarios.add(lblConfirmar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        lblAsterisco20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco20.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco20.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 10, -1));

        lblMail1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMail1.setForeground(new java.awt.Color(153, 153, 153));
        lblMail1.setText("Correo Electronico");
        pnlModificar_Usuarios.add(lblMail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        lblAsterisco21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco21.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco21.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 10, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(153, 153, 153));
        jLabel46.setText("Gmail");
        pnlModificar_Usuarios.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        pnlMail1.setBackground(new java.awt.Color(231, 231, 231));
        pnlMail1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMailModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtMailModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMailModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtMailModificar.setBorder(null);
        pnlMail1.add(txtMailModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 590, 40));

        pnlModificar_Usuarios.add(pnlMail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 600, 40));

        lblAsterisco22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco22.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco22.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 10, -1));

        pnlTelefono1.setBackground(new java.awt.Color(231, 231, 231));
        pnlTelefono1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefonoModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtTelefonoModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTelefonoModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtTelefonoModificar.setBorder(null);
        pnlTelefono1.add(txtTelefonoModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_Usuarios.add(pnlTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 260, 40));

        lblVeces.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVeces.setForeground(new java.awt.Color(255, 255, 255));
        pnlModificar_Usuarios.add(lblVeces, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 380, 120, 20));

        cbTipo1.setBorder(null);
        cbTipo1.setFocusable(false);
        pnlModificar_Usuarios.add(cbTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 230, 190, -1));

        lblAsterisco23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco23.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco23.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco23, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 10, -1));

        lblTipo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTipo1.setForeground(new java.awt.Color(153, 153, 153));
        lblTipo1.setText("QR");
        pnlModificar_Usuarios.add(lblTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 120, -1, -1));

        lblNombreDeUsuario2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombreDeUsuario2.setForeground(new java.awt.Color(153, 153, 153));
        lblNombreDeUsuario2.setText("Nombre de Usuario");
        pnlModificar_Usuarios.add(lblNombreDeUsuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, -1, -1));

        lblname6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname6.setForeground(new java.awt.Color(153, 153, 153));
        lblname6.setText("Nombre");
        pnlModificar_Usuarios.add(lblname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        lblAsterisco24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco24.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco24.setText("*");
        pnlModificar_Usuarios.add(lblAsterisco24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 10, -1));

        pnlNombreDeUsuario1.setBackground(new java.awt.Color(231, 231, 231));
        pnlNombreDeUsuario1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreDeModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtNombreDeModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreDeModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreDeModificar.setBorder(null);
        pnlNombreDeUsuario1.add(txtNombreDeModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_Usuarios.add(pnlNombreDeUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 260, 40));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 153));
        jLabel48.setText("Telefono");
        pnlModificar_Usuarios.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, -1, -1));

        lblQR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pnlModificar_Usuarios.add(lblQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 140, 110, 110));

        lblnombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(153, 153, 153));
        lblnombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlModificar_Usuarios.add(lblnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 260, 110, 20));

        lblTipo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTipo2.setForeground(new java.awt.Color(153, 153, 153));
        lblTipo2.setText("ID Tipo de Usuario");
        pnlModificar_Usuarios.add(lblTipo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        jspnlModificar_Usuarios.setViewportView(pnlModificar_Usuarios);

        pnlCuerpoUsuarios.add(jspnlModificar_Usuarios, "card2");

        pnlEliminar_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminar_Usuarios.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlEliminar_Usuarios.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlEliminar_Usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminarUser.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblEliminarUser.setForeground(new java.awt.Color(44, 62, 80));
        lblEliminarUser.setText("ELIMINAR USUARIO");
        pnlEliminar_Usuarios.add(lblEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblAsterisco7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco7.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco7.setText("*");
        pnlEliminar_Usuarios.add(lblAsterisco7, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 60, 10, -1));

        lblname3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname3.setForeground(new java.awt.Color(153, 153, 153));
        lblname3.setText("ID");
        pnlEliminar_Usuarios.add(lblname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 20, -1));

        btnEliminarUser.setBackground(new java.awt.Color(0, 204, 102));
        btnEliminarUser.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Eliminar.png"))); // NOI18N
        btnEliminarUser.setBorder(null);
        btnEliminarUser.setBorderPainted(false);
        btnEliminarUser.setContentAreaFilled(false);
        btnEliminarUser.setEnabled(false);
        btnEliminarUser.setFocusable(false);
        btnEliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserActionPerformed(evt);
            }
        });
        pnlEliminar_Usuarios.add(btnEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 110, 30));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        tblUsuariosEliminar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowindex, int colindex){
                return false;
            }
        };
        tblUsuariosEliminar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblUsuariosEliminar.setForeground(new java.awt.Color(51, 51, 51));
        tblUsuariosEliminar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        tblUsuariosEliminar.setGridColor(new java.awt.Color(255, 255, 255));
        tblUsuariosEliminar.setRowHeight(20);
        tblUsuariosEliminar.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane2.setViewportView(tblUsuariosEliminar);

        pnlEliminar_Usuarios.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 900, 230));

        pnlBuscarIDEliminar.setBackground(new java.awt.Color(231, 231, 231));
        pnlBuscarIDEliminar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarIDEliminar.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarIDEliminar.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarIDEliminar.setBorder(null);
        txtBuscarIDEliminar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarIDEliminarCaretUpdate(evt);
            }
        });
        txtBuscarIDEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarIDEliminarKeyTyped(evt);
            }
        });
        pnlBuscarIDEliminar.add(txtBuscarIDEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, 40));

        pnlEliminar_Usuarios.add(pnlBuscarIDEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 530, 40));

        lblPrimerNombre2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre2.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre2.setText("ID de la persona");
        pnlEliminar_Usuarios.add(lblPrimerNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 100, -1));

        lblPrimerNombre9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre9.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre9.setText("Asegurese que la casilla este seleccionada para eliminar");
        pnlEliminar_Usuarios.add(lblPrimerNombre9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 340, -1));

        lblQR1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pnlEliminar_Usuarios.add(lblQR1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, 110, 110));

        lblnombre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnombre1.setForeground(new java.awt.Color(153, 153, 153));
        lblnombre1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlEliminar_Usuarios.add(lblnombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 110, 20));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(153, 153, 153));
        jLabel49.setText("QR");
        pnlEliminar_Usuarios.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        pnlCuerpoUsuarios.add(pnlEliminar_Usuarios, "card4");

        pnlConsulta_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsulta_Usuarios.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlConsulta_Usuarios.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlConsulta_Usuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsultaUser.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblConsultaUser.setForeground(new java.awt.Color(44, 62, 80));
        lblConsultaUser.setText("CONSULTAS DE USUARIO");
        pnlConsulta_Usuarios.add(lblConsultaUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblname4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname4.setForeground(new java.awt.Color(153, 153, 153));
        lblname4.setText("ID");
        pnlConsulta_Usuarios.add(lblname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 20, -1));

        lblAsterisco8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco8.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco8.setText("*");
        pnlConsulta_Usuarios.add(lblAsterisco8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 10, -1));

        pnlBuscarIDConsulta.setBackground(new java.awt.Color(231, 231, 231));
        pnlBuscarIDConsulta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarIDConsulta.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDConsulta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarIDConsulta.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarIDConsulta.setBorder(null);
        txtBuscarIDConsulta.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarIDConsultaCaretUpdate(evt);
            }
        });
        txtBuscarIDConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarIDConsultaKeyTyped(evt);
            }
        });
        pnlBuscarIDConsulta.add(txtBuscarIDConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, 40));

        pnlConsulta_Usuarios.add(pnlBuscarIDConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 530, 40));

        lblPrimerNombre3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre3.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre3.setText("QR Solo por ID");
        pnlConsulta_Usuarios.add(lblPrimerNombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 100, -1));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);

        tblUsuariosConsulta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblUsuariosConsulta.setForeground(new java.awt.Color(51, 51, 51));
        tblUsuariosConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsuariosConsulta.setGridColor(new java.awt.Color(255, 255, 255));
        tblUsuariosConsulta.setRowHeight(20);
        tblUsuariosConsulta.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane3.setViewportView(tblUsuariosConsulta);

        pnlConsulta_Usuarios.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 870, 270));

        cbTipoConsulta.setBorder(null);
        cbTipoConsulta.setFocusable(false);
        cbTipoConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoConsultaActionPerformed(evt);
            }
        });
        pnlConsulta_Usuarios.add(cbTipoConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, 140, 20));

        chbPorNombreUsuario.setBackground(new java.awt.Color(255, 255, 255));
        chbPorNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPorNombreUsuario.setForeground(new java.awt.Color(153, 153, 153));
        chbPorNombreUsuario.setText("Buscar por Nombres");
        chbPorNombreUsuario.setFocusable(false);
        chbPorNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPorNombreUsuarioActionPerformed(evt);
            }
        });
        pnlConsulta_Usuarios.add(chbPorNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        chbPorId.setBackground(new java.awt.Color(255, 255, 255));
        chbPorId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPorId.setForeground(new java.awt.Color(153, 153, 153));
        chbPorId.setSelected(true);
        chbPorId.setText("Buscar por ID");
        chbPorId.setFocusable(false);
        chbPorId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPorIdActionPerformed(evt);
            }
        });
        pnlConsulta_Usuarios.add(chbPorId, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        chbPorIdConcepto.setBackground(new java.awt.Color(255, 255, 255));
        chbPorIdConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPorIdConcepto.setForeground(new java.awt.Color(153, 153, 153));
        chbPorIdConcepto.setText("Buscar por ID Tipo de Usuario");
        chbPorIdConcepto.setFocusable(false);
        chbPorIdConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPorIdConceptoActionPerformed(evt);
            }
        });
        pnlConsulta_Usuarios.add(chbPorIdConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        lblPrimerNombre5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre5.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre5.setText("Filltro Por Codigo de tipo de Usuario");
        pnlConsulta_Usuarios.add(lblPrimerNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 230, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/pdf.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlConsulta_Usuarios.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 110, 30));

        lblQR2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        pnlConsulta_Usuarios.add(lblQR2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 110, 110));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(153, 153, 153));
        jLabel50.setText("QR");
        pnlConsulta_Usuarios.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, -1));

        lblnombre2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblnombre2.setForeground(new java.awt.Color(153, 153, 153));
        lblnombre2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlConsulta_Usuarios.add(lblnombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 110, 20));

        lblPrimerNombre10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre10.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre10.setText("Por defecto (Nombres)");
        pnlConsulta_Usuarios.add(lblPrimerNombre10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 160, -1));

        pnlCuerpoUsuarios.add(pnlConsulta_Usuarios, "card4");

        pnlIngreso_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngreso_UsuariosTipo.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlIngreso_UsuariosTipo.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlIngreso_UsuariosTipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngresoUserTipo.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblIngresoUserTipo.setForeground(new java.awt.Color(44, 62, 80));
        lblIngresoUserTipo.setText("INGRESO TIPOS DE USUARIOS");
        pnlIngreso_UsuariosTipo.add(lblIngresoUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblname5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname5.setForeground(new java.awt.Color(153, 153, 153));
        lblname5.setText("ID");
        pnlIngreso_UsuariosTipo.add(lblname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 50, -1, -1));

        lblNombreDeUsuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombreDeUsuario1.setForeground(new java.awt.Color(153, 153, 153));
        lblNombreDeUsuario1.setText("Nombre de Usuario");
        pnlIngreso_UsuariosTipo.add(lblNombreDeUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        pnlIDTipoUsuario.setBackground(new java.awt.Color(231, 231, 231));
        pnlIDTipoUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDTipoUsuario.setBackground(new java.awt.Color(231, 231, 231));
        txtIDTipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDTipoUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtIDTipoUsuario.setBorder(null);
        txtIDTipoUsuario.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDTipoUsuarioCaretUpdate(evt);
            }
        });
        pnlIDTipoUsuario.add(txtIDTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlIngreso_UsuariosTipo.add(pnlIDTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 260, 40));

        lblPermisos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPermisos.setForeground(new java.awt.Color(153, 153, 153));
        lblPermisos.setText("Permisos");
        pnlIngreso_UsuariosTipo.add(lblPermisos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));

        btnRegistrarTipo.setBackground(new java.awt.Color(247, 88, 88));
        btnRegistrarTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRegistrarTipo.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Registrar.png"))); // NOI18N
        btnRegistrarTipo.setBorder(null);
        btnRegistrarTipo.setBorderPainted(false);
        btnRegistrarTipo.setContentAreaFilled(false);
        btnRegistrarTipo.setFocusable(false);
        btnRegistrarTipo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegistrarTipoMouseMoved(evt);
            }
        });
        btnRegistrarTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarTipoMouseExited(evt);
            }
        });
        btnRegistrarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarTipoActionPerformed(evt);
            }
        });
        pnlIngreso_UsuariosTipo.add(btnRegistrarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 110, 30));

        lblAsterisco10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco10.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco10.setText("*");
        pnlIngreso_UsuariosTipo.add(lblAsterisco10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 10, -1));

        lblAsterisco11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco11.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco11.setText("*");
        pnlIngreso_UsuariosTipo.add(lblAsterisco11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 10, -1));

        chbPermisoModificarConcepto.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarConcepto.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarConcepto.setText("Modificar Concepto");
        chbPermisoModificarConcepto.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoModificarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 160, -1));

        chbPermisoModificarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarEmpleado.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarEmpleado.setText("Modificar Personal");
        chbPermisoModificarEmpleado.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 160, -1));

        chbPermisoModificarDep.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarDep.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarDep.setText("Modificar Dep y Puesto");
        chbPermisoModificarDep.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoModificarDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 170, -1));

        chbPermisoNomina.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoNomina.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoNomina.setForeground(new java.awt.Color(102, 102, 102));
        chbPermisoNomina.setText("Permisos de Nomina");
        chbPermisoNomina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPermisoNominaActionPerformed(evt);
            }
        });
        pnlIngreso_UsuariosTipo.add(chbPermisoNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 160, -1));

        chbPermisoIngresoDep.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoDep.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoDep.setText("Mantenimiento de Dep y Puesto");
        chbPermisoIngresoDep.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoIngresoDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 240, -1));

        chbPermisoIngresoConcepto.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoConcepto.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoConcepto.setText("Mantenimiento de Concepto");
        chbPermisoIngresoConcepto.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoIngresoConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 220, -1));

        chbPermisoEliminarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarEmpleado.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarEmpleado.setText("Eliminar  Personal");
        chbPermisoEliminarEmpleado.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoEliminarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 280, 160, -1));

        chbPermisoEliminarDep.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarDep.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarDep.setText("Eliminar Dep y Puesto");
        chbPermisoEliminarDep.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoEliminarDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, 160, -1));

        chbPermisoEliminarConcepto.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarConcepto.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarConcepto.setText("Eliminar Concepto");
        chbPermisoEliminarConcepto.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoEliminarConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 160, -1));

        chbPermisoTabla.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoTabla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoTabla.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoTabla.setText("Mantenimiento Tabla");
        chbPermisoTabla.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, -1, -1));

        chbPermisoConsultaDep.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultaDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultaDep.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultaDep.setText("Consulta Dep y Puesto");
        chbPermisoConsultaDep.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoConsultaDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 310, 180, -1));

        chbPermisoConsultaConcepto.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultaConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultaConcepto.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultaConcepto.setText("Consulta Concepto");
        chbPermisoConsultaConcepto.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoConsultaConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 160, -1));

        chbPermisoConsultaEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultaEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultaEmpleado.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultaEmpleado.setText("Consulta Personal");
        chbPermisoConsultaEmpleado.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoConsultaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, 160, -1));

        chbPermisoIngresoEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoEmpleado.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoEmpleado.setText("Mantenimiento de Personal");
        chbPermisoIngresoEmpleado.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoIngresoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 220, -1));

        chbPermisoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoUsuario.setForeground(new java.awt.Color(102, 102, 102));
        chbPermisoUsuario.setText("Permisos de Usuario");
        chbPermisoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbPermisoUsuarioActionPerformed(evt);
            }
        });
        pnlIngreso_UsuariosTipo.add(chbPermisoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 160, -1));

        chbPermisoIngresoUser.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoUser.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoUser.setText("Ingreso de Usuario");
        chbPermisoIngresoUser.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoIngresoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 160, -1));

        chbPermisoModificarUser.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarUser.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarUser.setText("Modificar Usuario");
        chbPermisoModificarUser.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoModificarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 190, -1));

        chbPermisoEliminarUser.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarUser.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarUser.setText("Eliminar Usuario");
        chbPermisoEliminarUser.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 180, -1));

        chbPermisoConsultarUser.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultarUser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultarUser.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultarUser.setText("Consultar Usuario");
        chbPermisoConsultarUser.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoConsultarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 190, -1));

        chbPermisoIngresoUserTipo.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoUserTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoUserTipo.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoUserTipo.setText("Ingreso Tipo de Usuario");
        chbPermisoIngresoUserTipo.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoIngresoUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 160, -1));

        chbPermisoModificarUserTipo.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarUserTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarUserTipo.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarUserTipo.setText("Modificar Tipo de Usuario");
        chbPermisoModificarUserTipo.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoModificarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, 190, -1));

        chbPermisoEliminarUserTipo.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarUserTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarUserTipo.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarUserTipo.setText("Eliminar Tipo de Usuario");
        chbPermisoEliminarUserTipo.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoEliminarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 180, -1));

        chbPermisoConsultarUserTipo.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultarUserTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultarUserTipo.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultarUserTipo.setText("Consultar  Tipo de Usuario");
        chbPermisoConsultarUserTipo.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoConsultarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, 200, -1));
        pnlIngreso_UsuariosTipo.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 780, 10));
        pnlIngreso_UsuariosTipo.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 780, 10));

        lblname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname.setForeground(new java.awt.Color(153, 153, 153));
        lblname.setText("Nombre");
        pnlIngreso_UsuariosTipo.add(lblname, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, -1, -1));

        lblAsterisco12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco12.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco12.setText("*");
        pnlIngreso_UsuariosTipo.add(lblAsterisco12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 10, -1));

        pnlNombreDeUsuario2.setBackground(new java.awt.Color(231, 231, 231));
        pnlNombreDeUsuario2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreDeTipoUsuario.setBackground(new java.awt.Color(231, 231, 231));
        txtNombreDeTipoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreDeTipoUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreDeTipoUsuario.setBorder(null);
        pnlNombreDeUsuario2.add(txtNombreDeTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlIngreso_UsuariosTipo.add(pnlNombreDeUsuario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 260, 40));

        chbPermisoPastel.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoPastel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoPastel.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoPastel.setText("Grafica de Pastel");
        chbPermisoPastel.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoPastel, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, -1, -1));

        chbPermisoBarras.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoBarras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoBarras.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoBarras.setText("Grafica de Barras");
        chbPermisoBarras.setEnabled(false);
        pnlIngreso_UsuariosTipo.add(chbPermisoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, -1));

        pnlCuerpoUsuarios.add(pnlIngreso_UsuariosTipo, "card4");

        jspModificar_UsuariosTipo.setBorder(null);
        jspModificar_UsuariosTipo.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jspModificar_UsuariosTipo.setMinimumSize(new java.awt.Dimension(17, 10));
        jspModificar_UsuariosTipo.setPreferredSize(new java.awt.Dimension(1090, 40));

        pnlModificar_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificar_UsuariosTipo.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlModificar_UsuariosTipo.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlModificar_UsuariosTipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificarUserTipo.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblModificarUserTipo.setForeground(new java.awt.Color(44, 62, 80));
        lblModificarUserTipo.setText("MODIFICAR TIPOS DE USUARIOS");
        pnlModificar_UsuariosTipo.add(lblModificarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);

        tblUsuariosModificarTipo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowindex, int colindex){
                return false;
            }
        };
        tblUsuariosModificarTipo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblUsuariosModificarTipo.setForeground(new java.awt.Color(51, 51, 51));
        tblUsuariosModificarTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        tblUsuariosModificarTipo.setGridColor(new java.awt.Color(255, 255, 255));
        tblUsuariosModificarTipo.setRowHeight(20);
        tblUsuariosModificarTipo.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane4.setViewportView(tblUsuariosModificarTipo);

        pnlModificar_UsuariosTipo.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 850, 210));

        btnModificarUserTipo.setBackground(new java.awt.Color(0, 204, 102));
        btnModificarUserTipo.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarUserTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Modificar.png"))); // NOI18N
        btnModificarUserTipo.setBorder(null);
        btnModificarUserTipo.setBorderPainted(false);
        btnModificarUserTipo.setContentAreaFilled(false);
        btnModificarUserTipo.setEnabled(false);
        btnModificarUserTipo.setFocusable(false);
        btnModificarUserTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarUserTipoActionPerformed(evt);
            }
        });
        pnlModificar_UsuariosTipo.add(btnModificarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 110, 30));

        pnlIDTipoUsuario1.setBackground(new java.awt.Color(231, 231, 231));
        pnlIDTipoUsuario1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIDTipoUsuarioModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtIDTipoUsuarioModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDTipoUsuarioModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtIDTipoUsuarioModificar.setBorder(null);
        pnlIDTipoUsuario1.add(txtIDTipoUsuarioModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_UsuariosTipo.add(pnlIDTipoUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 260, 40));

        pnlNombreDeUsuario3.setBackground(new java.awt.Color(231, 231, 231));
        pnlNombreDeUsuario3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombreDeTipoUsuarioModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtNombreDeTipoUsuarioModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreDeTipoUsuarioModificar.setForeground(new java.awt.Color(153, 153, 153));
        txtNombreDeTipoUsuarioModificar.setBorder(null);
        pnlNombreDeUsuario3.add(txtNombreDeTipoUsuarioModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 40));

        pnlModificar_UsuariosTipo.add(pnlNombreDeUsuario3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, 260, 40));

        lblname9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname9.setForeground(new java.awt.Color(153, 153, 153));
        lblname9.setText("Nombre");
        pnlModificar_UsuariosTipo.add(lblname9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, -1, -1));

        lblAsterisco13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco13.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco13.setText("*");
        pnlModificar_UsuariosTipo.add(lblAsterisco13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, 10, -1));

        lblname10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname10.setForeground(new java.awt.Color(153, 153, 153));
        lblname10.setText("BUSQUE EL TIPO DE USUARIO QUE DESEA MODIFICAR");
        pnlModificar_UsuariosTipo.add(lblname10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        lblAsterisco14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco14.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco14.setText("*");
        pnlModificar_UsuariosTipo.add(lblAsterisco14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 10, -1));

        lblAsterisco18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco18.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco18.setText("*");
        pnlModificar_UsuariosTipo.add(lblAsterisco18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 10, -1));

        chbPermisoIngresoUser1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoUser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoUser1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoUser1.setText("Ingreso de Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoIngresoUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 160, -1));
        pnlModificar_UsuariosTipo.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 780, 10));

        chbPermisoModificarUser1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarUser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarUser1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarUser1.setText("Modificar Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoModificarUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 190, -1));

        chbPermisoModificarUserTipo1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarUserTipo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarUserTipo1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarUserTipo1.setText("Modificar Tipo de Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoModificarUserTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 190, -1));

        chbPermisoIngresoUserTipo1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoUserTipo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoUserTipo1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoUserTipo1.setText("Ingreso Tipo de Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoIngresoUserTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 160, -1));

        chbPermisoEliminarUserTipo1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarUserTipo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarUserTipo1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarUserTipo1.setText("Eliminar Tipo de Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoEliminarUserTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 180, -1));

        chbPermisoEliminarUser1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarUser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarUser1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarUser1.setText("Eliminar Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoEliminarUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 180, -1));

        chbPermisoConsultarUser1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultarUser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultarUser1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultarUser1.setText("Consultar Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoConsultarUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 190, -1));

        chbPermisoConsultarUserTipo1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultarUserTipo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultarUserTipo1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultarUserTipo1.setText("Consultar  Tipo de Usuario");
        pnlModificar_UsuariosTipo.add(chbPermisoConsultarUserTipo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 200, -1));
        pnlModificar_UsuariosTipo.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 780, 10));

        chbPermisoIngresoEmpleado1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoEmpleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoEmpleado1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoEmpleado1.setText("Ingreso de Personal");
        pnlModificar_UsuariosTipo.add(chbPermisoIngresoEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 160, -1));

        chbPermisoModificarEmpleado1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarEmpleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarEmpleado1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarEmpleado1.setText("Modificar Personal");
        pnlModificar_UsuariosTipo.add(chbPermisoModificarEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 160, -1));

        chbPermisoEliminarEmpleado1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarEmpleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarEmpleado1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarEmpleado1.setText("Eliminar  Personal");
        pnlModificar_UsuariosTipo.add(chbPermisoEliminarEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 160, -1));

        chbPermisoConsultaEmpleado1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultaEmpleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultaEmpleado1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultaEmpleado1.setText("Consulta Personal");
        pnlModificar_UsuariosTipo.add(chbPermisoConsultaEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, 160, -1));

        chbPermisoTabla1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoTabla1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoTabla1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoTabla1.setText("Tabla");
        pnlModificar_UsuariosTipo.add(chbPermisoTabla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, -1, -1));

        chbPermisoConsultaDep1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultaDep1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultaDep1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultaDep1.setText("Consulta Depertamento");
        pnlModificar_UsuariosTipo.add(chbPermisoConsultaDep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 360, 190, -1));

        chbPermisoEliminarDep1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarDep1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarDep1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarDep1.setText("Eliminar Depertamento");
        pnlModificar_UsuariosTipo.add(chbPermisoEliminarDep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 360, 190, -1));

        chbPermisoModificarDep1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarDep1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarDep1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarDep1.setText("Modificar Depertamento");
        pnlModificar_UsuariosTipo.add(chbPermisoModificarDep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 190, -1));

        chbPermisoIngresoDep1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoDep1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoDep1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoDep1.setText("Ingreso de Depertamento");
        pnlModificar_UsuariosTipo.add(chbPermisoIngresoDep1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 190, -1));

        chbPermisoIngresoConcepto1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoIngresoConcepto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoIngresoConcepto1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoIngresoConcepto1.setText("Ingreso de Concepto");
        pnlModificar_UsuariosTipo.add(chbPermisoIngresoConcepto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 160, -1));

        chbPermisoModificarConcepto1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoModificarConcepto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoModificarConcepto1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoModificarConcepto1.setText("Modificar Concepto");
        pnlModificar_UsuariosTipo.add(chbPermisoModificarConcepto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 390, 160, -1));

        chbPermisoEliminarConcepto1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoEliminarConcepto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoEliminarConcepto1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoEliminarConcepto1.setText("Eliminar Concepto");
        pnlModificar_UsuariosTipo.add(chbPermisoEliminarConcepto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 160, -1));

        chbPermisoConsultaConcepto1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoConsultaConcepto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoConsultaConcepto1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoConsultaConcepto1.setText("Consulta Concepto");
        pnlModificar_UsuariosTipo.add(chbPermisoConsultaConcepto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 390, 160, -1));

        lblname11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname11.setForeground(new java.awt.Color(153, 153, 153));
        lblname11.setText("ID");
        pnlModificar_UsuariosTipo.add(lblname11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        lblPermisos2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPermisos2.setForeground(new java.awt.Color(102, 102, 102));
        lblPermisos2.setText("Permisos de Nomina");
        pnlModificar_UsuariosTipo.add(lblPermisos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));

        pnlBuscarIDEliminar2.setBackground(new java.awt.Color(231, 231, 231));
        pnlBuscarIDEliminar2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarIDModificarTipo.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDModificarTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarIDModificarTipo.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarIDModificarTipo.setBorder(null);
        txtBuscarIDModificarTipo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarIDModificarTipoCaretUpdate(evt);
            }
        });
        pnlBuscarIDEliminar2.add(txtBuscarIDModificarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 470, 40));

        pnlModificar_UsuariosTipo.add(pnlBuscarIDEliminar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 480, 40));

        btnBuscarIDEModificarTipo.setBackground(new java.awt.Color(0, 204, 102));
        btnBuscarIDEModificarTipo.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarIDEModificarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        btnBuscarIDEModificarTipo.setBorder(null);
        btnBuscarIDEModificarTipo.setBorderPainted(false);
        btnBuscarIDEModificarTipo.setContentAreaFilled(false);
        btnBuscarIDEModificarTipo.setFocusable(false);
        btnBuscarIDEModificarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIDEModificarTipoActionPerformed(evt);
            }
        });
        pnlModificar_UsuariosTipo.add(btnBuscarIDEModificarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 110, 30));

        lblPermisos3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPermisos3.setForeground(new java.awt.Color(153, 153, 153));
        lblPermisos3.setText("Permisos");
        pnlModificar_UsuariosTipo.add(lblPermisos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        lblPermisos4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPermisos4.setForeground(new java.awt.Color(102, 102, 102));
        lblPermisos4.setText("Permisos de Usuario");
        pnlModificar_UsuariosTipo.add(lblPermisos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        chbPermisoBarras1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoBarras1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoBarras1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoBarras1.setText("Grafica de Barras");
        pnlModificar_UsuariosTipo.add(chbPermisoBarras1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, -1));

        chbPermisoPastel1.setBackground(new java.awt.Color(255, 255, 255));
        chbPermisoPastel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chbPermisoPastel1.setForeground(new java.awt.Color(153, 153, 153));
        chbPermisoPastel1.setText("Grafica de Pastel");
        pnlModificar_UsuariosTipo.add(chbPermisoPastel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, -1, -1));

        jspModificar_UsuariosTipo.setViewportView(pnlModificar_UsuariosTipo);

        pnlCuerpoUsuarios.add(jspModificar_UsuariosTipo, "card2");

        pnlEliminar_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminar_UsuariosTipo.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlEliminar_UsuariosTipo.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlEliminar_UsuariosTipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminarUserTipo.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblEliminarUserTipo.setForeground(new java.awt.Color(44, 62, 80));
        lblEliminarUserTipo.setText("ELIMINAR TIPO DE USUARIO");
        pnlEliminar_UsuariosTipo.add(lblEliminarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblAsterisco16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco16.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco16.setText("*");
        pnlEliminar_UsuariosTipo.add(lblAsterisco16, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 60, 10, -1));

        lblname7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname7.setForeground(new java.awt.Color(153, 153, 153));
        lblname7.setText("ID");
        pnlEliminar_UsuariosTipo.add(lblname7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 20, -1));

        btnEliminarUserTipo.setBackground(new java.awt.Color(0, 204, 102));
        btnEliminarUserTipo.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarUserTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Eliminar.png"))); // NOI18N
        btnEliminarUserTipo.setBorder(null);
        btnEliminarUserTipo.setBorderPainted(false);
        btnEliminarUserTipo.setContentAreaFilled(false);
        btnEliminarUserTipo.setEnabled(false);
        btnEliminarUserTipo.setFocusable(false);
        btnEliminarUserTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserTipoActionPerformed(evt);
            }
        });
        pnlEliminar_UsuariosTipo.add(btnEliminarUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 55, 110, 30));

        pnlBuscarIDEliminar1.setBackground(new java.awt.Color(231, 231, 231));
        pnlBuscarIDEliminar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarIDEliminarTipo.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDEliminarTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarIDEliminarTipo.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarIDEliminarTipo.setBorder(null);
        txtBuscarIDEliminarTipo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarIDEliminarTipoCaretUpdate(evt);
            }
        });
        txtBuscarIDEliminarTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarIDEliminarTipoKeyTyped(evt);
            }
        });
        pnlBuscarIDEliminar1.add(txtBuscarIDEliminarTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, 40));

        pnlEliminar_UsuariosTipo.add(pnlBuscarIDEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 530, 40));

        lblPrimerNombre6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre6.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre6.setText("Asegurese que la casilla este seleccionada para eliminar");
        pnlEliminar_UsuariosTipo.add(lblPrimerNombre6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 340, -1));

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setBorder(null);

        tblUsuariosEliminarTipo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowindex, int colindex){
                return false;
            }
        };
        tblUsuariosEliminarTipo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblUsuariosEliminarTipo.setForeground(new java.awt.Color(51, 51, 51));
        tblUsuariosEliminarTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsuariosEliminarTipo.setGridColor(new java.awt.Color(255, 255, 255));
        tblUsuariosEliminarTipo.setRowHeight(20);
        tblUsuariosEliminarTipo.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane7.setViewportView(tblUsuariosEliminarTipo);

        pnlEliminar_UsuariosTipo.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 900, 250));

        lblPrimerNombre8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre8.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre8.setText("ID del tipo de usuario");
        pnlEliminar_UsuariosTipo.add(lblPrimerNombre8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 140, -1));

        pnlCuerpoUsuarios.add(pnlEliminar_UsuariosTipo, "card4");

        pnlConsulta_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsulta_UsuariosTipo.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlConsulta_UsuariosTipo.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlConsulta_UsuariosTipo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsultaUserTipo.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblConsultaUserTipo.setForeground(new java.awt.Color(44, 62, 80));
        lblConsultaUserTipo.setText("CONSULTAS DE TIPOS DE USUARIO");
        pnlConsulta_UsuariosTipo.add(lblConsultaUserTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        lblname8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblname8.setForeground(new java.awt.Color(153, 153, 153));
        lblname8.setText("ID");
        pnlConsulta_UsuariosTipo.add(lblname8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 20, -1));

        lblAsterisco17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAsterisco17.setForeground(new java.awt.Color(255, 102, 102));
        lblAsterisco17.setText("*");
        pnlConsulta_UsuariosTipo.add(lblAsterisco17, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 60, 10, -1));

        pnlBuscarIDConsulta1.setBackground(new java.awt.Color(231, 231, 231));
        pnlBuscarIDConsulta1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscarIDConsultaTipo.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDConsultaTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarIDConsultaTipo.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarIDConsultaTipo.setBorder(null);
        txtBuscarIDConsultaTipo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarIDConsultaTipoCaretUpdate(evt);
            }
        });
        txtBuscarIDConsultaTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarIDConsultaTipoKeyTyped(evt);
            }
        });
        pnlBuscarIDConsulta1.add(txtBuscarIDConsultaTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, 40));

        pnlConsulta_UsuariosTipo.add(pnlBuscarIDConsulta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 530, 40));

        lblPrimerNombre7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPrimerNombre7.setForeground(new java.awt.Color(153, 153, 153));
        lblPrimerNombre7.setText("ID de Usuario");
        pnlConsulta_UsuariosTipo.add(lblPrimerNombre7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 100, -1));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(null);

        tblTipodeUsuarioConsulta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblTipodeUsuarioConsulta.setForeground(new java.awt.Color(51, 51, 51));
        tblTipodeUsuarioConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTipodeUsuarioConsulta.setGridColor(new java.awt.Color(255, 255, 255));
        tblTipodeUsuarioConsulta.setRowHeight(20);
        tblTipodeUsuarioConsulta.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane8.setViewportView(tblTipodeUsuarioConsulta);

        pnlConsulta_UsuariosTipo.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 910, 200));

        pnlCuerpoUsuarios.add(pnlConsulta_UsuariosTipo, "card4");

        pnlCuerpoNomina.setLayout(new java.awt.CardLayout());

        pnlInicio.setBackground(new java.awt.Color(255, 255, 255));
        pnlInicio.setMinimumSize(new java.awt.Dimension(940, 60));
        pnlInicio.setPreferredSize(new java.awt.Dimension(926, 700));
        pnlInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo2.png"))); // NOI18N
        lblLogo1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblLogo1MouseMoved(evt);
            }
        });
        lblLogo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogo1MouseExited(evt);
            }
        });
        pnlInicio.add(lblLogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, -1, -1));

        pnlCuerpoNomina.add(pnlInicio, "card4");

        pnlEmpleados.setBackground(new java.awt.Color(255, 255, 255));

        pnlIngresoEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngreso_NominaEmpleado.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblIngreso_NominaEmpleado.setForeground(new java.awt.Color(44, 62, 80));
        lblIngreso_NominaEmpleado.setText("MANTENIMIENTO PERSONAL/EMPLEADOS");
        pnlIngresoEmpleado.add(lblIngreso_NominaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 153, 153));
        jLabel23.setText("ID Empleado *");
        pnlIngresoEmpleado.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        txt_Sueldo_Empleado.setBackground(new java.awt.Color(231, 231, 231));
        txt_Sueldo_Empleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Sueldo_Empleado.setForeground(new java.awt.Color(153, 153, 153));
        txt_Sueldo_Empleado.setBorder(null);
        pnlIngresoEmpleado.add(txt_Sueldo_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 200, 35));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("Nombre Empleado *");
        pnlIngresoEmpleado.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        txt_BuscarE.setBackground(new java.awt.Color(231, 231, 231));
        txt_BuscarE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_BuscarE.setForeground(new java.awt.Color(153, 153, 153));
        txt_BuscarE.setBorder(null);
        txt_BuscarE.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_BuscarECaretUpdate(evt);
            }
        });
        pnlIngresoEmpleado.add(txt_BuscarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 450, 35));

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(153, 153, 153));
        jLabel61.setText("Apellido Empleado *");
        pnlIngresoEmpleado.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        txt_Nombre_Empleado1.setBackground(new java.awt.Color(231, 231, 231));
        txt_Nombre_Empleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Nombre_Empleado1.setForeground(new java.awt.Color(153, 153, 153));
        txt_Nombre_Empleado1.setBorder(null);
        pnlIngresoEmpleado.add(txt_Nombre_Empleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 250, 40));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(153, 153, 153));
        jLabel62.setText("DPI Empleado *");
        pnlIngresoEmpleado.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        txt_Apellido_Empleado1.setBackground(new java.awt.Color(231, 231, 231));
        txt_Apellido_Empleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Apellido_Empleado1.setForeground(new java.awt.Color(153, 153, 153));
        txt_Apellido_Empleado1.setBorder(null);
        pnlIngresoEmpleado.add(txt_Apellido_Empleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 250, 40));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(153, 153, 153));
        jLabel63.setText("Telefono Empleado *");
        pnlIngresoEmpleado.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        txt_DPI_Empleado1.setBackground(new java.awt.Color(231, 231, 231));
        txt_DPI_Empleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_DPI_Empleado1.setForeground(new java.awt.Color(153, 153, 153));
        txt_DPI_Empleado1.setBorder(null);
        pnlIngresoEmpleado.add(txt_DPI_Empleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 180, 40));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(153, 153, 153));
        jLabel64.setText("Estado Empleado *");
        pnlIngresoEmpleado.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        txt_Telefono_Empleado1.setBackground(new java.awt.Color(231, 231, 231));
        txt_Telefono_Empleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Telefono_Empleado1.setForeground(new java.awt.Color(153, 153, 153));
        txt_Telefono_Empleado1.setBorder(null);
        pnlIngresoEmpleado.add(txt_Telefono_Empleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 180, 40));

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(153, 153, 153));
        jLabel65.setText("Sueldo Empleado *");
        pnlIngresoEmpleado.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(153, 153, 153));
        jLabel66.setText("ID Puesto *");
        pnlIngresoEmpleado.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, -1));

        txt_ID_Empleado.setBackground(new java.awt.Color(231, 231, 231));
        txt_ID_Empleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_ID_Empleado.setForeground(new java.awt.Color(153, 153, 153));
        txt_ID_Empleado.setBorder(null);
        pnlIngresoEmpleado.add(txt_ID_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 150, 40));

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(153, 153, 153));
        jLabel67.setText("ID Departamento *");
        pnlIngresoEmpleado.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        jButton_InsertarE.setBackground(new java.awt.Color(231, 231, 231));
        jButton_InsertarE.setForeground(new java.awt.Color(51, 51, 51));
        jButton_InsertarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434749CEumVt6w.png"))); // NOI18N
        jButton_InsertarE.setText("Registrar");
        jButton_InsertarE.setBorder(null);
        jButton_InsertarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertarEActionPerformed(evt);
            }
        });
        pnlIngresoEmpleado.add(jButton_InsertarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 120, 30));

        jButton_ModificarE.setBackground(new java.awt.Color(231, 231, 231));
        jButton_ModificarE.setForeground(new java.awt.Color(51, 51, 51));
        jButton_ModificarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434446SRM9lBGm.png"))); // NOI18N
        jButton_ModificarE.setText("Modificar");
        jButton_ModificarE.setBorder(null);
        jButton_ModificarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ModificarEActionPerformed(evt);
            }
        });
        pnlIngresoEmpleado.add(jButton_ModificarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 120, 30));

        jButton_EliminarE.setBackground(new java.awt.Color(231, 231, 231));
        jButton_EliminarE.setForeground(new java.awt.Color(51, 51, 51));
        jButton_EliminarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_143457VkrjVJG9.png"))); // NOI18N
        jButton_EliminarE.setText("Eliminar");
        jButton_EliminarE.setBorder(null);
        jButton_EliminarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarEActionPerformed(evt);
            }
        });
        pnlIngresoEmpleado.add(jButton_EliminarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 120, 30));

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(153, 153, 153));
        jLabel69.setText("Ingresar ID Empleado");
        pnlIngresoEmpleado.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, -1, -1));

        jButton_BuscarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        jButton_BuscarE.setBorder(null);
        jButton_BuscarE.setBorderPainted(false);
        jButton_BuscarE.setContentAreaFilled(false);
        jButton_BuscarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BuscarEActionPerformed(evt);
            }
        });
        pnlIngresoEmpleado.add(jButton_BuscarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 260, -1, -1));

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblEmpleados.setGridColor(new java.awt.Color(255, 255, 255));
        tblEmpleados.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane21.setViewportView(tblEmpleados);

        pnlIngresoEmpleado.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 610, 190));

        cbEstado.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));
        cbEstado.setFocusable(false);
        pnlIngresoEmpleado.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 130, -1));

        cbDepartamento.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbDepartamento.setFocusable(false);
        pnlIngresoEmpleado.add(cbDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 190, -1));

        cbPuesto.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbPuesto.setFocusable(false);
        pnlIngresoEmpleado.add(cbPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 190, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Activo = A\nInactivo = I\n");
        jScrollPane15.setViewportView(jTextArea1);

        pnlIngresoEmpleado.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 240, 110));

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText(".Q");
        pnlIngresoEmpleado.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

        pnlModificarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificar_NominaEmpleado.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblModificar_NominaEmpleado.setForeground(new java.awt.Color(44, 62, 80));
        lblModificar_NominaEmpleado.setText("MODIFICAR EMPLEADOS");
        pnlModificarEmpleado.add(lblModificar_NominaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlModificarEmpleado.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));
        pnlModificarEmpleado.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jLabel70.setText("ID Empleado");
        pnlModificarEmpleado.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));
        pnlModificarEmpleado.add(txt_Sueldo_Empleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 100, -1));

        jLabel72.setText("Nombre Empleado");
        pnlModificarEmpleado.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel73.setText("Apellido Empleado");
        pnlModificarEmpleado.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));
        pnlModificarEmpleado.add(txt_Nombre_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 100, -1));

        jLabel74.setText("DPI Empleado");
        pnlModificarEmpleado.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));
        pnlModificarEmpleado.add(txt_Apellido_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 100, -1));

        jLabel75.setText("Telefono Empleado");
        pnlModificarEmpleado.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));
        pnlModificarEmpleado.add(txt_DPI_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 100, -1));

        jLabel76.setText("Estado Empleado");
        pnlModificarEmpleado.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));
        pnlModificarEmpleado.add(txt_Telefono_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 100, -1));

        jLabel77.setText("Sueldo Empleado");
        pnlModificarEmpleado.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));
        pnlModificarEmpleado.add(txt_Estado_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 100, -1));

        jLabel78.setText("ID Puesto");
        pnlModificarEmpleado.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));
        pnlModificarEmpleado.add(txt_ID_Empleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 100, -1));

        jLabel79.setText("ID Departamento");
        pnlModificarEmpleado.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));
        pnlModificarEmpleado.add(txt_IDP_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 100, -1));

        jLabel80.setText("ID Usuario");
        pnlModificarEmpleado.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));
        pnlModificarEmpleado.add(txt_IDD_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 100, -1));
        pnlModificarEmpleado.add(txt_IDU_Empleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 100, -1));

        pnlModificarEmpleado.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 220, -1));
        pnlModificarEmpleado.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 200, 150, -1));

        jLabel82.setText("Valor de Concepto:");
        pnlModificarEmpleado.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        pnlEliminarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminar_NominaEmpleado.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblEliminar_NominaEmpleado.setForeground(new java.awt.Color(44, 62, 80));
        lblEliminar_NominaEmpleado.setText("ELIMINAR EMPLEADOS");
        pnlEliminarEmpleado.add(lblEliminar_NominaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlEliminarEmpleado.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jTextField1.setText("jTextField1");
        pnlEliminarEmpleado.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jButton2.setText("jButton2");
        pnlEliminarEmpleado.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        pnlEliminarEmpleado.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        pnlConsultaEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsulta_NominaEmpleado.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblConsulta_NominaEmpleado.setForeground(new java.awt.Color(44, 62, 80));
        lblConsulta_NominaEmpleado.setText("CONSULTA EMPLEADOS");
        pnlConsultaEmpleado.add(lblConsulta_NominaEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlConsultaEmpleado.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jTextField4.setText("jTextField1");
        pnlConsultaEmpleado.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jButton3.setText("jButton2");
        pnlConsultaEmpleado.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        pnlConsultaEmpleado.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        javax.swing.GroupLayout pnlEmpleadosLayout = new javax.swing.GroupLayout(pnlEmpleados);
        pnlEmpleados.setLayout(pnlEmpleadosLayout);
        pnlEmpleadosLayout.setHorizontalGroup(
            pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlIngresoEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
            .addComponent(pnlModificarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlConsultaEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        pnlEmpleadosLayout.setVerticalGroup(
            pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlIngresoEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(pnlModificarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlConsultaEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );

        pnlCuerpoNomina.add(pnlEmpleados, "card8");

        pnlPuestos.setBackground(new java.awt.Color(255, 255, 255));

        pnlIngresoPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoPuesto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngreso_NominaEmpleado1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblIngreso_NominaEmpleado1.setForeground(new java.awt.Color(44, 62, 80));
        lblIngreso_NominaEmpleado1.setText("MANTENIMIENTO PUESTOS");
        pnlIngresoPuesto.add(lblIngreso_NominaEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("ID Puesto *");
        pnlIngresoPuesto.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Nombre del Puesto *");
        pnlIngresoPuesto.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Estado Puesto *");
        pnlIngresoPuesto.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        txt_idpuesto.setBackground(new java.awt.Color(231, 231, 231));
        txt_idpuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_idpuesto.setForeground(new java.awt.Color(153, 153, 153));
        txt_idpuesto.setBorder(null);
        pnlIngresoPuesto.add(txt_idpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 230, 35));

        txt_nombrepuesto.setBackground(new java.awt.Color(231, 231, 231));
        txt_nombrepuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_nombrepuesto.setForeground(new java.awt.Color(153, 153, 153));
        txt_nombrepuesto.setBorder(null);
        txt_nombrepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombrepuestoActionPerformed(evt);
            }
        });
        pnlIngresoPuesto.add(txt_nombrepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 310, 35));

        btn_ingresarpuesto.setBackground(new java.awt.Color(231, 231, 231));
        btn_ingresarpuesto.setForeground(new java.awt.Color(51, 51, 51));
        btn_ingresarpuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434749CEumVt6w.png"))); // NOI18N
        btn_ingresarpuesto.setText("Registrar");
        btn_ingresarpuesto.setBorder(null);
        btn_ingresarpuesto.setDefaultCapable(false);
        btn_ingresarpuesto.setFocusable(false);
        btn_ingresarpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarpuestoActionPerformed(evt);
            }
        });
        pnlIngresoPuesto.add(btn_ingresarpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 120, 30));

        btn_modificarpuesto.setBackground(new java.awt.Color(231, 231, 231));
        btn_modificarpuesto.setForeground(new java.awt.Color(51, 51, 51));
        btn_modificarpuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434446SRM9lBGm.png"))); // NOI18N
        btn_modificarpuesto.setText("Modificar");
        btn_modificarpuesto.setBorder(null);
        btn_modificarpuesto.setDefaultCapable(false);
        btn_modificarpuesto.setFocusable(false);
        btn_modificarpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarpuestoActionPerformed(evt);
            }
        });
        pnlIngresoPuesto.add(btn_modificarpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, 120, 30));

        btn_eliminarpuesto.setBackground(new java.awt.Color(231, 231, 231));
        btn_eliminarpuesto.setForeground(new java.awt.Color(51, 51, 51));
        btn_eliminarpuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_143457VkrjVJG9.png"))); // NOI18N
        btn_eliminarpuesto.setText("Eliminar");
        btn_eliminarpuesto.setBorder(null);
        btn_eliminarpuesto.setDefaultCapable(false);
        btn_eliminarpuesto.setFocusable(false);
        btn_eliminarpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarpuestoActionPerformed(evt);
            }
        });
        pnlIngresoPuesto.add(btn_eliminarpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 120, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Ingrese el ID del puesto ");
        pnlIngresoPuesto.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        txt_buscarpuesto.setBackground(new java.awt.Color(231, 231, 231));
        txt_buscarpuesto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_buscarpuesto.setForeground(new java.awt.Color(153, 153, 153));
        txt_buscarpuesto.setBorder(null);
        txt_buscarpuesto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_buscarpuestoCaretUpdate(evt);
            }
        });
        pnlIngresoPuesto.add(txt_buscarpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 360, 35));

        btn_buscarpuesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        btn_buscarpuesto.setBorder(null);
        btn_buscarpuesto.setContentAreaFilled(false);
        btn_buscarpuesto.setDefaultCapable(false);
        btn_buscarpuesto.setFocusable(false);
        btn_buscarpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarpuestoActionPerformed(evt);
            }
        });
        pnlIngresoPuesto.add(btn_buscarpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 160, -1, -1));
        pnlIngresoPuesto.add(label_statuspuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, -1));

        tblPuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblPuestos.setGridColor(new java.awt.Color(255, 255, 255));
        tblPuestos.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane22.setViewportView(tblPuestos);

        pnlIngresoPuesto.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 510, 160));

        cbEstadoP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));
        cbEstadoP.setFocusable(false);
        pnlIngresoPuesto.add(cbEstadoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 180, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Activo = A\nInactivo = I\n");
        jScrollPane16.setViewportView(jTextArea2);

        pnlIngresoPuesto.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 240, -1));

        pnlModificarPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarPuesto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificar_NominaEmpleado1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblModificar_NominaEmpleado1.setForeground(new java.awt.Color(44, 62, 80));
        lblModificar_NominaEmpleado1.setText("MODIFICAR PUESTOS");
        pnlModificarPuesto.add(lblModificar_NominaEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlModificarPuesto.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jLabel83.setText("ID Puesto");
        pnlModificarPuesto.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel84.setText("Nombre del Puesto");
        pnlModificarPuesto.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel85.setText("Estado Puesto");
        pnlModificarPuesto.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        pnlModificarPuesto.add(txt_idpuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 70, -1));

        txt_nombrepuesto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombrepuesto1ActionPerformed(evt);
            }
        });
        pnlModificarPuesto.add(txt_nombrepuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 70, -1));
        pnlModificarPuesto.add(txt_estadopuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 70, -1));

        btn_ingresarpuesto1.setText("Ingresar");
        btn_ingresarpuesto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresarpuesto1ActionPerformed(evt);
            }
        });
        pnlModificarPuesto.add(btn_ingresarpuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        btn_modificarpuesto1.setText("Modificar");
        btn_modificarpuesto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarpuesto1ActionPerformed(evt);
            }
        });
        pnlModificarPuesto.add(btn_modificarpuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        btn_eliminarpuesto1.setText("Eliminar");
        btn_eliminarpuesto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarpuesto1ActionPerformed(evt);
            }
        });
        pnlModificarPuesto.add(btn_eliminarpuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, -1, -1));

        jLabel86.setText("Ingrese el ID del puesto");
        pnlModificarPuesto.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));
        pnlModificarPuesto.add(txt_buscarpuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 60, -1));

        btn_buscarpuesto1.setText("Buscar");
        btn_buscarpuesto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarpuesto1ActionPerformed(evt);
            }
        });
        pnlModificarPuesto.add(btn_buscarpuesto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        pnlEliminarPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarPuesto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminar_NominaEmpleado1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblEliminar_NominaEmpleado1.setForeground(new java.awt.Color(44, 62, 80));
        lblEliminar_NominaEmpleado1.setText("ELIMINAR PUESTO");
        pnlEliminarPuesto.add(lblEliminar_NominaEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlEliminarPuesto.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jButton4.setText("jButton4");
        pnlEliminarPuesto.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jTextField5.setText("jTextField5");
        pnlEliminarPuesto.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable3);

        pnlEliminarPuesto.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        pnlConsultaPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaPuesto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsulta_NominaEmpleado1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblConsulta_NominaEmpleado1.setForeground(new java.awt.Color(44, 62, 80));
        lblConsulta_NominaEmpleado1.setText("CONSULTA PUESTOS");
        pnlConsultaPuesto.add(lblConsulta_NominaEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlConsultaPuesto.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        javax.swing.GroupLayout pnlPuestosLayout = new javax.swing.GroupLayout(pnlPuestos);
        pnlPuestos.setLayout(pnlPuestosLayout);
        pnlPuestosLayout.setHorizontalGroup(
            pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIngresoPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlModificarPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultaPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        pnlPuestosLayout.setVerticalGroup(
            pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIngresoPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlModificarPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlPuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultaPuesto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );

        pnlCuerpoNomina.add(pnlPuestos, "card5");

        pnlDepartamentos.setBackground(new java.awt.Color(255, 255, 255));

        pnlIngresoDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoDep.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngreso_NominaEmpleado2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblIngreso_NominaEmpleado2.setForeground(new java.awt.Color(44, 62, 80));
        lblIngreso_NominaEmpleado2.setText("MANTENIMIENTO DEPARTAMENTOS");
        pnlIngresoDep.add(lblIngreso_NominaEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText(" ID del Departamento *");
        pnlIngresoDep.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txt_iddep.setBackground(new java.awt.Color(231, 231, 231));
        txt_iddep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_iddep.setForeground(new java.awt.Color(153, 153, 153));
        txt_iddep.setBorder(null);
        pnlIngresoDep.add(txt_iddep, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 170, 35));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Nombre del Departamento *");
        pnlIngresoDep.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        txt_nombredep.setBackground(new java.awt.Color(231, 231, 231));
        txt_nombredep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_nombredep.setForeground(new java.awt.Color(153, 153, 153));
        txt_nombredep.setBorder(null);
        pnlIngresoDep.add(txt_nombredep, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 260, 35));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Estado del Departamento *");
        pnlIngresoDep.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        btn_ingresardep.setBackground(new java.awt.Color(231, 231, 231));
        btn_ingresardep.setForeground(new java.awt.Color(51, 51, 51));
        btn_ingresardep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434749CEumVt6w.png"))); // NOI18N
        btn_ingresardep.setText("Registrar");
        btn_ingresardep.setBorder(null);
        btn_ingresardep.setFocusable(false);
        btn_ingresardep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresardepActionPerformed(evt);
            }
        });
        pnlIngresoDep.add(btn_ingresardep, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 120, 30));

        btn_modificardep.setBackground(new java.awt.Color(231, 231, 231));
        btn_modificardep.setForeground(new java.awt.Color(51, 51, 51));
        btn_modificardep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434446SRM9lBGm.png"))); // NOI18N
        btn_modificardep.setText("Modificar");
        btn_modificardep.setToolTipText("");
        btn_modificardep.setBorder(null);
        btn_modificardep.setFocusable(false);
        btn_modificardep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificardepActionPerformed(evt);
            }
        });
        pnlIngresoDep.add(btn_modificardep, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 120, 30));

        btn_eliminardep.setBackground(new java.awt.Color(231, 231, 231));
        btn_eliminardep.setForeground(new java.awt.Color(51, 51, 51));
        btn_eliminardep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_143457VkrjVJG9.png"))); // NOI18N
        btn_eliminardep.setText("Eliminar");
        btn_eliminardep.setBorder(null);
        btn_eliminardep.setFocusable(false);
        btn_eliminardep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminardepActionPerformed(evt);
            }
        });
        pnlIngresoDep.add(btn_eliminardep, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 100, 120, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Ingrese ID departamento");
        pnlIngresoDep.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, -1, -1));

        btn_buscardep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        btn_buscardep.setBorder(null);
        btn_buscardep.setBorderPainted(false);
        btn_buscardep.setContentAreaFilled(false);
        btn_buscardep.setFocusable(false);
        btn_buscardep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscardepActionPerformed(evt);
            }
        });
        pnlIngresoDep.add(btn_buscardep, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 160, -1, -1));

        txt_buscardep.setBackground(new java.awt.Color(231, 231, 231));
        txt_buscardep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_buscardep.setForeground(new java.awt.Color(153, 153, 153));
        txt_buscardep.setBorder(null);
        txt_buscardep.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_buscardepCaretUpdate(evt);
            }
        });
        pnlIngresoDep.add(txt_buscardep, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 340, 35));
        pnlIngresoDep.add(label_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 80, -1));

        tblDep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblDep.setGridColor(new java.awt.Color(255, 255, 255));
        tblDep.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane23.setViewportView(tblDep);

        pnlIngresoDep.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 470, 170));

        cbEstadoD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "I" }));
        cbEstadoD.setFocusable(false);
        pnlIngresoDep.add(cbEstadoD, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 200, -1));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("Activo = A\nInactivo = I\n");
        jScrollPane17.setViewportView(jTextArea3);

        pnlIngresoDep.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        pnlModificarDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarDep.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificar_NominaEmpleado2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblModificar_NominaEmpleado2.setForeground(new java.awt.Color(44, 62, 80));
        lblModificar_NominaEmpleado2.setText("MODIFICAR DEPARTAMENTOS");
        pnlModificarDep.add(lblModificar_NominaEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlModificarDep.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        pnlEliminarDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarDep.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminar_NominaEmpleado2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblEliminar_NominaEmpleado2.setForeground(new java.awt.Color(44, 62, 80));
        lblEliminar_NominaEmpleado2.setText("ELIMINAR DEPARTAMENTOS");
        pnlEliminarDep.add(lblEliminar_NominaEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlEliminarDep.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jLabel91.setText("jLabel91");
        pnlEliminarDep.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jTextField7.setText("jTextField7");
        pnlEliminarDep.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTable5);

        pnlEliminarDep.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        pnlConsultaDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaDep.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsulta_NominaEmpleado2.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblConsulta_NominaEmpleado2.setForeground(new java.awt.Color(44, 62, 80));
        lblConsulta_NominaEmpleado2.setText("CONSULTA DEPARTAMENTOS");
        pnlConsultaDep.add(lblConsulta_NominaEmpleado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlConsultaDep.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jLabel92.setText("jLabel92");
        pnlConsultaDep.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        jTextField8.setText("jTextField8");
        pnlConsultaDep.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jButton6.setText("jButton6");
        pnlConsultaDep.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jTable6);

        pnlConsultaDep.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, -1, -1));

        javax.swing.GroupLayout pnlDepartamentosLayout = new javax.swing.GroupLayout(pnlDepartamentos);
        pnlDepartamentos.setLayout(pnlDepartamentosLayout);
        pnlDepartamentosLayout.setHorizontalGroup(
            pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIngresoDep, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlModificarDep, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarDep, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultaDep, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        pnlDepartamentosLayout.setVerticalGroup(
            pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIngresoDep, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlModificarDep, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarDep, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlDepartamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultaDep, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );

        pnlCuerpoNomina.add(pnlDepartamentos, "card4");

        pnlConceptos.setBackground(new java.awt.Color(255, 255, 255));

        pnlIngresoConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoConcepto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIngreso_NominaEmpleado3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblIngreso_NominaEmpleado3.setForeground(new java.awt.Color(44, 62, 80));
        lblIngreso_NominaEmpleado3.setText("MANTENIMIENTO CONCEPTOS");
        pnlIngresoConcepto.add(lblIngreso_NominaEmpleado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("ID Concepto Planilla *");
        pnlIngresoConcepto.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("Nombre Concepto Planilla *");
        pnlIngresoConcepto.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        txt_Buscar.setBackground(new java.awt.Color(231, 231, 231));
        txt_Buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Buscar.setForeground(new java.awt.Color(153, 153, 153));
        txt_Buscar.setBorder(null);
        txt_Buscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_BuscarCaretUpdate(evt);
            }
        });
        txt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BuscarActionPerformed(evt);
            }
        });
        pnlIngresoConcepto.add(txt_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 350, 35));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Tipo Concepto Panilla *");
        pnlIngresoConcepto.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        txt_Nombre_Concepto_Planilla.setBackground(new java.awt.Color(231, 231, 231));
        txt_Nombre_Concepto_Planilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Nombre_Concepto_Planilla.setForeground(new java.awt.Color(153, 153, 153));
        txt_Nombre_Concepto_Planilla.setBorder(null);
        pnlIngresoConcepto.add(txt_Nombre_Concepto_Planilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 290, 35));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setText("Clase Concepto Planilla *");
        pnlIngresoConcepto.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 153, 153));
        jLabel22.setText("Valor Concepto Planilla *");
        pnlIngresoConcepto.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        txt_Valor_Concepto_Planilla.setBackground(new java.awt.Color(231, 231, 231));
        txt_Valor_Concepto_Planilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_Valor_Concepto_Planilla.setForeground(new java.awt.Color(153, 153, 153));
        txt_Valor_Concepto_Planilla.setText("0");
        txt_Valor_Concepto_Planilla.setBorder(null);
        pnlIngresoConcepto.add(txt_Valor_Concepto_Planilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 90, 35));

        txt_ID_Concepto_Planilla.setBackground(new java.awt.Color(231, 231, 231));
        txt_ID_Concepto_Planilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_ID_Concepto_Planilla.setForeground(new java.awt.Color(153, 153, 153));
        txt_ID_Concepto_Planilla.setBorder(null);
        txt_ID_Concepto_Planilla.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                txt_ID_Concepto_PlanillaComponentHidden(evt);
            }
        });
        pnlIngresoConcepto.add(txt_ID_Concepto_Planilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 140, 35));

        jButton_Ingresar.setBackground(new java.awt.Color(231, 231, 231));
        jButton_Ingresar.setForeground(new java.awt.Color(51, 51, 51));
        jButton_Ingresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434749CEumVt6w.png"))); // NOI18N
        jButton_Ingresar.setText("Registrar");
        jButton_Ingresar.setBorder(null);
        jButton_Ingresar.setFocusable(false);
        jButton_Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IngresarActionPerformed(evt);
            }
        });
        pnlIngresoConcepto.add(jButton_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 120, 30));

        jButton_Modificar.setBackground(new java.awt.Color(231, 231, 231));
        jButton_Modificar.setForeground(new java.awt.Color(51, 51, 51));
        jButton_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434446SRM9lBGm.png"))); // NOI18N
        jButton_Modificar.setText("Modificar");
        jButton_Modificar.setBorder(null);
        jButton_Modificar.setFocusable(false);
        jButton_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ModificarActionPerformed(evt);
            }
        });
        pnlIngresoConcepto.add(jButton_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 120, 30));

        jButton_Eliminar.setBackground(new java.awt.Color(231, 231, 231));
        jButton_Eliminar.setForeground(new java.awt.Color(51, 51, 51));
        jButton_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_143457VkrjVJG9.png"))); // NOI18N
        jButton_Eliminar.setText("Eliminar");
        jButton_Eliminar.setBorder(null);
        jButton_Eliminar.setFocusable(false);
        jButton_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarActionPerformed(evt);
            }
        });
        pnlIngresoConcepto.add(jButton_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, 120, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 153, 153));
        jLabel27.setText("Ingrese ID Concepto Planilla");
        pnlIngresoConcepto.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, -1));

        jButton_Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        jButton_Buscar.setBorder(null);
        jButton_Buscar.setBorderPainted(false);
        jButton_Buscar.setContentAreaFilled(false);
        jButton_Buscar.setFocusable(false);
        jButton_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BuscarActionPerformed(evt);
            }
        });
        pnlIngresoConcepto.add(jButton_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, -1, -1));

        tblConceptos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblConceptos.setGridColor(new java.awt.Color(255, 255, 255));
        tblConceptos.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane24.setViewportView(tblConceptos);

        pnlIngresoConcepto.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 500, 170));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText("Monto =0\nCuota =0\nPorcentaje=%\n");
        jScrollPane18.setViewportView(jTextArea4);

        pnlIngresoConcepto.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, -1, -1));

        cbTipoConcepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Monto", "Cuota", "Porcentaje" }));
        cbTipoConcepto.setFocusable(false);
        pnlIngresoConcepto.add(cbTipoConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 210, -1));

        cbClase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Percepcion", "Deduccion" }));
        cbClase.setFocusable(false);
        pnlIngresoConcepto.add(cbClase, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 140, -1));

        jFormattedTextField2.setText("Tipo y Clase");
        pnlIngresoConcepto.add(jFormattedTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, -1));

        pnlModificarConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarConcepto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificar_NominaEmpleado3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblModificar_NominaEmpleado3.setForeground(new java.awt.Color(44, 62, 80));
        lblModificar_NominaEmpleado3.setText("MODIFICAR CONCEPTOS");
        pnlModificarConcepto.add(lblModificar_NominaEmpleado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlModificarConcepto.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jLabel93.setText("ID Concepto Planilla");
        pnlModificarConcepto.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel94.setText("Nombre Concepto Planilla");
        pnlModificarConcepto.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel95.setText("Tipo Concepto Panilla");
        pnlModificarConcepto.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));
        pnlModificarConcepto.add(txt_Nombre_Concepto_Planilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 130, -1));

        jLabel96.setText("Clase Concepto Planilla");
        pnlModificarConcepto.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));
        pnlModificarConcepto.add(txt_Tipo_Concepto_Planilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 130, -1));

        jLabel97.setText("Valor Concepto Planilla");
        pnlModificarConcepto.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));
        pnlModificarConcepto.add(txt_Clase_Concepto_Planilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 130, -1));
        pnlModificarConcepto.add(txt_Valor_Concepto_Planilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 130, -1));

        jLabel98.setText("ID Usuario");
        pnlModificarConcepto.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));
        pnlModificarConcepto.add(txt_ID_Concepto_Planilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 130, -1));
        pnlModificarConcepto.add(txt_IDU_Concepto_Planilla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 130, -1));

        pnlEliminarConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarConcepto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEliminar_NominaEmpleado3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblEliminar_NominaEmpleado3.setForeground(new java.awt.Color(44, 62, 80));
        lblEliminar_NominaEmpleado3.setText("ELIMINAR CONCEPTOS");
        pnlEliminarConcepto.add(lblEliminar_NominaEmpleado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlEliminarConcepto.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jLabel100.setText("jLabel100");
        pnlEliminarConcepto.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        jButton7.setText("jButton7");
        pnlEliminarConcepto.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jTextField9.setText("jTextField9");
        pnlEliminarConcepto.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jTable7);

        pnlEliminarConcepto.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        pnlConsultaConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaConcepto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsulta_NominaEmpleado3.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblConsulta_NominaEmpleado3.setForeground(new java.awt.Color(44, 62, 80));
        lblConsulta_NominaEmpleado3.setText("CONSULTA CONCEPTOS");
        pnlConsultaConcepto.add(lblConsulta_NominaEmpleado3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));
        pnlConsultaConcepto.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 870, 10));

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(jTable8);

        pnlConsultaConcepto.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        javax.swing.GroupLayout pnlConceptosLayout = new javax.swing.GroupLayout(pnlConceptos);
        pnlConceptos.setLayout(pnlConceptosLayout);
        pnlConceptosLayout.setHorizontalGroup(
            pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1090, Short.MAX_VALUE)
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIngresoConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlModificarConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultaConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE))
        );
        pnlConceptosLayout.setVerticalGroup(
            pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlIngresoConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlModificarConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEliminarConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
            .addGroup(pnlConceptosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlConsultaConcepto, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );

        pnlCuerpoNomina.add(pnlConceptos, "card4");

        pnlTabla.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTabla_Nomina.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 16)); // NOI18N
        lblTabla_Nomina.setForeground(new java.awt.Color(44, 62, 80));
        lblTabla_Nomina.setText("PLANILLA DETALLE Y GENERAL");
        pnlTabla.add(lblTabla_Nomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, 30));

        tblPlanillaGen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblPlanillaGen.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane20.setViewportView(tblPlanillaGen);

        pnlTabla.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, 480, 220));

        txt_IDEmpleadoPlanilla.setEditable(false);
        txt_IDEmpleadoPlanilla.setBackground(new java.awt.Color(231, 231, 231));
        txt_IDEmpleadoPlanilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_IDEmpleadoPlanilla.setForeground(new java.awt.Color(153, 153, 153));
        txt_IDEmpleadoPlanilla.setBorder(null);
        txt_IDEmpleadoPlanilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_IDEmpleadoPlanillaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_IDEmpleadoPlanillaKeyTyped(evt);
            }
        });
        pnlTabla.add(txt_IDEmpleadoPlanilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 40, 20));

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(153, 153, 153));
        jLabel103.setText("ID Empleado");
        pnlTabla.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jButton_InsertarE2.setBackground(new java.awt.Color(231, 231, 231));
        jButton_InsertarE2.setForeground(new java.awt.Color(51, 51, 51));
        jButton_InsertarE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434749CEumVt6w.png"))); // NOI18N
        jButton_InsertarE2.setText("Registrar");
        jButton_InsertarE2.setBorder(null);
        jButton_InsertarE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InsertarE2ActionPerformed(evt);
            }
        });
        pnlTabla.add(jButton_InsertarE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 120, 30));

        txt_IDConceptoPlanilla.setEditable(false);
        txt_IDConceptoPlanilla.setBackground(new java.awt.Color(231, 231, 231));
        txt_IDConceptoPlanilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_IDConceptoPlanilla.setForeground(new java.awt.Color(153, 153, 153));
        txt_IDConceptoPlanilla.setBorder(null);
        txt_IDConceptoPlanilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_IDConceptoPlanillaKeyReleased(evt);
            }
        });
        pnlTabla.add(txt_IDConceptoPlanilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 40, 20));

        txtBuscarTabla.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarTabla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtBuscarTabla.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarTabla.setBorder(null);
        txtBuscarTabla.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarTablaCaretUpdate(evt);
            }
        });
        pnlTabla.add(txtBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 230, 330, 35));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(153, 153, 153));
        jLabel71.setText("ID de Planilla Detalle");
        pnlTabla.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, 170, -1));

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(153, 153, 153));
        jLabel68.setText("ID Concepto");
        pnlTabla.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        tblPlanillaDet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblPlanillaDet.setGridColor(new java.awt.Color(255, 255, 255));
        tblPlanillaDet.setSelectionBackground(new java.awt.Color(0, 204, 255));
        jScrollPane19.setViewportView(tblPlanillaDet);

        pnlTabla.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 460, 220));

        btnEliminarTabla.setBackground(new java.awt.Color(231, 231, 231));
        btnEliminarTabla.setForeground(new java.awt.Color(51, 51, 51));
        btnEliminarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_143457VkrjVJG9.png"))); // NOI18N
        btnEliminarTabla.setText("Eliminar");
        btnEliminarTabla.setBorder(null);
        btnEliminarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTablaActionPerformed(evt);
            }
        });
        pnlTabla.add(btnEliminarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, 120, 30));

        btnModificarTabla1.setBackground(new java.awt.Color(231, 231, 231));
        btnModificarTabla1.setForeground(new java.awt.Color(51, 51, 51));
        btnModificarTabla1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_1434446SRM9lBGm.png"))); // NOI18N
        btnModificarTabla1.setText("Modificar");
        btnModificarTabla1.setBorder(null);
        btnModificarTabla1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarTabla1ActionPerformed(evt);
            }
        });
        pnlTabla.add(btnModificarTabla1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 120, 30));

        btnBuscarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Buscar.png"))); // NOI18N
        btnBuscarTabla.setBorder(null);
        btnBuscarTabla.setBorderPainted(false);
        btnBuscarTabla.setContentAreaFilled(false);
        btnBuscarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTablaActionPerformed(evt);
            }
        });
        pnlTabla.add(btnBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, -1, 30));

        txtValorConcepto.setBackground(new java.awt.Color(231, 231, 231));
        txtValorConcepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtValorConcepto.setForeground(new java.awt.Color(153, 153, 153));
        txtValorConcepto.setBorder(null);
        txtValorConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorConceptoActionPerformed(evt);
            }
        });
        txtValorConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorConceptoKeyReleased(evt);
            }
        });
        pnlTabla.add(txtValorConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 190, 35));

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(153, 153, 153));
        jLabel102.setText("Valor de Concepto:");
        pnlTabla.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        cbPercepcion.setBackground(new java.awt.Color(255, 255, 255));
        cbPercepcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbPercepcion.setForeground(new java.awt.Color(153, 153, 153));
        cbPercepcion.setSelected(true);
        cbPercepcion.setText("Percepcion");
        cbPercepcion.setFocusable(false);
        cbPercepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPercepcionActionPerformed(evt);
            }
        });
        pnlTabla.add(cbPercepcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 100, -1));

        cbDeduccion.setBackground(new java.awt.Color(255, 255, 255));
        cbDeduccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbDeduccion.setForeground(new java.awt.Color(153, 153, 153));
        cbDeduccion.setText("Deduccion");
        cbDeduccion.setFocusable(false);
        cbDeduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDeduccionActionPerformed(evt);
            }
        });
        pnlTabla.add(cbDeduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 100, -1));

        cbISR.setBackground(new java.awt.Color(255, 255, 255));
        cbISR.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbISR.setForeground(new java.awt.Color(153, 153, 153));
        cbISR.setText("Calcular ISR");
        cbISR.setFocusable(false);
        cbISR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbISRActionPerformed(evt);
            }
        });
        pnlTabla.add(cbISR, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 110, -1));

        jButton9.setBackground(new java.awt.Color(0, 204, 204));
        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/iggs.png"))); // NOI18N
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        pnlTabla.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 110, 30));

        jButton10.setForeground(new java.awt.Color(51, 51, 51));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/oie_JUZlOV8cmTXU.png"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusable(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        pnlTabla.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 110, 30));

        cbIDConcepto.setFocusable(false);
        cbIDConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDConceptoActionPerformed(evt);
            }
        });
        pnlTabla.add(cbIDConcepto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 140, -1));

        cbIDEmpleado.setFocusable(false);
        cbIDEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIDEmpleadoActionPerformed(evt);
            }
        });
        pnlTabla.add(cbIDEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 140, -1));

        jTextArea5.setColumns(20);
        jTextArea5.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jTextArea5.setRows(5);
        jTextArea5.setText("Para Calcular ISR active Calcular ISR con un Check y seleccione un ID Empleado y enter\nAutomaticamente le dara un resultado\n\nPara Calcular IGS seleccione un ID Empleado y enter seguido presione el boton IGSS\nPD: El Representante legal no se le cobra IGSS\n \n\n");
        jScrollPane25.setViewportView(jTextArea5);

        pnlTabla.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 480, 100));

        pnlCuerpoNomina.add(pnlTabla, "card6");

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlMenuInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlCuerpoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlIInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlCuerpoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenuUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlMenuInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(pnlCuerpoUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlIInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCuerpoNomina, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pnlCentro.add(pnlContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1090, 750));

        jPanel_Plataforma.add(pnlCentro, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1247, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel_Plataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 1247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel_Plataforma, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseMoved

    }//GEN-LAST:event_jLabel2MouseMoved

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited

    }//GEN-LAST:event_jLabel2MouseExited

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed

        pnlContenido.removeAll();
        pnlContenido.repaint();
        pnlContenido.revalidate();
        pnlContenido.add(pnlMenuInicio);
        pnlContenido.add(pnlIInicio);

        pnlMenu_barUser.setVisible(true);

        pnlMenu_barUser.setVisible(false);
        pnlContenido.repaint();
        pnlContenido.revalidate();

        lblNomina.setForeground(new java.awt.Color(0, 0, 0));
        lblUsuarios.setForeground(new java.awt.Color(0, 0, 0));
        lblIinicio.setForeground(new java.awt.Color(0, 204, 204));
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustesActionPerformed
        theme = true;
        pnlSubOpcionTema.setVisible(true);
        if (graficas == true) {
            pnlizquierdo.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, -1));
            pnlizquierdo.add(btnGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(pnlGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 160, -1));
        } else {
            pnlizquierdo.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, -1));
            pnlizquierdo.add(btnGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 160, -1));
        }


    }//GEN-LAST:event_btnAjustesActionPerformed

    private void btnGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficaActionPerformed

        if (theme == true) {
            pnlGraph.setVisible(true);
            pnlizquierdo.add(btnGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(pnlGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 160, -1));
            graficas = true;
        } else {
            pnlGraph.setVisible(true);
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, -1));
            graficas = true;
        }


    }//GEN-LAST:event_btnGraficaActionPerformed

    private void btnCalculadoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalculadoraMouseClicked
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("calc");
            p.waitFor();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnCalculadoraMouseClicked

    private void btnCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculadoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalculadoraActionPerformed

    private void btnPaginaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPaginaMouseClicked
        try {
            Desktop.getDesktop().browse(URI.create("https://umg.edu.gt/"));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, e);
        }
    }//GEN-LAST:event_btnPaginaMouseClicked

    private void btnPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaActionPerformed

    }//GEN-LAST:event_btnPaginaActionPerformed

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        Login2 PF = new Login2();
        PF.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseEntered
        btnMinimize.setBackground(new Color(93, 93, 93));
    }//GEN-LAST:event_btnMinimizeMouseEntered

    private void btnMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseExited
        btnMinimize.setBackground(new Color(60, 60, 60));
    }//GEN-LAST:event_btnMinimizeMouseExited

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed

    private void btnMaximizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseEntered
        btnMaximize.setBackground(new Color(93, 93, 93));
    }//GEN-LAST:event_btnMaximizeMouseEntered

    private void btnMaximizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseExited
        btnMaximize.setBackground(new Color(60, 60, 60));
    }//GEN-LAST:event_btnMaximizeMouseExited

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
        if (maximized) {
            //handle fullscreen - taskbar
            Plataforma.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Plataforma.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }//GEN-LAST:event_btnMaximizeActionPerformed

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnExit.setBackground(new Color(255, 101, 101));
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExit.setBackground(new Color(60, 60, 60));
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed


    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void btnPuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuestosActionPerformed


    }//GEN-LAST:event_btnPuestosActionPerformed

    private void btnDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepartamentosActionPerformed


    }//GEN-LAST:event_btnDepartamentosActionPerformed

    private void btnConceptosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConceptosActionPerformed


    }//GEN-LAST:event_btnConceptosActionPerformed

    private void btnTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTablaActionPerformed

    }//GEN-LAST:event_btnTablaActionPerformed

    private void jPanel_PlataformaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_PlataformaMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_jPanel_PlataformaMouseDragged

    private void jPanel_PlataformaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_PlataformaMousePressed
        xx = evt.getX();
        xy = evt.getY();
        btnMantenimientoUsuarios.setVisible(false);
        btnMantenimientoUsuarios1.setVisible(false);
        pnlOpciones.setVisible(false);
        pnlOpcionesTipo.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlCatalogo.setVisible(false);
        pnlProcesos.setVisible(false);
        pnlHerramientas.setVisible(false);
        pnlInformes.setVisible(false);
        pnlAyuda.setVisible(false);
    }//GEN-LAST:event_jPanel_PlataformaMousePressed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnInicioUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioUsuariosActionPerformed
        /*        pnlOpciones.setVisible(true);
        pnlOpcionesTipo.setVisible(true);
        pnlOpcionesTipo.setVisible(false);*/

    }//GEN-LAST:event_btnInicioUsuariosActionPerformed

    private void lblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsuariosMouseClicked
        pnlContenido.removeAll();
        pnlContenido.repaint();
        pnlContenido.revalidate();
        pnlContenido.add(pnlMenuUsuarios);
        pnlContenido.add(pnlCuerpoUsuarios);

        pnlMenu_barUser.setVisible(true);
        pnlContenido.repaint();
        pnlContenido.revalidate();

        lblNomina.setForeground(new java.awt.Color(204, 204, 204));
        lblUsuarios.setForeground(new java.awt.Color(0, 204, 204));
        lblIinicio.setForeground(new java.awt.Color(204, 204, 204));

    }//GEN-LAST:event_lblUsuariosMouseClicked

    private void lblNominaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNominaMouseClicked
        pnlContenido.removeAll();
        pnlContenido.repaint();
        pnlContenido.revalidate();
        pnlContenido.add(pnlMenu);
        pnlContenido.add(pnlCuerpoNomina);
        pnlMenu_barUser.setVisible(true);
        pnlMenu_barUser.setVisible(false);

        pnlContenido.repaint();
        pnlContenido.revalidate();

        lblNomina.setForeground(new java.awt.Color(0, 204, 204));
        lblUsuarios.setForeground(new java.awt.Color(204, 204, 204));
        lblIinicio.setForeground(new java.awt.Color(204, 204, 204));
        /* pnlMenuUsuarios.setVisible(false);
       pnlBodyUsuarios.setVisible(false);
       pnlMenu.setVisible(true);
       pnlBody.setVisible(true);*/
    }//GEN-LAST:event_lblNominaMouseClicked

    private void btnInicioUsuariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioUsuariosMouseMoved

    }//GEN-LAST:event_btnInicioUsuariosMouseMoved

    private void btnInicioUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioUsuariosMouseExited

    }//GEN-LAST:event_btnInicioUsuariosMouseExited

    private void pnlOpcionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpcionesMouseExited

    }//GEN-LAST:event_pnlOpcionesMouseExited

    private void btnOpsion_ConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultarMouseClicked


    }//GEN-LAST:event_btnOpsion_ConsultarMouseClicked

    private void btnOpsion_IngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarMouseClicked

    private void btnOpsion_ModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarMouseClicked

    private void btnOpsion_EliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarMouseClicked

    private void btnOpsion_IngresarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarMouseMoved

    }//GEN-LAST:event_btnOpsion_IngresarMouseMoved

    private void jPanel_PlataformaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_PlataformaMouseMoved

    }//GEN-LAST:event_jPanel_PlataformaMouseMoved

    private void btnOpsion_IngresarEmpleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarEmpleadoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarEmpleadoMouseMoved

    private void btnOpsion_IngresarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarEmpleadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarEmpleadoMouseClicked

    private void btnOpsion_IngresarDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarDepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarDepMouseClicked

    private void btnOpsion_IngresarConceptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarConceptoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarConceptoMouseClicked

    private void pnlOpciones_NominaEmpleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpciones_NominaEmpleadosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlOpciones_NominaEmpleadosMouseExited

    private void btnEmpleadosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpleadosMouseMoved

    }//GEN-LAST:event_btnEmpleadosMouseMoved

    private void btnOpsion_ConsultaEmpleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaEmpleadoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ConsultaEmpleadoMouseMoved

    private void btnOpsion_ConsultaEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaEmpleadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ConsultaEmpleadoMouseClicked

    private void btnOpsion_ConsultaDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaDepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ConsultaDepMouseClicked

    private void btnOpsion_ConsultaConceptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaConceptoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ConsultaConceptoMouseClicked

    private void pnlOpciones_NominaPuestosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpciones_NominaPuestosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlOpciones_NominaPuestosMouseExited

    private void btnOpsion_ModificarEmpleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarEmpleadoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarEmpleadoMouseMoved

    private void btnOpsion_ModificarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarEmpleadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarEmpleadoMouseClicked

    private void btnOpsion_ModificarDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarDepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarDepMouseClicked

    private void btnOpsion_ModificarConceptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarConceptoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarConceptoMouseClicked

    private void pnlOpciones_NominaDepartamentosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpciones_NominaDepartamentosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlOpciones_NominaDepartamentosMouseExited

    private void btnOpsion_EliminarEmpleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarEmpleadoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarEmpleadoMouseMoved

    private void btnOpsion_EliminarEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarEmpleadoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarEmpleadoMouseClicked

    private void btnOpsion_EliminarDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarDepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarDepMouseClicked

    private void btnOpsion_EliminarConceptoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarConceptoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarConceptoMouseClicked

    private void pnlOpciones_NominaConceptosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpciones_NominaConceptosMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlOpciones_NominaConceptosMouseExited

    private void btnOpsion_EliminarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarConceptoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlConceptos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlConceptos.removeAll();
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
        pnlConceptos.add(pnlEliminarConcepto);
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
    }//GEN-LAST:event_btnOpsion_EliminarConceptoActionPerformed

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        Login2 PF = new Login2();
        PF.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed

    }//GEN-LAST:event_jLabel17MousePressed

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MouseClicked
        Login2 PF = new Login2();
        PF.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel25MouseClicked

    private void lblIinicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIinicioMouseClicked
        pnlContenido.removeAll();
        pnlContenido.repaint();
        pnlContenido.revalidate();
        pnlContenido.add(pnlMenuInicio);
        pnlContenido.add(pnlIInicio);

        pnlMenu_barUser.setVisible(true);

        pnlMenu_barUser.setVisible(false);
        pnlContenido.repaint();
        pnlContenido.revalidate();

        lblNomina.setForeground(new java.awt.Color(204, 204, 204));
        lblUsuarios.setForeground(new java.awt.Color(204, 204, 204));
        lblIinicio.setForeground(new java.awt.Color(0, 204, 204));
    }//GEN-LAST:event_lblIinicioMouseClicked

    private void lblOscuroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOscuroMouseClicked
        color[0] = 24;
        color[1] = 24;
        color[2] = 24;
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo1.png")));
        lblLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo1.png")));
        UIManager.put("OptionPane.background", new Color(24, 24, 24));
        UIManager.put("Panel.background", new Color(19, 19, 19));
        UIManager.put("Button.background", new Color(235, 235, 235));
        UIManager.put("Button.foreground", new Color(27, 27, 25));
        UIManager.put("OptionPane.messageForeground", new Color(166, 166, 166));

        JCheckBox[] Permisos = {chbPermisoIngresoUser1, chbPermisoModificarUser1, chbPermisoEliminarUser1, chbPermisoConsultarUser1,
            chbPermisoIngresoUserTipo1, chbPermisoModificarUserTipo1, chbPermisoEliminarUserTipo1, chbPermisoConsultarUserTipo1,
            chbPermisoIngresoEmpleado1, chbPermisoModificarEmpleado1, chbPermisoEliminarEmpleado1, chbPermisoConsultaEmpleado1,
            chbPermisoIngresoDep1, chbPermisoModificarDep1, chbPermisoEliminarDep1, chbPermisoConsultaDep1, chbPermisoIngresoConcepto1,
            chbPermisoModificarConcepto1, chbPermisoEliminarConcepto1, chbPermisoConsultaConcepto1, chbPermisoTabla1, chbPermisoBarras1, chbPermisoPastel1,
            chbPermisoIngresoUser, chbPermisoModificarUser, chbPermisoEliminarUser, chbPermisoConsultarUser,
            chbPermisoIngresoUserTipo, chbPermisoModificarUserTipo, chbPermisoEliminarUserTipo, chbPermisoConsultarUserTipo,
            chbPermisoIngresoEmpleado, chbPermisoModificarEmpleado, chbPermisoEliminarEmpleado, chbPermisoConsultaEmpleado,
            chbPermisoIngresoDep, chbPermisoModificarDep, chbPermisoEliminarDep, chbPermisoConsultaDep, chbPermisoIngresoConcepto,
            chbPermisoModificarConcepto, chbPermisoEliminarConcepto, chbPermisoConsultaConcepto, chbPermisoTabla, chbPermisoBarras, chbPermisoPastel, chbPermisoUsuario, chbPermisoNomina, cbPercepcion, cbDeduccion, cbISR};
        //Menus
        lblEmpleados.setBackground(new Color(color[0], color[1], color[2]));
        lblConceptos.setBackground(new Color(color[0], color[1], color[2]));
        lblPuestos.setBackground(new Color(color[0], color[1], color[2]));
        lblDep.setBackground(new Color(color[0], color[1], color[2]));
        lblTabla.setBackground(new Color(color[0], color[1], color[2]));
        lbltipodeUsuario.setBackground(new Color(color[0], color[1], color[2]));
        lblIngresarUser.setBackground(new Color(color[0], color[1], color[2]));
        pnlMenuInicio.setBackground(new java.awt.Color(color[0], color[1], color[2]));
        pnlDetalles.setBackground(new java.awt.Color(39, 39, 39));
        pnlContacto.setBackground(new java.awt.Color(39, 39, 39));
        pnlDetalles1.setBackground(new java.awt.Color(39, 39, 39));
        pnlContacto1.setBackground(new java.awt.Color(39, 39, 39));
        pnlDetalles2.setBackground(new java.awt.Color(39, 39, 39));

        pnlContacto2.setBackground(new java.awt.Color(39, 39, 39));
        cbVertical.setBackground(new java.awt.Color(39, 39, 39));
        cbHorizontal.setBackground(new java.awt.Color(39, 39, 39));
        cbLineal.setBackground(new java.awt.Color(39, 39, 39));
        cbPastel.setBackground(new java.awt.Color(39, 39, 39));
        pnlMenuUsuarios.setBackground(new java.awt.Color(color[0], color[1], color[2]));
        pnlMenu.setBackground(new java.awt.Color(color[0], color[1], color[2]));

        //Ingreso Usuarios
        /* pnlNombreDeUsuario.setBackground(new java.awt.Color(39, 39, 39));
        pnlID.setBackground(new java.awt.Color(39, 39, 39));

        pnlContraseña.setBackground(new java.awt.Color(39, 39, 39));
        pnlConfirmar.setBackground(new java.awt.Color(39, 39, 39));
        pnlMail.setBackground(new java.awt.Color(39, 39, 39));
        pnlTelefono.setBackground(new java.awt.Color(39, 39, 39));
        txtNombreDeUsuario.setBackground(new java.awt.Color(39, 39, 39));
        txtIDUsuario.setBackground(new java.awt.Color(39, 39, 39));

        txtContraseñaUser.setBackground(new java.awt.Color(39, 39, 39));
        txtConfirmarUser.setBackground(new java.awt.Color(39, 39, 39));
        txtMail.setBackground(new java.awt.Color(39, 39, 39));
        txtTelefono.setBackground(new java.awt.Color(39, 39, 39));
        cbTipo.setBackground(new java.awt.Color(39, 39, 39));
        //Modificar User
        pnlBuscarIDModificar.setBackground(new java.awt.Color(39, 39, 39));
        txtBuscarIDModificar.setBackground(new java.awt.Color(39, 39, 39));
        //Eliminar User
        pnlBuscarIDEliminar.setBackground(new java.awt.Color(39, 39, 39));
        txtBuscarIDEliminar.setBackground(new java.awt.Color(39, 39, 39));
        //Consulta User
        pnlBuscarIDConsulta.setBackground(new java.awt.Color(39, 39, 39));
        txtBuscarIDConsulta.setBackground(new java.awt.Color(39, 39, 39));*/
        //Panels de contenido
        //Menus bar
        pnlMenu_barUser.setBackground(new java.awt.Color(17, 17, 17));

        //Contenido de Inicio
        pnlBienvenida.setBackground(new java.awt.Color(17, 17, 17));
        //Contenido de Usuarios
        pnlIngreso_Usuarios.setBackground(new java.awt.Color(17, 17, 17));
        pnlModificar_Usuarios.setBackground(new java.awt.Color(17, 17, 17));
        pnlEliminar_Usuarios.setBackground(new java.awt.Color(17, 17, 17));
        pnlConsulta_Usuarios.setBackground(new java.awt.Color(17, 17, 17));
        //Contenido de Tipos Usuarios
        pnlIngreso_UsuariosTipo.setBackground(new java.awt.Color(17, 17, 17));
        pnlModificar_UsuariosTipo.setBackground(new java.awt.Color(17, 17, 17));
        pnlEliminar_UsuariosTipo.setBackground(new java.awt.Color(17, 17, 17));
        pnlConsulta_UsuariosTipo.setBackground(new java.awt.Color(17, 17, 17));
        chbPorNombreUsuario.setBackground(new java.awt.Color(17, 17, 17));
        chbPorId.setBackground(new java.awt.Color(17, 17, 17));
        chbPorIdConcepto.setBackground(new java.awt.Color(17, 17, 17));

        for (int i = 0; i < 51; i++) {
            Permisos[i].setBackground(new java.awt.Color(17, 17, 17));
        }

        //Contenido de Nomina de Empleados
        pnlInicio.setBackground(new java.awt.Color(17, 17, 17));
        pnlInicio1.setBackground(new java.awt.Color(17, 17, 17));

        pnlEmpleados.setBackground(new java.awt.Color(17, 17, 17));
        pnlEmpleados.setBackground(new java.awt.Color(17, 17, 17));
        pnlIngresoEmpleado.setBackground(new java.awt.Color(17, 17, 17));
        pnlModificarEmpleado.setBackground(new java.awt.Color(17, 17, 17));
        pnlEliminarEmpleado.setBackground(new java.awt.Color(17, 17, 17));
        pnlConsultaEmpleado.setBackground(new java.awt.Color(17, 17, 17));

        pnlPuestos.setBackground(new java.awt.Color(17, 17, 17));
        pnlIngresoPuesto.setBackground(new java.awt.Color(17, 17, 17));
        pnlModificarPuesto.setBackground(new java.awt.Color(17, 17, 17));
        pnlEliminarPuesto.setBackground(new java.awt.Color(17, 17, 17));
        pnlConsultaPuesto.setBackground(new java.awt.Color(17, 17, 17));

        pnlDepartamentos.setBackground(new java.awt.Color(17, 17, 17));
        pnlIngresoDep.setBackground(new java.awt.Color(17, 17, 17));
        pnlModificarDep.setBackground(new java.awt.Color(17, 17, 17));
        pnlEliminarDep.setBackground(new java.awt.Color(17, 17, 17));
        pnlConsultaDep.setBackground(new java.awt.Color(17, 17, 17));
        pnlConceptos.setBackground(new java.awt.Color(17, 17, 17));
        pnlIngresoConcepto.setBackground(new java.awt.Color(17, 17, 17));
        pnlModificarConcepto.setBackground(new java.awt.Color(17, 17, 17));
        pnlEliminarConcepto.setBackground(new java.awt.Color(17, 17, 17));
        pnlConsultaConcepto.setBackground(new java.awt.Color(17, 17, 17));
        pnlTabla.setBackground(new java.awt.Color(17, 17, 17));

        //  Titulos de Inicio
        lblUsernameInicio.setForeground(new Color(240, 240, 240));
        // lblBienvenida.setForeground(new Color(240, 240, 240));
        //Titulos de Usuarios
        btnInicioUsuarios.setForeground(new java.awt.Color(240, 240, 240));
        //lblUsername.setForeground(new Color(240, 240, 240));
        lblIngresoUser.setForeground(new Color(240, 240, 240));
        lblModificarUser.setForeground(new Color(240, 240, 240));
        lblEliminarUser.setForeground(new Color(240, 240, 240));
        lblConsultaUser.setForeground(new Color(240, 240, 240));

        //Titlos de Tipo de usuario
        btnTipodeUsuario.setForeground(new java.awt.Color(240, 240, 240));
        lblIngresoUserTipo.setForeground(new Color(240, 240, 240));
        lblModificarUserTipo.setForeground(new Color(240, 240, 240));
        lblEliminarUserTipo.setForeground(new Color(240, 240, 240));
        lblConsultaUserTipo.setForeground(new Color(240, 240, 240));

        //Titulos de Nomina de Empleados
        //  lblUsernameNomina.setForeground(new Color(240, 240, 240));
        lblIngreso_NominaEmpleado.setForeground(new Color(240, 240, 240));
        lblModificar_NominaEmpleado.setForeground(new Color(240, 240, 240));
        lblEliminar_NominaEmpleado.setForeground(new Color(240, 240, 240));
        lblConsulta_NominaEmpleado.setForeground(new Color(240, 240, 240));
        lblIngreso_NominaEmpleado1.setForeground(new Color(240, 240, 240));
        lblModificar_NominaEmpleado1.setForeground(new Color(240, 240, 240));
        lblEliminar_NominaEmpleado1.setForeground(new Color(240, 240, 240));
        lblConsulta_NominaEmpleado1.setForeground(new Color(240, 240, 240));
        lblIngreso_NominaEmpleado2.setForeground(new Color(240, 240, 240));
        lblModificar_NominaEmpleado2.setForeground(new Color(240, 240, 240));
        lblEliminar_NominaEmpleado2.setForeground(new Color(240, 240, 240));
        lblConsulta_NominaEmpleado2.setForeground(new Color(240, 240, 240));
        lblIngreso_NominaEmpleado3.setForeground(new Color(240, 240, 240));
        lblModificar_NominaEmpleado3.setForeground(new Color(240, 240, 240));
        lblEliminar_NominaEmpleado3.setForeground(new Color(240, 240, 240));
        lblConsulta_NominaEmpleado3.setForeground(new Color(240, 240, 240));

        lblTabla_Nomina.setForeground(new Color(240, 240, 240));
        btnEmpleados.setForeground(new java.awt.Color(240, 240, 240));
        btnPuestos.setForeground(new java.awt.Color(240, 240, 240));
        btnDepartamentos.setForeground(new java.awt.Color(240, 240, 240));
        btnConceptos.setForeground(new java.awt.Color(240, 240, 240));
        btnTabla.setForeground(new java.awt.Color(240, 240, 240));

        //botones de tema
        lblClaro.setVisible(true);
        lblOscuro.setVisible(false);
    }//GEN-LAST:event_lblOscuroMouseClicked

    private void lblClaroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClaroMouseClicked
        color[0] = 222;
        color[1] = 222;
        color[2] = 222;

        UIManager.put("OptionPane.background", new Color(235, 235, 235));
        UIManager.put("Panel.background", new Color(238, 238, 238));
        UIManager.put("Button.background", new Color(235, 235, 235));
        UIManager.put("Button.foreground", new Color(27, 27, 25));
        UIManager.put("OptionPane.messageForeground", new Color(31, 31, 31));

        JCheckBox[] Permisos = {chbPermisoIngresoUser1, chbPermisoModificarUser1, chbPermisoEliminarUser1, chbPermisoConsultarUser1,
            chbPermisoIngresoUserTipo1, chbPermisoModificarUserTipo1, chbPermisoEliminarUserTipo1, chbPermisoConsultarUserTipo1,
            chbPermisoIngresoEmpleado1, chbPermisoModificarEmpleado1, chbPermisoEliminarEmpleado1, chbPermisoConsultaEmpleado1,
            chbPermisoIngresoDep1, chbPermisoModificarDep1, chbPermisoEliminarDep1, chbPermisoConsultaDep1, chbPermisoIngresoConcepto1,
            chbPermisoModificarConcepto1, chbPermisoEliminarConcepto1, chbPermisoConsultaConcepto1, chbPermisoTabla1, chbPermisoBarras1, chbPermisoPastel1,
            chbPermisoIngresoUser, chbPermisoModificarUser, chbPermisoEliminarUser, chbPermisoConsultarUser,
            chbPermisoIngresoUserTipo, chbPermisoModificarUserTipo, chbPermisoEliminarUserTipo, chbPermisoConsultarUserTipo,
            chbPermisoIngresoEmpleado, chbPermisoModificarEmpleado, chbPermisoEliminarEmpleado, chbPermisoConsultaEmpleado,
            chbPermisoIngresoDep, chbPermisoModificarDep, chbPermisoEliminarDep, chbPermisoConsultaDep, chbPermisoIngresoConcepto,
            chbPermisoModificarConcepto, chbPermisoEliminarConcepto, chbPermisoConsultaConcepto, chbPermisoTabla, chbPermisoBarras, chbPermisoPastel, chbPermisoUsuario, chbPermisoNomina, cbPercepcion, cbDeduccion, cbISR};
        //Menus
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo2.png")));
        lblLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/logo2.png")));
        lblEmpleados.setBackground(new Color(color[0], color[1], color[2]));
        lblConceptos.setBackground(new Color(color[0], color[1], color[2]));
        lblPuestos.setBackground(new Color(color[0], color[1], color[2]));
        lblDep.setBackground(new Color(color[0], color[1], color[2]));
        lblTabla.setBackground(new Color(color[0], color[1], color[2]));
        lbltipodeUsuario.setBackground(new Color(color[0], color[1], color[2]));
        lblIngresarUser.setBackground(new Color(color[0], color[1], color[2]));
        pnlMenuInicio.setBackground(new java.awt.Color(color[0], color[1], color[2]));
        pnlDetalles.setBackground(new java.awt.Color(255, 255, 255));

        pnlContacto.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalles1.setBackground(new java.awt.Color(255, 255, 255));

        pnlContacto1.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetalles2.setBackground(new java.awt.Color(255, 255, 255));

        pnlContacto2.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenuUsuarios.setBackground(new java.awt.Color(color[0], color[1], color[2]));
        pnlMenu.setBackground(new java.awt.Color(color[0], color[1], color[2]));
        cbTipo.setBackground(new java.awt.Color(255, 255, 255));
        cbVertical.setBackground(new java.awt.Color(255, 255, 255));
        cbHorizontal.setBackground(new java.awt.Color(255, 255, 255));
        cbLineal.setBackground(new java.awt.Color(255, 255, 255));
        cbPastel.setBackground(new java.awt.Color(255, 255, 255));

        //Ingreso Usuarios
        /* pnlNombreDeUsuario.setBackground(new java.awt.Color(231, 231, 231));
        pnlID.setBackground(new java.awt.Color(231, 231, 231));

        pnlContraseña.setBackground(new java.awt.Color(231, 231, 231));
        pnlConfirmar.setBackground(new java.awt.Color(231, 231, 231));
        pnlMail.setBackground(new java.awt.Color(231, 231, 231));
        pnlTelefono.setBackground(new java.awt.Color(231, 231, 231));
        txtNombreDeUsuario.setBackground(new java.awt.Color(231, 231, 231));
        txtIDUsuario.setBackground(new java.awt.Color(231, 231, 231));

        txtContraseñaUser.setBackground(new java.awt.Color(231, 231, 231));
        txtConfirmarUser.setBackground(new java.awt.Color(231, 231, 231));
        txtMail.setBackground(new java.awt.Color(231, 231, 231));
        txtTelefono.setBackground(new java.awt.Color(231, 231, 231));
        //Modificar User
        pnlBuscarIDModificar.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDModificar.setBackground(new java.awt.Color(231, 231, 231));
        //Eliminar User
        pnlBuscarIDEliminar.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDEliminar.setBackground(new java.awt.Color(231, 231, 231));
        //Consulta User
        pnlBuscarIDConsulta.setBackground(new java.awt.Color(231, 231, 231));
        txtBuscarIDConsulta.setBackground(new java.awt.Color(231, 231, 231));*/
        //Panels de contenido
        //Menus bar
        pnlMenu_barUser.setBackground(new java.awt.Color(255, 255, 255));

        //Contenido de Inicio
        pnlBienvenida.setBackground(new java.awt.Color(255, 255, 255));
        //Contenido de Usuarios
        pnlIngreso_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificar_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminar_Usuarios.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsulta_Usuarios.setBackground(new java.awt.Color(255, 255, 255));

        chbPorNombreUsuario.setBackground(new java.awt.Color(255, 255, 255));
        chbPorId.setBackground(new java.awt.Color(255, 255, 255));
        chbPorIdConcepto.setBackground(new java.awt.Color(255, 255, 255));

        for (int i = 0; i < 51; i++) {
            Permisos[i].setBackground(new java.awt.Color(255, 255, 255));
        }

        //Contenido de Tipos Usuarios
        pnlIngreso_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificar_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminar_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsulta_UsuariosTipo.setBackground(new java.awt.Color(255, 255, 255));
        //Contenido de Nomina de Empleados
        pnlInicio.setBackground(new java.awt.Color(255, 255, 255));
        pnlInicio1.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlPuestos.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaPuesto.setBackground(new java.awt.Color(255, 255, 255));
        pnlDepartamentos.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaDep.setBackground(new java.awt.Color(255, 255, 255));
        pnlConceptos.setBackground(new java.awt.Color(255, 255, 255));
        pnlIngresoConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlModificarConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlEliminarConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlConsultaConcepto.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabla.setBackground(new java.awt.Color(255, 255, 255));

        //  Titulos de Inicio
        lblUsernameInicio.setForeground(new Color(44, 62, 80));
        //  lblBienvenida.setForeground(new Color(44, 62, 80));
        //Titulos de Usuarios
        btnInicioUsuarios.setForeground(new java.awt.Color(44, 62, 80));
        // lblUsername.setForeground(new Color(44, 62, 80));
        lblIngresoUser.setForeground(new Color(44, 62, 80));
        lblModificarUser.setForeground(new Color(44, 62, 80));
        lblEliminarUser.setForeground(new Color(44, 62, 80));
        lblConsultaUser.setForeground(new Color(44, 62, 80));
        //Titlos de Tipo de usuario
        btnTipodeUsuario.setForeground(new java.awt.Color(44, 62, 80));
        lblIngresoUserTipo.setForeground(new Color(44, 62, 80));
        lblModificarUserTipo.setForeground(new Color(44, 62, 80));
        lblEliminarUserTipo.setForeground(new Color(44, 62, 80));
        lblConsultaUserTipo.setForeground(new Color(44, 62, 80));
        //Titulos de Nomina de Empleados
        // lblUsernameNomina.setForeground(new Color(44, 62, 80));
        lblIngreso_NominaEmpleado.setForeground(new Color(44, 62, 80));
        lblModificar_NominaEmpleado.setForeground(new Color(44, 62, 80));
        lblEliminar_NominaEmpleado.setForeground(new Color(44, 62, 80));
        lblConsulta_NominaEmpleado.setForeground(new Color(44, 62, 80));
        lblIngreso_NominaEmpleado1.setForeground(new Color(44, 62, 80));
        lblModificar_NominaEmpleado1.setForeground(new Color(44, 62, 80));
        lblEliminar_NominaEmpleado1.setForeground(new Color(44, 62, 80));
        lblConsulta_NominaEmpleado1.setForeground(new Color(44, 62, 80));
        lblIngreso_NominaEmpleado2.setForeground(new Color(44, 62, 80));
        lblModificar_NominaEmpleado2.setForeground(new Color(44, 62, 80));
        lblEliminar_NominaEmpleado2.setForeground(new Color(44, 62, 80));
        lblConsulta_NominaEmpleado2.setForeground(new Color(44, 62, 80));
        lblIngreso_NominaEmpleado3.setForeground(new Color(44, 62, 80));
        lblModificar_NominaEmpleado3.setForeground(new Color(44, 62, 80));
        lblEliminar_NominaEmpleado3.setForeground(new Color(44, 62, 80));
        lblConsulta_NominaEmpleado3.setForeground(new Color(44, 62, 80));

        lblTabla_Nomina.setForeground(new Color(44, 62, 80));
        btnEmpleados.setForeground(new java.awt.Color(44, 62, 80));
        btnPuestos.setForeground(new java.awt.Color(44, 62, 80));
        btnDepartamentos.setForeground(new java.awt.Color(44, 62, 80));
        btnConceptos.setForeground(new java.awt.Color(44, 62, 80));
        btnTabla.setForeground(new java.awt.Color(44, 62, 80));

        lblClaro.setVisible(false);
        lblOscuro.setVisible(true);
    }//GEN-LAST:event_lblClaroMouseClicked

    private void btnOpsion_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarActionPerformed
        pnlOpciones.setVisible(false);
        btnMantenimientoUsuarios.setVisible(false);
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(pnlIngreso_Usuarios);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
    }//GEN-LAST:event_btnOpsion_IngresarActionPerformed

    private void btnOpsion_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarActionPerformed
        pnlOpciones.setVisible(false);
        btnMantenimientoUsuarios.setVisible(false);
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(jspnlModificar_Usuarios);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        if (!(tblUsuariosModificar.isVisible())) {
            tblUsuariosModificar.setVisible(true);
        }


    }//GEN-LAST:event_btnOpsion_ModificarActionPerformed

    private void btnOpsion_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarActionPerformed
        pnlOpciones.setVisible(false);
        btnMantenimientoUsuarios.setVisible(false);
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(pnlEliminar_Usuarios);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
    }//GEN-LAST:event_btnOpsion_EliminarActionPerformed

    private void btnOpsion_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultarActionPerformed
        pnlOpciones.setVisible(false);
        btnMantenimientoUsuarios.setVisible(false);
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(pnlConsulta_Usuarios);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();


    }//GEN-LAST:event_btnOpsion_ConsultarActionPerformed

    private void btnRegistrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseMoved
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Registrar2.png")));
    }//GEN-LAST:event_btnRegistrarMouseMoved

    private void btnRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseExited
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Plataforma/Registrar.png")));
    }//GEN-LAST:event_btnRegistrarMouseExited

    private void btnAjustesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseMoved

    }//GEN-LAST:event_btnAjustesMouseMoved

    private void btnAjustesMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMouseDragged

    }//GEN-LAST:event_btnAjustesMouseDragged

    private void pnlSubOpcionTemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSubOpcionTemaMouseClicked

    }//GEN-LAST:event_pnlSubOpcionTemaMouseClicked

    private void btnTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemasActionPerformed
        theme = false;
        pnlTemas.setVisible(false);
        pnlTemas.setVisible(true);
        if (color[0] == 24) {
            lblClaro.setVisible(false);
            lblClaro.setVisible(true);
        } else if (color[0] == 222) {
            lblOscuro.setVisible(false);
            lblOscuro.setVisible(true);
        }
        pnlSubOpcionTema.setVisible(false);

        if (graficas == true) {
            pnlizquierdo.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 160, -1));
            pnlizquierdo.add(btnGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, -1));
            pnlizquierdo.add(pnlGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, -1));
        } else {
            pnlizquierdo.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 160, -1));
            pnlizquierdo.add(btnGrafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
        }


    }//GEN-LAST:event_btnTemasActionPerformed

    private void btnTemasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTemasMouseExited

    }//GEN-LAST:event_btnTemasMouseExited

    private void btnBarrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBarrasMouseExited

    }//GEN-LAST:event_btnBarrasMouseExited

    private void btnBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarrasActionPerformed
        if (theme == true) {
            pnlGraph.setVisible(false);
            pnlizquierdo.add(pnlGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            graficas = false;

        } else {
            pnlGraph.setVisible(false);
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            graficas = false;
        }


    }//GEN-LAST:event_btnBarrasActionPerformed

    private void pnlSubOpcionGraficaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSubOpcionGraficaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlSubOpcionGraficaMouseClicked

    private void btnPastelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPastelMouseExited

    }//GEN-LAST:event_btnPastelMouseExited

    private void btnPastelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPastelActionPerformed
        if (theme == true) {
            pnlGraph.setVisible(false);
            pnlizquierdo.add(pnlGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            graficas = false;

        } else {
            pnlGraph.setVisible(false);
            pnlizquierdo.add(btnCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 160, -1));
            pnlizquierdo.add(btnPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 160, -1));
            pnlizquierdo.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 160, -1));
            graficas = false;
        }
    }//GEN-LAST:event_btnPastelActionPerformed

    private void pnlSubOpcionGrafica2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSubOpcionGrafica2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlSubOpcionGrafica2MouseClicked

    private void btnGraficaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGraficaMouseMoved

    }//GEN-LAST:event_btnGraficaMouseMoved

    private void pnlGraphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlGraphMouseExited

    }//GEN-LAST:event_pnlGraphMouseExited

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        Login2 PF = new Login2();
        PF.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel20MousePressed

    private void lblLogoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseMoved

    }//GEN-LAST:event_lblLogoMouseMoved

    private void lblLogoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoMouseExited

    }//GEN-LAST:event_lblLogoMouseExited

    private void btnEliminarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserActionPerformed
        int cant = CantidadDeRegistros("Usuarios");
        String name = "";
        String nombredetxt = txtBuscarIDEliminar.getText().trim();
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from Usuarios where ID_Usuario = ?");

            for (int i = 0; i < cant; i++) {
                if (txtBuscarIDEliminar.getText().trim().equals(BasedeDatosUsuarios[i][0])) {
                    name = BasedeDatosUsuarios[i][2];
                    i = cant;
                }
            }

            pst.setString(1, txtBuscarIDEliminar.getText().trim());
            pst.executeUpdate();

            GuardarBasedeDatosMatrizUsuarios("Usuarios", 8, NombresColumnasUsuarios);
            AgregarItemsdeTipo();
            Graficar();
            BorraImagen(name + ".png");
            tblUsuariosModificar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            tblUsuariosEliminar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            tblUsuariosConsulta.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));
            Icon eliminado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Eliminado.png"));
            JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO - LOS DATOS ELIMINADOS EN LA BASE DE DATOS", "ELIMINADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, eliminado);
            DesktopNotify.showDesktopMessage("Eliminado", "El Usuario fue Eliminado con Exito", DesktopNotify.SUCCESS, 7000L);
            btnEliminarUser.setEnabled(false);
            txtBuscarIDEliminar.setText("");
            if (nombredetxt.equals(DatosPersonales2[0])) {
                Login2 PF = new Login2();
                PF.setVisible(true);
                dispose();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserActionPerformed

    private void btnModificarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUserActionPerformed
        int cant = CantidadDeRegistros("Usuarios");
        try {
            String categoria = (String) cbTipo1.getSelectedItem();
            String ID = "'" + txtBuscarIDModificar.getText().trim() + "'";
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Usuarios set ID_Usuario = ?, ID_Concepto = ?, NombreUsuario = ?, Contraseña = ?, CorreoElectronico = ?, Telefono = ?, Direcciones = ?, Sesiones = ? where ID_Usuario = " + ID);

            pst.setString(1, txtIDUsuarioModificar.getText().trim());
            pst.setString(2, categoria);
            pst.setString(3, txtNombreDeModificar.getText().trim());
            pst.setString(4, txtContraseñaUserModificar.getText().trim());
            pst.setString(5, txtMailModificar.getText().trim());
            pst.setString(6, txtTelefonoModificar.getText().trim());
            pst.setString(7, addr.getHostAddress() + " " + hostname);
            pst.setString(8, lblVeces.getText().trim());

            pst.executeUpdate();
            String name = "";
            for (int i = 0; i < cant; i++) {
                if (txtBuscarIDModificar.getText().trim().equals(BasedeDatosUsuarios[i][0])) {
                    name = BasedeDatosUsuarios[i][2];
                    i = cant;
                }
            }
            BorraImagen(name + ".png");
            GenerarQR(txtIDUsuarioModificar.getText().trim(), txtNombreDeModificar.getText().trim());

            lblQR.setIcon(null);;
            lblnombre.setText("");
            txtIDUsuarioModificar.setText("");
            txtNombreDeModificar.setText("");
            txtContraseñaUserModificar.setText("");
            txtConfirmarUserModficar.setText("");
            txtMailModificar.setText("");
            txtTelefonoModificar.setText("");

            GuardarBasedeDatosMatrizUsuarios("Usuarios", 8, NombresColumnasUsuarios);
            AgregarItemsdeTipo();
            tblUsuariosModificar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            tblUsuariosEliminar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            tblUsuariosConsulta.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));
            Icon modificado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Cambios.png"));
            JOptionPane.showMessageDialog(null, "USUARIO MODIFICADO - LOS DATOS CAMBIADOS EN LA BASE DE DATOS", "MODIFICADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, modificado);
            DesktopNotify.showDesktopMessage("Modificado", "El  Usuario fue Modificado con Exito", DesktopNotify.SUCCESS, 7000L);
            btnModificarUser.setEnabled(false);

        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Error", "ID y/o Nombre de Tipo ya Registrado, o campos vacios", DesktopNotify.ERROR, 5000L);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnModificarUserActionPerformed

    private void btnTipodeUsuarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipodeUsuarioMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipodeUsuarioMouseMoved

    private void btnTipodeUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipodeUsuarioMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipodeUsuarioMouseExited

    private void btnTipodeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipodeUsuarioActionPerformed
        /*        pnlOpcionesTipo.setVisible(true);
        pnlOpciones.setVisible(true);
        pnlOpciones.setVisible(false);*/

    }//GEN-LAST:event_btnTipodeUsuarioActionPerformed

    private void btnOpsion_ConsultarTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultarTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ConsultarTipoMouseClicked

    private void btnOpsion_ConsultarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultarTipoActionPerformed
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(pnlConsulta_UsuariosTipo);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
    }//GEN-LAST:event_btnOpsion_ConsultarTipoActionPerformed

    private void btnOpsion_IngresarTipoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarTipoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarTipoMouseMoved

    private void btnOpsion_IngresarTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarTipoMouseClicked

    private void btnOpsion_IngresarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarTipoActionPerformed
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(pnlIngreso_UsuariosTipo);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
    }//GEN-LAST:event_btnOpsion_IngresarTipoActionPerformed

    private void btnOpsion_ModificarTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarTipoMouseClicked

    private void btnOpsion_ModificarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarTipoActionPerformed
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(jspModificar_UsuariosTipo);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();

    }//GEN-LAST:event_btnOpsion_ModificarTipoActionPerformed

    private void btnOpsion_EliminarTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarTipoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarTipoMouseClicked

    private void btnOpsion_EliminarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarTipoActionPerformed
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(pnlEliminar_UsuariosTipo);
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
    }//GEN-LAST:event_btnOpsion_EliminarTipoActionPerformed

    private void pnlOpcionesTipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOpcionesTipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlOpcionesTipoMouseExited

    private void btnRegistrarTipoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarTipoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarTipoMouseMoved

    private void btnRegistrarTipoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarTipoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarTipoMouseExited

    private void btnModificarUserTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarUserTipoActionPerformed
        JCheckBox[] Permisos = {chbPermisoIngresoUser1, chbPermisoModificarUser1, chbPermisoEliminarUser1, chbPermisoConsultarUser1,
            chbPermisoIngresoUserTipo1, chbPermisoModificarUserTipo1, chbPermisoEliminarUserTipo1, chbPermisoConsultarUserTipo1,
            chbPermisoIngresoEmpleado1, chbPermisoModificarEmpleado1, chbPermisoEliminarEmpleado1, chbPermisoConsultaEmpleado1,
            chbPermisoIngresoDep1, chbPermisoModificarDep1, chbPermisoEliminarDep1, chbPermisoConsultaDep1, chbPermisoIngresoConcepto1,
            chbPermisoModificarConcepto1, chbPermisoEliminarConcepto1, chbPermisoConsultaConcepto1, chbPermisoTabla1, chbPermisoBarras1, chbPermisoPastel1};

        try {
            String ID = "'" + txtBuscarIDModificarTipo.getText().trim() + "'";

            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Concepto set ID_Concepto = ?, NombreConcepto = ?, IngresarUser = ?, ModificarUser = ?, EliminarUser = ?, ConsultarUser = ?, IngresarUserTipo = ?, ModificarUserTipo = ?,EliminarUserTipo = ?, ConsultarUserTipo = ?, IngresarEmpleado = ?, ModificarEmpleado = ?, EliminarEmpleado = ?, ConsultarEmpleado = ?,IngresarDepartamento = ?, ModificarDepartamento = ?, EliminarDepartamento = ?, ConsultarDepartamento = ?, IngresarConcepto = ?, ModificarConcepto = ?, EliminarConcepto = ?, ConsultarConcepto = ?, Tabla = ?, Barras = ?, Pastel = ? where ID_Concepto = " + ID);
            pst.setString(1, txtIDTipoUsuarioModificar.getText().trim());
            pst.setString(2, txtNombreDeTipoUsuarioModificar.getText().trim());

            for (int i = 0; i < 23; i++) {
                if (Permisos[i].isSelected()) {

                    pst.setString(i + 3, "1");

                } else {
                    pst.setString(i + 3, "0");
                }
            }

            pst.executeUpdate();
            txtIDTipoUsuarioModificar.setText("");
            txtNombreDeTipoUsuarioModificar.setText("");
            GuardarBasedeDatosMatriz("Concepto", 25, NombresColumnas);
            AgregarItemsdeTipo();
            tblUsuariosModificarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
            tblUsuariosEliminarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
            tblTipodeUsuarioConsulta.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
            Icon modificado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Cambios.png"));
            JOptionPane.showMessageDialog(null, "TIPO USUARIO MODIFICADO - LOS DATOS CAMBIADOS EN LA BASE DE DATOS", "MODIFICADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, modificado);
            DesktopNotify.showDesktopMessage("Modificado", "El Tipo de Usuario fue Modificado con Exito", DesktopNotify.SUCCESS, 7000L);
            btnModificarUserTipo.setEnabled(false);
            for (int i = 0; i < 23; i++) {
                if (Permisos[i].isSelected()) {

                    Permisos[i].setSelected(false);

                }

            }
        } catch (Exception e) {
            // e.printStackTrace();
            DesktopNotify.showDesktopMessage("Error", "ID y/o Nombre de Tipo ya Registrado, o campos vacios", DesktopNotify.ERROR, 5000L);
        }


    }//GEN-LAST:event_btnModificarUserTipoActionPerformed

    private void btnEliminarUserTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserTipoActionPerformed
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from Concepto where ID_Concepto = ?");

            pst.setString(1, txtBuscarIDEliminarTipo.getText().trim());
            pst.executeUpdate();
            GuardarBasedeDatosMatriz("Concepto", 25, NombresColumnas);
            AgregarItemsdeTipo();
            tblUsuariosModificarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
            tblUsuariosEliminarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
            tblTipodeUsuarioConsulta.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
            Icon eliminado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Eliminado.png"));
            JOptionPane.showMessageDialog(null, "TIPO USUARIO ELIMINADO - LOS DATOS ELIMINADOS EN LA BASE DE DATOS", "ELIMINADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, eliminado);
            DesktopNotify.showDesktopMessage("Eliminado", "El Tipo de Usuario fue Eliminado con Exito", DesktopNotify.SUCCESS, 7000L);
            btnEliminarUserTipo.setEnabled(true);
            txtBuscarIDEliminarTipo.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserTipoActionPerformed

    private void btnRegistrarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarTipoActionPerformed
        JCheckBox[] Permisos = {chbPermisoIngresoUser, chbPermisoModificarUser, chbPermisoEliminarUser, chbPermisoConsultarUser,
            chbPermisoIngresoUserTipo, chbPermisoModificarUserTipo, chbPermisoEliminarUserTipo, chbPermisoConsultarUserTipo,
            chbPermisoIngresoEmpleado, chbPermisoModificarEmpleado, chbPermisoEliminarEmpleado, chbPermisoConsultaEmpleado,
            chbPermisoIngresoDep, chbPermisoModificarDep, chbPermisoEliminarDep, chbPermisoConsultaDep, chbPermisoIngresoConcepto,
            chbPermisoModificarConcepto, chbPermisoEliminarConcepto, chbPermisoConsultaConcepto, chbPermisoTabla, chbPermisoBarras, chbPermisoPastel};
        String idtipo = txtIDTipoUsuario.getText().trim();
        String nombretipo = txtNombreDeTipoUsuario.getText().trim();
        if (BuscarRegistroDB(idtipo, "Concepto", "ID_Concepto") == false && BuscarRegistroPorNombre(nombretipo, "Concepto", "NombreConcepto") == false) {
            try {
                Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                PreparedStatement pst = cn.prepareStatement("insert into Concepto values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                pst.setString(1, txtIDTipoUsuario.getText().trim());
                pst.setString(2, txtNombreDeTipoUsuario.getText().trim());

                for (int i = 0; i < 23; i++) {
                    if (Permisos[i].isSelected()) {
                        if (Permisos[i].isEnabled()) {
                            pst.setString(i + 3, "1");
                        } else {
                            pst.setString(i + 3, "0");
                        }

                    } else {
                        pst.setString(i + 3, "0");
                    }
                }

                pst.executeUpdate();

                txtIDTipoUsuario.setText("");
                txtNombreDeTipoUsuario.setText("");
                GuardarBasedeDatosMatriz("Concepto", 25, NombresColumnas);
                AgregarItemsdeTipo();
                tblUsuariosModificarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
                tblUsuariosEliminarTipo.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
                tblTipodeUsuarioConsulta.setModel(new DefaultTableModel(BasedeDatosTipos, NombresColumnas));
                for (int i = 0; i < 23; i++) {
                    if (Permisos[i].isSelected()) {
                        chbPermisoUsuario.setSelected(false);
                        chbPermisoNomina.setSelected(false);

                        Permisos[i].setSelected(false);
                        Permisos[i].setEnabled(false);
                    }

                }
                Icon Ingresado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Ingresado.png"));
                JOptionPane.showMessageDialog(null, "TIPO USUARIO REGISTRADO - LOS DATOS FUERON ANOTADOS EN LA BASE DE DATOS", "INGRESADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, Ingresado);
                DesktopNotify.showDesktopMessage("Registrado", "El Tipo de Usuario fue ingresado con Exito", DesktopNotify.SUCCESS, 7000L);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            DesktopNotify.showDesktopMessage("Error", "ID y/o Nombre de Tipo ya Registrado, o campos vacios", DesktopNotify.ERROR, 5000L);
            txtIDTipoUsuario.setText("");
            txtNombreDeTipoUsuario.setText("");
        }

    }//GEN-LAST:event_btnRegistrarTipoActionPerformed

    private void chbPermisoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPermisoUsuarioActionPerformed
        if (chbPermisoUsuario.isSelected()) {
            chbPermisoIngresoUser.setEnabled(true);
            chbPermisoModificarUser.setEnabled(true);
            chbPermisoEliminarUser.setEnabled(true);
            chbPermisoConsultarUser.setEnabled(true);
            chbPermisoIngresoUserTipo.setEnabled(true);
            chbPermisoModificarUserTipo.setEnabled(true);
            chbPermisoEliminarUserTipo.setEnabled(true);
            chbPermisoConsultarUserTipo.setEnabled(true);
        } else {

            chbPermisoIngresoUser.setEnabled(false);
            chbPermisoModificarUser.setEnabled(false);
            chbPermisoEliminarUser.setEnabled(false);
            chbPermisoConsultarUser.setEnabled(false);
            chbPermisoIngresoUserTipo.setEnabled(false);
            chbPermisoModificarUserTipo.setEnabled(false);
            chbPermisoEliminarUserTipo.setEnabled(false);
            chbPermisoConsultarUserTipo.setEnabled(false);
        }
    }//GEN-LAST:event_chbPermisoUsuarioActionPerformed

    private void chbPermisoNominaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPermisoNominaActionPerformed
        if (chbPermisoNomina.isSelected()) {
            chbPermisoIngresoEmpleado.setEnabled(true);
            chbPermisoModificarEmpleado.setEnabled(true);
            chbPermisoEliminarEmpleado.setEnabled(true);
            chbPermisoConsultaEmpleado.setEnabled(true);
            chbPermisoIngresoDep.setEnabled(true);
            chbPermisoModificarDep.setEnabled(true);
            chbPermisoEliminarDep.setEnabled(true);
            chbPermisoConsultaDep.setEnabled(true);
            chbPermisoIngresoConcepto.setEnabled(true);
            chbPermisoModificarConcepto.setEnabled(true);
            chbPermisoEliminarConcepto.setEnabled(true);
            chbPermisoConsultaConcepto.setEnabled(true);
            chbPermisoTabla.setEnabled(true);
            chbPermisoBarras.setEnabled(true);
            chbPermisoPastel.setEnabled(true);

        } else {
            chbPermisoIngresoEmpleado.setEnabled(false);
            chbPermisoModificarEmpleado.setEnabled(false);
            chbPermisoEliminarEmpleado.setEnabled(false);
            chbPermisoConsultaEmpleado.setEnabled(false);
            chbPermisoIngresoDep.setEnabled(false);
            chbPermisoModificarDep.setEnabled(false);
            chbPermisoEliminarDep.setEnabled(false);
            chbPermisoConsultaDep.setEnabled(false);
            chbPermisoIngresoConcepto.setEnabled(false);
            chbPermisoModificarConcepto.setEnabled(false);
            chbPermisoEliminarConcepto.setEnabled(false);
            chbPermisoConsultaConcepto.setEnabled(false);
            chbPermisoTabla.setEnabled(false);
            chbPermisoBarras.setEnabled(false);
            chbPermisoPastel.setEnabled(false);
        }
    }//GEN-LAST:event_chbPermisoNominaActionPerformed

    private void btnBuscarIDEModificarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIDEModificarTipoActionPerformed
        JCheckBox[] Permisos = {chbPermisoIngresoUser1, chbPermisoModificarUser1, chbPermisoEliminarUser1, chbPermisoConsultarUser1,
            chbPermisoIngresoUserTipo1, chbPermisoModificarUserTipo1, chbPermisoEliminarUserTipo1, chbPermisoConsultarUserTipo1,
            chbPermisoIngresoEmpleado1, chbPermisoModificarEmpleado1, chbPermisoEliminarEmpleado1, chbPermisoConsultaEmpleado1,
            chbPermisoIngresoDep1, chbPermisoModificarDep1, chbPermisoEliminarDep1, chbPermisoConsultaDep1, chbPermisoIngresoConcepto1,
            chbPermisoModificarConcepto1, chbPermisoEliminarConcepto1, chbPermisoConsultaConcepto1, chbPermisoTabla1, chbPermisoBarras1, chbPermisoPastel1};

        int Posicion = PosicionDeRegistros(txtBuscarIDModificarTipo.getText().trim(), BasedeDatosTipos);

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from Concepto where ID_Concepto = ?");
            pst.setString(1, txtBuscarIDModificarTipo.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                btnModificarUserTipo.setEnabled(true);

                txtIDTipoUsuarioModificar.setText(rs.getString("ID_Concepto"));
                txtNombreDeTipoUsuarioModificar.setText(rs.getString("NombreConcepto"));

                for (int j = 2; j < 25; j++) {
                    if (BasedeDatosTipos[Posicion][j].equals("1")) {
                        Permisos[j - 2].setSelected(true);
                    } else {
                        Permisos[j - 2].setSelected(false);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Tipo de usuario no registrado.");
                txtIDTipoUsuarioModificar.setText("");
                txtNombreDeTipoUsuarioModificar.setText("");
                for (int i = 0; i < 23; i++) {
                    if (Permisos[i].isSelected()) {

                        Permisos[i].setSelected(false);

                    }

                }
                btnModificarUserTipo.setEnabled(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarIDEModificarTipoActionPerformed

    private void txtBuscarIDEliminarTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIDEliminarTipoKeyTyped

        FiltrarPorIdTipos(txtBuscarIDEliminarTipo.getText().trim(), "Concepto", "ID_Concepto");


    }//GEN-LAST:event_txtBuscarIDEliminarTipoKeyTyped

    private void txtIDTipoUsuarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDTipoUsuarioCaretUpdate

    }//GEN-LAST:event_txtIDTipoUsuarioCaretUpdate

    private void txtBuscarIDEliminarTipoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarIDEliminarTipoCaretUpdate
        String valor = txtBuscarIDEliminarTipo.getText().trim();
        if (valor.isEmpty()) {

            tblUsuariosEliminarTipo.clearSelection();
            btnEliminarUserTipo.setEnabled(false);
        } else {
            for (int i = 0; i < tblUsuariosEliminarTipo.getRowCount(); i++) {
                if (tblUsuariosEliminarTipo.getValueAt(i, 0).equals(valor)) {
                    btnEliminarUserTipo.setEnabled(true);
                    tblUsuariosEliminarTipo.changeSelection(i, 0, false, false);
                    Icon icon = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NohayRegistros.png"));
                    JOptionPane.showMessageDialog(null, "ADVERTENCIA\n" + "SI ELIMINA UN TIPO DE USUARIOS, TODOS LOS USUARIOS RELACIONADOS CON EL\nSE CONSERVARAN PERO NO TEDRA NINGUN TIPO DE PERMISO PARA LA PLATAFORMA ES RECOMENDABLE MODIFICAR CADA USUARIO", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE, icon);
                }/*else{
                     btnEliminarUserTipo.setEnabled(false);
                }*/

            }
        }
    }//GEN-LAST:event_txtBuscarIDEliminarTipoCaretUpdate

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        String categoria = (String) cbTipo.getSelectedItem();
        if (BuscarRegistroDB(txtIDUsuario.getText().trim(), "Usuarios", "ID_Usuario") == false && BuscarRegistroPorNombre(txtNombreDeUsuario.getText().trim(), "Usuarios", "NombreUsuario") == false) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                String hostname = addr.getHostName();
                Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                PreparedStatement pst = cn.prepareStatement("insert into Usuarios values(?,?,?,?,?,?,?,?)");

                pst.setString(1, txtIDUsuario.getText().trim());
                pst.setString(2, categoria);
                pst.setString(3, txtNombreDeUsuario.getText().trim());
                pst.setString(4, txtContraseñaUser.getText().trim());
                pst.setString(5, txtMail.getText().trim());
                pst.setString(6, txtTelefono.getText().trim());
                pst.setString(7, addr.getHostAddress() + " " + hostname);
                pst.setString(8, "0");

                pst.executeUpdate();

                GuardarBasedeDatosMatrizUsuarios("Usuarios", 8, NombresColumnasUsuarios);
                AgregarItemsdeTipo();
                Graficar();
                tblUsuariosModificar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
                tblUsuariosEliminar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
                tblUsuariosConsulta.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
                GenerarQR(txtIDUsuario.getText().trim(), txtNombreDeUsuario.getText().trim());

                Icon Ingresado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Ingresado.png"));
                JOptionPane.showMessageDialog(null, "USUARIO REGISTRADO - LOS DATOS FUERON ANOTADOS EN LA BASE DE DATOS", "INGRESADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, Ingresado);
                DesktopNotify.showDesktopMessage("Registrado", "El Usuario fue ingresado con Exito", DesktopNotify.SUCCESS, 7000L);
                txtContraseñaUser.setForeground(new Color(153, 153, 153));
                txtConfirmarUser.setForeground(new Color(153, 153, 153));
                txtIDUsuario.setText("");
                txtNombreDeUsuario.setText("");
                txtContraseñaUser.setText("");
                txtConfirmarUser.setText("");
                txtMail.setText("");
                txtTelefono.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            DesktopNotify.showDesktopMessage("Error", "ID y/o Nombre  ya Registrado, o campos vacios", DesktopNotify.ERROR, 5000L);

        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

<<<<<<< HEAD
    private void txtConfirmarUser2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarUser2KeyReleased
        if (txtContraseñaUser.getText().trim().equals(txtConfirmarUser2.getText().trim()) && (!(txtContraseñaUser.getText().trim().equals("")) && !(txtConfirmarUser2.getText().trim().equals("")))) {
            btnRegistrar.setEnabled(true);
            txtContraseñaUser.setForeground(new Color(0, 204, 102));
            txtConfirmarUser.setForeground(new Color(0, 204, 102));

        } else {
            btnRegistrar.setEnabled(false);
            txtContraseñaUser.setForeground(Color.RED);
            txtConfirmarUser.setForeground(Color.RED);
        }
    }//GEN-LAST:event_txtConfirmarUser2KeyReleased

=======
>>>>>>> ec692187cadd910ccf59b3a1bffc09200ebac154
    private void txtConfirmarUserModficarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarUserModficarKeyReleased
        if (txtContraseñaUserModificar.getText().trim().equals(txtConfirmarUserModficar.getText().trim()) && (!(txtContraseñaUserModificar.getText().trim().equals("")) && !(txtConfirmarUserModficar.getText().trim().equals("")))) {
            btnModificarUser.setEnabled(true);
            txtContraseñaUserModificar.setForeground(new Color(0, 204, 102));
            txtConfirmarUserModficar.setForeground(new Color(0, 204, 102));

        } else {
            btnModificarUser.setEnabled(false);
            txtContraseñaUserModificar.setForeground(Color.RED);
            txtConfirmarUserModficar.setForeground(Color.RED);
        }
    }//GEN-LAST:event_txtConfirmarUserModficarKeyReleased

    private void btnBuscarIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIDActionPerformed

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from Usuarios where ID_Usuario = ?");
            pst.setString(1, txtBuscarIDModificar.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                txtIDUsuarioModificar.setText(rs.getString("ID_Usuario"));
                txtNombreDeModificar.setText(rs.getString("NombreUsuario"));
                txtContraseñaUserModificar.setText(rs.getString("Contraseña"));
                txtConfirmarUserModficar.setText(rs.getString("Contraseña"));
                txtTelefonoModificar.setText(rs.getString("Telefono"));
                txtMailModificar.setText(rs.getString("CorreoElectronico"));
                cbTipo1.setSelectedItem(rs.getString("ID_Concepto"));
                lblVeces.setText(rs.getString("Sesiones"));
                String nombre = rs.getString("NombreUsuario");
                lblQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/" + nombre + ".png")));
                lblnombre.setText(nombre);
                if ((txtContraseñaUserModificar.getText().trim().equals(txtConfirmarUserModficar.getText().trim())) && (!(txtContraseñaUserModificar.getText().trim().equals("")) && !(txtConfirmarUserModficar.getText().trim().equals("")))) {
                    btnModificarUser.setEnabled(true);
                    txtContraseñaUserModificar.setForeground(new Color(0, 204, 102));
                    txtConfirmarUserModficar.setForeground(new Color(0, 204, 102));

                } else {
                    btnModificarUser.setEnabled(false);
                    txtContraseñaUserModificar.setForeground(Color.RED);
                    txtConfirmarUserModficar.setForeground(Color.RED);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de usuario no registrado.");
                txtIDUsuarioModificar.setText("");
                txtNombreDeModificar.setText("");
                txtContraseñaUserModificar.setText("");
                txtConfirmarUserModficar.setText("");
                txtTelefonoModificar.setText("");
                txtMailModificar.setText("");
                btnModificarUser.setEnabled(false);
                lblQR.setIcon(null);
                lblnombre.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarIDActionPerformed

    private void txtContraseñaUserModificarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaUserModificarKeyReleased
        if ((txtContraseñaUserModificar.getText().trim().equals(txtConfirmarUserModficar.getText().trim())) && (!(txtContraseñaUserModificar.getText().trim().equals("")) && !(txtConfirmarUserModficar.getText().trim().equals("")))) {
            btnModificarUser.setEnabled(true);
            txtContraseñaUserModificar.setForeground(new Color(0, 204, 102));
            txtConfirmarUserModficar.setForeground(new Color(0, 204, 102));

        } else {
            btnModificarUser.setEnabled(false);
            txtContraseñaUserModificar.setForeground(Color.RED);
            txtConfirmarUserModficar.setForeground(Color.RED);
        }
    }//GEN-LAST:event_txtContraseñaUserModificarKeyReleased

    private void txtBuscarIDModificarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarIDModificarCaretUpdate
        String valor = txtBuscarIDModificar.getText().trim();

        if (valor.isEmpty()) {
            tblUsuariosModificar.clearSelection();
        } else {
            for (int i = 0; i < tblUsuariosModificar.getRowCount(); i++) {
                if (tblUsuariosModificar.getValueAt(i, 0).equals(valor)) {

                    tblUsuariosModificar.changeSelection(i, 0, false, false);
                }
            }
        }
    }//GEN-LAST:event_txtBuscarIDModificarCaretUpdate

    private void txtBuscarIDModificarTipoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarIDModificarTipoCaretUpdate
        String valor = txtBuscarIDModificarTipo.getText().trim();

        if (valor.isEmpty()) {
            tblUsuariosModificarTipo.clearSelection();
        } else {
            for (int i = 0; i < tblUsuariosModificarTipo.getRowCount(); i++) {
                if (tblUsuariosModificarTipo.getValueAt(i, 0).equals(valor)) {

                    tblUsuariosModificarTipo.changeSelection(i, 0, false, false);
                }
            }
        }
    }//GEN-LAST:event_txtBuscarIDModificarTipoCaretUpdate

    private void txtBuscarIDEliminarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarIDEliminarCaretUpdate
        String valor = txtBuscarIDEliminar.getText().trim();
        int cant = CantidadDeRegistros("Usuarios");

        String name = "";
        for (int i = 0; i < cant; i++) {
            if (txtBuscarIDEliminar.getText().trim().equals(BasedeDatosUsuarios[i][0])) {
                name = BasedeDatosUsuarios[i][2];

                i = cant;
            }
        }
        if (name.equals("")) {
            lblQR1.setIcon(null);
            lblnombre1.setText("");
        } else {
            lblQR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/" + name + ".png")));
            lblnombre1.setText(name);
        }
        if (txtBuscarIDEliminar.getText().trim().equals(DatosPersonales2[0])) {
            Icon Aviso = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NoHayRegistros.png"));
            JOptionPane.showMessageDialog(null, "SI ELIMINA SU PERFIL SE CERRARA SESION AUTOMATICAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE, Aviso);
        }

        if (valor.isEmpty()) {

            tblUsuariosEliminar.clearSelection();
            btnEliminarUser.setEnabled(false);
        } else {
            for (int i = 0; i < tblUsuariosEliminar.getRowCount(); i++) {
                if (tblUsuariosEliminar.getValueAt(i, 0).equals(valor)) {

                    btnEliminarUser.setEnabled(true);
                    tblUsuariosEliminar.changeSelection(i, 0, false, false);
                }
                /* else{
                      btnEliminarUser.setEnabled(false);
                }*/
            }
        }
    }//GEN-LAST:event_txtBuscarIDEliminarCaretUpdate

    private void txtBuscarIDEliminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIDEliminarKeyTyped
        FiltrarPorIdUsuarios(txtBuscarIDEliminar.getText().trim(), "Usuarios", "ID_Usuario");

    }//GEN-LAST:event_txtBuscarIDEliminarKeyTyped

    private void cbTipoConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoConsultaActionPerformed

        String categoria = (String) cbTipoConsulta.getSelectedItem();
        if (categoria == null) {
            categoria = " ";

        }
        FiltrarPorIdConsultaUsuarios(categoria, "Usuarios", "ID_Concepto");


    }//GEN-LAST:event_cbTipoConsultaActionPerformed

    private void txtBuscarIDConsultaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIDConsultaKeyTyped
        String Filtro = "ID_Usuario";
        if (chbPorId.isSelected()) {
            Filtro = "ID_Usuario";

        } else if (chbPorNombreUsuario.isSelected()) {
            Filtro = "NombreUsuario";

        } else if (chbPorIdConcepto.isSelected()) {
            Filtro = "ID_Concepto";
        }
        FiltrarPorIdConsultaUsuarios(txtBuscarIDConsulta.getText().trim(), "Usuarios", Filtro);
    }//GEN-LAST:event_txtBuscarIDConsultaKeyTyped

    private void txtBuscarIDConsultaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarIDConsultaCaretUpdate
        String valor = txtBuscarIDConsulta.getText().trim();
        int Columna = 0;
        if (chbPorId.isSelected()) {
            Columna = 0;

        } else if (chbPorNombreUsuario.isSelected()) {

            Columna = 2;
        } else if (chbPorIdConcepto.isSelected()) {
            Columna = 1;
        }
        if (Columna == 0) {

            int cant = CantidadDeRegistros("Usuarios");

            String name = "";
            for (int i = 0; i < cant; i++) {
                if (txtBuscarIDConsulta.getText().trim().equals(BasedeDatosUsuarios[i][0])) {
                    name = BasedeDatosUsuarios[i][2];

                    i = cant;
                }
            }
            if (name.equals("")) {
                lblQR2.setIcon(null);
                lblnombre2.setText("");
            } else {
                lblQR2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/" + name + ".png")));
                lblnombre2.setText(name);
            }
        }

        if (valor.isEmpty()) {

            tblUsuariosConsulta.clearSelection();

        } else {
            for (int i = 0; i < tblUsuariosConsulta.getRowCount(); i++) {
                if (tblUsuariosConsulta.getValueAt(i, Columna).equals(valor)) {

                    tblUsuariosConsulta.changeSelection(i, Columna, false, false);
                }

            }
        }
    }//GEN-LAST:event_txtBuscarIDConsultaCaretUpdate

    private void txtBuscarIDConsultaTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIDConsultaTipoKeyTyped
        FiltrarPorIdConsultaTipos(txtBuscarIDConsultaTipo.getText().trim(), "Concepto", "ID_Concepto");
    }//GEN-LAST:event_txtBuscarIDConsultaTipoKeyTyped

    private void txtBuscarIDConsultaTipoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarIDConsultaTipoCaretUpdate
        String valor = txtBuscarIDConsultaTipo.getText().trim();
        if (valor.isEmpty()) {

            tblTipodeUsuarioConsulta.clearSelection();

        } else {
            for (int i = 0; i < tblTipodeUsuarioConsulta.getRowCount(); i++) {
                if (tblTipodeUsuarioConsulta.getValueAt(i, 0).equals(valor)) {

                    tblTipodeUsuarioConsulta.changeSelection(i, 0, false, false);
                }

            }
        }
    }//GEN-LAST:event_txtBuscarIDConsultaTipoCaretUpdate

    private void chbPorNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPorNombreUsuarioActionPerformed
        if (chbPorNombreUsuario.isSelected()) {
            chbPorId.setSelected(false);
            chbPorIdConcepto.setSelected(false);
        } else {
            chbPorNombreUsuario.setSelected(true);
        }

    }//GEN-LAST:event_chbPorNombreUsuarioActionPerformed

    private void chbPorIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPorIdActionPerformed
        if (chbPorId.isSelected()) {
            chbPorNombreUsuario.setSelected(false);
            chbPorIdConcepto.setSelected(false);
        } else {
            chbPorId.setSelected(true);
        }
    }//GEN-LAST:event_chbPorIdActionPerformed

    private void chbPorIdConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbPorIdConceptoActionPerformed
        if (chbPorIdConcepto.isSelected()) {
            chbPorNombreUsuario.setSelected(false);
            chbPorId.setSelected(false);
        } else {
            chbPorIdConcepto.setSelected(true);
        }
    }//GEN-LAST:event_chbPorIdConceptoActionPerformed

    private void btnOpsion_IngresarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarEmpleadoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlEmpleados);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlEmpleados.removeAll();
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
        pnlEmpleados.add(pnlIngresoEmpleado);
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
        cbPuesto.removeAllItems();
        cbDepartamento.removeAllItems();

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select id_puesto from Puesto");
            ResultSet rs = pst.executeQuery();

            PreparedStatement pst2 = cn.prepareStatement("select id_departamento from Departamento");
            ResultSet rs2 = pst2.executeQuery();

            while (rs.next()) {
                cbPuesto.addItem(rs.getString("id_puesto"));
            }

            while (rs2.next()) {
                cbDepartamento.addItem(rs2.getString("id_departamento"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnOpsion_IngresarEmpleadoActionPerformed

    private void cbVerticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVerticalActionPerformed
        if (cbVertical.isSelected()) {
            cbHorizontal.setSelected(false);
            cbLineal.setSelected(false);
            cbPastel.setSelected(false);
            Graficar();
        } else {
            cbVertical.setSelected(true);
        }
    }//GEN-LAST:event_cbVerticalActionPerformed

    private void cbHorizontalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHorizontalActionPerformed
        if (cbHorizontal.isSelected()) {
            cbVertical.setSelected(false);
            cbLineal.setSelected(false);
            cbPastel.setSelected(false);
            Graficar();
        } else {
            cbHorizontal.setSelected(true);
        }
    }//GEN-LAST:event_cbHorizontalActionPerformed

    private void lblLogo1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogo1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_lblLogo1MouseMoved

    private void lblLogo1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogo1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lblLogo1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int CantUsuarios = CantidadDeRegistros("Usuarios");
        String[][] gPDF = new String[CantUsuarios + 1][8];
        for (int j = 0; j < 8; j++) {
            gPDF[0][j] = NombresColumnasUsuarios[j];

        }

        for (int i = 1; i < CantUsuarios + 1; i++) {
            for (int j = 0; j < 8; j++) {
                gPDF[i][j] = BasedeDatosUsuarios[i - 1][j];
            }
        }
        generarPDF(gPDF, CantUsuarios + 1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarIDModificarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarIDModificarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarIDModificarKeyTyped

    private void cbLinealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLinealActionPerformed
        if (cbLineal.isSelected()) {
            cbVertical.setSelected(false);
            cbHorizontal.setSelected(false);
            cbPastel.setSelected(false);
            Graficar();
        } else {
            cbLineal.setSelected(true);
        }
    }//GEN-LAST:event_cbLinealActionPerformed

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        pnlCuerpoUsuarios.removeAll();
        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        pnlCuerpoUsuarios.add(jspnlModificar_Usuarios);

        pnlCuerpoUsuarios.repaint();
        pnlCuerpoUsuarios.revalidate();
        if (tblUsuariosModificar.isVisible()) {
            tblUsuariosModificar.setVisible(false);
        }
    }//GEN-LAST:event_btnCuentaActionPerformed

    private void btnOpsion_IngresarPuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarPuestoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_IngresarPuestoMouseClicked

    private void btnOpsion_ModificarPuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarPuestoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ModificarPuestoMouseClicked

    private void btnOpsion_EliminarPuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarPuestoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_EliminarPuestoMouseClicked

    private void btnOpsion_ConsultaPuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaPuestoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOpsion_ConsultaPuestoMouseClicked

    private void btnOpsion_ModificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarEmpleadoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlEmpleados);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlEmpleados.removeAll();
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
        pnlEmpleados.add(pnlModificarEmpleado);
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
    }//GEN-LAST:event_btnOpsion_ModificarEmpleadoActionPerformed

    private void btnOpsion_EliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarEmpleadoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlEmpleados);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlEmpleados.removeAll();
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
        pnlEmpleados.add(pnlEliminarEmpleado);
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
    }//GEN-LAST:event_btnOpsion_EliminarEmpleadoActionPerformed

    private void btnOpsion_ConsultaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaEmpleadoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlEmpleados);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlEmpleados.removeAll();
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
        pnlEmpleados.add(pnlConsultaEmpleado);
        pnlEmpleados.repaint();
        pnlEmpleados.revalidate();
    }//GEN-LAST:event_btnOpsion_ConsultaEmpleadoActionPerformed

    private void btnOpsion_IngresarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarPuestoActionPerformed

        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlPuestos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlPuestos.removeAll();
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
        pnlPuestos.add(pnlIngresoPuesto);
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
    }//GEN-LAST:event_btnOpsion_IngresarPuestoActionPerformed

    private void btnOpsion_ModificarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarPuestoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlPuestos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlPuestos.removeAll();
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
        pnlPuestos.add(pnlModificarPuesto);
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
    }//GEN-LAST:event_btnOpsion_ModificarPuestoActionPerformed

    private void btnOpsion_EliminarPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarPuestoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlPuestos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlPuestos.removeAll();
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
        pnlPuestos.add(pnlEliminarPuesto);
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
    }//GEN-LAST:event_btnOpsion_EliminarPuestoActionPerformed

    private void btnOpsion_ConsultaPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaPuestoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlPuestos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlPuestos.removeAll();
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
        pnlPuestos.add(pnlConsultaPuesto);
        pnlPuestos.repaint();
        pnlPuestos.revalidate();
    }//GEN-LAST:event_btnOpsion_ConsultaPuestoActionPerformed

    private void btnOpsion_IngresarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarDepActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlDepartamentos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlDepartamentos.removeAll();
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
        pnlDepartamentos.add(pnlIngresoDep);
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
    }//GEN-LAST:event_btnOpsion_IngresarDepActionPerformed

    private void btnOpsion_ModificarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarDepActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlDepartamentos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlDepartamentos.removeAll();
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
        pnlDepartamentos.add(pnlModificarDep);
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
    }//GEN-LAST:event_btnOpsion_ModificarDepActionPerformed

    private void btnOpsion_EliminarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_EliminarDepActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlDepartamentos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlDepartamentos.removeAll();
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
        pnlDepartamentos.add(pnlEliminarDep);
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
    }//GEN-LAST:event_btnOpsion_EliminarDepActionPerformed

    private void btnOpsion_ConsultaDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaDepActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlDepartamentos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlDepartamentos.removeAll();
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
        pnlDepartamentos.add(pnlConsultaDep);
        pnlDepartamentos.repaint();
        pnlDepartamentos.revalidate();
    }//GEN-LAST:event_btnOpsion_ConsultaDepActionPerformed

    private void btnOpsion_IngresarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_IngresarConceptoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlConceptos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlConceptos.removeAll();
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
        pnlConceptos.add(pnlIngresoConcepto);
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
    }//GEN-LAST:event_btnOpsion_IngresarConceptoActionPerformed

    private void btnOpsion_ModificarConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ModificarConceptoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlConceptos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlConceptos.removeAll();
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
        pnlConceptos.add(pnlModificarConcepto);
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
    }//GEN-LAST:event_btnOpsion_ModificarConceptoActionPerformed

    private void btnOpsion_ConsultaConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpsion_ConsultaConceptoActionPerformed
        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlConceptos);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        pnlConceptos.removeAll();
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
        pnlConceptos.add(pnlConsultaConcepto);
        pnlConceptos.repaint();
        pnlConceptos.revalidate();
    }//GEN-LAST:event_btnOpsion_ConsultaConceptoActionPerformed

    private void cbPastelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPastelActionPerformed
        if (cbPastel.isSelected()) {
            cbHorizontal.setSelected(false);
            cbLineal.setSelected(false);
            cbVertical.setSelected(false);
            Graficar();
        } else {
            cbPastel.setSelected(true);
        }
    }//GEN-LAST:event_cbPastelActionPerformed

    private void btn_ingresardepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresardepActionPerformed
        String estado = (String) cbEstadoD.getSelectedItem();
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into Departamento values(?,?,?)");

            pst.setString(1, txt_iddep.getText().trim());
            pst.setString(2, txt_nombredep.getText().trim());
            pst.setString(3, estado);
            pst.executeUpdate();

            txt_iddep.setText("");
            txt_nombredep.setText("");

            GuardarBasedeDatosMatrizDep("Departamento", 3, NombresColumnasDep);
            tblDep.setModel(new DefaultTableModel(BasedeDatosDep, NombresColumnasDep));
        } catch (Exception e) {

        }

    }//GEN-LAST:event_btn_ingresardepActionPerformed

    private void btn_modificardepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificardepActionPerformed
        String estado = (String) cbEstadoD.getSelectedItem();
        try {
            String ID = txt_buscardep.getText().trim();

            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Departamento set id_departamento = ?, nombre_departamento=?, estado=? where id_departamento = " + ID);

            pst.setString(1, txt_iddep.getText().trim());
            pst.setString(2, txt_nombredep.getText().trim());
            pst.setString(3, estado);

            pst.executeUpdate();

            GuardarBasedeDatosMatrizDep("Departamento", 3, NombresColumnasDep);
            tblDep.setModel(new DefaultTableModel(BasedeDatosDep, NombresColumnasDep));
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_modificardepActionPerformed

    private void btn_eliminardepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminardepActionPerformed
        // TODO add your handling code here:
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from Departamento where id_departamento = ?");

            pst.setString(1, txt_buscardep.getText().trim());
            pst.executeUpdate();

            txt_iddep.setText("");
            txt_nombredep.setText("");

            btn_eliminardep.setText("");
            GuardarBasedeDatosMatrizDep("Departamento", 3, NombresColumnasDep);
            tblDep.setModel(new DefaultTableModel(BasedeDatosDep, NombresColumnasDep));
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_eliminardepActionPerformed

    private void btn_buscardepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscardepActionPerformed
        // TODO add your handling code here:
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from Departamento where id_departamento = ?");
            pst.setString(1, txt_buscardep.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_iddep.setText(rs.getString("id_departamento"));
                txt_nombredep.setText(rs.getString("nombre_departamento"));
                cbEstadoD.setSelectedItem(rs.getString("estado"));
            } else {
                Icon Aviso = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NoHayRegistros.png"));
                JOptionPane.showMessageDialog(null, "EL ID QUE INGRESO NO EXISTE", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE, Aviso);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_buscardepActionPerformed

    private void txt_nombrepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombrepuestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombrepuestoActionPerformed

    private void btn_ingresarpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarpuestoActionPerformed
        // TODO add your handling code here:
        String estado = (String) cbEstadoP.getSelectedItem();        // TODO add your handling code here:
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into Puesto values(?,?,?)");

            pst.setString(1, txt_idpuesto.getText().trim());
            pst.setString(2, txt_nombrepuesto.getText().trim());
            pst.setString(3, estado);
            pst.executeUpdate();

            txt_idpuesto.setText("");
            txt_nombrepuesto.setText("");

            GuardarBasedeDatosMatrizPuestos("Puesto", 3, NombresColumnasPuestos);
            tblPuestos.setModel(new DefaultTableModel(BasedeDatosPuestos, NombresColumnasPuestos));

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_ingresarpuestoActionPerformed

    private void btn_modificarpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarpuestoActionPerformed
        // TODO add your handling code here:
        String estado = (String) cbEstadoP.getSelectedItem();
        try {
            String ID = txt_buscarpuesto.getText().trim();

            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Puesto set id_puesto = ?, nombre_puestos=?, estado=? where id_puesto = " + ID);

            pst.setString(1, txt_idpuesto.getText().trim());
            pst.setString(2, txt_nombrepuesto.getText().trim());
            pst.setString(3, estado);
            txt_idpuesto.setText("");
            txt_nombrepuesto.setText("");

            pst.executeUpdate();
            GuardarBasedeDatosMatrizPuestos("Puesto", 3, NombresColumnasPuestos);
            tblPuestos.setModel(new DefaultTableModel(BasedeDatosPuestos, NombresColumnasPuestos));
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_modificarpuestoActionPerformed

    private void btn_eliminarpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarpuestoActionPerformed
        // TODO add your handling code here:
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from Puesto where id_puesto = ?");

            pst.setString(1, txt_buscarpuesto.getText().trim());
            pst.executeUpdate();
            txt_idpuesto.setText("");
            txt_nombrepuesto.setText("");

            GuardarBasedeDatosMatrizPuestos("Puesto", 3, NombresColumnasPuestos);
            tblPuestos.setModel(new DefaultTableModel(BasedeDatosPuestos, NombresColumnasPuestos));
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_eliminarpuestoActionPerformed

    private void btn_buscarpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarpuestoActionPerformed
        // TODO add your handling code here:
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from Puesto where id_puesto = ?");
            pst.setString(1, txt_buscarpuesto.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_idpuesto.setText(rs.getString("id_puesto"));
                txt_nombrepuesto.setText(rs.getString("nombre_puestos"));
                cbEstadoP.setSelectedItem(rs.getString("estado"));
            } else {
                Icon Aviso = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NoHayRegistros.png"));
                JOptionPane.showMessageDialog(null, "EL ID QUE INGRESO NO EXISTE", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE, Aviso);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btn_buscarpuestoActionPerformed

    private void jButton_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IngresarActionPerformed
        // TODO add your handling code here:
        String tipo = (String) cbTipoConcepto.getSelectedItem();
        String clase = (String) cbClase.getSelectedItem();
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into Concepto_Planilla values(?,?,?,?,?,?)");

            pst.setString(1, txt_ID_Concepto_Planilla.getText().trim());
            pst.setString(2, txt_Nombre_Concepto_Planilla.getText().trim());
            pst.setString(3, tipo);
            pst.setString(4, clase);
            pst.setString(5, txt_Valor_Concepto_Planilla.getText().trim());
            pst.setString(6, DatosPersonales2[0]);
            pst.executeUpdate();

            txt_ID_Concepto_Planilla.setText("");
            txt_Nombre_Concepto_Planilla.setText("");

            txt_Valor_Concepto_Planilla.setText("");
            GuardarBasedeDatosMatrizConceptos("Concepto_Planilla", 6, NombresColumnasConceptos);
            tblConceptos.setModel(new DefaultTableModel(BasedeDatosConceptos, NombresColumnasConceptos));

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton_IngresarActionPerformed

    private void jButton_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ModificarActionPerformed
        // TODO add your handling code here:
        String tipo = (String) cbTipoConcepto.getSelectedItem();
        String clase = (String) cbClase.getSelectedItem();
        try {
            String ID = txt_Buscar.getText().trim();

            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Concepto_Planilla set id_conceptoPlanilla = ?,nombre_concepto = ?, tipo_concepto = ?,clase_concepto=?,Valor_concepto=?,ID_Usuario=? where id_conceptoPlanilla = " + ID);

            pst.setString(1, txt_ID_Concepto_Planilla.getText().trim());
            pst.setString(2, txt_Nombre_Concepto_Planilla.getText().trim());
            pst.setString(3, tipo);
            pst.setString(4, clase);
            pst.setString(5, txt_Valor_Concepto_Planilla.getText().trim());
            pst.setString(6, DatosPersonales2[0]);
            pst.executeUpdate();
            GuardarBasedeDatosMatrizConceptos("Concepto_Planilla", 6, NombresColumnasConceptos);
            tblConceptos.setModel(new DefaultTableModel(BasedeDatosConceptos, NombresColumnasConceptos));
            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);

            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton_ModificarActionPerformed

    private void jButton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuscarActionPerformed
        // TODO add your handling code here:

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from Concepto_Planilla where id_conceptoPlanilla = ?");
            pst.setString(1, txt_Buscar.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_ID_Concepto_Planilla.setText(rs.getString("id_conceptoPlanilla"));
                txt_Nombre_Concepto_Planilla.setText(rs.getString("nombre_concepto"));
                cbTipoConcepto.setSelectedItem(rs.getString("tipo_concepto"));
                cbClase.setSelectedItem(rs.getString("clase_concepto"));
                txt_Valor_Concepto_Planilla.setText(rs.getString("Valor_concepto"));

            } else {
                Icon Aviso = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NoHayRegistros.png"));
                JOptionPane.showMessageDialog(null, "EL ID QUE INGRESO NO EXISTE", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE, Aviso);
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton_BuscarActionPerformed

    private void jButton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarActionPerformed
        // TODO add your handling code here:
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from Concepto_Planilla where id_conceptoPlanilla = ?");

            pst.setString(1, txt_Buscar.getText().trim());
            pst.executeUpdate();

            txt_ID_Concepto_Planilla.setText("");
            txt_Nombre_Concepto_Planilla.setText("");

            txt_Valor_Concepto_Planilla.setText("");

            GuardarBasedeDatosMatrizConceptos("Concepto_Planilla", 6, NombresColumnasConceptos);
            tblConceptos.setModel(new DefaultTableModel(BasedeDatosConceptos, NombresColumnasConceptos));
            /* GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);

            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));*/

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton_EliminarActionPerformed

    private void jButton_InsertarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertarEActionPerformed

        String estado = (String) cbEstado.getSelectedItem();
        String puesto = (String) cbPuesto.getSelectedItem();
        String dep = (String) cbDepartamento.getSelectedItem();
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into Empleado values(?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, txt_ID_Empleado.getText().trim());
            pst.setString(2, txt_Nombre_Empleado1.getText().trim());
            pst.setString(3, txt_Apellido_Empleado1.getText().trim());
            pst.setString(4, txt_DPI_Empleado1.getText().trim());
            pst.setString(5, txt_Telefono_Empleado1.getText().trim());
            pst.setString(6, estado);
            pst.setString(7, txt_Sueldo_Empleado.getText().trim());
            pst.setString(8, puesto);
            pst.setString(9, dep);
            pst.setString(10, DatosPersonales2[0]);

            pst.executeUpdate();
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));
            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);
            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));
            txt_ID_Empleado.setText("");
            txt_Nombre_Empleado1.setText("");
            txt_Apellido_Empleado1.setText("");
            txt_DPI_Empleado1.setText("");
            txt_Telefono_Empleado1.setText("");
            txt_Sueldo_Empleado.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton_InsertarEActionPerformed

    private void jButton_ModificarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ModificarEActionPerformed
        String estado = (String) cbEstado.getSelectedItem();
        String puesto = (String) cbPuesto.getSelectedItem();
        String dep = (String) cbDepartamento.getSelectedItem();

        try {
            String ID = txt_BuscarE.getText().trim();

            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Empleado set id_empleado = ?,nombre_empleado = ?, apellido_empleado = ?,dpi_empleado = ?,telefono_empleado = ?,estado_empleado = ?,sueldo =?, id_puesto=?, id_departamento=?, ID_Usuario=? where id_empleado = " + ID);

            pst.setString(1, txt_ID_Empleado.getText().trim());
            pst.setString(2, txt_Nombre_Empleado1.getText().trim());
            pst.setString(3, txt_Apellido_Empleado1.getText().trim());
            pst.setString(4, txt_DPI_Empleado1.getText().trim());
            pst.setString(5, txt_Telefono_Empleado1.getText().trim());
            pst.setString(6, estado);
            pst.setString(7, txt_Sueldo_Empleado.getText().trim());
            pst.setString(8, puesto);
            pst.setString(9, dep);
            pst.setString(10, DatosPersonales2[0]);

            pst.executeUpdate();
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));
            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);

            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));
            txt_ID_Empleado.setText("");
            txt_Nombre_Empleado1.setText("");
            txt_Apellido_Empleado1.setText("");
            txt_DPI_Empleado1.setText("");
            txt_Telefono_Empleado1.setText("");

            txt_Sueldo_Empleado.setText("");

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton_ModificarEActionPerformed

    private void jButton_BuscarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BuscarEActionPerformed
        // TODO add your handling code here:

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from Empleado where id_empleado = ?");
            pst.setString(1, txt_BuscarE.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_ID_Empleado.setText(rs.getString("id_empleado"));
                txt_Nombre_Empleado1.setText(rs.getString("nombre_empleado"));
                txt_Apellido_Empleado1.setText(rs.getString("apellido_empleado"));
                txt_DPI_Empleado1.setText(rs.getString("dpi_empleado"));
                txt_Telefono_Empleado1.setText(rs.getString("telefono_empleado"));

                cbEstado.setSelectedItem(rs.getString("estado_empleado"));
                txt_Sueldo_Empleado.setText(rs.getString("sueldo"));
                cbPuesto.setSelectedItem(rs.getString("id_puesto"));
                cbDepartamento.setSelectedItem(rs.getString("id_departamento"));

            } else {
                Icon Aviso = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/NoHayRegistros.png"));
                JOptionPane.showMessageDialog(null, "EL ID QUE INGRESO NO EXISTE", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE, Aviso);

            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_jButton_BuscarEActionPerformed

    private void jButton_EliminarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarEActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from Empleado where id_empleado = ?");

            pst.setString(1, txt_BuscarE.getText().trim());

            pst.executeUpdate();
            GuardarBasedeDatosMatrizEmpleados("Empleado", 10, NombresColumnasEmpleados);
            tblEmpleados.setModel(new DefaultTableModel(BasedeDatosEmpleados, NombresColumnasEmpleados));
            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);

            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));
            txt_ID_Empleado.setText("");
            txt_Nombre_Empleado1.setText("");
            txt_Apellido_Empleado1.setText("");
            txt_DPI_Empleado1.setText("");
            txt_Telefono_Empleado1.setText("");

            txt_Sueldo_Empleado.setText("");

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton_EliminarEActionPerformed

    private void txt_nombrepuesto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombrepuesto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombrepuesto1ActionPerformed

    private void btn_ingresarpuesto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresarpuesto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ingresarpuesto1ActionPerformed

    private void btn_modificarpuesto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarpuesto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarpuesto1ActionPerformed

    private void btn_eliminarpuesto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarpuesto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarpuesto1ActionPerformed

    private void btn_buscarpuesto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarpuesto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscarpuesto1ActionPerformed

    private void jButton_InsertarE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InsertarE2ActionPerformed

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("insert into PlanillaDet values(?,?,?,?)");

            pst.setString(1, "0");
            pst.setString(2, txt_IDConceptoPlanilla.getText().trim());
            pst.setString(3, txt_IDEmpleadoPlanilla.getText().trim());
            pst.setString(4, txtValorConcepto.getText().trim());

            pst.executeUpdate();
            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);

            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));

            String IDdeEmpleado = txt_IDEmpleadoPlanilla.getText().trim();

            boolean EmpleadoPG = false;
            try {
                Connection cn1 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                PreparedStatement pst1 = cn1.prepareStatement("select * from PlanillaGen where id_planillaGen = ?");
                pst1.setString(1, txt_IDEmpleadoPlanilla.getText().trim());

                ResultSet rs = pst1.executeQuery();

                if (rs.next()) {
                    EmpleadoPG = true;

                } else {
                    EmpleadoPG = false;
                }

            } catch (Exception e) {

            }

            if (EmpleadoPG == false) {
                int cant3 = CantidadDeRegistros("Empleado");
                for (int i = 0; i < cant3; i++) {
                    if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosEmpleados[i][0])) {
                        TotalPercepcion = Float.parseFloat(BasedeDatosEmpleados[i][6]);
                        TotalDeducion = 0;
                        i = cant3;
                    }
                }
                if (cbPercepcion.isSelected()) {
                    TotalPercepcion = TotalPercepcion + Float.parseFloat(txtValorConcepto.getText().trim());
                } else if (cbDeduccion.isSelected()) {
                    TotalDeducion = TotalDeducion + Float.parseFloat(txtValorConcepto.getText().trim());
                }
                try {
                    Connection cn2 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                    PreparedStatement pst2 = cn2.prepareStatement("insert into PlanillaGen values(?,?,?,?,?)");

                    pst2.setString(1, "0");
                    pst2.setString(2, IDdeEmpleado);
                    pst2.setString(3, Float.toString(0));
                    pst2.setString(4, Float.toString(0));
                    pst2.setString(5, Float.toString(0));

                    pst2.executeUpdate();
                    GuardarBasedeDatosMatrizEmpleadosPlanillaGen("PlanillaGen", 5, NombresColumnasPlanillaGen);

                    tblPlanillaGen.setModel(new DefaultTableModel(BasedeDatosPlanillaGen, NombresColumnasPlanillaGen));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String ID = IDdeEmpleado;

                    Connection cn3 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                    PreparedStatement pst3 = cn3.prepareStatement("update PlanillaGen set total_percepsion = ?, total_deduccion = ?,total_liquido = ? where id_empleado = " + ID);

                    pst3.setString(1, Float.toString(TotalPercepcion));
                    pst3.setString(2, Float.toString(TotalDeducion));
                    pst3.setString(3, Float.toString(TotalPercepcion - TotalDeducion));
                    pst3.executeUpdate();
                    GuardarBasedeDatosMatrizEmpleadosPlanillaGen("PlanillaGen", 5, NombresColumnasPlanillaGen);

                    tblPlanillaGen.setModel(new DefaultTableModel(BasedeDatosPlanillaGen, NombresColumnasPlanillaGen));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                int cant3 = CantidadDeRegistros("PlanillaGen");
                for (int i = 0; i < cant3; i++) {
                    if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosPlanillaGen[i][0])) {
                        TotalPercepcion = Float.parseFloat(BasedeDatosPlanillaGen[i][2]);
                        TotalDeducion = Float.parseFloat(BasedeDatosPlanillaGen[i][3]);
                        i = cant3;
                    }
                }

                if (cbPercepcion.isSelected()) {
                    TotalPercepcion = TotalPercepcion + Float.parseFloat(txtValorConcepto.getText().trim());
                } else if (cbDeduccion.isSelected()) {
                    TotalDeducion = TotalDeducion + Float.parseFloat(txtValorConcepto.getText().trim());
                }
                try {
                    String ID = IDdeEmpleado;

                    Connection cn4 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                    PreparedStatement pst4 = cn4.prepareStatement("update PlanillaGen set total_percepsion = ?, total_deduccion = ?,total_liquido = ? where id_empleado = " + ID);

                    pst4.setString(1, Float.toString(TotalPercepcion));
                    pst4.setString(2, Float.toString(TotalDeducion));
                    pst4.setString(3, Float.toString(TotalPercepcion - TotalDeducion));
                    pst4.executeUpdate();
                    GuardarBasedeDatosMatrizEmpleadosPlanillaGen("PlanillaGen", 5, NombresColumnasPlanillaGen);

                    tblPlanillaGen.setModel(new DefaultTableModel(BasedeDatosPlanillaGen, NombresColumnasPlanillaGen));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton_InsertarE2ActionPerformed

    private void txt_BuscarECaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_BuscarECaretUpdate
        String valor = txt_BuscarE.getText().trim();
        if (valor.isEmpty()) {

            tblEmpleados.clearSelection();

        } else {
            for (int i = 0; i < tblEmpleados.getRowCount(); i++) {
                if (tblEmpleados.getValueAt(i, 0).equals(valor)) {

                    tblEmpleados.changeSelection(i, 0, false, false);

                }

            }
        }
    }//GEN-LAST:event_txt_BuscarECaretUpdate

    private void txt_buscarpuestoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_buscarpuestoCaretUpdate
        String valor = txt_buscarpuesto.getText().trim();
        if (valor.isEmpty()) {

            tblPuestos.clearSelection();

        } else {
            for (int i = 0; i < tblPuestos.getRowCount(); i++) {
                if (tblPuestos.getValueAt(i, 0).equals(valor)) {

                    tblPuestos.changeSelection(i, 0, false, false);

                }

            }
        }
    }//GEN-LAST:event_txt_buscarpuestoCaretUpdate

    private void txt_buscardepCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_buscardepCaretUpdate
        String valor = txt_buscardep.getText().trim();
        if (valor.isEmpty()) {

            tblDep.clearSelection();

        } else {
            for (int i = 0; i < tblDep.getRowCount(); i++) {
                if (tblDep.getValueAt(i, 0).equals(valor)) {

                    tblDep.changeSelection(i, 0, false, false);

                }

            }
        }
    }//GEN-LAST:event_txt_buscardepCaretUpdate

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BuscarActionPerformed

    private void txt_BuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_BuscarCaretUpdate
        String valor = txt_Buscar.getText().trim();
        if (valor.isEmpty()) {

            tblConceptos.clearSelection();

        } else {
            for (int i = 0; i < tblConceptos.getRowCount(); i++) {
                if (tblConceptos.getValueAt(i, 0).equals(valor)) {

                    tblConceptos.changeSelection(i, 0, false, false);

                }

            }
        }
    }//GEN-LAST:event_txt_BuscarCaretUpdate

    private void txt_ID_Concepto_PlanillaComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_txt_ID_Concepto_PlanillaComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ID_Concepto_PlanillaComponentHidden

    private void btnEliminarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTablaActionPerformed

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("delete from PlanillaDet where id_planillaDet = ?");

            pst.setString(1, txtBuscarTabla.getText().trim());
            pst.executeUpdate();

            txt_IDConceptoPlanilla.setText("");
            // txt_IDEmpleadoPlanilla.setText("");
            txtValorConcepto.setText("");
            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);
            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));

            boolean EmpleadoPG = false;
            String nombre = txt_IDEmpleadoPlanilla.getText().trim();
            try {
                Connection cn1 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                PreparedStatement pst1 = cn1.prepareStatement("select * from PlanillaDet where id_empleado = ?");
                pst1.setString(1, txt_IDEmpleadoPlanilla.getText().trim());

                ResultSet rs = pst1.executeQuery();

                if (rs.next()) {
                    EmpleadoPG = true;

                } else {
                    EmpleadoPG = false;
                }

            } catch (Exception e) {

            }

            if (EmpleadoPG == false) {
                Connection cn2 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                PreparedStatement pst2 = cn2.prepareStatement("delete from PlanillaGen where id_empleado = ?");

                pst2.setString(1, nombre);
                pst2.executeUpdate();
                pst2.executeUpdate();
                GuardarBasedeDatosMatrizEmpleadosPlanillaGen("PlanillaGen", 5, NombresColumnasPlanillaGen);
                tblPlanillaGen.setModel(new DefaultTableModel(BasedeDatosPlanillaGen, NombresColumnasPlanillaGen));

            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarTablaActionPerformed

    private void btnModificarTabla1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarTabla1ActionPerformed
        try {
            String ID = txtBuscarTabla.getText().trim();

            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update PlanillaDet set id_conceptoPlanilla = ?, id_empleado = ?, valor_conceptoDet = ? where id_planillaDet = " + ID);

            pst.setString(1, txt_IDConceptoPlanilla.getText().trim());
            pst.setString(2, txt_IDEmpleadoPlanilla.getText().trim());
            pst.setString(3, txtValorConcepto.getText().trim());
            pst.executeUpdate();

            GuardarBasedeDatosMatrizEmpleadosPlanillaDet("PlanillaDet", 4, NombresColumnasPlanillaDet);

            tblPlanillaDet.setModel(new DefaultTableModel(BasedeDatosEmpleadosPlanillaDet, NombresColumnasPlanillaDet));

            int cant3 = CantidadDeRegistros("PlanillaGen");
            for (int i = 0; i < cant3; i++) {
                if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosPlanillaGen[i][0])) {
                    TotalPercepcion = Float.parseFloat(BasedeDatosPlanillaGen[i][2]);
                    TotalDeducion = Float.parseFloat(BasedeDatosPlanillaGen[i][3]);
                    i = cant3;
                }
            }

            if (cbPercepcion.isSelected()) {
                TotalPercepcion = TotalPercepcion + Float.parseFloat(txtValorConcepto.getText().trim());
            } else if (cbDeduccion.isSelected()) {
                TotalDeducion = TotalDeducion + Float.parseFloat(txtValorConcepto.getText().trim());
            }
            try {
                String ID1 = txt_IDEmpleadoPlanilla.getText().trim();

                Connection cn4 = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
                PreparedStatement pst4 = cn4.prepareStatement("update PlanillaGen set total_percepsion = ?, total_deduccion = ?,total_liquido = ? where id_empleado = " + ID1);

                pst4.setString(1, Float.toString(TotalPercepcion));
                pst4.setString(2, Float.toString(TotalDeducion));
                pst4.setString(3, Float.toString(TotalPercepcion - TotalDeducion));
                pst4.executeUpdate();
                GuardarBasedeDatosMatrizEmpleadosPlanillaGen("PlanillaGen", 5, NombresColumnasPlanillaGen);

                tblPlanillaGen.setModel(new DefaultTableModel(BasedeDatosPlanillaGen, NombresColumnasPlanillaGen));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarTabla1ActionPerformed

    private void btnBuscarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTablaActionPerformed
        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from PlanillaDet where id_planillaDet = ?");
            pst.setString(1, txtBuscarTabla.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_IDConceptoPlanilla.setText(rs.getString("id_conceptoPlanilla"));
                txt_IDEmpleadoPlanilla.setText(rs.getString("id_empleado"));
                txtValorConcepto.setText(rs.getString("valor_conceptoDet"));

            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnBuscarTablaActionPerformed

    private void txtBuscarTablaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarTablaCaretUpdate
        String valor = txtBuscarTabla.getText().trim();
        if (valor.isEmpty()) {

            tblPlanillaDet.clearSelection();

        } else {
            for (int i = 0; i < tblPlanillaDet.getRowCount(); i++) {
                if (tblPlanillaDet.getValueAt(i, 0).equals(valor)) {

                    tblPlanillaDet.changeSelection(i, 0, false, false);

                }

            }
        }
    }//GEN-LAST:event_txtBuscarTablaCaretUpdate

    private void txt_IDConceptoPlanillaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDConceptoPlanillaKeyReleased

    }//GEN-LAST:event_txt_IDConceptoPlanillaKeyReleased

    private void txt_IDEmpleadoPlanillaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDEmpleadoPlanillaKeyReleased


    }//GEN-LAST:event_txt_IDEmpleadoPlanillaKeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int cant3 = CantidadDeRegistros("Empleado");
        int cantpuestos = CantidadDeRegistros("Puesto");
        int cantPlanillaGen = CantidadDeRegistros("PlanillaGen");
        float iggs = 0;
        /* for (int i = 0; i < cant3; i++) {
            if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosEmpleados[i][0])) {
                for (int n = 0; n < cantPlanillaGen; n++) {
                    if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosPlanillaGen[n][0])) {
                        iggs = Float.parseFloat(BasedeDatosPlanillaGen[n][2]);
                    } else {
                        iggs = Float.parseFloat(BasedeDatosEmpleados[i][6]);
                    }
                }

                for (int j = 0; j < cantpuestos; j++) {
                    if (BasedeDatosPuestos[j][0].equals(BasedeDatosEmpleados[i][7])) {
                        if (BasedeDatosPuestos[j][1].equals("Representante Legal")) {
                            txtValorConcepto.setText("No se le puede cobrar iggs");
                            txt_IDConceptoPlanilla.setText("");
                            j = cantpuestos;
                        }
                        else {
                        txtValorConcepto.setText(Float.toString((float) (iggs * 0.0483)));
                        txt_IDConceptoPlanilla.setText("1");

                    }

                    } 

                }
                i = cant3;
            }
        }*/
        for (int i = 0; i < cant3; i++) {
            if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosEmpleados[i][0])) {
                if (cantPlanillaGen > 0) {
                    for (int n = 0; n < cantPlanillaGen; n++) {
                        if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosPlanillaGen[n][1])) {
                            iggs = Float.parseFloat(BasedeDatosPlanillaGen[n][2]);
                            n = cantPlanillaGen;
                        } else {
                            if (n == cantPlanillaGen - 1) {
                                iggs = Float.parseFloat(BasedeDatosEmpleados[i][6]);
                            }
                        }
                    }
                } else {
                    iggs = Float.parseFloat(BasedeDatosEmpleados[i][6]);
                }

                for (int j = 0; j < cantpuestos; j++) {
                    if (BasedeDatosPuestos[j][0].equals(BasedeDatosEmpleados[i][7])) 
                    {
                        if (BasedeDatosPuestos[j][1].equals("Representante Legal")) {
                            txtValorConcepto.setText("No se le puede cobrar iggs");
                            txt_IDConceptoPlanilla.setText("");
                            j = cantpuestos;
                        }
                        else if(txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosEmpleados[i][0]))
                        {
                            txtValorConcepto.setText(Float.toString((float) (iggs * 0.0483)));
                            txt_IDConceptoPlanilla.setText("1");
                        }
                    }
                    /*else
                    {
                     
                            if (j == cantpuestos - 1) {
                                //txtValorConcepto.setText(Float.toString((float) (iggs * 0.0483)));
                                //txt_IDConceptoPlanilla.setText("1");
                                
                            }

                    }*/
                    

                }

                i = cant3;
            }

        }


    }//GEN-LAST:event_jButton9ActionPerformed

    private void cbISRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbISRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbISRActionPerformed

    private void btnHerramientasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHerramientasMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHerramientasMouseMoved

    private void btnHerramientasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHerramientasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHerramientasMouseClicked

    private void btnHerramientasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHerramientasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHerramientasActionPerformed

    private void btnCatalogosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCatalogosMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCatalogosMouseMoved

    private void btnCatalogosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCatalogosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCatalogosMouseClicked

    private void btnCatalogosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogosActionPerformed
        btnMantenimientoUsuarios.setVisible(true);
        btnMantenimientoUsuarios1.setVisible(true);
        btnMantenimientoUsuarios1.setVisible(false);
        pnlOpcionesTipo.setVisible(false);
    }//GEN-LAST:event_btnCatalogosActionPerformed

    private void btnProcesosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcesosMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProcesosMouseMoved

    private void btnProcesosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcesosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProcesosMouseClicked

    private void btnProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesosActionPerformed
        btnMantenimientoUsuarios1.setVisible(true);
        btnMantenimientoUsuarios.setVisible(true);
        btnMantenimientoUsuarios.setVisible(false);
        pnlOpciones.setVisible(false);
    }//GEN-LAST:event_btnProcesosActionPerformed

    private void btnHelpMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelpMouseMoved

    private void btnHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelpMouseClicked
        
    }//GEN-LAST:event_btnHelpMouseClicked

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
//<<<<<<< HEAD
        // TODO add your handling code here:
        try {
            String URL= "C:\\Users\\Brayan Cifuentes\\Desktop\\Manual de usuario.docx";
            ProcessBuilder p= new ProcessBuilder();
//=======
        //try {

            //ProcessBuilder p = new ProcessBuilder();
//>>>>>>> 02906463b984441bf627bfd806af40a730675726
            p.command("cmd.exe", "/c", URL);
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(Plataforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnInformesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformesMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformesMouseMoved

    private void btnInformesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformesMouseClicked

    private void btnInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformesActionPerformed

    private void btnHelp1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelp1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp1MouseMoved

    private void btnHelp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHelp1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelp1MouseClicked

    private void btnHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp1ActionPerformed
        pnlCatalogo.setVisible(false);
        pnlProcesos.setVisible(false);
        pnlHerramientas.setVisible(false);
        pnlInformes.setVisible(false);
        pnlAyuda.setVisible(true);
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
        
        
        try {
            //String URL= "C:\\Users\\Brayan Cifuentes\\Desktop\\Manual de usuario.docx";
            ProcessBuilder p= new ProcessBuilder();
            p.command("cmd.exe", "/c", URL);
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(Plataforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHelp1ActionPerformed

    private void btnHerramientas1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHerramientas1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHerramientas1MouseMoved

    private void btnHerramientas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHerramientas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHerramientas1MouseClicked

    private void btnHerramientas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHerramientas1ActionPerformed
        pnlCatalogo.setVisible(false);
        pnlProcesos.setVisible(false);
        pnlHerramientas.setVisible(true);
        pnlInformes.setVisible(false);
        pnlAyuda.setVisible(false);
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
    }//GEN-LAST:event_btnHerramientas1ActionPerformed

    private void btnInformes1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformes1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformes1MouseMoved

    private void btnInformes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformes1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInformes1MouseClicked

    private void btnInformes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformes1ActionPerformed
        pnlCatalogo.setVisible(false);
        pnlProcesos.setVisible(false);
        pnlHerramientas.setVisible(false);
        pnlInformes.setVisible(true);
        pnlAyuda.setVisible(false);
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
    }//GEN-LAST:event_btnInformes1ActionPerformed

    private void btnProcesos1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcesos1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProcesos1MouseMoved

    private void btnProcesos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcesos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProcesos1MouseClicked

    private void btnProcesos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesos1ActionPerformed
        pnlCatalogo.setVisible(false);
        pnlProcesos.setVisible(true);
        pnlHerramientas.setVisible(false);
        pnlInformes.setVisible(false);
        pnlAyuda.setVisible(false);
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
    }//GEN-LAST:event_btnProcesos1ActionPerformed

    private void btnCatalogos1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCatalogos1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCatalogos1MouseMoved

    private void btnCatalogos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCatalogos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCatalogos1MouseClicked

    private void btnCatalogos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogos1ActionPerformed
        pnlCatalogo.setVisible(true);
        pnlProcesos.setVisible(false);
        pnlHerramientas.setVisible(false);
        pnlInformes.setVisible(false);
        pnlAyuda.setVisible(false);
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);


    }//GEN-LAST:event_btnCatalogos1ActionPerformed

    private void btnMantenimientoUsuariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuariosMouseMoved
        pnlOpciones.setVisible(true);
        pnlOpcionesTipo.setVisible(true);
        pnlOpcionesTipo.setVisible(false);
        lblIngresarUser.setBackground(new Color(102, 153, 255));

        lbltipodeUsuario.setBackground(new Color(color[0], color[1], color[2]));

    }//GEN-LAST:event_btnMantenimientoUsuariosMouseMoved

    private void btnMantenimientoUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMantenimientoUsuariosMouseClicked

    private void btnMantenimientoUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMantenimientoUsuariosActionPerformed

    private void btnMantenimientoUsuarios1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuarios1MouseMoved
        pnlOpcionesTipo.setVisible(true);
        pnlOpciones.setVisible(true);
        pnlOpciones.setVisible(false);
        lblIngresarUser.setBackground(new Color(color[0], color[1], color[2]));

        lbltipodeUsuario.setBackground(new Color(255, 102, 102));
    }//GEN-LAST:event_btnMantenimientoUsuarios1MouseMoved

    private void btnMantenimientoUsuarios1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuarios1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMantenimientoUsuarios1MouseClicked

    private void btnMantenimientoUsuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMantenimientoUsuarios1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMantenimientoUsuarios1ActionPerformed

    private void btnMCMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMCMouseMoved
        lblConceptos.setBackground(new Color(51, 153, 255));

        lblEmpleados.setBackground(new Color(color[0], color[1], color[2]));
        lblDep.setBackground(new Color(color[0], color[1], color[2]));
        lblPuestos.setBackground(new Color(color[0], color[1], color[2]));
        lblTabla.setBackground(new Color(color[0], color[1], color[2]));
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);

        pnlOpciones_NominaConceptos.setVisible(true);
    }//GEN-LAST:event_btnMCMouseMoved

    private void btnMCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMCMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMCMouseClicked

    private void btnMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMCActionPerformed

    private void btnMEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMEMouseMoved
        lblEmpleados.setBackground(new Color(88, 122, 255));

        lblPuestos.setBackground(new Color(color[0], color[1], color[2]));

        lblConceptos.setBackground(new Color(color[0], color[1], color[2]));
        lblDep.setBackground(new Color(color[0], color[1], color[2]));
        lblTabla.setBackground(new Color(color[0], color[1], color[2]));

        pnlOpciones_NominaConceptos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaEmpleados.setVisible(true);
    }//GEN-LAST:event_btnMEMouseMoved

    private void btnMEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMEMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMEMouseClicked

    private void btnMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMEActionPerformed

    private void btnMPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMPMouseMoved
        lblPuestos.setBackground(new Color(0, 204, 255));

        lblEmpleados.setBackground(new Color(color[0], color[1], color[2]));
        lblConceptos.setBackground(new Color(color[0], color[1], color[2]));
        lblDep.setBackground(new Color(color[0], color[1], color[2]));
        lblTabla.setBackground(new Color(color[0], color[1], color[2]));
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(true);
    }//GEN-LAST:event_btnMPMouseMoved

    private void btnMPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMPMouseClicked

    private void btnMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMPActionPerformed

    private void btnMDMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMDMouseMoved
        lblDep.setBackground(new Color(0, 204, 204));

        lblEmpleados.setBackground(new Color(color[0], color[1], color[2]));
        lblConceptos.setBackground(new Color(color[0], color[1], color[2]));
        lblPuestos.setBackground(new Color(color[0], color[1], color[2]));
        lblTabla.setBackground(new Color(color[0], color[1], color[2]));
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);

        pnlOpciones_NominaConceptos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(true);
    }//GEN-LAST:event_btnMDMouseMoved

    private void btnMDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMDMouseClicked

    private void btnMDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMDActionPerformed

    private void btnMPPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMPPMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMPPMouseMoved

    private void btnMPPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMPPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMPPMouseClicked

    private void btnMPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMPPActionPerformed
        lblTabla.setBackground(new Color(0, 207, 216));

        lblEmpleados.setBackground(new Color(color[0], color[1], color[2]));
        lblConceptos.setBackground(new Color(color[0], color[1], color[2]));
        lblPuestos.setBackground(new Color(color[0], color[1], color[2]));
        lblDep.setBackground(new Color(color[0], color[1], color[2]));
        pnlOpciones_NominaEmpleados.setVisible(false);
        pnlOpciones_NominaPuestos.setVisible(false);
        pnlOpciones_NominaDepartamentos.setVisible(false);
        pnlOpciones_NominaConceptos.setVisible(false);

        pnlCuerpoNomina.removeAll();
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();
        pnlCuerpoNomina.add(pnlTabla);
        pnlCuerpoNomina.repaint();
        pnlCuerpoNomina.revalidate();

        cbIDEmpleado.removeAllItems();
        cbIDConcepto.removeAllItems();
        cbIDEmpleado.addItem("");
        cbIDConcepto.addItem("");

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select id_empleado from Empleado");
            ResultSet rs = pst.executeQuery();

            PreparedStatement pst2 = cn.prepareStatement("select id_conceptoPlanilla from Concepto_Planilla");
            ResultSet rs2 = pst2.executeQuery();

            while (rs.next()) {
                cbIDEmpleado.addItem(rs.getString("id_empleado"));
            }

            while (rs2.next()) {
                cbIDConcepto.addItem(rs2.getString("id_conceptoPlanilla"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMPPActionPerformed

    private void btnIPMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIPMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIPMouseMoved

    private void btnIPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIPMouseClicked

    private void btnIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIPActionPerformed

    private void btnCalcMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalcMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalcMouseMoved

    private void btnCalcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalcMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalcMouseClicked

    private void btnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalcActionPerformed

    private void btnManualMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManualMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManualMouseMoved

    private void btnManualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManualMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManualMouseClicked

    private void btnManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManualActionPerformed
        try {

            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", URL);
            p.start();
        } catch (IOException ex) {
            Logger.getLogger(Plataforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnManualActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            String ruta = "Nomina_formato.xls";
            WorkbookSettings conf = new WorkbookSettings();
            conf.setEncoding("ISO-8859-1");
            WritableWorkbook woorbook = Workbook.createWorkbook(new File(ruta), conf);
            WritableSheet sheet = woorbook.createSheet("RESULTADO", 0);
            WritableFont h = new WritableFont(WritableFont.COURIER, 12, WritableFont.NO_BOLD);
            WritableCellFormat hFormat = new WritableCellFormat(h);

            for (int i = 0; i < BasedeDatosPlanillaGen.length; i++) // filas
            {
                for (int j = 0; j < BasedeDatosPlanillaGen[i].length; j++) // columnas
                {
                    sheet.addCell(new jxl.write.Label(j, i, BasedeDatosPlanillaGen[i][j], hFormat));
                }
            }
            woorbook.write();
            woorbook.close();

        } catch (IOException ex) {
            Logger.getLogger(Plataforma.class.getName()).log(Level.SEVERE, null, ex);

        } catch (WriteException ex) {
            Logger.getLogger(Plataforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void cbPercepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPercepcionActionPerformed
        if (cbPercepcion.isSelected()) {
            cbDeduccion.setSelected(false);
        }


    }//GEN-LAST:event_cbPercepcionActionPerformed

    private void cbDeduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDeduccionActionPerformed
        if (cbDeduccion.isSelected()) {
            cbPercepcion.setSelected(false);
        }
    }//GEN-LAST:event_cbDeduccionActionPerformed

    private void cbIDEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDEmpleadoActionPerformed
        String valor = (String) cbIDEmpleado.getSelectedItem();
        txt_IDEmpleadoPlanilla.setText(valor);
    }//GEN-LAST:event_cbIDEmpleadoActionPerformed

    private void cbIDConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIDConceptoActionPerformed
        String valor = (String) cbIDConcepto.getSelectedItem();
        txt_IDConceptoPlanilla.setText(valor);
    }//GEN-LAST:event_cbIDConceptoActionPerformed

    private void txt_IDEmpleadoPlanillaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_IDEmpleadoPlanillaKeyTyped
        if (cbISR.isSelected()) {
            int cant3 = CantidadDeRegistros("Empleado");
            for (int i = 0; i < cant3; i++) {
                if (txt_IDEmpleadoPlanilla.getText().trim().equals(BasedeDatosEmpleados[i][0])) {
                    TotalPercepcion = Float.parseFloat(BasedeDatosEmpleados[i][6]);
                    i = cant3;
                }
            }

            if (TotalPercepcion >= 6000 && TotalPercepcion <= 7500) {
                txtValorConcepto.setText(Float.toString((float) (TotalPercepcion * 0.05)));
                txt_IDConceptoPlanilla.setText("2");
            } else if (TotalPercepcion >= 7500 && TotalPercepcion <= 9000) {
                txtValorConcepto.setText(Float.toString((float) (TotalPercepcion * 0.06)));
                txt_IDConceptoPlanilla.setText("3");
            } else if (TotalPercepcion >= 9000) {
                txtValorConcepto.setText(Float.toString((float) (TotalPercepcion * 0.08)));
                txt_IDConceptoPlanilla.setText("4");
            } else {
                txtValorConcepto.setText("0");
                txt_IDConceptoPlanilla.setText("NO SE LE PUEDE COBRAR ISR");
            }
            if (txt_IDEmpleadoPlanilla.getText().trim().equals("")) {
                txtValorConcepto.setText("");
                txt_IDConceptoPlanilla.setText("");
            }
        } else {
            txtValorConcepto.setText("");
            txt_IDConceptoPlanilla.setText("");
        }
    }//GEN-LAST:event_txt_IDEmpleadoPlanillaKeyTyped

    private void lblNomina1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNomina1MouseClicked
        // TODO add your handling code here:
        //codigo
        Main inicio = new Main();
        inicio.setVisible(true);
    }//GEN-LAST:event_lblNomina1MouseClicked

    private void VerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerMouseClicked
        // Se va a ver la contraseña:
        No_Ver.setVisible(true);
        No_Ver.setEnabled(true);
        int contra = txtContraseñaUser.getEchoChar();
        txtContraseñaUser.setEchoChar((char)0);
        Ver.setVisible(false);
        Ver.setEnabled(false);
    }//GEN-LAST:event_VerMouseClicked

    private void No_VerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_No_VerMouseClicked
        // No se va a ver la contraseña:
        Ver.setVisible(true);
        Ver.setEnabled(true);
        txtContraseñaUser.setEchoChar((char)8226);
        No_Ver.setVisible(false);
        No_Ver.setEnabled(false);
    }//GEN-LAST:event_No_VerMouseClicked

    private void Ver1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ver1MouseClicked
        // TODO add your handling code here:
        No_Ver1.setVisible(true);
        No_Ver1.setEnabled(true);
        int contra = txtConfirmarUser.getEchoChar();
        txtConfirmarUser.setEchoChar((char)0);
        Ver1.setVisible(false);
        Ver1.setEnabled(false);
    }//GEN-LAST:event_Ver1MouseClicked

    private void No_Ver1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_No_Ver1MouseClicked
        // TODO add your handling code here:
        Ver1.setVisible(true);
        Ver1.setEnabled(true);
        txtConfirmarUser.setEchoChar((char)8226);
        No_Ver1.setVisible(false);
        No_Ver1.setEnabled(false);
    }//GEN-LAST:event_No_Ver1MouseClicked

<<<<<<< HEAD
    private void txtContraseñaUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaUserKeyReleased
        // TODO add your handling code here:
        if (txtContraseñaUser.getText().trim().equals(txtConfirmarUser.getText().trim()) && (!(txtContraseñaUser.getText().trim().equals("")) && !(txtConfirmarUser.getText().trim().equals("")))) {
=======
    private void txtConfirmarUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarUserKeyReleased
        // TODO add your handling code here:
         if (txtContraseñaUser.getText().trim().equals(txtConfirmarUser.getText().trim()) && (!(txtContraseñaUser.getText().trim().equals("")) && !(txtConfirmarUser.getText().trim().equals("")))) {
>>>>>>> ec692187cadd910ccf59b3a1bffc09200ebac154
            btnRegistrar.setEnabled(true);
            txtContraseñaUser.setForeground(new Color(0, 204, 102));
            txtConfirmarUser.setForeground(new Color(0, 204, 102));

        } else {
            btnRegistrar.setEnabled(false);
            txtContraseñaUser.setForeground(Color.RED);
            txtConfirmarUser.setForeground(Color.RED);
        }
<<<<<<< HEAD
    }//GEN-LAST:event_txtContraseñaUserKeyReleased

    private void txtConfirmarUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmarUserKeyReleased
        // TODO add your handling code here:
        if (txtContraseñaUser.getText().trim().equals(txtConfirmarUser.getText().trim()) && (!(txtContraseñaUser.getText().trim().equals("")) && !(txtConfirmarUser.getText().trim().equals("")))) {
            btnRegistrar.setEnabled(true);
            txtContraseñaUser.setForeground(new Color(0, 204, 102));
            txtConfirmarUser.setForeground(new Color(0, 204, 102));

        } else {
            btnRegistrar.setEnabled(false);
            txtContraseñaUser.setForeground(Color.RED);
            txtConfirmarUser.setForeground(Color.RED);
        }
    }//GEN-LAST:event_txtConfirmarUserKeyReleased
=======
    }//GEN-LAST:event_txtConfirmarUserKeyReleased

    private void txtValorConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorConceptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorConceptoActionPerformed

    private void txtValorConceptoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorConceptoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorConceptoKeyReleased
>>>>>>> ec692187cadd910ccf59b3a1bffc09200ebac154
    public void AgregarItemsdeTipo() {
        cbTipo.removeAllItems();
        cbTipo1.removeAllItems();
        cbTipoConsulta.removeAllItems();
        cbTipoConsulta.addItem(" ");
        for (int i = 0; i < RegistrosTipo; i++) {
            cbTipo.addItem(BasedeDatosTipos[i][0]);
            cbTipo1.addItem(BasedeDatosTipos[i][0]);
            cbTipoConsulta.addItem(BasedeDatosTipos[i][0]);

        }

    }

    public void GuardarBasedeDatosMatriz(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosTipo = CantidadDeRegistros(Tabla);
        BasedeDatosTipos = new String[RegistrosTipo][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosTipos[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizUsuarios(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosUsuario = CantidadDeRegistros(Tabla);
        BasedeDatosUsuarios = new String[RegistrosUsuario][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosUsuarios[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizEmpleadosPlanillaDet(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosPlanillaDet = CantidadDeRegistros(Tabla);
        BasedeDatosEmpleadosPlanillaDet = new String[RegistrosPlanillaDet][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosEmpleadosPlanillaDet[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizEmpleadosPlanillaGen(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosPlanillaGen = CantidadDeRegistros(Tabla);
        BasedeDatosPlanillaGen = new String[RegistrosPlanillaGen][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosPlanillaGen[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizEmpleados(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosEmpleados = CantidadDeRegistros(Tabla);
        BasedeDatosEmpleados = new String[RegistrosEmpleados][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosEmpleados[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizConceptos(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosConceptos = CantidadDeRegistros(Tabla);
        BasedeDatosConceptos = new String[RegistrosConceptos][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosConceptos[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizPuestos(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosPuestos = CantidadDeRegistros(Tabla);
        BasedeDatosPuestos = new String[RegistrosPuestos][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosPuestos[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void GuardarBasedeDatosMatrizDep(String Tabla, int CantidadRegistros, String[] Columnas) {
        RegistrosDep = CantidadDeRegistros(Tabla);
        BasedeDatosDep = new String[RegistrosDep][CantidadRegistros];
        int[] columnas = new int[CantidadRegistros];

        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "select * from " + Tabla;
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();

            for (int i = 0; i < CantidadRegistros; i++) {
                columnas[i] = resultado.findColumn(Columnas[i]);
            }

            int filas = 0;

            while (resultado.next()) {
                for (int i = 0; i < CantidadRegistros; i++) {
                    BasedeDatosDep[filas][i] = resultado.getString(columnas[i]);
                }
                filas++;

            }

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void FiltrarPorIdConsultaUsuarios(String Tipo, String Tabla, String Filtro) {
        String[] columnas = new String[8];
        String query;
        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            if (Tipo.equals(" ")) {
                query = "SELECT * FROM " + Tabla;
            } else {
                query = "SELECT * FROM " + Tabla + " where " + Filtro + " LIKE '%" + Tipo + "%'";
            }

            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();
            DefaultTableModel md = new DefaultTableModel(null, NombresColumnasUsuarios);

            while (resultado.next()) {
                for (int i = 0; i < 8; i++) {
                    columnas[i] = resultado.getString(NombresColumnasUsuarios[i]);
                }
                md.addRow(columnas);

            }
            tblUsuariosConsulta.setModel(md);

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void FiltrarPorIdConsultaTipos(String Tipo, String Tabla, String Filtro) {
        String[] columnas = new String[25];
        String query;
        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            if (Tipo.equals(" ")) {
                query = "SELECT * FROM " + Tabla;
            } else {
                query = "SELECT * FROM " + Tabla + " where " + Filtro + " LIKE '%" + Tipo + "%'";
            }

            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();
            DefaultTableModel md = new DefaultTableModel(null, NombresColumnas);

            while (resultado.next()) {
                for (int i = 0; i < 25; i++) {
                    columnas[i] = resultado.getString(NombresColumnas[i]);
                }
                md.addRow(columnas);

            }
            tblTipodeUsuarioConsulta.setModel(md);

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void FiltrarPorIdTipos(String Tipo, String Tabla, String Filtro) {
        String[] columnas = new String[25];
        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "SELECT * FROM " + Tabla + " where " + Filtro + " LIKE '%" + Tipo + "%'";
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();
            DefaultTableModel md = new DefaultTableModel(null, NombresColumnas);

            while (resultado.next()) {
                for (int i = 0; i < 25; i++) {
                    columnas[i] = resultado.getString(NombresColumnas[i]);
                }
                md.addRow(columnas);

            }
            tblUsuariosEliminarTipo.setModel(md);

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public void FiltrarPorIdUsuarios(String Tipo, String Tabla, String Filtro) {
        String[] columnas = new String[8];
        try {

            Connection c = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            String query = "SELECT * FROM " + Tabla + " where " + Filtro + " LIKE '%" + Tipo + "%'";
            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();
            DefaultTableModel md = new DefaultTableModel(null, NombresColumnasUsuarios);

            while (resultado.next()) {
                for (int i = 0; i < 8; i++) {
                    columnas[i] = resultado.getString(NombresColumnasUsuarios[i]);
                }
                md.addRow(columnas);

            }
            tblUsuariosEliminar.setModel(md);

        } catch (Exception err) {
            err.printStackTrace();
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

    public int PosicionDeRegistros(String id, String[][] Matriz) {
        RegistrosTipo = CantidadDeRegistros("Concepto");
        for (int i = 0; i < RegistrosTipo; i++) {
            if (Matriz[i][0].equals(id)) {
                return i;
            }
        }
        return 0;
    }

    public Boolean BuscarRegistroDB(String Usuario_BasededatosID, String Tabla, String Filtro) {

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from " + Tabla + " where " + Filtro + " = ?");
            pst.setString(1, Usuario_BasededatosID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {
                if (Usuario_BasededatosID.equals("")) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean BuscarRegistroPorNombre(String Usuario_Basededatos, String Tabla, String Filtro) {

        try {
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("select * from " + Tabla + " where " + Filtro + " = ?");
            pst.setString(1, Usuario_Basededatos);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {
                if (Usuario_Basededatos.equals("")) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void AgregarNuevaSesion() {
        try {

            String ID = "'" + DatosPersonales2[0] + "'";
            Connection cn = DriverManager.getConnection(Base_de_Datos, Usuario, Clave);
            PreparedStatement pst = cn.prepareStatement("update Usuarios set ID_Usuario = ?, ID_Concepto = ?, NombreUsuario = ?, Contraseña = ?, CorreoElectronico = ?, Telefono = ?, Direcciones = ?, Sesiones = ? where ID_Usuario = " + ID);

            int sesion = Integer.parseInt(DatosPersonales2[7]) + 1;
            pst.setString(1, DatosPersonales2[0]);
            pst.setString(2, DatosPersonales2[1]);
            pst.setString(3, DatosPersonales2[2]);
            pst.setString(4, DatosPersonales2[3]);
            pst.setString(5, DatosPersonales2[4]);
            pst.setString(6, DatosPersonales2[5]);
            pst.setString(7, DatosPersonales2[6]);
            pst.setString(8, Integer.toString(sesion));
            pst.executeUpdate();
            GuardarBasedeDatosMatrizUsuarios("Usuarios", 8, NombresColumnasUsuarios);
            AgregarItemsdeTipo();
            tblUsuariosModificar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            tblUsuariosEliminar.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));
            tblUsuariosConsulta.setModel(new DefaultTableModel(BasedeDatosUsuarios, NombresColumnasUsuarios));

        } catch (Exception e) {
            DesktopNotify.showDesktopMessage("Error", "ID y/o Nombre de Tipo ya Registrado, o campos vacios", DesktopNotify.ERROR, 5000L);
            e.printStackTrace();
        }
    }

    public void Graficar() {

        DefaultCategoryDataset data = new DefaultCategoryDataset();
        DefaultPieDataset dataset2 = new DefaultPieDataset();

        int cantidad = CantidadDeRegistros("Usuarios");

        final String[] TO = new String[cantidad];
        int[] veces = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            TO[i] = BasedeDatosUsuarios[i][2];
        }

        for (int i = 0; i < cantidad; i++) {
            veces[i] = Integer.parseInt(BasedeDatosUsuarios[i][7]);
        }

        for (int i = 0; i < cantidad; i++) {
            data.addValue(veces[i], TO[i], TO[i]);
        }

        for (int i = 0; i < cantidad; i++) {
            dataset2.setValue(TO[i], new Double(veces[i]));
        }

        if (cbVertical.isSelected()) {
            JFreeChart grafica = ChartFactory.createBarChart3D("USUARIOS", "CUANTAS SESIONES HA REALIZADO UN USUARIO", "INICIOS DE SESION", data, PlotOrientation.VERTICAL, true, true, false);
            ChartPanel contenedor = new ChartPanel(grafica);
            pnlIInicio.removeAll();
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
            pnlIInicio.add(contenedor);
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
        } else if (cbHorizontal.isSelected()) {
            JFreeChart grafica = ChartFactory.createBarChart3D("USUARIOS", "CUANTAS SESIONES HA REALIZADO UN USUARIO", "INICIOS DE SESION", data, PlotOrientation.HORIZONTAL, true, true, false);
            ChartPanel contenedor = new ChartPanel(grafica);
            pnlIInicio.removeAll();
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
            pnlIInicio.add(contenedor);
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
        } else if (cbLineal.isSelected()) {
            XYSeries series = new XYSeries("Grafica");
            for (int i = 0; i < cantidad; i++) {
                series.add(i + 1, veces[i]);

            }
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series);
            JFreeChart chart = ChartFactory.createXYLineChart("GRAFICA LINEAL USUARIOS", "ID DE USUARIO", "CANTIDAD DE VECES INICIADAS", dataset, PlotOrientation.VERTICAL, true, false, false);

            XYPlot plot = chart.getXYPlot();

            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

            renderer.setSeriesPaint(0, Color.DARK_GRAY);

            renderer.setSeriesStroke(0, new BasicStroke(1.0f));

            plot.setRenderer(renderer);

            ChartPanel contenedor = new ChartPanel(chart);
            pnlIInicio.removeAll();
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
            pnlIInicio.add(contenedor);
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
        } else if (cbPastel.isSelected()) {

            JFreeChart grafica1 = ChartFactory.createPieChart3D("USUARIOS", dataset2, true, true, false);
            ChartPanel contenedor = new ChartPanel(grafica1);

            pnlIInicio.removeAll();
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
            pnlIInicio.add(contenedor);
            pnlIInicio.repaint();
            pnlIInicio.revalidate();
        }

    }

    public int gotoxMatrizParaDB(String Matrix[][], int x, int y, int registros) {
        int NumeroLetras[] = new int[8];
        int NumeroMayor = 0;

        for (int i = x; i < x + 1; i++) {

            for (int j = 0; j < registros; j++) {
                char[] caracteres = Matrix[j][i].toCharArray();
                NumeroLetras[j] = caracteres.length;
                if (NumeroLetras[j] >= NumeroMayor) {

                    NumeroMayor = NumeroLetras[j];

                }
            }

        }

        return NumeroMayor - NumeroLetras[y] + 5;
    }

    public void generarPDF(String[][] Contenido, int Registros) {
        String[] Parafo = new String[8];
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        String fecha = hourdateFormat.format(date);
        try {
            FileOutputStream archivo = new FileOutputStream("Registros_Usuarios.pdf");
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);

            doc.open();
            doc.add(CabezaPDF("USUARIOS"));

            doc.add(new Paragraph(""));

            for (int i = 0; i < Registros; i++) {

                for (int j = 0; j < 8; j++) {
                    Parafo[j] = Contenido[i][j];

                    for (int l = 0; l < gotoxMatrizParaDB(Contenido, j, i, Registros) - 2; l++) {
                        Parafo[j] = Parafo[j] + " ";
                    }
                }

                doc.add(CuerpoPDF(Parafo[0] + Parafo[1] + Parafo[2] + Parafo[3] + Parafo[4] + Parafo[5] + Parafo[6] + Parafo[7]));

            }
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            Image logo = Image.getInstance("Logo.png");
            logo.scaleAbsolute(128, 128);
            logo.setAlignment(Element.ALIGN_CENTER);
            doc.add(logo);

            doc.close();
            Icon generado = new ImageIcon(getClass().getResource("/Imagenes/Plataforma/Generar.png"));
            JOptionPane.showMessageDialog(null, "EL PDF FUE GENERADO CON EXITO, SE ENCUENTRA EN LA CARPETA DEL PROYECTO", "GENERADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE, generado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Paragraph CabezaPDF(String titulo) {
        com.itextpdf.text.Font fuente = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 12, com.itextpdf.text.Font.BOLD);

        Paragraph linea = new Paragraph();
        Chunk c = new Chunk();
        linea.setAlignment(Element.ALIGN_CENTER);
        c.append(titulo);
        c.setFont(fuente);
        linea.add(c);
        return linea;
    }

    public Paragraph CuerpoPDF(String titulo) {
        com.itextpdf.text.Font fuente = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 6, com.itextpdf.text.Font.NORMAL);
        Paragraph linea = new Paragraph();
        Chunk c = new Chunk();
        linea.setAlignment(Element.ALIGN_CENTER);
        c.append(titulo);
        c.setFont(fuente);
        linea.add(c);
        return linea;
    }

    public Paragraph FinalPDF(String titulo) {
        com.itextpdf.text.Font fuente = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 6, com.itextpdf.text.Font.ITALIC);
        Paragraph linea = new Paragraph();
        Chunk c = new Chunk();
        linea.setAlignment(Element.ALIGN_CENTER);
        c.append(titulo);
        c.setFont(fuente);
        linea.add(c);
        return linea;
    }

    public static void GenerarQR(String dato, String name) {
        int udm = 0, resolucion = 72, rotacion = 0;
        float mi = 0.000f, md = 0.000f, ms = 0.000f, min = 0.000f, tam = 5.00f;
        try {

            QRCode c = new QRCode();
            c.setData(dato);
            c.setDataMode(QRCode.MODE_BYTE);
            c.setUOM(udm);
            c.setLeftMargin(mi);
            c.setRightMargin(md);
            c.setTopMargin(ms);
            c.setBottomMargin(min);
            c.setResolution(resolucion);
            c.setRotate(rotacion);
            c.setModuleSize(tam);

            String archivo = (constante) + ("/" + name + ".png");
            c.renderBarcode(archivo);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

    public void BorraImagen(String NombreImagen) {
        try {
            File Imagen = new File(constante + "\\" + NombreImagen);
            FileInputStream readImage = new FileInputStream(Imagen);

            readImage.close();
            Imagen.delete();

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Plataforma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Plataforma().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel No_Ver;
    private javax.swing.JLabel No_Ver1;
    private javax.swing.JLabel Ver;
    private javax.swing.JLabel Ver1;
    private javax.swing.JButton btnAjustes;
    private javax.swing.JButton btnBarras;
    private javax.swing.JButton btnBuscarID;
    private javax.swing.JButton btnBuscarIDEModificarTipo;
    private javax.swing.JButton btnBuscarTabla;
    private javax.swing.JButton btnCalc;
    private javax.swing.JButton btnCalculadora;
    private javax.swing.JButton btnCatalogos;
    private javax.swing.JButton btnCatalogos1;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConceptos;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnDepartamentos;
    private javax.swing.JButton btnEliminarTabla;
    private javax.swing.JButton btnEliminarUser;
    private javax.swing.JButton btnEliminarUserTipo;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnGrafica;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHelp1;
    private javax.swing.JButton btnHerramientas;
    private javax.swing.JButton btnHerramientas1;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnIP;
    private javax.swing.JButton btnInformes;
    private javax.swing.JButton btnInformes1;
    private javax.swing.JButton btnInicioUsuarios;
    private javax.swing.JButton btnMC;
    private javax.swing.JButton btnMD;
    private javax.swing.JButton btnME;
    private javax.swing.JButton btnMP;
    private javax.swing.JButton btnMPP;
    private javax.swing.JButton btnMantenimientoUsuarios;
    private javax.swing.JButton btnMantenimientoUsuarios1;
    private javax.swing.JButton btnManual;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinimize;
    private javax.swing.JButton btnModificarTabla1;
    private javax.swing.JButton btnModificarUser;
    private javax.swing.JButton btnModificarUserTipo;
    private javax.swing.JButton btnOpsion_ConsultaConcepto;
    private javax.swing.JButton btnOpsion_ConsultaDep;
    private javax.swing.JButton btnOpsion_ConsultaEmpleado;
    private javax.swing.JButton btnOpsion_ConsultaPuesto;
    private javax.swing.JButton btnOpsion_Consultar;
    private javax.swing.JButton btnOpsion_ConsultarTipo;
    private javax.swing.JButton btnOpsion_Eliminar;
    private javax.swing.JButton btnOpsion_EliminarConcepto;
    private javax.swing.JButton btnOpsion_EliminarDep;
    private javax.swing.JButton btnOpsion_EliminarEmpleado;
    private javax.swing.JButton btnOpsion_EliminarPuesto;
    private javax.swing.JButton btnOpsion_EliminarTipo;
    private javax.swing.JButton btnOpsion_Ingresar;
    private javax.swing.JButton btnOpsion_IngresarConcepto;
    private javax.swing.JButton btnOpsion_IngresarDep;
    private javax.swing.JButton btnOpsion_IngresarEmpleado;
    private javax.swing.JButton btnOpsion_IngresarPuesto;
    private javax.swing.JButton btnOpsion_IngresarTipo;
    private javax.swing.JButton btnOpsion_Modificar;
    private javax.swing.JButton btnOpsion_ModificarConcepto;
    private javax.swing.JButton btnOpsion_ModificarDep;
    private javax.swing.JButton btnOpsion_ModificarEmpleado;
    private javax.swing.JButton btnOpsion_ModificarPuesto;
    private javax.swing.JButton btnOpsion_ModificarTipo;
    private javax.swing.JButton btnPagina;
    private javax.swing.JButton btnPastel;
    private javax.swing.JButton btnProcesos;
    private javax.swing.JButton btnProcesos1;
    private javax.swing.JButton btnPuestos;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrarTipo;
    private javax.swing.JButton btnTabla;
    private javax.swing.JButton btnTemas;
    private javax.swing.JButton btnTipodeUsuario;
    private javax.swing.JButton btn_buscardep;
    private javax.swing.JButton btn_buscarpuesto;
    private javax.swing.JButton btn_buscarpuesto1;
    private javax.swing.JButton btn_eliminardep;
    private javax.swing.JButton btn_eliminarpuesto;
    private javax.swing.JButton btn_eliminarpuesto1;
    private javax.swing.JButton btn_ingresardep;
    private javax.swing.JButton btn_ingresarpuesto;
    private javax.swing.JButton btn_ingresarpuesto1;
    private javax.swing.JButton btn_modificardep;
    private javax.swing.JButton btn_modificarpuesto;
    private javax.swing.JButton btn_modificarpuesto1;
    private javax.swing.JComboBox<String> cbClase;
    private javax.swing.JCheckBox cbDeduccion;
    private javax.swing.JComboBox<String> cbDepartamento;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<String> cbEstadoD;
    private javax.swing.JComboBox<String> cbEstadoP;
    private javax.swing.JCheckBox cbHorizontal;
    private javax.swing.JComboBox<String> cbIDConcepto;
    private javax.swing.JComboBox<String> cbIDEmpleado;
    private javax.swing.JCheckBox cbISR;
    private javax.swing.JCheckBox cbLineal;
    private javax.swing.JCheckBox cbPastel;
    private javax.swing.JCheckBox cbPercepcion;
    private javax.swing.JComboBox<String> cbPuesto;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JComboBox<String> cbTipo1;
    private javax.swing.JComboBox<String> cbTipoConcepto;
    private javax.swing.JComboBox<String> cbTipoConsulta;
    private javax.swing.JCheckBox cbVertical;
    private javax.swing.JCheckBox chbPermisoBarras;
    private javax.swing.JCheckBox chbPermisoBarras1;
    private javax.swing.JCheckBox chbPermisoConsultaConcepto;
    private javax.swing.JCheckBox chbPermisoConsultaConcepto1;
    private javax.swing.JCheckBox chbPermisoConsultaDep;
    private javax.swing.JCheckBox chbPermisoConsultaDep1;
    private javax.swing.JCheckBox chbPermisoConsultaEmpleado;
    private javax.swing.JCheckBox chbPermisoConsultaEmpleado1;
    private javax.swing.JCheckBox chbPermisoConsultarUser;
    private javax.swing.JCheckBox chbPermisoConsultarUser1;
    private javax.swing.JCheckBox chbPermisoConsultarUserTipo;
    private javax.swing.JCheckBox chbPermisoConsultarUserTipo1;
    private javax.swing.JCheckBox chbPermisoEliminarConcepto;
    private javax.swing.JCheckBox chbPermisoEliminarConcepto1;
    private javax.swing.JCheckBox chbPermisoEliminarDep;
    private javax.swing.JCheckBox chbPermisoEliminarDep1;
    private javax.swing.JCheckBox chbPermisoEliminarEmpleado;
    private javax.swing.JCheckBox chbPermisoEliminarEmpleado1;
    private javax.swing.JCheckBox chbPermisoEliminarUser;
    private javax.swing.JCheckBox chbPermisoEliminarUser1;
    private javax.swing.JCheckBox chbPermisoEliminarUserTipo;
    private javax.swing.JCheckBox chbPermisoEliminarUserTipo1;
    private javax.swing.JCheckBox chbPermisoIngresoConcepto;
    private javax.swing.JCheckBox chbPermisoIngresoConcepto1;
    private javax.swing.JCheckBox chbPermisoIngresoDep;
    private javax.swing.JCheckBox chbPermisoIngresoDep1;
    private javax.swing.JCheckBox chbPermisoIngresoEmpleado;
    private javax.swing.JCheckBox chbPermisoIngresoEmpleado1;
    private javax.swing.JCheckBox chbPermisoIngresoUser;
    private javax.swing.JCheckBox chbPermisoIngresoUser1;
    private javax.swing.JCheckBox chbPermisoIngresoUserTipo;
    private javax.swing.JCheckBox chbPermisoIngresoUserTipo1;
    private javax.swing.JCheckBox chbPermisoModificarConcepto;
    private javax.swing.JCheckBox chbPermisoModificarConcepto1;
    private javax.swing.JCheckBox chbPermisoModificarDep;
    private javax.swing.JCheckBox chbPermisoModificarDep1;
    private javax.swing.JCheckBox chbPermisoModificarEmpleado;
    private javax.swing.JCheckBox chbPermisoModificarEmpleado1;
    private javax.swing.JCheckBox chbPermisoModificarUser;
    private javax.swing.JCheckBox chbPermisoModificarUser1;
    private javax.swing.JCheckBox chbPermisoModificarUserTipo;
    private javax.swing.JCheckBox chbPermisoModificarUserTipo1;
    private javax.swing.JCheckBox chbPermisoNomina;
    private javax.swing.JCheckBox chbPermisoPastel;
    private javax.swing.JCheckBox chbPermisoPastel1;
    private javax.swing.JCheckBox chbPermisoTabla;
    private javax.swing.JCheckBox chbPermisoTabla1;
    private javax.swing.JCheckBox chbPermisoUsuario;
    private javax.swing.JCheckBox chbPorId;
    private javax.swing.JCheckBox chbPorIdConcepto;
    private javax.swing.JCheckBox chbPorNombreUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_Buscar;
    private javax.swing.JButton jButton_BuscarE;
    private javax.swing.JButton jButton_Eliminar;
    private javax.swing.JButton jButton_EliminarE;
    private javax.swing.JButton jButton_Ingresar;
    private javax.swing.JButton jButton_InsertarE;
    private javax.swing.JButton jButton_InsertarE2;
    private javax.swing.JButton jButton_Modificar;
    private javax.swing.JButton jButton_ModificarE;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDayChooser jDayChooser2;
    private com.toedter.calendar.JDayChooser jDayChooser3;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_Plataforma;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JScrollPane jspModificar_UsuariosTipo;
    private javax.swing.JScrollPane jspnlModificar_Usuarios;
    private javax.swing.JLabel label_status;
    private javax.swing.JLabel label_statuspuesto;
    private javax.swing.JLabel lblAsterisco;
    private javax.swing.JLabel lblAsterisco1;
    private javax.swing.JLabel lblAsterisco10;
    private javax.swing.JLabel lblAsterisco11;
    private javax.swing.JLabel lblAsterisco12;
    private javax.swing.JLabel lblAsterisco13;
    private javax.swing.JLabel lblAsterisco14;
    private javax.swing.JLabel lblAsterisco15;
    private javax.swing.JLabel lblAsterisco16;
    private javax.swing.JLabel lblAsterisco17;
    private javax.swing.JLabel lblAsterisco18;
    private javax.swing.JLabel lblAsterisco19;
    private javax.swing.JLabel lblAsterisco2;
    private javax.swing.JLabel lblAsterisco20;
    private javax.swing.JLabel lblAsterisco21;
    private javax.swing.JLabel lblAsterisco22;
    private javax.swing.JLabel lblAsterisco23;
    private javax.swing.JLabel lblAsterisco24;
    private javax.swing.JLabel lblAsterisco3;
    private javax.swing.JLabel lblAsterisco4;
    private javax.swing.JLabel lblAsterisco5;
    private javax.swing.JLabel lblAsterisco6;
    private javax.swing.JLabel lblAsterisco7;
    private javax.swing.JLabel lblAsterisco8;
    private javax.swing.JLabel lblAsterisco9;
    private javax.swing.JLabel lblClaro;
    private javax.swing.JLabel lblConceptos;
    private javax.swing.JLabel lblConfirmar;
    private javax.swing.JLabel lblConfirmar1;
    private javax.swing.JLabel lblConsultaUser;
    private javax.swing.JLabel lblConsultaUserTipo;
    private javax.swing.JLabel lblConsulta_NominaEmpleado;
    private javax.swing.JLabel lblConsulta_NominaEmpleado1;
    private javax.swing.JLabel lblConsulta_NominaEmpleado2;
    private javax.swing.JLabel lblConsulta_NominaEmpleado3;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblCorreo1;
    private javax.swing.JLabel lblCorreo2;
    private javax.swing.JLabel lblDep;
    private javax.swing.JLabel lblEliminarUser;
    private javax.swing.JLabel lblEliminarUserTipo;
    private javax.swing.JLabel lblEliminar_NominaEmpleado;
    private javax.swing.JLabel lblEliminar_NominaEmpleado1;
    private javax.swing.JLabel lblEliminar_NominaEmpleado2;
    private javax.swing.JLabel lblEliminar_NominaEmpleado3;
    private javax.swing.JLabel lblEmpleados;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblIP1;
    private javax.swing.JLabel lblIP2;
    private javax.swing.JLabel lblIdeIncio;
    private javax.swing.JLabel lblIdeIncio1;
    private javax.swing.JLabel lblIdeIncio2;
    private javax.swing.JLabel lblIinicio;
    private javax.swing.JLabel lblIngresarUser;
    private javax.swing.JLabel lblIngresoUser;
    private javax.swing.JLabel lblIngresoUserTipo;
    private javax.swing.JLabel lblIngreso_NominaEmpleado;
    private javax.swing.JLabel lblIngreso_NominaEmpleado1;
    private javax.swing.JLabel lblIngreso_NominaEmpleado2;
    private javax.swing.JLabel lblIngreso_NominaEmpleado3;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogo1;
    private javax.swing.JLabel lblMAC;
    private javax.swing.JLabel lblMAC1;
    private javax.swing.JLabel lblMAC2;
    private javax.swing.JLabel lblMail;
    private javax.swing.JLabel lblMail1;
    private javax.swing.JLabel lblModificarUser;
    private javax.swing.JLabel lblModificarUserTipo;
    private javax.swing.JLabel lblModificar_NominaEmpleado;
    private javax.swing.JLabel lblModificar_NominaEmpleado1;
    private javax.swing.JLabel lblModificar_NominaEmpleado2;
    private javax.swing.JLabel lblModificar_NominaEmpleado3;
    private javax.swing.JLabel lblNombreDeUsuario;
    private javax.swing.JLabel lblNombreDeUsuario1;
    private javax.swing.JLabel lblNombreDeUsuario2;
    private javax.swing.JLabel lblNomina;
    private javax.swing.JLabel lblNomina1;
    private javax.swing.JLabel lblOscuro;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPass1;
    private javax.swing.JLabel lblPermisos;
    private javax.swing.JLabel lblPermisos2;
    private javax.swing.JLabel lblPermisos3;
    private javax.swing.JLabel lblPermisos4;
    private javax.swing.JLabel lblPrimerNombre;
    private javax.swing.JLabel lblPrimerNombre1;
    private javax.swing.JLabel lblPrimerNombre10;
    private javax.swing.JLabel lblPrimerNombre2;
    private javax.swing.JLabel lblPrimerNombre3;
    private javax.swing.JLabel lblPrimerNombre4;
    private javax.swing.JLabel lblPrimerNombre5;
    private javax.swing.JLabel lblPrimerNombre6;
    private javax.swing.JLabel lblPrimerNombre7;
    private javax.swing.JLabel lblPrimerNombre8;
    private javax.swing.JLabel lblPrimerNombre9;
    private javax.swing.JLabel lblPuestos;
    private javax.swing.JLabel lblQR;
    private javax.swing.JLabel lblQR1;
    private javax.swing.JLabel lblQR2;
    private javax.swing.JLabel lblTabla;
    private javax.swing.JLabel lblTabla_Nomina;
    private javax.swing.JLabel lblTel;
    private javax.swing.JLabel lblTel1;
    private javax.swing.JLabel lblTel2;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTipo1;
    private javax.swing.JLabel lblTipo2;
    private javax.swing.JLabel lblTipodeUser;
    private javax.swing.JLabel lblTipodeUser1;
    private javax.swing.JLabel lblTipodeUser2;
    private javax.swing.JLabel lblUsernameInicio;
    private javax.swing.JLabel lblUsuarios;
    private javax.swing.JLabel lblVeces;
    private javax.swing.JLabel lbl_reloj;
    private javax.swing.JLabel lbllogouser;
    private javax.swing.JLabel lblname;
    private javax.swing.JLabel lblname1;
    private javax.swing.JLabel lblname10;
    private javax.swing.JLabel lblname11;
    private javax.swing.JLabel lblname2;
    private javax.swing.JLabel lblname3;
    private javax.swing.JLabel lblname4;
    private javax.swing.JLabel lblname5;
    private javax.swing.JLabel lblname6;
    private javax.swing.JLabel lblname7;
    private javax.swing.JLabel lblname8;
    private javax.swing.JLabel lblname9;
    private javax.swing.JLabel lblnameBitacora;
    private javax.swing.JLabel lblnameBitacora1;
    private javax.swing.JLabel lblnameBitacora2;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lblnombre1;
    private javax.swing.JLabel lblnombre2;
    private javax.swing.JLabel lbltipodeUsuario;
    private javax.swing.JPanel pnlAyuda;
    private javax.swing.JPanel pnlBienvenida;
    private javax.swing.JPanel pnlBuscarIDConsulta;
    private javax.swing.JPanel pnlBuscarIDConsulta1;
    private javax.swing.JPanel pnlBuscarIDEliminar;
    private javax.swing.JPanel pnlBuscarIDEliminar1;
    private javax.swing.JPanel pnlBuscarIDEliminar2;
    private javax.swing.JPanel pnlBuscarIDModificar;
    private javax.swing.JPanel pnlCatalogo;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlConceptos;
    private javax.swing.JPanel pnlConfirmar;
    private javax.swing.JPanel pnlConfirmar1;
    private javax.swing.JPanel pnlConsultaConcepto;
    private javax.swing.JPanel pnlConsultaDep;
    private javax.swing.JPanel pnlConsultaEmpleado;
    private javax.swing.JPanel pnlConsultaPuesto;
    private javax.swing.JPanel pnlConsulta_Usuarios;
    private javax.swing.JPanel pnlConsulta_UsuariosTipo;
    private javax.swing.JPanel pnlContacto;
    private javax.swing.JPanel pnlContacto1;
    private javax.swing.JPanel pnlContacto2;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlContraseña;
    private javax.swing.JPanel pnlContraseña1;
    private javax.swing.JPanel pnlCuerpoNomina;
    private javax.swing.JPanel pnlCuerpoUsuarios;
    private javax.swing.JPanel pnlDepartamentos;
    private javax.swing.JPanel pnlDetalles;
    private javax.swing.JPanel pnlDetalles1;
    private javax.swing.JPanel pnlDetalles2;
    private javax.swing.JPanel pnlEliminarConcepto;
    private javax.swing.JPanel pnlEliminarDep;
    private javax.swing.JPanel pnlEliminarEmpleado;
    private javax.swing.JPanel pnlEliminarPuesto;
    private javax.swing.JPanel pnlEliminar_Usuarios;
    private javax.swing.JPanel pnlEliminar_UsuariosTipo;
    private javax.swing.JPanel pnlEmpleados;
    private javax.swing.JPanel pnlEncabezados;
    private javax.swing.JPanel pnlGraph;
    private javax.swing.JPanel pnlHerramientas;
    private javax.swing.JPanel pnlID;
    private javax.swing.JPanel pnlID1;
    private javax.swing.JPanel pnlIDTipoUsuario;
    private javax.swing.JPanel pnlIDTipoUsuario1;
    private javax.swing.JPanel pnlIInicio;
    private javax.swing.JPanel pnlInformes;
    private javax.swing.JPanel pnlIngresoConcepto;
    private javax.swing.JPanel pnlIngresoDep;
    private javax.swing.JPanel pnlIngresoEmpleado;
    private javax.swing.JPanel pnlIngresoPuesto;
    private javax.swing.JPanel pnlIngreso_Usuarios;
    private javax.swing.JPanel pnlIngreso_UsuariosTipo;
    private javax.swing.JPanel pnlInicio;
    private javax.swing.JPanel pnlInicio1;
    private javax.swing.JPanel pnlLogo;
    private javax.swing.JPanel pnlMail;
    private javax.swing.JPanel pnlMail1;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenuInicio;
    private javax.swing.JPanel pnlMenuUsuarios;
    private javax.swing.JPanel pnlMenu_barUser;
    private javax.swing.JPanel pnlModificarConcepto;
    private javax.swing.JPanel pnlModificarDep;
    private javax.swing.JPanel pnlModificarEmpleado;
    private javax.swing.JPanel pnlModificarPuesto;
    private javax.swing.JPanel pnlModificar_Usuarios;
    private javax.swing.JPanel pnlModificar_UsuariosTipo;
    private javax.swing.JPanel pnlNombreDeUsuario;
    private javax.swing.JPanel pnlNombreDeUsuario1;
    private javax.swing.JPanel pnlNombreDeUsuario2;
    private javax.swing.JPanel pnlNombreDeUsuario3;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JPanel pnlOpcionesTipo;
    private javax.swing.JPanel pnlOpciones_NominaConceptos;
    private javax.swing.JPanel pnlOpciones_NominaDepartamentos;
    private javax.swing.JPanel pnlOpciones_NominaEmpleados;
    private javax.swing.JPanel pnlOpciones_NominaPuestos;
    private javax.swing.JPanel pnlOpciones_ventana;
    private javax.swing.JPanel pnlProcesos;
    private javax.swing.JPanel pnlPuestos;
    private javax.swing.JPanel pnlSubOpcionGrafica;
    private javax.swing.JPanel pnlSubOpcionGrafica2;
    private javax.swing.JPanel pnlSubOpcionTema;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlTelefono;
    private javax.swing.JPanel pnlTelefono1;
    private javax.swing.JPanel pnlTemas;
    private javax.swing.JPanel pnlizquierdo;
    private javax.swing.JTable tblConceptos;
    private javax.swing.JTable tblDep;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTable tblPlanillaDet;
    private javax.swing.JTable tblPlanillaGen;
    private javax.swing.JTable tblPuestos;
    private javax.swing.JTable tblTipodeUsuarioConsulta;
    private javax.swing.JTable tblUsuariosConsulta;
    private javax.swing.JTable tblUsuariosEliminar;
    private javax.swing.JTable tblUsuariosEliminarTipo;
    private javax.swing.JTable tblUsuariosModificar;
    private javax.swing.JTable tblUsuariosModificarTipo;
    private javax.swing.JTextField txtBuscarIDConsulta;
    private javax.swing.JTextField txtBuscarIDConsultaTipo;
    private javax.swing.JTextField txtBuscarIDEliminar;
    private javax.swing.JTextField txtBuscarIDEliminarTipo;
    private javax.swing.JTextField txtBuscarIDModificar;
    private javax.swing.JTextField txtBuscarIDModificarTipo;
    private javax.swing.JTextField txtBuscarTabla;
    private javax.swing.JPasswordField txtConfirmarUser;
    private javax.swing.JTextField txtConfirmarUserModficar;
    private javax.swing.JPasswordField txtContraseñaUser;
    private javax.swing.JTextField txtContraseñaUserModificar;
    private javax.swing.JTextField txtIDTipoUsuario;
    private javax.swing.JTextField txtIDTipoUsuarioModificar;
    private javax.swing.JTextField txtIDUsuario;
    private javax.swing.JTextField txtIDUsuarioModificar;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtMailModificar;
    private javax.swing.JTextField txtNombreDeModificar;
    private javax.swing.JTextField txtNombreDeTipoUsuario;
    private javax.swing.JTextField txtNombreDeTipoUsuarioModificar;
    private javax.swing.JTextField txtNombreDeUsuario;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoModificar;
    private javax.swing.JTextField txtValorConcepto;
    private javax.swing.JTextField txt_Apellido_Empleado1;
    private javax.swing.JTextField txt_Apellido_Empleado2;
    private javax.swing.JTextField txt_Buscar;
    private javax.swing.JTextField txt_BuscarE;
    private javax.swing.JTextField txt_Clase_Concepto_Planilla1;
    private javax.swing.JTextField txt_DPI_Empleado1;
    private javax.swing.JTextField txt_DPI_Empleado2;
    private javax.swing.JTextField txt_Estado_Empleado2;
    private javax.swing.JTextField txt_IDConceptoPlanilla;
    private javax.swing.JTextField txt_IDD_Empleado2;
    private javax.swing.JTextField txt_IDEmpleadoPlanilla;
    private javax.swing.JTextField txt_IDP_Empleado2;
    private javax.swing.JTextField txt_IDU_Concepto_Planilla1;
    private javax.swing.JTextField txt_IDU_Empleado2;
    private javax.swing.JTextField txt_ID_Concepto_Planilla;
    private javax.swing.JTextField txt_ID_Concepto_Planilla1;
    private javax.swing.JTextField txt_ID_Empleado;
    private javax.swing.JTextField txt_ID_Empleado1;
    private javax.swing.JTextField txt_Nombre_Concepto_Planilla;
    private javax.swing.JTextField txt_Nombre_Concepto_Planilla1;
    private javax.swing.JTextField txt_Nombre_Empleado1;
    private javax.swing.JTextField txt_Nombre_Empleado2;
    private javax.swing.JTextField txt_Sueldo_Empleado;
    private javax.swing.JTextField txt_Sueldo_Empleado1;
    private javax.swing.JTextField txt_Telefono_Empleado1;
    private javax.swing.JTextField txt_Telefono_Empleado2;
    private javax.swing.JTextField txt_Tipo_Concepto_Planilla1;
    private javax.swing.JTextField txt_Valor_Concepto_Planilla;
    private javax.swing.JTextField txt_Valor_Concepto_Planilla1;
    private javax.swing.JTextField txt_buscardep;
    private javax.swing.JTextField txt_buscarpuesto;
    private javax.swing.JTextField txt_buscarpuesto1;
    private javax.swing.JTextField txt_estadopuesto1;
    private javax.swing.JTextField txt_iddep;
    private javax.swing.JTextField txt_idpuesto;
    private javax.swing.JTextField txt_idpuesto1;
    private javax.swing.JTextField txt_nombredep;
    private javax.swing.JTextField txt_nombrepuesto;
    private javax.swing.JTextField txt_nombrepuesto1;
    // End of variables declaration//GEN-END:variables
}
