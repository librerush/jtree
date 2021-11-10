package jtree;

import java.io.*;

/**
 * Traversing and printing the directory tree
 */
public class DirTreeBuilder {
    private final DirTree dirTree;

    public DirTreeBuilder(String path) {
        this.dirTree = buildTree(path);
    }

    public DirTree getDirTree() {
        return dirTree;
    }

    private void buildHelper(DirTree dirTree, String parent) {
        File file = new File(parent.isEmpty() ?
                dirTree.getName() : parent.concat("/").concat(dirTree.getName()));

        if (!file.canRead() || file.isFile()) {
            return;
        }

        File[] files = file.listFiles();
        if (files == null) return;

        for (File f : files) {
            dirTree.getDirTreeList().add(new DirTree(f.getName()));
        }

        String p = parent.isEmpty() ? dirTree.getName() : parent.concat("/").concat(dirTree.getName());

        for (DirTree d : dirTree.getDirTreeList()) {
            buildHelper(d, p);
        }
    }

    private DirTree buildTree(String path) {
        DirTree dirTree = new DirTree(path);
        buildHelper(dirTree, "");
        return dirTree;
    }

    private static String indentString(String string, char ch, int indent) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < indent; i++) {
            sb.insert(0, ch);
        }
        return sb.toString();
    }

    private void printTreeHelper(DirTree dirTree, Writer writer, int indent, int prev) throws IOException {
        if (dirTree == null) return;

        writer.write(indentString("", ' ', indent - prev - 1));
        writer.write(indentString("", '-', prev));
        writer.write(indent == 0 && prev == 0 ? "" : " ");
        writer.write(dirTree.getName());
        writer.write('\n');
        writer.flush();

        int ind = indent + dirTree.getName().length() + 1;
        for (DirTree dir : dirTree.getDirTreeList()) {
            printTreeHelper(dir, writer, ind, dirTree.getName().length());
        }
    }

    public void printTree(Writer writer) {
        try {
            printTreeHelper(dirTree, writer, 0, 0);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTree() {
        printTree(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
