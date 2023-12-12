package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Ingredient;
import org.java.spring.db.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
	@Autowired
	private IngredientRepo ingredientRepo;
	
	public List<Ingredient> findAll(){
		return ingredientRepo.findAll();
	}
	
	public Ingredient findById(int id) {
		return ingredientRepo.findById(id).get();
	}
	
	public void save(Ingredient discount) {
		ingredientRepo.save(discount);
	}
	
	public void delete(Ingredient discount) {
		ingredientRepo.delete(discount);
	}
}