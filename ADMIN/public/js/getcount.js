
window.onload=function(){
    $.ajax({
        type: 'GET',
        
       url: 'https://bms-icl-yoga.herokuapp.com/trainer',
       success: function(data) {
       
    
        $.getJSON('https://bms-icl-yoga.herokuapp.com/trainer', function (data) {
            
            var div = $('#userss');
            div.attr('data-from',0);
            div.attr('data-to',data.count);
            div.attr('data-speed',1000);
            div.attr('data-fresh-interval',20);
            //console.log(div.attr('data-to'));  
            $("#userss").html(div.attr('data-to')); 
            
        })
        
    }

})    

}
        