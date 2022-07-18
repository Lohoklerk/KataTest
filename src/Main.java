import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        String theLine= sc.nextLine();
        theLine.toUpperCase();
        System.out.println(calc(theLine));
    }
    public static String calc(String input)
    {
        int A,B,C,D;
        D=0;
        C=0;
        A=-21;
        B=-21;
        String[] elements= input.split("\\s+");
        if (elements.length>3)
        {
            try
            {
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System. exit(1);
            }
        }
        if (elements.length<3)
        {
            try
            {
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                System. exit(1);
            }
        }

        try {A=Integer.parseInt(elements[0]);}  //попытка запарсить числа. Если не получится - число римское и значение переменной останется -11
        catch(NumberFormatException e) {}
        try {B=Integer.parseInt(elements[2]);}
        catch(NumberFormatException e) {}
        C=A+B;
        if (C>-42 && C<=-11)    //системы счисления совпадут только в том случае, если значения не запарсятся и в сумме А и В
        {                      //дадут -42 или если оба значения поменяются.Тогда минимальное значение которое может принять
            try                //выражение это -10. Все остальные числа в промежутке от -42 до -11 получаются только если
            {                  //системы счисления не совпадают.
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                System. exit(1);
            }
        }
        if (A==-21) A=toArabic(elements[0]);  //тк мы не можем совершать мат операции с римскими числами- переведем их в арабские
        if (B==-21) B=toArabic(elements[2]);
        if (A>10 || A<1 || B>10 || B<1)
        {
            try
            {
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("throws Exception //т.к. как минимум одно из чисел меньше 1 или больше 10");}
            System. exit(1);
        }
        switch(elements[1].charAt(0))
        {
            case '+':
                D=A+B;
                break;
            case '-':
                D=A-B;
                break;
            case '/':
                D=(int)(A/B);
                break;
            case '*':
                D=A*B;
                break;
            default: {try
            {
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            } break;}
        }
        if (D<1 && C==-42)
        {
            try
            {
                throw new IOException();
            } catch (IOException e)
            {
                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                System. exit(1);
            }
        }
        if (C==-42){return toRoman(D);}
        else { return Integer.toString(D);}
    }
    static int toArabic(String romNumber)
    {
        int result=0;
        for (int i= 0;i<romNumber.length(); i++){
            if(i<(romNumber.length()-1)){
                if((letterToNumber(romNumber.charAt(i)))<(letterToNumber(romNumber.charAt(i+1)))){
                    result-=letterToNumber(romNumber.charAt(i));
                }
                else{result+=letterToNumber(romNumber.charAt(i));}
            } else {result+=letterToNumber(romNumber.charAt(i));}
        }
        return result;
    }
    static String toRoman(int arNumber)
    {
        String result="";
        while (arNumber>0)
        {
            if(arNumber>=1000){
                arNumber-=1000;
                result+="M";
            }
            if(900<=arNumber && 1000>arNumber){
                arNumber-=900;
                result+="CM";
            }
            if(500<=arNumber && arNumber<900){
                arNumber-=500;
                result+="D";
            }
            if(400<=arNumber && arNumber<500){
                arNumber-=400;
                result+="CD";
            }
            if(100<=arNumber && arNumber<400){
                arNumber-=100;
                result+="C";
            }
            if(90<=arNumber && arNumber<100){
                arNumber-=90;
                result+="XC";
            }
            if(50<=arNumber && arNumber<90){
                arNumber-=50;
                result+="L";
            }
            if(40<=arNumber && arNumber<50){
                arNumber-=40;
                result+="XL";
            }
            if(10<=arNumber && arNumber<40){
                arNumber-=10;
                result+="X";
            }
            if (arNumber==9) {
                arNumber=0;
                result+="IX";
            }
            if(arNumber>=5 && arNumber<9){
                arNumber-=5;
                result+="V";
            }
            if(arNumber==4){
                arNumber=0;
                result+="IV";
            }
            if(arNumber>=1&&arNumber<=3){
                arNumber--;
                result+="I";
            }
        }
        return result;
    }
    private static int letterToNumber(char letter){
        switch (letter) {
            case 'I':  return 1;
            case 'V':  return 5;
            case 'X':  return 10;
            case 'L':  return 50;
            case 'C':  return 100;
            case 'D':  return 500;
            case 'M':  return 1000;
            default:   return -1;
        }
    }

}
