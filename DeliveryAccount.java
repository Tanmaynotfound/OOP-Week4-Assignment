public class DeliveryAccount {

    private String studentId;
    private double orderValue;

    private static int processedCount;

    // One-time class-level initialization
    static {
        processedCount = 0;
    }

    // Full constructor
    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    // Provisional constructor
    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    // Reused from Problem 4
    public final double calculateSurgeFee(int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException(
                "Order value and delay minutes cannot be negative."
            );
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double surgeFee = 0.0;

        // Minutes 1-5: 0.5% per minute
        int firstTier = Math.min(delayMinutes, 5);
        surgeFee += firstTier * orderValue * 0.005;

        // Minutes 6-15: 1% per minute
        if (delayMinutes > 5) {
            int secondTier = Math.min(delayMinutes - 5, 10);
            surgeFee += secondTier * orderValue * 0.01;
        }

        // Minute 16 onwards: 2% per minute
        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;
            surgeFee += thirdTier * orderValue * 0.02;
        }

        return surgeFee;
    }

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            return;
        }

        double surgeFee = account.calculateSurgeFee(delayMinutes);

        if (account instanceof PremiumAccount) {
            surgeFee = surgeFee * 0.50;
            System.out.println(
                account.studentId + " (Premium): Surge fee = Rs " + surgeFee
            );
        } else {
            System.out.println(
                account.studentId + " (Regular): Surge fee = Rs " + surgeFee
            );
        }

        processedCount++;
    }

    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        // Use the shortest array length.
        int length = Math.min(
            accounts.length,
            Math.min(amounts.length, delayMinutesArray.length)
        );

        for (int i = 0; i < length; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double surgeFee = account.calculateSurgeFee(delayMinutesArray[i]);

            if (account instanceof PremiumAccount) {
                surgeFee = surgeFee * 0.50;
                premium++;
            } else {
                regular++;
            }

            grandTotal += surgeFee;
            processed++;
        }

        System.out.println();
        System.out.println(processed + " processed | "
                + nullSkipped + " null skipped | "
                + premium + " premium | "
                + regular + " regular | "
                + "grand total surge fees = Rs " + grandTotal);
    }

    public static void main(String[] args) {

        DeliveryAccount regular =
                new DeliveryAccount("STU002", 300);

        PremiumAccount premium =
                new PremiumAccount("STU001", 500);

        DeliveryAccount[] accounts = {
            premium,
            null,
            regular
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delayMinutesArray = {
            10,
            5,
            0
        };

        processBatch(accounts, amounts, delayMinutesArray);
    }
}


// Premium account
class PremiumAccount extends DeliveryAccount {

    public PremiumAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumAccount(String studentId) {
        super(studentId);
    }
}