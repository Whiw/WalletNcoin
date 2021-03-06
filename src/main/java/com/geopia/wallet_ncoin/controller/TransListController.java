package com.geopia.wallet_ncoin.controller;

import java.net.MalformedURLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geopia.wallet_ncoin.api.dto.TradeListDto;
import org.ripple.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geopia.wallet_ncoin.api.dto.HistoryTransactionResultDto;
import com.geopia.wallet_ncoin.dto.AcoinTransactionsDto;
import com.geopia.wallet_ncoin.mapper.AcoinTransactionMapper;
import com.geopia.wallet_ncoin.util.GsonUTCDateAdapter;
import com.geopia.wallet_ncoin.util.apiTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sn.common.pagination.PagedList;
import com.sn.common.pagination.Pager;
import com.sn.common.pagination.PagingRowBounds;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TransListController {
	
	@Autowired
	AcoinTransactionMapper acointransactionmapper;
	
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();

	
    @RequestMapping("/listtransactions")
    public String getTransListPage(Model model){
		ArrayList<String> addressList;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();
		addressList = acointransactionmapper.getAddress(id);

		model.addAttribute("addressList", addressList);

        return "tiles/default/tradeLog";
    }

	@RequestMapping(value = "/api/getTradeLog", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public 	@ResponseBody ArrayList<TradeListDto> getTransList(@RequestParam("account") String account) {
		ArrayList<TradeListDto> tradeList;

        tradeList = acointransactionmapper.getTradeLog(account);
        return tradeList;
	}

    /*
    @RequestMapping("/tradeLog")
	public String viewSendMoney(Map<String, Object> param, ModelMap  mv, PagingRowBounds bounds, HttpServletRequest request) {
		String current_account = "NHb3CJAWyw4Nj31VRWh36UkukG4b9dtyTs";
		String type = request.getParameter("type") != null ? request.getParameter("type") : "";
		String searchType = request.getParameter("searchType") != null ? request.getParameter("searchType") : "";
		String query = request.getParameter("query") != null ? request.getParameter("query") : "";
		
		PagedList<AcoinTransactionsDto> list = null;
		if (type.compareTo("send") == 0)
		{
			System.out.println(type);
			list = acointransactionmapper.queryPageTransactioninfobyAccountSend(current_account, bounds);
		}
		else {
			System.out.println(type);
			list = acointransactionmapper.queryPageTransactioninfobyAccountReceive(current_account, bounds);
		}
		
		
		Integer pageNo = bounds.getPageNo();
		Integer limitCnt = bounds.getLimit();
		
		Integer seqNoStart = (pageNo * limitCnt) - limitCnt;
		
		
		Integer totalCnt = list.size();
		
		if (totalCnt < limitCnt * pageNo)
		{
			limitCnt = totalCnt % limitCnt;
		}
		for (int i = 0; i < limitCnt; i++) {
			AcoinTransactionsDto dto = list.get(i);
			dto.setRow_num(totalCnt - seqNoStart - i);
		}
		mv.addAttribute("page", list.getPager());
		mv.addAttribute("list", list);
		
		return "tiles/none/tradeLog";
	}
	*/
/*    @RequestMapping("/tradeLog")
   	public String viewSendMoney(Map<String, Object> param, ModelMap  mv, PagingRowBounds bounds, HttpServletRequest request) {
   		String current_account = "NHb3CJAWyw4Nj31VRWh36UkukG4b9dtyTs";
   		String type = request.getParameter("type") != null ? request.getParameter("type") : "";
   		String searchType = request.getParameter("searchType") != null ? request.getParameter("searchType") : "";
   		String query = request.getParameter("query") != null ? request.getParameter("query") : "";
   		
   		PagedList<AcoinTransactionsDto> list = new PagedList<AcoinTransactionsDto>();

   		HashMap map = new HashMap();
		map.put("address", "NHb3CJAWyw4Nj31VRWh36UkukG4b9dtyTs");
		map.put("limit", 20);
		map.put("pageNo", 1);
		
		String ret = null;
		try {
			ret = apiTask.sendApiCall(apiTask.method_history_transaction, map);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HistoryTransactionResultDto dtoa = gson.fromJson(ret, HistoryTransactionResultDto.class);
		System.out.println(dtoa.getList());
		Iterator<AcoinTransactionsDto> k = dtoa.getList().iterator();
		System.out.println("list is " + k.toString());
		while(k.hasNext()) {
			
			AcoinTransactionsDto kk = k.next();
			System.out.println("amount is " + kk.getAmount());
			
			if (type.compareTo("send") == 0 )
			{
				if (kk.getAccount().compareTo(current_account) == 0)
					list.add(kk);
				
			}
			else {
				if (kk.getDestination().compareTo(current_account) == 0)
					list.add(kk);
			}

		}

   		Pager pager = new Pager(dtoa.getPage().getCurrentPage(), dtoa.getPage().getOffset(), dtoa.getPage().getLimit(), dtoa.getPage().getTotalCount());
   		list.setPager(pager);
   		Integer pageNo = bounds.getPageNo();
   		Integer limitCnt = bounds.getLimit();
   		
   		Integer seqNoStart = (pageNo * limitCnt) - limitCnt;
   		
   		
   		Integer totalCnt = list.size();
   		
   		if (totalCnt < limitCnt * pageNo)
   		{
   			limitCnt = totalCnt % limitCnt;
   		}
   		for (int i = 0; i < limitCnt; i++) {
   			AcoinTransactionsDto dto = list.get(i);
   			dto.setRow_num(totalCnt - seqNoStart - i);
   		}
   		mv.addAttribute("page", list.getPager());
   		mv.addAttribute("list", list);
   		
   		return "tiles/none/tradeLog";
   	}
	*/

}
