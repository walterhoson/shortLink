package top.wayneshen.slk.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 进制转化
 *
 * @Author wayne
 * @Date 2020/6/4 15:38
 */
@Slf4j
public class ConversionUtil {

    private static char[] rDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/'};


    public static String encode(long number) {
        int digitIndex;
        long longPositive = Math.abs(number);
        //64进制
        int radix = 64;
        char[] outDigits = new char[65];
        for (digitIndex = 0; digitIndex <= 64; digitIndex++) {
            if (longPositive == 0) {
                break;
            }
            outDigits[outDigits.length - digitIndex - 1] = rDigits[(int) (longPositive % radix)];
            longPositive /= radix;
        }
        return new String(outDigits, outDigits.length - digitIndex, digitIndex);
    }


    public static void main(String[] args) {
        long a = 10000;
        System.out.println("encode = " + encode(a));
    }

}
