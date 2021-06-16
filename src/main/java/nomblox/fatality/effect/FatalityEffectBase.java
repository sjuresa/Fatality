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

package nomblox.fatality.effect;

import nomblox.fatality.data.EffectSettingsDto;
import org.bukkit.ChatColor;

abstract public class FatalityEffectBase implements FatalityEffect {
    private final String name;
    private final String category;
    private boolean disabledAll;
    private final int nrOfSettings;
    protected EffectSettingsDto settings;

    public FatalityEffectBase(String category, EffectSettingsDto effectSettingsDto, int nrOfSettings, String name) {
        this.name = name;
        this.category = category.toLowerCase();
        if (effectSettingsDto == null) {
            effectSettingsDto = new EffectSettingsDto();
            effectSettingsDto.name = getClass().getName();
        }
        settings = effectSettingsDto;
        this.nrOfSettings = nrOfSettings;
    }

    @Override
    public void previousSetting() {
        settings.selected = (--settings.selected + nrOfSettings) % nrOfSettings;
    }

    @Override
    public void nextSetting() {
        settings.selected = ++settings.selected % nrOfSettings;
    }

    @Override
    public void toggle() {
        settings.enabled = !settings.enabled;
    }

    public void setDisabledAll(boolean disabled) {
        disabledAll = disabled;
    }

    @Override
    public boolean isEnabled() {
        return !disabledAll && settings.enabled;
    }

    @Override
    public String getSettingName() {
        return name;
    }

    @Override
    public String getPermissionSuffix() {
        return category +"." + getName().toLowerCase() + "." + ChatColor.stripColor(getSettingName()).toLowerCase().replace(" ", "_");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return settings.toString();
    }
}