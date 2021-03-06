/*
 *  This file is part of Player Analytics (Plan).
 *
 *  Plan is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License v3 as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Plan is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Plan. If not, see <https://www.gnu.org/licenses/>.
 */
package com.djrapitops.pluginbridge.plan.griefprevention;

import com.djrapitops.plan.data.plugin.HookHandler;
import com.djrapitops.plugin.logging.console.PluginLogger;
import com.djrapitops.pluginbridge.plan.Hook;
import me.ryanhamshire.GriefPrevention.DataStore;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

/**
 * A Class responsible for hooking to GriefPrevention and registering data
 * sources.
 *
 * @author Rsl1122

 */
@Singleton
public class GriefPreventionHook extends Hook {

    private final PluginLogger logger;

    @Inject
    public GriefPreventionHook(
            PluginLogger logger
    ) {
        super("me.ryanhamshire.GriefPrevention.GriefPrevention");
        this.logger = logger;
    }

    public void hook(HookHandler handler) throws NoClassDefFoundError {
        if (enabled) {
            DataStore dataStore = getPlugin(GriefPrevention.class).dataStore;
            if (dataStore == null) {
                logger.warn("GriefPrevention DataStore was not initialized yet. Run '/plan reload' to try to re-hook.");
                return;
            }
            handler.addPluginDataSource(new GriefPreventionData(dataStore));
        }
    }
}
