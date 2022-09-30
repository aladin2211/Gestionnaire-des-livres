package spring.jpa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Livre {
@Id
@GeneratedValue
private Long id;
@Column(length = 50)
@NotNull // interdire une valeur null
@Size(min=3, max=50)//spécifier la taille acceptée
private String titre;
private int isbn;
@DecimalMin("0.1") // pour spécifier une valeur minimale pour le prix
private double prix;
private int qteStock;
@Temporal(TemporalType.DATE)
@DateTimeFormat (pattern = "yyyy-MM-dd")
Date dateEdition;

private String img;
@ManyToOne
private Categorie categorie;
@ManyToMany (cascade=CascadeType.ALL)
@JoinTable(
		  name = "livre_auteur", 
		  joinColumns = @JoinColumn(name = "livre_id"), 
		  inverseJoinColumns = @JoinColumn(name = "auteur_id"))
private Collection <Auteur> auteurs = new ArrayList<Auteur>();

public Collection<Auteur> getAuteurs() {
	return auteurs;
}
public void setAuteurs(Collection<Auteur> auteurs) {
	this.auteurs = auteurs;
}
public Livre( @NotNull @Size(min = 3, max = 50) String titre, int isbn, @DecimalMin("0.1") double prix,
		int qteStock, Date dateEdition, Categorie categorie) {
		super();
		this.titre = titre;
		this.isbn = isbn;
		this.prix = prix;
		this.qteStock = qteStock;
		this.dateEdition = dateEdition;
		this.categorie = categorie;
	}


public Date getDateEdition() {
	return dateEdition;
}
public void setDateEdition(Date dateEdition) {
	this.dateEdition = dateEdition;
}
public Categorie getCategorie() {
	return categorie;
}
public void setCategorie(Categorie categorie) {
	this.categorie = categorie;
}

	public Livre(@NotNull @Size(min = 3, max = 50) String titre, int isbn, @DecimalMin("0.1") double prix, int qteStock,
		Date dateEdition) {
	super();
	this.titre = titre;
	this.isbn = isbn;
	this.prix = prix;
	this.qteStock = qteStock;
	this.dateEdition = dateEdition;
}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQteStock() {
		return qteStock;
	}
	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}
	public Livre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Livre(@NotNull @Size(min = 3, max = 50) String titre, int isbn, @DecimalMin("0.1") double prix,
			int qteStock) {
		super();
		this.titre = titre;
		this.isbn = isbn;
		this.prix = prix;
		this.qteStock = qteStock;
	}
	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", isbn=" + isbn + ", prix=" + prix + ", qteStock=" + qteStock
				+ ", dateEdition=" + dateEdition + ", categorie=" + categorie + "]";
	}
	public Livre(Long id, @NotNull @Size(min = 3, max = 50) String titre, int isbn, @DecimalMin("0.1") double prix,
			int qteStock) {
		super();
		this.id = id;
		this.titre = titre;
		this.isbn = isbn;
		this.prix = prix;
		this.qteStock = qteStock;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	
	

}
