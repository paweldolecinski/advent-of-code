package pl.dolecinski.advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 {

    public static final int[] INTCODE = {3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 34, 43, 64, 85, 98, 179, 260, 341, 422, 99999, 3, 9, 1001, 9, 3, 9, 102, 3, 9, 9, 4, 9, 99, 3, 9, 102, 5, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 1002, 9, 4, 9, 1001, 9, 3, 9, 1002, 9, 4, 9, 4, 9, 99, 3, 9, 1001, 9, 3, 9, 102, 3, 9, 9, 101, 4, 9, 9, 102, 3, 9, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 1002, 9, 3, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99};

    public static void main(String[] args) {
//        step1();
        step2();
    }

    private static void step1() {
        Collection<List<Integer>> phaseSettings = generatePermutationsNoRepetition(Set.of(0, 1, 2, 3, 4));

        int maxThrusterSignal = phaseSettings.stream()
            .map(Day7::calcThrusterSignal)
            .mapToInt(i -> i)
            .max().orElseThrow();

        System.out.println(maxThrusterSignal);
    }

    private static void step2() {
        Collection<List<Integer>> phaseSettings = generatePermutationsNoRepetition(Set.of(5, 6, 7, 8, 9));

        int maxThrusterSignal = phaseSettings.stream()
            .map(Day7::calcThrusterSignalLoop)
            .mapToInt(i -> i)
            .max().orElseThrow();

        System.out.println(maxThrusterSignal);
    }

    static int calcThrusterSignalLoop(List<Integer> phaseSetting) {
        ArrayList<Amplifier> amplifiers = new ArrayList<>();
        for (int phase : phaseSetting) {
            amplifiers.add(new Amplifier(INTCODE, phase));
        }
        int nextAmp = 0;
        Signal signal = new Signal(0, NextStep.CONTINUE);
        int haltCounter = 0;
        while (haltCounter < amplifiers.size()) {
            signal = amplifiers.get(nextAmp).runAmplifier(signal.value);
            nextAmp = (nextAmp + 1) % amplifiers.size();
            if (signal.nextStep == NextStep.HALT) haltCounter++;
        }
        return signal.value;
    }

    static int calcThrusterSignal(List<Integer> phaseSetting) {
//        phaseSetting = List.of(0,1,2,3,4);
        int input = 0;

//        for (int phase : phaseSetting) {
//            input = runAmplifier(phase, input, false).result;
//        }
        return input;
    }

    static class Amplifier {

        private int[] intcode;
        private int phase;
        private boolean needPhase = true;
        private int nextPosition;
        private int lastOutput;

        Amplifier(int[] intcode, int phase) {
            this.intcode = Arrays.copyOf(intcode, intcode.length);
            this.phase = phase;
        }

        Signal runAmplifier(int input) {
            int i = nextPosition;
            while (i < intcode.length) {
                Instruction instruction = getInstruction(intcode, i);
                int opcode = instruction.opcode;

                if (opcode == 99) {
                    break;
                } else if (opcode == 1) {
                    intcode[instruction.params.get(2).value] = getValue(intcode, instruction.params.get(0)) + getValue(intcode, instruction.params.get(1));
                } else if (opcode == 2) {
                    intcode[instruction.params.get(2).value] = getValue(intcode, instruction.params.get(0)) * getValue(intcode, instruction.params.get(1));
                } else if (opcode == 3) {
                    if (needPhase) {
                        needPhase = false;
                        intcode[instruction.params.get(0).value] = phase;
                    } else {
                        intcode[instruction.params.get(0).value] = input;
                    }
                } else if (opcode == 4) {
                    lastOutput = intcode[instruction.params.get(0).value];
                    this.nextPosition = i + instruction.params.size() + 1;
                    return new Signal(lastOutput, NextStep.CONTINUE);
                } else if (opcode == 5) {
                    int jumpIfTrue = getValue(intcode, instruction.params.get(0));
                    if (jumpIfTrue != 0) {
                        i = getValue(intcode, instruction.params.get(1));
                        continue;
                    }
                } else if (opcode == 6) {
                    int jumpIfFalse = getValue(intcode, instruction.params.get(0));
                    if (jumpIfFalse == 0) {
                        i = getValue(intcode, instruction.params.get(1));
                        continue;
                    }
                } else if (opcode == 7) {
                    int value1 = getValue(intcode, instruction.params.get(0));
                    int value2 = getValue(intcode, instruction.params.get(1));
                    intcode[instruction.params.get(2).value] = value1 < value2 ? 1 : 0;
                } else if (opcode == 8) {
                    int value1 = getValue(intcode, instruction.params.get(0));
                    int value2 = getValue(intcode, instruction.params.get(1));
                    intcode[instruction.params.get(2).value] = value1 == value2 ? 1 : 0;
                }
                i += instruction.params.size() + 1;
            }
            return new Signal(lastOutput, NextStep.HALT);

        }

    }

    static class Signal {
        int value;
        NextStep nextStep;

        Signal(int value, NextStep nextStep) {
            this.value = value;
            this.nextStep = nextStep;
        }
    }

    enum NextStep {CONTINUE, HALT;}

    private static int getValue(int[] intcode, Param param) {
        if (param.mode == ParamMode.position)
            return intcode[param.value];
        return param.value;
    }

    private static Instruction getInstruction(int[] intcode, int i) {
        int value = intcode[i];
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
                param.value = intcode[i + pointer];
                param.mode = digit == 0 ? ParamMode.position : ParamMode.immediate;
                instruction.params.add(param);
            }
            value /= 10;
            pointer++;
        }
        if (pointer == 0) pointer++;

        if ((instruction.opcode == 1 || instruction.opcode == 2 || instruction.opcode == 7 || instruction.opcode == 8))
            while (instruction.params.size() < 3) {
                Param param = new Param();
                param.value = intcode[i + pointer];
                instruction.params.add(param);
                pointer++;

            }

        if ((instruction.opcode == 3 || instruction.opcode == 4))
            while (instruction.params.size() < 1) {
                Param param = new Param();
                param.value = intcode[i + pointer];
                instruction.params.add(param);
                pointer++;
            }

        if ((instruction.opcode == 5 || instruction.opcode == 6))
            while (instruction.params.size() < 2) {
                Param param = new Param();
                param.value = intcode[i + pointer];
                instruction.params.add(param);
                pointer++;
            }

        return instruction;
    }


    static class Instruction {
        int opcode;
        List<Param> params = new ArrayList<>();
    }

    static class Param {
        int value;
        ParamMode mode = ParamMode.position;
    }

    private enum ParamMode {
        position, immediate,
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
