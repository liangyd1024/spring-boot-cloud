package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lyd.spring.boot.redis.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/alipay/web")
@Slf4j
public class WebPayController extends AlipayController{


    @RequestMapping(value = "/pay")
    public String pay(ModelMap modelMap){
        log.info("call web.pay modelMap:{}",modelMap);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(buildCallBackUrl("web/returnUrl"));
        alipayRequest.setNotifyUrl(buildCallBackUrl("web/notifyUrl"));//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":"+System.nanoTime()+"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088102169743753\"" +
                "    }"+
                "  }");//填充业务参数
        try {
            String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            modelMap.addAttribute("form",form);
        } catch (AlipayApiException e) {
            log.error("call web.pay AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call web.pay Exception:{}",e);
        }
        return buildPage("page_pay");
    }


    @RequestMapping(value = "/returnUrl")
    public String returnUrl(@RequestParam Map<String,String> result,ModelMap modelMap){
        log.info("call web.returnUrl result:{}",result);
        modelMap.addAttribute("result",result);
        return buildPage("pay_result");
    }

    @RequestMapping(value = "/notifyUrl",method = RequestMethod.POST)
    @ResponseBody
    public String notifyUrl(@RequestParam Map<String,String> result){
        log.info("call web.notifyUrl result:{}",result);
        try {
            boolean check = AlipaySignature.rsaCheckV1(result,alipayConfig.getAlipayPublicKey(),"UTF-8",alipayConfig.getSignType());
            log.info("call web.notifyUrl check:{}",check);
        } catch (AlipayApiException e) {
            log.error("call web.notifyUrl AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call web.notifyUrl Exception:{}",e);
        }
        return "success";
    }



}
