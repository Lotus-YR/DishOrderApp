//检测用户金额是否足够
public class illegalltemException extends Exception{
    private double amount;
    public illegalltemException(double amount) {
        this.amount=-amount;
    }

    public double GetAmount(){
        return amount;
    }

}

