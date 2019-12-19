package pl.dolecinski.advent.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day14 {

    static class Nanofactory {

        Map<String, List<Chemical>> reactions = new HashMap<>();
        Map<String, Chemical> outputs = new HashMap<>();

        public Nanofactory(List<String> lines) {
            for (String line : lines) {
                String[] split = line.split(" => ");
                Chemical outputChemical = createChemical(split[1]);
                outputs.put(outputChemical.id, outputChemical);
                List<Chemical> inputChemicals = reactions.computeIfAbsent(outputChemical.id, chemical -> new ArrayList<>());
                String[] inputs = split[0].split(",");
                for (String input : inputs) {
                    inputChemicals.add(createChemical(input));
                }
            }

        }

        private Chemical createChemical(String input) {
            String[] s = input.trim().split(" ");
            return new Chemical(s[1], Integer.parseInt(s[0]));
        }

        int fuelCost() {
            int totalCost = 0;
            List<Chemical> fuelInputs = reactions.get("FUEL");
            LinkedList<Chemical> stack = new LinkedList<>(fuelInputs);
            Map<String, Integer> trash = new HashMap<>();

            while (!stack.isEmpty()) {
                Chemical chemical = stack.pop();
                if (chemical.id.equals("ORE")) {
                    totalCost += chemical.quantity;
                    continue;
                }
                int needed = chemical.quantity;

                Integer wasted = trash.put(chemical.id, 0);

                if(wasted != null && needed < wasted){
                    trash.put(chemical.id, wasted-needed);
                    continue;
                }

                if (wasted != null) {
                    needed -= wasted;
                }
                if (needed <= 0) continue;

                Chemical output = outputs.get(chemical.id);
                int toStack = needed / output.quantity;
                if (needed % output.quantity != 0) {
                    toStack += 1;
                    wasted = output.quantity - needed % output.quantity;
                    int finalWasted = wasted;
                    trash.compute(output.id, (k, q) -> q == null ? finalWasted : q + finalWasted);
                }
                for (int i = 0; i < toStack; i++) {
                    stack.addAll(reactions.get(output.id));
                }
            }
            return totalCost;
        }
    }

    static class Chemical {
        final String id;
        final int quantity;

        public Chemical(String id, int quantity) {
            this.id = id;
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Chemical chemical = (Chemical) o;
            return id.equals(chemical.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Chemical{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
        }
    }
}
