package com.ycxc.upload.common.utils;

import com.ycxc.upload.exception.CryptException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加签示例
 * Map<String, String> map = new TreeMap<String, String>();
 * //....省略map put值...
 * String sign = DigitalSign.getInstance().signMsgByAbsolutePath(map, path,
 * password);
 * map.put("sign", sign);
 * <p>
 * 验签示例
 * map.remove("sign");
 * DigitalSign digitalSign = DigitalSign.getInstance();
 * boolean result = digitalSign.verifyMsgByAbsolutePath(sign, params, merchCertPath);
 * 证书加解密/签名/验签类
 */
public class DigitalSignUtil {

    private static Logger logger = LoggerFactory.getLogger(DigitalSignUtil.class);

    private static DigitalSignUtil single = null;

    /**
     * 编码 格式
     */
    private static String encoding = "UTF-8";

    /**
     * 算法
     */
    private String arithmetic = "SHA1withRSA";

    /**
     * 私钥缓存
     **/
    private ConcurrentHashMap<String, RSAPrivateCrtKey> map = new ConcurrentHashMap<>();

    private DigitalSignUtil(String encoding) {
        logger.debug(encoding);
    }

    private DigitalSignUtil() {

    }

    /**
     * 初始化 实例
     */
    public static synchronized void init() {
        if (single == null) {
            single = new DigitalSignUtil(encoding);
        }
    }

    /**
     * 初始化 实例
     */
    public static synchronized void init(String charset) {
        if (single == null) {
            single = new DigitalSignUtil();
            encoding = charset;
        }
    }

    //静态工厂方法
    public static DigitalSignUtil getInstance() {
        if (single == null) {
            init(); //为空的时候同步实例
        }
        return single;
    }

    //静态工厂方法
    public static DigitalSignUtil getInstance(String charset) {
        if (single == null) {
            init(charset); //为空的时候同步实例
        }
        return single;
    }

    /**
     * 私钥签名
     *
     * @param tobeSigned 待签字符
     * @param priKeyPath classpath 私钥路径
     * @param password   密码
     * @return 签名字符
     * @throws CryptException 密钥异常
     */
    public String signMsgByRelativePath(final String tobeSigned, final String priKeyPath, final String password) throws CryptException {
        RSAPrivateCrtKey priKey = getPriKeyByRelativePath(priKeyPath, password);
        return sign(priKey, tobeSigned);
    }


    /**
     * 私钥签名
     *
     * @param tobeSigned   待签字符
     * @param priKeyStream 私钥文件流
     * @param password     密码
     * @return 签名字符
     * @throws CryptException 密钥异常
     */
    public String SignMsgByInputStream(String tobeSigned, InputStream priKeyStream, String password) throws CryptException {
        RSAPrivateCrtKey priKey = getPriKeyByInputStream(priKeyStream, password);
        return sign(priKey, tobeSigned);
    }

    /**
     * 私钥签名
     *
     * @param tobeSigned
     * @param priKeyPath 私钥绝对路径
     * @param password   密码
     * @return 签名字符
     * @throws CryptException 密钥异常
     */
    public String signMsgByAbsolutePath(String tobeSigned, String priKeyPath, String password) throws CryptException {
        RSAPrivateCrtKey priKey = map.get(priKeyPath);
        if (priKey == null) {
            priKey = getPriKeyByAbsolutePath(priKeyPath, password);
        }
        return sign(priKey, tobeSigned);
    }

    /**
     * 私钥签名
     *
     * @param tobeSigned 代签字符
     * @param priKeyPath 私钥绝对路径
     * @param password   密码
     * @return 签名字符
     * @throws CryptException 密钥异常
     */
    public String signMsgByAbsolutePath(Map<String, Object> tobeSigned, String priKeyPath, String password) throws CryptException {
        RSAPrivateCrtKey priKey = map.get(priKeyPath);
        if (priKey == null) {
            priKey = getPriKeyByAbsolutePath(priKeyPath, password);
        }
        return sign(priKey, tobeSigned);
    }

    /**
     * 公钥验签
     *
     * @param tobeVerfied 待签字符
     * @param plainText
     * @param pubKey      文件输入流
     * @return 验证结果 true | false
     * @throws CryptException 秘钥异常
     */
    public boolean verifyMsgByInputSteam(String tobeVerfied, String plainText, InputStream pubKey) throws CryptException {
        //获取公钥
        RSAPublicKey pubkey = getPubKeyByInputSteam(pubKey);
        return verify(pubkey, tobeVerfied, plainText);
    }

    /**
     * 通过公钥验签
     *
     * @param tobeVerified
     * @param plainText    待校验字符串
     * @param pubkeyPath   公钥绝对路径
     * @return
     * @throws CryptException
     */
    public boolean verifyMsgByAbsolutePath(String tobeVerified, String plainText, String pubkeyPath) throws CryptException {
        RSAPublicKey pubkey = getPubKeyByAbsolutePath(pubkeyPath);
        return verify(pubkey, tobeVerified, plainText);
    }


    /**
     * 通过公钥验签
     *
     * @param tobeVerified
     * @param map
     * @param pubkeyPath   公钥绝对路径
     * @return
     * @throws CryptException
     */
    public boolean verifyMsgByAbsolutePath(String tobeVerified, Map<String, Object> map, String pubkeyPath) throws CryptException {
        RSAPublicKey pubkey = getPubKeyByAbsolutePath(pubkeyPath);
        return verify(pubkey, tobeVerified, map);
    }

    /**
     * 公钥验签
     *
     * @param tobeVerfied
     * @param plainText
     * @param CertFile    文件classpath下路径
     * @return
     * @throws CryptException
     */
    public boolean verifyMsgByRelativePath(String tobeVerfied, String plainText, String CertFile) throws CryptException {
        RSAPublicKey pubkey = getPubKeyByRelativePath(CertFile);
        return verify(pubkey, tobeVerfied, plainText);
    }

    /**
     * 使用公钥对数据加密
     *
     * @param TobeEncryptMsg 待加密的明文字符串
     * @param certFile       公钥路径[相对地址 classpath下]
     * @return 加密后的字符串
     * @throws CryptException 错误信息
     */
    public String encryptMsg(String TobeEncryptMsg, String certFile) throws CryptException {
        //获取公钥
        RSAPublicKey pubKey = getPubKeyByRelativePath(certFile);
        Cipher instance;
        try {
            instance = Cipher.getInstance(pubKey.getAlgorithm());
            instance.init(Cipher.ENCRYPT_MODE, pubKey);
            String encryMsg = Base64.encodeBase64String(instance.doFinal(Base64.encodeBase64(TobeEncryptMsg.getBytes(encoding))));
            return encryMsg;
        } catch (Exception e) {
            throw new CryptException("加密失败", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param TobeDecodeMsg 待解密的加密字符串
     * @param priFile       私钥路径[相对路径 classpath下]
     * @param passWord      私钥密码
     * @return 解密后的明文字符串
     * @throws CryptException 错误信息
     */
    public String decodeMsg(String TobeDecodeMsg, String priFile, String passWord) throws CryptException {
        RSAPrivateCrtKey priKey = getPriKeyByRelativePath(priFile, passWord);
        Cipher instance;
        try {
            instance = Cipher.getInstance(priKey.getAlgorithm());
            instance.init(Cipher.DECRYPT_MODE, priKey);
            String string = new String(Base64.decodeBase64(instance.doFinal(Base64.decodeBase64(TobeDecodeMsg))), encoding);
            return string;
        } catch (Exception e) {
            throw new CryptException("解密失败", e);
        }
    }

    /**
     * 获取私钥 文件流
     *
     * @param keyFileStream
     * @param password
     * @return
     * @throws CryptException
     */
    private RSAPrivateCrtKey getPriKeyByInputStream(InputStream keyFileStream, String password) throws CryptException {
        return getPriKey(keyFileStream, password);
    }

    /**
     * 通过文件绝对路径加载私钥
     *
     * @param priKeyPath 绝对路径
     * @param password   密码
     * @return
     * @throws CryptException
     */
    private RSAPrivateCrtKey getPriKeyByAbsolutePath(String priKeyPath, String password) throws CryptException {
        FileInputStream file;
        try {
            file = new FileInputStream(priKeyPath);
        } catch (FileNotFoundException e) {
            throw new CryptException("秘钥路径不正确", e);
        }
        RSAPrivateCrtKey priKey = getPriKey(file, password);
        map.put(priKeyPath, priKey);
        return priKey;
    }

    /**
     * 获取私钥 [classpath]
     *
     * @param KeyFile
     * @param passWord
     * @return
     */
    private RSAPrivateCrtKey getPriKeyByRelativePath(String KeyFile, String passWord) throws CryptException {
        //获取项目 相对路径
        ClassLoader cl = DigitalSignUtil.class.getClassLoader();
        InputStream fiKeyFile = cl.getResourceAsStream(KeyFile);
        return getPriKey(fiKeyFile, passWord);
    }

    /**
     * 获取公钥 [文件输入流]
     *
     * @param pubKey
     * @return
     * @throws CryptException
     */
    private RSAPublicKey getPubKeyByInputSteam(InputStream pubKey) throws CryptException {
        return getPublicKey(pubKey);
    }


    /**
     * 获取公钥 [classpath下]
     *
     * @param CertFile 公钥文件路径
     * @return 公钥
     */
    private RSAPublicKey getPubKeyByRelativePath(String CertFile) throws CryptException {
        //读取公钥文件
        ClassLoader cl = DigitalSignUtil.class.getClassLoader();
        InputStream certfile = cl.getResourceAsStream(CertFile);
        //获取公钥
        return getPublicKey(certfile);
    }

    /**
     * 获取公钥 通过绝对路径
     *
     * @param pubKeyPath 公钥文件路径
     * @return 公钥
     * @throws CryptException 秘钥异常
     */
    private RSAPublicKey getPubKeyByAbsolutePath(String pubKeyPath) throws CryptException {
        try {
            FileInputStream pubKey = new FileInputStream(pubKeyPath);
            return getPublicKey(pubKey);
        } catch (Exception e) {
            throw new CryptException("文件读取失败", e);
        }
    }

    /**
     * 获取公钥
     *
     * @param pubKey 公钥流
     * @return 公钥
     * @throws CryptException 秘钥异常
     */
    private RSAPublicKey getPublicKey(InputStream pubKey) throws CryptException {
        X509Certificate x509cert;
        try {
            //实例化 x509
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            x509cert = (X509Certificate) cf.generateCertificate(pubKey);
        } catch (CertificateException e) {
            if (pubKey != null) {
                try {
                    pubKey.close();
                } catch (IOException e1) {
                    throw new CryptException("文件流关闭异常", e1);
                }
            }
            throw new CryptException("初始化公钥异常", e);
        }
        //读取公钥
        return (RSAPublicKey) x509cert.getPublicKey();
    }

    /**
     * 获取私钥
     *
     * @param priKey      私钥流
     * @param keyPassword 密码
     * @return 私钥
     * @throws CryptException 秘钥异常
     */
    private RSAPrivateCrtKey getPriKey(InputStream priKey, String keyPassword) throws CryptException {
        String keyAlias;
        RSAPrivateCrtKey prikey = null;
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(priKey, keyPassword.toCharArray());
            Enumeration<?> myEnum = ks.aliases();
            while (myEnum.hasMoreElements()) {
                keyAlias = (String) myEnum.nextElement();
                if (ks.isKeyEntry(keyAlias)) {
                    prikey = (RSAPrivateCrtKey) ks.getKey(keyAlias, keyPassword.toCharArray());
                    break;
                }
            }
        } catch (Exception e) {
            if (priKey != null) {
                try {
                    priKey.close();
                } catch (IOException e1) {
                    throw new CryptException("流关闭异常", e1);
                }
            }
            throw new CryptException("加载私钥失败", e);
        }
        if (prikey == null) {
            throw new CryptException("私钥不存在");
        }
        return prikey;
    }

    /**
     * 签名
     *
     * @param priKey     私钥
     * @param tobeSigned 待签字符串
     * @return 签名结果
     * @throws CryptException 密钥异常类
     */
    private String sign(RSAPrivateCrtKey priKey, String tobeSigned) throws CryptException {
        try {
            Signature sign = Signature.getInstance(arithmetic);
            sign.initSign(priKey);
            sign.update(tobeSigned.getBytes(encoding));
            byte signed[] = sign.sign();
            return Base64.encodeBase64String(signed).replaceAll("\\+", "%2B");
        } catch (Exception e) {
            throw new CryptException("签名失败", e);
        }
    }

    /**
     * 签名
     *
     * @param priKey 私钥
     * @param map    待签字符串
     * @return 签名结果
     * @throws CryptException 密钥异常类
     */
    private String sign(RSAPrivateCrtKey priKey, Map<String, Object> map) throws CryptException {
        try {
            String tobeSigned = appendMap(map);
            Signature sign = Signature.getInstance(arithmetic);
            sign.initSign(priKey);
            sign.update(tobeSigned.getBytes(encoding));
            byte signed[] = sign.sign();
            return Base64.encodeBase64String(signed).replaceAll("\\+", "%2B");
        } catch (Exception e) {
            throw new CryptException("签名失败", e);
        }
    }


    /**
     * 连接参数
     * @param map 参数map
     * @return 参数连接字符串
     */
    private static String appendMap(Map<String, Object> map) {
        Set<String> keySet = map.keySet();
        StringBuilder sb = new StringBuilder();
        for (String mapKey : keySet) {
            String value = (String) map.get(mapKey);
            sb.append(mapKey).append("=").append(value).append("&");
        }
        return sb.toString();
    }

    /**
     * 验证签名
     *
     * @param pubKey      公钥
     * @param tobeVerfied 已签名字符串
     * @param plainText   待校验字符串
     * @return true || false
     * @throws CryptException 密钥异常
     */
    private boolean verify(RSAPublicKey pubKey, String tobeVerfied, String plainText) throws CryptException {
        try {
            Signature verify = Signature.getInstance(arithmetic);
            verify.initVerify(pubKey);
            verify.update(plainText.getBytes(encoding));
            return verify.verify(Base64.decodeBase64(tobeVerfied.replaceAll("%2B", "\\+")));
        } catch (Exception e) {
            throw new CryptException("验签失败", e);
        }
    }


    /**
     * 验证签名
     *
     * @param pubkey      公钥
     * @param tobeVerfied 已经签名的map
     * @param map         待校验的map
     * @return true || false
     * @throws CryptException 密钥异常类
     */
    private boolean verify(RSAPublicKey pubkey, String tobeVerfied, Map<String, Object> map) throws CryptException {
        try {
            String plainText = appendMap(map);
            Signature verify = Signature.getInstance(arithmetic);
            verify.initVerify(pubkey);
            verify.update(plainText.getBytes(encoding));
            return verify.verify(Base64.decodeBase64(tobeVerfied.replaceAll("%2B", "\\+")));
        } catch (Exception e) {
            throw new CryptException("验签失败", e);
        }
    }

}
