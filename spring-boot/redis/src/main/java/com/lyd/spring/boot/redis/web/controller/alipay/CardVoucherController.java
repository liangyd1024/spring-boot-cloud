package com.lyd.spring.boot.redis.web.controller.alipay;

import com.alipay.api.request.AlipayPassInstanceAddRequest;
import com.alipay.api.request.AlipayPassInstanceUpdateRequest;
import com.alipay.api.request.AlipayPassTemplateAddRequest;
import com.alipay.api.request.AlipayPassTemplateUpdateRequest;
import com.alipay.api.response.AlipayPassInstanceAddResponse;
import com.alipay.api.response.AlipayPassInstanceUpdateResponse;
import com.alipay.api.response.AlipayPassTemplateAddResponse;
import com.alipay.api.response.AlipayPassTemplateUpdateResponse;
import com.lyd.spring.boot.redis.web.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：
 * <p>
 * #
 * </p>
 *
 * @author: lyd Date: 2018/1/17 ProjectName:spring-boot-cloud Version: 1.0
 */
@Controller
@RequestMapping(value = "/alipay/cardVoucher",method = {RequestMethod.GET,RequestMethod.POST})
@Slf4j
public class CardVoucherController extends AlipayController{


    @RequestMapping("/template/add")
    @ResponseBody
    public Object addTemplate(){
        log.info("call template add ");
        try {
            AlipayPassTemplateAddRequest request = new AlipayPassTemplateAddRequest();
            String bizContent = JsonUtils.file2json(classPath+("data/addCardVoucherTemplate.json"));
            request.setBizContent(bizContent);
            AlipayPassTemplateAddResponse response = alipayClient.execute(request);
            log.info("call template add response:{}", BeanMap.create(response));
            return BeanMap.create(response);
        } catch (Exception e) {
            log.error("call template add Exception:{}",e);
        }

        return null;
    }


    @RequestMapping("/template/update")
    @ResponseBody
    public Object updateTemplate(){
        log.info("call template update ");
        try {
            AlipayPassTemplateUpdateRequest request = new AlipayPassTemplateUpdateRequest();
            String bizContent = JsonUtils.file2json(classPath+("data/updateCardVoucherTemplate.json"));
            request.setBizContent(bizContent);
            AlipayPassTemplateUpdateResponse response = alipayClient.execute(request);
            log.info("call template update response:{}", BeanMap.create(response));
            return BeanMap.create(response);
        } catch (Exception e) {
            log.error("call template update Exception:{}",e);
        }

        return null;
    }


    @RequestMapping("/add")
    @ResponseBody
    public Object add(){
        log.info("call card_voucher add ");
        try {
            AlipayPassInstanceAddRequest request = new AlipayPassInstanceAddRequest();
            String bizContent = JsonUtils.file2json(classPath+("data/addCardVoucher.json"));
            request.setBizContent(bizContent);
            AlipayPassInstanceAddResponse response = alipayClient.execute(request);
            log.info("call card_voucher add response:{}", BeanMap.create(response));
            return BeanMap.create(response);
        } catch (Exception e) {
            log.error("call card_voucher add Exception:{}",e);
        }

        return null;
    }


    @RequestMapping("/update")
    @ResponseBody
    public Object update(){
        log.info("call card_voucher update ");
        try {
            AlipayPassInstanceUpdateRequest request = new AlipayPassInstanceUpdateRequest();
            String bizContent = JsonUtils.file2json(classPath+("data/updateCardVoucher.json"));
            request.setBizContent(bizContent);
            AlipayPassInstanceUpdateResponse response = alipayClient.execute(request);
            log.info("call card_voucher update response:{}", BeanMap.create(response));
            return BeanMap.create(response);
        } catch (Exception e) {
            log.error("call card_voucher update Exception:{}",e);
        }

        return null;
    }
}
