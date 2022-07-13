package in.nareshIT.hc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import in.nareshIT.hc.entity.Specialization;
import in.nareshIT.hc.repository.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SpecializationRepositoryTest {
	
	@Autowired
	private SpecializationRepository repo;
	
	
	@Test
	@Order(1)
	public void createTest() {
		Specialization sp=new Specialization(null,"CRDLS","Cardiolagist","This is expert of blood vesels");		
		sp=repo.save(sp);
		assertNotNull(sp.getId(),"specnot created");
	}
	
	@Test
	@Order(2)
	public void fetctchAll() {
		List<Specialization> list=repo.findAll();
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("Data not found");
		}
	
	}
	
	
 
}
