public class Farmacia implements Compras {

    @Override
    public void compraDepartamento() {

        System.out.println("Haciendo pedido de multivitaminas.");
    }

    @Override
    public void puedeComprar() {
        System.out.println("Este usuario tiene acceso a pedidos de Farmacia.");
    }
}
