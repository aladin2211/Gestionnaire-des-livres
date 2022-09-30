package spring.jpa.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categorie
{
@Id
@GeneratedValue
private Long id;
@Column(length = 50)
private String designation;
@Column(length = 50)
private String description;

@OneToMany (mappedBy = "categorie" ,cascade = {CascadeType.REMOVE,
CascadeType.MERGE, CascadeType.PERSIST} )
private Collection <Livre> livres = new ArrayList<Livre>();
@JsonIgnore
public Collection<Livre> getLivres() {
return livres;
}
public void setLivres(Collection<Livre> livres) {
this.livres = livres;
}
public Categorie(String designation, String description) {
super();
this.designation = designation;
this.description = description;
}
@Override
public String toString() {
	return "Categorie [id=" + id + ", designation=" + designation + ", description=" +

	description + "]";
	}
	public Categorie() {}
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
	this.id = id;
	}
	public String getDesignation() {
	return designation;
	}
	public void setDesignation(String designation) {
	this.designation = designation;
	}
	public String getDescription() {
	return description;
	}
	public void setDescription(String description) {
	this.description = description;
	}
	}
