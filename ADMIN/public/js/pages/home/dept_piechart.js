window.onload=function(){
    $.ajax({
        type: 'GET',
        url: 'https://test-api-man.herokuapp.com/category',
        success: function(data) {
            $.getJSON('https://test-api-man.herokuapp.com/category', function (data) {
                var count=[];
                var name=[];
                for(var i=0;i<data.TOTAL_NO_OF_CATEGORY;i++){
                    $.ajax({
                        type='GET',
                        url='https://test-api-man.herokuapp.com/upload/newappcategory/' + data.CATEGORY_DETAILS[i].category,
                        success: function(data) {
                            $.getJSON('https://test-api-man.herokuapp.com/upload/newappcategory/' + data.CATEGORY_DETAILS[i].category, function (data) {
                                count.push(data.TOTAL_NO_OF_PROJECTS_UPLOADED_YET_TO_BE_APPROVED_OF_CATEGORY);
                                name.push(data.PROJECT_CATEGORY);                                                                
                                config = {
                                    type: 'pie',
                                    data: {
                                        datasets: [{
                                            data: count,
                                            backgroundColor: [
                                                "rgb(233, 30, 99)",
                                                "rgb(255, 193, 7)",
                                                "rgb(0, 188, 212)",
                                                "rgb(139, 195, 74)",
                                                "rgb(255, 0, 255)",
                                                "rgb(255, 0, 64)" ,
                                                " rgb(64, 0, 255)",
                                                "rgb(191, 0, 255)",
                                                "rgb(191, 0, 255)",
                                                "rgb(0, 255, 128)",
                                                "rgb(153, 0, 61)",
                                                "rgb(255, 102, 161)",
                                                "rgb(255, 163, 26)",
                                                "rgb(128, 0, 128)",
                                                "rgb(51, 0, 51)",
                                                "rgb(255, 102, 102)",
                                                "rgb(0, 38, 77)",
                                                "rgb(0, 136, 204)",
                                                "rgb(102, 153, 0)"
                                            ],
                                        }],
                                        labels: name
                                    },
                                    options: {
                                        responsive: true,
                                        legend: false
                                    }
                                }

                            })
                            

                        }
                    })
                }
            })
            
        }
    })
}





