package com.example.producingwebservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

import io.spring.guides.gs_producing_web_service.Company;
import io.spring.guides.gs_producing_web_service.Contact;
import io.spring.guides.gs_producing_web_service.Package;

@Component
public class PackageRepository {
	
	private static final List<Package> packages = new ArrayList<>();
	
	private final DateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");

	@PostConstruct
	public void initData() throws DatatypeConfigurationException, ParseException {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(Calendar.getInstance().getTime());
		XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		
		Contact netflixContact = new Contact();
		netflixContact.setEmail("support@netflix.com");
		netflixContact.setMsisdn("+255910000000");
		
		Company netflixCompany = new Company();
		netflixCompany.setContact(netflixContact);
		netflixCompany.setCreatedAt(date);
		netflixCompany.setLocation("EUA, California ...");
		netflixCompany.setName("Netflix");
		
		
		Package netflix = new Package();
		netflix.setId(123);
		netflix.setName("Netflix standard package");
		netflix.setDescription("Netflix description");
		netflix.setPrice(BigDecimal.valueOf(19.99).setScale(2, RoundingMode.HALF_DOWN));
		netflix.setCurrency("TZS");
		netflix.setLoyaltyPeriod(5);
		netflix.setIcon("https://www.iconfinder.com/icons/4375011/logo_netflix_icon");
		Date dateStart = format.parse("20140424111500");
		GregorianCalendar dateStartGregorianCalendar = new GregorianCalendar();
		dateStartGregorianCalendar.setTime(dateStart);
		XMLGregorianCalendar dateStartXMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateStartGregorianCalendar);
		netflix.setStartDate(dateStartXMLGregorianCalendar);
		netflix.setEndDate(date);
		netflix.setCompany(netflixCompany);
		
		
		packages.add(netflix);
		
		Contact disneyPlusContact = new Contact();
		disneyPlusContact.setEmail("support@disney.com");
		disneyPlusContact.setMsisdn("+25591");
		
		Company disneyPlusCompany = new Company();
		disneyPlusCompany.setContact(disneyPlusContact);
		disneyPlusCompany.setCreatedAt(date);
		disneyPlusCompany.setLocation("EUA ...");
		disneyPlusCompany.setName("disneyPlus");
		
		Package disneyPlus = new Package();
		disneyPlus.setId(124);
		disneyPlus.setName("Disney standard package");
		disneyPlus.setDescription("Disney description");
		disneyPlus.setPrice(BigDecimal.valueOf(9.99).setScale(2, RoundingMode.HALF_DOWN));
		disneyPlus.setCurrency("TZS");
		disneyPlus.setLoyaltyPeriod(10);
		disneyPlus.setIcon("https://icons8.com.br/icons/set/disney-plus");
		disneyPlus.setStartDate(dateStartXMLGregorianCalendar);
		disneyPlus.setEndDate(date);
		disneyPlus.setCompany(disneyPlusCompany);
		
		packages.add(disneyPlus);
		
		Contact hboContact = new Contact();
		hboContact.setEmail("support@hbo.com");
		hboContact.setMsisdn("MSISDN");
		
		Company hboCompany = new Company();
		hboCompany.setContact(hboContact);
		hboCompany.setCreatedAt(date);
		hboCompany.setLocation("EUA ...");
		hboCompany.setName("HBO");
		
		Package hbo = new Package();
		hbo.setId(125);
		hbo.setName("Disney standard package");
		hbo.setDescription("Disney description");
		hbo.setPrice(BigDecimal.valueOf(14.99).setScale(2, RoundingMode.HALF_DOWN));
		hbo.setCurrency("TZS");
		hbo.setLoyaltyPeriod(0);
		hbo.setIcon("https://icons8.com.br/icons/set/hbo");
		hbo.setStartDate(dateStartXMLGregorianCalendar);
		hbo.setEndDate(date);
		hbo.setCompany(hboCompany);
		
		packages.add(hbo);
	}

	public List<Package> findPackages() {
		return packages;
	}
}
