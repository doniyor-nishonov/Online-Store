package uz.pdp.backend.tools;
import java.util.Scanner;
/**
 * @author : Doniyor Nishonov
 * @project : online_Shops
 * @date :  2024-02-10-> 16:35
 **/
public interface Input {
    Scanner scannerStr=new Scanner(System.in);
    static Integer inputInt(String msg){
        Scanner SCANNER_INT=new Scanner(System.in);
        System.out.print(COLOR.BLUE + msg+": " + COLOR.STOP);
        if(SCANNER_INT.hasNextInt()){
            return SCANNER_INT.nextInt();
        }else {
            return inputInt(msg);
        }
    }
    static String inputStr(String msg){
        System.out.print(COLOR.PURPLE + msg+": " + COLOR.STOP);
        return scannerStr.nextLine();
    }
}


