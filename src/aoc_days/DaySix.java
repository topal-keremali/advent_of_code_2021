package aoc_days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DaySix {

    public int countFishDaySlow(int days) {
        FishList fishList = new FishList();
        fishList.setFish(this.getIntiialFishList());
        int counter = 0;
        int counterNewFish = 0;
        while (counter < days) {
            for (ListIterator<Integer> iter = fishList.getFish().listIterator(); iter.hasNext(); ) {
                int element = iter.next();
                if (element == 0) {
                    iter.set(6);
                    counterNewFish++;
                } else {
                    iter.set(element - 1);
                }
            }
            for (int i = 0; i < counterNewFish; i++) {
                fishList.getFish().add(8);
            }
            counterNewFish = 0;
            fishList.setFish(fishList.getFish());
            counter++;
        }
        return fishList.getFish().size();
    }

    public Long countFishDayFast(int days) {
        FishList fishList = new FishList();
        fishList.setFishMap(this.getIntiialFishMap());
        int counter = 0;
        while (counter < days) {
            Map<Integer, Long> copy = new HashMap<>();
            for (int key : fishList.getFishMap().keySet()) {
                if (key == 0) {
                    copy.put(6, copy.getOrDefault(key - 1, 0L) + fishList.getFishMap().get(key));
                    copy.put(8, fishList.getFishMap().get(key));
                } else {
                    copy.put(key - 1, copy.getOrDefault(key - 1, 0L) + fishList.getFishMap().get(key));
                }
            }
            fishList.setFishMap(copy);
            counter++;
        }
        Long sum = 0L;
        for (int fishCount : fishList.getFishMap().keySet()) {
            sum += fishList.getFishMap().get(fishCount);
        }
        return sum;
    }

    private List<Integer> getIntiialFishList() {
        String line;
        List<Integer> initialFishList = new ArrayList<>();
        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_6.txt"))) {
            while ((line = objReader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] fishes = line.split(",");

                    for (String fish : fishes) {
                        initialFishList.add(Integer.parseInt(fish));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return initialFishList;
    }

    private Map<Integer, Long> getIntiialFishMap() {
        String line;
        Map<Integer, Long> initialFishList = new HashMap<>();
        try (BufferedReader objReader = new BufferedReader(new FileReader("./src/input/input_day_6.txt"))) {
            while ((line = objReader.readLine()) != null) {
                if (line.length() > 0) {
                    String[] fishes = line.split(",");

                    for (String fish : fishes) {
                        initialFishList.put(Integer.parseInt(fish), initialFishList.getOrDefault(Integer.parseInt(fish), 0L) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return initialFishList;
    }
}

class FishList {
    private List<Integer> fish;
    private Map<Integer, Long> fishMap;

    public Map<Integer, Long> getFishMap() {
        return fishMap;
    }

    public void setFishMap(Map<Integer, Long> fishMap) {
        this.fishMap = fishMap;
    }

    public List<Integer> getFish() {
        return fish;
    }

    public void setFish(List<Integer> fish) {
        this.fish = fish;
    }
}
