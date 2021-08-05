package com.ms.email.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dto.EmailDTO;
import com.ms.email.model.EmailModel;
import com.ms.email.service.EmailService;

@Component
public class EmailConsumer {
	
	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDTO emailDTO) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDTO, emailModel);
		emailService.sendEmail(emailModel);
		System.out.println("Email Status: " + emailModel.getStatus().toString());
	}
	
}
