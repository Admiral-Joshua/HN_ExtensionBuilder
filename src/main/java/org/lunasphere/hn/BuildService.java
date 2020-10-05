package org.lunasphere.hn;

public class BuildService {
    public static void main(String[] args) {
        SQLConfig sql = new SQLConfig("localhost", 5432, "EXTENSIONS_DB", "postgres", "postgres");

        ChangeDetector detector = new ChangeDetector(sql);
        detector.start();
    }
}
