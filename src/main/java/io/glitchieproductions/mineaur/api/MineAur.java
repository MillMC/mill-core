package io.glitchieproductions.mineaur.api;

import io.glitchieproductions.mineaur.api.pkgs.Package;
import io.glitchieproductions.mineaur.impl.MineAurImpl;

import java.io.File;
import java.nio.file.Path;

// eventually there may be some sort of provider system
public interface MineAur {
    MineAur INSTANCE = new MineAurImpl();
//    /**
//     * runs <code>git pull</code> on all buckets
//     */
//    void refreshDatabase();
//
//    /**
//     * Finds all packages that have a higher version available than what is installed
//     * @return a list of packages
//     */
//    List<Package> getOutdatedPackages();
//
//    /**
//     * Find a pacakge in the database by its slug
//     */
//    void resolvePackage(String slug);
    Package parseModBuild(File modBuild);
    void installPackage(Package... pkg);
}
