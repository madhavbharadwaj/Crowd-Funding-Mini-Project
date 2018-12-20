window.onload=function(){
    //TO GET THE NUMBER OF REGISTERED USERS
    $.ajax({

        type: 'GET',
        url: 'https://crowd-src.herokuapp.com/student',
        success: function(data) {   
            $.getJSON('https://crowd-src.herokuapp.com/student', function (data) {
                /* 
                var timer = $("#userss");
                timer.countTo({
                    from: 0,
                    to: timer.prop('data-to'),
                    speed: 1000,
                    refreshInterval: 50
                });
                 */
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
    // COUNT FOR APPROVED PROJECTS
    $.ajax({

        type: 'GET',
        url: 'https://crowd-src.herokuapp.com/upload/approved',
        success: function(data) {   
            $.getJSON('https://crowd-src.herokuapp.com/upload/approved', function (data) {
                /* 
                var timer = $("#userss");
                timer.countTo({
                    from: 0,
                    to: timer.prop('data-to'),
                    speed: 1000,
                    refreshInterval: 50
                });
                 */
                var div = $('#appproj');
                div.attr('data-from',0);
                div.attr('data-to',data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED);
                div.attr('data-speed',1000);
                div.attr('data-fresh-interval',20);
                //console.log(div.attr('data-to'));  
                $("#appproj").html(div.attr('data-to')); 
            
            })
        }
    })
    //COUNT FOR PENDING PROJECTS
    $.ajax({

        type: 'GET',
        url: 'https://crowd-src.herokuapp.com/upload/pending',
        success: function(data) {   
            $.getJSON('https://crowd-src.herokuapp.com/upload/pending', function (data) {
                /* 
                var timer = $("#userss");
                timer.countTo({
                    from: 0,
                    to: timer.prop('data-to'),
                    speed: 1000,
                    refreshInterval: 50
                });
                 */
                var div = $('#pendproj');
                
                div.attr('data-from',0);
                div.attr('data-to',data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED);
                div.attr('data-speed',1000);
                div.attr('data-fresh-interval',200);
                //console.log(div.attr('data-to')); 
                $("#pendproj").html(div.attr('data-to'));  
                
            
            })
        }
    })
    //NUMBER OF DEPARTMENTS AVAILABE
    $.ajax({

        type: 'GET',
        url: 'https://crowd-src.herokuapp.com/category',
        success: function(data) {   
            $.getJSON('https://crowd-src.herokuapp.com/category', function (data) {
                /* 
                var timer = $("#userss");
                timer.countTo({
                    from: 0,
                    to: timer.prop('data-to'),
                    speed: 1000,
                    refreshInterval: 50
                });
                 */
                var div = $('#pendproj');
                
                div.attr('data-from',0);
                div.attr('data-to',data.TOTAL_NO_OF_CATEGORY);
                div.attr('data-speed',1000);
                div.attr('data-fresh-interval',200);
                //console.log(div.attr('data-to')); 
                $("#noofdept").html(div.attr('data-to'));  
                
            
            })
        }
    })
        

}
        