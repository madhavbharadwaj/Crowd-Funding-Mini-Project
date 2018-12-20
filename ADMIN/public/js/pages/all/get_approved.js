
c=0;
window.onload=function(){
//GET ALL APPROVED PROJECTS
var x='https://crowd-src.herokuapp.com/upload/newapproved';
$.getJSON("https://crowd-src.herokuapp.com/upload/newapproved", function (data) 
{
    y=data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED;
})
    $.ajax({
        type: 'GET',
            
        url: x,
        success: function(data) 
        {   
            alert(y);
            $.getJSON(x, function (data) 
            {
                                                
                var x="<section class='content'><div class='container-fluid'><div class='row clearfix'>";
                var z="</div></div></section>";
                for (var i=0;i<y;i++){
                    //console.log(y);
                    g= document.createElement('div');
                    console.log(data.COMPLETE_DETAILS[i].title);
                    
                    g.id = i;
                    //g.style.margin.left= "1000px";
                    var pid=data.COMPLETE_DETAILS[i]._id;
                    var email=data.COMPLETE_DETAILS[i].email;
                    var title=data.COMPLETE_DETAILS[i].title;
                    var description=data.COMPLETE_DETAILS[i].description;
                    var domain=data.COMPLETE_DETAILS[i].domain;
                    var dept=data.COMPLETE_DETAILS[i].category;
                    var git=data.COMPLETE_DETAILS[i].git_proj_link;
                    var a="<div class='col-lg-4 col-md-4 col-sm-6 col-xs-12'><div class='card'><div class='header bg-pink'><h2>PROJECT:";
                    var f="</h2><br><h2>Title:";
                    var b="</h2><small class='email'>Email:";
                    var c="</small></h2></div><div class='body'>";
                    //ADD LIST APPROVE <LI CLASS="APPROVE"> AND DISAPPROVE <LI ID="DISAPROVE">

                    var d="</div></div></div>";
                    console.log("yes");
                    var n="<h5>DOMAIN :";
                    var m="<h5>STATUS :";
                    var l="<ul><li class='modalop'><a role='button'><i class='material-icons' style='color:grey;'>visibility</i></a></li></ul>";
                    var k="</h5>";
                    document.body.appendChild(g);
                    var abc = x+a+(i+1)+f+data.COMPLETE_DETAILS[i].title+b+data.COMPLETE_DETAILS[i].email+c+n+data.COMPLETE_DETAILS[i].domain+k+m+data.COMPLETE_DETAILS[i].status+k+l+d+z;
                    var app="<button class='btn btn-primary m-t-15 waves-effect' id='approve'>APPROVE</button><button class='btn btn-primary m-t-15 waves-effect' id='disapprove'>DISAPPROVE</button>"
                    document.getElementById(g.id).innerHTML=abc;
                    document.getElementById("modalinfo").innerHTML=app;
                }                                        
            })      
        }
    }) 
}          
                                    