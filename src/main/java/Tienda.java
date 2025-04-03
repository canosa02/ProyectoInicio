@Entity
public class Tienda{
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private string ubicacion;


    public Tienda(){}

    public Tienda(String ubicacion){
        this.ubicacion = ubicacion;     }

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
