import java.sql.*;
import java.util.Scanner;

//CRUD DE LA CONEXION DE JAVA CON MYSQL
//TRABAJO HECHO EN GRUPO POR: SHAMYR QUISHPE, MATEO TACURI
public class Main{
    public static void main(String[] args){
        String dbURL = "jdbc:mysql://localhost:3306/estudiantes";
        String dbUsername = "root";
        String dbPassword = "123456";
        Scanner scanner = new Scanner(System.in);

        int opcion1=0;
        int opcion2=0;
        while(opcion1!=5){
            System.out.println("Bienvenido a la conexion de Java y MySQL");
            System.out.println("1. Insertar un nuevo registro");
            System.out.println("2. Visualizar los registros");
            System.out.println("3. Actualizar registros");
            System.out.println("4. Eliminar un registro");
            System.out.println("5. Salir");
            System.out.println("Ingrese la opcion:");
            opcion1=scanner.nextInt();

            if(opcion1==1) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                    String sql = "INSERT INTO calificaciones VALUES (?, ?, ?, ?)";

                    PreparedStatement statement = conn.prepareStatement(sql);

                    int id;
                    String nombre;
                    int cedula;
                    float nota1;
                    float nota2;

                    System.out.println("Ingrese el id del estudiante: ");
                    id = scanner.nextInt();

                    System.out.println("Ingrese el nombre del estudiante: ");
                    nombre = scanner.next();

                    System.out.println("Ingrese la cedula del estudiante: ");
                    cedula = scanner.nextInt();

                    System.out.println("Ingrese la nota 1 del estudiante: ");
                    nota1 = scanner.nextFloat();

                    //  System.out.println("Ingrese la nota 2 del estudiante: ");
                    // nota2 = scanner.nextFloat();

                    statement.setInt(1, id);
                    statement.setString(2, nombre);
                    statement.setInt(3, cedula);
                    statement.setFloat(4, nota1);
                    // statement.setFloat(5, nota2);
                    int rowsUpdated = statement.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Se actualizo el registro exitosamente");
                    }


                } catch (Exception exception) {
                    System.out.println("Error: " + exception.getMessage());
                }
            }

            if(opcion1==2) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                    if (conn != null) {
                        System.out.println("Conectado a la base de datos estudiantes exitosamente");
                    }

                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM calificaciones");

                    int id;
                    String nombre;

                    while (rs.next()) {
                        id = rs.getInt("id");
                        nombre = rs.getString("nombre");
                        System.out.println(id + " " + nombre);
                    }

                } catch (Exception exception) {
                    System.out.println("Error: " + exception.getMessage());
                }
            }

            if (opcion1==3){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                    int id;
                    String nombre;
                    int cedula;
                    float nota1;
                    float nota2;
                    while(opcion2!=4) {
                        System.out.println("Actualizacion de registros");
                        System.out.println("1. Nombre");
                        System.out.println("2. Cedula");
                        System.out.println("3. Calificacion");
                        System.out.println("4. Regresar al menu principal");
                        System.out.println("Ingrese la opcion:");
                        opcion2=scanner.nextInt();

                        if(opcion2==1) {
                            String sql = "UPDATE calificaciones SET nombre=? where id=?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            System.out.println("Ingrese el id:");
                            id = scanner.nextInt();
                            System.out.println("Ingrese el nombre del estudiante: ");
                            nombre = scanner.next();
                            statement.setString(1, nombre);
                            statement.setInt(2, id);
                            int rowsUpdated = statement.executeUpdate();

                            if (rowsUpdated > 0) {
                                System.out.println("Se actualizo el registro exitosamente");
                            }
                        }
                        if(opcion2==2){
                            String sql = "UPDATE calificaciones SET cedula=? where id=?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            System.out.println("Ingrese el id:");
                            id = scanner.nextInt();
                            System.out.println("Ingrese la cedula del estudiante: ");
                            cedula = scanner.nextInt();
                            statement.setInt(1, cedula);
                            statement.setInt(2, id);
                            int rowsUpdated = statement.executeUpdate();

                            if (rowsUpdated > 0) {
                                System.out.println("Se actualizo el registro exitosamente");
                            }

                        }
                        if(opcion2==3){
                            String sql = "UPDATE calificaciones SET calificacion1=? where id=?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            System.out.println("Ingrese el id:");
                            id = scanner.nextInt();
                            System.out.println("Ingrese la nota 1 del estudiante: ");
                            nota1 = scanner.nextFloat();
                            statement.setFloat(1, nota1);
                            statement.setInt(2, id);
                            int rowsUpdated = statement.executeUpdate();

                            if (rowsUpdated > 0) {
                                System.out.println("Se actualizo el registro exitosamente");
                            }

                        }
                    }
                }catch (Exception exception){
                    System.out.println("Error: " + exception.getMessage());
                }
            }

            if (opcion1==4){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
                    String sql = "DELETE FROM calificaciones WHERE id = ?";

                    PreparedStatement statement = conn.prepareStatement(sql);
                    int id;
                    int confirmacion=0;
                    System.out.println("Ingrese el id del estudiante: ");
                    id = scanner.nextInt();

                    System.out.println("¿Esta seguro de que desea eliminar el registro?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    System.out.println("Ingrese el numero correspondiente a la opcion:");
                    confirmacion = scanner.nextInt();

                    if(confirmacion==1) {
                        statement.setInt(1, id);
                    }
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Se elimino el registro exitosamente");
                    }
                }catch (Exception exception){
                    System.out.println("Error: " + exception.getMessage());
                }
            }
            if (opcion1==5){
                System.out.println("Gracias por usar el sistema");
            }
        }
    }
}