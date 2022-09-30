package spring.jpa.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.jpa.model.Livre;
public interface LivreRepository extends JpaRepository<Livre, Long> {
	Page<Livre> findByTitreLike(String mc, Pageable pageable);
	

}
