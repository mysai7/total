package org.itbank.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/style")
public class CssController {
	
	@RequestMapping("/01")
	public void css01Handle() {
		// ���Ͽ� ���̸��� �������ϸ�, css/01
		// ==> InternalResourceViewResolver �� ���ؼ� �ؼ��ǰ�
	}
}
