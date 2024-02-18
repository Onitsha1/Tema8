public class Laboratorio implements Compras {

    @Override
    public void compraDepartamento() {

        System.out.println("Haciendo pedido de placas de Petri.");
    }

    @Override
    public void puedeComprar() {

        System.out.println("Este usuario tiene acceso a pedidos de Laboratorio.");
    }
}
