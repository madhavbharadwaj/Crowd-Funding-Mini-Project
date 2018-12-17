
window.onload=function(){
     alert("hi");
         $.ajax({
             type: 'GET',
             
            url: 'https://crowd-src.herokuapp.com/upload/pending',
            success: function(data) {
          
         
             $.getJSON('https://crowd-src.herokuapp.com/upload/pending', function (data) {
                    var x="<section class='content'><div class='container-fluid'><div class='row clearfix'>";
                    var z="</div></div></section>";
                    for (var i=0;i<data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED;i++){
                        g= document.createElement('div');
                        g.id = i;
                        //g.style.margin.left= "1000px";
                        
                        var a="<div class='col-lg-4 col-md-4 col-sm-6 col-xs-12'><div class='card'><div class='header bg-pink'><h2>";
                        var b="<small>";
                        var c="</small></h2></div><div class='body'>";
                        
                        var d="</div></div></div>";
                        console.log("yes");
                       
                        document.body.appendChild(g);
                        var abc = x+a+data.COMPLETE_DETAILS[i].title+b+data.COMPLETE_DETAILS[i].email+c+data.COMPLETE_DETAILS[i].description+data.COMPLETE_DETAILS[i].git_proj_link+d;
                        document.getElementById(g.id).innerHTML=abc;

                         
                         
                         
             } var y=abc+z;
             document.getElementById(g.id).innerHTML=y;
            })
       
             }
         })
     }