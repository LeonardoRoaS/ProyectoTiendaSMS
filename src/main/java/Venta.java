public class Venta {

    private String nombre;
    private int valor;
    private int codigo;
    private int cantidad;


    public Venta(String nombre, int valor, int codigo, int cantidad) {
        this.nombre = nombre;
        this.valor = valor;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getValor() {
        return this.valor;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public int returnCantidad(){
        return this.cantidad;
    }

}
