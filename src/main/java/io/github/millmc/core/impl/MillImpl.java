package io.github.millmc.core.impl;

import io.github.millmc.core.api.Mill;
import io.github.millmc.core.api.buckets.Bucket;
import io.github.millmc.core.api.pkgs.InstallablePackage;
import io.github.millmc.core.api.pkgs.Package;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.patchworkmc.logging.Logger;

// not final so that others can add features etc. while not reinventing the wheel
public class MillImpl implements Mill {
    public static final Logger LOGGER = new Logger("MineAUR");

    /**
     * Internal use only!
     */
    public MillImpl() {
        //
    }
//    @Override
//    public Package parseModBuild(File modBuild) {
//        FileConfig conf = FileConfig.of(modBuild, TomlFormat.instance());
//        conf.load();
//        String slug = conf.get("mod.slug");
//        ModLoader modLoader = ModLoader.valueOf(conf.get("mod.modloader").toString().toUpperCase());
//        PackageType type = PackageType.valueOf(conf.get("mod.type").toString().toUpperCase());
//        byte[] expectedChecksum;
//        try {
//            expectedChecksum = Hex.decodeHex(conf.get("mod.downloadHash").toString());
//        } catch(DecoderException ex) {
//            throw new IllegalArgumentException(ex);
//        }
//        switch(type) {
//            case SOURCE:
//
//            return new DownloadedGradlePackageImpl(this, slug, modLoader, conf.get("mod.downloadUrl"), expectedChecksum);
//            case BINARY:
//                return new DownloadedBinaryPackageImpl(this, slug, modLoader, conf.get("mod.downloadUrl"), expectedChecksum);
//            case META:
//
//            default:
//                return new PackageImpl(this, slug, modLoader);
//        }
//    }

	@Override
	public List<Bucket> getBuckets() {
		return null;
	}

	@Override
	public List<Package> resolvePackage(String slug) {
		return getBuckets().stream()
			.map(bucket -> bucket.getPackage(slug))
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}

	@Override
    public void installPackage(Package... pkgs) {
        for (Package pkg : pkgs) {
            if(!(pkg instanceof InstallablePackage))
                throw new UnsupportedOperationException("Unsupported package implementation " + pkg.getClass().getName());
        }
        List<InstallablePackage> packageList = Arrays.asList(Arrays.copyOf(pkgs, pkgs.length, InstallablePackage[].class));

        packageList.forEach(pkg -> pkg.download(getWorkingDirectory(pkg)));
        packageList.forEach(pkg -> pkg.build(getWorkingDirectory(pkg)));
        packageList.forEach(pkg -> pkg.install(getWorkingDirectory(pkg), createDirsIfNotExists("mods"),
                createDirsIfNotExists("config")));
    }

    private File getWorkingDirectory(Package pkg) {
        File workingDirectory = new File("work/" + pkg.getSlug());
        if(!workingDirectory.exists()) workingDirectory.mkdirs();
        return workingDirectory;
    }

    private File createDirsIfNotExists(String path) {
        File file = new File(path);
        if(!file.exists()) file.mkdirs();
        return file;
    }

    public static void main(String[] args) {
        Mill mill = Mill.INSTANCE;
//        Package pkg = mill.parseModBuild(new File("MODBUILD"));
//        mill.installPackage(pkg);
    }
}
