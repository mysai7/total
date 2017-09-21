package org.itbank.app.controllers;

import java.util.UUID;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	JavaMailSender sender;
	
	@RequestMapping("/basic")
	public String basicHandle() {
		return "test/basic";
	}
	
	@RequestMapping("uuid")
	public void uuidHandle() {
		UUID u = UUID.randomUUID();	// ���� �Ǻ��ڰ� �ʿ��ϰ� �ɶ� ���Ǵ� Ŭ����
		// ������ ��Ģ�� ���� ���� �Ϻ��ϰ� �ߺ����� �ʴ� ���� Ű�� �����ؼ� ����� �� ����
		// 32���� 16����..
		System.out.println(Math.pow(16, 32));
		System.out.println(u.toString());
		String auth_str = u.toString().substring(0, 13);
		System.out.println(auth_str);
	}
	
	@RequestMapping("/mail01")
	public void sendMail01Handle(@RequestParam(name="to") String to) {
		// 1. SimpleMailMessage ��ü �̿�
		SimpleMailMessage msg = new SimpleMailMessage();
		// �߽���, ������, ����
		try {
			msg.setTo(to);	// ������� �ּ�
			msg.setFrom("itbanksaan@gmail.com");	// ������ ��� �ּ�.
			// smtp.gmail ���� ��ȸ�ؼ� ���۵ɶ�, ������ ��� �ּҰ�, gmail �ּҷ� �ڵ� ��ȯ
			msg.setSubject("�׽�Ʈ �����Դϴ�.");	// ����
			String text = "ȯ���մϴ�.\n�׻� �ּ��� ���񽺸� �����ϰڽ��ϴ�.";
			msg.setText(text);	// TEXT �� ���� ����
			
			sender.send(msg);	// �߼�
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/mail02")
	public void sendMail02Handle(@RequestParam(name="to") String to) {
		// MimeMessage
		try {
			MimeMessage msg = sender.createMimeMessage();
			// TO
			msg.setRecipient(RecipientType.TO, new InternetAddress(to));
			// SUBJECT
			msg.setSubject("WELCOME");
			// TEXT
			String text = "<h1>ȯ�� �մϴ�~!</h1>";
			text += "������ ȯ���մϴ�.";
			text += "<a href=\"http://192.168.10.66\">����Ʈ �̵�</a>";
			
			msg.setText(text, "UTF-8", "html");
			// FROM
			
			sender.send(msg);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
