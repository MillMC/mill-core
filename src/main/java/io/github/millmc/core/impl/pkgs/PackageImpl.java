package io.github.millmc.core.impl.pkgs;

import io.github.millmc.core.api.ModLoader;
import io.github.millmc.core.api.pkgs.Package;
import io.github.millmc.core.impl.MillImpl;

public class PackageImpl implements Package {
    protected MillImpl mineAur;
    private String slug;
    private ModLoader loader;
    public PackageImpl(MillImpl mineAur, String slug, ModLoader loader)  {
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
