package com.booking.app.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

import org.bouncycastle.cert.CertIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booking.app.DTOs.CertificateDTO;
import com.booking.app.DTOs.NewCertificateDTO;
import com.booking.app.annotations.PermissionAnnotation;
import com.booking.app.certificates.KeyStoreReader;
import com.booking.app.certificates.RegularCertificateGenerator;
import com.booking.app.certificates.SelfSignedCertificateGenerator;
import com.booking.app.model.User;
import com.booking.app.repository.UserRepository;


@RestController
@RequestMapping("/api/cert")
public class CertificateController {

	@Autowired
    UserRepository userRepository;
	
    @PermissionAnnotation(name = "SELF_SIGNED_CERTIFICATE")
    @CrossOrigin
	@RequestMapping(value="/selfSignedCertificate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> generateSelfSignedCertificate(@RequestBody CertificateDTO cert) throws CertIOException{
		
		//User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		User user = userRepository.findByUsername("mario");
		if(user==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		SelfSignedCertificateGenerator cg = new SelfSignedCertificateGenerator(cert.getStartDate(), cert.getEndDate(),
				cert.getAlias(), cert.getPassword(), user, cert.getCn());

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @PermissionAnnotation(name = "NEW_CERTIFICATE")
    @CrossOrigin
	@RequestMapping(value="/newCertificate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> generateNewCertificate(@RequestBody NewCertificateDTO cert) throws IOException{
		
		//User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		User user = userRepository.findByUsername("mario");
		
//		User subject = userRepository.findByUsername(cert.getSubjectUsername());
		User subject = userRepository.findByUsername("stefan");
		
		
		
		RegularCertificateGenerator cer = new RegularCertificateGenerator(cert.getCn(), cert.getNewCertPass(), cert.getStartDate(),
				cert.getEndDate(), cert.getNewCertAlias(), cert.getIssuerPass(), cert.getIssuerAlias(), subject, user, true);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @PermissionAnnotation(name = "REVOKE_CERTIFICATE")
    @CrossOrigin
	@RequestMapping(value="/revoke", method = RequestMethod.POST)
	public void revokeCertificate(@RequestBody String s) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStoreReader ksr = new KeyStoreReader();
		X509Certificate cert = ksr.getCertificateBySN(new BigInteger(s));
		if(cert != null){
			try {
			Writer output;
			output = new BufferedWriter(new FileWriter("revoked.txt", true));  //clears file every time
			output.append(s + System.lineSeparator());
			output.close();
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			output = new BufferedWriter(new FileWriter("revoked.txt", true));  //clears file every time
			output.append(timeStamp + System.lineSeparator());
			output.close();
			    
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	}
}
