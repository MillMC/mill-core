package io.github.millmc.core.impl.pkgs;

import io.github.millmc.core.api.ModLoader;
import io.github.millmc.core.impl.MillImpl;
import io.github.millmc.core.impl.pkgs.parts.AbstractInstallablePackage;
import io.github.millmc.core.impl.pkgs.parts.DownloadedPackageImpl;
import io.github.millmc.core.impl.pkgs.parts.GradlePackageImpl;

public class DownloadedGradlePackageImpl extends AbstractInstallablePackage implements DownloadedPackageImpl, GradlePackageImpl {
    public DownloadedGradlePackageImpl(MillImpl mineAur, String slug, ModLoader loader, String downloadUrl, byte[] expectedChecksum) {
        super(mineAur, slug, loader, downloadUrl, expectedChecksum);
    }
}
