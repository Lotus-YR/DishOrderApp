//检测输入命令是否合法
public class illegalcmdException extends Exception {
    private String cmd;
    public illegalcmdException(String cmd){
        this.cmd=cmd;
    }

    public String GetCmd(){
        return cmd;
    }
}



