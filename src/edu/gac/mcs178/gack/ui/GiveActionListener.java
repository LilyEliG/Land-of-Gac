package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class GiveActionListener implements ActionListener {
	
	private static final Thing INTSRUCTIONS = new Thing("Give ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox eatJComboBox;
	private boolean enabled;
	private List<Thing> things;
	private List<Person> people;

	public GiveActionListener(GraphicalUserInterface gui, Person player, JComboBox giveJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.eatJComboBox = giveJComboBox;
		this.enabled = true;
		things = player.getPossessions();
		people = player.otherPeopleAtSamePlace();
		giveJComboBox.addItem(INTSRUCTIONS);
		if ((!things.isEmpty()) && (!people.isEmpty())) {
			for (Thing thing : things) {
				giveJComboBox.addItem(thing);
			}
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		eatJComboBox.removeAllItems();
		things = player.getPossessions();
		people = player.otherPeopleAtSamePlace();
		eatJComboBox.addItem(INTSRUCTIONS);
		if ((!things.isEmpty()) && (!people.isEmpty())) {
			for (Thing thing : things) {
				eatJComboBox.addItem(thing);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Thing item = (Thing) eatJComboBox.getSelectedItem();
			if (!item.equals(INTSRUCTIONS)) {
				JPopupMenu popup = new JPopupMenu();
				for (Person person : people) {
					popup.add(new GiveAction("Give " + item + " to " + person, player, item, person, gui));
				}
				popup.show(eatJComboBox, 0, 0);
			}
		}
	}
}

/**
 * A sample action that prints the action name to System.out
 */
@SuppressWarnings("serial")
class GiveAction extends AbstractAction  {
	
	private Person player;
	private Thing item;
	private Person recipient;
	private GraphicalUserInterface gui;
	
	public GiveAction(String name, Person player, Thing item, Person recipient, GraphicalUserInterface gui) {
		super(name);
		this.player = player;
		this.item = item;
		this.recipient = recipient;
		this.gui = gui;
	}

	public void actionPerformed(ActionEvent event) {
		//.give(item player). play says something(gui.displayMessage(player. player lose
		player.give(item, player);
		gui.displayMessage("I " + player + " bestow " + item + " to " + recipient);
		player.lose(item);
		gui.displayMessage("Thank you " + player + "for the honor of receiving this " + item);
		gui.playTurn();
	}
}

