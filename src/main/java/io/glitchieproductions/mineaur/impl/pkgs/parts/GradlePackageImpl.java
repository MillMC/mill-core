package io.glitchieproductions.mineaur.impl.pkgs.parts;

import io.glitchieproductions.mineaur.api.ModLoader;
import io.glitchieproductions.mineaur.api.pkgs.InstallablePackage;
import io.glitchieproductions.mineaur.impl.util.GradleRunner;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;

import static io.glitchieproductions.mineaur.impl.MineAurImpl.LOGGER;

public interface GradlePackageImpl extends InstallablePackage {
    @Override
    default void build(File workingDirectory) {
        LOGGER.info("building " + getSlug());
        //TODO version checking + loader checking
        GradleRunner runner = new GradleRunner(workingDirectory);
        runner.run("remapJar");
        LOGGER.info("done");
    }

    @Override
    default void install(File workingDirectory, File modFolder, File configFolder) {
        LOGGER.info("installing " + getSlug());
        File match = null;
        for(File file : FileUtils.listFiles(new File(workingDirectory.getPath() + "/build/libs/"), null, false)) {
            if(getModLoader() == ModLoader.FABRIC) {
                if(file.getName().matches(".*(?<!-dev.jar)$")) {
                    match = file;
                    break;
                }
            }
        }

        try {
            assert match != null;
            FileUtils.copyFile(match, new File(modFolder, getSlug() + ".jar"));
        } catch(IOException ex) {
            throw new IOError(ex);
        }
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
