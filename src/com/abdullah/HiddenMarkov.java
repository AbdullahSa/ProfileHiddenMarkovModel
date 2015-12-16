package com.abdullah;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 6.12.2015.
 */
public class HiddenMarkov implements com.abdullah.FileReaderAndHandler {


    ArrayList<Character> differentCharacters=new ArrayList<>();//unigue characters
    StringBuffer sequences; //all of sequences by String
    int lengthOfColumns;
    int lengthOfLines;
    List<String> linesForColumn=new ArrayList<>();

    List<Delete> deleteList=new LinkedList<>();
    List<Insert> insertList=new LinkedList<>();

    List<Match> matchTransactionList=new ArrayList<>();

    int insertNodeSize;
    int deleteNodeSize;

    List<Match> matchList=new LinkedList<>();

    List<Match> protectMatchList=new LinkedList<>();

    char character;

    int counter=0;
    double sumChars=0;
    double score=0;

         public HiddenMarkov(){

             collectCharacters();

             Iterator iterator=differentCharacters.iterator();

             System.out.println("Unique Characters:");
             while (iterator.hasNext()){
                 System.out.println(iterator.next());
             }

             System.out.println("Matching Columns:");
             for (int i=0;i<lengthOfColumns;i++){
             System.out.println("Match"+(i+1)+": "+linesForColumn.get(i));
         }

                findProbabilty();


             System.out.println("\nProtected/Non-Protected");
             int i=0;
             for (Match p:protectMatchList){
                 System.out.println("Match "+(i+1)+" "+p.isProtected());
                 i++;
             }

             //disassembly for matches M1->M2->M3 ...
             System.out.println("\nMatches Probabilities");
             for (Match m:matchList){
                 System.out.println("Match "+m.getMatchNum()+" "+m.getUniqueChar()+": "+m.getProbability()+" "+m.getMatch());
             }

             deleteNodeSize=lengthOfColumns;
             insertNodeSize=lengthOfColumns+1;


             transactions();

             for (Delete d:deleteList){
                 System.out.println("D("+d.getDeleteNum()+"): "+d.getTransactionToDelete());
             }

             for (Match m:matchTransactionList){
                 System.out.println("M(" + m.getTransactionIndex() + "): " + m.getTransaction());
             }

             for (Insert in:insertList){
                 System.out.println("I(" + in.getInsertNum() + "): " + in.getTransactionToInsert());
             }




         }

    //find unique characters in sequences
    @Override
    public void collectCharacters() {

        readFile();

                for(int i=0;i<sequences.length();i++){
                        if(!differentCharacters.contains(sequences.charAt(i))){
                            if(sequences.charAt(i)!='\n')
                            differentCharacters.add(sequences.charAt(i));
                        }
                }
        System.out.println(differentCharacters.size());


        for (int i=0;i<lengthOfColumns;i++){
                 StringBuffer columnSequence=new StringBuffer();
            for (int j=0;j<lengthOfLines;j++){
                String[] a= sequences.toString().split("\n");

               try {

                        columnSequence.append(a[j].charAt(i));


               }catch (Exception e){

               }
            }
            linesForColumn.add(columnSequence.toString());
            System.out.println("\n");


        }


    }

    @Override
    public void findProbabilty() {
        for (int i=0;i<lengthOfColumns;i++){
        for (Character c : differentCharacters) {
                counter=0;
                for (int j = 0; j < lengthOfLines; j++) {
                    if(c==linesForColumn.get(i).charAt(j)){
                        counter++;
                    }
                }

                matchList.add(new Match(i+1,c,counter/(double)lengthOfLines,linesForColumn.get(i)));
                if(c!='-'){
                    sumChars+=counter/(double)lengthOfLines;
                }else {
                    score=counter/(double)lengthOfLines;
                }
            }

            if (score>=sumChars){
                protectMatchList.add(new Match(false));
            }else  protectMatchList.add(new Match(true));

            sumChars=0;


        }
    }


    //read document file
    @Override
    public void readFile() {

        sequences=new StringBuffer();
        FileReader fileReader= null;

        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fileReader);

        String lineContent = null;
        lengthOfLines=0;
        try {
                while ((lineContent = br.readLine()) != null) {
                    sequences.append(lineContent + "\n");
                    lengthOfColumns=lineContent.length();
                    lengthOfLines++;
                }

            }catch(IOException e){
                e.printStackTrace();
            }

       // System.out.println(sequences);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void transactions(){

        System.out.println("\nTransactions");

        int counterDeleteOfProtected=0,counterMatchOfProtected=0,counterInsertOfProtected=0;;
        int[] indexDelete=new int[lengthOfLines];
        int[] indexMatch=new int[lengthOfLines];
        int[] indexInsert=new int[lengthOfLines];

        double[] deleteTransaction=new double[lengthOfColumns];
        double[] matchTransaction=new double[lengthOfColumns];
        double[] insertTransaction=new double[lengthOfColumns];
        for (int i=0;i<lengthOfColumns;i++){
            counterDeleteOfProtected=0;
            counterMatchOfProtected=0;
            counterInsertOfProtected=0;
            if(protectMatchList.get(i).isProtected()){
            for (int j=0;j<lengthOfLines;j++){
                    if (linesForColumn.get(i).charAt(j)=='-'){
                        counterDeleteOfProtected++;
                        indexDelete[j]=j+1;
                    }else {
                        counterMatchOfProtected++;
                        indexMatch[j]=j+1;
                    }
                }
                 deleteTransaction[i]=counterDeleteOfProtected/(double)lengthOfLines;
                 matchTransaction[i]=counterMatchOfProtected/(double)lengthOfLines;
                //System.out.println(" "+counterMatchOfProtected/(double)lengthOfLines+" : "+counterDeleteOfProtected/(double)lengthOfLines);
            }else {
                for (int j=0;j<lengthOfLines;j++){
                if (linesForColumn.get(i).charAt(j)!='-'){
                    counterInsertOfProtected++;
                    indexInsert[j]=j+1;
                }
            }
            insertTransaction[i]=counterInsertOfProtected/(double)lengthOfLines;

        }
        }

            for (int j=0;j<lengthOfLines;j++){
                if(deleteTransaction[j]!=0.0){
                    deleteList.add(new Delete(deleteTransaction[j],indexDelete[j]));
                }
                if(matchTransaction[j]!=0.0){
                        matchTransactionList.add(new Match(matchTransaction[j],indexMatch[j]));
                    }
                if (insertTransaction[j]!=0.0)
                {
                insertList.add(new Insert(insertTransaction[j],indexInsert[j]));
                }
            }

            }


    }


