package io.github.millmc.core.impl.util;



import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class GradleRunner {
    private File workingDirectory;
    private boolean isWindows;
    public GradleRunner(File workingDirectory) {
        this.workingDirectory = workingDirectory;
        this.isWindows = System.getProperty("os.name").toLowerCase().contains("win"); //TODO not sure how accurate this is.
    }
    public void run(String... args) {
        StringBuilder sb = new StringBuilder();

        try {
            if(isWindows) {
                //TODO test
                sb.append(".\\gradlew.bat");
            } else {
                sb.append("./gradlew");
                new ProcessBuilder("chmod +x ./gradlew".split(" ")).directory(workingDirectory).redirectOutput(ProcessBuilder.Redirect.INHERIT).start().waitFor();
            }
            for(String arg : args) {
                sb.append(" ").append(arg);
            }
            new ProcessBuilder(sb.toString().split(" ")).directory(workingDirectory).redirectOutput(ProcessBuilder.Redirect.INHERIT).start().waitFor();

        } catch(IOException ex) {
            throw new IOError(ex);
        } catch(InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }
}
