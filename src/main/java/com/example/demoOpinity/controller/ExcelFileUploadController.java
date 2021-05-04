package com.example.demoOpinity.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demoOpinity.storage.StorageFileNotFoundException;
import com.example.demoOpinity.storage.StorageService;

@Controller
@RequestMapping("/")
public class ExcelFileUploadController {

	private final StorageService storageService;

	@Autowired
	public ExcelFileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {

		// add the files as an attribute to the model/webpage as a attribute so they can
		// be show this is done by loading all files from the storageService and the
		// mapping them using the serveFile method in this class and the result is
		// eventually returned as a list of strings/Uri
		model.addAttribute("files",
				storageService.loadAll().map(path -> MvcUriComponentsBuilder
						.fromMethodName(ExcelFileUploadController.class, "serveFile", path.getFileName().toString())
						.build().toUri().toString()).collect(Collectors.toList()));

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		// load a file from the storageService and return a response 200 ok with a
		// string header and the file as body
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		// store the send file using the storageService and add a redirectAttribute so
		// that we can see a message after being redirected
		// TODO using a wildfly 21 server cause this redirect to fail the file and such are still properly uploaded
		// the issue seems to be a difference in what is viewed as the rootlocation by wildfly
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		// in case the file could not be found this handler will build a response
		return ResponseEntity.notFound().build();
	}

}