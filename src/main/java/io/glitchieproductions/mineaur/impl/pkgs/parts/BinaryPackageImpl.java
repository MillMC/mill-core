package io.glitchieproductions.mineaur.impl.pkgs.parts;

import io.glitchieproductions.mineaur.api.pkgs.InstallablePackage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

import static io.glitchieproductions.mineaur.impl.MineAurImpl.LOGGER;

public interface BinaryPackageImpl extends InstallablePackage {

    @Override
    default void build(File workingDirectory) {
        //pass
    }

    @Override
    default void install(File workingDirectory, File modFolder, File configFolder) {
        getLogger().info("installing files");
        for(File file : FileUtils.listFiles(workingDirectory, null, false)) {
            if(file.getName().endsWith(".jar")) {
                try {
                    FileUtils.copyFile(file, new File(modFolder.getPath() + "/" + getSlug() + ".jar"));
                } catch (IOException ex) {
                    throw new IOError(ex);
                }
            }
        }
        //delete files
        try {
            //TODO leave gradle files and delete everything else
            FileUtils.deleteDirectory(workingDirectory);
        } catch (IOException ex) {
            LOGGER.error("Unable to delete working directory!");
            LOGGER.error(ex.toString());
            LOGGER.error("Future builds of this mod may not work as expected!");
        }
        LOGGER.info("done!");
    }
}
