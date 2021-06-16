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

import nomblox.fatality.FatalityPlugin;
import nomblox.fatality.effect.FatalityEffect;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Effects {
    private final String entity;
    private final ParticleCategory particles;
    private final TextCategory texts;
    private final SoundCategory soundEffects;
    private final SpecialCategory specialCategory;
    private boolean disableAll;

    public Effects(String entity, Map<String, List<EffectSettingsDto>> entitySettings) {
        this.entity = entity;
        particles = new ParticleCategory(entitySettings.get(ParticleCategory.NAME));
        texts = new TextCategory(entitySettings.get(TextCategory.NAME));
        soundEffects = new SoundCategory(entitySettings.get(SoundCategory.NAME));
        specialCategory = new SpecialCategory(entitySettings.get(SpecialCategory.NAME));
    }

    public void toggle() {
        disableAll = !disableAll;
        setEffectsState();
    }

    public void enable() {
        disableAll = false;
        setEffectsState();
    }

    public String getTitle() {
        return disableAll ? ChatColor.RED + capitalize(entity) + " (disabled)" : ChatColor.YELLOW + capitalize(entity);
    }

    public static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private void setEffectsState() {
        particles.setDisabledAll(disableAll);
        texts.setDisabledAll(disableAll);
        soundEffects.setDisabledAll(disableAll);
        specialCategory.setDisabledAll(disableAll);
    }

    public void applyEffects(LivingEntity livingEntity, Player player, Location location, FatalityPlugin fatalityPlugin) {
        particles.apply(livingEntity, player, location.add(0, 1, 0), getEntity());
        texts.apply(player, getEntity());
        soundEffects.apply(player, entity, fatalityPlugin);
        specialCategory.apply(livingEntity, player, entity, fatalityPlugin);
    }

    public ParticleCategory getParticles() {
        return particles;
    }

    public TextCategory getTexts() {
        return texts;
    }

    public SoundCategory getSoundEffects() {
        return soundEffects;
    }

    public SpecialCategory getSpecialCategory() {
        return specialCategory;
    }

    public String getEntity() {
        return entity;
    }

    /**
     * @return Status for menu lore
     */
    public List<String> getStatus() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Current effects:");
        lore.add("");
        addEnabledEffectsForCategory(lore, particles);
        lore.add("");
        addEnabledEffectsForCategory(lore, texts);
        lore.add("");
        addEnabledEffectsForCategory(lore, soundEffects);
        lore.add("");
        addEnabledEffectsForCategory(lore, specialCategory);
        lore.add("");
        lore.add(ChatColor.GRAY + "Left click to open menu");
        lore.add(ChatColor.GRAY + "Right click to toggle category on/off");
        return lore;

    }

    private void addEnabledEffectsForCategory(List<String> lore, Category category) {
        lore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + category.getName() + ":" + ChatColor.RESET);
        boolean displayed = false;
        for (FatalityEffect fatalityEffect : category.getEffectsList()) {
            if (fatalityEffect.isEnabled()) {
                lore.add(ChatColor.GRAY + "  • " + ChatColor.BOLD + fatalityEffect.getName() + ChatColor.GRAY + " (" + ChatColor.stripColor(fatalityEffect.getSettingName().replace("_", " ")) + ")");
                displayed = true;
            }
        }
        if (!displayed) {
            lore.add(ChatColor.DARK_GRAY + "  • /");
        }
    }
}