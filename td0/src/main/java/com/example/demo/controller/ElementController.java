package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Element;

@Controller
public class ElementController {
	
	@ModelAttribute("elements")
	public List<Element> getElements(){
		List<Element> elements = new ArrayList<>();
		return elements;
	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute("elements") List<Element> elements) {
		Element elm = new Element();
		elm.setNom("Test");
		elements.add(elm);
		return "index";
	}
}
