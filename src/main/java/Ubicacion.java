@Entity
public class Ubicacion {
    @Id_ubicacion

    private String id_ubicacion;
    private String pais;
    private String ciudad;
    private String direccion;

    public Ubicacion() {

    }

    public Ubicacion(String pais, String ciudad, String direccion) {
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getId_ubicacion() {
        return id;
    }

    public void setId_ubicacion(String id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }

    public String getPais() {
        return pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public float getDireccion() {
        return direccion;
    }

    public void getDireccion(float direccion) {
        this.direccion = direccion;
    }

}