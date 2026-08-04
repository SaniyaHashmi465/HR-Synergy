package com.hrs.hrs.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrs.hrs.dto.CandidateDto;
import com.hrs.hrs.dto.ChangePasswordDto;
import com.hrs.hrs.entity.Candidate;
import com.hrs.hrs.repository.CandidateRepo;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepo candidateRepo;

	// ==========================
	// Candidate Registration
	// ==========================

	public void registerCandidate(Candidate candidate, MultipartFile resumeFile) throws Exception {

		String email = candidate.getEmailaddress().trim();

		if (resumeFile.getSize() > 20 * 1024 * 1024) {
			throw new Exception("Resume size must be less than 20 MB.");
		}

		if (candidateRepo.existsByEmailaddress(email)) {
			throw new Exception("Email already exists.");
		}

		String uploadDir = "uploads/resumes/";

		File dir = new File(uploadDir);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		String fileName = System.currentTimeMillis() + "_" + resumeFile.getOriginalFilename();

		Path path = Paths.get(uploadDir, fileName);

		Files.copy(resumeFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		candidate.setEmailaddress(email);
		candidate.setResume(fileName);

		candidateRepo.save(candidate);
	}

	// ==========================
	// Candidate Login
	// ==========================

	public Candidate loginCandidate(String email, String passwd) {

		return candidateRepo.findByEmailaddressAndPasswd(email, passwd);
	}

	// ==========================
	// Get Candidate Entity
	// ==========================

	public Candidate getCandidateByEmail(String email) {

		return candidateRepo.findByEmailaddress(email);
	}

	// ==========================
	// Get Candidate DTO
	// ==========================

	public CandidateDto getCandidateProfile(String email) throws Exception {

		Candidate candidate = candidateRepo.findByEmailaddress(email);

		if (candidate == null) {
			throw new Exception("Candidate not found.");
		}

		CandidateDto dto = new CandidateDto();

		dto.setCandidateid(candidate.getCandidateid());
		dto.setName(candidate.getName());
		dto.setGender(candidate.getGender());
		dto.setEmailaddress(candidate.getEmailaddress());
		dto.setContactno(candidate.getContactno());
		dto.setCity(candidate.getCity());
		dto.setQualification(candidate.getQualification());
		dto.setExperience(candidate.getExperience());
		dto.setKeyskill(candidate.getKeyskill());
		dto.setAddress(candidate.getAddress());
		dto.setResume(candidate.getResume());

		return dto;
	}

	// ==========================
	// Update Candidate Profile
	// ==========================

	public void updateCandidateProfile(CandidateDto dto, String sessionEmail) throws Exception {

		Candidate candidate = candidateRepo.findByEmailaddress(sessionEmail);

		if (candidate == null) {
			throw new Exception("Candidate not found.");
		}

		candidate.setName(dto.getName());
		candidate.setGender(dto.getGender());
		candidate.setContactno(dto.getContactno());
		candidate.setCity(dto.getCity());
		candidate.setQualification(dto.getQualification());
		candidate.setExperience(dto.getExperience());
		candidate.setKeyskill(dto.getKeyskill());
		candidate.setAddress(dto.getAddress());

		candidateRepo.save(candidate);
	}

	public void updateResume(String email, MultipartFile resumeFile) throws Exception {

		Candidate candidate = candidateRepo.findByEmailaddress(email);

		if (candidate == null) {
			throw new Exception("Candidate not found.");
		}

		if (resumeFile.isEmpty()) {
			throw new Exception("Please select a resume.");
		}

		if (resumeFile.getSize() > 20 * 1024 * 1024) {
			throw new Exception("Resume size must be less than 20 MB.");
		}

		String uploadDir = "uploads/resumes/";

		File dir = new File(uploadDir);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		// Delete old resume (if any)
		if (candidate.getResume() != null) {

			File oldFile = new File(uploadDir + candidate.getResume());

			if (oldFile.exists()) {
				oldFile.delete();
			}
		}

		String fileName = System.currentTimeMillis() + "_" + resumeFile.getOriginalFilename();

		Path path = Paths.get(uploadDir, fileName);

		Files.copy(resumeFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		candidate.setResume(fileName);

		candidateRepo.save(candidate);
	}

	public void changePassword(String email, ChangePasswordDto dto) throws Exception {

		Candidate candidate = candidateRepo.findByEmailaddress(email);

		if (candidate == null) {
			throw new Exception("Candidate not found.");
		}

		if (!candidate.getPasswd().equals(dto.getOldPassword())) {
			throw new Exception("Old password is incorrect.");
		}

		if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
			throw new Exception("New password and Confirm password do not match.");
		}

		if (dto.getNewPassword().length() < 6) {
			throw new Exception("Password must contain at least 6 characters.");
		}

		candidate.setPasswd(dto.getNewPassword());

		candidateRepo.save(candidate);
	}

}