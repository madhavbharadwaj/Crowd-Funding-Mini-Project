//ADD ADMIN DYNAMICALLY TO THE TABLE WITH ID "admin" IN ADMIN.HTML
window.onload=function(){
    
    console.log("d");
    $.ajax({
        type: 'GET',
        url: 'https://crowd-src.herokuapp.com/admin',
        success: function(data) {
            console.log("x");
            $.getJSON("https://crowd-src.herokuapp.com/admin", function (data) {
                for(i=0;i<data.count;i++)
                {
                    $("#admin tbody").append("<tr> <td>"+(i+1)+" </td> <td>"+data.admin[i].Admin_details.username+"</td><td>"+data.admin[i].Admin_details.email+"</td><td>"+data.admin[i].Admin_details.mobile+"</td></tr>");
                }
            


            })
        }
        
    })
}
    
      
