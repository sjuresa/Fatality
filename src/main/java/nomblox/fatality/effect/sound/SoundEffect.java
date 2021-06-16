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

import nomblox.fatality.data.EffectSettingsDto;
import nomblox.fatality.data.SoundCategory;
import nomblox.fatality.effect.FatalityEffect;
import nomblox.fatality.effect.FatalityEffectBase;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static nomblox.fatality.effect.EffectConstants.SOUND_NAMES;
import static nomblox.fatality.effect.EffectConstants.SOUND_PARAMS;

public class SoundEffect extends FatalityEffectBase implements FatalityEffect {
    public static final String NAME = "Effect";

    public SoundEffect(EffectSettingsDto effectSettingsDto) {
        super(SoundCategory.NAME, effectSettingsDto, SOUND_NAMES.length, NAME);
    }

    public void playSound(JavaPlugin plugin, Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, new PlaySound(SOUND_PARAMS[settings.selected], 0, plugin, player), 0);
    }

    @Override
    public String getSettingName() {
        return SOUND_NAMES[settings.selected];
    }

    @Override
    public Material getMenuIcon() {
        return Material.BELL;
    }
}