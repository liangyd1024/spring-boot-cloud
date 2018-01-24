package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.lyd.spring.boot.redis.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/15 ProjectName:spring-boot-cloud Version: 1.0
 */
@Controller
@RequestMapping("/alipay/token")
@Slf4j
public class TokenController  extends AlipayController{


    @RequestMapping(value = "/returnUrl")
    public String getTokenReturnUrl(@RequestParam Map<String,String> notifyMap, ModelMap modelMap, HttpSession session) {
        try {
            log.info("call getTokenReturnUrl notifyMap:{}",notifyMap);

            AlipaySystemOauthTokenResponse oauthTokenResponse = (AlipaySystemOauthTokenResponse) session.getAttribute("oauthTokenResponse");
            if(null == oauthTokenResponse){
                AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
                request.setCode(notifyMap.get("auth_code"));
                request.setGrantType("authorization_code");
                oauthTokenResponse = alipayClient.execute(request);
                log.info("call getTokenReturnUrl oauthTokenResponse:{}", BeanMap.create(oauthTokenResponse));
                session.setAttribute("oauthTokenResponse",oauthTokenResponse);
                session.setAttribute("accessToken",oauthTokenResponse.getAccessToken());
            }


            AlipayUserInfoShareRequest alipayUserInfoShareRequest = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(alipayUserInfoShareRequest,oauthTokenResponse.getAccessToken());
            log.info("call getTokenReturnUrl userinfoShareResponse:{}", BeanMap.create(userinfoShareResponse));

            modelMap.put("oauthTokenResponse",oauthTokenResponse);
            modelMap.put("userinfoShareResponse",userinfoShareResponse);
        }catch (AlipayApiException e){
            log.error("call getTokenReturnUrl AlipayApiException:{}",e);
        }catch (Exception e){
            log.error("call getTokenReturnUrl Exception:{}",e);
        }

        return buildPage("user_info");
    }


    @RequestMapping(value = "/userAuth")
    public String userAuth(ModelMap modelMap) {
        try {
            log.info("call userAuth modelMap:{}",modelMap);
            AlipayUserInfoAuthRequest request = new AlipayUserInfoAuthRequest();
            request.setBizContent("{\"scopes\":\"auth_user\",\"state\":\"xxxxxx\"}");
            AlipayUserInfoAuthResponse response = alipayClient.execute(request);
            log.info("call userAuth response:{}",BeanMap.create(response));

            modelMap.addAttribute("result", JsonUtils.json2Model(response.getBody(),Map.class));

        }catch (AlipayApiException e){
            log.error("call userAuth AlipayApiException:{}",e);
        }catch (Exception e){
            log.error("call userAuth Exception:{}",e);
        }

        return buildPage("pay_result");
    }


}
