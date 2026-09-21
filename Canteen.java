public class Canteen {

    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {

        // Higher trust score comes first
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        // Tie-break using canteen code, ignoring case
        int codeComparison = this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (codeComparison != 0) {
            return codeComparison;
        }

        // If codes are the same ignoring case, shorter name comes first
        return Integer.compare(this.canteenName.length(), other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {

        // Manual selection sort
        for (int i = 0; i < canteens.length - 1; i++) {

            int bestIndex = i;

            for (int j = i + 1; j < canteens.length; j++) {

                if (canteens[j].compareTo(canteens[bestIndex]) < 0) {
                    bestIndex = j;
                }
            }

            Canteen temp = canteens[i];
            canteens[i] = canteens[bestIndex];
            canteens[bestIndex] = temp;
        }

        return canteens;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    public static void main(String[] args) {

        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(canteen.getCanteenCode());
        }
    }
}