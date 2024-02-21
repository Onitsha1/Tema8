public class App {

    public static void main(String[] args) throws Exception {

        /* Creamos una instancia de la clase Usuario para poder usar sus métodos. */
        Usuario gestion = new Usuario("gestion", "gestion", "gestion", "gestion");
        String numeroIndicado = "";
        int opcion = 0;
        boolean incorrecto = true;
        String decision = "";

        /*
         * Este do while sirve para que el actor introduzca una opción de acción. Si
         * escribe una opción diferente a lo
         * que se le pide, le indicará que es un error y tendrá que introducirlo de
         * nuevo.
         */
        do {
            try {
                System.out.println("Escriba un número para la acción que desee realizar.");
                System.out.println("1. Crear una cuenta \n" +
                        "2. Iniciar sesión.");
                numeroIndicado = Usuario.lector.nextLine();
                opcion = Integer.parseInt(numeroIndicado);
                if (opcion == 1 || opcion == 2) {
                    incorrecto = false;
                } else {
                    incorrecto = true;
                    System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                incorrecto = true;
                System.out.println("Opción inválida");
            }
        } while (incorrecto);

        /*
         * En este switch case, utilizando el input del do while anterior, se elige que
         * acción se quiere realizar.
         */
        switch (opcion) {
            case 1:

                gestion.registro();

                System.out.println("¿Desea iniciar sesion? S/N: ");
                decision = Usuario.lector.nextLine();
                if (decision.equals("S")) {
                    gestion.inicioSesion();
                    break;
                } else {
                    System.out.println("¡Hasta la próxima!");
                    break;
                }

            case 2:
                gestion.inicioSesion();
                break;
        }
    }
}
