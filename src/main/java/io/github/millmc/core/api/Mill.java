package io.github.millmc.core.api;

import io.github.millmc.core.api.buckets.Bucket;
import io.github.millmc.core.api.pkgs.Package;
import io.github.millmc.core.impl.MillImpl;

import java.util.Collection;
import java.util.List;

// eventually there may be some sort of provider system
public interface Mill {
    Mill INSTANCE = new MillImpl();
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
	List<Bucket> getBuckets();
    /**
     * Find a pacakge in the database by its slug
     */
    List<Package> resolvePackage(String slug);

    //Package parseModBuild(File modBuild);
    void installPackage(Package... pkg);
}
