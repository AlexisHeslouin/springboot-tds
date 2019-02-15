package s4.spring.td0.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("elements")
public class ElementController {
	
	@ModelAttribute("elements")
	public List<Element> getElements(){
		List<Element> elements=new ArrayList<>();
		return elements;
	}
	
	@RequestMapping("/")
	public String indexElement(@ModelAttribute("elements") List<Element> elements) {
		Element e = new Element();
		e.setNom("Test");
		elements.add(e);
		return "indexElement";
	}
}