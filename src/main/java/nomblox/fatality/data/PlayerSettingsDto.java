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

import nomblox.fatality.effect.particle.*;
import nomblox.fatality.effect.sound.SoundEffect;
import nomblox.fatality.effect.special.Burn;
import nomblox.fatality.effect.special.ItemFountain;
import nomblox.fatality.effect.text.Comic;
import nomblox.fatality.effect.text.Sarcastic;
import nomblox.fatality.effect.text.Terminator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerSettingsDto {
    private final int CURRENT_VERSION = 5;
    private int version = CURRENT_VERSION;
    private final Map<String, Map<String, Map<String, List<EffectSettingsDto>>>> players = new HashMap<>();

    public PlayerSettingsDto() {
    }

    protected Map<String, Map<String, List<EffectSettingsDto>>> getSettingsForPlayer(String playerId) {
        return players.computeIfAbsent(playerId, k -> {
            Map<String, Map<String, List<EffectSettingsDto>>> entities = new HashMap<>();
            entities.put("Mob", createCategories());
            entities.put("Monster", createCategories());
            entities.put("Player", createCategories());
            return entities;
        });
    }

    @NotNull
    private Map<String, List<EffectSettingsDto>> createCategories() {
        Map<String, List<EffectSettingsDto>> categories = new HashMap<>();
        categories.put(ParticleCategory.NAME, getEffectSettings(Blood.class.getName(), BubblePop.class.getName(), GraySmoke.class.getName(),
                Vaporize.class.getName(), PixieDust.class.getName(), PlasmaBurst.class.getName(), Condensate.class.getName()));
        categories.put(TextCategory.NAME, getEffectSettings(Comic.class.getName(), Sarcastic.class.getName(), Terminator.class.getName()));
        categories.put(SoundCategory.NAME, getEffectSettings(SoundEffect.class.getName()));
        categories.put(SpecialCategory.NAME, getEffectSettings(Burn.class.getName(), ItemFountain.class.getName()));
        return categories;
    }

    @NotNull
    private List<EffectSettingsDto> getEffectSettings(String... names) {
        List<EffectSettingsDto> effects;
        effects = new ArrayList<>();
        for (String name : names) {
            effects.add(newEffect(name));
        }
        return effects;
    }

    private EffectSettingsDto newEffect(String name) {
        EffectSettingsDto effectSettingsDto = new EffectSettingsDto();
        effectSettingsDto.name = name;
        return effectSettingsDto;
    }

    protected void printPlayerSettings() {
        System.out.println("version:" + version);
        System.out.println("players:"+players.size());
        for (Map.Entry<String, Map<String, Map<String, List<EffectSettingsDto>>>> entity : players.entrySet()) {
            System.out.println("Player id = " + entity.getKey());
            Map<String, Map<String, List<EffectSettingsDto>>> bla = entity.getValue();
            for (Map.Entry<String, Map<String, List<EffectSettingsDto>>> effect : bla.entrySet()) {
                System.out.println("Category = " + effect.getKey() +
                        ", settings = " + effect.getValue());
            }
        }
    }

    public boolean update() {
        if (version == CURRENT_VERSION) {
            return false;
        }
        System.out.println("update from version " + version);
        boolean updated = false;
        if (version == 2) {
            for (Map.Entry<String, Map<String, Map<String, List<EffectSettingsDto>>>> player : players.entrySet()) {
                Map<String, Map<String, List<EffectSettingsDto>>> playerSettings = player.getValue();
                for (Map.Entry<String, Map<String, List<EffectSettingsDto>>> entity : playerSettings.entrySet()) {
                    entity.getValue().put(SpecialCategory.NAME, getEffectSettings(Burn.NAME));
                }
            }
            version = CURRENT_VERSION;
            updated = true;
        }
        if (version == 3) {
            for (Map.Entry<String, Map<String, Map<String, List<EffectSettingsDto>>>> player : players.entrySet()) {
                Map<String, Map<String, List<EffectSettingsDto>>> playerSettings = player.getValue();
                for (Map.Entry<String, Map<String, List<EffectSettingsDto>>> entity : playerSettings.entrySet()) {
                    Map<String, List<EffectSettingsDto>> categorySettings = entity.getValue();
                    for (Map.Entry<String, List<EffectSettingsDto>> category : categorySettings.entrySet()) {
                        if (SpecialCategory.NAME.equalsIgnoreCase(category.getKey())) {
                            category.getValue().add(newEffect(ItemFountain.NAME));
                        }
                    }
                }
            }
            version = 4;
            updated = true;
        }
        if (version == 4) {
            for (Map.Entry<String, Map<String, Map<String, List<EffectSettingsDto>>>> player : players.entrySet()) {
                Map<String, Map<String, List<EffectSettingsDto>>> playerSettings = player.getValue();
                for (Map.Entry<String, Map<String, List<EffectSettingsDto>>> entity : playerSettings.entrySet()) {
                    Map<String, List<EffectSettingsDto>> categorySettings = entity.getValue();
                    for (Map.Entry<String, List<EffectSettingsDto>> category : categorySettings.entrySet()) {
                        if (ParticleCategory.NAME.equalsIgnoreCase(category.getKey())) {
                            category.getValue().add(newEffect(BubblePop.NAME));
                            category.getValue().add(newEffect(GraySmoke.NAME));
                            category.getValue().add(newEffect(Vaporize.NAME));
                            category.getValue().add(newEffect(PixieDust.NAME));
                            category.getValue().add(newEffect(PlasmaBurst.NAME));
                            category.getValue().add(newEffect(Condensate.NAME));
                        }
                        if (SpecialCategory.NAME.equalsIgnoreCase(category.getKey())) {
                            List<EffectSettingsDto> effectSettingsDtos = category.getValue();
                            for (EffectSettingsDto effectSettingsDto : effectSettingsDtos) {
                                if ("Item fountain".equalsIgnoreCase(effectSettingsDto.name)) {
                                    effectSettingsDto.name = ItemFountain.NAME;
                                }
                            }
                        }
                    }
                }
            }
            version = 5;
            updated = true;
        }
        System.out.println("updated to version " + version);
        return updated;
    }
}