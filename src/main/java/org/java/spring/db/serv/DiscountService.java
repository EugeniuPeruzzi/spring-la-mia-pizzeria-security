package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Discount;
import org.java.spring.db.repo.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	@Autowired
	private DiscountRepo discountRepo;
	
	public List<Discount> findAll(){
		return discountRepo.findAll();
	}
	
	public Discount findById(int id) {
		return discountRepo.findById(id).get();
	}
	
	public void save(Discount discount) {
		discountRepo.save(discount);
	}
	
	public void delete(Discount discount) {
		discountRepo.delete(discount);
	}
}
