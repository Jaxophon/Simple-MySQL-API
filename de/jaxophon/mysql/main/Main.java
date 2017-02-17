package de.jaxophon.mysql.main;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Jan Scheel
 * @version 0.0.1
 * @category Database & Connection
 * @since 29.01.2017
 * 
 *        The classes of this project are legally copy-protected. Copying or
 *        decompiling is strictly forbidden!
 */

public class Main extends JavaPlugin {

	public static Main main;

	@Override
	public void onEnable() {
		main = this;
		System.out.println("[MySQL] was loaded successfully");
	}

	@Override
	public void onDisable() {
		System.out.println("[MySQL] was unloaded successfully");

	}

}
