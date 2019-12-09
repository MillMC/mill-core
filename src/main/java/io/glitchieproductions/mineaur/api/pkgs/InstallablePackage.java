package io.glitchieproductions.mineaur.api.pkgs;

import java.io.File;

public interface InstallablePackage extends Package {
    /**
     * Get the required files.
     * This could be a <code>git clone</code> or just the java equiv. to a <code>curl</code>
     */
    void download(File workingDirectory);

    /**
     * Stage everything to be installed.
     * For source, this means actual building.
     * Binaries might need to be unpacked, etc.
     */
    void build(File workingDirectory);

    /**
     * For now this means get the end-result jar and put it somewhere
     */
    void install(File workingDirectory, File modFolder, File configFolder);

    String getDownloadUrl();

    byte[] getExpectedChecksum();
}
