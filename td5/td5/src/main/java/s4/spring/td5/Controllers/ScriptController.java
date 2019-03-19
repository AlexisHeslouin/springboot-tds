package s4.spring.td5.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import s4.spring.td5.entities.User;
import s4.spring.td5.repositories.ScriptRepository;
import s4.spring.td5.repositories.UserRepository;

@Controller
@RequestMapping("/accueil/")
public class ScriptController {
	@Autowired
	private ScriptRepository scriptsRepo;
	
	@Autowired
	private UserRepository usersRepo;
	
	@RequestMapping("create")
	public String createUser(Model model) {
		User user = new User();
		user.setLogin("test");
		user.setPassword("test123");
		user.setIdentity("MOI");
		user.setEmail("test@test.fr");
		usersRepo.save(user);
		return "create";
	}
	
	@RequestMapping("{path:index}")
	public String Index(Model model) {
		List<User> users = usersRepo.findAll();
		model.addAttribute("users", users);
		return "index";
	}
	
	@RequestMapping("login")
	public String Login(Model model) {
		usersRepo.findAll();
		
		
		

		
		return "login";
	}
	
	
	
}
