package me.neznamy.tab.shared.command.level2;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.shared.TAB;
import me.neznamy.tab.shared.command.SubCommand;

import java.util.List;

public class ListPriorityCommand extends SubCommand {

    public ListPriorityCommand() {
        super("list", "tab.group.priority.list");
    }

    public void execute(TabPlayer sender, String[] args) {
        List<String> list = TAB.getInstance().getConfiguration().getConfig().getStringList("group-sorting-priority-list");
        sender.sendMessage("",false);
        sender.sendMessage(SubCommand.getTranslation("position-list-title"), true);
        sender.sendMessage("",false);
        if (list.isEmpty()) {
            sender.sendMessage(SubCommand.getTranslation("position-list-empty"), true);
            return;
        }
        list.forEach((name)-> {
            int position = list.lastIndexOf(name);
            sender.sendMessage("&8• &7#" + position + " " + name,true);
        });
        sender.sendMessage("",false);
    }
}
