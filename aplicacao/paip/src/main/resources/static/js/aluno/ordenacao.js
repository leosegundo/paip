$(document).ready(function(){
	$( ".semestre" ).sortable({
		connectWith: ".semestre:not(.block)",
		items:".disciplina",
		tolerance:"pointer",
	});
	$('.modal').modal();
});

var sessionCurriculo;
var sessionPreRequisito;
var flag = false;
var first_id;

function verificarSemestre(){
	let semestres = $(".semestre .body");
	let result = false;
	for(let i=0; i<semestres.length;i++){
		if($(semestres[i]).children().length === 0) result = true;
	}	
	return result;
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
				
				//$("#s"+semestre.semestre).find("#helper").length === 1 || 
				if(verificarSemestre()){
					location.reload();
				}
			}else{
				Materialize.toast("Não foi possível salvar suas alterações, tente novamente", 3000, "red rounded");
				location.reload();
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


function useSession(idDisciplina, idSemestre){
	var i = 0;
	var j = 0;
	var pos,cont = 0;
	let vetor = new Array();
	
	for(i =0; i< sessionPreRequisito.length; i++ ){
		if(sessionPreRequisito[i].disciplina.id == idDisciplina){
			vetor.push(sessionPreRequisito[i].disciplinaPreRequisito);
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

function buscarCurriculoPorSemestre(semestre){
	let vetor = new Array();
	for(let i=0;i<sessionCurriculo.length;i++){
		if(sessionCurriculo[i].semestre == semestre){
			vetor.push(sessionCurriculo[i]);			
		}
	}
	return vetor;
}
 
function buscarPreRequisitoPorDisciplina(disciplina){
	let vetor = new Array();
	for(let i=0;i<sessionPreRequisito.length;i++){
		if(sessionPreRequisito[i].disciplina.id == disciplina){
			vetor.push(sessionPreRequisito[i].disciplinaPreRequisito);			
		}
	}
	return vetor;
}
function buscarCurriculoPorPreRequisitos(PreRequsitos){
	let vetor = new Array();
	for(let i=0;i<sessionCurriculo.length;i++){
		for(let j=0;j<PreRequsitos.length;j++){
			if(sessionCurriculo[i].disciplina.id == PreRequsitos[j].id){
				vetor.push(sessionCurriculo[i]);
			}
		}
	}
	return vetor;
}

function auxSaveOpt(semestre_opt, id_disc){
	pres = buscarPreRequisitoPorDisciplina(id_disc);
	c_pres = buscarCurriculoPorPreRequisitos(pres);
	
	if(pres.length >0 && c_pres.length == 0){
		return false;
	}
	else{
		for(let i = 0; i < c_pres.length; i++){
			if(c_pres[i].semestre >= semestre_opt){
				return false;
			}
		}
	}
	return true;
}


function updateCurriculo(iddisciplinas,semestre, flag){
	let disciplinas = new Array();
	if(flag){
		disciplinas= iddisciplinas.split(",");
	}else{
		disciplinas.push(iddisciplinas);
	}
	
	for(let i=0;i<sessionCurriculo.length;i++){
		if(disciplinas.length == 0){
			break;
		}
		for(let j=0;j<disciplinas.length;j++){
			if(sessionCurriculo[i].disciplina.id == disciplinas[j]){
				let re = disciplinas.splice(j,1);
				let aux = sessionCurriculo[i];
				aux.semestre = parseInt(semestre);
				sessionCurriculo.splice(i,1,aux);
			}
		}
	}
		
}

function getCountSemestre(semestre){
	let sum = 0;
	for(let i=0;i<sessionCurriculo.length;i++){
		if(sessionCurriculo[i].semestre == semestre){
			sum = sum + 1;
		}
	}
	return sum;
}

function getCargaSemestre(semestre){
	let sum = 0;
	for(let i=0;i<sessionCurriculo.length;i++){
		if(sessionCurriculo[i].semestre == semestre){
			sum = sum + parseInt(sessionCurriculo[i].disciplina.cargaHoraria);
		}
	}
	return sum;
}


function confirma(texto){
	$('#texto').text(texto);
	$('#modal1').modal('open');
	let confirme = false;
	$('#btn1').click(function a(){
		confirme = true;
	});
	
	$('#btn2').click(function b(){
		confirme = false;
	});
	
	return confirme;
}


function ultimaDisciplina(id_disc){
	console.log("id ",id_disc);
	sem = getSemestreByID(id_disc);
	console.log("sem " + sem);
	a = getCountSemestre(sem);
	console.log("aa " +a);
	
		
	if(a > 1){
		return true;
	}
	else{
		if(confirm("Esta ação irá fechar o semestre "+ sem+ " deseja prosseguir? \n\n\n Esta ação no poderá ser desfeita.")){
			return true;
		}else{
			return false;
		}
	}
	return false;
	
}

function alertEmpurrarDisciplina(idDisciplinas,semestre){
	console.log(idDisciplinas[0]);
	console.log(semestre);
	
	updateCurriculo(idDisciplinas,semestre,true);
	let vetor = new Array();
	let sem=1;
	let a = "Ao executar essa ação: \n";
	let b;
	while(sem>0){
		let curriculo = buscarCurriculoPorSemestre(sem);
		if(curriculo.length !=0){
			for(let i=0;i<curriculo.length;i++){
				//alert(curriculo[i].disciplina.nome +" - " +curriculo[i].semestre);
				let pre_requisitos = buscarPreRequisitoPorDisciplina(curriculo[i].disciplina.id);
				let curriculo2 = buscarCurriculoPorPreRequisitos(pre_requisitos);
				
				for(let j=0;j<curriculo2.length;j++){
					if(curriculo[i].semestre<=curriculo2[j].semestre){
						if(getCargaSemestre((curriculo2[j].semestre+1)) <= 320){
							b = "* A disciplina "+curriculo[i].disciplina.nome+ " será movida para o semestre "+ (parseInt(curriculo2[j].semestre)+1)+"\n";
							updateCurriculo(curriculo[i].disciplina.id,(curriculo2[j].semestre+1),false);	
						}
						else{
							b = "* A disciplina "+curriculo[i].disciplina.nome+ " será movida para o semestre "+ (parseInt(curriculo2[j].semestre)+2)+"\n";
							updateCurriculo(curriculo[i].disciplina.id,(curriculo2[j].semestre+2),false);
						}
						
						vetor.push(b);
					}
					else if(curriculo2[j].semestre >= sem && !curriculo2[j].finalizado ){
						if(getCargaSemestre((curriculo2[j].semestre+1)) <= 320){
							b = "* A disciplina "+curriculo[i].disciplina.nome + " será movida para o semestre "+ (parseInt(sem)+1)+"\n";
							updateCurriculo(curriculo[i].disciplina.id,sem+1,false);
						}
						else{
							b = "* A disciplina "+curriculo[i].disciplina.nome + " será movida para o semestre "+ (parseInt(sem)+2)+"\n";
							updateCurriculo(curriculo[i].disciplina.id,sem+2,false);
						}
						vetor.push(b);
					}
				}
			}
		}
		else{
			sem = -1000;
		}
		sem = sem+1;
	}
	
	for(let i =0; i<vetor.length;i++){
		a= a+vetor[i]+" ";
	}
	
	if(vetor.length>0){
		if(confirm(a)){
			return [true,vetor.length];
		}
		else{
			return [false,0];
		}
	}
	else{
		console.log("wntrois");
		return [true,0];
	}
}


function save(semestre,ui){
	var url = "/aluno/semestre";
	var token = $("meta[name='_csrf']").attr("content");
	let disciplinas = JSON.stringify(semestre.disciplinas);
	let [condicao,valor] = alertEmpurrarDisciplina(disciplinas.substring(1, disciplinas.length-1),semestre.semestre);
	
	var param = {semestre:semestre.semestre, disciplinasId:disciplinas.substring(1, disciplinas.length-1),valor:valor};
	if(condicao){
		$.ajax({
			url,
			type: "POST",
			data: param,
			headers: {"X-CSRF-TOKEN":token},		
			success: function(response) {
				if(response.status=="DONE"){
					Materialize.toast("A disciplina "+ semestre.item+ " teve sua posição alterada", 3000, "teal rounded");
					getSessionCurriculo();
					setTimeout('location.reload();', 500);
				}else{
					ui.sender.sortable("cancel");
					console.log("Shit");
					//Materialize.toast("Não foi possível salvar suas alterações, verifique semestre e pre-requisitos", 3000, "red rounded");
					Materialize.toast("Não foi possível salvar suas alterações motivos de "+ response.data +", verifique semestre e pre-requisitos", 3000, "red rounded");
					//setTimeout('location.reload();', 3000);
				}
			},
			error : function(e) {
				ui.sender.sortable("cancel");
				Materialize.toast("Não foi possível salvar suas alterações, verifique semestre e pre-requisitos", 3000, "red rounded")
			}
		});
	}else{
		ui.sender.sortable("cancel");
	}
}

function saveOpativa(semestre_id1,id_um,id_dois){
	
	if(auxSaveOpt(semestre_id1,id_dois)){
	
		var url = "/aluno/salvarOptativa";
		var token = $("meta[name='_csrf']").attr("content");
	
		var param = {idOptativa:id_um, idDisciplina:id_dois};
		$.ajax({
				url,
				type: "POST",
				data: param,
				headers: {"X-CSRF-TOKEN":token},		
				success: function(response) {
					if(response.status=="DONE"){
						Materialize.toast("A disciplina  teve sua posição alterada", 3000, "teal rounded");
						getSessionCurriculo();
						setTimeout('location.reload();', 500);
					}else{
					//ui.sender.sortable("cancel");
						Materialize.toast("Não foi possível salvar suas alterações, verifique semestre e pre-requisitos", 3000, "red rounded");
					//	setTimeout('location.reload();', 3000);
					}
				},
				error : function(e) {
					//ui.sender.sortable("cancel");
					Materialize.toast("Não foi possível salvar suas alterações, verifique semestre e pre-requisitos", 3000, "red rounded")
				}
			});
	}else{
		Materialize.toast("Não foi possível salvar suas alterações, verifique semestre e pre-requisitos", 3000, "red rounded");
	}
}


function getSemestreByID(id_disc){
	for(let i = 0; i<sessionCurriculo.length; i++){
		if(sessionCurriculo[i].disciplina.id == id_disc){
			console.log(id_disc);
			return sessionCurriculo[i].semestre;
		}
	}
	
	return -1;
	
}

function getSemestre(item){
	let semestre = item.parent().parent().attr("id");
	semestre = semestre.substring(1, semestre.length);
	let disciplinas =  $.map(item.parent().children(), function(disciplina){
		if ($(disciplina).attr("id") !== "helper") return parseInt($(disciplina).attr("id"));
	});
	//console.log(({semestre: semestre, disciplinas:disciplinas, item: item.find(".disciplina-nome").text()}));
	return ({semestre: semestre, disciplinas:disciplinas, item: item.find(".disciplina-nome").text()});	
}

function isFinalizado(id){
	let flag1 = true;
	let flag2 = true;
	let flagAux = false;
	for(i =0; i< sessionCurriculo.length; i++ ){
		//console.log(i + " - "+ sessionCurriculo[i].id +" -- " + sessionCurriculo[i].disciplina.id);
		//if(sessionCurriculo[i].id == id){
		if(sessionCurriculo[i].disciplina.id == id){
			flagAux = true;
			if(sessionCurriculo[i].finalizado){
				flag1 = true;
			}
			else{
				flag1 = false;
			}
			flag2 = flag2 && flag1;
		}
	}
	if(flagAux){
		return flag2;
	}
	return false;
}




$( ".semestre" ).on( "sortupdate", function( event, ui ) {
	getSessionCurriculo();
	getSessionPreRequisito();
	
	let draggedOut = this !== ui.item.parent()[0] && !$.contains(this, ui.item.parent()[0]);
    let draggedIn = ui.sender !== null;
    let sameList = !draggedOut && !draggedIn;
	//previne que o método seja chamado em duplicado pela saída de um semestre para outro
	if (sameList || draggedIn) {
		let semestre = (ui.item.parent().parent().attr("id")).split('s')[1];
		//let origem = ((ui.sender.parent().parent().parent().context.id).split('s')[1]);
		let disc = ui.item.attr("id");
		//console.log("disc ", disc,"sems ", semestre);
		if(!useSession(disc,semestre)){
			ui.sender.sortable("cancel");
			Materialize.toast("Não foi possível mover para esse semestre, verifique os pré-requisitos e tente novamente", 3000, "red rounded")
		}
		else if(isFinalizado(disc)){
			ui.sender.sortable("cancel");
			Materialize.toast("Esta disciplina já está finalizada", 3000, "red rounded")
		}
		else{
			if(ultimaDisciplina(disc)){
				save(getSemestre(ui.item),ui);
			}
			else{
				ui.sender.sortable("cancel");
			}
		}
    }
	
});


$('.OOdisciplina').on("click",function(event, ui){
	first_id = event.currentTarget.attributes.id.nodeValue;
});

$('.NNdisciplina').on("click",function(event, ui){
	second_id = event.currentTarget.attributes.id.nodeValue;
	f_id = getSemestreByID(first_id);
	saveOpativa(f_id,first_id,second_id);
});


$(".semestre").on("sortstart", function(event, ui){
	$(".semestres").children().last().toggleClass("hide");
});

$(".semestre").on("sortstop", function(event, ui){
	$(".semestres").children().last().toggleClass("hide");
});
