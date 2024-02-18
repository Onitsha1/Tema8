public class App {

    public static void main(String[] args) throws Exception {

        Usuario gestion = new Usuario("gestion", "gestion", "gestion", "gestion");

        String numeroIndicado = "";
        int opcion = 0;
        boolean incorrecto = true;
        String decision = "";

        do {
            System.out.println("Escriba un número para la acción que desee realizar.");
            System.out.println("1. Crear una cuenta \n" +
                    "2. Iniciar sesión.");
            numeroIndicado = Usuario.lector.nextLine();
            opcion = Integer.parseInt(numeroIndicado);
            if (opcion >= 1 || opcion <= 2) {
                incorrecto = false;
            } else {
                incorrecto = true;
                System.out.println("Opción inválida");
            }

        } while (incorrecto);

        switch (opcion) {
            case 1:

                gestion.registro();

                System.out.println("¿Desea iniciar sesion? S/N: ");
                decision = Usuario.lector.nextLine();
                if (decision.equals("S")) {
                    gestion.inicioSesion();
                } else {
                    break;
                }

            case 2:

                break;

        }
    }

}
