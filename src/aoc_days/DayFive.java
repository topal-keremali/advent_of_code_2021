package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayFive {

    public int checkDangerousZone(int mapSize) {
        List<int[]> coordinateList = getCoordinates();
        int[][] map = new int[mapSize][mapSize];

        List<int[]> points = new ArrayList<>();
        for (int[] line : coordinateList) {
            points.addAll(this.getPointsBetween(line));
        }

        for (int[] point : points) {
            map[point[0]][point[1]]++;
        }

        return this.countTwoOrMore(map,mapSize);
    }

    private int countTwoOrMore(int[][] map,int mapSize) {
        int counter = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] >= 2) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private List<int[]> getVertical(int[] line) {
        List<int[]> points = new ArrayList<>();

        if (line[1] < line[3]) {
            int i = line[3];
            while (i >= line[1]) {
                points.add(new int[]{line[0], i});
                i--;
            }
        } else {
            int i = line[1];
            while (i >= line[3]) {
                points.add(new int[]{line[0], i});
                i--;
            }
        }

        return points;
    }

    private List<int[]> getHorizontal(int[] line) {
        List<int[]> points = new ArrayList<>();

        if (line[0] < line[2]) {
            int i = line[2];
            while (i >= line[0]) {
                points.add(new int[]{i, line[1]});
                i--;
            }
        } else {
            int i = line[0];
            while (i >= line[2]) {
                points.add(new int[]{i, line[1]});
                i--;
            }
        }
        return points;
    }

    private List<int[]> getDiagonalUpDown(int[] line) {
        List<int[]> points = new ArrayList<>();

        if ((line[0] - line[2]) > 0) {
            int x = line[0];
            int y = line[1];

            while (x >= line[2]) {
                points.add(new int[]{x, y});
                x--;
                y--;
            }
        } else {
            int x = line[2];
            int y = line[3];

            while (x >= line[0]) {
                points.add(new int[]{x, y});
                x--;
                y--;
            }
        }

        return points;
    }

    private List<int[]> getDiagonalDownUp(int[] line) {
        List<int[]> points = new ArrayList<>();

        int x = line[2];
        int y = line[3];
        if ((line[0] - line[2]) > 0) {
            while (x <= line[0]) {
                points.add(new int[]{x, y});
                x++;
                y--;
            }
        } else {
            while (x >= line[0]) {
                points.add(new int[]{x, y});
                x--;
                y++;
            }
        }

        return points;
    }

    private List<int[]> getPointsBetween(int[] line) {
        List<int[]> points = new ArrayList<>();

        if (line[1] == line[3]) {
            points.addAll(this.getHorizontal(line));
        } else if (line[0] == line[2]) {
            points.addAll(this.getVertical(line));
        } else if ((line[2] - line[0]) == (line[3] - line[1])) {
            points.addAll(this.getDiagonalUpDown(line));
        } else if ((line[2] - line[0]) == (line[1] - line[3])) {
            points.addAll(this.getDiagonalDownUp(line));
        }
        return points;
    }

    private List<int[]> getCoordinates() {
        String line;
        List<int[]> coordinateList = new ArrayList<>();
        int[] coordinates = new int[4];
        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_5.txt"))) {
            while ((line = objReader.readLine()) != null) {
                if (line.length() > 0) {
                    String positionOne = line.split(" -> ")[0];
                    String positionTwo = line.split(" -> ")[1];

                    coordinates[0] = Integer.parseInt(positionOne.split(",")[0]);
                    coordinates[1] = Integer.parseInt(positionOne.split(",")[1]);
                    coordinates[2] = Integer.parseInt(positionTwo.split(",")[0]);
                    coordinates[3] = Integer.parseInt(positionTwo.split(",")[1]);
                }
                coordinateList.add(coordinates);
                coordinates = new int[4];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coordinateList;
    }

}
