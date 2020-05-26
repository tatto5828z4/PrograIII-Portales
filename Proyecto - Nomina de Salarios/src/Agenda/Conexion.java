/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Brayan Cifuentes
 */
public class Conexion {
    private static Connection conn;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String user="brayan";
    private static final String password="cifuentes";
    private static final String url="jdbc:mysql://35.225.163.187/Nomina_de_Empleados";

    public Conexion() {
        conn=null;
        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(url, user, password);
            if(conn!=null){
                System.out.println("Conección establecida!");
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar "+e);
        }
    }
    
    //Método que retorna la conexión
    public Connection getConnection(){
        return conn;
    }
    //Método que desconecta con la DB
    public void desconectar(){
        try{
        conn.close();
        System.out.println("Conexión terminada...");
    }catch(SQLException ex){

    }
        /*conn=null;
        if(conn==null){
            System.out.println("Conexión terminada...");
        }*/
    }
    
   
    
    public int enviarActividad(Conexion cc,String titulo,String descripcion,String categoria,Calendar fecha,String prioridad,String sql) throws SQLException{
        Connection cn=cc.getConnection();
        String date=Integer.toString(fecha.get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(fecha.get(Calendar.MONTH)+1)+"/"+Integer.toString(fecha.get(Calendar.YEAR))+"/"+Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
        System.out.println(date);
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, titulo);
        pst.setString(2, descripcion);
        pst.setString(3, categoria);
        pst.setString(4, date);
        pst.setString(5, prioridad);
        
        int n=pst.executeUpdate();
        return n;
    }
    
   
    
    public String consultarActividad(Conexion cc, String sql) throws SQLException{
        String consultaTitulo = "",consultaCategoria="",consultaDes="";
        Connection cn=cc.getConnection();
        Statement st=cn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()){
            consultaTitulo=rs.getString("titulo_actividad");
            consultaCategoria=rs.getString("tipo_actividad");
            consultaDes=rs.getString("descripcion_actividad");
        }
        /*ResultSet rs1=st.executeQuery(sql);
        while (rs1.next()){
            consultaCategoria=rs1.getString("tip_act");
        }
        ResultSet rs2=st.executeQuery(sql);
        while (rs2.next()){
            consultaDes=rs2.getString("des_act");
        }*/
        return (consultaTitulo+"]"+consultaDes+"-"+consultaCategoria);
    }
    
    public String notificacionActividad(Conexion cc,String sql) throws SQLException{
        String consultaTitulo = "",consultaCat="",consultaDes="",consultaFec="",consultaPri="";
        Connection cn=cc.getConnection();
        Statement st=cn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()){
            consultaTitulo=rs.getString("titulo_actividad");
            consultaDes=rs.getString("descripcion_actividad");
            consultaCat=rs.getString("tipo_actividad");
            consultaFec=rs.getString("fecha_actividad");
            consultaPri=rs.getString("prioridad_actividad");
        }
        /*ResultSet rs1=st.executeQuery(sql);
        while (rs1.next()){
            consultaDes=rs1.getString("des_act");
        }
        ResultSet rs2=st.executeQuery(sql);
        while (rs2.next()){
            consultaCat=rs2.getString("tip_act");
        }
        ResultSet rs3=st.executeQuery(sql);
        while (rs3.next()){
            consultaFec=rs3.getString("fec_act");
        }
        ResultSet rs4=st.executeQuery(sql);
        while (rs4.next()){
            consultaPri=rs4.getString("pri_act");
        }*/
        return (consultaTitulo+"]"+consultaDes+"]"+consultaCat+"]"+consultaFec+"]"+consultaPri);
    }
    
    public void eliminarActividad(Conexion cc,String sql) throws SQLException{
        Connection cn=cc.getConnection();
        Statement st=cn.createStatement();
        st.execute(sql);
    }
    
}
