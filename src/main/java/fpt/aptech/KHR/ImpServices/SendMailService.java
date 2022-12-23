/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Mail;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.ISendMail;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class SendMailService implements ISendMail{
    @Autowired
    IAccount accountr;
    private final JavaMailSender javaMailSender;

    public SendMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Mail mail) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(mail.getRecipient());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent(), true);
            helper.setSentDate(new Date());
//            ##Nếu muốn thêm attachment thì add dòng dưới này
//            helper.addAttachment("something.jpg", new ClassPathResource("./static/images/something.jpg"));
            javaMailSender.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(SendMailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Mail> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendRecoveryCode(String mail) {
        try {
//            ##Generate recovery code then add it into relevant account
            Account account = this.accountr.findByUserNameUser(mail);
            String code = getRandomNumberString();
            account.setRecovery(code);
            accountr.save(account);
            
//            ##Save mail content to DB
            Mail mailof = new Mail();
            mailof.setRecipient(mail);
            mailof.setSubject("Mã phục hồi tài khoản đăng nhập FPT");
            mailof.setContent("Account code");                      
//            ##Send mail
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(account.getEmail());
            helper.setSubject("Mã phục hồi tài khoản đăng nhập FPT");
            helper.setText("Mã của bạn là: " + account.getRecovery(), true);
            helper.setSentDate(new Date());
            javaMailSender.send(msg);
            

        } catch (MessagingException ex) {
            Logger.getLogger(SendMailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
    
}
