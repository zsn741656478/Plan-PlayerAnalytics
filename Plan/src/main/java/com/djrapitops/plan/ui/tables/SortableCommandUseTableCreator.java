package main.java.com.djrapitops.plan.ui.tables;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.ui.Html;
import main.java.com.djrapitops.plan.utilities.comparators.MapComparator;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

/**
 *
 * @author Rsl1122
 */
public class SortableCommandUseTableCreator {

    public static String createSortedCommandUseTable(HashMap<String, Integer> commandUse) {
        List<String[]> sorted = MapComparator.sortByValue(commandUse);
        String html = "";
        if (sorted.isEmpty()) {
            html = Html.ERROR_TABLE_2.parse();
            return html;
        }
        Collections.reverse(sorted);
        for (String[] values : sorted) {
            try {
                html += Html.TABLELINE_2.parse(values[1], values[0]);
            } catch (IllegalArgumentException e) {
                Plan plugin = getPlugin(Plan.class);
                plugin.toLog("SortableCommandUseTableCreator", e);
                plugin.toLog("Cause: "+values[1]+" "+values[2]);
            }
        }
        return html;
    }
}
