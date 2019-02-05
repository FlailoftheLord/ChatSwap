package net.flail.chatswap.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import net.flail.chatswap.ChatSwap;

public class ChatUtils {

	public String chat(String msg) {

		ChatSwap plugin = JavaPlugin.getPlugin(ChatSwap.class);

		try {

			String newMsg = ChatColor.translateAlternateColorCodes('&',
					msg.replace("%prefix%", plugin.getConfig().getString("Prefix")));

			return newMsg;
		} catch (Throwable t) {
			return msg;
		}

	}

	public String n2t(int input) {

		String zero = "O";
		String one = "L";
		String two = "R";
		String three = "E";
		String four = "H";
		String five = "S";
		String six = "B";
		String seven = "I";
		String eight = "R";
		String nine = "P";

		switch (input) {

		case 0:
			return zero;
		case 1:
			return one;
		case 2:
			return two;
		case 3:
			return three;
		case 4:
			return four;
		case 5:
			return five;
		case 6:
			return six;
		case 7:
			return seven;
		case 8:
			return eight;
		case 9:
			return nine;
		default:
			return input + "";

		}

	}

	public String replaceInt(String string) {

		if (string.contains("(?0)" + Pattern.quote(string))) {

			return string.replaceAll("(?i)" + Pattern.quote("9u"), "qu").replaceAll("(?i)" + Pattern.quote("4r"), "Ar")
					.replaceAll("(?i)" + Pattern.quote("1i"), "li").replaceAll("0", n2t(0)).replaceAll("1", n2t(1))
					.replaceAll("2", n2t(2)).replaceAll("3", n2t(3)).replaceAll("4", n2t(4)).replaceAll("5", n2t(5))
					.replaceAll("6", n2t(6)).replaceAll("7", n2t(7)).replaceAll("8", n2t(8)).replaceAll("9", n2t(9));

		} else {

			return string.replaceAll("0", n2t(0)).replaceAll("1", n2t(1)).replaceAll("2", n2t(2))
					.replaceAll("3", n2t(3)).replaceAll("4", n2t(4)).replaceAll("5", n2t(5)).replaceAll("6", n2t(6))
					.replaceAll("7", n2t(7)).replaceAll("8", n2t(8)).replaceAll("9", n2t(9));

		}

	}

	public String simplifyText(String text, boolean numberstoletters) {
		ChatSwap plugin = JavaPlugin.getPlugin(ChatSwap.class);

		if (numberstoletters) {
			text = replaceInt(text);
		}

		char[] punctuation = { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '`', '~', '\'',
				'\"', '/', '\\', '[', ']', '{', '}' };

		char[] chars = text.toCharArray();

		List<Character> newChars = new ArrayList<>();

		for (char c : chars) {
			for (char element : punctuation) {
				if (c != element) {
					newChars.add(Character.valueOf(c));

				}
			}
		}

		String newString = "";

		for (Character c : newChars) {
			newString += c.toString();

		}

		plugin.console.sendMessage(newString);

		return newString;

	}

	public String condensedText(String string) {
		return this.simplifyText(string.toLowerCase().replace(" ", ""), true);
	}

}
