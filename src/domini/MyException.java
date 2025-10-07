package domini;

public class MyException extends Exception{
    private MyException(){
        // no utilitzis una excepció sense indicar un missatge!!
    }
    public MyException(String missatge){
        super(missatge);
    }
}
