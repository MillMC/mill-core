package io.github.millmc.core.impl.pkgs.parts;

import io.github.millmc.core.api.ModLoader;
import io.github.millmc.core.api.pkgs.InstallablePackage;
import io.github.millmc.core.impl.MillImpl;
import io.github.millmc.core.impl.pkgs.PackageImpl;

public abstract class AbstractInstallablePackage extends PackageImpl implements InstallablePackage {
    private String downloadUrl;
    private byte[] expectedChecksum;
    public AbstractInstallablePackage(MillImpl mineAur, String slug, ModLoader loader, String downloadUrl, byte[] expectedChecksum) {
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
