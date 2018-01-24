package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
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
@RequestMapping("/alipay/f2f")
@Slf4j
public class Face2FacePayController extends AlipayController{


    @RequestMapping(value = "/precreate")
    public String precreate(ModelMap modelMap){
        log.info("call f2f.precreate modelMap:{}",modelMap);
        try {
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();//创建API对应的request类
            request.setReturnUrl(buildCallBackUrl("wap/returnUrl"));
            request.setNotifyUrl(buildCallBackUrl("wap/notifyUrl"));//在公共参数中设置回跳和通知地址
            request.setBizContent("{" +
                    "    \"out_trade_no\":"+System.nanoTime()+"," +
                    "    \"total_amount\":\"88.88\"," +
                    "    \"subject\":\"Iphone6 16G\"," +
                    "    \"store_id\":\"NJ_001\"," +
                    "    \"timeout_express\":\"90m\"}");//设置业务参数
            log.info("call f2f.precreate bizContent:{}",request.getBizContent());
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            log.info("call f2f.precreate response:{}", BeanMap.create(response));
            modelMap.addAttribute("qrCode",response.getQrCode());
        } catch (AlipayApiException e) {
            log.error("call f2f.precreate AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call f2f.precreate Exception:{}",e);
        }
        return buildPage("pay_result");
    }


    @RequestMapping(value = "/notifyUrl",method = RequestMethod.POST)
    @ResponseBody
    public String notifyUrl(@RequestParam Map<String,String> result){
        log.info("call f2f.notifyUrl result:{}",result);
        try {
            boolean check = AlipaySignature.rsaCheckV1(result,alipayConfig.getAlipayPublicKey(),"UTF-8",alipayConfig.getSignType());
            log.info("call f2f.notifyUrl check:{}",check);
        } catch (AlipayApiException e) {
            log.error("call f2f.notifyUrl AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call f2f.notifyUrl Exception:{}",e);
        }
        return "success";
    }



}
