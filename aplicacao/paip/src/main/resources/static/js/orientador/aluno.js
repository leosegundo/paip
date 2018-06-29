/**
* Created by allan on 04/05/2017.
*/

$("#usuario").sideNav();

function _disciplinas(){
    var disciplinas = [{
        "cargaHoraria": "64h",
        "codigo": "QXD0108",
        "nome": "Introdução à Ciência da Computação",
        "cursosOferta": [{
            "codigoCurso": 404,
            "nomeCurso": "Ciência da Computação",
            "obrigatorio": true,
            "semestreOferta": 1
        }],
        "preRequisito": [],
        "correquisito": [],
        "equivalencia": []
    },
        {
            "cargaHoraria": "96h",
            "codigo": "QXD0001",
            "nome": "Fundamentos de Programação",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 1
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0005",
            "nome": "Arquitetura de Computadores",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 1
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0056",
            "nome": "Matemática Básica",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 1
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "32h",
            "codigo": "QXD0103",
            "nome": "Ética, Direito e Legislação",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 1
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "32h",
            "codigo": "QXD0109",
            "nome": "Pré-Cálculo",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 1
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0007",
            "nome": "Programação Orientada a Objetos",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 2
            }],
            "preRequisito": [{
                "cargaHoraria": "96h",
                "codigo": "QXD0001",
                "nome": "Fundamentos de Programação",
                "cursosOferta": [{
                    "codigoCurso": 404,
                    "nomeCurso": "Ciência da Computação",
                    "obrigatorio": true,
                    "semestreOferta": 1
                }],
                "preRequisito": [],
                "correquisito": [],
                "equivalencia": []
            }],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0013",
            "nome": "Sistemas Operacionais",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 2
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0010",
            "nome": "Estrutura de Dados",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 2
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0006",
            "nome": "Cálculo Diferencial e Integral I",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 2
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "32h",
            "codigo": "QXD0008",
            "nome": "Matemática Discreta",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 2
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0114",
            "nome": "Programação Funcional",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 3
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0115",
            "nome": "Estrutura de Dados Avançada",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 3
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0040",
            "nome": "Linguagens Formais e Autômatos",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 3
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0017",
            "nome": "Lógica para Computação",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 3
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0012",
            "nome": "Probabilidade e Estatística",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 3
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0011",
            "nome": "Fundamentos de Banco de Dados",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 4
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0016",
            "nome": "Linguagens de Programação",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 4
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0041",
            "nome": "Projeto e Análise de Algoritmos",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 4
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0116",
            "nome": "Álgebra Linear",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 4
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0014",
            "nome": "Análise e Projeto de Sistemas",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 4
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0025",
            "nome": "Compiladores",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 5
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0119",
            "nome": "Computação Gráfica",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 5
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0120",
            "nome": "Matemática Computacional",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 5
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0021",
            "nome": "Redes de Computadores",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 5
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0020",
            "nome": "Desenvolvimento de Software para Web",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 5
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0019",
            "nome": "Engenharia de Software",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 6
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0038",
            "nome": "Interface Humano-Computador",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 6
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0038",
            "nome": "Interface Humano-Computador",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": false,
                "semestreOferta": 6
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0043",
            "nome": "Sistemas Distribuídos",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 6
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0037",
            "nome": "Inteligência Artificial",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 6
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "32h",
            "codigo": "QXD0046",
            "nome": "Teoria da Computação",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 6
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "32h",
            "codigo": "QXD0110",
            "nome": "Projeto de Pesquisa Científica e Tecnológica",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 7
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "32h",
            "codigo": "QXD0157",
            "nome": "Trabalho de Conclusão de Curso I",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 7
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "160h",
            "codigo": "QXD0155",
            "nome": "Estágio Supervisionado I",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 7
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "64h",
            "codigo": "QXD0029",
            "nome": "Empreendedorismo",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 7
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "96h",
            "codigo": "QXD0158",
            "nome": "Trabalho de Conclusão de Curso II",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 8
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "cargaHoraria": "160h",
            "codigo": "QXD0156",
            "nome": "Estágio Supervisionado II",
            "cursosOferta": [{
                "codigoCurso": 404,
                "nomeCurso": "Ciência da Computação",
                "obrigatorio": true,
                "semestreOferta": 8
            }],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "nome": "Desenvolvimento De Software Para Dispositivos Móveis",
            "codigo": "QXD0022",
            "cursosOferta": [{
                "codigoCurso": 401,
                "nomeCurso": "Sistemas de Informação",
                "obrigatorio": true,
                "semestreOferta": 5
            },
                {
                    "codigoCurso": 402,
                    "nomeCurso": "Engenharia de Software",
                    "obrigatorio": false,
                    "semestreOferta": 5
                },
                {
                    "codigoCurso": 403,
                    "nomeCurso": "Redes de Computadores",
                    "obrigatorio": true,
                    "semestreOferta": 6
                },
                {
                    "codigoCurso": 404,
                    "nomeCurso": "Ciência da Computação",
                    "obrigatorio": false,
                    "semestreOferta": 1
                },
                {
                    "codigoCurso": 406,
                    "nomeCurso": "Design Digital",
                    "obrigatorio": false,
                    "semestreOferta": 8
                }

            ],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        },
        {
            "nome": "Gestão de Processos de Negócios",
            "codigo": "QXD0020",
            "cursosOferta": [{
                "codigoCurso": 401,
                "nomeCurso": "Sistemas de Informação",
                "obrigatorio": true,
                "semestreOferta": 5
            },
                {
                    "codigoCurso": 402,
                    "nomeCurso": "Engenharia de Software",
                    "obrigatorio": false,
                    "semestreOferta": 5
                },
                {
                    "codigoCurso": 403,
                    "nomeCurso": "Redes de Computadores",
                    "obrigatorio": true,
                    "semestreOferta": 6
                },
                {
                    "codigoCurso": 406,
                    "nomeCurso": "Design Digital",
                    "obrigatorio": false,
                    "semestreOferta": 8
                }

            ],
            "preRequisito": [],
            "correquisito": [],
            "equivalencia": []
        }
    ];
    return disciplinas;
}

function autoPreenchimento(codigoCurso){
    var disciplinas =  _disciplinas();
    var semestre = [[],[],[],[],[],[],[],[]];
    var tmp = 0;
    for (var j = 0 ; j < disciplinas.length; j++){
        var disciplina = disciplinas[j];
        var ofertas = disciplina.cursosOferta;
        var check = false;
        for (var i = 0; i < ofertas.length; i++){
            if(ofertas[i].codigoCurso === codigoCurso && ofertas[i].obrigatorio){
                switch(ofertas[i].semestreOferta){
                    case 1:
                        semestre[0].push(disciplina);
                        break;
                    case 2:
                        semestre[1].push(disciplina);
                        break;
                    case 3:
                        semestre[2].push(disciplina);
                        break;
                    case 4:
                        semestre[3].push(disciplina);
                        break;
                    case 5:
                        semestre[4].push(disciplina);
                        break;
                    case 6:
                        semestre[5].push(disciplina);
                        break;
                    case 7:
                        semestre[6].push(disciplina);
                        break;
                    case 8:
                        semestre[7].push(disciplina);
                        break;
                }
                check = true;
            }
        }

    }
    for (var j = 0 ; j<semestre.length; j++){
        if(semestre[j].length > tmp) tmp = semestre[j].length;
    }
    var tableRow = "";
    for (var i = 0; i< tmp; i++) {
        tableRow += "<tr>";
        var tmpDisc;
        for (var j = 0; j < semestre.length; j++) {
            tmpDisc = semestre[j].shift();
            if(typeof tmpDisc != "undefined")   tableRow += "<td>" + tmpDisc.nome + "</td>";
            else tableRow += "<td></td>";
        }
        tableRow+= "</tr>";
    }
    var table = $('table tbody');
    table.append(tableRow);

}

$(document).ready(function(){
    autoPreenchimento(404);
});