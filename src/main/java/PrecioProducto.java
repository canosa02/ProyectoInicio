@Entity
public class Producto{
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;
    private String  id_ubicacion;
    private float precio;

    public PrecioProducto() {

    }

    public PrecioProducto(int  id_producto, String id_ubicacion, float precio){
        this.id_producto = id_producto;
        this.id_ubicacion = id_ubicacion;
        this.precio = precio;
    }


    public int getId() {
        return id_producto;
    }

    public void setId(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getId_ubicacion() {
        return id_ubicacion;
    }

    public void setId_ubicacion(String id_ubicacion) {this.id_ubicacion = id_ubicacion;}

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio){
        this.precio = precio;
    }

}