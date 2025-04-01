@Entity
public class Productos{
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre
}