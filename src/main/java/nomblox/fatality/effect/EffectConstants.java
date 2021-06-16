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

import net.md_5.bungee.api.ChatColor;
import nomblox.fatality.effect.sound.SoundParams;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.Map;

public class EffectConstants {
    public static final String[] COMIC_EFFECTS = {"WHACK!","SMACK!","THUMP!","BUMP!","WHAM!","SLAM!","KA-BAAM!","KADOOM!","KAPOW!",
            "POW!","KA-CHOW!","KLANK!","KLONK!","KNOCK!","KRAM!","KRANCH!","TAKA!","PHOOM!","BRAM!","BANG!","ZAP!",
            "ZING!","CRASH!","SPLONK!","BIFF!","SPLATT!","SOCK!","VRONK!","POINK!","PONK!"};
    public static final String PREDEFINED_RANDOM_NAME = "§aP§br§ce§dd§ee§ff§1i§2n§3e§4d_§6Random";
    public static final String RGB_RANDOM_NAME = "§cR§aG§9B_§6Random";
    public static final int PREDEFINED_RANDOM = 0;
    public static final int RGB_RANDOM = 1;
    public static final ChatColor[] COMIC_COLORS = {ChatColor.GOLD, ChatColor.GOLD, ChatColor.BLACK, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN,
            ChatColor.DARK_AQUA, ChatColor.DARK_RED, ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.GRAY, ChatColor.DARK_GRAY,
            ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE};
    public static final Material[] BLOOD_MATERIAL = {null, Material.BLACK_CONCRETE, Material.BLUE_CONCRETE, Material.GREEN_CONCRETE,
            Material.CYAN_CONCRETE, Material.RED_CONCRETE, Material.PURPLE_CONCRETE, Material.YELLOW_CONCRETE, Material.LIGHT_GRAY_CONCRETE,
            Material.GRAY_CONCRETE, Material.TUBE_CORAL_BLOCK, Material.LIME_CONCRETE, Material.LIGHT_BLUE_CONCRETE, Material.REDSTONE_BLOCK,
            Material.MAGENTA_CONCRETE, Material.YELLOW_CONCRETE_POWDER, Material.WHITE_CONCRETE};
    public static final ChatColor RANDOM_BLOOD_COLOR = ChatColor.MAGIC;
    public static final ChatColor SMART_BLOOD_COLOR = ChatColor.BOLD;
    public static final ChatColor[] BLOOD_COLORS = {RANDOM_BLOOD_COLOR, ChatColor.BLACK, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN,
            ChatColor.DARK_AQUA, ChatColor.DARK_RED, ChatColor.DARK_PURPLE, ChatColor.GOLD, ChatColor.GRAY, ChatColor.DARK_GRAY,
            ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA, ChatColor.RED, ChatColor.LIGHT_PURPLE, ChatColor.YELLOW, ChatColor.WHITE,
            SMART_BLOOD_COLOR};
    public static final String[] SOUND_NAMES = {"Rimshot", "Homerun", "Figaro", "Squeak", "Splat", "Wet_rubber", "Bleh",
            "Puffer", "Aaah", "Rumble"};
    public static final float SOUND_VOLUME = 0.8f;
    public static final SoundParams[][] SOUND_PARAMS = {
            {   //rimshot
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 2f, 0.1),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_BASEDRUM, 1.5f, 0.4),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_CHIME, 0.8f, 0)
            },
            {   // homerun
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 0.7f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.1f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.4f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.7f, 0.28),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.4f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.7f, 0),
            },
            {   // figaro
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.8f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.6f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.4f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.8f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.6f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.4f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.8f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.6f, 0.14),
                new SoundParams(Sound.BLOCK_NOTE_BLOCK_PLING, 1.4f, 0.14)
            },
            {new SoundParams(Sound.ENTITY_BAT_DEATH, .5f, 0)}, // squeak
            {new SoundParams(Sound.ENTITY_COD_DEATH, 2f, 0)}, // splat
            {new SoundParams(Sound.ENTITY_CREEPER_DEATH, 2f, 0)}, // wet rubber
            {new SoundParams(Sound.ENTITY_EVOKER_DEATH, 2f, 0)}, //  bleh
            {new SoundParams(Sound.ENTITY_PUFFER_FISH_DEATH, 1f, 0)}, // puffer
            {new SoundParams(Sound.ENTITY_RABBIT_DEATH, .5f, 0)}, // aaah
            {new SoundParams(Sound.ENTITY_SNOW_GOLEM_DEATH, .5f, 0)} // rumble
    };
    public static final Map<ChatColor, Material> COLOR_MATERIAL_MAP;
    static {
        COLOR_MATERIAL_MAP = new HashMap<>();
        for (int i = 1; i < BLOOD_MATERIAL.length; i++) {
            COLOR_MATERIAL_MAP.put(BLOOD_COLORS[i], BLOOD_MATERIAL[i]);
        }
    }
    public static final String[] SARCASTIC_EFFECTS = {"Wow, you really showed them.", 
            "Is violence really the answer?", "Why so angry?", "Oh, so barbaric!", "Such an alpha move, bravo.",
            "It's dead now, proud of yourself?", "Don't you think that was a bit much?", "Excessive use of force, why not.",
            "Don't try this at home, kids.", "Was there really no other way?", "Ugh, such a mess.", "That went well.",
            "You got 'em, champ. Now clean your boots.", "You could have just avoided it, you know.",
            "You're trouble alright.", "They asked for it, I'm sure.", "Always so much violence.",
            "How can you even sleep at night?", "You're enjoying this, aren't you?", "How unsavory.", "What was that even about?",
            "You have a mean temper.", "I'm sure they deserved it.", "Was that amount of force necessary?",
            "Not the most gentle way of handling that.", "Another day, another corpse.", "Such a fine day for murder!",
            "That was a rather short conversation.", "That's one way to settle a disagreement.", "That will show them!",
            "Don't even try to explain this one.", "Doesn't the blood bother you?",
            "Do you ever wonder how would it be living without cruelty?", "The fear, can you smell it?",
            "So many foes, so little time.", "You're killing it!", "You absolutely slayed it!", "Massacre away!",
            "Taking lives, what a nice pastime!", "Nighty-night, sleep tight!", "This was fun. Anyone else?",
            "Rest in pieces.", "R.I.P. in peace.", "Sorry, not sorry.", "Killing is so exhausting, don't you think?!"};

    public static final String[] TERMINATOR_POOL_1_1 = {"Target", "Threat", "Life form", "Life", "Object", "Creature",
            "Third party", "Individual"};
    public static final String[] TERMINATOR_POOL_1_2 = {"Eliminated", "Liquidated", "Irradicated", "Exterminated",
            "Terminated", "Annihilated", "Destroyed", "Obliterated", "Liquidated", "Decimated", "Crushed", "Erased",
            "Nullified", "Extirpated", "Killed", "Removed", "Deleted", "Effaced", "Invalidated", "Abrogated", "Expunged",
            "Disconnected"};
    public static final String[] TERMINATOR_POOL_2_1 = {"Mission", "Task", "Process"};
    public static final String[] TERMINATOR_POOL_2_2 = {"Eliminate", "Liquidate", "Irradicate", "Exterminate",
            "Terminate", "Annihilate", "Destroy", "Obliterate", "Liquidate", "Decimate", "Crush", "Erase",
            "Nullify", "Extirpate", "Kill", "Remove", "Delete", "Efface", "Invalidate", "Abrogate", "Expunge",
            "Disconnected"};
    public static final String[] TERMINATOR_POOL_3 = {"Recalibrating after successful termination.", "I'll be back.",
            "Hasta la vista, baby.", "Acquiring new target, plase stand by ...", "This was not Sarah Connor.",
            "Next target: Sarah Connor.", "Life: Revoked.", "Future: Denied.", "Existence: Void.",
            "Resistance is futile.", "No captives.", "Take no prisoners."};
    public static final Material[] FOUNTAIN_OPTIONS = {Material.BONE, Material.ROTTEN_FLESH, Material.SPIDER_EYE,
            Material.BEEF, Material.COOKIE, Material.REDSTONE, Material.LEATHER};
}