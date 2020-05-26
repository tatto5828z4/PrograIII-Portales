package Agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * @author Brayan Cifuentes
 */

public class Actividad {
    private String titulo;
    private String descripcion;
    private String tipoActividad;
    private Calendar fechaActividad;
    private String prioridad;
    private ArrayList<Actividad> listaInmediata;
    private ArrayList<Actividad> listaMediana;
    private ArrayList<Actividad> listaBaja;

    public Actividad(String titulo, String descripcion, String tipoActividad, Calendar fechaActividad, String prioridad) {
        this.listaInmediata=new ArrayList<>();
        this.listaMediana=new ArrayList<>();
        this.listaBaja=new ArrayList<>();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipoActividad = tipoActividad;
        this.fechaActividad = fechaActividad;
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void eliminar(String fechaSY){
        String fecha="";
        int cont=0;
        boolean aux=false;
            Iterator<Actividad> it = listaInmediata.iterator();
            while(it.hasNext()){
                Actividad act = it.next();
                fecha=Integer.toString(act.getFechaActividad().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.MONTH)+1)+"/"+Integer.toString(act.getFechaActividad().get(Calendar.YEAR))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.HOUR_OF_DAY));
                if(fecha.equals(fechaSY)==true){
                    it.remove();
                    aux=true;
                    break;
                }
                cont++;
            }
            if(aux==true){
                listaInmediata.remove(cont); 
            }
            cont=0;
            aux=false;
            Iterator<Actividad> it1 = listaMediana.iterator();
            while(it1.hasNext()){
                Actividad act = it1.next();
                fecha=Integer.toString(act.getFechaActividad().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.MONTH)+1)+"/"+Integer.toString(act.getFechaActividad().get(Calendar.YEAR))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.HOUR_OF_DAY));
                if(fecha.equals(fechaSY)==true){
                    it1.remove();
                    aux=true;
                    break;
                }
                cont++;
            }
            if(aux==true)
                listaMediana.remove(cont);
            cont=0;
            aux=false;
            Iterator<Actividad> it2 = listaBaja.iterator();
            while(it2.hasNext()){
                Actividad act = it2.next();
                fecha=Integer.toString(act.getFechaActividad().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.MONTH)+1)+"/"+Integer.toString(act.getFechaActividad().get(Calendar.YEAR))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.HOUR_OF_DAY));
                if(fecha.equals(fechaSY)==true){
                    it2.remove();
                    aux=true;
                    break;
                }
                cont++;
            }
            if(aux==true)
                listaBaja.remove(cont);
    }
    
    public String imprimirInmediato(String fecha){
        String cadena="INMEDIATO";
        String fec="";
        System.out.println("INMEDIATO:\n");
        Iterator<Actividad> it = listaInmediata.iterator();
        while(it.hasNext()){
            Actividad act = it.next();
            //System.out.print(act.getTitulo()+"\n ");
            fec=Integer.toString(act.getFechaActividad().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.MONTH)+1)+"/"+Integer.toString(act.getFechaActividad().get(Calendar.YEAR));
            //System.out.println("fecha actual: "+fecha);
            if(fecha.equals(fec)==true)
                cadena=cadena+"\n"+act.getTitulo();
        }
        return cadena;
    }
    public  String imprimirMediana(String fecha){
        String cadena="\nMEDIANA";
        String fec="";
        System.out.println("MEDIANA:\n");
        Iterator<Actividad> it = listaMediana.iterator();
        while(it.hasNext()){
            Actividad act = it.next();
            //System.out.print(act+" \n ");
            fec=Integer.toString(act.getFechaActividad().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.MONTH)+1)+"/"+Integer.toString(act.getFechaActividad().get(Calendar.YEAR));
            //System.out.println("fecha actual: "+fecha);
            if(fecha.equals(fec)==true)
                cadena=cadena+"\n"+act.getTitulo();
        }
        return cadena;
    }
    public String imprimirBaja(String fecha){
         String cadena="\nBAJA";
         String fec="";
        System.out.println("BAJA:\n");
        Iterator<Actividad> it = listaBaja.iterator();
        while(it.hasNext()){
            Actividad act = it.next();
            //System.out.print(act+" \n");
            fec=Integer.toString(act.getFechaActividad().get(Calendar.DAY_OF_MONTH))+"/"+Integer.toString(act.getFechaActividad().get(Calendar.MONTH)+1)+"/"+Integer.toString(act.getFechaActividad().get(Calendar.YEAR));
            //System.out.println("fecha actual: "+fecha);
            if(fecha.equals(fec)==true)
                cadena=cadena+"\n"+act.getTitulo();
        }
        return cadena;
    }
    
    public void agregarInmediato(Actividad act){
        listaInmediata.add(act);
    }
    
    public void agregarMediana(Actividad act){
        listaMediana.add(act);
    }
    
    public void agregarBaja(Actividad act){
        listaBaja.add(act);
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public Calendar getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Calendar fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }
    
    
}
