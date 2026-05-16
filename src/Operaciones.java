import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Operaciones {
    
    static Scanner sc = new Scanner(System.in);

    //Ingresar alumno
    public static void ingresarAlumno(){
        try {
            Conexion con = new Conexion();
            Connection cn = con.conectar();

            System.out.print("Carnet: ");
            String carnet = sc.nextLine();

            System.out.print("Nombres: ");
            String nombres = sc.nextLine();

            System.out.print("Apellidos: ");
            String apellidos = sc.nextLine();

            System.out.print("Seccion (A/B): ");
            String seccion = sc.nextLine();

            String sql = "INSERT INTO alumnos VALUES(?,?,?,?,?)";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, carnet);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setString(4, seccion);
            ps.setDouble(5, 0);

            ps.executeUpdate();

            System.out.println("Alumno guardado");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Ingrese Notas
    public static void ingresarNotas(){
        try {
            
            Conexion con = new Conexion();
            Connection cn = con.conectar();

            System.out.print("Carnet del alumno: ");
            String carnet = sc.nextLine();

            System.out.print("Nota: ");
            double nota = Double.parseDouble(sc.nextLine());

            String sql = "UPDATE alumnos SET nota=? Where carnet=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            //parametro 1
            ps.setDouble(1, nota);

            //parametro 2
            ps.setString(2, carnet);
            int resultado = ps.executeUpdate();

            if(resultado > 0){
                System.out.println("Nota actualizada");
            }else{
                System.out.println("Alumno no encontrado");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Eliminar
    public static void eliminarAlumno(){

        try {
            
            Conexion con = new Conexion();
            Connection cn = con.conectar();

            System.out.println("Buscar alumno por: ");
            System.out.println("1 Carnet");
            System.out.println("2 Nombre");

            System.out.print("Seleccione una opción: ");
            int opcion =Integer.parseInt(sc.nextLine());

            String sqlBuscar = "";
            String dato = "";

            if(opcion ==1){
                System.out.print("Ingrese Carnet: ");
                dato = sc.nextLine();

                sqlBuscar = "SELECT * FROM alumnos WHERE carnet=?";
            }else if(opcion ==2){
                System.out.print("Ingrese nombre: ");
                dato = sc.nextLine();

                sqlBuscar = "SELECT * FROM alumnos WHERE nombres=?";
            }else{
                System.out.println("Opción inválida");
                return;
            }

            PreparedStatement psBuscar = cn.prepareStatement(sqlBuscar);

            psBuscar.setString(1, dato);
            ResultSet rs = psBuscar.executeQuery();

            if(rs.next()){
                System.out.println("\n==== ALUMNO ENCONTTADO ====");

                System.out.println("Carnet: " + rs.getString("carnet"));
                System.out.println("Nombres: " + rs.getString("nombres"));
                System.out.println("Apellidos: " + rs.getString("apellidos"));
                System.out.println("Sección: " + rs.getString("seccion"));
                System.out.println("Nota: " + rs.getDouble("nota"));

                System.out.println("\n¿Esta seguro de eliminar al alumno? (S/N): ");
                String respuesta = sc.nextLine();

                if(respuesta.equalsIgnoreCase("S")){
                    String sqlEliminar = "DELETE FROM alumnos WHERE carnet=?";
                    PreparedStatement psEliminar = cn.prepareStatement(sqlEliminar);
                    psEliminar.setString(1, rs.getString("carnet"));
                    int resultado = psEliminar.executeUpdate();

                    if(resultado > 0){
                        System.out.println("Alumno eliminado correctamente");
                    }else{
                        System.out.println("Eliminación cancelada");
                    }
                }else{
                    System.out.println("Alumno no encontrado");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Buscar
    public static void buscarAlumno(){

        try {
            
            Conexion con = new Conexion();
            Connection cn = con.conectar();
            
            System.out.print("Carnet: ");
            String carnet = sc.nextLine();

            String sql = "SELECT * FROM alumnos WHERE carnet=?";

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, carnet);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                System.out.println("Carnet: " + rs.getString("carnet"));
                System.out.println("Nombres: " + rs.getString("nombres"));
                System.out.println("Apellidos: " + rs.getString("apellidos"));
                System.out.println(" Seccion: " + rs.getString("seccion"));
                System.out.println("Nota: " + rs.getDouble("nota"));
            }else{
                System.out.println("Alumno no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Actualizar
    public static void actualizarAlumno(){

        try {

            Conexion con = new Conexion();
            Connection cn = con.conectar();

            System.out.print("Carnet del alumno: ");
            String carnet = sc.nextLine();

            System.out.print("Nuevo nombre: ");
            String nombres = sc.nextLine();

            System.out.print("Nuevo apellido: ");
            String apellidos = sc.nextLine();

            System.out.print("Nueva Seccion: ");
            String seccion = sc.nextLine();

            System.out.print("Nuevo nota: ");
            String nota = sc.nextLine();

            String sql = "UPDATE alumnos SET nombres=?, apellidos=?, seccion=?, nota=? WHERE carnet=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, nombres);
            ps.setString(2, apellidos);
            ps.setString(3, seccion);
            ps.setString( 4,nota);
            ps.setString(2, carnet);

            int resultado = ps.executeUpdate();

            if(resultado > 0){
                System.out.println("Actualizado correctamente");
            }else{
                System.out.println("Alumno no encontrado");
            }

            System.out.println("Datos actualizados");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }
    }
    
    //Promedios
    public static void obtenerPromedios(){

        try {
            
            Conexion con = new Conexion();
            Connection cn = con.conectar();

            String sql = "SELECT seccion, AVG(nota) AS promedio FROM alumnos GROUP BY seccion";

            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(
                    "seccion: " + rs.getString("seccion") + "Promedio: " + rs.getDouble("Promedio"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Listar
    public static void listarAlumnos(){

        try {
            
            Conexion con = new Conexion();
            Connection cn = con.conectar();

            System.out.print("Seccion: ");
            String seccion = sc.nextLine();

            String sql = "SELECT * FROM alumnos WHERE seccion=?";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, seccion);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println( rs.getString("carnet")+ " - "+ rs.getString("nombres") + " " + rs.getString("apellidos") + " - Nota: " + rs.getDouble("nota"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void ingresarNota() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

