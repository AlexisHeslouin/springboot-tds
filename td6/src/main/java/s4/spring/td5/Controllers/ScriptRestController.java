package s4.spring.td5.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.td5.entities.Script;
import s4.spring.td5.repositories.ScriptRepository;

@RequestMapping("/search")
@RestController
public class ScriptRestController {
	@Autowired
	private ScriptRepository ScriptRepo;
	
	@ResponseBody
	@GetMapping("")
	public List<Script> get(String crit){
		List<Script> scripts = ScriptRepo.findAll();
		List<Script> res = null;
		for(Script scr : scripts) {
			if(scr.getTitle() == crit)
				res.add(scr);
		}
		return res;
	}

}
