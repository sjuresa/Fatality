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

import java.util.HashMap;
import java.util.Map;

public class Data {
    private String version;
    private final Map<String, Map<String, Map<String, EffectDto>>> playerSettings = new HashMap<>();

    public String getVersion() {
        if (version == null) {
            version = "1.5";
        }
        return version;
    }

    public void putEffectSetting(String playerId, String entity, String effectName, EffectDto effectDto) {
        Map<String, EffectDto> effectSettings = getSettingsForEntity(playerId, entity);
        effectSettings.put(effectName, effectDto.clone());
    }

    public EffectDto getSettingsForEffect(String playerId, String entity, String effectName) {
        Map<String, EffectDto> effectSettings = getSettingsForEntity(playerId, entity);
        if ("Blood".equalsIgnoreCase(effectName)) {
            return effectSettings.get("Blood").clone();
        }
        return null;
    }

    protected Map<String, EffectDto> getSettingsForEntity(String playerId, String entity) {
        return getSettingsForPlayer(playerId).computeIfAbsent(entity, k -> new HashMap<>());
    }

    protected Map<String, Map<String, EffectDto>> getSettingsForPlayer(String playerId) {
        return playerSettings.computeIfAbsent(playerId, k -> {
            Map<String, Map<String, EffectDto>> entities = new HashMap<>();
            entities.put("mob", newCategory());
            entities.put("monster", newCategory());
            entities.put("player", newCategory());
            return entities;
        });
    }

    private Map<String, EffectDto> newCategory() {
        Map<String, EffectDto> category = new HashMap<>();
        category.put("Blood", new EffectDto());
        category.put("Comic", new EffectDto());
        category.put("Effect", new EffectDto());
        return category;
    }

    protected void printPlayerSettings() {
        System.out.println("version:" + getVersion());
        System.out.println("players:"+playerSettings.size());
        for (Map.Entry<String, Map<String, Map<String, EffectDto>>> entity : playerSettings.entrySet()) {
            System.out.println("Key = " + entity.getKey() +
                    ", Value = " + entity.getValue());
            Map<String, Map<String, EffectDto>> bla = entity.getValue();
            for (Map.Entry<String, Map<String, EffectDto>> effect : bla.entrySet()) {
                System.out.println("EfKey = " + effect.getKey() +
                        ", EfValue = " + effect.getValue());

            }
        }
    }

    protected Map<String, Map<String, Map<String, EffectDto>>> getPlayerSettings() {
        return playerSettings;
    }
}