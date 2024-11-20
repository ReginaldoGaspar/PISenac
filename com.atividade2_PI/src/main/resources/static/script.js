$(document).ready(function () {
                $('#telefone').mask('(00) 00000-0000');
                $('#cnpj').mask('00.000.000/0000-00');
            });

$("#Formulario").validate({
    rules: {
        nome: {
            required: true
        },
        servico: {
            required: true,
        },
        cnpj: {
            required: true,
        },
        telefone: {
            required: true,
        }
    },
    messages: {
        nome: {
            required: "campo nome é obrigatório",
        },
        servico: {
            required: "campo serviço é obrigatório",            
        },
        cnpj: {
            required: "campo CNPJ é obrigatório (SOMENTE NÚMEROS)",            
        },
        telefone: {
            required: "campo telefone é obrigatório (SOMENTE NÚMEROS)",            
        },
    }   
    
});
              