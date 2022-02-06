package file;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.plexus.util.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.stream.Stream;

@Log4j2
public class FileNameChanger {

    public static final int NUM_OF_RANDOM = 3;

    @Test
    public void test() throws IOException{
        String[] extensions = {"jpg"};
        this.changeFileName("D:\\FOX\\Parent\\N\\Test\\", "IMG_20", "02MTT", 5, false);
    }

    //Insert a number of random chars at index ; Replace String
    private void changeFileName(String inputDir, String fromPattern, String toPattern, int index, boolean isTest) throws IOException{
        String outputDir = inputDir + "output\\";
        if(!Files.isDirectory(Paths.get(outputDir))){
            Files.createDirectory(Paths.get(outputDir));
        }

        Stream<Path> list = Files.list(Paths.get(inputDir));
        list.filter(path -> path.getFileName().toString().contains(fromPattern)).forEach(p -> {
            try {
                String sourceFileName = p.getFileName().toString();
                String targetFileName = StringUtils.replace(sourceFileName, fromPattern, toPattern);
                if(index > 0) {
                    targetFileName = targetFileName.substring(0, index) + RandomStringUtils.randomAlphabetic(NUM_OF_RANDOM)
                            + targetFileName.substring(index);
                }
                log.info(outputDir + targetFileName);
                if(!isTest){
                    Files.copy(p, Paths.get(outputDir + targetFileName), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void reverse(String inputDir, String fromPattern, String toPattern, int index, boolean isTest) throws IOException {
        
    }

}
