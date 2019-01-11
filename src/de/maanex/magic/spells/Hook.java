package de.maanex.magic.spells;


import de.maanex.magic.MagicPlayer;
import de.maanex.magic.missile.HookMissile;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;
import de.maanex.magic.wands.WandValues.WandModifier;


public class Hook extends MagicSpell {

	public Hook() {
		super(53, "Hook", "Zieht schlawiner wieder dahin, wo sie hingehören!", 2, 5, SpellType.ACTIVE, SpellCategory.UTILITY, SpellRarity.VERY_RARE);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		HookMissile mis = new HookMissile(caster.getMCPlayer().getEyeLocation(), caster, caster.getMCPlayer().getLocation().clone(), val.getMod(WandModifier.ENERGY));
		mis.launch();
		takeMana(caster, val);
	}

}
