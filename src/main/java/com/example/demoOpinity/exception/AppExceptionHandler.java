package com.example.demoOpinity.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// a handler that can process our custtom exception in caser one of our controllers throws it
@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(FileStorageException.class)
	public ModelAndView handleException(FileStorageException exception, RedirectAttributes redirectAttributes) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", exception.getMsg());
		mav.setViewName("error");
		return mav;
	}
}