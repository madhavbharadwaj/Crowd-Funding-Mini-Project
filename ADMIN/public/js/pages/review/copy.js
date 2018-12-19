
c=0;
window.onload=function(){

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
                    $("#getdomain").append("<li class='use'><a>All</a></li>");
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
                            var x='https://crowd-src.herokuapp.com/upload/pending';
                            $.getJSON("https://crowd-src.herokuapp.com/upload/pending", function (data) 
                            {
                                y=data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED;
                            })
                            
                        }
                        //API TO GET ALL THE PROJECTS FOR A PERTICULAR DOMAIN
                        else{
                            //location.reload();
						//	alert(title);
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
                                        var a="<div class='col-lg-4 col-md-4 col-sm-6 col-xs-12'><div class='card'><div class='header bg-pink'><h2>";
                                        var b="<small class='email'>";
                                        var c="</small></h2></div><div class='body'>";
                                        //ADD LIST APPROVE <LI CLASS="APPROVE"> AND DISAPPROVE <LI CALSS="DISAPROVE">
                                        var e="<ul class='header-dropdown m-r--5'><li class='dropdown'><a href='javascript:void(0);' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'><i class='material-icons'>more_vert</i></a><ul class='dropdown-menu pull-right'><li class='approve' ><a>APPROVE</a></li><li class='disapprove'><a>DISAPPROVE</a></li></ul>";
                                        var d="</div></div></div>";
                                        console.log("yes");
                                        var n="<h5>PROJECT DOMAIN :";
                                        var m="<h5>PROJECT STATUS :";
                                        var k="</h5><br>"
                                        document.body.appendChild(g);
                                        var abc = x+a+data.COMPLETE_DETAILS[i].title+b+data.COMPLETE_DETAILS[i].email+e+c+n+data.COMPLETE_DETAILS[i].domain+k+m+data.COMPLETE_DETAILS[i].status+k+d;
                                        document.getElementById(g.id).innerHTML=abc;
                                        
                                    }
                                    //APPROVE PROJECTS 
                                    $(".approve").click(function(){
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        console.log(x);
                                        $.ajax({
                                            type: 'PUT',
                                            data:  ({email: x, status: "approve"}),
                                            url: "https://crowd-src.herokuapp.com/upload/edit/"+id,
                                            success: function() {
                                             
                                              console.log('Trainer Updated Successfully!');
                                              location.reload(); 
                                            }
                                       
                                        })
                                
                                    })
                                    $(".disapprove").click(function(){
                                        id=pid;
                                        console.log("dd");
                                        $.ajax({
                                            type: 'DELETE',
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
                                        //g.style.margin.left= "1000px";
                                        
                                        var a="<div class='col-lg-4 col-md-4 col-sm-6 col-xs-12'><div class='card'><div class='header bg-pink'><h2>";
                                        var b="<small>";
                                        var c="</small></h2></div><div class='body'>";
                                        var pid=data.COMPLETE_DETAILS[i]._id;
                                        var email=data.COMPLETE_DETAILS[i].email;
                                        var e="<ul class='header-dropdown m-r--5'><li class='dropdown'><a href='javascript:void(0);' class='dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false'><i class='material-icons'>more_vert</i></a><ul class='dropdown-menu pull-right'><li class='approve'><a>APPROVE</a></li><li class='disapprove'><a'>DISAPPROVE</a></li></ul></li></ul>"
                                        var d="</div></div></div>";
                                        console.log("yes");
                                    
                                        document.body.appendChild(g);
                                        var abc = x+a+data.COMPLETE_DETAILS[i].title+b+data.COMPLETE_DETAILS[i].email+e+c+data.COMPLETE_DETAILS[i].description+data.COMPLETE_DETAILS[i].git_proj_link+d;
                                        document.getElementById(g.id).innerHTML=abc;
                                    }
                                    $(".approve").click(function(){
                                        id=pid;
                                        x=email;
                                        console.log(id);
                                        console.log(x);
                                        $.ajax({
                                            type: 'PUT',
                                            data: ({email: x, status: "approve"}),
                                            url: "https://crowd-src.herokuapp.com/upload/edit/"+id,
                                            success: function() {
                                             
                                              console.log('Trainer Updated Successfully!');
                                              location.reload(); 
                                            }
                                       
                                        })
                                
                                    })
                                    $(".disapprove").click(function(){
                                        alert("DD");
                                        id=pid;
                                        console.log(id);
                                        $.ajax({
                                            type: 'DELETE',
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
                                    