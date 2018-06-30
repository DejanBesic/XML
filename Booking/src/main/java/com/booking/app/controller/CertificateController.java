package com.booking.app.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.cert.CertIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	@RequestMapping(value="/selfSignedCertificate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> generateSelfSignedCertificate(@RequestBody CertificateDTO cert) throws CertIOException{
		
		User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		//User user = userRepository.findByUsername("mario");
		if(user==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		SelfSignedCertificateGenerator cg = new SelfSignedCertificateGenerator(cert.getStartDate(), cert.getEndDate(),
				cert.getAlias(), cert.getPassword(), user, cert.getCn());

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @PermissionAnnotation(name = "NEW_CERTIFICATE")
	@RequestMapping(value="/newCertificate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> generateNewCertificate(@RequestBody NewCertificateDTO cert) throws IOException{
		
		User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//		User user = userRepository.findByUsername("mario");
		
		User subject = userRepository.findByUsername(cert.getSubjectUsername());
		//User subject = userRepository.findByUsername("stefan");
		
		if(user==null || subject==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		RegularCertificateGenerator cer = new RegularCertificateGenerator(cert.getCn(), cert.getNewCertPass(), cert.getStartDate(),
				cert.getEndDate(), cert.getNewCertAlias(), cert.getIssuerPass(), cert.getIssuerAlias(), subject, user, true);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @PermissionAnnotation(name = "REVOKE_CERTIFICATE")
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
			
//			File source = new File("/revoked.txt");
//			File dest = new File(getClass().getResource("/revoked.txt").getFile());
//			
//			
//			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		}
	}
    
    @RequestMapping(value="/newUserCertificate", method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> generateNewUserCertificate(@RequestBody NewCertificateDTO cert) throws IOException{
		
		User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//		User user = userRepository.findByUsername("mario");
		
		User subject = userRepository.findByUsername(cert.getSubjectUsername());
		//User subject = userRepository.findByUsername("stefan");
		
		if(user==null || subject==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		
		RegularCertificateGenerator cer = new RegularCertificateGenerator(cert.getCn(), cert.getNewCertPass(), cert.getStartDate(),
				cert.getEndDate(), cert.getNewCertAlias(), cert.getIssuerPass(), cert.getIssuerAlias(), subject, user, false);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
    
    
    @RequestMapping(value="/isRevoked", method = RequestMethod.POST)
	public ResponseEntity<?> isRevoked(@RequestBody String s) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStoreReader ksr = new KeyStoreReader();
		X509Certificate cert = ksr.getCertificateBySN(new BigInteger(s));
		if(cert != null){
//			ClassLoader classLoader = getClass().getClassLoader();
//			BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/revoked.txt")));  
			BufferedReader br = new BufferedReader(new FileReader("revoked.txt")); 
			String line = null;  
			boolean tmp = true;
			while ((line = br.readLine()) != null)  
			{  
			   if(tmp){
				   if(line.equals(s))
					   return new ResponseEntity<>(true, HttpStatus.OK);
			   }
			   tmp = !tmp;
			} 
			//br.close();
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	   return new ResponseEntity<>(false, HttpStatus.OK);
	}
    
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> downloadCertificate(@PathVariable BigInteger id) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException{
		KeyStoreReader ksr = new KeyStoreReader();
		X509Certificate cert = ksr.getCertificateBySN(id);
		
		if(cert==null)
			return new ResponseEntity<>(HttpStatus.OK);
		
		FileUtils.writeByteArrayToFile(new File("C:\\Users\\Public\\cert" + id.toString() + ".crt"), cert.getEncoded());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
