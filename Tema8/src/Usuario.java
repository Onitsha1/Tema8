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

        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    // Se presentan getters.
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    /*
     * Este método se usa para obtener los diferentes atributos de la clase Usuario
     * (datos)
     */
    public void introducirDatos() {
        String pruebaNombre;
        String pruebaApellido;
        String pruebaCorreoElectronico;
        boolean error1 = true;
        boolean error2 = true;
        boolean error3 = true;

        do {
            System.out.print("Escriba su nombre: ");
            pruebaNombre = lector.nextLine();
            if (Pattern.matches("[a-zA-Z]+", pruebaNombre)) {
                error1 = false;
                this.nombre = pruebaNombre;
            } else {
                error1 = true;
                System.out.println("Por favor, escriba su nombre correctamente, sin tildes.");
            }
        } while (error1);

        do {
            System.out.print("Escriba su primer apellido: ");
            pruebaApellido = lector.nextLine();
            if (Pattern.matches("[a-zA-Z]+", pruebaApellido)) {
                error2 = false;
                this.apellido = pruebaApellido;
            } else {
                error2 = true;
                System.out.println("Por favor, escriba su apellido correctamente, sin tildes.");
            }
        } while (error2);

        do {
            System.out.print("Escriba su correo electronico: ");
            pruebaCorreoElectronico = lector.nextLine();
            if (Pattern.matches("^(.+)@(\\S+)$", pruebaCorreoElectronico)) {
                error3 = false;
                this.correoElectronico = pruebaCorreoElectronico;
            } else {
                error3 = true;
                System.out.println("Por favor, escriba su correo electronico correctamente.");
            }
        } while (error3);

        /*
         * Este do-while pedirá una contraseña. Si no es segura, pedirá que se
         * introduzca de nuevo
         * con ciertas caracteristicas. Si es segura, nos pedirá que introduzcamos
         * nuevamente la contraseña
         * para comprobar que coinciden. Si no coincide, nos lo indicará y habrá que
         * introducir las contraseñas de nuevo.
         * Si sí coindice, se guardará como contraseña junto al correo electrónico
         * en un HashMap llamado credenciales siendo el correo la clave y la contraseña
         * el valor.
         */

        boolean error = true;
        String pruebaContraseña;
        String pruebaContraseña2;
        String pruebaContraseña3 = "";

        do {
            System.out.print("Escriba su contraseña: ");
            pruebaContraseña = lector.nextLine();

            if (Pattern.matches("^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{8,20}$", pruebaContraseña)) {
                error = false;
                System.out.print("Escriba su contraseña una vez más: ");
                pruebaContraseña2 = lector.nextLine();
                if (pruebaContraseña.equals(pruebaContraseña2)) {
                    error = false;
                    pruebaContraseña3 = pruebaContraseña2;
                    this.contraseña = pruebaContraseña3;
                } else {
                    error = true;
                    System.out.println("Las contraseñas introducidas no coinciden.");
                }

            } else {
                error = true;
                System.out.println(
                        "La contraseña debe contener minimo 8 digitos y ser una combinación de letras mayusculas y minusculas,\n"
                                + " numeros y caracteres especiales [@#$%^&+=] sin espacios.");
            }

        } while (error);

        credenciales.put(correoElectronico, contraseña);

    }

    /* Asignamos roles a un objeto para poder crear la asociación a cada rol. */
    public void asignarDepartamento(Compras gestor) {
        this.gestor = gestor;
    }

    public void pedidos() {
        gestor.compraDepartamento();
    }

    void puedeComprar() {
        gestor.puedeComprar();

    }

    /*
     * Este método sirve para asignar un rol a los usuarios dependiendo del input
     * que elijan.
     */
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

    /*
     * Si se elige el rol Farmacia, con este bucle for se crea una instancia de
     * usuario ligada al rol Farmacia (farmaceutico).
     */
    public void registroFarmacia() {
        Usuario farmaceutico;
        for (int i = 0; i < PERSONAL_CONTRATADO; i++) {
            farmaceutico = new Usuario(nombre, apellido, correoElectronico, contraseña);
            farmaceutico.introducirDatos();
            farmaceutico.asignarDepartamento(new Farmacia());
            farmaceuticos.add(farmaceutico);
            System.out.println("Registro realizado correctamente en el Departamento Farmacia.");
            farmaceutico.puedeComprar();
        }
    }

    public void registroLaboratorio() {
        Usuario tecnicoLaboratorio;
        for (int i = 0; i < PERSONAL_CONTRATADO; i++) {
            tecnicoLaboratorio = new Usuario(nombre, apellido, correoElectronico, contraseña);
            tecnicoLaboratorio.introducirDatos();
            tecnicoLaboratorio.asignarDepartamento(new Laboratorio());
            tecnicosLaboratorio.add(tecnicoLaboratorio);
            System.out.println("Registro realizado correctamente en el Departamento Laboratorio.");
            tecnicoLaboratorio.puedeComprar();
        }
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
                System.out.println("No existe la cuenta indicada.");

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
