/*
 * Fatality
 * Copyright (C) 2020 Nejc Korošec and Simon Jureša
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

package nomblox.fatality.gui;

import nomblox.fatality.data.PlayerEffects;
import nomblox.fatality.data.Effects;
import nomblox.fatality.data.Storage;
import nomblox.fatality.effect.FatalityEffect;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.*;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.slot.SlotSettings;
import org.ipvp.canvas.type.ChestMenu;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FatalityMenu {
    public static final String TITLE = "§x§4§A§0§D§0§DF§x§6§0§0§B§0§BA§x§7§7§0§9§0§9T§x§8§D§0§8§0§8A§x§A§4§0§6§0§6L§x§B§B§0§4§0§4I§x§D§1§0§3§0§3T§x§E§8§0§1§0§1Y";
    public static final String PRE_1_6_TITLE = "FATALITY";
    private final Map<String, Menu> menus = new HashMap<>();
    private final Storage storage;
    private final boolean ver1_16;

    public FatalityMenu(Storage storage, boolean ver1_16) {
        this.storage = storage;
        this.ver1_16 = ver1_16;
    }

    private String getTitle() {
        if (ver1_16) {
            return TITLE;
        } else {
            return PRE_1_6_TITLE;
        }
    }

    public void openMainMenu(Player player) {
        getMainMenu(player.getUniqueId().toString()).open(player);
    }

    @NotNull
    public Menu getMainMenu(String playerId) {
        Menu mainMenu = menus.get(playerId);
        if (mainMenu == null) {
            mainMenu = createMainMenu(playerId);
            menus.put(playerId, mainMenu);
        }
        return mainMenu;
    }

    private Menu createMainMenu(String playerId) {
        PlayerEffects playerEffects = storage.getPlayerEffects(playerId);
        Menu mainMenu = ChestMenu.builder(3).title(getTitle()).redraw(true).build();
        mainMenu.setCloseHandler((p, menu) -> storage.saveSettings());
        Menu mobMenu = createSubMenu(mainMenu, playerEffects.getMobSettings());
        Menu monsterMenu = createSubMenu(mainMenu, playerEffects.getMonsterSettings());
        Menu playerMenu = createSubMenu(mainMenu, playerEffects.getPlayerSettings());
        mainMenu.getSlot(2).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMainMenuItem(playerEffects.getMobSettings(), Material.COD))
                .clickHandler(mainMenuHandler(mobMenu, playerEffects.getMobSettings())).build());
        mainMenu.getSlot(4).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMainMenuItem(playerEffects.getMonsterSettings(), Material.SKELETON_SKULL))
                .clickHandler(mainMenuHandler(monsterMenu, playerEffects.getMonsterSettings())).build());
        mainMenu.getSlot(6).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMainMenuItem(playerEffects.getPlayerSettings(), Material.PLAYER_HEAD))
                .clickHandler(mainMenuHandler(playerMenu, playerEffects.getPlayerSettings())).build());
        mainMenu.getSlot(18).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.BARRIER, ChatColor.RED + "Close menu"))
                .clickHandler(closeMenu(mainMenu)).build());
        return mainMenu;
    }

    private ItemStack createMainMenuItem(Effects effects, Material material) {
        return createMenuItem(material, effects.getTitle(), effects.getStatus());
    }

    public Menu createSubMenu(Menu parentMenu, Effects effects) {
        Menu subMenu = ChestMenu.builder(3).redraw(true).build();
        subMenu.setCloseHandler((player, menu) -> storage.saveSettings());
        String entity = effects.getEntity();
        subMenu.getSlot(1).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.NETHER_WART, ChatColor.YELLOW + "Particle (" + entity + ")"))
                .clickHandler((player, info) -> createEffectMenu(subMenu, effects.getParticles().getEffectsList(), effects.getEntity()).open(player)).build());
        subMenu.getSlot(3).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.PAPER, ChatColor.YELLOW + "Text (" + entity + ")"))
                .clickHandler((player, info) -> createEffectMenu(subMenu, effects.getTexts().getEffectsList(), effects.getEntity()).open(player)).build());
        subMenu.getSlot(5).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.NOTE_BLOCK, ChatColor.YELLOW + "Sound (" + entity + ")"))
                .clickHandler((player, info) -> createEffectMenu(subMenu, effects.getSoundEffects().getEffectsList(), effects.getEntity()).open(player)).build());
        subMenu.getSlot(7).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.TOTEM_OF_UNDYING, ChatColor.YELLOW + "Special (" + entity + ")"))
                .clickHandler((player, info) -> createEffectMenu(subMenu, effects.getSpecialCategory().getEffectsList(), effects.getEntity()).open(player)).build());
        addNavigation(subMenu, (player, info) -> parentMenu.open(player));
        return subMenu;
    }

    public Menu createEffectMenu(Menu parentMenu, List<FatalityEffect> effects, String entity) {
        Menu effectMenu = ChestMenu.builder(3).redraw(true).build();
        effectMenu.setCloseHandler((player, menu) -> storage.saveSettings());
        Slot.ClickHandler backHandler = (p, info) -> {
            getMainMenu(p.getUniqueId().toString()).update(p);
            parentMenu.open(p);
        };
        int i = 0;
        for (FatalityEffect fatalityEffect : effects) {
            effectMenu.getSlot(i).setSettings(SlotSettings.builder()
                    .itemTemplate(p ->
                            createMenuItem(fatalityEffect.getMenuIcon(),
                                    ChatColor.YELLOW + fatalityEffect.getName() + " (" + entity + ")",
                                    createEffectLore(p, fatalityEffect, entity))
                    )
                    .clickHandler(changeSetting(effectMenu, fatalityEffect, entity)).build());
            i++;
        }
        addNavigation(effectMenu, backHandler);
        return effectMenu;
    }

    private void addNavigation(Menu menu, Slot.ClickHandler backHandler) {
        menu.getSlot(18).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.BARRIER, ChatColor.RED + "Close menu"))
                .clickHandler(closeMenu(menu)).build());
        menu.getSlot(19).setSettings(SlotSettings.builder()
                .itemTemplate(p -> createMenuItem(Material.CHAIN, ChatColor.RED + "Previous menu"))
                .clickHandler(backHandler).build());
    }

    private Slot.ClickHandler mainMenuHandler(Menu menu, Effects effects) {
        return (player, info) -> {
            if (info.getClickType() == ClickType.LEFT) {
                effects.enable();
                menu.open(player);
            }
            if (info.getClickType() == ClickType.RIGHT) {
                effects.toggle();
                info.getClickedMenu().update(player);
            }
            menu.update(player);
        };
    }

    private Slot.ClickHandler changeSetting(Menu menu, FatalityEffect fatalityEffect, String entity) {
        return (player, info) -> {
            if (info.getClickType() == ClickType.RIGHT) {
                fatalityEffect.previousSetting();
            }
            if (info.getClickType() == ClickType.LEFT) {
                fatalityEffect.nextSetting();
            }
            if (info.getClickType() == ClickType.SHIFT_LEFT && player.hasPermission(getPermission(entity, fatalityEffect))) {
                fatalityEffect.toggle();
            }
            menu.update(player);
        };
    }

    private String getPermission(String entity, FatalityEffect fatalityEffect) {
        return "fatality." + entity + "." + fatalityEffect.getPermissionSuffix();
    }

    private Slot.ClickHandler closeMenu(Menu menu) {
        return (player, info) -> menu.close(player);
    }

    private List<String> createEffectLore(Player player, FatalityEffect fatalityEffect, String entity) {
        List<String> effectLore = new ArrayList<>();
        if (!player.hasPermission(getPermission(entity, fatalityEffect))) {
            effectLore.add(ChatColor.GRAY + "No permission");
        } else if (fatalityEffect.isEnabled()) {
            effectLore.add(ChatColor.GRAY + "This effect is " + ChatColor.GREEN + "ENABLED");
        } else {
            effectLore.add(ChatColor.GRAY + "This effect is " + ChatColor.RED + "DISABLED");
        }
        effectLore.add(ChatColor.GRAY + fatalityEffect.getName() + ": " + fatalityEffect.getSettingName().replace("_", " "));
        effectLore.add("");
        effectLore.add(ChatColor.GRAY + "Shift Left Click to toggle on/off");
        effectLore.add(ChatColor.GRAY + "Left Click for next setting");
        effectLore.add(ChatColor.GRAY + "Right Click for previous setting");
        return effectLore;
    }

    private ItemStack createMenuItem(final Material material, final String name) {
        return createMenuItem(material, name, null);
    }

    private ItemStack createMenuItem(final Material material, final String name, final List<String> lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (lore != null) {
                meta.setLore(lore);
            }
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
            meta.addItemFlags(ItemFlag.HIDE_DYE);
            meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            if (material == Material.SPLASH_POTION) {
                PotionMeta potionMeta = (PotionMeta) meta;
                potionMeta.setBasePotionData(new PotionData(PotionType.WATER, false, false));
            }
        }
        item.setItemMeta(meta);
        return item;
    }
}