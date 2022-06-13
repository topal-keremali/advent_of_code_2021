package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayTwo {

    public int calculatePosition() {

        int x = 0;
        int y = 0;
        int aim = 0;

        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_2.txt"))) {
            String command;

            while ((command = objReader.readLine()) != null) {

                int steps = Integer.parseInt(command.substring(command.length() - 1));

                if (command.contains("forward")) {
                    x = x + steps;
                    y = y + (aim * steps);
                } else if (command.contains("down")) {
                    aim = aim + steps;
                } else if (command.contains("up")) {
                    aim = aim - steps;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return x * y;
    }


}
