package io.glitchieproductions.mineaur.impl.pkgs;

import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.api.pkgs.Package;
import io.glitchieproductions.mineaur.impl.MineAurImpl;

public class PackageImpl implements Package {
    protected MineAurImpl mineAur;
    private String slug;
    private ModLoader loader;
    public PackageImpl(MineAurImpl mineAur, String slug, ModLoader loader)  {
        this.mineAur = mineAur;
        this.slug = slug;
        this.loader = loader;
        try {
        } catch(Exception ex) {
            throw new IllegalArgumentException(ex);
        }

    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public ModLoader getModLoader() {
        return loader;
    }




}
