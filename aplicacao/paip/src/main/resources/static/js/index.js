const bgCorAlerta = "grey darken-1";
const cardCorAlerta = "grey lighten-3";
const bgCor = "grey lighten-3";


var sessionCurriculo;
var sessionPreRequisito;
var disciplinasSemestre;
var gclick;
var semestreclick;

$(".expander").click(function(){
	var div = $("#s" + this.id);
	var grow = $(".grow");
    div.find(".hideable").toggle();
    div.find(".expander").toggleClass("hide");
    div.toggleClass("shrink");
    grow.toggleClass("expand");
});

function barraLateral(click, verificar){
	gclick = click;
	
	let barraNaoAtiva = $(verificar).hasClass("hide");
	let target = $("#"+click);
	if(barraNaoAtiva){
		target.toggleClass("hide");
		$("#fluxograma").toggleClass("expand");
	}else{
		$(verificar).toggleClass("hide");
		target.toggleClass("hide");
	}
}

function virgToPont(notas){
	let vet = new Array();
	for(let i = 0; i< notas.length; i++){
		vet[i] = notas[i].replace(",",".");
	}
	console.log(vet);
	return vet;
}

function inserirCurriculo(disciplina,nota){
	var url = "/aluno/historico";
	var token = $("meta[name='_csrf']").attr("content");
	let aux = JSON.stringify(disciplina);
	let aux2 = JSON.stringify(virgToPont(nota));
	var param = {disciplinaNome:aux.substring(1, aux.length-1), valorNota:aux2.substring(1, aux2.length-1), semestre: semestreclick};
	$.ajax({
		url,
		type: "POST",
		data: param,
		headers: {"X-CSRF-TOKEN":token},		
		success: function(response) {
			if(response.status=="DONE"){
				alert("Semestre Salvo");
				getSessionCurriculo()
				setTimeout('location.reload();', 200);
			}else{
				//alert("shit");
				//sessionPreRequisito = null;
			}
		},
		error : function(e) {
			console.log("ERROR: "+ e);
		}
	});
	//setTimeout('location.reload();', 200);
}


function forElement(){
	var valor;
	var notas = new Array();
	var cadeiras = new Array();
	
	for(let i = 0; i< disciplinasSemestre.length; i++){
		valor = document.getElementById(i).value;
		notas[i] = valor;
		cadeiras[i] = disciplinasSemestre[i];//$(disciplinasSemestre[i]).find(".disciplina-nome").text();
	}
	if(notas.indexOf("") == -1){
		inserirCurriculo(cadeiras,notas);
	}else{
		alert("Por favor, preencha todas as notas.");
	}
}

$(".editar-semestre").click(function(){
	barraLateral($(this)[0].target, "#barra-lateral");
	let semestre = $(this).parent().parent().parent();
	semestreclick = semestre.attr("id").split('s')[1];
	let disciplinas = semestre.find(".disciplina");
	let disciplinasF = semestre.find(".disciplinaF");
	disciplinasSemestre = new Array(); 
	
	$("#barra-editar").empty();
	$("#barra-editar").append("<form class='col s12'> <div class='row'>" +
			"Inserir as notas das disciplinas"); 
	
	let tamanho = 0, flag = false, flag2 = false, aux = null;
	if(disciplinas.length > 0 && disciplinasF.length >0){
		flag = true;
		flag2 = true;
		//tamanho = disciplinas.length;
	}else if(disciplinas.length > 0) {
		flag = true;
		tamanho = disciplinas.length;
	}
	else if(disciplinasF.length > 0) {
		flag = false;
		tamanho = disciplinasF.length;
	}
	
	for(let i = 0; i< tamanho; i++){
		if(flag){
			aux = disciplinas[i];
			disciplinasSemestre[i] =disciplinas[i].id;
		}else{
			aux = disciplinasF[i];
			disciplinasSemestre[i] =disciplinasF[i].id;
		}
		$("#barra-editar").append("<div class='input-field col s12'>"+
				"<input id="+i+ " type='text' name="+i+ " class='validate' placeholder='digite a sua nota' >" +
				"<div data-toggle='tooltip'> <a class='tooltipped' data-position='right' data-delay='50' data-tooltip='"+"Insira sua nota na Discplina "+$(disciplinas[i]).find(".disciplina-nome").text()+" e " +
						"prossiga' >" +
				"<label class='active' for='name'>"+$(aux).find(".disciplina-nome").text()+"</label> </a> </div> </div>");
	}
	if(flag && flag2){
		for(let i = 0; i< disciplinas.length; i++){
			aux = disciplinas[i];
			disciplinasSemestre[i] =disciplinas[i].id;
			$("#barra-editar").append("<div class='input-field col s12'>"+
					"<input id="+i+ " type='text' name="+i+ " class='validate' placeholder='digite a sua nota' >" +
					"<div data-toggle='tooltip'> <a class='tooltipped' data-position='right' data-delay='50' data-tooltip='"+"Insira sua nota na Discplina "+$(disciplinas[i]).find(".disciplina-nome").text()+" e " +
							"prossiga' >" +
					"<label class='active' for='name'>"+$(aux).find(".disciplina-nome").text()+"</label> </a> </div> </div>");
		}
		for(let i = 0; i< disciplinasF.length; i++){
			aux = disciplinasF[i];
			disciplinasSemestre[i] =disciplinasF[i].id;
			$("#barra-editar").append("<div class='input-field col s12'>"+
					"<input id="+i+ " type='text' name="+i+ " class='validate' placeholder='digite a sua nota' >" +
					"<div data-toggle='tooltip'> <a class='tooltipped' data-position='right' data-delay='50' data-tooltip='"+"Insira sua nota na Discplina "+$(disciplinas[i]).find(".disciplina-nome").text()+" e " +
							"prossiga' >" +
					"<label class='active' for='name'>"+$(aux).find(".disciplina-nome").text()+"</label> </a> </div> </div>");
		}
	}	
	$("#barra-editar").append("<input class='btn' for='name' type='submit' value='enviar' onClick='forElement()'>"+
			"</div>" +
			"</form>");
	//$('[data-toggle="tooltip"').tooltip();
	$('.tooltipped').tooltip();	
	
});


$(document).keyup(function(e) { 
	if (e.keyCode == 27) { 
        let barraNaoAtiva = $("#barra-editar").hasClass("hide");
        let barra2NaoAtiva = $("#barra-lateral").hasClass("hide");
        if(!barraNaoAtiva){
        	let target = $("#"+gclick);
			target.toggleClass("hide");
			$("#fluxograma").toggleClass("expand");
		}
        if(!barra2NaoAtiva){
        	let target = $("#"+gclick);
			target.toggleClass("hide");
        	$("#fluxograma").toggleClass("expand");
        }
    } 
});

//"<input id="+i+ " type='text' name="+i+ " class='validate' placeholder='digite a sua nota'>" +
$(".navbar-option").click(function(){
	barraLateral($(this)[0].target, "#barra-editar");
});

$(".add-semestre").on("click",function(){
	let ultimoSemestre = $(".semestres").children().last().clone();
	let cabecalho = ultimoSemestre.find(".cabecalho-semestre").find(".semestre");
	let expander = ultimoSemestre.find(".cabecalho-semestre").find(".expander");
	let corpo = ultimoSemestre.find(".body");
	corpo.empty();
	let id =(Number((ultimoSemestre[0].id).slice(1, ((ultimoSemestre[0].id).length)))+1);	
	for(let i=0; i<expander.length; i++) expander[i].id = id;
	if((id.toString().length)==1) cabecalho.text("SEMESTRE 0"+id);
	else cabecalho.text("SEMESTRE "+id);
	ultimoSemestre[0].id = "s"+id;
	ultimoSemestre.appendTo(".semestres");
});

function preRequisitos(codigo){
	var hasRequisito = false;
	for(var i = 0; i < disciplinas.length; i++){
		if (disciplinas[i].codigo ===	codigo){
			disciplinas = disciplinas[i].preRequisito;
			hasRequisito = true;
			break;
		}	
	}
	if(hasRequisito){
		var grade = document.getElementsByClassName("grow");
		for(var i = 0; i < grade.length; i++){
			var parent = $("#"+grade[i].id);
			for(var j = 0; j < disciplinas.length; j++){
				var child = $("#"+disciplinas[j].codigo);
				if(isDescendant(parent, child)){
					var semestre = parseInt(parent.attr("id").charAt(1));
					for(var k = 1; k < (semestre+1); k++){
						$("#s"+k).removeClass(bgCor);
						$("#s"+k).droppable("option", "disabled", true);
						$("#s"+k).addClass(bgCorAlerta);
					}
					$("#"+child.attr("id")).addClass(cardCorAlerta);					
				}
			}
		}
	}
	
}

function isDescendant(parent, child) {
    var node = child[0].parentNode;
    while (node != null) {
        if (node == parent[0]) {
            return true;
        }
        node = node.parentNode;
    }
    return false;
}


function getSessionCurriculo(){
	var a = 1;
	var url = "/aluno/sessionCurriculo";
	var token = $("meta[name='_csrf']").attr("content");
	var param = {a};
	$.ajax({
		url,
		type: "POST",
		data: param,
		headers: {"X-CSRF-TOKEN":token},		
		success: function(response) {
			if(response.status=="DONE"){
				sessionCurriculo = response.data;
				
			}else{
				sessionCurriculo = null;
			}
		},
		error : function(e) {
			console.log("ERROR: "+ e);
		}
	});
}

function getSessionPreRequisito(){
	var a = 1;
	var url = "/aluno/sessionPreRequisito";
	var token = $("meta[name='_csrf']").attr("content");
	var param = {a};
	$.ajax({
		url,
		type: "POST",
		data: param,
		headers: {"X-CSRF-TOKEN":token},		
		success: function(response) {
			if(response.status=="DONE"){
				sessionPreRequisito = response.data;
			}else{
				sessionPreRequisito = null;
			}
		},
		error : function(e) {
			console.log("ERROR: "+ e);
		}
	});
}

function getCurriculoBySemestre(semestre){
	var vetor = new Array();
	var pos =0;
	for(var i=0;i<sessionCurriculo.length;i++){
		if(sessionCurriculo[i].semestre == semestre){
			vetor[pos] = sessionCurriculo[i];
			pos = pos+1;
		}
	}
	return vetor;
}


$(document).ready(function(){
	getSessionCurriculo();
	getSessionPreRequisito();
	
	//$('.tooltipped').tooltip({delay: 50});
	$("select").material_select();
    $(".modal").modal();
    $(".draggable").draggable({
        helper:'clone',
        revert:  function(dropped) {
            var hasBeenDroppedBefore = $(this).data('hasBeenDropped');        
            if (hasBeenDroppedBefore) {
                $(this).animate({ top: 0, left: 0 }, 'slow');                
                return false;
            } else {
                return true;
            }
       },
       stop: function(event, ui){
    	   $(this).data('hasBeenDropped', false);
       },
       start: function(event, ui){
    	   $("[id^='materialize-modal-overlay-']").toggleClass("modal-overlay");  
       }
    });
    
    drop();
    
    
});

function useSession(idDisciplina, idSemestre){
	var i = 0;
	var j = 0;
	var pos = 0;
	var vetor = new Array();
	
	for(i =0; i< sessionPreRequisito.length; i++ ){
		if(sessionPreRequisito[i].disciplina.id == idDisciplina){
			vetor[pos] = sessionPreRequisito[i].disciplinaPreRequisito;
			pos = pos+1;
		}
	}

	for(i =0; i< sessionCurriculo.length; i++ ){
		for(j =0; j< vetor.length; j++ ){
			if(sessionCurriculo[i].disciplina.id == vetor[j].id){
				if(sessionCurriculo[i].semestre >= idSemestre){
					return false;
				}
			}
		}
	}
	
	return true;
}

function salvar(idDisciplina, idSemestre){
	if(useSession(idDisciplina,idSemestre)){
		var url = "/aluno/disciplina";
		var token = $("meta[name='_csrf']").attr("content");
		var param = {disciplina:idDisciplina, semestre: idSemestre};
		$.ajax({
			url,
			type: "POST",
			data: param,
			headers: {"X-CSRF-TOKEN":token},		
			success: function(response) {
				if(response.status=="DONE"){
					Materialize.toast("A disciplina "+ response.data.disciplina.nome+ " foi movida para o semeste: " + response.data.semestre, 3000, "teal rounded");
					getSessionCurriculo();
					setTimeout('location.reload();', 500);
				}else{
					Materialize.toast("Não foi possível salvar suas alterações, tente novamente", 3000, "red rounded")
					window.location.reload();
				}
			},
			error : function(e) {
				console.log("ERROR: "+ e);
			}
		});
	}
	else{
		Materialize.toast("Não foi possível salvar suas alterações, tente novamente", 3000, "red rounded")
		setTimeout('location.reload();', 500);
	}
}

function drop(){
$( ".droppable" ).droppable({ 
	drop: function( event, ui ) {
        var editButton = "<a class='edit btn-floating waves-effect waves-light right'><i class='material-icons'>edit</i></a>";
        var card = ui.draggable;
        if(card.find(".opcoes .edit").length === 0){
            card.find(".opcoes").append(editButton);
        }
        var result = ($(this).attr("id"));
        salvar(card.attr("id") , result.slice(1));
        card.detach();
        card[0].style.cssText = "";
        card.appendTo($(this).find(".body"));
        $("#modal1").modal("close");
        $(card).data('hasBeenDropped', true);
        
    },
    activate: function( event, ui ) {
    	var card = ui.draggable;
        //preRequisitos(card.attr("id"));
    },
    deactivate: function(event, ui){
        $(".grow.grey").removeClass(bgCorAlerta).addClass(bgCor);
        $(".card.grey").removeClass(cardCorAlerta);
    	$(".ui-droppable-disabled").droppable("option", "disabled", false);
    	$("[id^='materialize-modal-overlay-']").toggleClass("modal-overlay");
    },
});}