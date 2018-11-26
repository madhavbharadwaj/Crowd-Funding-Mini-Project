c=0;
$('#domainc').click(function () {

    c++;
    // alert("hi");
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
     })