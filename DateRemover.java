import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DateRemover {
    public static void main(String[] args) {
        final File path = new File("C:\\Users\\Pierre\\Downloads");
        final String pattern = "_";

        int filesRenamed = 0;

        for (File f : path.listFiles((_, name) -> name.matches("\\d{4}-\\d{2}-\\d{2}.*"))) {
            String oldName = f.getName();
            int stop = oldName.indexOf(pattern);
            if (stop == -1) {
                System.out.println("no match found in '" + oldName + "'");
                continue;
            }
            String newName = oldName.substring(0, stop) + ".pdf";

            Path oldFile = f.toPath();
            try {
                Files.move(oldFile, oldFile.resolveSibling(newName));
                System.out.println("successfully renamed '" + oldName + "' to '" +
                newName + "'");
                filesRenamed++;
            } catch (IOException e) {
                System.out.println("error when trying to rename '" + oldName + "'");
            }
        }

        if (filesRenamed == 0) {
            System.out.println("no files found and renamed");
            return;
        }
        System.out.println(filesRenamed + " files renamed");
    }
}
