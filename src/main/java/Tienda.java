@Entity
public class Tienda{
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private string ubicacion;
    private Long idP;

    public Tienda(){}

    public Tienda(String ubicacion, int idP){
        this.ubicacion = ubicacion;
        this.idP = idP;
        }

        public Long getId(){
            return id;
        }

        public void setId(Long id){
            this.id = id;
        }
        public String getUbicacion(){
            return ubicacion;
        }

        public void setUbicacion(String ubicacion){
            this.ubicacion = ubicacion;
        }


        public void setIdP(Long idP){
            this.idP = idP
        }
        public int getIdp(){
                return idP;
        }
}