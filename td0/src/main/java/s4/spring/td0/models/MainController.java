package s4.spring.td0.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("items")
public class MainController {
	@ModelAttribute("items") 
	    public List<Element> getItems(){
		Element elm = new Element();
		List<Element> elms = new ArrayList<>();
		elms.add(elm);
	      return elms;
	    }
		@GetMapping("items")
		public String index(ModelMap model) {
			model.addAttribute("essai","ooo");
			return "index";
		}
		@GetMapping("new")
		public String newItem() {
			return "newItem";
		}
		@PostMapping("addNew")
		public RedirectView addView(@RequestParam("nom") String nom,@RequestParam int evaluation) {
			Element elm = new Element();
			elm.setNom(nom);
			elm.setEvaluation(evaluation);
			getItems().add(elm);
		      return new RedirectView("/items");
		}
}

