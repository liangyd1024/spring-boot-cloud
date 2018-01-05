package com.lyd.spring.boot.redis.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2017/12/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@Controller
@RequestMapping("/faceId")
@Slf4j
public class FaceIdController {

    private static final String TOKEN_URL = "https://api.megvii.com/faceid/liveness/v2/get_token";

    private static final String DO_LIVENESS_URL = "https://api.megvii.com/faceid/liveness/v2/do";

    private static final String GET_RESULT = "https://api.megvii.com/faceid/lite/get_result";


    @Value("${apiKey}")
    private String apiKey;

    @Value("${apiSecret}")
    private String apiSecret;


    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/getToken")
    @ResponseBody
    public Object getToken(String certNo,String certName,String comparisonType,HttpSession session){
        Map responseEntity = new HashMap();
        try{
            log.info("call getToken certNo:{},certName:{},comparisonType:{},TOKEN_URL:{}",certNo,certName,comparisonType,TOKEN_URL);
            session.setAttribute("bizId",System.currentTimeMillis()+"");
            MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
            reqParams.add("api_key",apiKey);
            reqParams.add("api_secret",apiSecret);
            reqParams.add("comparison_type",comparisonType);
            reqParams.add("idcard_name",certName);
            reqParams.add("idcard_number",certNo);
            reqParams.add("uuid", UUID.randomUUID().toString());
            reqParams.add("return_url","http://d9ea3598.ngrok.io/faceId/getTokenReturnUrl");
            reqParams.add("notify_url","http://d9ea3598.ngrok.io/faceId/getTokenNotifyUrl");
            reqParams.add("biz_no",session.getAttribute("bizId").toString());
            reqParams.add("biz_extra_data","test");
            log.info("call getToken reqParams:{}",reqParams);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(reqParams,headers);
            ResponseEntity<Map> resp  = restTemplate.exchange(TOKEN_URL, HttpMethod.POST,entity,Map.class);
            responseEntity.put("resp",resp.getBody());
        }catch (HttpClientErrorException h){
            responseEntity.put("statusCode",h.getStatusCode().value());
            responseEntity.put("headers",h.getResponseHeaders());
            responseEntity.put("body",h.getResponseBodyAsString());
            log.error("call getToken HttpClientErrorException:{}",h);
        }catch (Exception e){
            log.error("call getToken Exception:{}",e);
        }

        log.info("call getToken responseEntity:{}",responseEntity);
        return responseEntity;
    }

    @RequestMapping("/getTokenV2")
    public String getTokenV2(String certNo,String certName,String comparisonType,HttpSession session){
        try{
            log.info("call getTokenV2 certNo:{},certName:{},comparisonType:{},TOKEN_URL:{}",certNo,certName,comparisonType,TOKEN_URL);
            session.setAttribute("bizId",System.currentTimeMillis()+"");
            MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
            reqParams.add("api_key",apiKey);
            reqParams.add("api_secret",apiSecret);
            reqParams.add("comparison_type",comparisonType);
            reqParams.add("idcard_name",certName);
            reqParams.add("idcard_number",certNo);
            reqParams.add("uuid", UUID.randomUUID().toString());
            reqParams.add("return_url","http://d9ea3598.ngrok.io/faceId/getTokenReturnUrl");
            reqParams.add("notify_url","http://d9ea3598.ngrok.io/faceId/getTokenNotifyUrl");
            reqParams.add("biz_no",session.getAttribute("bizId").toString());
            reqParams.add("biz_extra_data","test");
            log.info("call getTokenV2 reqParams:{}",reqParams);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(reqParams,headers);
            ResponseEntity<Map> resp  = restTemplate.exchange(TOKEN_URL, HttpMethod.POST,entity,Map.class);
            Map result = resp.getBody();
            return redirect(result.get("token").toString());
        }catch (HttpClientErrorException h){
            log.error("call getTokenV2 HttpClientErrorException:{},body:{}",h,h.getResponseBodyAsString());
        }catch (Exception e){
            log.error("call getTokenV2 Exception:{}",e);
        }

        return "";
    }



    @RequestMapping(value = "/redirect")
    public String redirect(String token){
        log.info("call redirect token:{}",token);
        return "redirect:"+DO_LIVENESS_URL+"?token="+token;
    }


    @RequestMapping(value = "/getTokenReturnUrl")
    public String getTokenReturnUrl(){
        log.info("call getTokenReturnUrl");
        return "result";
    }


    @RequestMapping(value = "/getTokenNotifyUrl")
    @ResponseBody
    public Object getTokenNotifyUrl(@RequestParam Map<String,String> notifyMap){
        log.info("call getTokenNotifyUrl notifyMap:{}",notifyMap);
        return "success";
    }



    @RequestMapping(value = "/getResult")
    public Map getResult(HttpSession session){
        Map responseEntity = new HashMap();
        try{
            MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
            reqParams.add("api_key",apiKey);
            reqParams.add("api_secret",apiSecret);
            reqParams.add("biz_id",session.getAttribute("bizId").toString());
            reqParams.add("return_image","4");
            log.info("call getResult reqParams:{}",reqParams);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(reqParams,headers);
            ResponseEntity<String> resp  = restTemplate.exchange(GET_RESULT, HttpMethod.GET,entity,String.class);
            responseEntity.put("resp",resp);
        }catch (HttpClientErrorException h){
            responseEntity.put("statusCode",h.getStatusCode().value());
            responseEntity.put("headers",h.getResponseHeaders());
            responseEntity.put("body",h.getResponseBodyAsString());
            log.error("call getResult HttpClientErrorException:{}",h);
        }catch (Exception e){
            log.error("call getResult Exception:{}",e);
        }

        log.info("call getResult responseEntity:{}",responseEntity);
        return responseEntity;
    }

}
