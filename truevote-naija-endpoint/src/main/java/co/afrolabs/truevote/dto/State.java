package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class State implements Serializable{

		private static final long serialVersionUID = 1L;
	@Id
    private Long id;
	private String name;
	public State(){
		
	}
	public State(String name){
		this.name= name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
