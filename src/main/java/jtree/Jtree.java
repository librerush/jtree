package jtree;

public class Jtree {
    public static void usage() {
        System.out.println("jtree [path]");
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            usage();
            return;
        }

        DirTreeBuilder d = new DirTreeBuilder(args[0]);
        d.printTree();
    }
}
