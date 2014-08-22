package gui.fileChoosers;

import java.io.File;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class Extensions {

    public static String getExtension(File f) {
        String fileName = f.getName();
        String fileExtension = "";
        int nameLength = fileName.length();
        int lastDot = 0; // character position where the extension begins
        for (int i = nameLength - 1; i > 0; i--) {
            if (fileName.charAt(i) == '.') {
                lastDot = i;
                break;
            }
        }
        for (int i = lastDot; i < nameLength; i++) {
            fileExtension += fileName.charAt(i);
        }
        return fileExtension.toLowerCase();
    }
}
