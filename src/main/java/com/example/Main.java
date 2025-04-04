import com.example.Competitor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // Crear el EntityManagerFactory usando la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CompetitorsPU");
        
        // Crear el EntityManager
        EntityManager em = emf.createEntityManager();
        
        // Iniciar una transacción
        em.getTransaction().begin();
        
        // Crear una nueva entidad Competitor (suponiendo que tienes una clase Competitor)
        Competitor competitor = new Competitor();
        competitor.setName("Juan");
        competitor.setSurname("Pérez");
        competitor.setAge(30);
        
        // Guardar el competidor en la base de datos
        em.persist(competitor);
        
        // Confirmar la transacción
        em.getTransaction().commit();
        
        // Consultar todos los competidores
        List<Competitor> competitors = em.createQuery("SELECT c FROM Competitor c", Competitor.class).getResultList();
        competitors.forEach(c -> System.out.println(c.getName()));
        
        // Cerrar el EntityManager y EntityManagerFactory
        em.close();
        emf.close();
    }
}
