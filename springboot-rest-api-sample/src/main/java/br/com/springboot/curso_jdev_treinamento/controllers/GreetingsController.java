package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
   /* @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	return "Olá mundo " + nome;
    }*/
    
    @GetMapping("/listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @GetMapping(value= "/buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam Long iduser){
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @PostMapping(value = "/salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if(usuario.getId()==null) {
    		return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity<String> salvar(@RequestParam Long iduser){
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuário deletado com sucesso" , HttpStatus.OK);
    }
    
    @GetMapping(value = "/buscarPorNome")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestBody @RequestParam(name = "name") String name){
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }
}
