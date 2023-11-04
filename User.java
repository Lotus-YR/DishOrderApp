public class User {
    String name;
   private String password;
   private double money;
    User(String nm,String pw,double m){
        name=nm;
        password=pw;
        money=m;
    }
    User(){
        name=null;
        password=null;
        money=0;
    }
    String GetPassword(){return password;}
    double GetMoney(){return money;}
    void SetMoney(char p,double d){
        if(p=='+') money+=d;
        else if(p=='-')money-=d;
        else  System.out.println("Wrong order!");
    }
}



