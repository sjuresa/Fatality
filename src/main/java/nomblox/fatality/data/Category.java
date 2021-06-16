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

package nomblox.fatality.data;

import nomblox.fatality.effect.FatalityEffect;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

abstract public class Category {
    protected Map<String, FatalityEffect> effects = new HashMap<>();
    protected List<FatalityEffect> effectsList = new ArrayList<>();
    private final String name;

    public Category(List<EffectSettingsDto> settings, String name) {
        this.name = name;
        for (EffectSettingsDto effectSettingsDto : settings) {
            FatalityEffect fatalityEffect = createEffect(effectSettingsDto, name.toLowerCase());
            if (fatalityEffect != null) {
                effects.put(fatalityEffect.getClass().getName(), fatalityEffect);
                effectsList.add(fatalityEffect);
            }
        }
    }

    protected FatalityEffect createEffect(EffectSettingsDto effectSettingsDto, String packageName) {
        FatalityEffect fatalityEffect = null;
        String name = effectSettingsDto.name;
        try {
            Class<?> c;
            if (name.startsWith("nomblox")) {
                c = Class.forName(name);
            } else {
                if ("effect".equalsIgnoreCase(name)) {  // SoundEffect name exception
                    name = "SoundEffect";
                }
                c = Class.forName("nomblox.fatality.effect." + packageName + "." + name);
            }
            Constructor<?> cons = c.getConstructor(EffectSettingsDto.class);
            fatalityEffect = (FatalityEffect) cons.newInstance(effectSettingsDto);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return fatalityEffect;
    }

    public Map<String, FatalityEffect> getEffects() {
        return effects;
    }

    public List<FatalityEffect> getEffectsList() {
        return effectsList;
    }

    public void setDisabledAll(boolean disableAll) {
        for (Map.Entry<String, FatalityEffect> effect : effects.entrySet()) {
            effect.getValue().setDisabledAll(disableAll);
        }
    }

    public FatalityEffect getEffect(Class<?> clazz) {
        return effects.get(clazz.getName());
    }

    protected FatalityEffect getRandomActiveEffect(Player player, String entity) {
        ArrayList<FatalityEffect> activeEffects = getActive();
        if (activeEffects.isEmpty()) {
            return null;
        }
        Random random = new Random();
        FatalityEffect fatalityEffect = activeEffects.get(random.nextInt(activeEffects.size()));
        String permissionPrefix = "fatality." + entity + ".";
        if (fatalityEffect != null && player.hasPermission(permissionPrefix + fatalityEffect.getPermissionSuffix())) {
            return fatalityEffect;
        }
        return null;
    }

    private ArrayList<FatalityEffect> getActive() {
        ArrayList<FatalityEffect> activeEffects = new ArrayList<>();
        for (Map.Entry<String, FatalityEffect> effect : effects.entrySet()) {
            if (effect.getValue().isEnabled()) {
                activeEffects.add(effect.getValue());
            }
        }
        return activeEffects;
    }

    public String getName() {
        return name;
    }
}