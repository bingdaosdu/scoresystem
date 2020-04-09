package com.gde.integral.service.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * TODO
 *
 * @author ~~~
 * @date 2020/2/17
 */
public class IDataUtils {

    /**
     * http get request
     * @param reqUrl 请求url
     * @param contentType 文档类型
     * @param headerMap 头参数列表
     * @return
     * @throws Exception
     */
    public static String getHttpContent(String reqUrl,String contentType, Map headerMap) throws Exception {
        HttpURLConnection httpConnection = null;
        BufferedReader br = null, brErr = null;
        InputStreamReader isr = null, isrErr = null;
        InputStream is = null, isErr = null;
        String resultData = "";
        try {
            URL url = new URL(reqUrl);
//            System.out.println(reqUrl);//建议log4j打印，便于问题定位
            Logger.getLogger(reqUrl);
            httpConnection = (HttpURLConnection) url.openConnection();
            if(null !=headerMap){
                Iterator<Entry<String, String>> it= headerMap.entrySet().iterator();
                while(it.hasNext()){
                    Entry<String, String> entry = it.next();
                    httpConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            httpConnection.setRequestMethod("GET");
            if (contentType == null) {
                httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            } else {
                httpConnection.setRequestProperty("Content-Type", contentType);
            }
            httpConnection.setConnectTimeout(10 * 1000);//连接iData服务的时间，用户可以调整
            httpConnection.setReadTimeout(60 * 1000);//读取流的时间，用户可以调整
            httpConnection.connect();
            is = httpConnection.getInputStream();
            isr = new InputStreamReader(is, "utf-8");
            br = new BufferedReader(isr);
            resultData = br.readLine();
        } catch (Exception e) {
            isErr = httpConnection.getErrorStream();
            if (isErr != null) {
                isrErr = new InputStreamReader(isErr);
                brErr = new BufferedReader(isrErr);
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = brErr.readLine()) != null) {
                    sb.append(line);
                }
                throw new Exception(sb.toString());
            } else {
                throw e;
            }
        }
        finally {
            closeStreams(br, isr, is, brErr, isrErr, isErr);
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }

        return resultData;
    }
    /**
     * post请求服务
     * @param postUrl 服务url
     * @param contentType 文档类型
     * @param postContent 请求body报文
     * @return
     * @throws Exception
     */
    public static String postHttpContent(String postUrl, String contentType,String postContent)
            throws Exception {
        URL url = new URL(postUrl);
//        System.out.println(postUrl);//建议log4j打印，便于问题定位
        Logger.getLogger(postUrl);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("POST");
        if (contentType == null) {
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        } else {
            httpConnection.setRequestProperty("Content-Type", contentType);
        }
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        httpConnection.setUseCaches(true);
        httpConnection.connect();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(httpConnection.getOutputStream(), "utf-8"));
        out.write(postContent);
        out.flush();
        InputStream is = null, isErr = null;
        InputStreamReader isr = null, isrErr = null;
        BufferedReader br = null, brErr = null;
        try {
            is = httpConnection.getInputStream();
            isr = new InputStreamReader(is, "utf-8");
            br = new BufferedReader(isr);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            isErr = httpConnection.getErrorStream();
            if (isErr != null) {
                isrErr = new InputStreamReader(isErr);
                brErr = new BufferedReader(isrErr);
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = brErr.readLine()) != null) {
                    sb.append(line);
                }
                throw new Exception(sb.toString());
            } else {
                throw e;
            }
        } finally {
            // 关闭流
            closeStreams(br, isr, is, brErr, isrErr, isErr, out);
            if (httpConnection != null) {
                try {
                    httpConnection.getInputStream().close();
                    httpConnection.disconnect();
                } catch (IOException e) {
                    throw new Exception("IOException:", e);
                }

            }
        }
    }

    /**
     * 流关闭方法
     * @param closeables
     */
    private static void closeStreams(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用soa rest接口获取动态token
     * @return
     * @throws Exception
     */
    private static String getSoaTokenBySOARest() throws Exception {
        //soa管控的url，生产和测试不同，要配到数据字典
//        String soaServiceUrl="http://kwe-beta.huawei.com/ApiCommonQuery/appToken/getRestAppDynamicToken";
//2018.12.19已从haegl-beta切换到kwe-beta
//	这是生产的：	String soaServiceUrl="http://w3cloud.huawei.com/ApiCommonQuery/appToken/getRestAppDynamicToken";
        String soaServiceUrl="http://w3cloud.huawei.com/ApiCommonQuery/appToken/getRestAppDynamicToken";
        //到soa管控申请自己的appid和静态token，静态token是不变的
        String appID = "com.huawei.gtscloud.operation";
//        String appID = "com.huawei.gtsoperation.dataanalysis.webservice";
        String credential = "GT0e)Wc2+ELl0xWrM-3jAp001k~t74MZr4XY<zq-KQfLlY5(bcd1560321624806";
//        String credential = "@zlJfLkZcvEH7bb-DTQbtVT9^!EQlAR@I@z-NoRo45CEncvIJNP1570698253873";
        String credential_encode= Base64.getEncoder().encodeToString(credential.getBytes("utf-8"));
        ObjectMapper mapper = new ObjectMapper();
        Map params= new HashMap();
        //soa管控平台申请的appId的静态token
        params.put("appId", appID);
        params.put("credential",credential_encode);
        String mapJakcson = mapper.writeValueAsString(params);
        //通过soa管控平台的服务获取动态token，该token 5分钟内有效
        String result = postHttpContent(soaServiceUrl, "application/json; charset=utf-8",mapJakcson);
        if(null!=result){
            Map<String,String> dataMap=	mapper.readValue(result, Map.class);
            String Authorization=dataMap.get("result");
            if(null!=Authorization ){
                return Authorization;
            }else{
                throw new Exception(result);
            }
        }else{
            throw new Exception(result);
        }
    }

    public static String getPersonByEmail(String email) {
        String lang="zh";
        int type = 5;
        String iDataServiceDomain="http://efs.huawei.com";
        //根据单个服务FAQ中拿到服务rest url,注意生产和测试的域名不同，建议做成配置，上线统一修改
        //efs-beta.huawei.com 对应的生产url为efs.huawei.com
        //w3-beta.huawei.com 对应的生产url为w3.huawei.com
        Map headerMap = new HashMap();
        String serviceUrl = iDataServiceDomain + "/f/idata/hr/HRPersonSearch?searchValue={0}&lang={1}&searchType={2}";
//        if (queryID.length() == 8) {
//            //服务uri从每个服务FAQ中获取
//            serviceUrl = iDataServiceDomain+"/ws/soaservices/PersonServlet?empNo={0}&lang={1}";
//        } else {
//            serviceUrl = iDataServiceDomain + "/ws/soaservices/PersonServlet?w3Account={0}&lang={1}";
//        }
        String url = MessageFormat.format(serviceUrl, new String[]{email, lang, String.valueOf(type)});

        String result = "";
        try{
            //http头设置
            //通过Rest服务获取SOA管控动态token ;参考http://3ms.huawei.com/hi/group/2692001/wiki_5009967.html
            //如果已经集成soa管控平台的jar包，直接通过 SoaAppTokenClientUtil.getBasicTokenByAppCredential()获取
            headerMap.put("Authorization", getSoaTokenBySOARest());
            //FAQ中区分服务调用的httpmethod，iData一般是GET或POST
            result = getHttpContent(url, null, headerMap);
            if(!result.contains("person_Mobile_Code")){
                //由于w3.huawei.com域名的iData旧版服务报错的时候状态码还是200，可以根据返回报文是否包含关键字判断是否成功：（
                System.out.println("errorInfo=>"+result);
            }else{
                //这里的demo只打印json，实际可以根据FAQ中的返回信息编写对应的java对象反序列化报文
//                System.out.println("data=>" + result);
                Logger.getLogger(result);
            }
        }catch(Exception e){
            //异常要用log4j打印出来，便于定位问题，是一个编程好习惯！！！
            e.printStackTrace();
        }
        return result;
    }

    public static String getPersonServlet(String queryID) {

        String lang="zh";
        String iDataServiceDomain="http://w3.huawei.com";
        //根据单个服务FAQ中拿到服务rest url,注意生产和测试的域名不同，建议做成配置，上线统一修改
        //efs-beta.huawei.com 对应的生产url为efs.huawei.com
        //w3-beta.huawei.com 对应的生产url为w3.huawei.com
        Map headerMap = new HashMap();
        String serviceUrl = "";
        if (queryID.length() == 8) {
            //服务uri从每个服务FAQ中获取
            serviceUrl = iDataServiceDomain+"/ws/soaservices/PersonServlet?empNo={0}&lang={1}";
        } else {
            serviceUrl = iDataServiceDomain + "/ws/soaservices/PersonServlet?w3Account={0}&lang={1}";
        }
        String url = MessageFormat.format(serviceUrl, new String[]{queryID, lang});

        String result = "";
        try{
            //http头设置
            //通过Rest服务获取SOA管控动态token ;参考http://3ms.huawei.com/hi/group/2692001/wiki_5009967.html
            //如果已经集成soa管控平台的jar包，直接通过 SoaAppTokenClientUtil.getBasicTokenByAppCredential()获取
            headerMap.put("Authorization", getSoaTokenBySOARest());
            //FAQ中区分服务调用的httpmethod，iData一般是GET或POST
            result = getHttpContent(url, null, headerMap);
            if(!result.contains("person_Mobile_Code")){
                //由于w3.huawei.com域名的iData旧版服务报错的时候状态码还是200，可以根据返回报文是否包含关键字判断是否成功：（
                System.out.println("errorInfo=>"+result);
            }else{
                //这里的demo只打印json，实际可以根据FAQ中的返回信息编写对应的java对象反序列化报文
//                System.out.println("data=>" + result);
                Logger.getLogger(result);
            }
        }catch(Exception e){
            //异常要用log4j打印出来，便于定位问题，是一个编程好习惯！！！
            e.printStackTrace();
        }
        return result;
    }

}
