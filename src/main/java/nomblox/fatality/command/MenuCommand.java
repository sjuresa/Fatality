/*
 * Fatality
 * Copyright (C) 2020 Nejc Korošec and Simon Jureša
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

package nomblox.fatality.command;

import nomblox.fatality.FatalityPlugin;
import nomblox.fatality.gui.FatalityMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {
    private final FatalityPlugin fatalityPlugin;
    private FatalityMenu fatalityMenu;

    public MenuCommand(FatalityPlugin fatalityPlugin) {
        this.fatalityPlugin = fatalityPlugin;
    }

    @Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull CommandSender commandSender, @org.jetbrains.annotations.NotNull Command command, @org.jetbrains.annotations.NotNull String s, @org.jetbrains.annotations.NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player)commandSender;
            if (fatalityMenu == null) {
                fatalityMenu = new FatalityMenu(fatalityPlugin.getStorage(), fatalityPlugin.isVer1_16());
            }
            fatalityMenu.openMainMenu(player);
            return true;
        }
        return false;
    }
}