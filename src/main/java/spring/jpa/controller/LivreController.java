package spring.jpa.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.jpa.model.Livre;
import spring.jpa.repository.CategorieRepository;
import spring.jpa.repository.LivreRepository;

@Controller // pour déclarer un contrôleur
@RequestMapping (value = "/livre")
public class LivreController {
	@Autowired // pour l'injection de dépendances
	private LivreRepository livreRepos;
	 // pour l'injection de dépendances
	
	@Autowired
	private CategorieRepository CategorieRepos;
	
	//nom logique pour accéder à l'action "index" ou méthode "index
	@RequestMapping (value = "/index")
	public String index (Model model,
	//paramètre pour le numero de la page (0 par défaut)
	@RequestParam (name="page" , defaultValue ="0") int p,
	//paramètre "motCle"
	@RequestParam (name="motCle", defaultValue="") String mc)
	{
		Page <Livre> pg =	livreRepos.findByTitreLike("%"+mc+"%", PageRequest.of(p, 6));

				// nombre total des pages
				int nbrePages =pg.getTotalPages();
				int [] pages = new int[nbrePages];
				for(int i= 0 ; i< nbrePages; i++)
				{
				pages[i]=i;
				}
	//placer le tableau dans le "Model"
	model.addAttribute("pages", pages);
	//placer la page des livres dans le "Model" comme un attribut"
	model.addAttribute("pagelivres", pg);
	//retourner le numéro de la page courante
	model.addAttribute("pageCourante",p);
	//retourner la valeur du mot clé
	model.addAttribute("motCle", mc);
	//retourner le nom de la vue WEB à afficher
	return "livres";

	}

	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formLivre (Model model)
	{  
	//placer un objet de type "Produit" dans le modèle
	model.addAttribute("livre", new Livre());
	model.addAttribute("categories", CategorieRepos.findAll());
	//retourner le nom de la vue WEB à afficher (le formulaire)
	return "formLivre";
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save (Model model, @Valid Livre livre , BindingResult bindingResult)
	{
	if (bindingResult.hasErrors())
	// en cas d'erreurs de validation
	return "formLivre";
	//sinon
	//enregistrer le produit dans la BD
	livreRepos.save(livre);
	//Afficher une page pour confirmer l'enregistrement
	return "redirect:/livre/index";
	}
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete (Long id, int page, String motCle)
	{
	livreRepos.deleteById(id);
	return "redirect:index?page="+page+"&motCle="+motCle;
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit (Model model,

	@RequestParam (name="id")Long id)

	{
	// récupérer l'objet ayany l'id spécifié
	Livre l =(Livre) livreRepos.findById(id).orElse(null);
	// placer le produit trouvé dans le modèle
	model.addAttribute("livre", l);
	model.addAttribute("categories", CategorieRepos.findAll());

	// rediriger l'affichage vers la vue "editProduit"
	return "editLivre";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update (Model model, @Valid Livre livre ,
	BindingResult bindingResult)
	{
	if (bindingResult.hasErrors())
	// en cas d'erreurs de validation
	return "editLivre";
	//enregistrer le produit dans la BD
	livreRepos.save(livre);
	//Afficher une page pour confirmer l'enregistrement
	return "redirect:/livre/index";

	}

}
