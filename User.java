import java.util.ArrayList;
import java.util.Scanner;

public class User implements Function {
    public String name;
    private String password;
    private double money;
    User(String nm, String pw, double m) {
        name = nm;
        password = pw;
        money = m;
    }
    User() {
        name = null;
        password = null;
        money = 0;
    }
    String GetPassword() {
        return password;
    }
    double GetMoney() {
        return money;
    }
    void SetMoney(char p, double d) {
        if (p == '+') money += d;
        else if (p == '-') money -= d;
        else System.out.println("Wrong order!");
    }
    @Override
    public void ShowCmd() {
        System.out.println("Command\t\tContent");
        System.out.println("1\t\t\tShow the menu");
        System.out.println("2\t\t\tOrder the dishes");
        System.out.println("3\t\t\tCheak your dishes");
        System.out.println("4\t\t\tSettlement");
        System.out.println("5\t\t\tCheck account balance");
        System.out.println("6\t\t\tSet your password");
        System.out.println("7\t\t\tExit");
    }
    @Override
    public void SetPassword() {
        System.out.println("Enter the old password:");
        Scanner in= new Scanner(System.in);
        if(in.nextLine().equals(password)){
            System.out.println("Enter the new password:");
            password = in.nextLine();
        }
        else System.out.println("#Wrong password!");
    }
    @Override
    public void ChooseCmd(ArrayList<Dish> dishes, ArrayList<Dish> order, User now_user, Admin ad,ArrayList<User>users) {
        int cmd = 0;
        while (cmd != 7) {
            System.out.println("——————————————————————————————");
            System.out.print("Enter the command:");
            Scanner in = new Scanner(System.in);
            cmd = in.nextInt();
            switch (cmd) {
                case 1:
                    Main.ShowList(dishes);
                    break;
                case 2:
                    Main.Order(dishes, order);
                    break;
                case 3:
                    Main.CheckOut(order);
                    break;
                case 4:
                    try {
                        Main.Settle(dishes, order, now_user);
                    } catch (illegalltemException e1) {
                        System.out.println("Sorry,but you are short ￥" + e1.GetAmount());
                        e1.printStackTrace();
                        System.out.println("Enter “add” to add money to your account\nEnter “exit” to exit the program");
                        String scmd;
                        Scanner sin = new Scanner(System.in);
                        scmd = sin.next();
                        if (scmd.equals("add")) {
                            System.out.print("Add ￥");
                            int add = sin.nextInt();
                            now_user.SetMoney('+', add);
                        } else if (scmd.equals("exit"))
                            Main.Exit();
                    } catch (illegalcmdException e) {
                        System.out.println("#“" + e.GetCmd() + "” is illegal");
                        System.out.println("#Please enter again");
                    }
                    break;
                case 5:
                    System.out.println("Your account balance:￥" + now_user.GetMoney());
                    break;
                case 6:
                    now_user.SetPassword();break;
                case 7:
                    Main.Exit();break;
                default:
                    System.out.println("Wrong order!");
                    break;
            }

        }
    }
}



