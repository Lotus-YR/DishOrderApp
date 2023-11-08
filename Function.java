import java.util.ArrayList;
//用户和管理员 同一功能的不同实现
public interface Function {
    public void ShowCmd();
    public void SetPassword();
    public void ChooseCmd(ArrayList<Dish>dishes,ArrayList<Dish>order,User now_user,Admin ad,ArrayList<User>users);

    //public void DelUser();

}





