const bgCorAlerta = "grey darken-1";
const cardCorAlerta = "grey lighten-3";
const bgCor = "grey lighten-3";


function clica(){
    var x = document.getElementById("clica").value;
    alert(x);
}


$("a").click(function(){
	var div = $("#s" + this.id);
	var grow = $(".grow");
    div.find(".hideable").toggle();
    div.find(".expander").toggleClass("hide");
    div.toggleClass("shrink");
    grow.toggleClass("expand");
});

$("#usuario").sideNav();

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

$(document).ready(function(){
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
    
});

function salvar(idDisciplina, idSemestre){
	alert("aqui?");
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
			}else{
				Materialize.toast("Não foi possível salvar suas alterações, tente novamente", 3000, "red rounded");
			}
		},
		error : function(e) {
			console.log("ERROR: "+ e);
			Materialize.toast("Não foi possível salvar suas alterações, tente novamente", 3000, "red rounded")
		}
	});
}

$( ".droppable" ).droppable({ 
	drop: function( event, ui ) {
        var editButton = "<a class='edit btn-floating waves-effect waves-light right'><i class='material-icons'>edit</i></a>";
        var card = ui.draggable;
        if(card.find(".opcoes .edit").length === 0){
            card.find(".opcoes").append(editButton);
        }
        var result = ($(this).attr("id"));
        salvar(card.attr("id") , result.substring(1,2));
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
});