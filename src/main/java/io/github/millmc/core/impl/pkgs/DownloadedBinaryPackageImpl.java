package io.github.millmc.core.impl.pkgs;

import io.github.millmc.core.api.ModLoader;
import io.github.millmc.core.impl.MillImpl;
import io.github.millmc.core.impl.pkgs.parts.AbstractInstallablePackage;
import io.github.millmc.core.impl.pkgs.parts.BinaryPackageImpl;
import io.github.millmc.core.impl.pkgs.parts.DownloadedPackageImpl;

public class DownloadedBinaryPackageImpl extends AbstractInstallablePackage implements DownloadedPackageImpl, BinaryPackageImpl {

    public DownloadedBinaryPackageImpl(MillImpl mineAur, String slug, ModLoader loader, String downloadUrl, byte[] expectedChecksum) {
        super(mineAur, slug, loader, downloadUrl, expectedChecksum);
    }
}
