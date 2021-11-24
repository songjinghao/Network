package com.paul.song.network.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
//import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class TencentUtil {
//    public static final String secretId = "AKIDjxlr38cez45xt69i1qbiMSwPf59c3i1kw1ge";
//    public static final String secretKey = "cr05ks0w1pGtgiztpIfk44rln8el4n3zzm1lorv8";
    public static final String secretId = "AKIDajoQiz63Dg17613b32ahlhs5yFt3fJ8DSc7o";
    public static final String secretKey = "9z4imw0nsn3hadyv07vg5rx11l5k6xj35no794Wk";
    public static final String CONTENT_CHARSET = "UTF-8";
    public static final String HMAC_ALGORITHM = "HmacSHA1";

    private TencentUtil() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String sign(String secret, String timeStr) {
        // get signStr
        String signStr = "date: " + timeStr + "\n" + "source: " + "source";
        // get sig
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);

            byte[] hash;
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
            mac.init(secretKey);
            hash = mac.doFinal(signStr.getBytes(CONTENT_CHARSET));
            String sig = new String(Base64.getEncoder().encode(hash));
            System.out.println("signValue--->" + sig);
            return sig;
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getAuthorization(String timeStr) {
        String sig = sign(secretKey, timeStr);
        return "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"date source\", signature=\"" + sig + "\"";
    }

    public static String getTimeStr() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(cd.getTime());
    }
}
