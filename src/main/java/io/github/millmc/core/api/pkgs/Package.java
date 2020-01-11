package io.github.millmc.core.api.pkgs;

import io.github.millmc.core.api.ModLoader;
import io.github.millmc.core.impl.MillImpl;

import com.patchworkmc.logging.Logger;

public interface Package {
    String getSlug();
    ModLoader getModLoader();
    //getAuthor
    //getGameVersion
    default Logger getLogger() {
        return MillImpl.LOGGER;
    }
}
