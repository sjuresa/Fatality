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

import org.bukkit.Material;

public interface FatalityEffect {
    /**
     * select previous setting
     */
    void previousSetting();

    /**
     * select next setting
     */
    void nextSetting();

    /**
     * enable/disable effect
     */
    void toggle();

    /**
     * Disable/"undisable" all effects in category.
     * @param disabled = true disable all effects / false all effects are set back to previous state
     */
    void setDisabledAll(boolean disabled);

    /**
     *
     * @return Color code + color name for example: ChatColor.RED+"Red"
     */
    String getSettingName();

    /**
     *
     * @return true if effect is enabled
     */
    boolean isEnabled();

    /**
     *
     * @return effect name
     */
    String getName();

    /**
     *
     * @return true if player has permission for effect settings
     */
    String getPermissionSuffix();

    /**
     * Get menu icon material
     */
    Material getMenuIcon();
}