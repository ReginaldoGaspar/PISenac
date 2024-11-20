package com.atividade2_avaliaPrestador.controller;

import com.atividade2_avaliaPrestador.model.Avaliacao;
import com.atividade2_avaliaPrestador.model.Prestador;
import java.util.ArrayList;
import java.util.List;
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
public class avaliaPrestadorController {

    private List<Prestador> listaPrestadores = new ArrayList();
    private List<Avaliacao> listaAvaliacoes = new ArrayList();
    List<Avaliacao> listaAvaliacaoEncontrada = new ArrayList<>();
    private Prestador prestadorEncontrado = new Prestador();

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("prestador", new Prestador());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarFormulario(@ModelAttribute Prestador prestador, Model model) {

        if (prestador.getId() > 0) {
            for (Prestador p : listaPrestadores) {
                if (p.getId() == prestador.getId()) {
                    p.setNome(prestador.getNome());
                    p.setServico(prestador.getServico());
                    p.setCnpj(prestador.getCnpj());
                    p.setTelefone(prestador.getTelefone());
                }
            }
        } else {
            prestador.setId(listaPrestadores.size() + 1);
            listaPrestadores.add(prestador);
        }
        model.addAttribute("prestadores", listaPrestadores);
        return "exibir-prestador";
    }

    @GetMapping("/avaliacao")
    public String exibirAvaliacao(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);

        for (Prestador p : listaPrestadores) {
            if (p.getId() == idPrestador) {
                prestadorEncontrado = p;
                break;
            }
        }

        model.addAttribute("pEncontrado", prestadorEncontrado);
        model.addAttribute("avaliacao", new Avaliacao());

        return "avaliacao";
    }

    @PostMapping("/avaliacao")
    public String processarAvaliacao(@ModelAttribute Avaliacao avaliacao, Model model) {

        avaliacao.setPrestador(prestadorEncontrado);
        avaliacao.setId(listaAvaliacoes.size() + 1);

        listaAvaliacoes.add(avaliacao);
        listaAvaliacaoEncontrada.clear();

        for (Avaliacao a : listaAvaliacoes) {

            if (a.getPrestador() == prestadorEncontrado) {
                listaAvaliacaoEncontrada.add(a);
            }
        }

        model.addAttribute("pEncontrado", prestadorEncontrado);
        model.addAttribute("listaAvaliacaoEncontrada", listaAvaliacaoEncontrada);

        return "avaliacoes-prestador";
    }

    @GetMapping("/exibir-prestador")
    public String exibir(@ModelAttribute Prestador prestador, Model model) {
        model.addAttribute("prestadores", listaPrestadores);
        return "exibir-prestador";
    }

    @GetMapping("/avaliacoes-prestador")
    public String exibirAvaliacoes(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);
        for (Prestador p : listaPrestadores) {
            if (p.getId() == idPrestador) {
                prestadorEncontrado = p;
                break;
            }
        }
        listaAvaliacaoEncontrada.clear();
        for (Avaliacao a : listaAvaliacoes) {
            if (a.getPrestador() == prestadorEncontrado) {
                listaAvaliacaoEncontrada.add(a);
            }
        }
        model.addAttribute("pEncontrado", prestadorEncontrado);
        model.addAttribute("listaAvaliacaoEncontrada", listaAvaliacaoEncontrada);
        return "avaliacoes-prestador";
    }

    @GetMapping("/excluirPrestador")
    public String excluirPrestador(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);
        for (Prestador p : listaPrestadores) {
            if (p.getId() == idPrestador) {
                prestadorEncontrado = p;
                break;
            }
        }
        listaPrestadores.remove(prestadorEncontrado);

        model.addAttribute("pEncontrado", prestadorEncontrado);
        model.addAttribute("listaAnvaliacaoEncontrada", listaAvaliacaoEncontrada);
        return "redirect:/exibir-prestador";
    }

    @GetMapping("/alterarPrestador")
    public String alterarPrestador(Model model, @RequestParam("id") String id) {
        Integer idPrestador = Integer.valueOf(id);
        for (Prestador p : listaPrestadores) {
            if (p.getId() == idPrestador) {
                prestadorEncontrado = p;
                break;
            }
        }

        model.addAttribute("prestador", prestadorEncontrado);

        return "cadastro";
    }

}
