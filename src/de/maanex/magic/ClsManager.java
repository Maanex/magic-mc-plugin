package de.maanex.magic;


import java.util.ArrayList;
import java.util.List;

import de.maanex.magic.spells.AirBlast;
import de.maanex.magic.spells.ArrowStorm;
import de.maanex.magic.spells.Comet;
import de.maanex.magic.spells.DarkSeal;
import de.maanex.magic.spells.EarthBenderBridge;
import de.maanex.magic.spells.EarthBenderCannon;
import de.maanex.magic.spells.Fireball;
import de.maanex.magic.spells.Firepunch;
import de.maanex.magic.spells.Frostwave;
import de.maanex.magic.spells.HolyShield;
import de.maanex.magic.spells.Knock;
import de.maanex.magic.spells.Levitate;
import de.maanex.magic.spells.MagmaWorm;
import de.maanex.magic.spells.Nitro;
import de.maanex.magic.spells.PainfullSting;
import de.maanex.magic.spells.ProtectionWall;
import de.maanex.magic.spells.Strike;
import de.maanex.magic.spells.Stun;
import de.maanex.magic.spells.TheSeeker;
import de.maanex.magic.spells.TrueSight;
import de.maanex.magic.spells.Warp;
import de.maanex.magic.spells.WaterBenderSplash;


public class ClsManager {

	private ClsManager() {
	}

	public static List<MagicSpell> spells = new ArrayList<>();

	//

	static {
		spells.add(new Strike());
		spells.add(new Comet());
		spells.add(new ProtectionWall());
		spells.add(new Knock());
		spells.add(new AirBlast());
		spells.add(new Nitro());
		spells.add(new ArrowStorm());
		spells.add(new Warp());
		spells.add(new HolyShield());
		spells.add(new Fireball());
		spells.add(new TheSeeker());
		spells.add(new TrueSight());
		spells.add(new Frostwave());
		spells.add(new PainfullSting());
		spells.add(new MagmaWorm());
		spells.add(new EarthBenderBridge());
		spells.add(new EarthBenderCannon());
		spells.add(new DarkSeal());
		spells.add(new Levitate());
		spells.add(new Stun());
		spells.add(new Firepunch());
		spells.add(new WaterBenderSplash());
	}
}
