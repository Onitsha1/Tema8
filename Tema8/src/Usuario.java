import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

public class Usuario extends Gestor {

    static public Scanner lector = new Scanner(System.in);
    String nombre;
    String apellido;
    String correoElectronico;
    String contraseña;
    String profesion;
    final int PERSONAL_CONTRATADO = 1;
    ArrayList<Usuario> usuarios = new ArrayList<>();
    HashMap<String, String> mapList = new HashMap<String, String>();

    public Usuario(String nombre, String apellido, String correoElectronico, String contraseña, String profesion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.profesion = profesion;
    }

    public void introducirDatos() {

        System.out.print("Escriba su nombre: ");
        this.nombre = lector.nextLine();

        System.out.print("Escriba su apellido: ");
        this.apellido = lector.nextLine();

        System.out.print("Escriba su correo electronico: ");
        this.correoElectronico = lector.nextLine();

        System.out.print("Escriba su contraseña: ");
        this.contraseña = lector.nextLine();

        do {   try {
            System.out.println("Dame la " + dato);
            String numeroLeido = Constantes.lector.nextLine();
            resultado = Integer.parseInt(numeroLeido);
            if (resultado >= 0 && resultado < Constantes.NUMERO_CASILLAS) {
                fallo = false;
            } else {
                System.out.println("El valor tiene que estar entre 0 y  " + (Constantes.NUMERO_CASILLAS - 1));
            }
        } catch (Exception e) {
            fallo = true;
            System.out.println("Valor inválido");}
        System.out.print("Escriba su profesión: ");
        this.profesion = lector.nextLine();

        mapList.put(correoElectronico, contraseña);
        System.out.println("Registro realizado correctamente.");

    }

    public void registro() {
        Usuario usuario;
        for (int i = 0; i < PERSONAL_CONTRATADO; i++) {
            usuario = new Usuario(nombre, apellido, correoElectronico, contraseña, profesion);
            usuario.introducirDatos();
            usuarios.add(usuario);
            mapList.put(correoElectronico, contraseña);
            System.out.println("Registro realizado correctamente.");

        }
    }

    public void mostrar() {

        System.out.println(usuarios.get(1));
    }

    public static boolean esContraseñaSegura(String contraseña) {

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);

        if (contraseña == null) {
            return false;
        }

        Matcher m = p.matcher(contraseña);

        return m.matches();

    }

    public void inicioSesion() {

        System.out.print("Introduzca su correo electronico: ");
        String correoElectronico = lector.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String contraseña = lector.nextLine();

        if (!mapList.containsKey(correoElectronico)) {
            System.out.println("El usuario no existe.");
        } else {
            if (mapList.get(correoElectronico).equals(contraseña)) {
                System.out.println("Acceso concedido.");
            } else {
                System.out.println("Correo electronico o contraseña invalida");
            }
        }

    }

}
