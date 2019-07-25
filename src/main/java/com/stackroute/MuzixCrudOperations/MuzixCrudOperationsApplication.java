package com.stackroute.MuzixCrudOperations;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class MuzixCrudOperationsApplication implements ApplicationListener<ContextRefreshedEvent> , CommandLineRunner

{
	@Value("${id}")
	private int id;
	@Value("${name}")
	private String name;
	@Value("${comment}")
	private String comment;

	@Autowired
	TrackRepository trackRepository;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(MuzixCrudOperationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{

		trackRepository.save(new Track(id,name,comment));

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
	{

		trackRepository.save(new Track(Integer.parseInt(environment.getProperty("id")),environment.getProperty("name"),environment.getProperty("comment")));
	}
}
