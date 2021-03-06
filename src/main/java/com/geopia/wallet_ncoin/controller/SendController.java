package com.geopia.wallet_ncoin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geopia.wallet_ncoin.domain.AcoinAddressVO;
import com.geopia.wallet_ncoin.mapper.AcoinAddressMapper;
import com.geopia.wallet_ncoin.mapper.AcoinSettingMapper;
import com.geopia.wallet_ncoin.util.apiTask;
import com.google.gson.Gson;
import com.geopia.wallet_ncoin.api.dto.SettingDto;
import com.geopia.wallet_ncoin.api.dto.SubmitResultDto;
import com.geopia.wallet_ncoin.api.dto.SendMoneyDto;
import com.geopia.wallet_ncoin.jsonrpc.dto.AccountDataDto;
import com.geopia.wallet_ncoin.jsonrpc.dto.JsonRPCDto;
import com.geopia.wallet_ncoin.util.ApiResponseCode;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SendController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(apiTask.class);

	@Autowired
	AcoinAddressMapper acoinaddressmapper;
	
	@Autowired
	AcoinSettingMapper acoinsettingmapper;
	
	private Gson gson = new Gson();
	
    @RequestMapping("/send")
    public String getSendPage(Model model){
    	 AcoinAddressVO vo;
 		try {
 			vo = acoinaddressmapper.select("NHb3CJAWyw4Nj31VRWh36UkukG4b9dtyTs");
 			model.addAttribute("address", vo.getAddress());
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
        return "tiles/default/sendMoney";
    }
    
    @RequestMapping(value = "/api/sendCoinWithMsg" , method = RequestMethod.POST)
	public String sendCoinWithMsg(Map<String, Object> paramss, ModelMap  mv, HttpServletRequest request, SendMoneyDto sendParam) {
		
		Exception ex=null;
		try {
			
			HashMap map = new HashMap();
    		
			String tx_blob = apiTask.sign(sendParam.getSender_address(), 
					sendParam.getSendAmount(), 
					sendParam.getRecipient_address(), 
					sendParam.getSecret_key(), 
					sendParam.getTag(), 
					sendParam.getFee());
			map.put("tx_blob", tx_blob);
			
			String ret = apiTask.sendApiCall(apiTask.method_transaction_submit, map);
			mv.put("sendState", gson.fromJson(ret,HashMap.class));
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			ex=e;
		}
		ApiResponseCode.makeResponse(mv, ApiResponseCode.StateCode.SUCCESS, ApiResponseCode.DetailCode.SUCCESS, ex);
		
		return "tiles/default/tradeLog";
	}
    
    @RequestMapping("/signandsubmit")
    public String sendWithMsg(Map<String, Object> paramss, ModelMap  mv, HttpServletRequest request,SendMoneyDto sendParam) {
		
		Exception ex=null;
		try {
			String accountInfo=apiTask.account_info(apiTask.RPCURL, sendParam.getSender_address());
			
			JsonRPCDto dot=gson.fromJson(accountInfo, JsonRPCDto.class);
			AccountDataDto dto=dot.getResult().getAccount_data();
			String Sequence=dto.getSequence();
			
			String resoponse=apiTask.payMents(
							apiTask.RPCURL
							, sendParam.getSender_address()
							, sendParam.getSendAmount()
							, sendParam.getRecipient_address()
							, Sequence
							, sendParam.getSecret_key()
							,sendParam.getTag()
							,sendParam.getFee()
			);
		
			mv.put("sendState", gson.fromJson(resoponse,HashMap.class));
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			ex=e;
		}
		ApiResponseCode.makeResponse(mv, ApiResponseCode.StateCode.SUCCESS, ApiResponseCode.DetailCode.SUCCESS, ex);
		
		return "tiles/default/tradeLog";
	}

	@RequestMapping("/api/getFee")
	public String getFee(Map<String, Object> param, ModelMap  mv, HttpServletRequest request) {
		SettingDto dto;
		try {
			dto = acoinsettingmapper.selectSetting();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("param: {}", dto);
			}
			mv.put("fee", dto.getFee());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		ApiResponseCode.makeResponse(mv, ApiResponseCode.StateCode.SUCCESS, ApiResponseCode.DetailCode.SUCCESS, null);

		return "tiles/default/setting";

	}
    
    
    
}
