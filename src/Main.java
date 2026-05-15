import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        int opcion;

        do { 
            System.out.println("\n===== Menú PRINCIPAL =====");
            System.out.println("1. Ingreso de Alumnos");
            System.out.println("2. Ingreso de Notas");
            System.out.println("3. Eliminar Alumnos");
            System.out.println("4. Actualizar datos y notas");
            System.out.println("5. Buscar alumnos");
            System.out.println("6. Obtener Promedios");
            System.out.println("7. Listar alumnos");
            System.out.println("8. Salir");

            System.out.println("Seleccione una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch(opcion){
                case 1:
                    Operaciones.ingresarAlumno();
                    break;

                case 2:
                    Operaciones.ingresarNotas();
                    break;
                
                case 3:
                    Operaciones.eliminarAlumno();
                    break;

                case 4: 
                    Operaciones.actualizarAlumno();
                    break;
                
                case 5:
                    Operaciones. buscarAlumno();
                    break;

                case 6:
                    Operaciones.obtenerPromedios();
                    break;

                case 7:
                    Operaciones.listarAlumnos();
                    break;

                case 8:
                    System.out.println("Saliendo....");
                
                default:
                    System.out.println("Opcion invalida");
            }
        } while(opcion != 8);
    }
}
