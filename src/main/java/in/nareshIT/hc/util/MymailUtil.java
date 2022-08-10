package in.nareshIT.hc.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MymailUtil {

	@Autowired
	private JavaMailSender mailSender;

	public boolean send(
			String to[],
			String cc[],
			String bbc[],
			String subject,
			String text,
			Resource file[]
			) { 
		boolean sent=false;
		try {
			MimeMessage massage=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(massage,file!=null && file.length>0);
			helper.setTo(to);
			if(cc!=null) helper.setCc(cc);
			if(bbc!=null) helper.setBcc(text);
			helper.setText(text);
			helper.setSubject(subject);
			if(file!=null) {
				for(Resource ob:file) {
					helper.addAttachment(ob.getFilename(), ob); 
				}
			}
			mailSender.send(massage);
			sent=true;

		} catch (Exception e) {
			e.printStackTrace();
		}	
		return sent;
	}

	public boolean send(
			String to,
			String subject,
			String text,
			Resource file
			) { 

		return send(new String[] {to},null,null,subject,text,file!=null?new Resource[] {file}:null);
	}

	public boolean send(
			String to,
			String subject,
			String text
			) { 

		return send(new String[] {to},null,null,subject,text,null);
	}



}
