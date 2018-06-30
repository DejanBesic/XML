package com.booking.app.certificates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.booking.app.model.User;

public class RegularCertificateGenerator {
	
	private X509Certificate newCert;
	
	public RegularCertificateGenerator( String cn, String pass3, String startDate, String endDate,
			String alias, String pass2, String issuerAlias, User subject, User user, boolean ca) throws IOException{
		
		Security.addProvider(new BouncyCastleProvider());
		KeyPair keyPair = generateKeyPair();
		SubjectData subjectData = generateSubjectData(startDate, endDate, keyPair, subject, cn);
		
		KeyStoreReader ksr = new KeyStoreReader();
		
		String issuerKS = "selfSignedKS";
		X509Certificate cert = (X509Certificate) ksr.readCertificate(issuerKS + ".jks", "123456", issuerAlias);
		if(cert==null){
			issuerKS = "caCertsKS";
			cert = (X509Certificate) ksr.readCertificate(issuerKS + ".jks", "123456", issuerAlias);
		}
		if(cert==null){
			return;
		}
		if(cert.getBasicConstraints() != -1 && !isRevoked(cert)){
			IssuerData issuerData = ksr.readIssuerFromStore(issuerKS + ".jks", issuerAlias, "123456".toCharArray(), pass2.toCharArray());
			CertificateGenerator cg = new CertificateGenerator();
			
			//Korisnik user = korisnici.getKorisnici().get(subject);
			
			
			newCert = cg.generateCertificate(subjectData, issuerData, ca);
			KeyStoreWriter ksw = new KeyStoreWriter();
			String pass = "123456";
			String ks;
			if(ca){
				ks="caCertsKS";
			}else{
				ks="userCertsKS";
			}
			
			ksw.loadKeyStore(ks+".jks", pass.toCharArray());
			
			
			ksw.write(alias, issuerData.getPrivateKey(), pass3.toCharArray(), newCert, ks);
		}
		
	}
	
	private boolean isRevoked(X509Certificate cert) throws IOException {
		// TODO Auto-generated method stub
		String s = cert.getSerialNumber().toString();
		if(cert != null){
			BufferedReader br = new BufferedReader(new FileReader("revoked.txt"));  
			String line = null;  
			boolean tmp = true;
			while ((line = br.readLine()) != null)  
			{  
			   if(tmp){
				   if(line.equals(s))
					   return true;
			   }
			   tmp = !tmp;
			} 
		}
		return false;
	}

	private SubjectData generateSubjectData(String startDate1, String endDate1, KeyPair keyPair, User user, String cn){
		try {
			KeyPair keyPairSubject = keyPair;
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse(startDate1);
			Date endDate = iso8601Formater.parse(endDate1);
			
			//Serijski broj sertifikata
			Random rnd = new Random();
			int serialNumber = 100000 + rnd.nextInt(900000);
			String sn=Integer.toString(serialNumber);
			
			//Korisnik user = korisnici.getKorisnici().get(subject);
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, cn);
		    builder.addRDN(BCStyle.SURNAME, user.getLastName());
		    builder.addRDN(BCStyle.GIVENNAME, user.getName());
		    builder.addRDN(BCStyle.O, "Booking");
		    builder.addRDN(BCStyle.OU, "Booking");
		    builder.addRDN(BCStyle.C, user.getAddress());
		    builder.addRDN(BCStyle.E, user.getEmail());
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, user.getId().toString());
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private KeyPair generateKeyPair() {
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        return null;
	}

	public X509Certificate getNewCert() {
		return newCert;
	}

	public void setNewCert(X509Certificate newCert) {
		this.newCert = newCert;
	}
}
