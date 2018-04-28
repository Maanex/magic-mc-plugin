package de.maanex.magic.missile;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

import de.maanex.magic.MagicPlayer;


public abstract class MagicMissile {

	public Location		position;
	public MagicPlayer	sender;

	public MagicMissile(Location startPos, MagicPlayer sender) {
		this.position = startPos;
		this.sender = sender;
	}

	public abstract void tick();

	/*
	 * 
	 */

	private static List<MagicMissile> activeMissiles = new ArrayList<>();

	public void launch() {
		if (!activeMissiles.contains(this)) activeMissiles.add(this);
	}

	public void destroy() {
		if (activeMissiles.contains(this)) activeMissiles.remove(this);
	}

	public static void doTick() {
		new ArrayList<>(activeMissiles).forEach(a -> a.tick());
	}

}
