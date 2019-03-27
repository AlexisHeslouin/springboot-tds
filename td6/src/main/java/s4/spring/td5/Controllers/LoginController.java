package s4.spring.td5.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td5.entities.Script;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositories.CateroryRepository;
import s4.spring.td5.repositories.HistoryRepository;
import s4.spring.td5.repositories.LangageRepository;
import s4.spring.td5.repositories.ScriptRepository;
import s4.spring.td5.repositories.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private UserRepository usersRepo;

	@Autowired
	private ScriptRepository scriptsRepo;
	
	@Autowired
	private HistoryRepository historiesRepo; 
	
	@Autowired
	private LangageRepository languagesRepo; 
	
	@Autowired
	private CateroryRepository categoriesRepo; 

	// ---------------USER------------------------

	@RequestMapping("users/new")
	@ResponseBody
	public String newUtilisateur() {
		User user = new User();
		user.setLogin("root");
		user.setPassword("");
		user.setIdentity("");
		user.setEmail("");

		usersRepo.save(user);
		return user.getIdentity() + " a ete ajoute a la base de donnee";
	}

	@GetMapping("login")
	public String connexion(Model model) {
		model.addAttribute("utilisateur", new User());
		return "login";
	}

	@PostMapping("login")
	public RedirectView connexion(Model model, User utilisateur, HttpSession session) {
		// String login, String password, String email, String identity
		User user = usersRepo.findByLogin(utilisateur.getLogin());

		if (user != null) {
			System.err.println("user existe");
			session.setAttribute("UtilisateurConnecte", user);
			return new RedirectView("/index");
		}
		System.out.println("user existe pas !");
		// si il n'existe pas alors on demande reconnexion
		return new RedirectView("/login");
	}

	@GetMapping("index")
	public String index(Model model, HttpSession session) {
		if (isConnected(session)) {
			//on ajoute au model l utilisateur connecte et ses scripts
			model.addAttribute("user", session.getAttribute("UtilisateurConnecte"));
			return "index";
		} else {
			return "login";
		}
	}

	// si utilisateur connecte => true
	public boolean isConnected(HttpSession session) {
		if (session.getAttribute("UtilisateurConnecte") != null) {
			return true;
		} else {
			return false;
		}
	}
	
	// revenir sur la page connexion si utilisateur se deconnecte
	@GetMapping("deconnexion")
	public RedirectView deconnexion(Model model, HttpSession session) {
		session.removeAttribute("UtilisateurConnecte");
		return new RedirectView("/login");
	}

	//-----------------SCRIPT----------------------------
	@GetMapping("scripts/index")
	public String afficherScripts(Model model) {
		//afficher liste des scripts
		model.addAttribute("scripts", scriptsRepo.findAll());
		model.addAttribute("langages", languagesRepo.findAll());
		model.addAttribute("categories", categoriesRepo.findAll());
		return "indexS";
	}
	
	@GetMapping("scripts/search")
	public String rechercheScripts(Model model) {
		model.addAttribute("scripts", scriptsRepo.findAll());
		model.addAttribute("langages", languagesRepo.findAll());
		model.addAttribute("categories", categoriesRepo.findAll());
		return "vueJS/search";
	}
	
	
	@RequestMapping("scripts/new")
	@ResponseBody
	public String createScript() {
		Script s1 = new Script();
		s1.setTitle("Mon premier script");
		s1.setCreationDate("24/03/2019");
		s1.setDescription("C'est un script d'essai");
		s1.setContent("oESHDVPZOuhf ncefhnZPEF?CzhfcpiZNFHCIz fhZIZPZGRNCHFFHU");
		
		Script s2 = new Script();
		s2.setTitle("Mon deuxieme script");
		s2.setCreationDate("24/03/2019");
		s2.setDescription("C'est un script d'essai");
		s2.setContent("fzuhoghzrg zeonezofhzeibif viaeubfuigfzegtU");
		
		System.out.println(s1);
		System.out.println(s2);
		scriptsRepo.save(s1);
		scriptsRepo.save(s2);
		return "s1 et s2 ont ete ajoutes a la base de donnees";
	}

	
	
	
}
