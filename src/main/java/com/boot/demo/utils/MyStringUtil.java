package com.boot.demo.utils;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

/**
 * Created by chenkaihua on 2017/11/1.
 */
public class MyStringUtil {
    public static String formatDouble(Double num, int format) {
        return formatDouble(num, format, RoundingMode.HALF_UP);
    }

    public static String formatDouble(Double num, int format, RoundingMode roundingMode) {
        BigDecimal bigDecimal = new BigDecimal(num);
        bigDecimal = bigDecimal.setScale(format, roundingMode);
        return bigDecimal.toString();
    }

    public static String overlayName(String string) {
        return StringUtils.overlay(string, "*", 1, string != null && string.length() > 2 ? string.length() - 1 : 2);
    }

    public static String overlayPhone(String phone) {
        if(StringUtils.isEmpty(phone) || phone.length() < 6) {
            return phone;
        }
        return StringUtils.overlay(phone, "******", 3, phone.length() - 3);
    }

    public static String overlayIdCard(String cardNo) {
        if(StringUtils.isEmpty(cardNo) || cardNo.length() < 8) {
            return cardNo;
        }
        return StringUtils.overlay(cardNo, "******", 4, cardNo.length() - 3);
    }

    public static String overlayBankCard(String bankCardNo) {
        if(StringUtils.isEmpty(bankCardNo) || bankCardNo.length() < 9) {
            return bankCardNo;
        }
        return StringUtils.overlay(bankCardNo, "******", 4, bankCardNo.length() - 4);
    }

    public static String getBankCardTailNum(String bankCardNo) {
        if (StringUtils.isNotBlank(bankCardNo) && bankCardNo.length() > 4) {
            return bankCardNo.substring(bankCardNo.length() - 4);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(String.format("test： %.0f", 11d));
        System.out.println(overlayPhone("13763376939"));
        System.out.println(overlayPhone("137633"));
        System.out.println(overlayName("22444545"));
        System.out.println(overlayName("孙德厚"));
        System.out.println(overlayName("孙厚"));
        System.out.println(overlayIdCard("622851199009155689"));
        System.out.println(overlayIdCard("6348511990091"));
        System.out.println(DateFormatUtils.format(Calendar.getInstance().getTimeInMillis(), "yyyy-MM-dd") + " 20:00:00");
        System.out.println(overlayBankCard("622851199009155689"));

        System.out.println(StringEscapeUtils.escapeXml("dsjskdjds<script>alter(1)</script>"));
        System.out.println(StringEscapeUtils.escapeHtml4("dsjskdjds<script>alter(1)</script>"));

        System.out.println(StringUtils.isNumeric("-1"));
        System.out.println(StringUtils.isNumeric("+1"));
        System.out.println(StringUtils.isNumeric("15214353029"));

        System.out.println(RandomUtils.nextInt(1000));
        System.out.println(RandomUtils.nextInt(1000));
        System.out.println(RandomUtils.nextInt(1000));
        System.out.println(RandomUtils.nextInt(1000));

        System.out.println("-193.69的绝对值=" + Math.abs(-193.69));
    }
}
