var cores = ["red", "blue","green","purple","yellow","grey"];

$(document).ready(function(){
	$("ul.tabs").tabs();
	//mostrarFluxo();
});

function mostrarFluxo(){
	let disciplinas = $(".disciplina");
	disciplinas.each(function(){
		let id = $(this).attr("id");
		let cor = cores.shift()
		criarDiagrama(id, cor);
		cores.push(cor);
		
	});
}

function mostrarFluxoDisciplina(disciplina){
	
}

$(".verificar-descentes").on("click", function(){
	$(this).toggleClass("hide");
	$(this).siblings().toggleClass("hide");
	let instance = jsPlumb.getInstance();
	instance.deleteEveryConnection()
	let id = $(this).parent().parent().attr("id");
	let cor = cores.shift()
	criarDiagrama(id, cor);
	cores.push(cor);
});

function createFlow(origem, destinos, color){
	let firstInstance = jsPlumb.getInstance();
	firstInstance.importDefaults({
		  Connector : [ "Flowchart",{ stub: 150 }],
		  Anchors : [ ["Right"], ["Left"]],
		  Endpoint:"Dot"
		});
	for(let i = 0; i<destinos.length; i++){
		firstInstance.connect({
			  source:origem, 
			  target:destinos[i],
			  endpoint: "Blank",
			  paintStyle:{ stroke:color, strokeWidth:2 },
			  overlays: [
			        ["Arrow", {
			            width: 15,
			            length: 15,
			            location: 1,
			            paintStyle: {
			                fillStyle: "black"
			            }
			        }]
			    ]
		});
	}
		
}

function criarDiagrama(disciplina, color){
	var url = "/aluno/dependencia";
	var token = $("meta[name='_csrf']").attr("content");
	var param = {disciplina:disciplina};
	var result;
	$.ajax({
		url,
		type: "POST",
		data: param,
		headers: {"X-CSRF-TOKEN":token},		
		success: function(response) {
			if(response.status=="DONE"){
				result = response.data
				let dependentes = []
				for(let i =0; i< result.length;i++){
					dependentes.push(""+result[i].id)
				}
				createFlow(""+disciplina,  dependentes, color);
			}else{
				result = null;
			}
		},
		error : function(e) {
			console.log("ERROR: "+ e);
			Materialize.toast("Não foi possível salvar suas alterações, tente novamente", 3000, "red rounded")
		}
	});
	
}