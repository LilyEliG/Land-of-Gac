package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Thing {
	
	private String name;
	private Person owner;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public Person getOwner() { return owner; }
	public void setOwner(Person owner) { this.owner = owner; }

	public Thing(String name) {
		super();
		this.name = name;
	}
	
	public boolean isOwned() {
		return owner != null;
	}
	
	public void becomeUnowned() {
		owner = null;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void beEaten() {
		// TODO Auto-generated method stub
		Person owner = getOwner();
		if (owner == null) {
			Utility.displayMessage("No one has " + getName());
		} else {
			owner.say("Yum Yum Yummmyyyy! I have eaten " + getName() +". That was delicious!");
		}
	
	}
	
	
	
	
	

}
