/*
 * Fatality
 * Copyright (C) 2021 Nejc Korošec and Simon Jureša
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nomblox.fatality;

import bstats.FatalityMetrics;
import nomblox.fatality.command.MenuCommand;
import nomblox.fatality.data.Storage;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.ipvp.canvas.MenuFunctionListener;

import java.io.File;

public class FatalityPlugin extends JavaPlugin {
    private Storage storage;
    private final boolean ver1_16;

    public FatalityPlugin() {
        super();
        ver1_16 = Bukkit.getVersion().contains("1.16");
    }

    protected FatalityPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
        ver1_16 = true;
    }

    @Override
    public void onEnable() {
        storage = new Storage(getDataFolder().toString());
        getServer().getPluginManager().registerEvents(new MenuFunctionListener(), this);
        FatalityListener fatalityListener = new FatalityListener(this);
        getServer().getPluginManager().registerEvents(fatalityListener, this);
        PluginCommand pluginCommand = getCommand("fatalitymenu");
        if (pluginCommand != null) {
            pluginCommand.setExecutor(new MenuCommand(this));
        }
        try {
            new FatalityMetrics(this, 9445);
        } catch (Exception ignored) {}
    }

    public Storage getStorage() {
        return storage;
    }

    public boolean isVer1_16() {
        return ver1_16;
    }
}