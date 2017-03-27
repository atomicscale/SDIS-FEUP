package FileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Files {

    public static boolean fileExists (String path){
        File f = new File(path);
        if (f.exists() && !f.isDirectory()){
            return true;
        }
        return false;
    }

    public byte[] readFile(String path) {
        File file = new File(path);
        byte[] data = new byte[64000];
        // TODO import value;

        try {
            if (fileExists(path)) {
                FileInputStream is = new FileInputStream(file);

                is.read(data);

                is.close();

                return data;
                }
            } catch(FileNotFoundException fnfE){
                // file not found, handle case
            } catch(IOException ioE){
                // problem reading, handle case
            }

            return null;

        }

}