/*
 * Fatality
 * Copyright (C) 2021 Nejc Korošec and Simon Jureša
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nomblox.fatality.effect.text;

import net.md_5.bungee.api.ChatColor;
import nomblox.fatality.data.EffectSettingsDto;
import nomblox.fatality.data.TextCategory;
import nomblox.fatality.effect.FatalityEffect;
import nomblox.fatality.effect.FatalityEffectBase;
import org.bukkit.Bukkit;

import java.util.Random;

import static nomblox.fatality.data.Effects.capitalize;
import static nomblox.fatality.effect.EffectConstants.*;

abstract public class Text extends FatalityEffectBase implements FatalityEffect {
    protected Random random = new Random();

    public Text(EffectSettingsDto effectSettingsDto, String name) {
        super(TextCategory.NAME, effectSettingsDto, COMIC_COLORS.length, name);
    }

    @Override
    public String getSettingName() {
        if (settings.selected == PREDEFINED_RANDOM) {
            return PREDEFINED_RANDOM_NAME;
        }
        if (settings.selected == RGB_RANDOM) {
            return RGB_RANDOM_NAME;
        }
        return COMIC_COLORS[settings.selected].toString() + capitalize(COMIC_COLORS[settings.selected].getName().toLowerCase().replace("_", " "));
    }

    protected ChatColor getColor() {
        if (settings.selected != PREDEFINED_RANDOM && settings.selected != RGB_RANDOM) {
            return COMIC_COLORS[settings.selected];
        }
        if (settings.selected == RGB_RANDOM && is1_16()) {
            return ChatColor.of("#" + String.format("%06X", random.nextInt(16777216)));
        } else {
            return ChatColor.getByChar(String.format("%x", random.nextInt(16)).charAt(0));
        }
    }

    private boolean is1_16() {
        return Bukkit.getVersion().contains("1.16");
    }

    protected void setSeed(long seed) {
        random.setSeed(seed);
    }

    abstract public String getText();
}