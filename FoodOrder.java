public class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    // Parameterized constructor only
    public FoodOrder(String studentName, String dishName) {

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    public void markDelivered() {

        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered successfully.");
        } else {
            System.out.println("Warning: Order already delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {

            try {
                FoodOrder foodOrder = new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {

        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Ravi", "Pizza");

        order.markDelivered();
        order.markDelivered();
    }
}