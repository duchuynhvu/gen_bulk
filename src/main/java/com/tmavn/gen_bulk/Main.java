package com.tmavn.gen_bulk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        addRecord();
    }

    static void addRecord() throws ParseException {
        BufferedWriter bufferedWriter = null;
        int j = 1;
        int id = 1;
        while (j <= 20) {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter("jsonb" + j + ".sql"));

                int i = 1;
                int n = 500000;
                System.out.println(j);
                while (i <= n) {
                    String data = id
                            + "\t[{\"key\": \"hostname\", \"value\": \"google\"}, {\"key\": \"status\", \"value\": \"Held\"}, {\"key\": \"port\", \"value\": \"8080\"}]\t2019-02-01 15:16:15\t2019-02-01 15:16:15\t[{\"key\":\"hostname\",\"value\":\"tma\"},{\"key\":\"status\",\"value\":\"Running\"},{\"key\":\"port\",\"value\":\"80\"}]\t[\"eventMatchInfo 1\", \"eventMatchInfo 2\", \"eventMatchInfo 3\"]\t[\"eventOriginalInfoList 1\", \"eventOriginalInfoList 2\", \"eventOriginalInfoList 3\"]\t2019-02-01 15:16:15\tABC\tABC\teventSubType1\teventSubType2\teventSubType3\tABC\t[{\"key\": \"hostname\", \"value\": \"facebook\"}, {\"key\": \"status\", \"value\": \"Pending\"}, {\"key\": \"port\", \"value\": \"8888\"}]\n";

                    bufferedWriter.write(data);
                    i++;
                    id++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            j++;
        }
    }

}
