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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Reginaldo Gaspar
 */
@Controller
public class AvaliacaoController {

    @Autowired
    PrestadorService prestadorService;

    @Autowired
    AvaliacaoService avaliacaoService;

    PrestadorEntity prestadorEncontrado;
    List<AvaliacaoEntity> listaAvaliacaoEncontrada = new ArrayList<>();

    
    @GetMapping("/avaliacao")
    public String exibirAvaliacao(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);

        for (PrestadorEntity p : prestadorService.listarTodosPrestadores()) {
            if (p.getId() == idPrestador) {
                prestadorEncontrado = p;
                break;
            }
        }

        model.addAttribute("pEncontrado", prestadorEncontrado);
        model.addAttribute("avaliacao", new AvaliacaoEntity());

        return "avaliacao";
    }

    @PostMapping("/avaliacao")
    public String processarAvaliacao(@ModelAttribute AvaliacaoEntity avaliacao, Model model) {

        avaliacao.setPrestador(prestadorEncontrado);
        avaliacao.setId(avaliacaoService.listarTodosAvaliacoes().size() + 1);

        avaliacaoService.listarTodosAvaliacoes().add(avaliacao);
        listaAvaliacaoEncontrada.clear();

        for (AvaliacaoEntity a : avaliacaoService.listarTodosAvaliacoes()) {

            if (a.getPrestador() == prestadorEncontrado) {
                listaAvaliacaoEncontrada.add(a);
            }
        }

        model.addAttribute("pEncontrado", prestadorEncontrado);
        model.addAttribute("listaAvaliacaoEncontrada", listaAvaliacaoEncontrada);

        return "avaliacoes-prestador";
    }
}
