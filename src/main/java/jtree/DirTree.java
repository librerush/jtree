package jtree;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/*
* The directory tree structure.
* */
public class DirTree implements Comparable<DirTree> {
    private String name;
    private Set<DirTree> dirTreeList;

    public DirTree() {
        this.name = ".";
        this.dirTreeList = new TreeSet<>();
    }

    public DirTree(String name) {
        this.name = name;
        this.dirTreeList = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DirTree> getDirTreeList() {
        return dirTreeList;
    }

    public void setDirTreeList(Set<DirTree> dirTreeList) {
        this.dirTreeList = dirTreeList;
    }

    @Override
    public int compareTo(DirTree o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirTree dirTree = (DirTree) o;
        return Objects.equals(name, dirTree.name) && Objects.equals(dirTreeList, dirTree.dirTreeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dirTreeList);
    }

    @Override
    public String toString() {
        return "DirTree{" +
                "name='" + name + '\'' +
                ", dirTreeList=" + dirTreeList +
                '}';
    }
}
