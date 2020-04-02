package Nomina_de_salarios;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Main {

    public static void main(String[] args){

       try{
            /**
             * Sirve para diseño de JOptionPane fondo y botones
             */
          JFrame.setDefaultLookAndFeelDecorated(true);
           JDialog.setDefaultLookAndFeelDecorated(true);



            /**
             * 4 Temas a utilizar de libreria
             * javax.swing(Utilizamos la opcion 3)
             */
             //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
           // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            
            

            /**
             * Agregamos colores a botones,fondo,fondo de Boton,Mensaje de Regreso
             * con la libreria javax.swing
             */
            UIManager.put("OptionPane.background", new Color(24, 24, 24));
            UIManager.put("Panel.background", new Color(19, 19, 19));
            UIManager.put("Button.background", new Color(235, 235, 235));
            UIManager.put("Button.foreground", new Color(27, 27, 25));
            UIManager.put("OptionPane.messageForeground", new Color(166, 166, 166));

            /**
             * Agregamos colores a botones,fondo,fondo de Boton,Mensaje de Regreso,
             * celdas y titulos con la libreria javax.swing en tabla de mostrar bitacora
             */
            UIManager.put("Table.background", new Color(19, 19, 17));
            UIManager.put("Table.foreground", new Color(166, 166, 166));
            UIManager.put("Table.gridColor", new Color(58, 88, 166));
            UIManager.put("Table.selectionForeground", new Color(37, 38, 37));
            UIManager.put("Table.selectionBackground", new Color(243, 239, 243));
            UIManager.put("TableHeader.background", new Color(207, 198, 72));
            UIManager.put("TableHeader.foreground", new Color(7, 6, 7));
            
          
            


        }
        /**
         * (try y catch): Sirve para que cuando un proceso entre a mi try y encuentre un error o excepcion
         * en el catch nos muestre el error
         */ catch (Exception e) {
            /**
             *  (printStackTrace):Imprime el error junto con otros detalles como el
             *  número de línea y el nombre de la clase donde ocurrió la excepción.
             */
            e.printStackTrace();
        }
        /**
         * Creamos Objeto de Clase Login para poder usar metodos de la Login
         */
        Login User = new Login();
        /**
         * (op):Variable de Opcion para Switch
         */
        int op = 0;
        /**
         * Utlizamos ciclo Do while que sale del ciclo al
         * seleccionar la opcion Salir (op = 4)
         */
        do {
            /**
             * (Icon):Objeto de librera (javax.swing.Icon) y (javax.swing.ImageIcon)
             * que nos sirve para poder ingresar una imagen en un JOptionPane
             */
            Icon icon = new ImageIcon(Main.class.getResource("/Imagenes/2.png"));
           
           
            
                /**
                 * (op):Le pide al usuario un entero
                 * (parentComponent):Agregar un componente(Ejemplo:un objeto) en este caso no utilizamos un componente por lo que colocamos un null
                 * (message):Ingresamos un String en este caso nuestros titulos
                 * (title):Ingresamos un String como Titulo en este caso es (Login)
                 * (icon):Ingresamos una imagen
                 * (selectionValues):Pide un objeto en este caso nosotros no le ponemos por lo que es(null)
                 * (initialSelectionValue):Pide un objeto en este caso nosotros no le ponemos por lo que es(null)
                 */
             boolean letra;
              do{
            try{
                letra=true;
            op = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Proyeto realizado parala la administracion de una planilla de sueldos\n Implementacion de JAVA + MySQL\n Excel, PDF, Codigos QR, Google Gmail, Notificaciones,\n Temas, Archivos planos, Graficas\n\n"+"<< LOGIN >>\n\n"+"1. INGRESAR" + "\n" + "2. RECUPERAR CONTRASEÑA" + "\n" + "3. SALIR" + "\n\n" + "DIGITE LA OPCION: " +
                        "", "Login", JOptionPane.INFORMATION_MESSAGE, icon, null, null));
            
             }catch(Exception e){
             Icon error = new ImageIcon(Main.class.getResource("/Imagenes/ErrorNum.png"));
            JOptionPane.showMessageDialog(null,  "EL DATO INGRESADO ES INCORRECTO", "ERROR", JOptionPane.INFORMATION_MESSAGE,error);
            letra=false;
             }
           }
          while(letra != true);
            
                
             
               
                /**
                 * (NumberFormatException): Indicar que el proceso ha intentado
                 * convertir una cadena a uno de los tipos numéricos, pero que la cadena no tiene el formato apropiado.
                 */
            

                    /**
             * switch(op):Condicionales Para ingresar a una opcion
             */
            switch (op) {
                case 1:

                    /**
                     * (User.Ingreso()):Manda a llamar al metodo Ingreso del objeto (User)
                     */
                    User.Ingreso();

                    break;

                case 2:
                    /**
                     * (User.RecuperarUser()):Manda a llamar al metodo RecuperarUser del objeto (User)
                     */
                    User.RecuperarUser();
                    break;
                    
                case 3:
                    DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                    DesktopNotify.showDesktopMessage("Evento de Salida", "Gracias por confiar en nosotros, Por favor de click en este agredecimiento", DesktopNotify.INFORMATION,new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                              Icon mensaje = new ImageIcon(Main.class.getResource("/Imagenes/Mensaje.png"));
                            JOptionPane.showMessageDialog(null, "Este es un mensaje de agredeciemiento, creado como resultado\nde los evento realizados en nuestro programa, y con esto\nconcluye su sesion del dia.\nEn futuras versiones se pueden incluir nuevas funciones y\nopciones para mejorar y personalizar aún más el proyecto.\nDesarrollado por estudiantes de la Facultad de Ingenieria en sistemas UMG\n\nPuede enviar sugerencias a: tunewayniac@gmail.com\n\n¡Gracias por descargar este software!", "Acción", JOptionPane.INFORMATION_MESSAGE,mensaje);

                        }
                    }
                    );
                    break;
            }
        } while (op != 3);
    }
    
  
}


