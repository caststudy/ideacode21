package com.onlyboot.controller;


import com.onlyboot.service.FldService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController // 相当于 @Controller + @ResponseBody   所有请求返回json格式数据
@RequestMapping("/fld")
public class FldController {
	Logger log = Logger.getLogger(FldController.class);
	@Autowired
	FldService fldService;


	@RequestMapping()
	public List select(@RequestParam Map map) throws IOException {
		List lst = fldService.getAll(map);
		log.debug(lst.size());
		return lst;
	}

	@RequestMapping("/check/{id}")
	public String check(@PathVariable int id) throws IOException {
		if (fldService.isExist(id)) {
			return "exist";
		} else {
			return "not";
		}
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(HttpServletRequest req, @RequestParam Map map, MultipartFile picFile)
			throws IOException {
        log.debug(map);
		if (!picFile.isEmpty()) {
//			String sPath = req.getServletContext().getRealPath("/");
//			sPath = sPath + "/upimgs/";
			String fileName = map.get("flda") + "_.png";
			picFile.transferTo(new File(fileName));
		}

		if (fldService.insertFld(map)) {
			return "success";
		} else {
			return "failure";
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable int id) throws IOException {

		if (fldService.deleteFld(id)) {
			return "success";
		} else {
			return "failure";
		}
	}

}
