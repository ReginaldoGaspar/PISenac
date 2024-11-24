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

    PrestadorEntity pEncontrado = new PrestadorEntity();
    AvaliacaoEntity aEncontrada = new AvaliacaoEntity();
    List<AvaliacaoEntity> listaAvaliacaoEncontrada = new ArrayList<>();

    @GetMapping("/avaliacao")
    public String exibirAvaliacao(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);
        pEncontrado = prestadorService.getPrestadorId(idPrestador);

        model.addAttribute("pEncontrado", pEncontrado);
        model.addAttribute("avaliacao", new AvaliacaoEntity());

        return "avaliacao";
    }

    @PostMapping("/avaliacao")
    public String processarAvaliacao(@ModelAttribute AvaliacaoEntity avaliacao, Model model) {
        listaAvaliacaoEncontrada.clear();
        if (avaliacao.getId() > 0) {
            avaliacaoService.atualizarAvaliacao(avaliacao, avaliacao.getId());

        } else {
            avaliacao.setPrestador(prestadorService.getPrestadorId(pEncontrado.getId()));
            avaliacaoService.criarAvaliacao(avaliacao);
        }
        
        for (AvaliacaoEntity a : avaliacaoService.listarTodosAvaliacoes()) {
            if (a.getPrestador() == prestadorService.getPrestadorId(pEncontrado.getId())) {
                listaAvaliacaoEncontrada.add(a);
            }
        }

        model.addAttribute("pEncontrado", pEncontrado);
        model.addAttribute("listaAvaliacaoEncontrada", listaAvaliacaoEncontrada);

        return "avaliacoes-prestador";
    }

    @GetMapping("/excluirAvaliacao")
    public String excluirAvaliacao(@RequestParam String id, Model model) {
        Integer idAvaliacao = Integer.valueOf(id);
        aEncontrada = avaliacaoService.getAvaliacaoId(idAvaliacao);
        pEncontrado = aEncontrada.getPrestador();

        avaliacaoService.deletarAvaliacao(idAvaliacao);

        listaAvaliacaoEncontrada.clear();
        for (AvaliacaoEntity a : avaliacaoService.listarTodosAvaliacoes()) {
            if (a.getPrestador() == pEncontrado) {
                listaAvaliacaoEncontrada.add(a);
            }
        }

        model.addAttribute("pEncontrado", pEncontrado);
        model.addAttribute("listaAvaliacaoEncontrada", listaAvaliacaoEncontrada);
        return "avaliacoes-prestador";
    }

    @GetMapping("/alterarAvaliacao")
    public String alterarAvaliacao(Model model, @RequestParam("id") String id) {
        Integer idAvaliacao = Integer.valueOf(id);
        aEncontrada = avaliacaoService.getAvaliacaoId(idAvaliacao);
        pEncontrado = aEncontrada.getPrestador();
        model.addAttribute("avaliacao", aEncontrada);
        model.addAttribute("pEncontrado", pEncontrado);

        return "avaliacao";
    }
}
