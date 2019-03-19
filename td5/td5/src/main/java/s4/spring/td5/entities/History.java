package s4.spring.td5.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class History {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String date;
	
	private String content;
	
	private String comment;
	
	@ManyToOne//(mappedBy="", cascade=CascadeType.ALL)
	private Script script; 
	
}
