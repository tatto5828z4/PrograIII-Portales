package Nomina_de_salarios;

import javax.swing.*;
import java.awt.*;

/**
 * (Tabla extends JFrame):Sirve para ejecutar un Jframe(una tabla)
 */
public class Matriz extends JFrame
{
    /**
     * (cantaux):Cantidad de Usuarios Existentes en el Archivo
     */
    int filas;
    int columnas;
    /**
     * (Nomina_jtable[][]):Matriz Para guardar datos de Empleados existentes
     */
    String[][] Matriz_jtable;
    /**
     * (nomina[][]): Matriz de tipo Objeto para guardar la Matriz(Nomina_jtable)
     */
    Object[][] table;
    /**
     * (encabezado[]):Vector que contiene los titulos de mi Tabla(Nombres,etc)
     */
    String[] encabezado;
    String[] Titulos;

    /**
     *
     * (Nomina_jtable[][]):Parametro a pedir cuando mandemos
     * a pedir nuestro contructorfuncion  para guardar datos de Empleados existentes
     *
     * (cantaux):Parametro a pedir cuando se mande a llamar nuestro constructorfuncion
     * que es la cantidad de Usuarios Existentes en el Archivo
     */
    public Matriz(String[] Titulos,String[][] Matriz_jtable, int filas, int columnas)
    {
        /**
         * (this.nomia):Le decimos clase que la nomina de mi constructor osea( this.nomina) es igual
         * a la nomina puesta en mis atributos osea (Object[][]  nomina).
         * this.nomina = new Object[cantaux+1][15] : (filas = cantaux+1):Se crea un nuevo Objeto porque para agregar
         * una fila llamada(Totales) se necesita un espacio mas en mi matriz.
         * [Columnas = 15]:Porque solo vamos a tener 15 titulos osea "15 Columnas".
         *
         * Ejemplo:
         * Si yo Ingreso 4 empleados (cantaux = 4) significa que vamos a tener 4 empleados lo que significa
         * que (filas = 4) pero en una nomina se necesita una suma de totales osea otra ""FILA"por lo que le agregamos
         * un valor mas a mi fila osea que mi filas serian(filas = 4+1) osea(filas = [cantaux+1][15]) se preguntaran
         * porque (Columnas = 15) es igual a "15" porque al usuario se le va a pedir determinadas cosas como su sueldo,
         * nombre y cosas parecidas , pero que en total son 15 a lo que nos lleva que mi nomina va a tener una cantidad
         * de (nomina[4+1][15]) en este ejemplo.
         */
        this.filas= filas;
        this.columnas= columnas;
        this.table = new Object[filas][columnas];
        this.encabezado = new String[columnas];
        this.Matriz_jtable = new String[filas][columnas];
        this.Titulos = new String[columnas];

        /**
         * Bucle For para llenar los Titulos
         */
        for(int j =0; j < columnas; j++)
        {
            /**
             * (Nomina_jtable[0][j]):Van a ser mis titulos en la Clase(Menu), a la hora de llamar
             * la funcion (llenarMatriz()) de mi clase Menu por lo que Nomina_jtable[0][i] = Titulos[i]
             * de mi clase Menu
             */
            encabezado[j] = Titulos[j];
        }

        /**
         * Bucles for para llenar los Totales
         * (i < cantaux+2):Se coloco asi porque para que agarre los totales,se preguntaran porque (cantaux+2)
         * y no (cantaux+1) esto es por que si ponemos (cantaux+1) no nos toma encuenta la fila de los totales
         * pero si ponemos (cantaux+2) si lo toma encuenta, esto por que a la hora de llenar la matriz (nomina[i-1][j])
         * este(nomina[i-1]):hace que nuestra matriz no tome encuenta nuestra fila de totales, por es se le agrega
         * (cantaux+2) para que no reste mi fila de totales
         */
        for(int i =0; i < filas;i++)
        {
            for(int j =0; j < columnas; j++)
            {
                table[i][j] = Matriz_jtable[i+1][j];
            }
        }

        /**
         * (JTable tabla):Se crea este objeto de tipo Jtable de las librerias(javax.swing) y
         * (java.awt.) para crear una tabla, que pide como parametros un objeto en este caso
         * (nomina) y pide un vector en este caso (encabezado).
         */
        JTable tabla = new JTable(table,encabezado);
       
        

        /**
         * (JScrollPane scroll): Se crea un Objeto de tipo (JScrollPane) en este caso scroll
         * que pide de parametro un JTable en este caso nosotros ingresamos (tabla),esto sirve
         * para mover mi tabla de arriba,abajo,izquierda,derecha
         */
        JScrollPane scroll = new JScrollPane(tabla);
        /**
         * (x):Posicion inicial Horizontal
         * (y):Posicion inicial Vertical
         * (width):Dando Ancho a mi tabla
         * (Height):Dando Alto a mi tabla
         */
        setBounds(350,130,500,500);
       
        /**
         * (setVisible(true)):Sirve para poder mostrar una tabla tiene un parametro booleano
         * (true):Para que lo muestre
         * (false):Para que no lo muestre
         */
        setVisible(true);
        /**
         *(setResizable):Un método para garantizar que una interfaz gráfica tenga el aspecto
         * que desea es evitar que el usuario cambie su tamaño
         */
        setResizable(true);
        /**
         * (setSize):Da el tamaño que queremos a las letras
         * (width):Ancho de la letra.
         * (Height):Alto de l Letra
         */
        setSize(700,600);
        /**
         * (add(new Panel()):Agrega un panel
         */
        //add(new Panel());
        

        /**
         * (add(scroll)) = Agrega nuestro Scroll
         */
       add(scroll);
       //add(tabla);
      
        /**
         * (setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)): Sirve para que tengamos opcion de
         * salir por defecto(es decir que al salir no alla ningun problema)
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * (Icon Table):Agregamos una Imagen a nuestro JOptionPane
         */
        Icon Table = new ImageIcon(getClass().getResource("/Imagenes/User.png"));
        /**
         *(parentComponent):Agregar un componente(Ejemplo:un objeto) en este caso no utilizamos un componente por lo que colocamos un null
         *(message):Ingresamos un String en este caso le colocamos(ACEPTAR PARA CERRAR TABLA)
         *(title):Ingresamos un String como Titulo en este caso es (TABLA CON VALORES)
         *(icon):Ingresamos una imagen
         */
       JOptionPane.showMessageDialog(null, "ACEPTAR PARA CERRAR TABLA ", "USUARIOS", JOptionPane.INFORMATION_MESSAGE,Table);
        /**
         * (setVisible(false)):Cierra Mi tabla
         */
        setVisible(false);
    }


}
