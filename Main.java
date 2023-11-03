import java.util.ArrayList;
import java.lang.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
     public static void main(String[] args) {
        User now_user=new User();
        Scanner in_user=new Scanner(System.in);
        System.out.print("Enter your user name:");
        String uname=in_user.next();
        System.out.print("Enter your password:");
        String upw=in_user.next();

        int count=0;   //控制再输入密码的次数
        now_user=UserCheck(uname,upw,count);
        System.out.println("———————————WELCOME————————————");

        Dish d1=new Dish(1,"potato",7);
        Dish d2=new Dish(2,"tomato and eggs",5);
        Dish d3=new Dish(3,"beef noodle",11);
        Dish d4=new Dish(4,"rice noodle",12);
        Dish d5=new Dish(5,"hot pot",50);

        ArrayList<Dish>dishes = new ArrayList<>();
        dishes.add(d1);
        dishes.add(d2);
        dishes.add(d3);
        dishes.add(d4);
        dishes.add(d5);
        ArrayList<Dish>order = new ArrayList<>();

        ShowCmd();
        int cmd=0;
        while(cmd!=6) {
            System.out.print("Enter the command:");
            Scanner in = new Scanner(System.in);
            cmd = in.nextInt();

            switch (cmd) {
                case 1: ShowList(dishes);break;
                case 2: Order(dishes, order);break;
                case 3: CheckOut(order);break;
                case 4:
                    try{
                        Settle(dishes, order, now_user);
                    }catch (illegalltemException e){
                        System.out.println("Sorry,but you are short ￥"+e.GetAmount());
                        e.printStackTrace();
                        System.out.println("Enter “add” to add money to your account\nEnter “exit” to exit the program");
                        String scmd;
                        Scanner sin=new Scanner(System.in);
                        scmd=sin.next();
                        if(scmd.equals("add")){
                            System.out.print("Add ￥");
                            int add=sin.nextInt();
                            now_user.SetMoney('+',add);
                        }
                        else if(scmd.equals("exit"))
                            Exit();
                    }break;
                case 5: System.out.println("Your account balance:"+now_user.GetMoney()+"￥");break;
                case 6: Exit();
                default:System.out.println("Wrong order!");break;
            }
        }
    }
    public static void ShowCmd(){
        System.out.println("Command\t\tContent");
        System.out.println("1\t\t\tShow the menu");
        System.out.println("2\t\t\tOrder the dishes");
        System.out.println("3\t\t\tCheak your dishes");
        System.out.println("4\t\t\tSettlement");
        System.out.println("5\t\t\tCheck account balance");
        System.out.println("6\t\t\tExit");
    }
    //显示菜单
    public static void ShowList(ArrayList<Dish> dishes) {
        System.out.println("———————————— MENU ————————————");
        for(Dish d:dishes) {
            System.out.println(d.num+" "+d.name+" "+d.GetPrice()+"￥");
        }
    }
    //点菜
    public static void Order(ArrayList<Dish> dishes,ArrayList<Dish> order){
        System.out.println("——————————————————————————————");
        System.out.print("Chooes dishes:");
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        Scanner sc=new Scanner(s);
        while(sc.hasNextInt()){
            int norder=sc.nextInt();
            for(Dish d: dishes) {
                if (norder == d.num)
                    order.add(d);
            }
        }
    }
    //
    public static void Cancel(ArrayList<Dish>dishes,ArrayList<Dish>order){
        CheckOut(order);
        System.out.println("Dishes that you want to cancel:");
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        Scanner sc=new Scanner(s);
        while(sc.hasNextInt()){
            int ncel=sc.nextInt();
            for(Dish d: order) {
                if (ncel == d.num)
                    order.remove(d);
            }
        }
    }
    //显示已点的菜，显示账单
    public static void CheckOut(ArrayList<Dish> order){
        int n=0;
        System.out.println("——————————————————————————————");
        System.out.println("Dishes that have been ordered:");
        for(Dish d:order) {
            System.out.println(++n + " " + d.name + " " + d.GetPrice() + "￥");
        }
    }

    //消费结算
    public static void Settle(ArrayList<Dish>dishes,ArrayList<Dish> order,User nowu) throws
            illegalltemException{
        System.out.println("——————————————————————————————");
        int all_price=0;
        for(Dish d:order)
            all_price +=d.GetPrice();
        System.out.println("Total prices:"+all_price+"￥");
        System.out.println("#Your account balance :"+nowu.GetMoney()+"￥");

        System.out.println("Enter “yes” to pay for it \nEnter “no” to cancel order\nEnter “change” to change order");
        String ucmd;
        Scanner in = new Scanner(System.in);
        ucmd = in.nextLine();

        if(ucmd.equals("yes")) {
            nowu.SetMoney('-',all_price);
            if(nowu.GetMoney()<0)
                throw new illegalltemException(nowu.GetMoney());
        }
        else if(ucmd.equals("change")) ChgeOrder(dishes,order);
        else if(ucmd.equals("no")) Exit();
        else System.out.println("#Wrong order!");
    }

    public static void ChgeOrder(ArrayList<Dish>dishes,ArrayList<Dish>order){
        System.out.println("Enter “+” to order more dishes\nEnter “-” to cancel some dishes");
        Scanner in=new Scanner(System.in);
        String p=in.nextLine();
        if(p.equals("+"))Order(dishes,order);
        else if(p.equals("-"))Cancel(dishes,order);
        else System.out.println("#Wrong order!");
    }

    public static User UserCheck(String uname,String upw,int count){
        int user_num=4;
        User u1=new User("admin","12345",5000);
        User u2=new User("Jack","12345",5300);
        User u3=new User("Mary","12345",6500);
        User u4=new User("Marco","19890531",50000);

        ArrayList<User>users=new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);

        int i=1;//判断用户是否存在以及密码是否正确
        for(User u:users){
            if(u.name.equals(uname) && u.GetPassword().equals(upw)) {
                System.out.println("———————————SUCCEED————————————");
                return u;
            }
            else if(u.name.equals(uname) && !u.GetPassword().equals(upw)&&count<3){
                System.out.println("#Wrong password!Please try again!");
                count++;
                Scanner in_user=new Scanner(System.in);
                System.out.print("Enter your user name:");
                uname=in_user.next();
                System.out.print("Enter your password:");
                upw=in_user.next();

                return UserCheck(uname,upw,count);

            }
            else if(count==3){
                System.out.println("#The number of errors exceeds the limit!");
                Exit();
            }
            else if(i==user_num) {
                //用户不存在 注册新用户
                System.out.println("#The user does not exist!\nEnter “new” to sign up for a new account\nEnter “exit” to exit the program ");
                String ucmd;
                Scanner in = new Scanner(System.in);
                ucmd = in.nextLine();

                if(ucmd.equals("new")) {
                    return SignUp(users);
                }
                else if(ucmd.equals("exit")) Exit();
                else System.out.println("#Wrong order!");
            }
            i++;
        }
        return null;
    }

    public static User SignUp(ArrayList<User> users){
        Scanner in=new Scanner(System.in);
        System.out.print("Enter your user name:");
        String new_name=in.nextLine();
        System.out.print("Enter your password:");
        String new_password=in.next();

        //添加新用户
        User newu=new User(new_name,new_password,0);
        users.add(newu);
        return newu;
    }

    public static void Exit(){
        System.out.println("———————————— EXIT ————————————");
        exit(0);
    }
}

