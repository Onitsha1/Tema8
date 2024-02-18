import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;

public class Usuario {

    static public Scanner lector = new Scanner(System.in);
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contraseña;
    final int PERSONAL_CONTRATADO = 1;
    private ArrayList<Usuario> farmaceuticos = new ArrayList<>();
    private ArrayList<Usuario> tecnicosLaboratorio = new ArrayList<>();
    private static HashMap<String, String> credenciales = new HashMap<String, String>();
    private Compras gestor;

    public Usuario(String nombre, String apellido, String correoElectronico, String contraseña) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    /*
     * public String getNombre() {
     * return nombre;
     * }
     * 
     * public String getApellido() {
     * return apellido;
     * }
     * 
     * public String getCorreoElectronico() {
     * return correoElectronico;
     * }
     * 
     * public String getContraseña() {
     * return contraseña;
     * }
     */
    public void introducirDatos() {

        System.out.print("Escriba su nombre: ");
        this.nombre = lector.nextLine();

        System.out.print("Escriba su apellido: ");
        this.apellido = lector.nextLine();

        System.out.print("Escriba su correo electronico: ");
        this.correoElectronico = lector.nextLine();

        Usuario gestor1 = new Usuario("Gestor1", "Gestor1", "correo1", "kihh");

        String pruebaContraseña;
        boolean error = true;

        do {
            System.out.print("Escriba su contraseña: ");
            pruebaContraseña = lector.nextLine();
            if (gestor1.esContraseñaSegura(pruebaContraseña) == true) {
                error = false;
                this.contraseña = pruebaContraseña;
            } else {
                error = true;
                System.out.println(
                        "La contraseña debe contener minimo 8 digitos y ser una combinación de letras mayusculas y minusculas,\n"
                                + " numeros y caracteres especiales [@#$%^&+=] sin espacios.");
            }

        } while (error);

        credenciales.put(correoElectronico, contraseña);
    }

    public void asignarDepartamento(Compras gestor) {
        this.gestor = gestor;
    }

    public void pedidos() {
        gestor.compraDepartamento();
    }

    void puedeComprar() {
        gestor.puedeComprar();

    }

    public void registro() {
        String pruebaDepartamento;
        boolean invalido = true;
        String profesion = "-1";

        do {
            System.out.print("Escriba su departamento tal cual se muestra (Farmacia o Laboratorio): ");
            pruebaDepartamento = lector.nextLine();
            if (pruebaDepartamento.equals("Farmacia") || pruebaDepartamento.equals("Laboratorio")) {
                invalido = false;
                profesion = pruebaDepartamento;

            } else {
                invalido = true;
                System.out.println("No existe ese departamento.");
            }

        } while (invalido);

        switch (profesion) {
            case "Farmacia":
                registroFarmacia();
                break;

            case "Laboratorio":
                registroLaboratorio();
                break;

            default:
                System.out.println("Departamento no valido.");
        }

    }

    public void registroFarmacia() {
        Usuario farmaceutico;
        for (int i = 0; i < PERSONAL_CONTRATADO; i++) {
            farmaceutico = new Usuario(nombre, apellido, correoElectronico, contraseña);
            farmaceutico.introducirDatos();
            farmaceutico.asignarDepartamento(new Farmacia());
            farmaceuticos.add(farmaceutico);
            farmaceutico.puedeComprar();
            System.out.println("Registro realizado correctamente en el Departamento Farmacia.");
        }
    }

    public void registroLaboratorio() {
        Usuario tecnicoLaboratorio;
        for (int i = 0; i < PERSONAL_CONTRATADO; i++) {
            tecnicoLaboratorio = new Usuario(nombre, apellido, correoElectronico, contraseña);
            tecnicoLaboratorio.introducirDatos();
            tecnicoLaboratorio.asignarDepartamento(new Laboratorio());
            tecnicosLaboratorio.add(tecnicoLaboratorio);
            tecnicoLaboratorio.puedeComprar();
            System.out.println("Registro realizado correctamente en el Departamento Laboratorio.");
        }
    }

    public boolean esContraseñaSegura(String contraseña) {

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

        boolean fallo = true;

        do {

            System.out.print("Introduzca su correo electronico: ");
            correoElectronico = lector.nextLine();
            System.out.print("Introduzca su contraseña: ");
            contraseña = lector.nextLine();

            if (!credenciales.containsKey(correoElectronico)) {
                fallo = true;
                System.out.println("El usuario no existe.");

            } else {
                if (credenciales.get(correoElectronico).equals(contraseña)) {
                    fallo = false;
                    System.out.println("Acceso concedido.");
                } else {
                    fallo = true;
                    System.out.println("Correo electronico o contraseña invalida");
                }
            }

        } while (fallo);

    }

}
