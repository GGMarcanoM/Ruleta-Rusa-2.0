/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ruletarusa2.pkg0;
import java.io.*;
import java.util.Scanner;

class nodo{
    String nombre;
    boolean estado;
    nodo sig;
    
    nodo(String name,boolean condicion){
        nombre = name;
        estado = condicion;
    }
    nodo(String name,boolean condicion, nodo j) {
        this.estado=condicion;
        this.nombre = name;
        this.sig = j;
    }
    
    public String getNombre() {
        return nombre;
    }

    public boolean Condicion() {
        return estado;
    }

    public void muerte() {
        estado = false;
    }
}
class Jugador{
    nodo j;
    nodo aux;
    private int numJugadores;
    private int balas;
    
    public Jugador() {
        numJugadores = 3;
        balas = 1;
    }
    
    static class nodo{
        String nombre;
        boolean estado;
        nodo sig;
        
        nodo(String name, boolean condicion){
            estado = condicion;
            nombre = name;
        }
        
        nodo(String name,boolean condicion, nodo j) {
            this.estado = condicion;
            this.nombre = name;
            this.sig = j;
        }
        public String getNombre() {
        return nombre;
    }

    public boolean Condicion() {
        return estado;
    }

    public void muerte() {
        estado = false;
    }
    }
    
    public void agregarjugador(String name,boolean condicion){
        nodo p = j;
            if (p==null){
               j=new nodo(name, condicion);
               j.sig=j;

           }else{
               aux=j;
               while(aux.sig!=j)
                   aux=aux.sig;
               aux.sig=new nodo(name,condicion, j);
           }
        
   }
    
    void game () throws IOException{
        int participantes = numJugadores;
        int posicionBala = (int) (Math.random() * 6); // Posición de la bala en el tambor (de 0 a 5)
        int tambor = 0;
        aux=j;
        int ronda = 1;
        while (participantes>0){
                
                if (aux.Condicion()){          
                    
                System.out.println(aux.getNombre() + " aprieta el gatillo...");
                ronda=ronda+1;
                if (tambor == posicionBala) {
                { 
                    if (aux.sig.estado == true )
                    {
                    aux.sig.muerte();
                    System.out.println(aux.sig.getNombre() + " ha sido eliminado aloha.");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    FileWriter escribi = new FileWriter("D:\\Derrotados.out.txt", true);
                    BufferedWriter escribi2 = new BufferedWriter(escribi);
                    escribi2.write("en la ronda "+ronda+" murio "+aux.sig.getNombre());
                    escribi2.newLine(); 
                    escribi2.close();
                    participantes--;
                   
                    if (participantes == 1 || participantes == 0){
                        break;}}
                    
                    else if(aux.sig.estado != true){
                        
                    aux.sig.sig.muerte();
                    System.out.println(aux.sig.sig.getNombre() + " ha sido eliminado adios.");
                    FileWriter escribi = new FileWriter("D:\\Derrotados.out.txt", true);
                    BufferedWriter escribi2 = new BufferedWriter(escribi);
                    escribi2.write("en la ronda "+ronda+" murio "+aux.sig.sig.getNombre());
                    escribi2.newLine(); 
                    escribi2.close();
                    participantes--;
                  
                    if (participantes == 1 || participantes == 0){
                        break;}
                    
                    }
                }   
                } else {
                    
                    if (aux.sig != null && aux.sig.estado != true) {
                         System.out.println(aux.sig.sig.getNombre() + " ha sobrevivido.");
                         
                    } else {
                        System.out.println(aux.sig.getNombre() + " ha sobrevivido.");
                        
                    }
                    Scanner scanner = new Scanner(System.in);
                   scanner.nextLine();
                }
                tambor = (tambor + 1) % 6;
                
            }
           
            aux=aux.sig;
            
        }
        int i = 0;
        
        do {
            if (aux.Condicion()) {
                System.out.println("El ganador es " + aux.getNombre()+" y duro un total de: "+ronda+" rondas");
                i++;
            }
            
        }while (i>numJugadores);
            FileWriter escribi = new FileWriter("D:\\Ganadores.out.txt", true);
            BufferedWriter escribi2 = new BufferedWriter(escribi);
            escribi2.write("en la ronda "+ronda+" gano "+aux.getNombre());
            escribi2.newLine();
            escribi2.close();
            
        } 
       
     
}
public class RuletaRusa20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Jugador player = new Jugador();
       try (FileReader fr = new FileReader("D:\\Jugadores.txt")) {
         BufferedReader br = new BufferedReader(fr);
         
         String linea;
         
         while((linea=br.readLine())!=null){
            player.agregarjugador(linea,true);
         }
            player.game();
            System.out.println();
      }
        
        
      catch(Exception e){
         e.printStackTrace();
         }
    }
    
}
