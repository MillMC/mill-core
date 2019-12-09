package io.glitchieproductions.mineaur.impl;

import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import io.glitchieproductions.mineaur.api.MineAur;
import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.api.pkgs.InstallablePackage;
import io.glitchieproductions.mineaur.api.pkgs.Package;
import io.glitchieproductions.mineaur.api.pkgs.PackageType;
import io.glitchieproductions.mineaur.impl.pkgs.DownloadedBinaryPackageImpl;
import io.glitchieproductions.mineaur.impl.pkgs.PackageImpl;
import io.glitchieproductions.mineaur.impl.pkgs.DownloadedGradlePackageImpl;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;


// not final so that others can add features etc. while not reinventing the wheel
public class MineAurImpl implements MineAur {
    public static final Logger LOGGER = LoggerFactory.getLogger("MineAUR");

    /**
     * Internal use only!
     */
    public MineAurImpl() {
        //
    }
    @Override
    public Package parseModBuild(File modBuild) {
        FileConfig conf = FileConfig.of(modBuild, TomlFormat.instance());
        conf.load();
        String slug = conf.get("mod.slug");
        ModLoader modLoader = ModLoader.valueOf(conf.get("mod.modloader").toString().toUpperCase());
        PackageType type = PackageType.valueOf(conf.get("mod.type").toString().toUpperCase());
        byte[] expectedChecksum;
        try {
            expectedChecksum = Hex.decodeHex(conf.get("mod.downloadHash").toString());
        } catch(DecoderException ex) {
            throw new IllegalArgumentException(ex);
        }
        switch(type) {
            case SOURCE:

            return new DownloadedGradlePackageImpl(this, slug, modLoader, conf.get("mod.downloadUrl"), expectedChecksum);
            case BINARY:
                return new DownloadedBinaryPackageImpl(this, slug, modLoader, conf.get("mod.downloadUrl"), expectedChecksum);
            case META:

            default:
                return new PackageImpl(this, slug, modLoader);
        }
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
        MineAur mineAur = MineAur.INSTANCE;
        Package pkg = mineAur.parseModBuild(new File("MODBUILD"));
        mineAur.installPackage(pkg);
    }
}
