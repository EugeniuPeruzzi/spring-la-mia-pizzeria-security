package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    @Autowired
    public PizzaRepository pizzaRepository;  // Iniezione di dipendenza del repository di Pizza

    // Metodo per recuperare tutte le pizze dal repository
    public List<Pizza> findAll() {
        // Utilizza il metodo findAll del repository per recuperare tutte le pizze
        return pizzaRepository.findAll();
    }

    // Metodo per trovare una pizza per ID dal repository
    public Pizza findById(int id) {
        // Utilizza il metodo findById del repository per trovare una pizza per ID
        // Il metodo get è utilizzato per estrarre l'oggetto Pizza dall'Optional restituito da findById
        return pizzaRepository.findById(id).get();
    }

    // Metodo per salvare una pizza nel repository
    public void save(Pizza pizza) {
        // Utilizza il metodo save del repository per salvare una pizza
        pizzaRepository.save(pizza);
    }

    // Metodo per trovare pizze per nome dal repository
    public List<Pizza> findByName(String query) {
        // Utilizza il metodo findByNameContainingIgnoreCase del repository per trovare pizze che contengono la query nel loro nome, ignorando la distinzione tra maiuscole e minuscole
        return pizzaRepository.findByNameContainingIgnoreCase(query);
    }
    
    // Metodo per eliminare una pizza dal repository
    public void delete(Pizza pizza) {
        // Utilizza il metodo delete del repository per eliminare una pizza
        pizzaRepository.delete(pizza);
    } 
//	public void deleteById(int id) {
//		
//		pizzaRepository.deleteById(id);
//	}
}
