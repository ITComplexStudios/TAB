package me.neznamy.tab.shared.command.level2;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.shared.TAB;
import me.neznamy.tab.shared.command.SubCommand;

import java.util.List;

public class SetPriorityCommand extends SubCommand {

    public SetPriorityCommand() {
        super("set", "tab.group.priority.set");
    }

    @Override
    public void execute(TabPlayer sender, String[] args) {
        if (args.length != 2) {
            sendMessage(sender, "Usage: /tab priority set <group> <priority>");
            return;
        }
        List<String> groups = TAB.getInstance().getConfiguration().getConfig().getStringList("group-sorting-priority-list");
        String group = args[0].toLowerCase();
        int position = isInteger(args[1]);
        if (groups.contains(group)) {
            int old_position = groups.indexOf(group);
            if (position == old_position) {
                sendMessage(sender, getTranslation("position-already-set"));
                return;
            }
            groups.remove(group);
        }
        groups.add(position, group);
        TAB.getInstance().getConfiguration().getConfig().set("group-sorting-priority-list", groups);
        TAB.getInstance().getConfiguration().getConfig().save();
        for (TabPlayer pl : TAB.getInstance().getPlayers()) {
            if (pl.getGroup().equals(group) || group.equals("_OTHER_")) {
                pl.forceRefresh();
            }
        }
        String msg = getTranslation("position-updated")
                .replaceAll("%position%", String.valueOf(position))
                .replaceAll("%group%", group);
        sendMessage(sender, msg);

    }
}
