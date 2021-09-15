package com.learnspigot.freezewand.profile;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Profile {

    // The player's unique id & username
    private final UUID uniqueId;
    private String name;

    // A list of profiles the player currently has frozen, and if they themselves are frozen
    private final List<Profile> frozenList = new ArrayList<>();
    private boolean frozen = false;

    // Constructor takes the player's unique id & username
    public Profile(@NotNull final UUID uniqueId, @NotNull final String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    // Get the player's unique id
    @NotNull
    public UUID getUniqueId() {
        return uniqueId;
    }

    // Get the player's name
    @NotNull
    public String getName() {
        return name;
    }

    // Set player's name
    public void setName(@NotNull final String name) {
        this.name = name;
    }

    // Add a profile to the player's frozen list
    public void addFrozen(@NotNull final Profile profile) {
        frozenList.add(profile);
    }

    // Remove a profile from the player's frozen list
    public void removeFrozen(@NotNull final Profile profile) {
        frozenList.remove(profile);
    }

    // Check if the player themselves is frozen
    public boolean isFrozen() {
        return frozen;
    }

    // Set whether the player themselves is frozen
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    // Send a chat message to the player
    public void sendMessage(@NotNull final String message) {
        Objects.requireNonNull(Bukkit.getPlayer(uniqueId))
                .sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
