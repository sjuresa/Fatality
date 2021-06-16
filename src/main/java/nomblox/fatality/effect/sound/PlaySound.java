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

package nomblox.fatality.effect.sound;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static nomblox.fatality.effect.EffectConstants.SOUND_VOLUME;

public class PlaySound implements Runnable {
    private SoundParams[] soundParams;
    private int index;
    private JavaPlugin plugin;
    private Player player;

    public PlaySound(SoundParams[] soundParams, int index, JavaPlugin plugin, Player player) {
        this.soundParams = soundParams;
        this.index = index;
        this.plugin = plugin;
        this.player = player;
    }

    @Override
    public void run() {
        SoundParams params = soundParams[index];
        player.playSound(player.getLocation(), params.sound, SOUND_VOLUME, params.pitch);
        if (++index != soundParams.length) {
            Bukkit.getScheduler().runTaskLater(plugin, new PlaySound(soundParams, index, plugin, player), params.delay);
        }
    }
}