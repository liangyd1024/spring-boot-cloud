package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
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
@RequestMapping("/alipay/wap")
@Slf4j
public class WapPayController extends AlipayController{


    @RequestMapping(value = "/pay")
    public String pay(ModelMap modelMap){
        log.info("call wap.pay modelMap:{}",modelMap);
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(buildCallBackUrl("wap/returnUrl"));
        alipayRequest.setNotifyUrl(buildCallBackUrl("wap/notifyUrl"));//在公共参数中设置回跳和通知地址
        String payReqNo = System.nanoTime()+"";
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":"+payReqNo+"," +
                " \"total_amount\":\"88.88\"," +
                " \"subject\":\"Iphone6 16G\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");//填充业务参数
        try {
            log.info("call wap.pay bizContent:{}",alipayRequest.getBizContent());
            String form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            modelMap.addAttribute("form",form);
        } catch (AlipayApiException e) {
            log.error("call wap.pay AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call wap.pay Exception:{}",e);
        }
        return buildPage("page_pay");
    }


    @RequestMapping(value = "/query")
    public String query(String tradeNo,ModelMap modelMap){
        log.info("call query tradeNo:{}",tradeNo);
        try {
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizContent("{" +
                    "\"trade_no\":"+tradeNo+"" +
                    "}");
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            log.info("call query response:{}",BeanMap.create(response));
            modelMap.addAttribute("result", JsonUtils.json2Model(response.getBody(),Map.class));
        } catch (AlipayApiException e) {
            log.error("call query AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call query Exception:{}",e);
        }
        return buildPage("pay_result");
    }


    @RequestMapping(value = "/cancel")
    public String cancel(String tradeNo,ModelMap modelMap){
        log.info("call cancel tradeNo:{}",tradeNo);
        try {
            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
            request.setBizContent("{" +
                    "\"trade_no\":"+tradeNo+"" +
                    "  }");
            AlipayTradeCancelResponse response = alipayClient.execute(request);
            log.info("call cancel response:{}",BeanMap.create(response));
            modelMap.addAttribute("result", JsonUtils.json2Model(response.getBody(),Map.class));
        } catch (AlipayApiException e) {
            log.error("call cancel AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call cancel Exception:{}",e);
        }
        return buildPage("pay_result");
    }


    @RequestMapping(value = "/refund")
    public String refund(String tradeNo,ModelMap modelMap){
        log.info("call refund tradeNo:{}",tradeNo);
        try {
            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
            request.setBizContent("{" +
                    "\"trade_no\":"+tradeNo+"" +
                    "  }");
            AlipayTradeCancelResponse response = alipayClient.execute(request);
            log.info("call refund response:{}",BeanMap.create(response));
            modelMap.addAttribute("result", JsonUtils.json2Model(response.getBody(),Map.class));
        } catch (AlipayApiException e) {
            log.error("call refund AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call refund Exception:{}",e);
        }
        return buildPage("pay_result");
    }


    @RequestMapping(value = "/returnUrl")
    public String returnUrl(@RequestParam Map<String,String> result,ModelMap modelMap){
        log.info("call wap.returnUrl result:{}",result);
        modelMap.addAttribute("result",result);
        return buildPage("pay_result");
    }

    @RequestMapping(value = "/notifyUrl",method = RequestMethod.POST)
    @ResponseBody
    public String notifyUrl(@RequestParam Map<String,String> result){
        log.info("call wap.notifyUrl result:{}",result);
        try {
            boolean check = AlipaySignature.rsaCheckV1(result,alipayConfig.getAlipayPublicKey(),"UTF-8",alipayConfig.getSignType());
            log.info("call wap.notifyUrl check:{}",check);
        } catch (AlipayApiException e) {
            log.error("call wap.notifyUrl AlipayApiException:{}",e);
        } catch (Exception e) {
            log.error("call wap.notifyUrl Exception:{}",e);
        }
        return "success";
    }



}
