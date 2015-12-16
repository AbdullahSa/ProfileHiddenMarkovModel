package com.abdullah;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by asus on 6.12.2015.
 */
public interface FileReaderAndHandler {

    public static final String fileName="dna.txt";

    void readFile() throws FileNotFoundException;

    void collectCharacters();

    void findProbabilty();


}
