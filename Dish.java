public class Dish {
    int num;
    String name;
    private double price;
    int count;
    public Dish(int n, String nm, double p) {
        num = n;
        name = nm;
        price = p;
    }
    double GetPrice(){
        return price;
    }
}
