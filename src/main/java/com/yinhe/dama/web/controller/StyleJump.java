package com.yinhe.dama.web.controller;

import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataCompany;
import com.yinhe.dama.entity.DataUser;
import com.yinhe.dama.service.DataAccountService;
import com.yinhe.dama.service.DataCompanyService;
import com.yinhe.dama.service.DataUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @parameter 模块方法的页面跳转
 * @author lc
 * @date 2020-3-12 16:00:52
 * @version 1.0.1
 * @other 
 */
@Controller
@RequestMapping("/StyleJump")
public class StyleJump{

	@Resource
	DataUserService dataUserService;
	@Resource
	DataAccountService dataAccountService;
	@Resource
	DataCompanyService dataCompanyService;
	static String  main = "/error";
	//用户
	@RequestMapping("/UserJump")
	public String UserJump(HttpServletRequest request , Model model){
		String num = request.getParameter("num");
        if("UserAdd".equals(num)) {
				List<DataCompany> colist = dataCompanyService.selectcoAll();
				model.addAttribute("co",colist);
				main = "/datauser/UserAdd";
        }
		if("UserUpd".equals(num)) {
			String uid = request.getParameter("uid");
			DataUser dataUser = dataUserService.selectUsid(Long.valueOf(uid));
			List<DataCompany> colist = dataCompanyService.selectcoAll();
			model.addAttribute("co",colist);
			model.addAttribute("us",dataUser);
			main = "/datauser/UserUpd";
		}
		return main;
	}
	//账号
	@RequestMapping("/AccJump")
	public String AccJump(HttpServletRequest request , Model model){
		String num = request.getParameter("num");
		if("AccAdd".equals(num)) {
				List<DataUser> dataUsers = dataUserService.selectDausAl();
				model.addAttribute("dau",dataUsers);
				main = "/dataacc/accadd";
		}
		if("AccUpd".equals(num)) {
			String uid = request.getParameter("accid");
			List<DataUser> dataUsers = dataUserService.selectDausAl();
			DataAccount dataAccount = dataAccountService.selectAcid(Long.valueOf(uid));
			DataUser pho = dataUserService.selectUsid(dataAccount.getU_id());
			model.addAttribute("dac",dataAccount);
			model.addAttribute("dau",dataUsers);
			model.addAttribute("pho",pho);
			main = "/dataacc/accupd";
		}
		if("AccEdit".equals(num)) {
			String accid = request.getParameter("accid");
			DataAccount dataAccount = dataAccountService.selectAcid(Long.valueOf(accid));
			if(dataAccount.getModuleo_authority() != null){
				StringBuffer auth =  new StringBuffer(dataAccount.getModuleo_authority());
				if(dataAccount.getModulet_authority() != null){
					auth.append(dataAccount.getModulet_authority());
						if(dataAccount.getOperate_authority() != null){
							auth.append(dataAccount.getOperate_authority());
						}
				}
				model.addAttribute("ac",auth);
				System.err.println(auth);
			}

			main = "/ztree/ztree";
		}


		return main;
	}

	//公司
	@RequestMapping("/CompJump")
	public String CompJump(HttpServletRequest request , Model model){
		String num = request.getParameter("num");

		if("compadd".equals(num)) {
			main = "/datacompany/compadd";
		}
		if("compupd".equals(num)) {
			String uid = request.getParameter("coid");
			DataCompany dataCompany = dataCompanyService.selectCoid(Long.valueOf(uid));
			model.addAttribute("co",dataCompany);
			main = "/datacompany/compupd";
		}
		return main;
	}
}
