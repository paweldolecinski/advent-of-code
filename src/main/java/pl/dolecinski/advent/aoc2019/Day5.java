package pl.dolecinski.advent.aoc2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Day5 {

    public static final int[] INTCODE = {3, 225, 1, 225, 6, 6, 1100, 1, 238, 225, 104, 0, 1102, 45, 16, 225, 2, 65, 191, 224, 1001, 224, -3172, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 5, 224, 1, 223, 224, 223, 1102, 90, 55, 225, 101, 77, 143, 224, 101, -127, 224, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 7, 224, 1, 223, 224, 223, 1102, 52, 6, 225, 1101, 65, 90, 225, 1102, 75, 58, 225, 1102, 53, 17, 224, 1001, 224, -901, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 3, 224, 1, 224, 223, 223, 1002, 69, 79, 224, 1001, 224, -5135, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 5, 224, 1, 224, 223, 223, 102, 48, 40, 224, 1001, 224, -2640, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 1, 224, 1, 224, 223, 223, 1101, 50, 22, 225, 1001, 218, 29, 224, 101, -119, 224, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 2, 224, 1, 223, 224, 223, 1101, 48, 19, 224, 1001, 224, -67, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 6, 224, 1, 223, 224, 223, 1101, 61, 77, 225, 1, 13, 74, 224, 1001, 224, -103, 224, 4, 224, 1002, 223, 8, 223, 101, 3, 224, 224, 1, 224, 223, 223, 1102, 28, 90, 225, 4, 223, 99, 0, 0, 0, 677, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1105, 0, 99999, 1105, 227, 247, 1105, 1, 99999, 1005, 227, 99999, 1005, 0, 256, 1105, 1, 99999, 1106, 227, 99999, 1106, 0, 265, 1105, 1, 99999, 1006, 0, 99999, 1006, 227, 274, 1105, 1, 99999, 1105, 1, 280, 1105, 1, 99999, 1, 225, 225, 225, 1101, 294, 0, 0, 105, 1, 0, 1105, 1, 99999, 1106, 0, 300, 1105, 1, 99999, 1, 225, 225, 225, 1101, 314, 0, 0, 106, 0, 0, 1105, 1, 99999, 7, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 329, 1001, 223, 1, 223, 8, 226, 677, 224, 1002, 223, 2, 223, 1005, 224, 344, 101, 1, 223, 223, 8, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 359, 101, 1, 223, 223, 1008, 677, 226, 224, 1002, 223, 2, 223, 1005, 224, 374, 1001, 223, 1, 223, 108, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 389, 1001, 223, 1, 223, 1107, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 404, 101, 1, 223, 223, 1008, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 419, 1001, 223, 1, 223, 7, 677, 226, 224, 1002, 223, 2, 223, 1005, 224, 434, 101, 1, 223, 223, 1108, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 449, 101, 1, 223, 223, 7, 226, 226, 224, 102, 2, 223, 223, 1005, 224, 464, 101, 1, 223, 223, 108, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 479, 1001, 223, 1, 223, 1007, 677, 226, 224, 1002, 223, 2, 223, 1006, 224, 494, 1001, 223, 1, 223, 1007, 677, 677, 224, 1002, 223, 2, 223, 1006, 224, 509, 1001, 223, 1, 223, 107, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 524, 101, 1, 223, 223, 1108, 226, 677, 224, 102, 2, 223, 223, 1006, 224, 539, 1001, 223, 1, 223, 8, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 554, 101, 1, 223, 223, 1007, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 569, 1001, 223, 1, 223, 107, 677, 226, 224, 102, 2, 223, 223, 1005, 224, 584, 1001, 223, 1, 223, 108, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 599, 1001, 223, 1, 223, 107, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 614, 1001, 223, 1, 223, 1108, 677, 226, 224, 1002, 223, 2, 223, 1005, 224, 629, 1001, 223, 1, 223, 1107, 677, 677, 224, 102, 2, 223, 223, 1005, 224, 644, 1001, 223, 1, 223, 1008, 677, 677, 224, 102, 2, 223, 223, 1005, 224, 659, 101, 1, 223, 223, 1107, 677, 226, 224, 1002, 223, 2, 223, 1006, 224, 674, 101, 1, 223, 223, 4, 223, 99, 226};

    public static void main(String[] args) {
//        step1();
        step2();
    }

    private static void step1() {
        int[] intcode = Arrays.copyOf(INTCODE, INTCODE.length);

        int input = 1;
        LinkedList<Integer> results = runIntcode(intcode, input);

        System.out.println(results.getLast());
    }

    private static void step2() {
        int[] intcode = Arrays.copyOf(INTCODE, INTCODE.length);

        int input = 5;
        LinkedList<Integer> results = runIntcode(intcode, input);

        System.out.println(results.getLast());
    }

    private static LinkedList<Integer> runIntcode(int[] intcode, int input) {
        LinkedList<Integer> results = new LinkedList<>();
        int i = 0;
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
                intcode[instruction.params.get(0).value] = input;
            } else if (opcode == 4) {
                int output = intcode[instruction.params.get(0).value];
                if (output != 0) results.add(output);
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
        return results;
    }

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
        position, immediate
    }
}
