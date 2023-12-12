package org.java.spring.controller;


import org.java.spring.db.pojo.Discount;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.DiscountService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;


@Controller
public class DiscountController {
	
	@Autowired 
	private PizzaService pizzaService;
	
	@Autowired 
	private DiscountService discountService;
	
    // Mostra la pagina per creare una nuova offerta
	@GetMapping("/pizzas/{id}/discount")
    public String createDiscount(Model model, @PathVariable int id) {
        
        Pizza pizza = pizzaService.findById(id);
        
        Discount discount = new Discount();
        
        discount.setPizza(pizza);
        
        model.addAttribute("discount", discount);
        
        return "discountCreate";
    }
	
	@PostMapping("/pizzas/{id}/discount")
	public String storeDiscount(@Valid @ModelAttribute DiscountDTO discountDTO, BindingResult bindingResult, @PathVariable int id, Model model) {
	    
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("discount", discountDTO);
	        return "discountCreate";
	    }

	    Pizza pizza = pizzaService.findById(id);
	    
	    Discount discount = new Discount(discountDTO.getTitolo(), discountDTO.getDataDiInizio(), discountDTO.getDataDiFine(), pizza);
	    
	    discountService.save(discount);
	    
	    return "redirect:/pizza/{id}";
	}
	
	
	@GetMapping("/pizzas/{pizzaId}/discount/edit/{discountId}")
	public String editDiscount (Model model, @PathVariable int pizzaId, @PathVariable int discountId) {
	    Discount discount = discountService.findById(discountId);
	    model.addAttribute("discount", discount);
	    return "discountCreate";
	}
	
	@PostMapping("/pizzas/{pizzaId}/discount/edit/{discountId}")
	public String updateDiscount(@ModelAttribute DiscountDTO discountDTO, @PathVariable int pizzaId, @PathVariable int discountId) {
	    
	    // Trova la pizza e lo sconto esistenti
	    Pizza pizza = pizzaService.findById(pizzaId);
	    Discount discount = discountService.findById(discountId);

	    // Aggiorna i campi dello sconto
	    discount.setTitolo(discountDTO.getTitolo());
	    discount.setDataDiInizio(discountDTO.getDataDiInizio());
	    discount.setDataDiFine(discountDTO.getDataDiFine());
	    discount.setPizza(pizza);

	    // Salva lo sconto aggiornato
	    discountService.save(discount);

	    // Reindirizza alla pagina della pizza
	    return "redirect:/pizza/{pizzaId}";
	}
	
	
}
