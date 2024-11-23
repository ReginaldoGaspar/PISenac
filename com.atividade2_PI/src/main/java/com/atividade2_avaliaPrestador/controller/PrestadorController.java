package com.atividade2_avaliaPrestador.controller;

import com.atividade2_avaliaPrestador.model.AvaliacaoEntity;
import com.atividade2_avaliaPrestador.model.PrestadorEntity;
import com.atividade2_avaliaPrestador.service.AvaliacaoService;
import com.atividade2_avaliaPrestador.service.PrestadorService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Reginaldo Gaspar
 */
@Controller
@RequestMapping("/")
public class PrestadorController {

    @Autowired
    PrestadorService prestadorService = new PrestadorService();

    @Autowired
    AvaliacaoService avaliacaoService = new AvaliacaoService();

    List<AvaliacaoEntity> listaAvaliacaoEncontrada = new ArrayList<>();

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("prestador", new PrestadorEntity());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarFormulario(@ModelAttribute PrestadorEntity prestador, Model model) {

        if (prestador.getId() > 0) {
            prestadorService.atualizarPrestador(prestador, prestador.getId());
        } else {
            prestadorService.criarPrestador(prestador);
        }
        model.addAttribute("prestadores", prestadorService.listarTodosPrestadores());
        return "exibir-prestador";
    }

    @GetMapping("/exibir-prestador")
    public String exibir(@ModelAttribute PrestadorEntity prestador, Model model) {
        model.addAttribute("prestadores", prestadorService.listarTodosPrestadores());
        return "exibir-prestador";
    }

    @GetMapping("/avaliacoes-prestador")
    public String exibirAvaliacoes(Model model, @RequestParam("id") String id) {
        listaAvaliacaoEncontrada.clear();
        Integer idPrestador = Integer.valueOf(id);
        for(AvaliacaoEntity a : avaliacaoService.listarTodosAvaliacoes()){
            if(a.getPrestador().getId() == idPrestador){
                listaAvaliacaoEncontrada.add(a);
            }
        }
        
        model.addAttribute("pEncontrado", prestadorService.getPrestadorId(idPrestador));
        model.addAttribute("listaAvaliacaoEncontrada", listaAvaliacaoEncontrada);
        return "avaliacoes-prestador";
    }

    @GetMapping("/excluirPrestador")
    public String excluirPrestador(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);
        avaliacaoService.deletarAvaliacao(idPrestador);
        prestadorService.deletarPrestador(idPrestador);        
        
        model.addAttribute("pEncontrado", prestadorService.getPrestadorId(idPrestador));
        model.addAttribute("listaAnvaliacaoEncontrada", avaliacaoService.listarTodosAvaliacoes());
        return "redirect:/exibir-prestador";
    }

    @GetMapping("/alterarPrestador")
    public String alterarPrestador(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);
        
        model.addAttribute("prestador", prestadorService.getPrestadorId(idPrestador));

        return "cadastro";
    }

}
