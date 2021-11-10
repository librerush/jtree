package jtree;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirTreeTest {
    private static String dir;
    private static final String tmpDir = System.getProperty("java.io.tmpdir").concat("/dir_tree_test");
    private static final Path tmpDirPath = Path.of(tmpDir);
    private static final File file1 = new File(tmpDir.concat("/1.txt"));

    @BeforeAll
    static void createDirs() throws IOException {
        if (new File(tmpDir).exists()) {
            dir = tmpDir;
        } else {
            dir = Files.createDirectory(tmpDirPath).toFile().getAbsolutePath();
        }
        file1.createNewFile();
    }

    @AfterAll
    static void cleanUpDirs() {
        file1.deleteOnExit();
        tmpDirPath.toFile().deleteOnExit();
    }

    @Test
    public void testName() {
        DirTreeBuilder dirTreeBuilder = new DirTreeBuilder(dir);
        DirTree dirTree = dirTreeBuilder.getDirTree();

        Assertions.assertEquals(tmpDir, dirTree.getName());
    }

    @Test
    public void shouldContainFile() {
        DirTreeBuilder dirTreeBuilder = new DirTreeBuilder(dir);
        DirTree dirTree = dirTreeBuilder.getDirTree();

        Assertions.assertTrue(dirTree.getDirTreeList().contains(new DirTree("1.txt")));
    }
}
