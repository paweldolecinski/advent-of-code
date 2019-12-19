package pl.dolecinski.advent.aoc2019;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day11 {

    public static final long[] INTCODE = {3, 8, 1005, 8, 291, 1106, 0, 11, 0, 0, 0, 104, 1, 104, 0, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 108, 0, 8, 10, 4, 10, 1002, 8, 1, 28, 1, 1003, 20, 10, 2, 1103, 19, 10, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1001, 8, 0, 59, 1, 1004, 3, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 0, 8, 10, 4, 10, 1001, 8, 0, 84, 1006, 0, 3, 1, 1102, 12, 10, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 101, 0, 8, 114, 3, 8, 1002, 8, -1, 10, 101, 1, 10, 10, 4, 10, 108, 1, 8, 10, 4, 10, 101, 0, 8, 135, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 102, 1, 8, 158, 2, 9, 9, 10, 2, 2, 10, 10, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 1008, 8, 1, 10, 4, 10, 101, 0, 8, 188, 1006, 0, 56, 3, 8, 1002, 8, -1, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1001, 8, 0, 212, 1006, 0, 76, 2, 1005, 8, 10, 3, 8, 102, -1, 8, 10, 1001, 10, 1, 10, 4, 10, 108, 1, 8, 10, 4, 10, 1001, 8, 0, 241, 3, 8, 102, -1, 8, 10, 101, 1, 10, 10, 4, 10, 1008, 8, 0, 10, 4, 10, 1002, 8, 1, 264, 1006, 0, 95, 1, 1001, 12, 10, 101, 1, 9, 9, 1007, 9, 933, 10, 1005, 10, 15, 99, 109, 613, 104, 0, 104, 1, 21102, 83848420648L, 1, 1, 21102, 1, 308, 0, 1106, 0, 412, 21102, 1, 937267929116L, 1, 21101, 0, 319, 0, 1105, 1, 412, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 1, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 1, 21102, 206312598619L, 1, 1, 21102, 366, 1, 0, 1105, 1, 412, 21101, 179410332867L, 0, 1, 21102, 377, 1, 0, 1105, 1, 412, 3, 10, 104, 0, 104, 0, 3, 10, 104, 0, 104, 0, 21101, 0, 709580595968L, 1, 21102, 1, 400, 0, 1106, 0, 412, 21102, 868389384552L, 1, 1, 21101, 411, 0, 0, 1106, 0, 412, 99, 109, 2, 21202, -1, 1, 1, 21102, 1, 40, 2, 21102, 1, 443, 3, 21101, 0, 433, 0, 1106, 0, 476, 109, -2, 2105, 1, 0, 0, 1, 0, 0, 1, 109, 2, 3, 10, 204, -1, 1001, 438, 439, 454, 4, 0, 1001, 438, 1, 438, 108, 4, 438, 10, 1006, 10, 470, 1102, 0, 1, 438, 109, -2, 2106, 0, 0, 0, 109, 4, 1202, -1, 1, 475, 1207, -3, 0, 10, 1006, 10, 493, 21102, 0, 1, -3, 21202, -3, 1, 1, 21201, -2, 0, 2, 21101, 0, 1, 3, 21102, 1, 512, 0, 1106, 0, 517, 109, -4, 2105, 1, 0, 109, 5, 1207, -3, 1, 10, 1006, 10, 540, 2207, -4, -2, 10, 1006, 10, 540, 22101, 0, -4, -4, 1106, 0, 608, 21201, -4, 0, 1, 21201, -3, -1, 2, 21202, -2, 2, 3, 21101, 0, 559, 0, 1106, 0, 517, 21201, 1, 0, -4, 21102, 1, 1, -1, 2207, -4, -2, 10, 1006, 10, 578, 21101, 0, 0, -1, 22202, -2, -1, -2, 2107, 0, -3, 10, 1006, 10, 600, 21201, -1, 0, 1, 21102, 600, 1, 0, 106, 0, 475, 21202, -2, -1, -2, 22201, -4, -2, -4, 109, -5, 2106, 0, 0};

    public static void main(String[] args) {
        step1();
//        step2();
    }

    private static void step1() {

        IntcodeComputer intcodeComputer = new IntcodeComputer(INTCODE);
        int input = 1;

        HashMap<Point, PanelPosition> panels = new HashMap<>();

        Point point00 = new Point(0, 0);
        PanelPosition currentPanel = new PanelPosition(1, point00);

        panels.put(point00, currentPanel);

        Signal signal = new Signal(BigInteger.ZERO, NextStep.CONTINUE);
        int minX = 0, maxX = 0, minY = 0, maxY = 0;

        List<Integer> outputs = new ArrayList<>();
        while (signal.nextStep != NextStep.HALT) {
            signal = intcodeComputer.run(currentPanel.color);
            outputs.add(signal.value.intValue());
            if (outputs.size() == 2) {
                int color = outputs.get(0);
                int turn = outputs.get(1);
                outputs.clear();
                if (currentPanel.color != color) {
                    currentPanel.color = color;
                    currentPanel.paintCounter++;
                }

                if (currentPanel.point.x < minX) minX = currentPanel.point.x;
                if (currentPanel.point.x > maxX) maxX = currentPanel.point.x;
                if (currentPanel.point.y < minY) minY = currentPanel.point.y;
                if (currentPanel.point.y > maxY) maxY = currentPanel.point.y;

                NextPoint nextPoint = currentPanel.nextPoint(turn);
                PanelPosition panel = panels.computeIfAbsent(nextPoint.point, p -> new PanelPosition(0, p));
                panel.direction = nextPoint.direction;
                currentPanel = panel;

            }
        }


        //part1
        long count = panels.values().stream().filter(p -> p.color == 1).count();
        System.out.println(count);

        //part2
        char[][] grid = new char[Math.abs(minY) + Math.abs(maxY) + 1][Math.abs(minX) + Math.abs(maxX) + 1];

        int shiftY = Math.abs(maxY);
        int shiftX = Math.abs(minX);
        panels.values().forEach(panel -> {
            if (panel.color == 1) {
                int y = Math.abs(panel.point.y + shiftY);
                int x = panel.point.x + shiftX;
                grid[y][x] = '#';
            }
        });

        for (char[] line : grid) {
            for (char c : line) {
                if (c == '#') {
                    System.out.print("$");
                }
                else System.out.print(" ");
            }
            System.out.println();
        }

//UERPRFGJ
    }


    static class PanelPosition {
        int color;
        Point point;
        Direction direction = Direction.UP;
        int paintCounter;

        public PanelPosition(int color, Point point) {
            this.color = color;
            this.point = point;
        }

        public NextPoint nextPoint(int turn) {
            if (direction == Direction.UP) {
                if (turn == 0) {
                    direction = Direction.LEFT;
                    return new NextPoint(new Point(point.x - 1, point.y), Direction.LEFT);
                } else {
                    direction = Direction.RIGTH;
                    return new NextPoint(new Point(point.x + 1, point.y), Direction.RIGTH);
                }

            } else if (direction == Direction.DOWN) {
                if (turn == 0) {
                    direction = Direction.RIGTH;
                    return new NextPoint(new Point(point.x + 1, point.y), Direction.RIGTH);
                } else {
                    direction = Direction.LEFT;
                    return new NextPoint(new Point(point.x - 1, point.y), Direction.LEFT);
                }
            } else if (direction == Direction.LEFT) {
                if (turn == 0) {
                    direction = Direction.DOWN;
                    return new NextPoint(new Point(point.x, point.y - 1), Direction.DOWN);
                } else {
                    direction = Direction.UP;
                    return new NextPoint(new Point(point.x, point.y + 1), Direction.UP);
                }
            } else if (direction == Direction.RIGTH) {
                if (turn == 0) {
                    direction = Direction.UP;
                    return new NextPoint(new Point(point.x, point.y + 1), Direction.UP);
                } else {
                    direction = Direction.DOWN;
                    return new NextPoint(new Point(point.x, point.y - 1), Direction.DOWN);
                }
            }
            return null;
        }
    }

    static class NextPoint {
        Point point;
        Direction direction;

        public NextPoint(Point point, Direction direction) {

            this.point = point;
            this.direction = direction;
        }
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    enum Direction {UP, DOWN, LEFT, RIGTH}

    static class IntcodeComputer {

        private List<BigInteger> intcode;
        private int nextPosition;
        private BigInteger lastOutput;
        private int relativeBaseOffset;
        private List<BigInteger> output = new ArrayList<>();

        IntcodeComputer(long[] intcode) {
            this.intcode = Arrays.stream(intcode).mapToObj(BigInteger::valueOf).collect(Collectors.toList());
        }

        Signal run(int input) {
            int i = nextPosition;
            while (i < intcode.size()) {
                Instruction instruction = getInstruction(i);
                int opcode = instruction.opcode;

                if (opcode == 99) {
                    break;
                } else if (opcode == 1) {
                    intcode.set(getPosition(instruction.params.get(2)), getValue(instruction.params.get(0)).add(getValue(instruction.params.get(1))));
                } else if (opcode == 2) {
                    intcode.set(getPosition(instruction.params.get(2)), getValue(instruction.params.get(0)).multiply(getValue(instruction.params.get(1))));
                } else if (opcode == 3) {
                    intcode.set(getPosition(instruction.params.get(0)), BigInteger.valueOf(input));
                } else if (opcode == 4) {
                    lastOutput = getValue(instruction.params.get(0));
                    this.nextPosition = i + instruction.params.size() + 1;
                    return new Signal(lastOutput, NextStep.CONTINUE);

                } else if (opcode == 5) {
                    int jumpIfTrue = getValue(instruction.params.get(0)).intValue();
                    if (jumpIfTrue != 0) {
                        i = getValue(instruction.params.get(1)).intValue();
                        continue;
                    }
                } else if (opcode == 6) {
                    int jumpIfFalse = getValue(instruction.params.get(0)).intValue();
                    if (jumpIfFalse == 0) {
                        i = getValue(instruction.params.get(1)).intValue();
                        continue;
                    }
                } else if (opcode == 7) {
                    BigInteger value1 = getValue(instruction.params.get(0));
                    BigInteger value2 = getValue(instruction.params.get(1));
                    intcode.set(getPosition(instruction.params.get(2)), value1.compareTo(value2) < 0 ? BigInteger.ONE : BigInteger.ZERO);
                } else if (opcode == 8) {
                    BigInteger value1 = getValue(instruction.params.get(0));
                    BigInteger value2 = getValue(instruction.params.get(1));
                    intcode.set(getPosition(instruction.params.get(2)), value1.equals(value2) ? BigInteger.ONE : BigInteger.ZERO);
                } else if (opcode == 9) {
                    // adjusts the relative base
                    relativeBaseOffset += getValue(instruction.params.get(0)).intValue();
                }
                i += instruction.params.size() + 1;
            }
            return new Signal(lastOutput, NextStep.HALT);

        }

        private BigInteger getValue(Param param) {
            if (param.mode == ParamMode.immediate)
                return param.value;
            int position = getPosition(param);
            return intcode.get(position);
        }

        private int getPosition(Param param) {
            int position = param.mode == ParamMode.relative ? relativeBaseOffset + param.value.intValue() : param.value.intValue();
            while (position >= intcode.size()) {
                intcode.add(BigInteger.ZERO);
            }
            return position;
        }

        private Instruction getInstruction(int i) {
            int value = intcode.get(i).intValue();
            Instruction instruction = new Instruction();


            int pointer = -1;
            while (value > 0) {
                int digit = value % 10;
                if (pointer == -1) {
                    instruction.opcode = digit;
                } else if (pointer == 0) {
                    instruction.opcode += digit * 10;
                } else {
                    Param param = new Param();
                    param.value = intcode.get(i + pointer);
                    switch (digit) {
                        case 0:
                            param.mode = ParamMode.position;
                            break;
                        case 1:
                            param.mode = ParamMode.immediate;
                            break;
                        case 2:
                            param.mode = ParamMode.relative;
                            break;
                    }
                    instruction.params.add(param);
                }
                value /= 10;
                pointer++;
            }
            if (pointer == 0) pointer++;

            if ((instruction.opcode == 1 || instruction.opcode == 2 || instruction.opcode == 7 || instruction.opcode == 8))
                while (instruction.params.size() < 3) {
                    Param param = new Param();
                    param.value = intcode.get(i + pointer);
                    instruction.params.add(param);
                    pointer++;

                }

            if ((instruction.opcode == 3 || instruction.opcode == 4 || instruction.opcode == 9))
                while (instruction.params.size() < 1) {
                    Param param = new Param();
                    param.value = intcode.get(i + pointer);
                    instruction.params.add(param);
                    pointer++;
                }

            if ((instruction.opcode == 5 || instruction.opcode == 6))
                while (instruction.params.size() < 2) {
                    Param param = new Param();
                    param.value = intcode.get(i + pointer);
                    instruction.params.add(param);
                    pointer++;
                }

            return instruction;
        }

    }

    static class Signal {

        BigInteger value;

        NextStep nextStep;

        Signal(BigInteger value, NextStep nextStep) {
            this.value = value;
            this.nextStep = nextStep;
        }

    }

    enum NextStep {CONTINUE, HALT;}


    static class Instruction {
        int opcode;
        List<Param> params = new ArrayList<>();
    }

    static class Param {
        BigInteger value;
        ParamMode mode = ParamMode.position;
    }

    private enum ParamMode {
        position, immediate, relative,
    }

    public static Collection<List<Integer>> generatePermutationsNoRepetition(Set<Integer> availableNumbers) {
        Collection<List<Integer>> permutations = new HashSet<>();

        for (Integer number : availableNumbers) {
            Set<Integer> numbers = new HashSet<>(availableNumbers);
            numbers.remove(number);

            if (!numbers.isEmpty()) {
                Collection<List<Integer>> childPermutations = generatePermutationsNoRepetition(numbers);
                for (List<Integer> childPermutation : childPermutations) {
                    List<Integer> permutation = new ArrayList<>();
                    permutation.add(number);
                    permutation.addAll(childPermutation);
                    permutations.add(permutation);
                }
            } else {
                List<Integer> permutation = new ArrayList<>();
                permutation.add(number);
                permutations.add(permutation);
            }
        }

        return permutations;
    }
}
