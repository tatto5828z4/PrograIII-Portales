package Nomina_de_salarios;

import java.io.*;
import javax.swing.*;
import com.barcodelib.barcode.QRCode;
//import javafx.scene.media.AudioClip;
import java.applet.AudioClip;

public class Nomina_sueldos
{
    /**
     * (cant):cantidad a ingresar Usuario
     */
    int cant;
    /**
     * (cantaux):Cantidad que tiene el archivo
     */
    int cantaux;
    /**
     * (Titulos[]):Titulos o columnas que va a tener el archivo
     */
    String[] Titulos =  {"Nombre","Puesto","Sueldo Ord", "Sueldo Ext","Bonificaciones",
            "Comisiones","Otros", "TOTAL DEVENGADO","IGGS", "ISR","Anticipos", "Desc. Jud","Otros", "TOTAL DESCUENTOS","TOTAL LIQUIDADO"};
    /**
     * (totales[]):Va a contener todos los totales numericos
     */
    float totales[] = new float[13];
    /**
     * (newArray[]):Vector para ingresar nombres
     */
    String[] newArray = new String[cantaux+cant];
    /**
     * (newArray2[]):Vector para ingresar Puestos
     */
    String[] newArray2 = new String[cantaux+cant];
    /**
     * (ID):ID para pedir a cada empleado
     */
    String ID;
    /**
     * (Nomina_salarios[][]):Contiene titulos y todos los floats y totales
     * pedidos
     */
    float[][] Nomina_salarios = new float[cantaux+cant][13];
    //Constructor

    /**
     * (cant):Cantidad ingresada por el usuario
     * (cantaux):Cantidad de empleados que contiene mi archivo que se le suma a mi vector y matriz
     */
    public Nomina_sueldos(int cant, int cantaux)
    {
        this.cant = cant;
        this.cantaux = cantaux;
        this.newArray = new String[cant+cantaux];
        this.newArray2 = new String[cant+cantaux];
        this.Nomina_salarios = new float[cant+cantaux][13];
        this.totales = new float[13];
    }

    /**
     * Metodo de clase Nomina_sueldos donde tiene como parametros:
     * (Nomina_salariosArray[][]):Que es la matriz que contiene toda la nomina
     * ( nombre[]):Contiene nombres de los empleados.
     * (puesto[]):Contiene puestos de los empleados
     */
    public void Sonidos(String Sonido) throws IOException {
        String tono="/Sonidos/"+Sonido;
        AudioClip player;
        player=java.applet.Applet.newAudioClip(getClass().getResource(tono));
        player.play();

    }
    public void IngresoDatos(float Nomina_salariosArray[][], String nombre[], String puesto[])
    {
        float aux_igss=0;
        float aux_totaldev=0;
        float sueldo_ordinario=0;
        float aux_totaldesc=0;
        float igs=0;
        float isr=0;
        float auxtotales=0;

        Icon Ingresar = new ImageIcon(getClass().getResource("/Imagenes/Altas.png"));

        try
        {
            FileWriter Escribir = new FileWriter("Nomina.txt",true);
            PrintWriter Esc = new  PrintWriter(Escribir);
            /**
             *
             */
            newArray = new String[nombre.length-1 + cant];
            /**
             * src : esta es la matriz fuente.
             *
             * srcPos : esta es la posición inicial en la matriz fuente.
             *
             * dest : esta es la matriz de destino.
             *
             * destPos : esta es la posición inicial en los datos de destino.
             *
             * longitud : este es el número de elementos de matriz que se copiarán.
             */
            System.arraycopy(nombre, 1, newArray, 0, nombre.length-1);
            newArray2 = new String[puesto.length-1 + cant];
            /**
             * src : esta es la matriz fuente.
             *
             * srcPos : esta es la posición inicial en la matriz fuente.
             *
             * dest : esta es la matriz de destino.
             *
             * destPos : esta es la posición inicial en los datos de destino.
             *
             * longitud : este es el número de elementos de matriz que se copiarán.
             */
            System.arraycopy(puesto, 1, newArray2, 0, puesto.length-1);
            Nomina_salarios = new float[cantaux+cant][13];

            /**
             * for para que Nomina_salariosArray[][] se valla haciendo mas grande
             */
            for(int i = 0; i < cantaux; i++)
            {

                for(int j = 0; j < 13;j++){

                    Nomina_salarios[i][j] = Nomina_salariosArray[i][j];

                }

            }

            for(int i = cantaux; i < cantaux+cant;i++)
            {
                ID= (String) JOptionPane.showInputDialog(null,"DIGITE EL ID DE LA PERSONA. -"+(i+1), "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,"");
                do{
                    newArray[i] = (String) JOptionPane.showInputDialog(null,"DIGITE EL NOMBRE DE LA PERSONA NO. -"+(i+1), "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,"");
                    if(BuscarRegistro(newArray[i]) == true){

                        JOptionPane.showMessageDialog(null,"EMPLEADO YA REGISTRADO DIGITE DE NUEVO EL NOMBRE", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar);
                    }

                }
                while(BuscarRegistro(newArray[i]) == true);
                Esc.println(newArray[i]);
                GenerarQR(ID,newArray[i]);
                newArray2[i] = (String) JOptionPane.showInputDialog(null,"DIGITE EL PUESTO DE LA PERSONA NO. -"+(i+1), "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,"");Esc.println(newArray2[i]);
                PrintWriter EscribirEnArchivo = new  PrintWriter(newArray[i]+".txt");
                EscribirEnArchivo.println("DATOS PERSONALES"+"\n");
                EscribirEnArchivo.println("ID: "+ID);
                EscribirEnArchivo.println("Nombre: "+newArray[i]);
                EscribirEnArchivo.println("Puesto: "+newArray2[i]);

                for(int j = 0; j < 13;j++){
                    switch (j){
                        case 0:
                            do{
                                Nomina_salarios[i][j] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE EL SUELDO ORDINARIO: ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                                if(Nomina_salarios[i][j] < 2800){

                                    JOptionPane.showMessageDialog(null,"DIGITE DE NUEVO EL SUELDO ORDINARIO ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar);
                                }
                            }
                            while(Nomina_salarios[i][j] < 2800);
                            aux_igss = aux_igss + Nomina_salarios[i][j];
                            sueldo_ordinario = Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("\nDEVENGADO\n");
                            EscribirEnArchivo.println("Salario Ordinario: "+Nomina_salarios[i][j]);
                            break;
                        case 1:
                            Nomina_salarios[i][j] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE EL SUELDO EXTRAORDINARIO: ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_igss = aux_igss + Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Salario Extraordinario: "+Nomina_salarios[i][j]);
                            break;
                        case 2:
                            Nomina_salarios[i][j]= Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LAS BONIFICACIONES: ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_totaldev= Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Bonificaciones: "+Nomina_salarios[i][j]);
                            break;
                        case 3:
                            Nomina_salarios[i][j]= Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LAS COMISIONES: ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_igss = aux_igss +Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Comisiones: "+Nomina_salarios[i][j]);
                            break;
                        case 4:
                            Nomina_salarios[i][j]= Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LA CANTIDAD DE OTRO TIPO DE MONTO ADICIONAL : ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_igss = aux_igss +Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Otros: "+Nomina_salarios[i][j]);
                            break;
                        case 5:
                            Nomina_salarios[i][j] = aux_igss + aux_totaldev;
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("\nTotal Devengado: "+Nomina_salarios[i][j]);
                            break;
                        case 6:
                            Nomina_salarios[i][j] = (float) (aux_igss*(0.0483));
                            igs = Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("\nDESCUENTOS\n");
                            EscribirEnArchivo.println("Iggs: "+Nomina_salarios[i][j]);
                            break;
                        case 7:
                            if(sueldo_ordinario >= 6000 && sueldo_ordinario <= 7500){
                                Nomina_salarios[i][j]= (float)(sueldo_ordinario*0.05);
                                Esc.println(Nomina_salarios[i][j]);
                            }
                            else if(sueldo_ordinario >= 7500 && sueldo_ordinario <= 9000){
                                Nomina_salarios[i][j]= (float)(sueldo_ordinario*0.06);
                                Esc.println(Nomina_salarios[i][j]);
                            }
                            else if(sueldo_ordinario >= 9000){
                                Nomina_salarios[i][j]= (float)(sueldo_ordinario*0.08);
                                Esc.println(Nomina_salarios[i][j]);
                            }
                            else{
                                Nomina_salarios[i][j]= (float)0;
                                Esc.println(Nomina_salarios[i][j]);
                            }
                            EscribirEnArchivo.println("Isr: "+Nomina_salarios[i][j]);
                            isr = Nomina_salarios[i][j];
                            break;
                        case 8:
                            Nomina_salarios[i][j]= Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LA CANTIDAD DE  ANTICIPOS : ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_totaldesc = aux_totaldesc + Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Anticipos: "+Nomina_salarios[i][j]);
                            break;
                        case 9:
                            Nomina_salarios[i][j]= Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LA CANTIDAD DE DESC.JUD : ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_totaldesc = aux_totaldesc + Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Descuentos Judiciales: "+Nomina_salarios[i][j]);
                            break;
                        case 10:
                            Nomina_salarios[i][j]=Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LA CANTIDAD DE OTRO TIPO DE DESCUENTOS : ", "ALTAS", JOptionPane.INFORMATION_MESSAGE,Ingresar, null,""));
                            aux_totaldesc = aux_totaldesc + Nomina_salarios[i][j];
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Otros descuentos: "+Nomina_salarios[i][j]);
                            break;
                        case 11:
                            Nomina_salarios[i][j]= aux_totaldesc+ igs+isr;
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("Total descuentos: "+Nomina_salarios[i][j]);
                            break;
                        case 12:
                            Nomina_salarios[i][j]=  (aux_igss + aux_totaldev)-(aux_totaldesc+ igs+isr);
                            aux_igss=0;aux_totaldev=0;sueldo_ordinario=0;aux_totaldesc=0; igs=0; isr=0;
                            Esc.println(Nomina_salarios[i][j]);
                            EscribirEnArchivo.println("\nTOTALES\n");
                            EscribirEnArchivo.println("TOTAL LIQUIDADO: "+Nomina_salarios[i][j]);
                            EscribirEnArchivo.close();
                            break;
                    }
                }
            }
            try
            {
                PrintWriter Esc1 = new PrintWriter("Cantidad.txt");
                Esc1.println(cant+cantaux);
                Esc1.close();
            }catch(Exception err)
            {

            }

            /**
             * Matriz Invetida para suma de Totales
             */
            for(int i = 0; i < 13;i++)
            {
                for(int j = 0; j < cantaux+cant;j++)
                {
                    auxtotales = auxtotales + Nomina_salarios[j][i];
                }
                totales[i] = auxtotales;
                auxtotales=0;
            }

            /**
             * Muestra en consola todos los datos
             */
            System.out.println(" ");
            for(int i = 0; i < cant+cantaux; i++)
            {
                System.out.print(i+1+"\033[34m. Nombre: "+newArray[i]);
                System.out.println("\033[33m -> CARGO -- "+newArray2[i]+"  ");

                for(int j = 0; j < 13;j++)
                {
                    System.out.print("\033[37m   "+Titulos[j]+": ");
                    System.out.println("\033[36m"+Nomina_salarios[i][j]);
                }
                System.out.println(" ");
            }
            System.out.print("\033[35m   TOTALES: ");
            for(int j = 0; j < 13;j++){
                System.out.print("\033[37m  "+totales[j]);
            }
            System.out.println("\033[36m");
            Sonidos("Insertado.wav");
            Icon Ingresado = new ImageIcon(getClass().getResource("/Imagenes/Ingresado.png"));
            JOptionPane.showMessageDialog(null, " REGISTRADO EN PLANILLA - SE MOSTRARASU TOTAL LIQUIDO ", "EMPLEADO INGRESADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE,Ingresado);
            Esc.close();
        }
        catch(Exception err){

        }
    }

    /**
     *(ModificarDatos):Metodo de la Clase Nomina_sueldos que sirve para modificar datos que tiene como parametros:
     * (NominaAModificar[][]):Nos a servir para acceder a cualquier dato de nuestra matriz floats tambien para modificarlos
     * (nombre[]):Vector que nos va a servir para acceder a los nombres y para poder modificarlos.
     * (puesto[]):Vector que nos va a servir para acceder a los puestos y para poder modificarlos
     */
    public void ModificarDatos(float NominaAModificar[][], String nombre[], String puesto[]){
        String PersonaAModificar=" ";
        Icon Modificar = new ImageIcon(getClass().getResource("/Imagenes/Cambios.png"));

        int op=0, posicion=0;
        if(cantaux > 0){
            do{
                PersonaAModificar = (String) JOptionPane.showInputDialog(null, "DIGITE EL NOMBRE DE LA PERSONA. -", "CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,"");
                if(BuscarRegistro(PersonaAModificar) == false){
                    JOptionPane.showMessageDialog(null,"EL EMPLEADO NO EXISTE, DIGITE DE NUEVO EL NOMBRE", "EMPLEADO NO REGISTRADO", JOptionPane.INFORMATION_MESSAGE,Modificar);
                }

            }
            while(BuscarRegistro( PersonaAModificar) ==false);

        }
        String NuevoNombre=null;
        for(int l = 0;l < cantaux; l++){
           newArray[l]=  nombre[l+1];
            newArray2[l]=  puesto[l+1];

            for(int j = 0; j < 13;j++){

                Nomina_salarios[l][j] = NominaAModificar[l][j];

            }

        }

        for(int i = 0; i < cantaux; i++){

            if(PersonaAModificar.equals(newArray[i])){
               posicion = i;
                do{

                    try{

                        op = Integer.parseInt((String) JOptionPane.showInputDialog(null,"1. NOMBRE"+"\n"+"2. PUESTO"+"\n"+"3. SUELDO ORDINARIO"+"\n"+"4. SUELDO EXTRAORDNARIO"+"\n"
                                +"5. BONIFICACIONES"+"\n"+"6. COMISIONES"+"\n"+"7. OTROS"+"\n"+"8. ANTICIPOS"+"\n"+"9. DESCUENTOS JUDICIALES"+"\n"+"10. OTROS DESCUENTOS"+"\n"
                                +"11. SALIR"+  "\n\n"  + "DIGITE LA OPCION: ", "CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));

                    }catch (NumberFormatException e){

                    }

                    /**
                     * Suma total Devengado
                     */
                    Nomina_salarios[posicion][5] =  Nomina_salarios[posicion][0]+Nomina_salarios[posicion][1]+Nomina_salarios[posicion][2]+Nomina_salarios[posicion][3]+Nomina_salarios[posicion][4];
                    /**
                     * Calculo igss
                     */
                    Nomina_salarios[posicion][6] = (float)( Nomina_salarios[posicion][5]*0.0483);

                    /**
                     * Calculo ISR
                     */
                    if(Nomina_salarios[posicion][0] >= 6000 && Nomina_salarios[posicion][0] <= 7500){
                        Nomina_salarios[posicion][7]= (float)( Nomina_salarios[posicion][0]*0.05);

                    }
                    /**
                     * Calculo ISR
                     */
                    else if(Nomina_salarios[posicion][0] >= 7500 && Nomina_salarios[posicion][0] <= 9000){
                        Nomina_salarios[posicion][7]= (float)(Nomina_salarios[posicion][0]*0.06);

                    }
                    /**
                     * Calculo ISR
                     */
                    else if(Nomina_salarios[posicion][0] >= 9000){
                        Nomina_salarios[posicion][7]= (float)(Nomina_salarios[posicion][0]*0.08);

                    }
                    else{
                        /**
                         * Calculo ISR
                         */
                        Nomina_salarios[posicion][7]= (float)0;
                    }
                    /**
                     * Calculo total liquido
                     */
                    Nomina_salarios[posicion][11]= Nomina_salarios[posicion][6]+ Nomina_salarios[posicion][7]+ Nomina_salarios[posicion][8]
                            + Nomina_salarios[posicion][9]+ Nomina_salarios[posicion][10];
                    /**
                     * Calculo planilla total
                     */
                    Nomina_salarios[posicion][12] =   Nomina_salarios[posicion][5]-Nomina_salarios[posicion][11];
                    switch(op){
                        case 1:
                            for(int j = 0; j < cantaux; j++) {
                                if(j == posicion){
                                    do{
                                        NuevoNombre = (String) JOptionPane.showInputDialog(null,"DIGITE EL NUEVO NOMBRE DE LA PERSONA. -", "CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,"");
                                        if(BuscarRegistro( NuevoNombre) == true){

                                            JOptionPane.showMessageDialog(null,"EMPLEADO YA REGISTRADO DIGITE DE NUEVO EL NOMBRE","EMPLEADO NO REGISTRADO", JOptionPane.INFORMATION_MESSAGE,Modificar);
                                        }
                                    }  while(BuscarRegistro( NuevoNombre) == true);
                                    newArray[j] = NuevoNombre;
                                }
                            }
                            break;
                        case 2:
                            for(int j = 0; j < cantaux; j++) {
                                if(j ==posicion){
                                    String NuevoPuesto = (String) JOptionPane.showInputDialog(null,"DIGITE EL NUEVO PUESTO DE LA PERSONA. -","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,"");
                                    newArray2[j] = NuevoPuesto;
                                }
                            }
                            break;
                        case 3:
                            do{
                                Nomina_salarios[posicion][0] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE EL NUEVO SUELDO ORDINARIO: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                                if(Nomina_salarios[posicion][0] < 2800){
                                    JOptionPane.showMessageDialog(null,"DIGITE DE NUEVO EL SUELDO ORDINARIO ");
                                }
                            }
                            while(Nomina_salarios[posicion][0] < 2800);
                            break;
                        case 4:
                            Nomina_salarios[posicion][1] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE EL NUEVO SUELDO EXTRAORDINARIO: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));

                            break;
                        case 5:
                            Nomina_salarios[posicion][2] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LA NUEVA BONIFICACION: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                            break;
                        case 6:
                            Nomina_salarios[posicion][3] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LA NUEVA COMISION: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                            break;
                        case 7:
                            Nomina_salarios[posicion][4] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE EL NUEVO OTROS: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                            break;
                        case 8:
                            Nomina_salarios[posicion][8] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LOS NUEVOS ANTICIPOS: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                            break;
                        case 9:
                            Nomina_salarios[posicion][9] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LOS NUEVOS DESCUENTOS JUDICIALES: ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                            break;
                        case 10:
                            Nomina_salarios[posicion][10] = Float.parseFloat((String) JOptionPane.showInputDialog(null,"DIGITE LOS OTROS NUEVOS DESCUENTOS ","CAMBIOS", JOptionPane.INFORMATION_MESSAGE,Modificar, null,""));
                            break;
                    }
                }while(op != 11);
            }
        }

        String IDE = "";
        String BfRead;
        try {
            FileReader Leer = new FileReader(PersonaAModificar+".txt");
            BufferedReader Buffer = new BufferedReader(Leer);

            while ((BfRead = Buffer.readLine()) != null) {

                IDE = BfRead;
                IDE =  Buffer.readLine();
                IDE =  Buffer.readLine();


                break;

            }
           Leer.close();

        } catch (Exception err) {


        }
        BorraArchivo(PersonaAModificar);
        BorraImagen(PersonaAModificar);


        try{
            if( NuevoNombre == null){
                NuevoNombre = PersonaAModificar;
            }


            GenerarQR(IDE,NuevoNombre);
            PrintWriter Esc1 = new PrintWriter("Nomina.txt");
            PrintWriter  EscribirEnArchivo = new  PrintWriter(NuevoNombre+".txt");
            EscribirEnArchivo.println("DATOS PERSONALES"+"\n");
            EscribirEnArchivo.println(IDE);
            EscribirEnArchivo.println("Nombre: "+newArray[posicion]);
            EscribirEnArchivo.println("Puesto: "+newArray2[posicion]);
            EscribirEnArchivo.println("\nDEVENGADO\n");
            EscribirEnArchivo.println("Salario Ordinario: "+Nomina_salarios[posicion][0]);
            EscribirEnArchivo.println("Salario Extraordinario: "+Nomina_salarios[posicion][1]);
            EscribirEnArchivo.println("Bonificaciones: "+Nomina_salarios[posicion][2]);
            EscribirEnArchivo.println("Comisiones: "+Nomina_salarios[posicion][3]);
            EscribirEnArchivo.println("Otros: "+Nomina_salarios[posicion][4]);
            EscribirEnArchivo.println("\nTotal Devengado: "+Nomina_salarios[posicion][5]);
            EscribirEnArchivo.println("\nDESCUENTOS\n");
            EscribirEnArchivo.println("Iggs: "+Nomina_salarios[posicion][6]);
            EscribirEnArchivo.println("Isr: "+Nomina_salarios[posicion][7]);
            EscribirEnArchivo.println("Anticipos: "+Nomina_salarios[posicion][8]);
            EscribirEnArchivo.println("Descuentos Judiciales: "+Nomina_salarios[posicion][9]);
            EscribirEnArchivo.println("Otros descuentos: "+Nomina_salarios[posicion][10]);
            EscribirEnArchivo.println("Total descuentos: "+Nomina_salarios[posicion][11]);
            EscribirEnArchivo.println("\nTOTALES\n");
            EscribirEnArchivo.println("TOTAL LIQUIDADO: "+Nomina_salarios[posicion][12]);
            for(int j= 0; j < cantaux; j++) {
                Esc1.println(newArray[j]);
                Esc1.println(newArray2[j]);
                for(int k= 0; k < 13; k++) {
                    Esc1.println(Nomina_salarios[j][k]);
                }
            }
            Sonidos("Modificado.wav");
            Icon Modificado = new ImageIcon(getClass().getResource("/Imagenes/Cambios.png"));
            JOptionPane.showMessageDialog(null, PersonaAModificar+":  MODIFICADO EN PLANILLA - SE MOSTRARASU TOTAL LIQUIDO ", "EMPLEADO MODIFICADO CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE,Modificado);

            Esc1.close();
            EscribirEnArchivo.close();
        }catch(Exception err) {
        }
    }
    /**
     *(EliminarDatos()):Metodo de mi clase Nomina_sueldos que nos sirve para eliminar datos que tiene como parametros:
     * (RegistroAEliminar[][]):Se necesita la matriz donde tenemos los datos a eliminar
     * (nombre[]):Se necesita por que tenesmos que eliminar por medio de un nombre y para poder eliminar un nombre tambien
     * (puesto[]):para poder eliminar un puesto
     */
    public void EliminarDatos(float RegistroAEliminar[][], String nombre[], String puesto[]){
      int UbicarPersona[] = new int[cantaux];
      newArray = new String[nombre.length-1 - cant];
      newArray2 = new String[puesto.length-1 - cant];
      Nomina_salarios = new float[cantaux-cant][13];
      float NominaRegistroEliminado[][] = new float[cantaux][13];
      String[] Empleados = new String[cantaux];
      String[] Puestos = new String[cantaux];
      String[] NombresNoEliminar = new String[cantaux];
      String[]PuestosNoEliminar = new String[cantaux];
      String[]NombresEliminar = new String[cant];
      String PersonaAEliminar ="";
        Icon Eliminar = new ImageIcon(getClass().getResource("/Imagenes/Bajas.png"));

        for(int l = 1;l < cantaux+1; l++){
            Empleados[l-1]=  nombre[l];
            UbicarPersona[l-1] =-1;
            Puestos[l-1] = puesto[l];
        }
        for(int i =0;i < cant;i++) {
            do{

                PersonaAEliminar = (String) JOptionPane.showInputDialog(null,"DIGITE EL NOMBRE DE LA PERSONA A ELIMINAR", "BAJAS", JOptionPane.INFORMATION_MESSAGE,Eliminar, null,"");
                if(BuscarRegistro(PersonaAEliminar) == false){

                    JOptionPane.showMessageDialog(null,"EL EMPLEADO NO EXISTE, DIGITE DE NUEVO EL NOMBRE", "EMPLEADO NO REGISTRADO", JOptionPane.INFORMATION_MESSAGE,Eliminar);
                }

            }
            while(BuscarRegistro(PersonaAEliminar) ==false);

            NombresEliminar[i] = PersonaAEliminar;
            for(int j = 0;j< cantaux;j++){

                if((PersonaAEliminar.equals(Empleados[j]))){

                    UbicarPersona[j] = j;

                }


            }

        }
       /* for(int i =0;i < cantaux-1;i++) {

            for(int j = 0;j< cantaux-1;j++){
            if(UbicarPersona[j] > UbicarPersona[j+1]){
                auxiliar = UbicarPersona[j];
                UbicarPersona[j] =  UbicarPersona[j+1];
                UbicarPersona[j+1] = auxiliar;
            }

            }

        }*/

        int n2=0;
        for( int i = 0;i< cantaux;i++){
         i = Recursividad(i,UbicarPersona);
         if(i == UbicarPersona.length){
             break;
         }
           NombresNoEliminar[n2] = Empleados[i];
           PuestosNoEliminar[n2] = Puestos[i];
            for(int j = 0; j < 13;j++) {
                NominaRegistroEliminado[n2][j] = RegistroAEliminar[i][j];
                if(j == 12){
                    n2++;
                }
            }
        }

        for(int i = 0;i< cantaux-cant;i++){
                newArray[i]= NombresNoEliminar[i];
                  newArray2[i]= PuestosNoEliminar[i];
            for(int j = 0; j < 13;j++) {
                    Nomina_salarios[i][j] =  NominaRegistroEliminado[i][j];
            }
        }
        for(int i = 0; i < cant;i++ ){
            BorraArchivo(NombresEliminar[i]);
            BorraImagen(NombresEliminar[i]);
        }

        try{

    PrintWriter Esc = new  PrintWriter("Nomina.txt");
    PrintWriter Cantidad = new  PrintWriter("Cantidad.txt");
    Cantidad.println(cantaux-cant);
            for(int j= 0; j < cantaux-cant; j++) {
                Esc.println(newArray[j]);
                Esc.println(newArray2[j]);
                for(int k= 0; k < 13; k++) {
                    Esc.println(Nomina_salarios[j][k]);
                }
            }
            Cantidad.close();
            Esc.close();
            Sonidos("Eliminado.wav");
            Icon Eliminado = new ImageIcon(getClass().getResource("/Imagenes/Eliminado.png"));
            JOptionPane.showMessageDialog(null, "ELIMINADOS EN PLANILLA - SE MOSTRARASU TOTAL LIQUIDO ", "EMPLEADO ELIMINADOS CORRECTAMENTE", JOptionPane.INFORMATION_MESSAGE,Eliminado);
     }catch (Exception err){
}


    }
    /**
     *(int i):retorna la posicion deseada
     * (int Posiciones[]):las posiciones a eliminar y las que no
     */
    public int Recursividad(int i, int Posiciones[]){
        if(!(Posiciones[i] == -1)){
            i++;
            if(i == Posiciones.length){
                return i;
            }
           return Recursividad(i, Posiciones);
        }else{
            return i;
        }

    }
    /**
     *(boolean BuscarRegistro):Meotodo de la clase Nomina_sueldos para buscar un Registro tiene como parametro:
     * (NombreAuxiliar):Que nos va a servir para encontrar el registro
     */
    public boolean BuscarRegistro(String NombreAuxiliar){
        try{
            FileReader Leer = new FileReader("Nomina.txt");
            BufferedReader Buffer = new BufferedReader(Leer);
            String temp = " ";
            String BfRead;
                while((BfRead = Buffer.readLine()) != null){
                temp = BfRead;
                if(temp.equals(NombreAuxiliar)){
                    return true;
                }else{
                    for(int i =0; i < 14;i++){
                        BfRead = Buffer.readLine();
                    }
                }
            }

        }catch(Exception err){
            return false;
        }
        return false;
    }
    /**
     *(void BorraArchivo):Metodo de la Clase Nomina_suledos que sirve para borrar un archivo que tiene como parametro:
     * (NombreArchivo):Que sirve para que elimine el File osea mi archivo.
     */
    public void BorraArchivo(String NombreArchivo){
        try{
            File Leer = new File(NombreArchivo+".txt");
            Leer.delete();
        }catch(Exception err){

        }
    }
    /**
     *(void BorraImagen):Metodo de la Clase Nomina_suledos que sirve para borrar una imagen que tiene como parametro:
     * (NombreImagen):Que sirve para que elimine un png osea mi imagen.
     */
    public void BorraImagen(String NombreImagen){
        try{
            File Imagen = new File(NombreImagen+".png");
            Imagen.delete();
        }catch(Exception err){

        }
    }

    /**
     *(void GenerarQR):Metodo para crear un codigo Qr de mi clase Nomina_sueldos que tiene como parametros:
     * (dato): lo que contiene el QR
     * (name): nombre del QR
     */
    public static void GenerarQR(String dato, String name)

    {
        int udm= 0,resolucion=72, rotacion=0;
        float mi=0.000f,md=0.000f,ms=0.000f,min=0.000f,tam=5.00f;
        try
        {
            final String constante = "C:\\Users\\hp\\Documents\\Proyecto-Nomina_de_sueldos";
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

            String archivo = (constante)+("/"+name+".png");
            c.renderBarcode(archivo);


            }catch(Exception e)
            {
            System.out.println("Error " + e);
            }
    }


}
