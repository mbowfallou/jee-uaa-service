package com.groupeisi;

import com.groupeisi.dao.IAppUserRepository;
import com.groupeisi.dto.AppUser;
import com.groupeisi.entities.AppUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableHystrix
public class UaaServiceApplication {

	@Autowired
	private IAppUserRepository repository;
	@PostConstruct
	public void initUsers(){
		List<AppUserEntity> users = Stream.of(
				new AppUserEntity(101, "MBOW", "Fallou", "", "mbow@gmail.com", "fmbow", "passer123", 1, null),
				new AppUserEntity(102, "DIOP", "Moustapha", "", "diop@gmail.com", "diop1", "passer123", 1, null),
				new AppUserEntity(103, "SECK", "Diama", "", "seck@gmail.com","seck", "passer123", 1, null),
				new AppUserEntity(104, "TALL", "Madicke", "", "tall@gmail.com", "tall", "passer123", 1, null)
		).collect(Collectors.toList());
		repository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(UaaServiceApplication.class, args);
	}

}
