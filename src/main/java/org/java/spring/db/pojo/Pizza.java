package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  // Identificatore univoco della pizza
	
	
	@Length(min = 1, max = 30, message = "Il nome della pizza deve contenere tra 1 e 30 caratteri.")
	@NotEmpty(message = "Il campo nome della pizza non può essere vuoto.")
	private String name;  // Nome della pizza
	
	@Column(columnDefinition = "TEXT")
	@Length(min = 1, max = 200, message = "La descrizione della pizza deve contenere tra 1 e 200 caratteri.")
	@NotEmpty(message = "Il campo descrizione della pizza non può essere vuoto.")
	private String description;  // Descrizione della pizza
	
	@URL(protocol = "https")
	@NotEmpty(message = "Il campo foto della pizza non può essere vuoto.")
	private String foto;  // URL dell'immagine della pizza
	
	@PositiveOrZero(message = "Il prezzo della pizza deve essere maggiore o uguale a 0.")
	private double price;  // Prezzo della pizza
	
	@OneToMany(mappedBy = "pizza")
	private List<Discount> discount;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	// Costruttore vuoto richiesto da JPA
	public Pizza() {}
	
	// Costruttore per creare una nuova pizza con informazioni iniziali
	public Pizza(String name, String description, String foto, double price, Ingredient... ingredients) {
		setName(name);
		setDescription(description);
		setFoto(foto);
		setPrice(price);
		setIngredients(ingredients);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<Discount> getDiscount() {
		return discount;
	}

	public void setDiscount(List<Discount> discount) {
		this.discount = discount;
	}
	

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setIngredients(Ingredient... ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	
	public void clearIngredients() {
		getIngredients().clear();
	}
	
	public void clearDiscount() {
		getDiscount().clear();
	}

	// Metodo toString per la rappresentazione testuale dell'oggetto Pizza
	@Override
	public String toString() {
	    return 
	        "id= " + getId() +
	        "name= " + getName() + '\'' +
	        "description= " + getDescription() + '\'' +
	        "foto= " + getFoto() + '\'' +
	        "price= " + getPrice() ;
	}
}
