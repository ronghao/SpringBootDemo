package com.haohaohu.springbootdemo.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author haohao(ronghao3508 gmail.com) on 2024/2/28 15:58
 * @version v1.0
 */
public class AESTool {

    private static final String AES_ALG = "AES";

    /**
     * AES算法
     */
    private static final String AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";

    private static final byte[] AES_IV = initIv(AES_CBC_PCK_ALG);

    private static String aesDecrypt(String content) throws Exception {
        return aesDecrypt(content, "NDU3M2IwZmZkYTlhYTYzZWNiMWMwN2M2ZmY5ZmVhNmE=", "utf-8");
    }

    /**
     * AES解密
     *
     * @param content
     * @param key
     * @param charset
     * @return
     * @throws ErrorCodeException
     */
    private static String aesDecrypt(String content, String key, String charset) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec iv = new IvParameterSpec(initIv(AES_CBC_PCK_ALG));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Base64.getDecoder().decode(key.getBytes()),
                    AES_ALG), iv);

            byte[] cleanBytes = cipher.doFinal(Base64.getDecoder().decode(content.getBytes()));
            return new String(cleanBytes, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param fullAlg
     * @return
     * @throws GeneralSecurityException
     */
    private static byte[] initIv(String fullAlg) {

        try {
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        } catch (Exception e) {

            int blockSize = 16;
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        }
    }

    public static void main(String[] args) throws Exception {


        //String s1 = aesDecrypt("cmhI+xSdQrHR9rXykItGIloA5dD3hEq72vjGz+v5vZvhC5vth2q+kcB7DbThxfDtSkmMyLnx9fzHR9mV9owSEBejriixaA+EiKY4JD7cRpa/b1wY7S1Gqw+c//Fa4L8o6165sa4RPNnNDjg0iw3HnhUUAd2KyMA/BaJ6K33/pDiXQTR6yHaekkns+hhKEf8rbu015rXYSsl+R+fdjPHrZny2CZ8QuwaU6ir4O/d1kVcf3i8ipv8LYYm4qomkkDzRv26y1cxF9KC+nFRmxSRE8uxLT2W9OIffJD1sbWArXPnjgKU80TpHAPcMXIG9qzSFMWcthlRCRVhZWQ313m9aiUqKuatCd3QWeb1KtJ2OJph60Sf6P54qLhyyaKuS+eshIH7KmZdVl5RIUkRy4JfhFcoBiFLrWUbt5QwlSWsdavW6r16ER8KIG4Qah+fhMHSVgEUI3gJ4LfLecpqHGDn4aN3hOfX1rleoE/NN6vvrpuOskQCp4oWeRczBZch+UCutxfjxxrWUDAipCUhDdwM7ydv6ICH90TWPlXMV/lIMBywaeVD3oOev+xPqybMjXFgQHwcneTqd+SxjriJKW57+3q0/4VOdoM7E14dPvZ4R5DC6knkAUliqGNqZLzWuf3NsBQmpO9TVy8VSHlQBbiV+rtiOjBTn/Kf7i7h5aW8K+1O2RoSP96oOkvtDXveBcCjvEAIA1nVXhUpRpNPUc3qxdYYgvcHINGcF86bGJVVcAlWNsn2FGfqDnQ1CMLxe83yXlBRhqp8s9dgUgOYIeUFf86pDHD/2CYuSlmQLvBuHinrRntsHsyBdWC/tF2z1jbS/XcscXmnTvteRDMZcknoZhmBrrJT7Yg3ppQHRiZ7RrF5GKkfvU5qxTowFz7ab5PxEBvHy399+hzm3lqApk9Oquc//fdWZ20+kKrRfu0uimN8QedroWzYLDRt/nFWFx+ufeLUyncuf4g9K/V2BcNfcSCwu7HUkB4rDvDjbBKoAH7ROVobmrS3+5ZAwqxasapcKR05Re1cGHXtqoXzneAwxpo2nbK9pcmx3Ru5i6WkqSqcXVjPboCWXVIOQJmvzXY7zrsm7vwZ3vFnRZ/UggPcu76CAN+zEGJGno1wvEKf/RxXnteMsKCgTFf2RCH3MKOp5wjFhV74ef7oeX/MLY9gA7v0erR/L3R46iAOjApcFkds21IFPpPJ7JBPPpU+7gD/jknyqbSQchbazS3swN9RohbY7e/fj+d72oWwpykdLbM2fBW0VAeI8Ozby7B3kdynFE7V6M8BU33H0TvnohqI6pqFN7GxZQyMX1u7NjhUVNRHfI1MHauwWW+0ii1axq5iB+5ShFbayGBDgNObk+BwCKjVYSxJelNq7SXBhMUZqHTZfbPr5TgLQLW6ie+XAOIBcpWO+698otJGiKjRV3FfRVDqaSAwFYjKllhtNMwtyldkcJCjINjjHO89FaE+3GT72SeZP7n9uMPda6/1h+/o96ObEV9t+nQFaxjXveU64WMNIuvhJ+Ljn2d1dhpdQVTQIzXgJBezjn7y0RfCHx1KB7BPlI1qsxmBE+I9/LAMu2TqQE6enQVhddR4ziTk2rlEPSFhQrFl8uEsgW7hz7Qq8VVbuJcAof2EfyKBwe4VXxncPsqHclfuvw3kcXTnwB2igD1cFazoI4B3M8E6NQKelZUrmx48HYIgIp18FdV04bUJKKgqumdf44qIdu98vemD/Bm/cfqArprgWqPm3xEMiQk/kDKbLSKkP5feFDjl06J1nBJLJg9M03yfX40sLun76qB/vyP65tcmv1y/VEi5YirabLbJU+fBXxS32X93pvx2Rk9XHpl1yjr8ONi1yn5xms8Ogq+2bIKjIBNXwx5s2BjwItsdIX6po4bqa1BbMN+3ZA+ioRURR0aB8fuJ0IFqZ", "YTViYmM0MGZiZWUxMzgxMDU1OTdjNGM4ZTZlYzYyYTE=", "utf-8");
        //System.out.println(s1);

        //线上
        String jsonStr = "{\"data\":\"wEySR35/SOnozsYg3p4Rnv8PLU4uKCVniHC3XqkrMYk7s2j4rSQ4UdzzPt7ECEWYJ3If3UqLE2Istb014fRd9HwXB3Q4mVam7Ujc59qc4UnbSJD4UQzObWXyOCGn3GW7W6hfX9XXbWpjXoOcCboi+Eq1270uLg74hnoKKanqsGHbqtLM5is8AFiWGq+biwpA9txL9F2BxLagNDkfAsahQfNm1q1quS27gq6Bdo5mAXcpKCZ1holkoCpt1H58j0n4s1hS7oIFE2RTy6NbOY0pTTTUwSkb5r22vAZ4mZZP2lXRRv0nustrVzY/YTW6f25LnssfNIle+16nwr+93xBdvkO5S0ONrxNgYAZXqDeavjKzAUCGAR1KiIqQcJf2V7x+XYfiWmFGThmyweAAi/yD60SQD93L9nyj2TBVUBQSU/SwjIlhOMIZDb2cgTihR9ai\"}";

        //测试
        //String jsonStr = "{\"data\":\"{\\\"data\\\":\\\"lDLMBQHcFaOhS5LObT5QGe+MUhL8emuIqsC7zPPriI3g+j9F8je6s2OHopkRY8W/JM41dN5N0LXr17Zzi/sGmjof+7ov+rl4TisGCc3lXt9A0EPx0F87AjtEQfavMjJ1Bp7Y8OEX2l3UK+FmxdcjMIn0r2d4KJ4bJAyP9Vd6IHtyug/6aY5EM4hj1Y+bd/teg4aWuhr3oeuBdUQTMI8R0Vs1B1bXLXf8Obpw9+vya86+QJMvLYYd0i/sSaA+c9yKWOKxc9iZ6z/VuMqzdHMdLLqCnjGp5uCYXRm3pvopN7RxFjblY8ZqT8ko+TV+igE5YVO0b5j+P07qTNFuOn1whvsjBbkb/LCRnMm2yeXnpkZN77/m6b+M9s2XOfMlmcA6mYG7pvDIcEELN4DOS/AQuRTdw4F6HVes+XZ+dIi9RvQpFprcMYvPp+dHDjujAFld8ICGWSOtngtxzj5VgloRoQ==\\\"}\"}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        if (jsonObject != null && jsonObject.containsKey("data")) {
            //JSONObject innerDataJSON = jsonObject.getJSONObject("data");
            //if (innerDataJSON != null && innerDataJSON.containsKey("data")) {
                String data = jsonObject.getString("data");
                System.out.println(data);
                System.out.println("----------------------");

                AESTool aesTool = new AESTool();
                String s = aesTool.aesDecrypt(data);
                System.out.println(s);
                if (StrUtil.isBlank(s)) {
                    return;
                }
            }
        //}
    }
}
