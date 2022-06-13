package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayOne {


    private List<Integer> getDepthMeasurement() {
        List<Integer> depthMeasurement = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input/input_day_1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                depthMeasurement.add(Integer.parseInt(line));
            }
            return depthMeasurement;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depthMeasurement;
    }


    public int checkMeasurementsBigger() {
        List<Integer> depthMeasurements = getDepthMeasurement();
        int size = depthMeasurements.size();
        int biggerThenPrevious = 0;
        for (int i = 0; i < size; i++) {

            if ((i != size - 1) && (depthMeasurements.get(i) < depthMeasurements.get(i + 1)))
                biggerThenPrevious++;

        }


        return biggerThenPrevious;
    }

    public int threePartDepthMeasurement() {
        List<Integer> depthMeasurements = getDepthMeasurement();
        int size = depthMeasurements.size();
        int biggerThenPrevious = 0;

        int previous = 0;
        for (int i = 0; i < size; i++) {
            int sum = 0;
            switch (i) {
                case 0:
                    sum += depthMeasurements.get(0);
                    break;
                case 1:
                    sum += depthMeasurements.get(0);
                    sum += depthMeasurements.get(1);
                    break;
                default:
                    if (i == size - 1) {
                        sum += depthMeasurements.get(i);
                    } else if (i == size - 2) {
                        sum += depthMeasurements.get(i - 1);
                        sum += depthMeasurements.get(i);
                    } else {
                        sum += depthMeasurements.get(i - 2);
                        sum += depthMeasurements.get(i - 1);
                        sum += depthMeasurements.get(i);
                    }
                    break;
            }
            if (sum > previous) {
                biggerThenPrevious++;
            }
            previous = sum;
        }
        return biggerThenPrevious - 1; //-1 first number is bigger than 0, but it doesnt count
    }

}
