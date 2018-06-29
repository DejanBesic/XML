package com.booking.app.certificates;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.booking.app.model.User;


public class SelfSignedCertificateGenerator {

public SelfSignedCertificateGenerator(String startDate, String endDate, String alias, String pass2, User user, String cn) throws CertIOException{
		
		Security.addProvider(new BouncyCastleProvider());
		
		KeyPair keyPair = generateKeyPair();
		SubjectData subjectData = generateSubjectData(startDate, endDate, keyPair, user, cn);
		IssuerData issuerData = generateIssuerData(keyPair.getPrivate(), user, cn);
		
		CertificateGenerator cg = new CertificateGenerator();
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData, true);
		
		KeyStoreWriter ksw = new KeyStoreWriter();
		String pass = "123456";
		ksw.loadKeyStore("selfSignedKS.jks", pass.toCharArray());
		ksw.write(alias, keyPair.getPrivate(), pass2.toCharArray(), cert, "selfSignedKS"); 
		
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
			//serialNumber++;
			
			//Korisnik user = korisnici.getUlogovan();
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, user.getUsername());
		    builder.addRDN(BCStyle.SURNAME, user.getLastName());
		    builder.addRDN(BCStyle.GIVENNAME, user.getName());
		    builder.addRDN(BCStyle.O, "Booking");
		    builder.addRDN(BCStyle.OU, "BookingAgent");
		    builder.addRDN(BCStyle.C, "SRB");
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
	
	private IssuerData generateIssuerData(PrivateKey issuerKey, User user, String cn) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    builder.addRDN(BCStyle.CN, cn);
	    builder.addRDN(BCStyle.SURNAME, user.getLastName());
	    builder.addRDN(BCStyle.GIVENNAME, user.getName());
	    builder.addRDN(BCStyle.O, "Booking");
	    builder.addRDN(BCStyle.OU, "BookingAgent");
	    builder.addRDN(BCStyle.C, "SRB");
	    builder.addRDN(BCStyle.E, user.getEmail());
	    //UID (USER ID) je ID korisnika
	    builder.addRDN(BCStyle.UID, user.getId().toString());

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(issuerKey, builder.build());
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
}
