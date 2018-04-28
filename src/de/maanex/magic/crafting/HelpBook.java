package de.maanex.magic.crafting;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BookMeta;


public class HelpBook {

	@SuppressWarnings("deprecation")
	public static void registerRecipe() {
		ShapedRecipe res = new ShapedRecipe(getBook());

		res.shape("000", "0B0", "000");

		res.setIngredient('B', Material.BOOK);

		Bukkit.addRecipe(res);
	}

	private static ItemStack getBook() {
		ItemStack out = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta m = (BookMeta) out.getItemMeta();

		m.setTitle("�2Codex Magica - �ber Zauberei");
		m.setAuthor("Tude Magic Division");

		//

		m.addPage("-------------------" + //
				"\n�ber Zauberei" + //
				"\n-------------------" + //
				"\n" + //
				"\nEin Lexikon der Tude Magic Division" + //
				"\n" + //
				"\nAuf den n�chsten Seiten finden Sie alle grundlegenen Dinge die Sie �ber Magie wissen m�ssen."//
		);

		m.addPage("�l�nInhalt�r" + //
				"\n" + //
				"\n1. Mana und nat�rliche Magie" + //
				"\n" + //
				"\n2. Magische Utensilien" + //
				"\n" + //
				"\n3. Zauberspr�che erforschen" + //
				"\n" + //
				"\n4. Grundbegriffe" + //
				"\n" + //
				"\n5. Sonstiges & Dank" //
		);

		m.addPage("-------------------" + //
				"\n�lKap I:�0 Mana und nat�rliche Magie" + //
				"\n-------------------" + //
				"\n" + //
				"\nEin jeder besitzt Mana. Sie ist klar sichtbar �ber der Schnellzugriffsleiste des Spielers sichtbar. Aufgeteilt in anfangs 20 Balken, zeigen die blau gef�rbten, wie viele Einheiten" //
		);

		m.addPage("an Mana verbleiben" + //
				"\n" + //
				"\nMana regeneriert automatisch, allerdings nur die hellgrau gef�rbte Distanz. Um den dunkelgrauen Bereich mit Mana zu f�llen, werden andere Methoden ben�tigt: Der einfachste Weg, schneller Mana zu regenerieren und diese Limitierung zu" //
		);

		m.addPage("umgehen ist das benutzen von Manatr�nken. Dazu mehr in Kapitel II." //
		);

		m.addPage("-------------------" + //
				"\n�lKap II:�0 Magische Utensilien" + //
				"\n-------------------" + //
				"\n" + //
				"\nDas wichtigste Werkzeug eines jeden Zauberers ist sicher sein Zauberstab. Hergestellt wird dieser ganz einfach durch platzierung eines Stockes in der Mitte einer Werkbank und" //
		);

		m.addPage(
				"zwei Eisennuggets oben rechts und unten links davon. Dieser Zauberstab besitzt allerdings noch nicht die F�higkeit damit zu Zaubern. Um dies zu erm�glichen, muss einfach mit diesem in der Hand ein Zaubertisch angeklickt werden. F�r diesen Prozess sind" //
		);

		m.addPage("3 Erfahrungslevel von n�ten." + //
				"\n" + //
				"\n Ein Zauberstab alleine reicht allerdings noch nicht um zu Zaubern: Ebenso essenziell wie jender ist f�r jeden Zauberer sein Zauberbuch in dem er seine gelernten Zauberspr�che nachschlagen kann. Zur Herstellung von"//
		);
		m.addPage(
				"einem Zauberbuch muss in einer Werkbank ein normales Buch vollst�ndig mit Eisennuggets umrandet werden. Vom Tisch entnommen, kann es mit rechtsklick ge�ffnet und Zauberspr�che darin abgelegt werden. (Mehr dazu in Kapittel III)"
						+ //
						"\n" + //
						"\nAls letztes wichtiges"//
		);

		//

		out.setItemMeta(m);
		return out;
	}
}