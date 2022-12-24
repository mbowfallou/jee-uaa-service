package com.groupeisi.service;

import com.groupeisi.dao.IAppUserRepository;
import com.groupeisi.dto.AppUser;
import com.groupeisi.exception.EntityNotFoundException;
import com.groupeisi.exception.RequestException;
import com.groupeisi.mapping.AppUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class AppUserService {
	
	private IAppUserRepository appUserRepository;
	private AppUserMapper appUserMapper;
	private MessageSource messageSource;

	// Get All Users
	@Transactional(readOnly = true)
	public List<AppUser> getAppUsers() {
		int id = 0;
		return StreamSupport.stream(Optional.ofNullable(appUserRepository.findAll()).orElseThrow(() ->
								new EntityNotFoundException(messageSource.getMessage("appUser.notfound", new Object[]{id}, Locale.getDefault())))
						.spliterator(), false)
				.map(appUserMapper::toAppUser)
				.collect(Collectors.toList());
	}

	// Get One User By his ID
	@Transactional(readOnly = true)
	public AppUser getAppUser(Integer id) {
		 return appUserMapper.toAppUser(appUserRepository.findById(id)
				 .orElseThrow(() -> 
				 new EntityNotFoundException(messageSource.getMessage("appUserId.notfound", new Object[]{id},
						 Locale.getDefault()))));
	}

	// Get One User By his Email
	@Transactional(readOnly = true)
	public AppUser getAppUserByEmail(String email) {
		return appUserMapper.toAppUser(Optional.ofNullable(appUserRepository.findByEmail(email))
				.orElseThrow(() ->
						new EntityNotFoundException(messageSource.getMessage("appUserEmail.notfound", new Object[]{email},
								Locale.getDefault()))));

	}

	@Transactional(readOnly = true)
	public List<AppUser> getAppUserByLastname(String lastname) {
		return StreamSupport.stream(Optional.ofNullable(appUserRepository.findByNom(lastname)).orElseThrow(() ->
						new EntityNotFoundException(messageSource.getMessage("appUserNom.notfound", new Object[]{lastname}, Locale.getDefault())))
						.spliterator(), false)
				.map(appUserMapper::toAppUser)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<AppUser> getAppUserByFirstname(String firstname) {
		return StreamSupport.stream(Optional.ofNullable(appUserRepository.findByPrenom(firstname)).orElseThrow(() ->
								new EntityNotFoundException(messageSource.getMessage("appUserPrenom.notfound", new Object[]{firstname}, Locale.getDefault())))
						.spliterator(), false)
				.map(appUserMapper::toAppUser)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<AppUser> getAppUserByPrenomAndNom(String firstname, String lastname) {
		return StreamSupport.stream(Optional.ofNullable(appUserRepository.findByPrenomAndNom(firstname, lastname))
				.orElseThrow(() -> new EntityNotFoundException(
						messageSource.getMessage("appUserPrenomNom.notfound", new Object[]{firstname, lastname}, Locale.getDefault())
					)
				)
				.spliterator(), false)
				.map(appUserMapper::toAppUser)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public AppUser getAppUserByEmailAndPassword(String email, String password) {
		return appUserMapper.toAppUser(Optional.ofNullable(appUserRepository.findByEmailAndPassword(email, password))
			.orElseThrow(() ->
				new EntityNotFoundException(messageSource.getMessage("appUserEmail.notfound", new Object[]{email},
						Locale.getDefault()
					)
				)
			)
		);
	}
	
	@Transactional
	public AppUser createAppUser(AppUser AppUser) {
		return appUserMapper.toAppUser(appUserRepository.save(appUserMapper.fromAppUser(AppUser)));
	}
	
	@Transactional
	public AppUser updateAppUser(Integer id, AppUser AppUser) {
		return appUserRepository.findById(id)
				.map(entity -> {
					AppUser.setId(id);
					return appUserMapper.toAppUser(appUserRepository.save(appUserMapper.fromAppUser(AppUser)));
				})
				.orElseThrow(
						() -> new EntityNotFoundException(messageSource.getMessage("appUser.notfound", new Object[]{id}, Locale.getDefault()))
				);
	}
	
	@Transactional
	public void deleteAppUser(Integer id) {
		try {
			appUserRepository.deleteById(id);
		} catch (Exception e) {
			throw new RequestException(messageSource.getMessage("appUser.errordeletion", new Object[] {id},
					Locale.getDefault()), HttpStatus.CONFLICT);
		}
	}

}
