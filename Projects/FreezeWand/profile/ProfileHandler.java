package com.learnspigot.freezewand.profile;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ProfileHandler {

    // Declare a map for storing profiles, player's unique id as key
    private final Map<UUID, Profile> profileMap = new ConcurrentHashMap<>();

    // Public constructor, allow for instantiation
    public ProfileHandler() {

    }

    // Register a new profile into the handler
    @NotNull
    public Profile register(@NotNull final UUID uniqueId, @NotNull final String name) {
        final Profile profile = new Profile(uniqueId, name);
        profileMap.put(uniqueId, profile);
        return profile;
    }

    // Find a potential profile based off of player unique id
    @NotNull
    public Optional<Profile> findByUniqueId(@NotNull final UUID uniqueId) {
        return Optional.of(profileMap.get(uniqueId));
    }

    // Check if a UUID exists in the map
    public boolean exists(@NotNull final UUID uniqueId) {
        return profileMap.containsKey(uniqueId);
    }

}
