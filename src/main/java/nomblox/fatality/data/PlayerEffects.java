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

package nomblox.fatality.data;

import java.util.List;
import java.util.Map;

public class PlayerEffects {
    private final Effects mobSetting;
    private final Effects monsterEffects;
    private final Effects playerEffects;

    public PlayerEffects(Map<String, Map<String, List<EffectSettingsDto>>> settingsForPlayer) {
        mobSetting = new Effects("mob", settingsForPlayer.get("Mob"));
        monsterEffects = new Effects("monster", settingsForPlayer.get("Monster"));
        playerEffects = new Effects("player", settingsForPlayer.get("Player"));
    }

    public Effects getMobSettings() {
        return mobSetting;
    }

    public Effects getMonsterSettings() {
        return monsterEffects;
    }

    public Effects getPlayerSettings() {
        return playerEffects;
    }
}