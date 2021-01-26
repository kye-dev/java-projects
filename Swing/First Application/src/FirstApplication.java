import java.util.HashMap;
import java.util.Map;

public class FirstApplication {
    public static void main(String[] args) {
        Map<String, Name> nameMap = new HashMap<>();

        //nameMap.put(, "Hello");

        // myMap.put(names[i], name);
        // myMap.put("Mark", name);

        for (int i = 0; i < numberofnames; i++) {
            String nameKey = names[i];
            if (nameMap.containsKey(nameKey)) {
                System.out.println(nameKey + "'s age is " + nameMap.get(nameKey));
            }
        }
    }

    static class Name {
        private final String name;

        public Name(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}