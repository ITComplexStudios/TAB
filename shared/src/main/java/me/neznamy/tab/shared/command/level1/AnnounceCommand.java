package me.neznamy.tab.shared.command.level1;

import java.util.Arrays;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.shared.command.SubCommand;
import me.neznamy.tab.shared.command.level2.AnnounceBarCommand;
import me.neznamy.tab.shared.command.level2.AnnounceScoreboardCommand;

/**
 * Handler for "/tab announce" subcommand
 */
public class AnnounceCommand extends SubCommand {

	/**
	 * Constructs new instance and registers subcommands
	 */
	public AnnounceCommand() {
		super("announce", null);
		getSubcommands().put("bar", new AnnounceBarCommand());
		getSubcommands().put("scoreboard", new AnnounceScoreboardCommand());
	}

	@Override
	public void execute(TabPlayer sender, String[] args) {
		if (args.length == 0) {
			sendMessage(sender, "&cSyntax&8: &3&l/tab announce &l<type> &3<bar name> &9<length>");
			sendMessage(sender, "&8Currently supported types: &7bar");
			return;
		}
		String arg0 = args[0].toLowerCase();
		SubCommand command = getSubcommands().get(arg0);
		if (command != null) {
			if (command.hasPermission(sender)) {
				command.execute(sender, Arrays.copyOfRange(args, 1, args.length));
			} else {
				sendMessage(sender, getTranslation("no_permission"));
			}
		} else {
			sendMessage(sender, "&cSyntax&8: &3&l/tab announce &9<type> &3<bar name> &9<length>");
			sendMessage(sender, "&8Currently supported types: &7bar");
		}
	}
}