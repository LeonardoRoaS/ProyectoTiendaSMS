import java.util.ArrayList;


public class Usuario {
    private String nombreUsuario;
    private String contraseña;

    public Usuario(String nombreUsuario, String contraseña){
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public String getUsuario(){
        return this.nombreUsuario;
    }


    public int totalVentas(ArrayList<Venta> ventas){
        return ventas.size();
    }

    public void mostrarTotalVentas(int total){
        System.out.println("El usuario "+this.nombreUsuario+" ha ventido un total de: "+total);
    }

    public int cantidadVendida(ArrayList<Venta> ventas){
        int cantidadVendida = 0;
        for(Venta producto: ventas){
            cantidadVendida += producto.getCantidad();
        }
        return cantidadVendida;
    }
    public void mostrarCantidadVendida(int total){
        System.out.println("EL usuario "+this.nombreUsuario+"ha vendido un total de: "+total+ "unidades");
    }
    //el valor total que se vendio entre todos los productos vendidos por el usuario en esa sesión
    public int valorVendido(ArrayList<Venta> ventas){
        int valorVendido = 0;
        for(Venta producto: ventas){
            valorVendido += producto.getValor();
        }
        return valorVendido;
    }

    public void mostrarValorVendido(int total){
        System.out.println("El usuario "+this.nombreUsuario+"ha vendido un total de: $"+total+" pesos");
    }
}
