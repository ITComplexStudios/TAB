package me.neznamy.tab.shared.command.level1;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.shared.command.SubCommand;
import me.neznamy.tab.shared.command.level2.ListPriorityCommand;
import me.neznamy.tab.shared.command.level2.SetPriorityCommand;

import java.util.Arrays;

public class PriorityCommand extends PropertyCommand {
    public PriorityCommand() {
        super("priority", null);
        getSubcommands().put("set", new SetPriorityCommand());
        getSubcommands().put("list", new ListPriorityCommand());
    }

    @Override
    public void execute(TabPlayer sender, String[] args) {
        if (args.length == 0) {
            sendMessage(sender, "&cSyntax&8: &3&l/tab priority &9<set|list>");
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
            sendMessage(sender, "&cSyntax&8: &3&l/tab priority &9<set|list>");
            ;
        }
    }
}
