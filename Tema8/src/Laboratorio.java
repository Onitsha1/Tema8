/*Esta clase representa el rol de tecnicoLaboratorio de las instancias de Usuario, que extiende de la interfaz Compras
para poder hacer sobreescritura de métodos y que cada rol tenga una implementación diferente (permisos). */
public class Laboratorio implements Compras {

    @Override
    public void compraDepartamento() {

        System.out.println("Haciendo pedido de placas de Petri.");
    }

    /* Este método se utilizará para hacer comprobación de los permisos del rol. */
    @Override
    public void puedeComprar() {

        System.out.println("Este usuario tiene acceso a pedidos de Laboratorio.");
    }
}
