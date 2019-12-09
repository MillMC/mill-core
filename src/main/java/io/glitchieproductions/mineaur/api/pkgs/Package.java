package io.glitchieproductions.mineaur.api.pkgs;

import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.impl.MineAurImpl;
import org.slf4j.Logger;

public interface Package {
    String getSlug();
    ModLoader getModLoader();
    //getAuthor
    //getGameVersion
    default Logger getLogger() {
        return MineAurImpl.LOGGER;
    }
}
