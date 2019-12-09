package io.glitchieproductions.mineaur.impl.pkgs;

import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.impl.MineAurImpl;
import io.glitchieproductions.mineaur.impl.pkgs.parts.AbstractInstallablePackage;
import io.glitchieproductions.mineaur.impl.pkgs.parts.BinaryPackageImpl;
import io.glitchieproductions.mineaur.impl.pkgs.parts.DownloadedPackageImpl;

public class DownloadedBinaryPackageImpl extends AbstractInstallablePackage implements DownloadedPackageImpl, BinaryPackageImpl {

    public DownloadedBinaryPackageImpl(MineAurImpl mineAur, String slug, ModLoader loader, String downloadUrl, byte[] expectedChecksum) {
        super(mineAur, slug, loader, downloadUrl, expectedChecksum);
    }
}
