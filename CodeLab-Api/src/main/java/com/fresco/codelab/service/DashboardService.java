package com.fresco.codelab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresco.codelab.model.CodeLabRepo;
import com.fresco.codelab.model.CodeLabUser;
import com.fresco.codelab.repo.CodeLabRepoRepository;
import com.fresco.codelab.repo.CodeLabUserRepository;

@Service
@Transactional
public class DashboardService {
	
	@Autowired
	CodeLabUserRepository codeLabUserRepo;
	
	@Autowired
	CodeLabRepoRepository codeLabRepoRepo;
	
	
	public void save(CodeLabUser codeLabUser) {
		codeLabUserRepo.save(codeLabUser);
	}
	
	public CodeLabUser getCodeLabUser(String name, String password) {
		List<CodeLabUser> codeLabUserList = new ArrayList<CodeLabUser>();
		CodeLabUser labUser = null;
		codeLabUserList = codeLabUserRepo.findAll();
		for(CodeLabUser user: codeLabUserList) {
			if(name.equals(user.getUsername()) && password.equals(user.getPassword())) {
				labUser = user;
				break;
			}
		}
		return labUser;
	}
	
	public void save(CodeLabRepo codeLabRepo) {
		codeLabRepoRepo.save(codeLabRepo);
	}
	
	public List<CodeLabRepo> getCodeLabRepoWithOwnerId(Long ownerId){
		List<CodeLabRepo> codeLabRepoList = new ArrayList<CodeLabRepo>();
		codeLabRepoList = codeLabRepoRepo.findAll();
		for(CodeLabRepo repo: codeLabRepoList) {
			if(ownerId.equals(repo.getRepoOwnerId())) {
				codeLabRepoList.add(repo);
			}
		}
		return codeLabRepoList;
	}

	public List<CodeLabRepo> getAllCodeLabRepo(){
		List<CodeLabRepo> codeLabRepoList = codeLabRepoRepo.findAll();
		return codeLabRepoList;
	}
}
