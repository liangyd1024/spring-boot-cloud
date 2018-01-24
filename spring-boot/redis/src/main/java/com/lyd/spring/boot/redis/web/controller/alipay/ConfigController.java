package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayOpenAuthTokenAppQueryRequest;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppQueryResponse;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.lyd.spring.boot.redis.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/18 ProjectName:spring-boot-cloud Version: 1.0
 */
@Controller
@RequestMapping("/alipay/config")
@Slf4j
public class ConfigController extends AlipayController{


    @RequestMapping(value = "/notifyUrl")
    public String notifyUrl(@RequestParam Map<String,String> notifyMap,
                            HttpSession session,
                            ModelMap modelMap){
        log.info("call config.notifyUrl notifyMap:{}",notifyMap);
        try {
            AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
            request.setBizContent("{" +
                    "    \"grant_type\":\"authorization_code\"," +
                    "    \"code\":"+notifyMap.get("app_auth_code")+"" +
                    "  }");
            AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
            log.info("call config.notifyUrl response:{}", BeanMap.create(response));

            session.setAttribute("app_auth_code",notifyMap.get("app_auth_code"));
            modelMap.addAttribute("result", JsonUtils.json2Model(response.getBody(),Map.class));

        } catch (AlipayApiException e) {
            log.error("call config.notifyUrl AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call config.notifyUrl Exception:{}",e);
        }
        return buildPage("pay_result");
    }


    @RequestMapping(value = "/query")
    public String query(@RequestParam Map<String,String> notifyMap,
                        HttpSession session,
                        ModelMap modelMap){
        log.info("call config.query notifyMap:{}",notifyMap);
        try {
            AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
            request.setBizContent("{" +
                    "    \"app_auth_token\":"+session.getAttribute("app_auth_code")+"" +
                    "  }");
            AlipayOpenAuthTokenAppQueryResponse response = alipayClient.execute(request);
            log.info("call config.query response:{}", BeanMap.create(response));

            modelMap.addAttribute("result", JsonUtils.json2Model(response.getBody(),Map.class));

        } catch (AlipayApiException e) {
            log.error("call config.query AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call config.query Exception:{}",e);
        }
        return buildPage("pay_result");
    }

}
