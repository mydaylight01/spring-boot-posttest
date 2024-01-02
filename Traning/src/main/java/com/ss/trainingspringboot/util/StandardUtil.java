package com.ss.trainingspringboot.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StandardUtil {
    public static boolean isNull(final Object obj) {
        boolean b = true;
        if (obj != null) {
            b = false;
        }
        return (b);
    }

    public static boolean isEmpty(final BigDecimal d) {
        final boolean b = isNull(d);
        return (b);
    }

    public static boolean isEmpty(final Byte byt) {
        final boolean b = isNull(byt);
        return (b);
    }

    public static boolean isEmpty(final Character c) {
        final boolean b = isNull(c);
        return (b);
    }

    public static boolean isEmpty(final Double d) {
        final boolean b = isNull(d);
        return (b);
    }

    public static boolean isEmpty(final Float f) {
        final boolean b = isNull(f);
        return (b);
    }

    public static boolean isEmpty(final Integer integer) {
        final boolean b = isNull(integer);
        return (b);
    }

    public static boolean isEmpty(final List<?> ls) {
        boolean b = true;
        if ((ls != null) && !ls.isEmpty()) {
            b = false;
        }
        return (b);
    }

    public static boolean isEmpty(final Long l) {
        final boolean b = isNull(l);
        return (b);
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        boolean b = true;
        if ((map != null) && !map.isEmpty()) {
            b = false;
        }
        return (b);
    }

    public static boolean isEmpty(final Number num) {
        final boolean b = isNull(num);
        return (b);

    }

    public static boolean isEmpty(final Short s) {
        final boolean b = isNull(s);
        return (b);
    }

    public static boolean isEmpty(final String st) {
        boolean b = true;
        if ((st != null) && (st.trim().length() > 0)) {
            b = false;
        }
        return (b);
    }

    public static boolean isEmpty(final StringBuilder st) {
        boolean b = true;
        if ((st != null) && (st.toString().trim().length() > 0)) {
            b = false;
        }
        return (b);
    }

    public static boolean isEmpty(final String[] st) {
        boolean b = true;
        if ((st != null) && (st.length > 0)) {
            b = false;
        }
        return (b);
    }

    public static boolean isEmpty(final Date d) {
        final boolean b = isNull(d);
        return (b);
    }


    private static final String[] NUMBER_SCALES = {
            "ล้าน", "สิบ", "ร้อย",
            "พัน", "หมื่น", "แสน", ""
    };
    private static final String[] DIGIT_WORDS = {
            "ศูนย์", "หนึ่ง", "สอง",
            "สาม", "สี่", "ห้า", "หก",
            "เจ็ด", "แปด", "เก้า"
    };

    public static String priceBath(BigDecimal request) {
        String amountText;
        amountText = getBahtText(request);

        return amountText;
    }


    public static String getBahtText(BigDecimal amount) {
        StringBuffer buffer = new StringBuffer();
        BigDecimal absolute = amount.abs();
        int precision = absolute.precision();
        int scale = absolute.scale();
        int rounded_precision = ((precision - scale) + 2);
        MathContext mc = new MathContext(rounded_precision, RoundingMode.HALF_UP);
        BigDecimal rounded = absolute.round(mc);
        BigDecimal[] compound = rounded.divideAndRemainder(BigDecimal.ONE);
        boolean negative_amount = (-1 == amount.compareTo(BigDecimal.ZERO));

        compound[0] = compound[0].setScale(0);
        compound[1] = compound[1].movePointRight(2);

        if (negative_amount) {
            buffer.append("ลบ");
        }

        buffer.append(getNumberText(compound[0].toBigIntegerExact()));
        buffer.append("บาท");

        if (0 == compound[1].compareTo(BigDecimal.ZERO)) {
            buffer.append("ถ้วน");
        } else {
            buffer.append(getNumberText(compound[1].toBigIntegerExact()));
            buffer.append("สตางค์");
        }
        return buffer.toString();
    }

    public static String getNumberText(BigInteger number) {
        StringBuffer buffer = new StringBuffer();
        char[] digits = number.toString()
                .toCharArray();

        for (int index = digits.length; index > 0; --index) {
            int digit = Integer.parseInt(String.valueOf(digits[digits.length
                    - index]));
            String digit_text = DIGIT_WORDS[digit];
            int scale_idx = ((1 < index)
                    ? ((index - 1) % 6)
                    : 6);

            if ((1 == scale_idx) && (2 == digit)) {
                digit_text = "ยี่";
            }

            if (1 == digit) {
                switch (scale_idx) {
                    case 0:
                    case 6:
                        buffer.append((index < digits.length) ? "เอ็ด" : digit_text);
                        break;
                    case 1:
                        break;
                    default:
                        buffer.append(digit_text);
                        break;
                }
            } else if (0 == digit) {
                if (0 == scale_idx) {
                    buffer.append(NUMBER_SCALES[scale_idx]);
                }
                continue;
            } else {
                buffer.append(digit_text);
            }
            buffer.append(NUMBER_SCALES[scale_idx]);
        }
        return buffer.toString();
    }

    public static String leftPad(String input, int totalSize, String padChar) {
        int length = input.length();
        int remain = length - totalSize;
        String result = "";
        if (remain <= 0) {
            result = input.substring(0, totalSize);
        } else {
            result = input;
            for (int i = 0; i < remain; i++) {
                result = padChar.concat(result);
            }
        }
        return result;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date endDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static Date covertDateStringToDate(String date, String format, Locale locale) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        if (date != null && !date.isEmpty()) {
            return formatter.parse(date);
        }
        return null;
    }

    public static Date convertStringToDate(String date, String format, Locale locale) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        if (date != null && !date.isEmpty()) {
            return formatter.parse(date);
        }
        return null;
    }

    public static Timestamp convertDateToTimestamp(Date date) {
        if (!isNull(date)) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    public static String convertYear(String year, String type) {
        if (!isEmpty(type) && "1".equalsIgnoreCase(type)) {
            return Integer.toString(Integer.parseInt(year) + 543);
        } else if (!isEmpty(type) && "2".equalsIgnoreCase(type)) {
            return Integer.toString(Integer.parseInt(year) - 543);
        }
        return year;
    }
}
