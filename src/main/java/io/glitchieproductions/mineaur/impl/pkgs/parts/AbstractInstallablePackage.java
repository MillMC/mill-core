package io.glitchieproductions.mineaur.impl.pkgs.parts;

import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.api.pkgs.InstallablePackage;
import io.glitchieproductions.mineaur.impl.MineAurImpl;
import io.glitchieproductions.mineaur.impl.pkgs.PackageImpl;

public abstract class AbstractInstallablePackage extends PackageImpl implements InstallablePackage {
    private String downloadUrl;
    private byte[] expectedChecksum;
    public AbstractInstallablePackage(MineAurImpl mineAur, String slug, ModLoader loader, String downloadUrl, byte[] expectedChecksum) {
        super(mineAur, slug, loader);
        this.downloadUrl = downloadUrl;
        this.expectedChecksum = expectedChecksum;
    }

    @Override
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @Override
    public byte[] getExpectedChecksum() {
        return expectedChecksum;
    }
}
