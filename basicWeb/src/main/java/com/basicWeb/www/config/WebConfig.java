package com.basicWeb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	// EncodingFilter 설정
	@Override
	protected Filter[] getServletFilters() {
		// filter 설정
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true); //외부로 보내는 데이터도 인코딩 설정
		return new Filter[] {encoding};
	}
	
	// 그 외 기타 사용자 설정 (개발자가 직접 추가하는 것)
	/* 사용자 지정 Exception 처리 지정
	 * -> 404와 같은 Exception 처리 페이지 꾸미기 -> 나중에 설정
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		String uploadLocation = "D:\\anzy\\_myProject\\_java\\_fileUpload";
		int maxFileSize = 1024 * 1024 * 20;
		int maxReSize = maxFileSize*2;
		int fileSizeThreshold = maxFileSize;
		
		MultipartConfigElement multipartConfig =
				new MultipartConfigElement(uploadLocation, maxFileSize, maxReSize, fileSizeThreshold);
		
		registration.setMultipartConfig(multipartConfig);
	}
	
	

}
