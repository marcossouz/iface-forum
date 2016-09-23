package br.ufal.ic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sistema")
public class ControllerSystem {

	@RequestMapping(value = "/boasvindas", method = RequestMethod.GET)
	ModelAndView boasVindas(@RequestParam(value = "nome", required = false, defaultValue = "iface" ) String nome) {
		
		ModelAndView modelAndView = new ModelAndView("boasVindas");
		modelAndView.addObject("mensagem", "Ola" + nome);
		
		return modelAndView;
	}
}
