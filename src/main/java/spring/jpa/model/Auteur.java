package spring.jpa.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Auteur {
@Id
@GeneratedValue
@Column(name = "auteur_id")
private Long id;
@NotNull
private String prenom;
@NotNull
private String nom;
private String adresse;
@ManyToMany (mappedBy = "auteurs",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
private Collection <Livre> Livres = new ArrayList<Livre>();

public Auteur( @NotNull String prenom, @NotNull String nom, String adresse) {
	super();
	this.prenom = prenom;
	this.nom = nom;
	this.adresse = adresse;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public Collection<Livre> getLivres() {
	return Livres;
}
public void setLivres(Collection<Livre> livres) {
	Livres = livres;
}
@Override
public String toString() {
	return "Auteur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", adresse=" + adresse +  "]";
}
public Auteur() {
	super();
	// TODO Auto-generated constructor stub
}



}
