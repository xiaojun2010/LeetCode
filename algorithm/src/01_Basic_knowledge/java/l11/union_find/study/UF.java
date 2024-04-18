package l11.union_find.study;

public interface UF {

    boolean isConnected(int p,int q);

    void unionElements(int p,int q);

    int getSize();
}
