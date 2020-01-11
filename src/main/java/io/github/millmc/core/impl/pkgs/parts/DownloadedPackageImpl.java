package io.github.millmc.core.impl.pkgs.parts;

import io.github.millmc.core.api.pkgs.InstallablePackage;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface DownloadedPackageImpl extends InstallablePackage {
    @Override
    default void download(File workingDirectory) {
        getLogger().info("downloading");
        String[] urlContents = getDownloadUrl().split("/");
        String outputFileName = urlContents[urlContents.length-1];
        File outputFile = new File(workingDirectory.getPath() + "/download/" + outputFileName);
        try {
            FileUtils.copyURLToFile(new URL(getDownloadUrl()), outputFile);
        } catch(MalformedURLException ex) {
            throw new IllegalArgumentException("Unable to parse URL", ex);
        } catch(IOException ex) {
            throw new IOError(ex);
        }
        getLogger().info("verifying checksum");
        //sonarlint has trouble understanding that this local var is, in fact, used.
        //so we just disable the warning
        boolean digestMatches; //NOSONAR
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(FileUtils.readFileToByteArray(outputFile));
            digestMatches = Arrays.equals(digest, getExpectedChecksum()); //NOSONAR
        } //TODO fail gracefully?
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        } catch (IOException ex) {
            throw new IOError(ex);
        }
        if (!digestMatches) {
            throw new IOError(new IOException("downloaded file does not match expected checksum"
                    + Hex.encodeHexString(getExpectedChecksum())));
        }
        if(outputFile.getName().endsWith(".jar")) {
            try {
                FileUtils.copyFileToDirectory(outputFile, workingDirectory);
            } catch (IOException ex) {
                throw new IOError(ex);
            }
        }
        getLogger().info("extracting files");
        Archiver archiver = ArchiverFactory.createArchiver(outputFile);
        File extractionDirectory = new File(workingDirectory.getPath() + "/extract");
        try {
            archiver.extract(outputFile, extractionDirectory);
        } catch (IOException e) {
            throw new IOError(e);
        }

        List<File> files = new ArrayList<>(FileUtils.listFilesAndDirs(extractionDirectory,
                DirectoryFileFilter.DIRECTORY, DirectoryFileFilter.DIRECTORY));
        try {
            FileUtils.copyDirectory(files.get(1), workingDirectory);
        } catch (IOException ex) {
            throw new IOError(ex);
        }

    }

}
