package pl.dolecinski.advent.aoc2019;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day12 {

    public static class MotionSimluator {

        private List<Moon> moons = new ArrayList<>();

        public MotionSimluator(List<List<Integer>> positions) {
            positions.forEach(coordinates -> moons.add(new Moon(coordinates.get(0), coordinates.get(1), coordinates.get(2))));
        }

        public void run(int steps) {
            for (int value = 0; value < steps; value++) {
                doTick();
            }
        }

        public long run() {
            int steps = 0;
            int xCycleInterval = -1, yCycleInterval = -1, zCycleInterval = -1;

            List<Integer> initialXPosList = getValuesFor(m -> m.position.x);
            List<Integer> initialXVelocityList = getValuesFor(m -> m.velocity.x);

            List<Integer> initialYPosList = getValuesFor(m -> m.position.y);
            List<Integer> initialYVelocityList = getValuesFor(m -> m.velocity.y);

            List<Integer> initialZPosList = getValuesFor(m -> m.position.z);
            List<Integer> initialZVelocityList = getValuesFor(m -> m.velocity.z);

            while (true) {
                steps++;
                doTick();

                if (xCycleInterval == -1)
                    xCycleInterval = findCycle(initialXPosList, initialXVelocityList, getValuesFor(m -> m.position.x), getValuesFor(m -> m.velocity.x), steps);
                if (yCycleInterval == -1)
                    yCycleInterval = findCycle(initialYPosList, initialYVelocityList, getValuesFor(m -> m.position.y), getValuesFor(m -> m.velocity.y), steps);
                if (zCycleInterval == -1)
                    zCycleInterval = findCycle(initialZPosList, initialZVelocityList, getValuesFor(m -> m.position.z), getValuesFor(m -> m.velocity.z), steps);

                if (xCycleInterval != -1 && yCycleInterval != -1 && zCycleInterval != -1) {
                    break;
                }
            }
            return commonCycle(xCycleInterval, yCycleInterval, zCycleInterval);
        }

        private long commonCycle(int x, int y, int z) {
            BigInteger xyLcm = lcm(BigInteger.valueOf(x), BigInteger.valueOf(y));
            return lcm(xyLcm, BigInteger.valueOf(z)).longValue();
        }

        private BigInteger lcm(BigInteger number1, BigInteger number2) {
            BigInteger gcd = number1.gcd(number2);
            BigInteger absProduct = number1.multiply(number2).abs();
            return absProduct.divide(gcd);
        }

        private int findCycle(List<Integer> initialPosList, List<Integer> initialVelocityList, List<Integer> currentPosList, List<Integer> currentVelocityList, int steps) {
            if (initialPosList.equals(currentPosList) && initialVelocityList.equals(currentVelocityList))
                return steps;
            return -1;
        }

        private List<Integer> getValuesFor(Function<Moon, Integer> getXPosition) {
            return moons.stream().map(getXPosition).collect(Collectors.toUnmodifiableList());
        }

        private void doTick() {
            for (int i = 0; i < moons.size(); i++) {
                for (int j = i + 1; j < moons.size(); j++) {
                    moons.get(i).applyGravity(moons.get(j));
                }
                moons.get(i).applyVelocity();
            }
        }


        public int getTotalEnergy() {
            return moons.stream().mapToInt(Moon::getTotalEnergy).sum();
        }

        private void accept(int i) {
            doTick();
        }
    }

    private static class Moon {
        Position position;
        Velocity velocity;

        public Moon(int x, int y, int z) {
            position = new Position(x, y, z);
            velocity = new Velocity(0, 0, 0);
        }

        public int getTotalEnergy() {

            return getPotentialEnergy() * getKineticEnergy();
        }

        private int getPotentialEnergy() {
            return Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);
        }

        private int getKineticEnergy() {
            return Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);
        }

        public void applyGravity(Moon other) {
            updateVelocityX(other);
            updateVelocityY(other);
            updateVelocityZ(other);
        }

        private void updateVelocityX(Moon other) {
            if (this.position.x < other.position.x) {
                this.velocity.x++;
                other.velocity.x--;
            } else if (this.position.x > other.position.x) {
                this.velocity.x--;
                other.velocity.x++;
            }
        }

        private void updateVelocityY(Moon other) {
            if (this.position.y < other.position.y) {
                this.velocity.y++;
                other.velocity.y--;
            } else if (this.position.y > other.position.y) {
                this.velocity.y--;
                other.velocity.y++;
            }
        }

        private void updateVelocityZ(Moon other) {
            if (this.position.z < other.position.z) {
                this.velocity.z++;
                other.velocity.z--;
            } else if (this.position.z > other.position.z) {
                this.velocity.z--;
                other.velocity.z++;
            }
        }


        private void applyVelocity() {
            position.x += velocity.x;
            position.y += velocity.y;
            position.z += velocity.z;
        }
    }

    private static class Position {
        int x;
        int y;
        int z;

        public Position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Velocity {
        int x;
        int y;
        int z;

        public Velocity(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
