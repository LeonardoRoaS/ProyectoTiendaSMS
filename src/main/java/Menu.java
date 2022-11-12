import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public ArrayList<Producto> productos;
    public ArrayList<Venta> ventas;
    public ArrayList<Usuario> usuarios;

    private int indice;

    public Menu(){
        this.productos =  new ArrayList<Producto>();
        this.ventas = new ArrayList<Venta>();
        this.indice = 1;
    }

    public void menuUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a SMS");
        System.out.println("Eliga que accion desea realizar:");
        System.out.println("1)Realizar una venta");
        System.out.println("2)Mostrar el inventario");
        System.out.println("3)Buscar producto");
        System.out.println("4)Cerrar sesión");
        int opcion = sc.nextInt();
        opcionUsuario(opcion);
    }

    public void opcionUsuario(int opcion){
        switch(opcion){
            case 1:
                String nombreV = preguntarPalabra("nombre","producto");
                int precioV = preguntarNumero("precio","producto");
                int codigoV = preguntarNumero("código","producto");
                int cantidadV = preguntarNumero("cantidad","producto");
                vender(nombreV,precioV,codigoV,cantidadV);
                break;
            case 2:
                imprimirInventario();
                break;
            case 3:
                String nombre = preguntarPalabra("nombre","producto");
                buscarProducto(nombre);
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Opcion incorrecta, intente denuevo.");
                menuUsuario();
                break;
        }
    }

    public void menuAdministrador(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a SMS");
        System.out.println("Eliga que accion desea realizar:");
        System.out.println("1)Crear Usuario");
        System.out.println("2)Mostrar el inventario");
        System.out.println("3)Buscar producto");
        System.out.println("4)Aumentar o rebajar el precio de un producto");
        System.out.println("5)Añadir o Disminuir el Stock de un producto");
        System.out.println("6)Añadir producto nuevo" );
        System.out.println("7)Cerrar sesión");
        int opcion = sc.nextInt();
        opcionAdministrador(opcion);
    }

    public void opcionAdministrador(int seleccion){
        switch(seleccion){
            case 1:
                String nombre = preguntarPalabra("nombre", "usuario");
                String contraseña = preguntarPalabra("contraseña", "usuario");
                crearUsuario(nombre,contraseña);
                break;
            case 2:
                imprimirInventario();
                break;
            case 3:
                String busquedaNombre = preguntarPalabra("nombre","producto");
                buscarProducto(busquedaNombre);
                break;
            case 4:
                String nombreValor = preguntarPalabra("nombre","producto");
                int valor = preguntarNumero("valor","aumento o la rebaja");
                sumarPrecio(nombreValor,valor);
                break;
            case 5:
                String nombreExistencias = preguntarPalabra("nombre","producto");
                int stock = preguntarNumero("valor","existencias");
                sumarExistencias(nombreExistencias,stock);
                break;
            case 6:
                String nombreP = preguntarPalabra("nombre","producto");
                int precioP = preguntarNumero("precio","producto");
                int cantidadP = preguntarNumero("cantidad","producto");
            añadirProducto(nombreP,precioP,cantidadP);
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Opcion incorrecta, intente denuevo.");
                menuAdministrador();
                break;
        }
    }

    private  void añadirProducto(String nombre, int precio, int existencia){
        this.productos.add(new Producto(nombre,precio,existencia,this.indice));
        this.indice++;
    }

    //Reutilizable//
    private String preguntarPalabra(String palabra1, String palabra2){
        Scanner sc = new Scanner(System.in);
        System.out.println("Porfavor ingrese el "+palabra1 +" del "+palabra2);
        String palabra = sc.nextLine();
        return palabra;
    }

    //Reutilizable//
    private int preguntarNumero(String palabra1, String palabra2){
        Scanner sc = new Scanner(System.in);
        System.out.println("Porfavor ingrese el "+palabra1+" del "+palabra2);
        int valor = sc.nextInt();
        return valor;
    }

    public void sumarExistencias(String nombre, int valor){
        for(Producto producto:this.productos){
            if(nombre == (producto.getNombre())){ //ocupa un equals//
                producto.sumarExistencias(valor);
            }
        }
    }

    public void sumarPrecio(String nombre, int valor){
        for(Producto producto:this.productos){
            if(nombre == (producto.getNombre())){
                producto.sumarPrecio(valor);
            }
        }
    }

    public String toString() {
        String cantidad="";
        cantidad+="El inventario contiene:\n";
        for (Producto item:this.productos) {
            cantidad+='\n'+item.toString();
        }
        return cantidad;
    }

    public void imprimirInventario(){
        System.out.println(toString());
    }

    public void vender(String nombre, int precio,int codigo, int cantidad){
        int precioTotal;

        for(Producto producto:this.productos){
            if(nombre == producto.getNombre()){
                producto.sumarExistencias(-cantidad);
                precioTotal = producto.getPrecio()*cantidad;
                nombre = producto.getNombre();
                codigo = producto.getCodigo();
                this.ventas.add(new Venta(nombre,precioTotal,codigo, cantidad));
                mostrarVender(precioTotal);
            }
        }
    }

    private void mostrarVender(int precioTotal){
        System.out.println("El valor total de la venta es de : "+(precioTotal));
    }


    public ArrayList<Venta> getVentas(){
        return this.ventas;
    }

    public ArrayList<Producto> getProductos(){
        return this.productos;
    }


    public void buscarProducto(String nombre){
        for(Producto producto:this.productos){
            if(nombre == producto.getNombre()){
                System.out.println("Se ha encontrado el producto con el nombre : "+nombre);
                producto.toString();
            }
        }
    }


    public void elegirUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de usuario usara SMS ?");
        System.out.println("1)Administrador");
        System.out.println("2)Vendedor");
        int seleccion = sc.nextInt();
        switch(seleccion){
            case 1:
                menuAdministrador();
                break;
            case 2:
                menuUsuario();
                break;
            default:
                System.out.println("Eleccion invalida, intente denuevo");
                elegirUsuario();
                break;
        }
    }

    public void crearUsuario(String nombre, String contraseña){

        this.usuarios.add(new Usuario(nombre, contraseña));
    }

    public void logout(){
    }
}
