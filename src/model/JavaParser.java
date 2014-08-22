package model;

import de.gulden.util.javasource.Package;
import de.gulden.util.javasource.ProgressTracker;
import de.gulden.util.javasource.SourceParser;
import de.gulden.util.javasource.jjt.ParseException;

import java.io.File;
import java.io.IOException;

/**
 * User: Martin Gutierrez
 * Date: 27/06/12
 * Time: 12:03
 */
public class JavaParser extends SourceParser {
    private ProgressTracker tracker = new ProgressTracker() {
        public void todo(int i) {}
        public void done(int i) {}
    };

    public Package read(File javaClass) throws IOException, ParseException, NoClassDefFoundError {
        return parse(javaClass, tracker);
    }
}