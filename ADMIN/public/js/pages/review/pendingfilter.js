
c=0;
window.onload=function(){
    //var pageloader="<div class='page-loader-wrapper'><div class='loader'><div class='preloader'><div class='spinner-layer pl-red'><div class='circle-clipper left'><div class='circle'></div></div><div class='circle-clipper right'><div class='circle'></div></div></div></div><p>Please wait...</p></div></div>";
    c++;

    //GET ALL THE DOMAINS TO FILTER
    $.ajax({
        type: 'GET',
        url: 'https://crowd-src.herokuapp.com/domain',
        success: function(data) 
        { 
            $.getJSON("https://crowd-src.herokuapp.com/domain", function (data) 
            {
                if(c==1)
                {
                    $("#getdomain").append("<li class='use'>All</li>");
                    for (var i=0;i<data.TOTAL_NO_OF_DOMAINS;i++)
                    {
                        $("#getdomain").append("<li class='use'>"+data.DOMAIN_DETAILS[i].domain+"</li>");
                    }
                    var title;
                    //CLICK DOMAIN NAME AND THEN DISPLAY PENDING PROJECTS
					
					var y;
                    $('.use').click(function ()
                    {      
                        title=$(this).text();
                        console.log(title);
                        
                        if(title==='All')
                        {
                           location.reload();
                           
                            
                        }
                        //API TO GET ALL THE PROJECTS FOR A PERTICULAR DOMAIN
                        else{
                            
                            //location.reload();
                            //	alert(title);
                            //document.getElementById("page").innerHTML=pageloader;
                            
                            var x='https://crowd-src.herokuapp.com/upload/pendomain/'+title;
                            $.getJSON("https://crowd-src.herokuapp.com/upload/pendomain/"+title, function (data) 
                            {
                                y=data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_DOMAIN;
                            }
							)
						}  
                        
                        //console.log(x);
                        //CALLING THE API 
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
                                    //OPEN MODAL POPUP
                                    $(".modalop").click(function()
                                    {
                                        
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        console.log(x);
                                        document.getElementById("title").innerHTML=title;
                                        document.getElementById("mail").innerHTML=email;
                                        document.getElementById("desc").innerHTML=description;
                                        document.getElementById("domain").innerHTML=domain;
                                        document.getElementById("department").innerHTML=dept;
                                        document.getElementById("git").innerHTML=git;
                                        $('#appdomain').modal();

                                    })
                                    //APPROVE PROJECTS 
                                    $("#approve").click(function(){
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        console.log(x);
                                        $.ajax({
                                            type: 'PUT',
                                            data:  ({email: x, status: "approved"}),
                                            url: "https://crowd-src.herokuapp.com/upload/edit/"+id,
                                            success: function() {
                                             
                                              console.log('Trainer Updated Successfully!');
                                              location.reload(); 
                                            }
                                       
                                        })
                                
                                    })
                                    //DIAPPROVE PROJECTS
                                    $("#disapprove").click(function(){
                                        id=pid;
                                        console.log("dd");
                                        $.ajax({
                                            type: 'POST',
                                            data:({email:x}),
                                            url: "https://crowd-src.herokuapp.com/upload/delete/"+id,
                                            success: function() {
                                              location.reload(); 
                                              
                                            }
                                        })
                                    })                                    
                                })      
                            }
                        })
                    })
                } 
            })
        }
    })






    //GET ALL DEPARTMENT NAME
    $.ajax({
        type: 'GET',
        
        url: 'https://crowd-src.herokuapp.com/category',
        success: function(data) {
            $.getJSON('https://crowd-src.herokuapp.com/category', function (data) {
                if(c==1)
                {
                    for (var i=0;i<data.TOTAL_NO_OF_CATEGORY;i++){
                        $("#getdept").append("<li class='cat'>"+data.CATEGORY_DETAILS[i].category+"</li>");
                    }
                    //ONCLICK ON DEPARTMENT NAME GET ALL PENDING PROJECTS OF THAT DEPARTMENT
                    var title;
                    $('.cat').click(function ()
                    {
                        
                        title=$(this).text();
                        //API FOR GETTING PENDING PROJECTS OF THE DEPARTMENT
                        $.ajax({
                            type: 'GET',
                             
                            url: 'https://crowd-src.herokuapp.com/upload/pencategory/' + title,
                            success: function(data) 
                            { 
                                $.getJSON("https://crowd-src.herokuapp.com/upload/pencategory/" + title, function (data) 
                                {
                                    //PROJECTS IN THE FORM OF COLOURED CARDS
                                    var x="<section class='content'><div class='container-fluid'><div class='row clearfix'>";
                                    var z="</div></div></section>";
                                    for (var i=0;i<data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_CATEGORY;i++){
                                        
                                        g= document.createElement('div');
                                        console.log(data.COMPLETE_DETAILS[i].title);
                                        
                                        g.id = i;
                                        g.style.width ="100%";
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
                                    //OPEN MODAL POPUP
                                    $(".modalop").click(function()
                                    {
                                        
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        console.log(x);
                                        document.getElementById("title").innerHTML=title;
                                        document.getElementById("mail").innerHTML=email;
                                        document.getElementById("desc").innerHTML=description;
                                        document.getElementById("domain").innerHTML=domain;
                                        document.getElementById("department").innerHTML=dept;
                                        document.getElementById("git").innerHTML=git;
                                        $('#appdomain').modal();

                                    })
                                    //APPROVE PROJECTS
                                    $("#approve").click(function(){
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        console.log(x);
                                        $.ajax({
                                            type: 'PUT',
                                            data: ({email: x, status: "approved"}),
                                            url: "https://crowd-src.herokuapp.com/upload/edit/"+id,
                                            success: function() {
                                             
                                              console.log('Trainer Updated Successfully!');
                                              location.reload(); 
                                            }
                                       
                                        })
                                
                                    })
                                    //DISAPPROVE PROJECTS
                                    $("#disapprove").click(function(){
                                        alert("DD");
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        $.ajax({
                                            type: 'POST',
                                            data:({email:x}),
                                            url: "https://crowd-src.herokuapp.com/upload/edit/"+id,
                                            success: function() {
                                              location.reload(); 
                                              
                                            }
                                        })
                                    });
                                })
                            }    
                        })
                    }) 
                }     
            })
        }
    })
}         

