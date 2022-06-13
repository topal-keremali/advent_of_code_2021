package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DayThree {

    public int calculateRate() {
        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_3.txt"))) {
            String bits;

            int[] positive = new int[12];
            int[] negative = new int[12];

            while ((bits = objReader.readLine()) != null) {

                int i = 0;
                for (char ch : bits.toCharArray()) {
                    if (ch == '1') {
                        positive[i]++;
                    } else if (ch == '0') {
                        negative[i]++;
                    }
                    i++;
                }
            }

            StringBuilder gammaRate = new StringBuilder();
            StringBuilder epsilonRate = new StringBuilder();
            for (int i = 0; i < positive.length; i++) {
                if (positive[i] > negative[i]) {
                    gammaRate.append('1');
                    epsilonRate.append('0');
                } else {
                    gammaRate.append('0');
                    epsilonRate.append('1');
                }
            }
            int gamma = Integer.parseInt(gammaRate.toString(), 2);
            int epsilon = Integer.parseInt(epsilonRate.toString(), 2);

            return gamma * epsilon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int calculateRating(int[] positive, int[] negative, Map<String, String> list, boolean isCoTwo) {
        char first = '1';
        char second = '0';
        if (isCoTwo) {
            first = '0';
            second = '1';
        }

        char firstBit = first;
        char secondBit = second;

        int k = 0;
        int j = 0;
        while (list.size() != 1) {
            for (String bitString : list.values()) {
                char ch = bitString.charAt(j);
                if (ch == '1') {
                    positive[j]++;
                } else if (ch == '0') {
                    negative[j]++;
                }
            }
            j++;

            int c = k;
            if (positive[k] > negative[k]) {
                list.values().removeIf(value -> value.charAt(c) != firstBit);
            } else if (positive[k] < negative[k]) {
                list.values().removeIf(value -> value.charAt(c) != secondBit);
            } else if (positive[k] == negative[k]) {
                list.values().removeIf(value -> value.charAt(c) != firstBit);
            }
            k++;
        }
        Optional<String> firstKey = list.keySet().stream().findFirst();


        return firstKey.map(s -> Integer.parseInt(s, 2)).orElse(0);
    }

    public int calculateLifeSupport() {
        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_3.txt"))) {
            String bits;

            Map<String, String> oxygenList = new HashMap<>();
            Map<String, String> cotwoList = new HashMap<>();


            while ((bits = objReader.readLine()) != null) {
                oxygenList.put(bits, bits);
                cotwoList.put(bits, bits);
            }

            int oxygenInt = this.calculateRating(new int[12], new int[12], oxygenList, false);

            int cotwoInt = this.calculateRating(new int[12], new int[12], cotwoList, true);

            System.out.println(oxygenInt + " " + cotwoInt);
            return oxygenInt * cotwoInt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
