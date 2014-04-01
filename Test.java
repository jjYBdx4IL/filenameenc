// vim:set shiftwidth=4 tabstop=4 expandtab ai smartindent fileformat=unix fileencoding=utf-8:

/**
 * Beware! You need to make sure that your specified encodings actually exist
 * on your specific system!
 * <pre>
 * $ mkdir a
 * $ javac -encoding UTF-8 Test.java
 * $ LC_ALL=de_DE.ISO-8859-15@euro java Test create
 * create, charset in use: ISO-8859-15
 * $ LC_ALL=de_DE.UTF-8 java Test check
 * check, charset in use: UTF-8
 * NOT FOUND: /home/mark/mysvn/devel/java/evaluation/�
 * 
 *   ^--- !!!!!!!
 * 
 * $ LC_ALL=de_DE.UTF-8 ls -l a     # v-- ISO terminal
 * insgesamt 0
 * -rw-rw-r-- 1 mark mark 0 Apr  2 00:36 ???
 * $ LC_ALL=de_DE.ISO-8859-15@euro ls -l a   # v-- ISO terminal
 * insgesamt 0
 * -rw-rw-r-- 1 mark mark 0 Apr  2 00:36 üöä
 * </pre>
 * <a href="http://stackoverflow.com/questions/22775758/java-io-file-accessing-files-with-invalid-filename-encodings">http://stackoverflow.com/questions/22775758/java-io-file-accessing-files-with-invalid-filename-encodings</a>
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        String encUsed = Charset.defaultCharset().displayName();
        System.out.println(args[0] + ", charset in use: " + encUsed);

        if (args[0].equals("create")) {
            if (encUsed.indexOf("ISO") == -1) {
                throw new RuntimeException();
            }
            try {
                File f = new File("a/üöä");
                f.delete();
                f.createNewFile();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            if (encUsed.indexOf("UTF") == -1) {
                throw new RuntimeException();
            }
            for (File f : new File("a").listFiles()) {
                if (!f.exists()) {
                    System.out.println("NOT FOUND: " + f.getPath());
                } else {
                    System.out.println("found:     " + f.getPath());
                }
            }
        }
    }
}
