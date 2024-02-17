public class App {

    public static void main(String[] args) throws Exception {

        Usuario usuario1 = new Usuario("Marta", "Gomez", "correo", "123", "gestora");

        usuario1.introducirDatos();
        usuario1.inicioSesion();

    }
}
