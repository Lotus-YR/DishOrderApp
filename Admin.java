import java.util.ArrayList;
import java.util.Scanner;

public class Admin implements Function {
    Admin(String nm, String pw, double m) {
    }
    void ShowUser(ArrayList<User>users){
        for(User e:users)
            System.out.println("name:"+e.name);
    }

    void SetUser(ArrayList<User>users){
        ShowUser(users);
        System.out.println("Enter the user-name you will delete:");
        Scanner in=new Scanner(System.in);
        String delname=in.next();
        if(delname.equals("admin"))
            System.out.println("#You can not delete the administrator");
        else
            for (User e:users) {
                if (delname.equals(e.name)) {
                    users.remove(e);
                    System.out.println("Deleted!");
                    break;
                }
            }
    }
    void ChgMenu(ArrayList<Dish>dishes){
        Main.ShowList(dishes);
        String chgcmd;
        System.out.println();
        System.out.println("Enter delete to delete dish\nEnter add to add dish\nEnter change to change price");
        Scanner in=new Scanner(System.in);
        chgcmd=in.nextLine();
        if(chgcmd.equals("delete")){
            System.out.println("Enter the number of the dish you will delete:");
            int delnum=in.nextInt();
            for (Dish e:dishes){
                if(e.num==delnum){
                    dishes.remove(e);
                    break;
                }
            }
            for(Dish e:dishes){
                if(e.num>delnum)
                    e.num--;
            }
        }
        else if(chgcmd.equals("add")){
            System.out.println("Enter its details(name&price):");
            int num=dishes.size();
            String nname= in.next();
            double nprice= Double.parseDouble(in.next());
            Dish ndish=new Dish(num+1,nname,nprice);
            dishes.add(ndish);
        }
        else if(chgcmd.equals("change")){
            System.out.println("Enter the dish-number and its new price:");
            int chgnum=in.nextInt();
            for (Dish e:dishes){
                if(e.num==chgnum) {
                    double nprice= Double.parseDouble(in.next());
                    e.ChgPrice(nprice);
                }
            }
        }
        else System.out.println("#Wrong commond!");
    }
    @Override
    public void ShowCmd() {
        System.out.println("Command\t\tContent");
        System.out.println("1\t\t\tShow the menu");
        System.out.println("2\t\t\tOrder the dishes");
        System.out.println("3\t\t\tCheak your dishes");
        System.out.println("4\t\t\tSettlement");
        System.out.println("5\t\t\tCheck account balance");
        System.out.println("6\t\t\tSet password");
        System.out.println("7\t\t\tSet menu");
        System.out.println("8\t\t\tSet user");
        System.out.println("9\t\t\tExit");
    }
    @Override
    public void SetPassword(){

    }
    @Override
    public void ChooseCmd(ArrayList<Dish>dishes,ArrayList<Dish>order,User now_user,Admin ad,ArrayList<User>users){
        int cmd = 0;
        while (cmd != 6) {
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
                    ad.SetPassword();break;
                case 7:
                    ad.ChgMenu(dishes);break;
                case 8:
                    ad.SetUser(users);break;
                case 9:
                    Main.Exit();break;
                default:
                    System.out.println("Wrong order!");
                    break;
            }

        }

    }
}
