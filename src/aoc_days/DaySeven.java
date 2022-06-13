package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaySeven {


    public long solve() {
        List<Integer> crabs = getCrabs();

        int max = 0;
        for (int crab : crabs) {
            if (max < crab) {
                max = crab;
            }
        }

        long lest = Integer.MAX_VALUE;

        for (int i = 0; i < max; i++) {
            long sum = 0;
            for (int crab : crabs) {
                long dist = Math.abs(i - crab);
                sum += (dist * (dist*1))/2L;
            }
            if (lest > sum){
                lest = sum;
            }
        }
        return lest;
    }

    private List<Integer> getCrabs() {
        List<Integer> crabsList = new ArrayList<>();
        String line;

        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/7.txt"))) {
            while ((line = objReader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] crabs = line.split(",");

                    for (String crab : crabs) {
                        crabsList.add(Integer.parseInt(crab));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return crabsList;
    }

}
