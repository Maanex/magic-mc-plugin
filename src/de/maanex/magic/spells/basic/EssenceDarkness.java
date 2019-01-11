package de.maanex.magic.spells.basic;


import de.maanex.magic.MagicPlayer;
import de.maanex.magic.spell.MagicSpell;
import de.maanex.magic.spell.SpellCategory;
import de.maanex.magic.spell.SpellRarity;
import de.maanex.magic.spell.SpellType;
import de.maanex.magic.wands.WandType;
import de.maanex.magic.wands.WandValues;


public class EssenceDarkness extends MagicSpell {
	public EssenceDarkness() {
		super(29, "Essenz der Dunkelheit", "Der Zugang zu unendlich gro�er Macht!", 50, 100, SpellType.NOT_USEABLE, SpellCategory.UTILITY, SpellRarity.FORBIDDEN, WandType.DARK);
	}

	@Override
	protected void onCastPerform(MagicPlayer caster, WandType type, WandValues val) {
		caster.getMCPlayer().sendMessage("�4Lol wie? xD");
	}

}
