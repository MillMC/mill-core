package io.glitchieproductions.mineaur.impl.pkgs;

import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.impl.MineAurImpl;
import io.glitchieproductions.mineaur.impl.pkgs.parts.AbstractInstallablePackage;
import io.glitchieproductions.mineaur.impl.pkgs.parts.DownloadedPackageImpl;
import io.glitchieproductions.mineaur.impl.pkgs.parts.GradlePackageImpl;

public class DownloadedGradlePackageImpl extends AbstractInstallablePackage implements DownloadedPackageImpl, GradlePackageImpl {
    public DownloadedGradlePackageImpl(MineAurImpl mineAur, String slug, ModLoader loader, String downloadUrl, byte[] expectedChecksum) {
        super(mineAur, slug, loader, downloadUrl, expectedChecksum);
    }
}
