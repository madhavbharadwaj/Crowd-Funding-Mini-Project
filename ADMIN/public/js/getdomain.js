c=0;
b=0;
$('#domainc').click(function () {

    c++;
    b++;
    alert("hi");
         $.ajax({
             type: 'GET',
             
            url: 'https://test-api-man.herokuapp.com/domain',
            success: function(data) {
            
         
             $.getJSON("https://test-api-man.herokuapp.com/domain", function (data) {
                 if(c==1){
                    for (var i=0;i<data.TOTAL_NO_OF_DOMAINS;i++){
                        $("#getdomain").append("<li> <a href='pages/dept.html'>"+data.DOMAIN_DETAILS[i].domain+"</li>");
                 }
                 
     
             
             }
            })
       
             }
         })
         $.ajax({
            type: 'GET',
            
           url: 'https://test-api-man.herokuapp.com/category',
           success: function(data) {
           
        
            $.getJSON('https://test-api-man.herokuapp.com/category', function (data) {
                if(b==1)
                {
                    for (var i=0;i<data.TOTAL_NO_OF_CATEGORY;i++){
                        $("#getdept").append("<li> <a href='ICL.html'>"+data.CATEGORY_DETAILS[i].category+"</li>");
                }
               
    
            } 
           })
      
            }
        })
     })