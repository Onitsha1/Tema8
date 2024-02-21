/*Esta clase representa el rol de farmaceutico de las instancias de Usuario, que extiende de la interfaz Compras
para poder hacer sobreescritura de métodos y que cada rol tenga una implementación diferente (permisos). */
public class Farmacia implements Compras {

    @Override
    public void compraDepartamento() {

        System.out.println("Haciendo pedido de multivitaminas.");
    }

    /* Este método se utilizará para hacer comprobación de los permisos del rol. */
    @Override
    public void puedeComprar() {
        System.out.println("Este usuario tiene acceso a pedidos de Farmacia.");
    }
}
