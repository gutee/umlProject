package java;

import de.gulden.util.javasource.Package;
import de.gulden.util.javasource.ProgressTracker;
import de.gulden.util.javasource.SourceParser;
import de.gulden.util.javasource.jjt.ParseException;

import java.io.File;
import java.io.IOException;

public class JavaParser
    extends SourceParser
{

    ProgressTracker tracker = new ProgressTracker() {
        public void todo(int i) {
//            System.out.println(i);
        }

        public void done(int i) {
//            System.out.println(i + "%");
        }
    };

    Package read(File javaClass)
            throws IOException, ParseException
    {
        return parse(javaClass, tracker);
    }

}
